package com.example.akakcecasestudykotlin.domain.model

// Data class for the product, which contains the following fields:
// We will convert ProductDto to Product in the data layer
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Double
)
