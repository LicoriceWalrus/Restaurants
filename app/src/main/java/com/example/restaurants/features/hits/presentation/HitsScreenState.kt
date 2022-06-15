package com.example.restaurants.features.hits.presentation

import com.example.restaurants.features.hits.domain.entity.Hit

data class HitsScreenState(
    val loading: Boolean = false,
    val hits: List<Hit> = emptyList(),
    val isRefreshing: Boolean = false
)