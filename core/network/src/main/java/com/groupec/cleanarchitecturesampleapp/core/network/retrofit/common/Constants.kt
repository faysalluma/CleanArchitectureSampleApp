package com.groupec.cleanarchitecturesampleapp.core.network.retrofit.common

class Constants {
    companion object {
        const val BASE_URL = "https://sampleapp.groupec.net/public/"

        // Get endpoint
        const val GET_ORDERS = "commands"
        // Get endpoint
        const val GET_PRODUCTS = "products/{orderid}"
    }
}