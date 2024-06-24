plugins {
    alias(libs.plugins.common.android.library)
}

android {
    namespace = "com.groupec.cleanarchitecturesampleapp.core.domain"
}

dependencies {

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
}