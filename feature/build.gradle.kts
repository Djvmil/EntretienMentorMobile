plugins {
    alias(libs.plugins.djvmil.ementor.core)
    alias(libs.plugins.djvmil.ementor.library.compose)
}

android {
    namespace = "com.djvmil.entretienmentor.feature"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)

    testImplementation(projects.core.testing)
    androidTestImplementation(projects.core.testing)
}