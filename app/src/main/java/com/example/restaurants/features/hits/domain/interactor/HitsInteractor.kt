package com.example.restaurants.features.hits.domain.interactor

import com.example.restaurants.features.hits.domain.entity.Hit

interface HitsInteractor {

    suspend fun getHits(): List<Hit>
}