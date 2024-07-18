package com.groupec.cleanarchitecturesampleapp.core.data.repository

import com.groupec.cleanarchitecturesampleapp.core.data.model.toOrder
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.network.retrofit.ApiService
import com.groupec.cleanarchitecturesampleapp.core.network.retrofit.common.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepositoryImpl @Inject constructor(private val apiService: ApiService) : OrderRepository {
    override fun getOrders(): Flow<List<Order>> = flow {
        val result = safeApiCall(
            apiCall = { apiService.getOrders() },
            transform = { response ->
                response.commands.map { it.toOrder() }
            }
        )
        emit(result)
    }.flowOn(Dispatchers.IO)
}