package com.example.restaurants.features.reviews.domain.entity

data class Review(
    val isPositive: Boolean,
    val message: String,
    val dateAdded: String,
    val userFIO: String,
    val restaurantName: String
)