package com.groupec.cleanarchitecturesampleapp.core.network.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class OrderResponse(
    @SerializedName("commands")
    val commands : ArrayList<OrderItemResponse>
)
data class OrderItemResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("datecreation")
    val datecreation: Date,

    @SerializedName("fullname")
    val customerName: String,
)