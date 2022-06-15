package com.example.restaurants.features.reviews.data.repo

import com.example.restaurants.data.getOrThrow
import com.example.restaurants.features.reviews.data.api.ReviewsApi
import com.example.restaurants.features.reviews.data.dto.ReviewsDto
import com.example.restaurants.features.reviews.domain.entity.Review
import com.example.restaurants.features.reviews.domain.repo.ReviewsRepo

class ReviewsRepoImpl(
    private val api: ReviewsApi
) : ReviewsRepo {

    override suspend fun getReviews(): List<Review> {

        val resp = api.getReviews().getOrThrow()

        return resp.map {
            it.mapToEntity()
        }
    }

    private fun ReviewsDto.mapToEntity() = Review(
        isPositive = isPositive == "true",
        message = message.orEmpty(),
        dateAdded = dateAdded.orEmpty(),
        userFIO = userFIO.orEmpty(),
        restaurantName = restaurantName.orEmpty()
    )
}