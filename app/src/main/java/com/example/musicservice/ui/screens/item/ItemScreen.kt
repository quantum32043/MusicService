package com.example.musicservice.ui.screens.item

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
    val song = viewModel.song.collectAsState(initial = null)

    LaunchedEffect(songId) {
        Log.d("ItemScreen", "Loading song info for id: $songId")
        viewModel.loadSongInfo(songId)
    }

    Log.d("ItemScreen", "Outer $song")

    if (song.value == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", color = Color.White)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepGray)
        ) {
            ImageSlider(images = song.value!!.images)

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                Modifier.padding(horizontal = 10.dp)
            )
            {
                Text(text = song.value!!.title, fontSize = 50.sp, color = Color.White)
                Text(text = song.value!!.artist, fontSize = 25.sp, color = Color.White)
                Spacer(Modifier.height(40.dp))
                Text(text = song.value!!.about, fontSize = 22.sp, color = Color.White)
            }
        }

    }
}








