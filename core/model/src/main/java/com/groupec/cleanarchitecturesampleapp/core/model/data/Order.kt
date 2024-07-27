package com.groupec.cleanarchitecturesampleapp.core.model.data

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Order(val id: Int, val datecreation: Date, val customerName: String) : Parcelable