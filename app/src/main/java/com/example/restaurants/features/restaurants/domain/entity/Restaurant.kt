package com.example.restaurants.features.restaurants.domain.entity

data class Restaurant(
    val name: String,
    val logo: String,
    val minCost: String,
    val deliveryCost: String,
    val deliveryTime: String,
    val positiveReviews: String,
    val reviewsCount: String,
    val specializations: List<Specialization>
)