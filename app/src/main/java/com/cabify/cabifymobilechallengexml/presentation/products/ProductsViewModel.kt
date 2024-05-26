package com.cabify.cabifymobilechallengexml.presentation.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabify.cabifymobilechallengexml.domain.usecases.GetProductsUseCase
import com.cabify.cabifymobilechallengexml.presentation.mappers.toVo
import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo
import com.cabify.cabifymobilechallengexml.presentation.models.ProductsVo
import com.cabify.cabifymobilechallengexml.presentation.utils.getPrice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    @Named("DISPATCHER_IO") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _products = MutableStateFlow<List<CabifyProductVo>>(emptyList())
    val products: StateFlow<List<CabifyProductVo>> = _products

    private val _totalCount = MutableStateFlow(0)
    val totalCount: StateFlow<Int> = _totalCount

    private val _selectedProducts: MutableStateFlow<ProductsVo?> = MutableStateFlow(null)
    val selectedProducts: StateFlow<ProductsVo?> = _selectedProducts

    private val _navigate = MutableStateFlow(false)
    val navigate: StateFlow<Boolean> = _navigate

    fun getProducts() = viewModelScope.launch(ioDispatcher) {
        if (selectedProducts.value == null) {
            getProductsUseCase.invoke().collect { products ->
                _products.value = products.map { it.toVo() }
            }
        }
    }

    fun updateTotalCount(products: List<CabifyProductVo>) {
        _products.value = products
        _totalCount.value = products.sumOf { it.count }
    }

    fun updateSelectedProductsList(products: List<CabifyProductVo>) {
        val selectedProductsList = products.filter { it.count > 0 }
        val selectedProducts = ProductsVo(
            products = selectedProductsList,
            totalPrice = selectedProductsList.getPrice()
        )
        _selectedProducts.value = selectedProducts
        _navigate.value = !_navigate.value
    }

    fun updateNavigate(navigate: Boolean) {
        _navigate.value = navigate
    }
}