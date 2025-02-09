package com.example.akakcecasestudykotlin.domain.repository

import com.example.akakcecasestudykotlin.domain.model.Product

// Repository interface for the products
interface ProductRepository {
    // Function to get the products with a limit
    suspend fun getProducts(limit: Int): List<Product>

    // Function to get the product by id, returns null if the product is not found
    suspend fun getProductById(id: Int): Product?
}