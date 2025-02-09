package com.example.akakcecasestudykotlin.data.api

import com.example.akakcecasestudykotlin.data.model.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    // Get the products with a limit, returns a list of ProductDto
    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int): Response<List<ProductDto>>

    // Get the product by id, returns a ProductDto
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ProductDto>
}