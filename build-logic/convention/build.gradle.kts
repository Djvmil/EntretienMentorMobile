import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.djvmil.entretienmentor.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "djvmil.e-mentor.app"
            implementationClass = "EMentorAppConventionPlugin"
        }
        register("androidAppCompose") {
            id = "djvmil.e-mentor.app.compose"
            implementationClass = "EMentorAppComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "djvmil.e-mentor.library"
            implementationClass = "EMentorLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "djvmil.e-mentor.library.compose"
            implementationClass = "EMentorLibraryComposeConventionPlugin"
        }
        register("androidCore") {
            id = "djvmil.e-mentor.core"
            implementationClass = "EMentorCoreConventionPlugin"
        }
        register("androidTest") {
            id = "djvmil.e-mentor.test"
            implementationClass = "EMentorTestConventionPlugin"
        }
        register("androidFlavors") {
            id = "djvmil.e-mentor.app.flavors"
            implementationClass = "EMentorAppFlavorsConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = "djvmil.e-mentor.app.jacoco"
            implementationClass = "EMentorAppJacocoConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = "djvmil.e-mentor.library.jacoco"
            implementationClass = "EMentorLibraryJacocoConventionPlugin"
        }
    }
}