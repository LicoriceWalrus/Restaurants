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

    fun searchByName(query: String) {
        getRestaurants(querySearch = query)
    }

    fun onQueryChanged(newQuery: String) {
        viewModelScope.launch {
            state = state.copy(query = newQuery)
            updateUi()
        }
    }

    fun screenState(): StateFlow<RestaurantsScreenState> = screenState

    fun refresh() {
        getRestaurants(isRefreshing = true, querySearch = state.query ?: "")
    }

    private fun getRestaurants(isRefreshing: Boolean = false, querySearch: String = "") {
        viewModelScope.launch {
            state = state.copy(loading = !isRefreshing, isRefreshing = isRefreshing)
            updateUi()
            runCatching {
                interactor.getRestaurants()
            }.onSuccess {
                state = state.copy(
                    loading = false,
                    isRefreshing = false,
                    restaurants = if (querySearch.isNotEmpty()) it.filter { restaurant ->
                        restaurant.name.lowercase().contains(querySearch.lowercase())
                    } else it
                )
                updateUi()
            }.onFailure {
                state = state.copy(
                    restaurants = emptyList(),
                    loading = false,
                    isRefreshing = false
                )
                updateUi()
            }
        }
    }

    private suspend fun updateUi() {
        screenState.emit(state)
    }
}