package com.example.restaurants.features.reviews.presentation.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurants.R
import com.example.restaurants.features.reviews.domain.entity.Review

@Composable
fun ReviewsItem(
    review: Review
) {

    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row {
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(18.dp)
                        .align(CenterVertically),
                    painter = painterResource(
                        id = if (review.isPositive) R.drawable.ic_thumb_up
                        else R.drawable.ic_thumb_down
                    ),
                    contentDescription = "",
                    tint = colorResource(id = R.color.bottom_bar_color)
                )
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontSize = 18.sp,
                    text = review.userFIO + " о ресторане " + review.restaurantName
                )
            }
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                fontSize = 14.sp,
                text = review.message
            )
            Text(
                text = review.dateAdded,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )
        }
    }
}

@Composable
@Preview
private fun ReviewItemPreview() {
    ReviewsItem(
        review = Review(
            isPositive = true,
            message = "Было вкусно",
            dateAdded = "2021-01-21 14:32:56",
            userFIO = "ЛёХа",
            restaurantName = "Суши-Пес"
        )
    )
}