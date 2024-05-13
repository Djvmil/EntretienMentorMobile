plugins {
    alias(libs.plugins.djvmil.ementor.library)
    alias(libs.plugins.djvmil.ementor.library.jacoco)
}
//apply("$project.rootDir/tools/script-jacoco.gradle")

android {
    namespace = "com.djvmil.entretienmentor.core.domain"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)

    testImplementation(projects.core.testing)

    implementation(libs.koin.android)

}