package com.example.restaurants.features.hits.presentation.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.restaurants.features.hits.domain.entity.Hit

@Composable
fun HitItem(
    hit: Hit
) {

    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    )
    {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(CenterVertically)
                        .size(75.dp),
                    painter = rememberAsyncImagePainter(hit.productImage),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Column(
                    modifier = Modifier
                        .align(CenterVertically)
                ) {
                    Text(
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 4.dp),
                        fontSize = 20.sp,
                        text = hit.productName
                    )
                    Row {
                        Icon(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(20.dp)
                                .align(CenterVertically),
                            painter = rememberAsyncImagePainter(hit.restaurantLogo),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                        Text(
                            text = hit.restaurantName,
                            maxLines = 1,
                            fontSize = 20.sp,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    Text(
                        fontSize = 18.sp,
                        text = hit.productPrice + " руб."
                    )
                }
            }
            Text(
                fontSize = 16.sp,
                text = hit.productDescription,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
@Preview
private fun HitItemPreview() {
    HitItem(
        hit = Hit(
            productName = "Сяки-Мяки",
            productImage = "",
            productPrice = "100",
            productDescription = " Оч вкусные",
            restaurantId = "111",
            restaurantName = "Суши-Мухи",
            restaurantLogo = ""
        )
    )
}