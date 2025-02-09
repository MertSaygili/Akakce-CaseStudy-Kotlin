package com.example.akakcecasestudykotlin.presentation.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.akakcecasestudykotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fetchProducts()
    }

    private fun fetchProducts() {
        viewModel.fetchHorizontalProducts()
        viewModel.fetchVerticalProducts()
    }

    private fun observeProducts() {
        // Observe products
        viewModel.verticalProducts.observe(this, Observer { products ->
            // Log or display the products
            products.forEach { product ->
                println("Product: ${product.title}, Price: ${product.price}")
            }
        })

        viewModel.horizontalProducts.observe(this, Observer { products ->
            // Log or display the products
            products.forEach { product ->
                println("Product: ${product.title}, Price: ${product.price}")
            }
        })

        // Observe error messages
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            println(errorMessage)
        })

    }
}