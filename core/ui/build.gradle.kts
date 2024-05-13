plugins {
    alias(libs.plugins.djvmil.ementor.library)
    alias(libs.plugins.djvmil.ementor.library.compose)
    alias(libs.plugins.djvmil.ementor.library.jacoco)
}
//apply("$project.rootDir/tools/script-jacoco.gradle")3

android {
    namespace = "com.djvmil.entretienmentor.core.ui"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
            merges += "META-INF/INDEX.LIST"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.constraintlayout)
    api(libs.androidx.material.icons.extended)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)
    api(libs.compose.coil)

    api(libs.material)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.graphics.shapes)
    api(libs.androidx.compose.ui.tooling.preview)

    debugApi(libs.androidx.compose.ui.tooling)

   androidTestImplementation(projects.core.testing)
}