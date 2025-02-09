package com.example.akakcecasestudykotlin.presentation.product.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.akakcecasestudykotlin.R
import com.example.akakcecasestudykotlin.domain.model.Product
import com.example.akakcecasestudykotlin.utils.extensions.SizeConverterExtension.Companion.dpToPx

class HorizontalProductAdapter(
    var products: List<Product>
) : RecyclerView.Adapter<HorizontalProductAdapter.HorizontalProductViewHolder>() {

    private var onClickListener: ((Product) -> Unit)? = null

    // Create the view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horizontal_product, parent, false)
        return HorizontalProductViewHolder(view)
    }

    // Bind the data to the view holder
    override fun onBindViewHolder(holder: HorizontalProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

        holder.itemView.setOnClickListener() {
            onClickListener?.invoke(product)
        }
    }

    // Get the item count
    override fun getItemCount(): Int = products.size

    // Set the click listener for the products
    fun setOnClickListener(onClick: (Product) -> Unit) {
        onClickListener = onClick
    }

    class HorizontalProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Get the views from the layout
        private val ivProductImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        private val tvProductTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        private val tvProductDescription: TextView = itemView.findViewById(R.id.tvProductDescriptions)
        private val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        private val llStarContainer: LinearLayout = itemView.findViewById(R.id.llStars)

        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            // Set the values to the views
            tvProductTitle.text = product.title
            tvProductDescription.text = product.description
            tvProductPrice.text = "$${product.price}"
            setStarRating(llStarContainer, product.rating.toInt())

            Glide.with(itemView.context)
                .load(product.image)
                .into(ivProductImage)
        }

        // Function to dynamically add star icons
        private fun setStarRating(llStarContainer: LinearLayout, rating: Int) {
            llStarContainer.removeAllViews() // Clear previous views

            for (i in 1..5) {
                val star = ImageView(llStarContainer.context)
                star.layoutParams = LinearLayout.LayoutParams(18.dpToPx(), 18.dpToPx()).apply {
                    marginEnd = 2.dpToPx() // Add some spacing
                }
                star.setImageResource(if (i <= rating) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
                llStarContainer.addView(star)
            }
        }
    }
}