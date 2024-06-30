plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.hilt)
}

android {
    namespace = "com.groupec.cleanarchitecturesampleapp.core.database"
}

dependencies {

    // Modules calls
    implementation(project(":core:model"))

    // Test
    implementation(project(":core:testing"))
}