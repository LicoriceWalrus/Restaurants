package com.example.restaurants.features.restaurants.data.dto

import com.google.gson.annotations.SerializedName

data class RestaurantsDto(
    @SerializedName("Name")
    val name: String? = null,
    @SerializedName("Logo")
    val logo: String? = null,
    @SerializedName("MinCost")
    val minCost: String? = null,
    @SerializedName("DeliveryCost")
    val deliveryCost: String? = null,
    @SerializedName("DeliveryTime")
    val deliveryTime: String? = null,
    @SerializedName("PositiveReviews")
    val positiveReviews: String? = null,
    @SerializedName("ReviewsCount")
    val reviewsCount: String? = null,
    @SerializedName("Specializations")
    val specializations: List<SpecializationResp>? = null
)