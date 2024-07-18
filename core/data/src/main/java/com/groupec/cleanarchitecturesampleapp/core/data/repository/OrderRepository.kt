package com.groupec.cleanarchitecturesampleapp.core.data.repository


import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository{
    fun getOrders() : Flow<List<Order>>
}