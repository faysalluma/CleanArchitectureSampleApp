package com.groupec.cleanarchitecturesampleapp.core.model.data

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Order(val id: Int, val datecreation: Date, val customerName: String) : Parcelable /*{
    companion object NavigationType : NavType<Order>(isNullableAllowed = true) {
        override fun get(bundle: Bundle, key: String): Order? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): Order {
            return Gson().fromJson(value, Order::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: Order) {
            bundle.putParcelable(key, value)
        }
    }
}
*/