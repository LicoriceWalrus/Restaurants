package com.example.restaurants.features.restaurants.data.api

import com.example.restaurants.features.restaurants.data.dto.RestaurantsDto
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantsApi {

    @GET("restaurants")
    suspend fun getRestaurants(): Response<List<RestaurantsDto>>
}