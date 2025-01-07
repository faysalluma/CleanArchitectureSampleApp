package com.groupec.cleanarchitecturesampleapp.core.data

import com.groupec.cleanarchitecturesampleapp.core.data.model.toOrderList
import com.groupec.cleanarchitecturesampleapp.core.data.repository.OrderRepositoryImpl
import com.groupec.cleanarchitecturesampleapp.core.network.model.OrderItemResponse
import com.groupec.cleanarchitecturesampleapp.core.network.model.OrderResponse
import com.groupec.cleanarchitecturesampleapp.core.network.retrofit.ApiService
import com.groupec.cleanarchitecturesampleapp.core.toDate
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import retrofit2.Response

class OrderRepositoryTest {

    private val apiService: ApiService = mockk()
    private lateinit var orderRepository: OrderRepositoryImpl

    @BeforeEach
    fun setup() {
        orderRepository = OrderRepositoryImpl(apiService)
    }

    @Test
    fun `getOrders should return list of orders when api call is successful`() = runTest {
        // Arrange
        val orderResponse = OrderResponse(
            arrayListOf(
                OrderItemResponse(
                    id = 1,
                    datecreation = "2025-01-01 10:22:00".toDate()!!,
                    customerName = "Franck LASSO"
                ),
                OrderItemResponse(
                    id = 2,
                    datecreation = "2025-01-12 15:12:10".toDate()!!,
                    customerName = "Marc LUTA"
                )
            )
        )
        coEvery { apiService.getOrders() } returns Response.success(orderResponse)

        // Act
        val result = orderRepository.getOrders().first()

        // Assert
        assertEquals(orderResponse.toOrderList(), result)
    }

    @Test
    fun `getOrders should throw an exception when API call fails`() = runTest {
        // Arrange
        val exception = Exception("Failed to get orders")
        coEvery { apiService.getOrders() } throws exception

        // Act
        val result = orderRepository.getOrders()

        // Assert
        assertThrows<Exception> {
            result.first()
        }.also { thrown ->
            assertEquals("Failed to get orders", thrown.message)
        }
    }

}