package com.example.restaurants.features.reviews.domain.interactor

import com.example.restaurants.features.reviews.domain.entity.Review
import com.example.restaurants.features.reviews.domain.repo.ReviewsRepo

class ReviewsInteractorImpl(
    private val repo: ReviewsRepo
): ReviewsInteractor {

    override suspend fun getReviews(): List<Review> = repo.getReviews()
}