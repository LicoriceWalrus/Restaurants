package com.example.restaurants.features.restaurants.domain.interactor

import com.example.restaurants.features.restaurants.domain.entity.Restaurant

interface RestaurantsInteractor {

    suspend fun getRestaurants(): List<Restaurant>
}