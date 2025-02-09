package com.example.akakcecasestudykotlin.presentation.product_detail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akakcecasestudykotlin.domain.model.Product
import com.example.akakcecasestudykotlin.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productDetail = MutableLiveData<Product?>()
    val productDetail: LiveData<Product?> get() = _productDetail

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchProductDetail(productId: Int) {
        viewModelScope.launch {
            try {
                val product = productRepository.getProductById(productId)
                if(product == null){
                    _errorMessage.value = "Product not found"
                    return@launch
                }else {
                    _productDetail.value = product
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching products: ${e.message}"
            }
        }
    }
}
