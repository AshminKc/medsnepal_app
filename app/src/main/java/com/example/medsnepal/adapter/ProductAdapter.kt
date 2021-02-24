package com.example.medsnepal.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medsnepal.R
import com.example.medsnepal.entity.Product
import com.example.medsnepal.entity.ProductList
import com.squareup.picasso.Picasso
import okhttp3.internal.Util.concat

class ProductAdapter (private val data:List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val TAG = "ProductAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_single_item, parent, false)
        return ProductViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        return holder.bind(data[position])
    }

    class ProductViewHolder(val view: View): RecyclerView.ViewHolder(view){

        val name = view.findViewById<TextView>(R.id.productName)
        val price = view.findViewById<TextView>(R.id.productPrice)
        val stock = view.findViewById<TextView>(R.id.productStock)
        val image = view.findViewById<ImageView>(R.id.productImage)

        fun bind(product: Product){
            name.text = product.name
            price.text = product.price
            stock.text = product.countInStock
            Picasso.get()
                    .load(R.drawable.medsnepal)
                    .into(image)
        }
    }
}