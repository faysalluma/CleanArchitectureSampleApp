package com.groupec.cleanarchitecturesampleapp.core.data.repository


import com.groupec.cleanarchitecturesampleapp.core.model.data.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(orderId: Int) : Flow<List<Product>>
}