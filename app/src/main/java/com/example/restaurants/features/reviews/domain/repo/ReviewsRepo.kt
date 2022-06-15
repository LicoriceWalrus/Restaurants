package com.example.restaurants.features.reviews.domain.repo

import com.example.restaurants.features.reviews.domain.entity.Review

interface ReviewsRepo {

    suspend fun getReviews(): List<Review>
}