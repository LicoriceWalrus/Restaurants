package com.example.restaurants.features.reviews.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurants.components.LoadingBox
import com.example.restaurants.features.reviews.domain.entity.Review
import com.example.restaurants.features.reviews.presentation.model.ReviewsItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.getViewModel

@Composable
fun ScreenReviews(
    vm: ReviewsViewModel = getViewModel()
) {

    Reviews(
        state = vm.screenState().collectAsState(),
        refresh = vm::refresh
    )
}

@Composable
private fun Reviews(
    state: State<ReviewsScreenState>,
    refresh: () -> Unit
) {

    LoadingBox(
        loading = state.value.loading,
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(state.value.isRefreshing),
            onRefresh = { refresh() }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(top = 8.dp)
            ) {
                val reviews = state.value.reviews
                items(
                    count = reviews.size,
                    key = null,
                ) { position ->
                    ReviewsItem(
                        review = reviews[position]
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun ReviewsPreview() {
    Reviews(
        state = flowOf<ReviewsScreenState>().collectAsState(
            initial = ReviewsScreenState(
                loading = false,
                reviews = listOf(
                    Review(
                        isPositive = false,
                        message = "Было не вкусно",
                        dateAdded = "2020-02-21T14:32:56.977",
                        userFIO = "Алла",
                        restaurantName = "Крок"
                    ),
                    Review(
                        isPositive = true,
                        message = "Было вкусно",
                        dateAdded = "2021-01-21T14:32:56.977",
                        userFIO = "ЛёХа",
                        restaurantName = "Суши-Пес"
                    )
                ),
                errorMessage = "",
                isRefreshing = false
            )
        ),
        refresh = {}
    )
}