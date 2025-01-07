package com.groupec.cleanarchitecturesampleapp.core.data.repository

import com.groupec.cleanarchitecturesampleapp.core.data.model.toOrderList
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
class OrderRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    OrderRepository {
    override fun getOrders(): Flow<List<Order>> = flow {
        val result = safeApiCall(
            apiCall = { apiService.getOrders() },
            transform = { response ->
                response.toOrderList()
            }
        )
        emit(result)
    }.flowOn(Dispatchers.IO)

    /*override fun getRandom(): Flow<NetworkResult<List<Order>>> = flow {
        try {
            val response = apiService.getRandomDog()
            if (response.isSuccessful) {
                val orders = response.body()?.commands?.map { it.toOrder() } ?: emptyList()
                emit(NetworkResult.Success(orders))
            } else {
                emit(NetworkResult.Error(HttpException(response)))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }.flowOn(Dispatchers.IO)*/
}