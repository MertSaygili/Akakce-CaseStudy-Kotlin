package com.example.akakcecasestudykotlin.data.repository

import com.example.akakcecasestudykotlin.data.api.ProductService
import com.example.akakcecasestudykotlin.data.model.ProductDto
import com.example.akakcecasestudykotlin.domain.model.Product
import com.example.akakcecasestudykotlin.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {

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

    override suspend fun getProductById(id: Int): Product? {
        val response = productService.getProductById(id)
        return if (response.isSuccessful) {
            response.body()?.toProduct()
        } else {
            null
        }
    }

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