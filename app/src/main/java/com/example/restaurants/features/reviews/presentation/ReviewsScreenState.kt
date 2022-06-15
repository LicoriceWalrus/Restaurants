package com.example.restaurants.features.reviews.presentation

import com.example.restaurants.features.reviews.domain.entity.Review

data class ReviewsScreenState(
    val loading: Boolean = false,
    val reviews: List<Review> = emptyList(),
    val errorMessage: String = "",
    val isRefreshing: Boolean = false
)