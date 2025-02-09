package com.example.akakcecasestudykotlin.domain.repository

import com.example.akakcecasestudykotlin.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(limit: Int): List<Product>
    suspend fun getProductById(id: Int): Product?
}