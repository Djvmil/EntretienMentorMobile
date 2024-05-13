import com.djvmil.entretienmentor.EMBuildType

plugins {
    alias(libs.plugins.djvmil.ementor.app)
    alias(libs.plugins.djvmil.ementor.app.compose)
    alias(libs.plugins.djvmil.ementor.app.flavors)
    alias(libs.plugins.module.graph) apply true // Plugin applied to allow module graph generation
    alias(libs.plugins.djvmil.ementor.app.jacoco)
}

//apply("$project.rootDir/tools/script-jacoco.gradle")

android {
    namespace = "com.djvmil.entretienmentor"

    defaultConfig {
        applicationId = "com.djvmil.entretienmentor"
        versionCode = 2
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        //testInstrumentationRunner = "com.djvmil.entretienmentor.core.testing.EMTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = EMBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = EMBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.named("debug").get()

        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
            merges += "META-INF/INDEX.LIST"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
/*
    project.gradle.addBuildListener(object: BuildListener {
        override fun beforeSettings(settings: Settings) {
            super.beforeSettings(settings)
            println("⚈ ⚈ ⚈ beforeSettings Tasks ⚈ ⚈ ⚈")
        }

        override fun settingsEvaluated(settings: Settings) {
            println("⚈ ⚈ ⚈ settingsEvaluated Tasks ⚈ ⚈ ⚈")
        }

        override fun projectsLoaded(gradle: Gradle) {
            println("⚈ ⚈ ⚈ projectEvaluated Tasks ⚈ ⚈ ⚈")
        }

        override fun projectsEvaluated(gradle: Gradle) {
            println("⚈ ⚈ ⚈ projectsEvaluated Tasks ⚈ ⚈ ⚈")
        }

        override fun buildFinished(result: BuildResult) {
            println("⚈ ⚈ ⚈ buildFinished Tasks ⚈ ⚈ ⚈")
        }
    })*/
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.feature)
    implementation(projects.core.ui)
    implementation(projects.core.data)
    implementation(projects.core.domain)

    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.navigation)

    implementation(libs.androidx.core.splashscreen)

    implementation(libs.koin.workmanager)
    testImplementation(projects.core.testing)
    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.navigation.testing)
}

// androidx.test is forcing JUnit, 4.12. This forces it to use 4.13
configurations.configureEach {
    resolutionStrategy {
        force(libs.junit4)
        // Temporary workaround for https://issuetracker.google.com/174733673
        force("org.objenesis:objenesis:2.6")
    }
}

dependencyGuard {
    configuration("releaseRuntimeClasspath")
    //configuration("prodReleaseRuntimeClasspath")
}

moduleGraphAssert {
    maxHeight = 4
    allowed = arrayOf(
       // ":.* -> :domain:.*",
       // ":.* -> :core:.*",
    )
    restricted = arrayOf(
       // ":core:.* -X> :feature",
        ":feature:.* -X> :feature:.*"
    )
    assertOnAnyBuild = false
}