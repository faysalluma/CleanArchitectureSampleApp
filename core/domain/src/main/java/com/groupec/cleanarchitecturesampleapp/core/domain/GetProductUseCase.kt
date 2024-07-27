package com.groupec.cleanarchitecturesampleapp.core.domain

import com.groupec.cleanarchitecturesampleapp.core.data.repository.ProductRepository
import com.groupec.cleanarchitecturesampleapp.core.model.data.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    operator fun invoke(orderId: Int): Flow<List<Product>> = productRepository.getProducts(orderId)
}