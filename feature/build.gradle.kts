plugins {
    alias(libs.plugins.djvmil.ementor.core)
    alias(libs.plugins.djvmil.ementor.library.compose)
    alias(libs.plugins.djvmil.ementor.library.jacoco)
}
//apply("$project.rootDir/tools/script-jacoco.gradle")

android {
    namespace = "com.djvmil.entretienmentor.feature"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)

    testImplementation(projects.core.testing)
    androidTestImplementation(projects.core.testing)
}