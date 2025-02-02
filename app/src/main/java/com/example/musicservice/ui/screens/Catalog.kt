package com.example.musicservice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musicservice.ui.components.ImageFromUrl
import com.example.musicservice.ui.components.MusicItem
import com.example.musicservice.ui.theme.DeepGray

@Composable
fun CatalogScreen(navController: NavController) {
    val musicItems = listOf(
        MusicItem("1", "1", Modifier, { navController.navigate("item") }),
        MusicItem("2", "2", Modifier, { navController.navigate("item") }),
        MusicItem("3", "3", Modifier, { navController.navigate("item") }),
        MusicItem("4", "4", Modifier, { navController.navigate("item") }),
        MusicItem("5", "5", Modifier, { navController.navigate("item") }),
        MusicItem("6", "6", Modifier, { navController.navigate("item") })
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepGray)
            .padding(10.dp)
    ) {
        Text(
            "Song catalog",
            Modifier.align(Alignment.Start)
                .padding(15.dp),
            fontSize = 30.sp,
            color = Color.White
            )
        LazyHorizontalGrid(
            rows = GridCells.Adaptive(400.dp),
            modifier = Modifier.fillMaxSize()
        ) {
//            items(musicItems) { musicItem ->
//                MusicItem(
//                    artistName = musicItem.artist,
//                    songName = musicItem.title,
//                    action = { /* Обработчик нажатия */ }
//                )
//            }
        }
        //MusicItem("Some name", "Some name", Modifier) { navController.navigate("item") }
    }
}