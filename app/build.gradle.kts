plugins {
    alias(libs.plugins.common.android.application)
    alias(libs.plugins.common.android.application.compose)
    alias(libs.plugins.common.android.hilt)

    // Make sure that you have the Google services Gradle plugin
    alias(libs.plugins.google.services)
    // Add the Crashlytics Gradle plugin
    alias(libs.plugins.firebase.crashlytics)
}

val vcode = (((System.currentTimeMillis() / 1000) - 1451606400) / 10).toInt()

android {
    namespace = "com.groupec.cleanarchitecturesampleapp"

    defaultConfig {
        applicationId = "com.groupec.cleanarchitecturesampleapp"
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = vcode
        versionName = libs.versions.versionName.get()

        vectorDrawables {
            useSupportLibrary = true
        }
        buildFeatures {
            buildConfig = true
        }
    }

    buildTypes {
        debug {
            // applicationIdSuffix = ".debug"
            // Definies config data
            buildConfigField("boolean", "ENABLE_CRASH_REPORTING", "false")
            buildConfigField("String", "BASE_URL", "\"https://sampleapp.groupec.net/public/\"")
            buildConfigField("int", "NETWORK_TIMEOUT_SECONDS", "30")
            buildConfigField("boolean", "FEATURE_X_ENABLED", "true")
            buildConfigField("String", "GOOGLE_MAPS_API_KEY", "\"DEBUG_API_KEY\"")
            buildConfigField("long", "TOKEN_EXPIRATION_TIME", "3600L")
            buildConfigField("String", "DEFAULT_LOCALE", "\"en_US\"")
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug") // Disable signing for release builds

            // Definies config data
            buildConfigField("boolean", "ENABLE_CRASH_REPORTING", "true")
            buildConfigField("String", "BASE_URL", "\"https://sampleapp.groupec.net/public/\"")
            buildConfigField("int", "NETWORK_TIMEOUT_SECONDS", "60")
            buildConfigField("boolean", "FEATURE_X_ENABLED", "false")
            buildConfigField("String", "GOOGLE_MAPS_API_KEY", "\"RELEASE_API_KEY\"")
            buildConfigField("long", "TOKEN_EXPIRATION_TIME", "7200L")
            buildConfigField("String", "DEFAULT_LOCALE", "\"fr_FR\"")

        }
    }

    /*flavorDimensions += listOf("version")
    productFlavors {
        // Dimension : version
        create("free") {
            dimension = "version"
            buildConfigField("boolean", "FEATURE_X_ENABLED", "false")
        }
        create("paid") {
            dimension = "version"
            buildConfigField("boolean", "FEATURE_X_ENABLED", "true")
        }
    }*/

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:detail"))

    implementation(project(":core:common"))
    implementation(project(":core:config"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.retrofit.converterGson)

    ksp(libs.hilt.compiler)
    kspTest(libs.hilt.compiler)
    testImplementation(libs.hilt.android.testing)

    testImplementation(kotlin("test"))
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)

    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom))

    // Add the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
}
