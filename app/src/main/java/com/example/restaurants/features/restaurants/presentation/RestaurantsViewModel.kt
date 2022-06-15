package com.example.restaurants.features.restaurants.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.features.restaurants.domain.interactor.RestaurantsInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RestaurantsViewModel(
    private val interactor: RestaurantsInteractor
) : ViewModel() {

    private var state: RestaurantsScreenState =
        RestaurantsScreenState()
    private val screenState: MutableStateFlow<RestaurantsScreenState> = MutableStateFlow(state)

    init {
        getRestaurants()
    }

    fun screenState(): StateFlow<RestaurantsScreenState> = screenState

    fun refresh() {
        getRestaurants(isRefreshing = true)
    }

    private fun getRestaurants(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            state = state.copy(loading = !isRefreshing, isRefreshing = isRefreshing)
            updateUi()
            runCatching {
                interactor.getRestaurants()
            }.onSuccess {
                state = state.copy(loading = false, isRefreshing = false, restaurants = it)
                updateUi()
            }.onFailure {
                state = state.copy(
                    loading = false,
                    isRefreshing = false,
                    errorMessage = it.message.orEmpty()
                )
                updateUi()
            }
        }
    }

    private suspend fun updateUi() {
        screenState.emit(state)
    }
}