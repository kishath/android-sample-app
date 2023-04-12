package com.example.kishath.productlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kishath.core.domain.UseCaseResult
import com.example.kishath.productlist.domain.model.Product
import com.example.kishath.productlist.domain.usecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun loadProducts() {
        viewModelScope.launch {
            getAllProductsUseCase(Unit).collect { result ->
                _uiState.emit(
                    when (result) {
                        is UseCaseResult.Error -> UiState.Error(result.exception)
                        UseCaseResult.Loading -> UiState.Loading
                        is UseCaseResult.Success -> UiState.Success(result.data)
                    }
                )
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val products: List<Product>) : UiState()
        data class Error(val exception: Exception) : UiState()
    }
}