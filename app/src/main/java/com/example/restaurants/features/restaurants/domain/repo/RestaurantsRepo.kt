package com.example.restaurants.features.restaurants.domain.repo

import com.example.restaurants.features.restaurants.domain.entity.Restaurant

interface RestaurantsRepo {

    suspend fun getRestaurants(): List<Restaurant>
}