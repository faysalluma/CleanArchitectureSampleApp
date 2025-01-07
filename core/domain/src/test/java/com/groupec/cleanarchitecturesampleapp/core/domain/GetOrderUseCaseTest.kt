package com.groupec.cleanarchitecturesampleapp.core.domain

import com.groupec.cleanarchitecturesampleapp.core.data.repository.OrderRepository
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import com.groupec.cleanarchitecturesampleapp.core.toDate
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class GetOrderUseCaseTest {

    private lateinit var getOrderUseCase: GetOrderUseCase
    private val orderRepository: OrderRepository = mockk()

    @BeforeEach
    fun setup() {
        getOrderUseCase = GetOrderUseCase(orderRepository)
    }

    @Test
    fun `invoke should emit list of orders when repository returns data`() = runTest {
        // Arrange (Here you create all of the objects and variables you require for your test)
        val expectedOrders = listOf(
            Order(1, "2022-10-02 10:00:03".toDate()!!, "Jean"),
            Order(2, "2022-10-02 10:00:03".toDate()!!, "Patrice")
        )
        every { orderRepository.getOrders() } returns flowOf(expectedOrders)

        // Act (you perform the behavior you wish to test)
        val result = getOrderUseCase().first()

        // Assert (you test that the final result)
        assertEquals(expectedOrders, result)
    }

    @Test
    fun `invoke should emit empty list when repository returns no data`() = runTest {
        // Arrange
        every { orderRepository.getOrders() } returns flowOf(emptyList())

        // Act
        val result = getOrderUseCase().first()

        // Assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke should throw exception when repository throws an error`() = runTest {
        // Arrange
        val exception = RuntimeException("Failed to fetch orders")
        every { orderRepository.getOrders() } returns flow { throw exception }

        // Act
        val result = getOrderUseCase()

        // Assert
        assertThrows<Exception> {
            result.first()
        }.also { thrown ->
            assertEquals("Failed to fetch orders", thrown.message)
        }
    }
}
