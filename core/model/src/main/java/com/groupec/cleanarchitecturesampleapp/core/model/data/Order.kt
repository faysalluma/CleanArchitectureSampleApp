package com.groupec.cleanarchitecturesampleapp.core.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Order(val id: Int, val datecreation: Date, val customerName: String) : Parcelable