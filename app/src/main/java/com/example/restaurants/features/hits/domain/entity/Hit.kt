package com.example.restaurants.features.hits.domain.entity

data class Hit(
    val productName: String,
    val productImage: String,
    val productPrice: String,
    val productDescription: String,
    val restaurantId: String,
    val restaurantName: String,
    val restaurantLogo: String
)