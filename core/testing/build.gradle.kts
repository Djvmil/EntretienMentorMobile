plugins {
    alias(libs.plugins.djvmil.ementor.library)
    alias(libs.plugins.djvmil.ementor.library.compose)
    alias(libs.plugins.djvmil.ementor.library.jacoco)
}
//apply("$project.rootDir/tools/script-jacoco.gradle")

android {
    namespace = "com.djvmil.entretienmentor.core.testing"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(kotlin("test"))
    implementation(projects.core.common)
    implementation(projects.core.data)

    api(libs.androidx.compose.ui.test)
    api(libs.truth)
    api(libs.turbine)
    api(libs.logback.classic)
    api(libs.robolectric)
    api(libs.mockk)
    api(libs.koin.test.junit4)
    api(libs.kotlinx.coroutines.test)

    debugApi(libs.androidx.compose.ui.test.manifest)

    implementation(libs.androidx.test.rules)
    implementation(libs.kotlinx.datetime)
}