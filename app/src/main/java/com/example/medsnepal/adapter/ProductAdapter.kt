package com.example.medsnepal.adapter

import android.content.Context
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medsnepal.R
import com.example.medsnepal.entity.Product

class ProductAdapter (private val data:List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(product: Product){
            val name = view.findViewById<TextView>(R.id.productName)
            val price = view.findViewById<TextView>(R.id.productPrice)
            val stock = view.findViewById<TextView>(R.id.productStock)
            val image = view.findViewById<ImageView>(R.id.productImage)

            name.text = product.name
            price.text = product.price
            stock.text = product.stock

            Glide.with(view.context).load(product.image).centerCrop().into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_single_item, parent, false)
        return ProductViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }


}