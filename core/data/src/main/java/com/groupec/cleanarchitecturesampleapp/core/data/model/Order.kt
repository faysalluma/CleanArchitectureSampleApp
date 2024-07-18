package com.groupec.cleanarchitecturesampleapp.core.data.model

import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.network.model.OrderItemResponse
import com.groupec.cleanarchitecturesampleapp.core.network.model.OrderResponse

fun OrderResponse.toOrderList(): List<Order> {
    return commands.map { it.toOrder() }
}

fun OrderItemResponse.toOrder(): Order {
    return Order(id = id, datecreation = datecreation, customerName = customerName)
}