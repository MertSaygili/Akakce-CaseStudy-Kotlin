package com.example.akakcecasestudykotlin.presentation.product


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
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _horizontalProducts = MutableLiveData<List<Product>>()
    val horizontalProducts: LiveData<List<Product>> get() = _horizontalProducts

    private val _verticalProducts = MutableLiveData<List<Product>>()
    val verticalProducts: LiveData<List<Product>> get() = _verticalProducts

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchHorizontalProducts() {
        viewModelScope.launch {
            try {
                val productList = productRepository.getProducts(5)
                _horizontalProducts.value = productList
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching products: ${e.message}"
            }
        }
    }

    fun fetchVerticalProducts() {
        viewModelScope.launch {
            try {
                val productList = productRepository.getProducts(20)
                _verticalProducts.value = productList
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching products: ${e.message}"
            }
        }
    }
}
