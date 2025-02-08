package com.example.musicservice.ui.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.musicservice.ui.theme.DeepGray
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(images: List<Any>) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val cardHeight = screenHeight / 2 // половина экрана по высоте
    val pagerState = rememberPagerState() // состояние для отслеживания текущей страницы

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
    ) {
        HorizontalPager(
            state = pagerState,
            count = images.size,
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
        ) { pageIndex ->
            val image = images[pageIndex]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .clickable { /* Можно добавить обработку кликов, если нужно */ }
                    .padding(0.dp), // Убираем отступы у Card
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 12.dp,
                    hoveredElevation = 6.dp
                ),
                shape = RectangleShape // Убираем скругления, задавая прямоугольную форму
            ) {
                AsyncImage(
                    model = image as String,
                    contentDescription = "Slider item",
                    contentScale = ContentScale.Crop, // обязательно для правильной обрезки
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardHeight)
                        .padding(0.dp)
                        .background(DeepGray)
                )
            }
        }
    }
}

