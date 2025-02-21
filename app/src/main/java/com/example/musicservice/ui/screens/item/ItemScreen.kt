package com.example.musicservice.ui.screens.item

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicservice.ui.components.ImageSlider
import kotlinx.coroutines.delay
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.musicservice.ui.theme.DeepGray

@Composable
fun ItemScreen(viewModel: ItemViewModel, songId: String) {
    val song by viewModel.song.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()

    LaunchedEffect(songId) {
        viewModel.loadSongInfo(songId)
        viewModel.checkIfFavorite(songId)
    }

    if (song == null) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
            Text(text = "Loading...", color = Color.White)
        }
    } else {
        Column(modifier = Modifier.fillMaxSize().background(DeepGray).padding(10.dp)) {
            ImageSlider(images = song!!.images)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(text = song!!.title, fontSize = 50.sp, color = Color.White)
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(45.dp).clickable { viewModel.toggleFavorite(song!!) },
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
            Text(text = song!!.artist, fontSize = 25.sp, color = Color.White)
            Spacer(Modifier.height(40.dp))
            Text(text = song!!.about, fontSize = 22.sp, color = Color.White)
        }
    }
}








