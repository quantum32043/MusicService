package com.example.musicservice.ui.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musicservice.ui.components.MusicItem
import com.example.musicservice.ui.screens.item.ItemViewModel
import com.example.musicservice.ui.theme.DeepGray

@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel) {
    val favoriteSongs by viewModel.favoriteSongs.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(DeepGray)
            .padding(vertical = 10.dp, horizontal = 5.dp)
    ) {
        Text("Favorite",
            Modifier.align(Alignment.Start)
                .padding(10.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
            items(favoriteSongs) { song ->
                MusicItem(song) { navController.navigate("itemScreen/${song.id}") }
            }
        }
    }
}

