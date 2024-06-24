package com.groupec.cleanarchitecturesampleapp.core.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(val id: Long, val full_name: String, val description: String?, val languages_url: String,
    val stargazers_url: String, val branches_url : String, val contributors_url: String) : Parcelable {
    companion object NavigationType : NavType<Repository>(isNullableAllowed = true) {
        override fun get(bundle: Bundle, key: String): Repository? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): Repository {
            return Gson().fromJson(value, Repository::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: Repository) {
            bundle.putParcelable(key, value)
        }
    }
}