plugins {
    alias(libs.plugins.common.android.library)
}

android {
    namespace = "com.groupec.cleanarchitecturesampleapp.core.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    testImplementation(kotlin("test"))
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
}