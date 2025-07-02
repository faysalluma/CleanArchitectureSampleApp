package com.groupec.cleanarchitecturesampleapp.appconfig

import com.groupec.cleanarchitecturesampleapp.BuildConfig
import com.groupec.cleanarchitecture.core.config.AppConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppConfigImpl @Inject constructor() : AppConfig {
    override val isDebug: Boolean = BuildConfig.DEBUG
    override val enableCrashReporting: Boolean
        get() = BuildConfig.ENABLE_CRASH_REPORTING
    override val baseUrl: String
        get() = BuildConfig.BASE_URL
    override val networkTimeoutSeconds: Int
        get() = BuildConfig.NETWORK_TIMEOUT_SECONDS
    override val featureXEnabled: Boolean
        get() = BuildConfig.FEATURE_X_ENABLED
    override val googleMapsApiKey: String
        get() = BuildConfig.GOOGLE_MAPS_API_KEY
    override val tokenExpirationTime: Long
        get() = BuildConfig.TOKEN_EXPIRATION_TIME
    override val defaultLocale: String
        get() = BuildConfig.DEFAULT_LOCALE
    override val analyticsUrl: String ? = null
    override val paymentApiUrl: String ? = null
    override val defaultTheme: String ? = null
}