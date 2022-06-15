package com.example.restaurants.features.reviews.data.dto

import com.google.gson.annotations.SerializedName

data class ReviewsDto(
    @SerializedName("IsPositive")
    val isPositive: String? = null,
    @SerializedName("Message")
    val message: String? = null,
    @SerializedName("DateAdded")
    val dateAdded: String? = null,
    @SerializedName("UserFIO")
    val userFIO: String? = null,
    @SerializedName("RestaurantName")
    val restaurantName: String? = null
)