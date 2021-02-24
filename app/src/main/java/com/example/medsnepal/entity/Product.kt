package com.example.medsnepal.entity

data class AllProduct(
        val data: List<Product>
)

data class Product(
        val name: String,
        val price: String,
        val image: String,
        val countInStock: String,
        val description: String
)
