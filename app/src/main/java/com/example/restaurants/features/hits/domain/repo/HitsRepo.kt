package com.example.restaurants.features.hits.domain.repo

import com.example.restaurants.features.hits.domain.entity.Hit

interface HitsRepo {

    suspend fun getHits(): List<Hit>
}