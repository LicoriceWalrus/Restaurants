package com.example.restaurants.features.reviews.data.api

import com.example.restaurants.features.reviews.data.dto.ReviewsDto
import retrofit2.Response
import retrofit2.http.GET

interface ReviewsApi {

    @GET("reviews")
    suspend fun getReviews(): Response<List<ReviewsDto>>
}