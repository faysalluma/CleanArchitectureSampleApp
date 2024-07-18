package com.groupec.cleanarchitecturesampleapp.feature.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupec.cleanarchitecturesampleapp.core.Result
import com.groupec.cleanarchitecturesampleapp.core.asResult
import com.groupec.cleanarchitecturesampleapp.core.domain.GetOrderUseCase
import com.groupec.cleanarchitecturesampleapp.core.model.data.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getOrderUseCase: GetOrderUseCase) : ViewModel() {

    private val _orderUiState = MutableStateFlow<OrderUiState>(OrderUiState.Loading)
    val orderUiState: StateFlow<OrderUiState> = _orderUiState

    init {
        getOrders()
    }
    fun getOrders() {
        viewModelScope.launch {
            getOrderUseCase()
                .asResult()
                .collect { result ->
                    _orderUiState.value = when (result) {
                        is Result.Loading-> OrderUiState.Loading
                        is Result.Success -> {
                            if (result.data.isEmpty()){
                                OrderUiState.Empty
                            } else {
                                OrderUiState.Success(result.data)
                            }
                        }
                        is Result.Error -> OrderUiState.Error( result.exception.message ?: "Retrofit Unknown error")
                    }
                }
        }
    }
}

sealed class OrderUiState {
    data object Loading : OrderUiState()
    data class Success(val orders: List<Order>) : OrderUiState()
    data class Error(val message: String) : OrderUiState()
    data object Empty : OrderUiState()
}
