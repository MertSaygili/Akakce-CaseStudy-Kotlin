package com.example.akakcecasestudykotlin.presentation.product_detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.akakcecasestudykotlin.databinding.ActivityProductDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Show back button
        supportActionBar?.setDisplayShowTitleEnabled(false) // Hide default title

        // Handle back button click
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Get the product ID from the intent
        val productId = intent.getIntExtra("product_id", -1)
        if (productId == -1) {
            Snackbar.make(binding.root, "Invalid product ID", Snackbar.LENGTH_SHORT).show()
            finish()
            return
        }

        // Fetch and observe product details
        getProductDetail(productId)
        observeProductDetail()
    }

    private fun getProductDetail(productId: Int) {
        viewModel.fetchProductDetail(productId)
    }

    @SuppressLint("SetTextI18n")
    private fun observeProductDetail() {
        viewModel.productDetail.observe(this) { product ->
            if (product != null) {
                // Update the UI with the product details
                binding.toolbar.title = "Product Detail"
                binding.tvProductTitle.text = product.title
                binding.tvProductPrice.text = "$${product.price}"
                binding.tvProductDescription.text = product.description
                binding.ratingBar.rating = product.rating.toFloat()
                binding.tvRatingValue.text = product.rating.toString()

                // Load the product image using Glide
                Glide.with(this)
                    .load(product.image)
                    .into(binding.ivProductImage)
            }
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            if (errorMessage != null) {
                Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}