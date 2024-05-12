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
        alias(libs.plugins.module.graph) apply true // Plugin applied to allow module graph generation
        alias(libs.plugins.dependencyGuard) apply false
        alias(libs.plugins.version.catalog.update)
        alias(libs.plugins.ben.manes.versions)

    }
}
apply("${project.rootDir}/gradle/toml-updater-config.gradle")

tasks.register("clean")
    .configure {
        delete(rootProject.buildDir)
    }
