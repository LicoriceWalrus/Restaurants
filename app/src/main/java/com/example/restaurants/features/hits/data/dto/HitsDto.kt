package com.example.restaurants.features.hits.data.dto

import com.google.gson.annotations.SerializedName

data class HitsDto(
    @SerializedName("ProductName")
    val productName: String? = null,
    @SerializedName("ProductImage")
    val productImage: String? = null,
    @SerializedName("ProductPrice")
    val productPrice: String? = null,
    @SerializedName("ProductDescription")
    val productDescription: String? = null,
    @SerializedName("RestaurantId")
    val restaurantId: String? = null,
    @SerializedName("RestaurantName")
    val restaurantName: String? = null,
    @SerializedName("RestaurantLogo")
    val restaurantLogo: String? = null
)