package com.example.restaurants.features.hits.domain.interactor

import com.example.restaurants.features.hits.domain.entity.Hit
import com.example.restaurants.features.hits.domain.repo.HitsRepo

class HitsInteractorImpl(
    private val repo: HitsRepo
) : HitsInteractor {

    override suspend fun getHits(): List<Hit> = repo.getHits()
}