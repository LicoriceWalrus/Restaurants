package com.example.restaurants.features.hits.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.features.hits.domain.interactor.HitsInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HitsViewModel(
    private val interactor: HitsInteractor
) : ViewModel() {

    private var state: HitsScreenState =
        HitsScreenState()
    private val screenState: MutableStateFlow<HitsScreenState> = MutableStateFlow(state)

    init {
        getHits()
    }

    fun screenState(): StateFlow<HitsScreenState> = screenState

    fun refresh() {
        getHits(isRefreshing = true)
    }

    private fun getHits(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            state = state.copy(loading = !isRefreshing, isRefreshing = isRefreshing)
            updateUi()
            runCatching {
                interactor.getHits()
            }.onSuccess {
                state = state.copy(loading = false, isRefreshing = false, hits = it)
                updateUi()
            }.onFailure {
                state = state.copy(
                    hits = emptyList(),
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