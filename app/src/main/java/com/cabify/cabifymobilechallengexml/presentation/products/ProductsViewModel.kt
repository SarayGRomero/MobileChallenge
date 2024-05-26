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

    private val _totalCount = MutableStateFlow<Int>(0)
    val totalCount: StateFlow<Int> = _totalCount

    fun getProducts() = viewModelScope.launch(ioDispatcher) {
        getProductsUseCase.invoke().collect { products ->
            _products.value = products.map { it.toVo() }
        }
    }

    fun updateTotalCount(products: List<CabifyProductVo>) {
        _products.value = products
        _totalCount.value = products.sumOf { it.count }
    }

    fun goToShoppingCart(products: List<CabifyProductVo>) {
        val selectedProducts = products.filter { it.count > 0 }
        val totalProducts = ProductsVo(
            products = selectedProducts,
            totalPrice = selectedProducts.getPrice()
        )
        Log.d("ProductsViewModel", totalProducts.products.toString() + " - " + totalProducts.totalPrice.toString())
        //TODO Navigate to shopping cart
    }
}