buildscript{
    repositories {
        google()
        //maven("https://plugins.gradle.org/m2/")
    }
    dependencies{
        classpath(libs.spotless.plugin.gradle)

    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    libs.plugins.apply {
        alias(android.application) apply false
        alias(libs.plugins.android.library) apply false
        alias(libs.plugins.android.test) apply false
        alias(kotlin.serialization) apply false
        alias(sqldelight) apply false
        alias(libs.plugins.gms) apply false
        alias(libs.plugins.ksp) apply false
        alias(libs.plugins.dependencyGuard) apply false
        alias(libs.plugins.module.graph) apply true // Plugin applied to allow module graph generation
        alias(libs.plugins.spotless).apply(false)
        alias(libs.plugins.version.catalog.update)
        alias(libs.plugins.ben.manes.versions)

    }
    //apply("${project.rootDir}/gradle/toml-updater-config.gradle.kts")
}



subprojects{

    afterEvaluate{
        //Assign the path to the spotless gradle file depending on the project where you are located
        val pathSpotless = if(this.path.contains(":core:", true))
            "../../spotless.gradle"
        else
            "../spotless.gradle"


        project.apply(pathSpotless)
    }

    //plugins.matching { anyPlugin -> anyPlugin is AppPlugin || anyPlugin is LibraryPlugin }.whenPluginAdded {
    //    apply(plugin = libs.plugins.spotless.get().pluginId)
    //    extensions.configure<SpotlessExtension> {
    //        kotlin {
    //            target("**/*.kt")
    //            targetExclude("$buildDir/**/*.kt")
    //            ktlint()
    //                .setEditorConfigPath("${project.rootDir}/spotless/.editorconfig")
    //        }
    //    }
    //}
}

task("addPreCommitGitHookOnBuild") {
    println("⚈ ⚈ ⚈ Running Add Pre Commit Git Hook Script on Build ⚈ ⚈ ⚈")
   // exec {
        //commandLine("cp", "./.scripts/pre-commit", "./.git/hooks")
        //commandLine("cp", "./spotless/spotless.sh", "./.git/hooks")
   // }
    println("✅ Added Pre Commit Git Hook Script.")
}
tasks.register("clean")
    .configure {
        delete(rootProject.buildDir)
    }
