package com.example.restaurants.features.restaurants.presentation

import com.example.restaurants.features.restaurants.domain.entity.Restaurant

data class RestaurantsScreenState(
    val loading: Boolean = false,
    val restaurants: List<Restaurant> = emptyList(),
    val isRefreshing: Boolean = false,
    val query: String? = null
)