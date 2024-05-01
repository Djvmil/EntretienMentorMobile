plugins {
    id("djvmil.e-mentor.library")
}

android {
    namespace = "com.djvmil.entretienmentor.core.common"
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.tink.android)
    testImplementation(projects.core.testing)
}