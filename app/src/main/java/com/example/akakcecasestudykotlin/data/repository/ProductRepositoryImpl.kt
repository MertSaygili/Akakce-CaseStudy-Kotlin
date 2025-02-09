package com.example.akakcecasestudykotlin.data.repository

import com.example.akakcecasestudykotlin.data.api.ProductService
import com.example.akakcecasestudykotlin.data.model.ProductDto
import com.example.akakcecasestudykotlin.domain.model.Product
import com.example.akakcecasestudykotlin.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {

    // Function to get the products with a limit, returns a list of Product
    override suspend fun getProducts(limit: Int): List<Product> {
        return try {
            val response = productService.getProducts(limit)
            if (response.isSuccessful) {
                response.body()?.map { it.toProduct() } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Function to get the product by id, returns null if the product is not found
    override suspend fun getProductById(id: Int): Product? {
        val response = productService.getProductById(id)
        return if (response.isSuccessful) {
            response.body()?.toProduct()
        } else {
            null
        }
    }

    // Extension function to convert ProductDto to Product
    // This function is used to convert the ProductDto to Product
    private fun ProductDto.toProduct(): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            description = description,
            category = category,
            image = image,
            rating = rating.rate
        )
    }
}