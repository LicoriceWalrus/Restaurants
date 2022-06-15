package com.example.restaurants.features.hits.presentation

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
import com.example.restaurants.features.hits.domain.entity.Hit
import com.example.restaurants.features.hits.presentation.model.HitItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.getViewModel

@Composable
fun ScreenHits(
    vm: HitsViewModel = getViewModel()
) {
    Hits(
        state = vm.screenState().collectAsState(),
        refresh = vm::refresh
    )
}

@Composable
private fun Hits(
    state: State<HitsScreenState>,
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
                val hits = state.value.hits
                items(
                    count = hits.size,
                    key = null,
                ) { position ->
                    HitItem(
                        hit = hits[position]
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun HitsPreview() {
    Hits(
        state = flowOf<HitsScreenState>().collectAsState(
            initial = HitsScreenState(
                loading = false,
                hits = listOf(
                    Hit(
                        productName = "Сяки-Мяки",
                        productImage = "",
                        productPrice = "100",
                        productDescription = " Оч вкусные",
                        restaurantId = "111",
                        restaurantName = "Суши-Мухи",
                        restaurantLogo = ""
                    )
                )
            )
        ),
        refresh = {}
    )
}