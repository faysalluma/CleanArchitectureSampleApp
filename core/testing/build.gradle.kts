plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.library.compose)
    alias(libs.plugins.common.android.hilt)
}

android {
    namespace = "com.groupec.cleanarchitecturesampleapp.core.testing"
}

dependencies {

    api(kotlin("test"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.coroutines.test)
}