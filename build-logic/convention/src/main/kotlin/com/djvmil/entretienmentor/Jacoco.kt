package com.djvmil.entretienmentor

import com.android.build.api.artifact.ScopedArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ScopedArtifacts
import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.util.Locale

private val coverageExclusions = listOf(
    // Android
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*"
)

private fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}

/**
 * Creates a new task that generates a combined coverage report with data from local and
 * instrumented tests.
 *
 * `create{variant}CombinedCoverageReport`
 *
 * Note that coverage data must exist before running the task. This allows us to run device
 * tests on CI using a different Github Action or an external device farm.
 */
internal fun Project.configureJacoco(
    androidComponentsExtension: AndroidComponentsExtension<*, *, *>,
) {
    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacoco").get().toString()
    }

    androidComponentsExtension.onVariants { variant ->
        println("jacoco: variant.name = ${variant.name}")
        val myObjFactory = project.objects
        println("jacoco: project.objects = ${project.objects}")
        val buildDir = layout.buildDirectory.get().asFile
        val allJars: ListProperty<RegularFile> = myObjFactory.listProperty(RegularFile::class.java)
        val allDirectories: ListProperty<Directory> = myObjFactory.listProperty(Directory::class.java)

        println("jacoco: allDirectories = ${allDirectories}")
        val reportTask =
            tasks.register("create${variant.name.capitalize()}CombinedCoverageReport", JacocoReport::class) {

                classDirectories.setFrom(
                    allJars,
                    allDirectories.map { dirs ->

                        dirs.map { dir ->
                            val dirV = myObjFactory.fileTree().setDir(dir).exclude(coverageExclusions)
                            println("jacoco: dirV = ${dirV}")
                            dirV
                        }
                    }
                )
                reports {
                    xml.required.set(true)
                    html.required.set(true)
                }

                // TODO: This is missing files in src/debug/, src/prod, src/demo, src/demoDebug...
                sourceDirectories.setFrom(files("$projectDir/src/main/java", "$projectDir/src/main/kotlin"))

                println("jacoco: dirV = $buildDir/outputs/unit_test_code_coverage/${variant.name}UnitTest")
                executionData.setFrom(
                    project.fileTree("$buildDir/outputs/unit_test_code_coverage/${variant.name}UnitTest")
                        .matching { include("**/*.exec") },

                    project.fileTree("$buildDir/outputs/code_coverage/${variant.name}AndroidTest")
                        .matching { include("**/*.ec") }
                    )
            }


        variant.artifacts.forScope(ScopedArtifacts.Scope.PROJECT)
            .use(reportTask)
            .toGet(
                ScopedArtifact.CLASSES,
                { _ -> allJars },
                { _ -> allDirectories },
            )
    }

    tasks.withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            // Required for JaCoCo + Robolectric
            // https://github.com/robolectric/robolectric/issues/2230
            isIncludeNoLocationClasses = true

            // Required for JDK 11 with the above
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = listOf("jdk.internal.*")
        }
    }
}
