package com.groupec.cleanarchitecturesampleapp.core.data.repository

import com.groupec.cleanarchitecturesampleapp.core.data.model.toOrder
import com.groupec.cleanarchitecturesampleapp.core.data.model.toProduct
import com.groupec.cleanarchitecturesampleapp.core.model.data.Product
import com.groupec.cleanarchitecturesampleapp.core.network.retrofit.ApiService
import com.groupec.cleanarchitecturesampleapp.core.network.retrofit.common.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(private val apiService: ApiService) : ProductRepository {
    override fun getProducts(orderId: Int): Flow<List<Product>> = flow {
        val result = safeApiCall(
            apiCall = { apiService.getProducts(orderId) },
            transform = { response ->
                response.products.map { it.toProduct() }
            }
        )
        emit(result)
    }.flowOn(Dispatchers.IO)
}