package com.groupec.cleanarchitecture.core.config

interface AppConfig {
    val isDebug: Boolean
    val enableCrashReporting: Boolean // For CrashLytics
    val baseUrl: String
    val networkTimeoutSeconds: Int
    val featureXEnabled: Boolean
    val googleMapsApiKey: String
    val tokenExpirationTime: Long
    val defaultLocale: String
    val analyticsUrl: String?
    val paymentApiUrl: String?
    val defaultTheme: String?
}