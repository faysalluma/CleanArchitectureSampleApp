package com.groupec.cleanarchitecturesampleapp.feature.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupec.cleanarchitecturesampleapp.core.Result
import com.groupec.cleanarchitecturesampleapp.core.asResult
import com.groupec.cleanarchitecturesampleapp.core.domain.GetProductUseCase
import com.groupec.cleanarchitecturesampleapp.core.model.data.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private val _productUiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val productUiState: StateFlow<ProductUiState> = _productUiState

    init {
        // getProducts()
    }
    fun getProducts(orderId: Int) {
        viewModelScope.launch {
            getProductUseCase(orderId)
                .asResult()
                .collect { result ->
                    _productUiState.value = when (result) {
                        is Result.Loading-> ProductUiState.Loading
                        is Result.Success -> {
                            if (result.data.isEmpty()){
                                ProductUiState.Empty
                            } else {
                                ProductUiState.Success(result.data)
                            }
                        }
                        is Result.Error -> ProductUiState.Error( result.exception.message ?: "Retrofit Unknown error")
                    }
                }
        }
    }
}

sealed class ProductUiState {
    data object Loading : ProductUiState()
    data class Success(val products: List<Product>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
    data object Empty : ProductUiState()
}
