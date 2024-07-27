package com.groupec.cleanarchitecturesampleapp.core.network.retrofit

import com.groupec.cleanarchitecturesampleapp.core.network.model.OrderResponse
import com.groupec.cleanarchitecturesampleapp.core.network.model.ProductResponse
import com.groupec.cleanarchitecturesampleapp.core.network.retrofit.common.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(Constants.GET_ORDERS)
    suspend fun getOrders() : Response<OrderResponse>

    @GET(Constants.GET_PRODUCTS)
    suspend fun getProducts(@Path("orderid") orderid: Int) : Response<ProductResponse>
}