package com.groupec.cleanarchitecturesampleapp

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose (
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }

    dependencies {
        val composeBom = libs.findLibrary("androidx.compose.bom").get()
        add("implementation", platform(composeBom))
        add("implementation", libs.findLibrary("androidx.ui").get())
        add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
        add("implementation", libs.findLibrary("androidx.material3").get())
        // Library for list detail pane
        add("implementation", libs.findLibrary("androidx.compose.material3.adaptive").get())
        add("implementation", libs.findLibrary("androidx.compose.material3.adaptive.layout").get())
        add("implementation", libs.findLibrary("androidx.compose.material3.adaptive.navigation").get())
        add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
    }

}