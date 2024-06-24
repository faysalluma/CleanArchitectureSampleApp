plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.hilt)
}

android {
    namespace = "com.groupec.cleanarchitecturesampleapp.core.data"
}

dependencies {

    // Modules calls
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
    implementation(project(":core:testing"))

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime)

    // Retrofit
    implementation(libs.retrofit.converterGson)

    // Test
    testImplementation(libs.kotlinx.coroutines.test)

}