package com.example.restaurants.features.reviews.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.features.reviews.domain.interactor.ReviewsInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReviewsViewModel(
    private val interactor: ReviewsInteractor
) : ViewModel() {

    private var state: ReviewsScreenState =
        ReviewsScreenState()
    private val screenState: MutableStateFlow<ReviewsScreenState> = MutableStateFlow(state)

    init {
        getReviews()
    }

    fun screenState(): StateFlow<ReviewsScreenState> = screenState

    fun refresh() {
        getReviews(isRefreshing = true)
    }

    private fun getReviews(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            state = state.copy(loading = !isRefreshing, isRefreshing = isRefreshing)
            updateUi()
            runCatching {
                interactor.getReviews()
            }.onSuccess {
                state = state.copy(loading = false, isRefreshing = false, reviews = it)
                updateUi()
            }.onFailure {
                state = state.copy(
                    reviews = emptyList(),
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