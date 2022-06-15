package com.example.restaurants.features.reviews.domain.interactor

import com.example.restaurants.features.reviews.domain.entity.Review

interface ReviewsInteractor {

    suspend fun getReviews(): List<Review>
}