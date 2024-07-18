package com.groupec.cleanarchitecturesampleapp.core.domain

import com.groupec.cleanarchitecturesampleapp.core.data.repository.OrderRepository
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(private val orderRepository : OrderRepository) {
    operator fun invoke(): Flow<List<Order>> = orderRepository.getOrders()
}