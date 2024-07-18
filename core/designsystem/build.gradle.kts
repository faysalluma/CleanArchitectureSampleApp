plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.library.compose)
}

android {
    namespace = "com.groupec.cleanarchitecturesampleapp.core.designsystem"
}

dependencies {
    implementation(libs.androidx.hilt.navigation.compose)
}