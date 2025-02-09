package com.example.akakcecasestudykotlin.presentation.product

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akakcecasestudykotlin.databinding.ActivityProductsBinding
import com.example.akakcecasestudykotlin.presentation.product.adapters.HorizontalProductAdapter
import com.example.akakcecasestudykotlin.presentation.product.adapters.VerticalProductAdapter
import com.example.akakcecasestudykotlin.presentation.product_detail.ProductDetailActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private val viewModel: ProductsViewModel by viewModels()

    private lateinit var horizontalAdapter: HorizontalProductAdapter
    private lateinit var verticalAdapter: VerticalProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup the recycler views
        setupRecyclerViews()
        // Fetch the products and observe the changes
        fetchProducts()
        // Observe the products
        observeProducts()
    }

    private fun setupRecyclerViews() {
        // Horizontal RecyclerView
        horizontalAdapter = HorizontalProductAdapter(emptyList())
        binding.rvHorizontalProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvHorizontalProducts.adapter = horizontalAdapter

        // Vertical RecyclerView with GridLayoutManager (2 columns)
        verticalAdapter = VerticalProductAdapter(emptyList())
        binding.rvVerticalProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvVerticalProducts.adapter = verticalAdapter

        // set the scroll of the vertical recycler view to false
        binding.rvVerticalProducts.isNestedScrollingEnabled = false

        // Set the click listeners for the products
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        // Set the click listeners for the products
        horizontalAdapter.setOnClickListener { product ->
            navigateToProductDetail(product.id)
        }

        verticalAdapter.setOnClickListener { product ->
            navigateToProductDetail(product.id)
        }
    }

    private fun fetchProducts() {
        // Fetch the horizontal products
        viewModel.fetchHorizontalProducts()
        // Fetch the vertical products
        viewModel.fetchVerticalProducts()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeProducts() {
        // Observe the horizontal and vertical products, and update the adapters
        viewModel.horizontalProducts.observe(this, Observer { products ->
            horizontalAdapter.products = products
            horizontalAdapter.notifyDataSetChanged()
        })

        viewModel.verticalProducts.observe(this, Observer { products ->
            verticalAdapter.products = products
            verticalAdapter.notifyDataSetChanged()
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun navigateToProductDetail(productId: Int?) {
        if(productId == null) return
        // Navigate to the product detail screen
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("product_id", productId)
        startActivity(intent)
    }
}