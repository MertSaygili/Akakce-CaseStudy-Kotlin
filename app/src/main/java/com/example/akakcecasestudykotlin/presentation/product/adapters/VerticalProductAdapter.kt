package com.example.akakcecasestudykotlin.presentation.product.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.akakcecasestudykotlin.R
import com.example.akakcecasestudykotlin.domain.model.Product

class VerticalProductAdapter(
    var products: List<Product>
) : RecyclerView.Adapter<VerticalProductAdapter.VerticalProductViewHolder>() {

    private var onClickListener: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vertical_product, parent, false)
        return VerticalProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

        holder.itemView.setOnClickListener() {
            onClickListener?.invoke(product)
        }
    }

    override fun getItemCount(): Int = products.size

    fun setOnClickListener(onClick: (Product) -> Unit) {
        onClickListener = onClick
    }
    class VerticalProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProductImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        private val tvProductTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        private val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)

        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            tvProductTitle.text = product.title
            tvProductPrice.text = "$${product.price}"
            Glide.with(itemView.context)
                .load(product.image)
                .into(ivProductImage)
        }
    }
}