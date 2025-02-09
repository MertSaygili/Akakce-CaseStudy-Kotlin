package com.example.akakcecasestudykotlin.data.api

import com.example.akakcecasestudykotlin.data.repository.ProductRepositoryImpl
import com.example.akakcecasestudykotlin.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// Module for providing the Retrofit instance
@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {
    // should be an read from the environment variable, but for simplicity, we will hardcode it
    private const val BASE_URL = "https://fakestoreapi.com/"

    @Provides
    @Singleton
    fun provideProductService(): ProductService {
        // Create OkHttpClient instance, set the timeout values, and build the client
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        // Create Retrofit instance
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)
    }

    // Provide the ProductRepository, which requires the ProductService
    @Provides
    @Singleton
    fun provideProductRepository(productService: ProductService): ProductRepository {
        return ProductRepositoryImpl(productService)
    }
}