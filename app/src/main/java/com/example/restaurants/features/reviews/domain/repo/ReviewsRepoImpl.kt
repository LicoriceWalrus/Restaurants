package com.example.restaurants.features.reviews.domain.repo

import com.example.restaurants.data.getOrThrow
import com.example.restaurants.features.reviews.data.api.ReviewsApi
import com.example.restaurants.features.reviews.data.dto.ReviewsDto
import com.example.restaurants.features.reviews.domain.entity.Review

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
        isPositive = this.isPositive == "true",
        message = this.message.orEmpty(),
        dateAdded = this.dateAdded.orEmpty(),
        userFIO = this.userFIO.orEmpty(),
        restaurantName = this.restaurantName.orEmpty()
    )
}