package com.groupec.cleanarchitecturesampleapp.core.network.retrofit

import com.groupec.cleanarchitecturesampleapp.core.network.model.OrderResponse
import com.groupec.cleanarchitecturesampleapp.core.network.retrofit.common.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.GET_ORDER)
    suspend fun getOrders() : Response<OrderResponse>
}