package com.example.musicservice.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.musicservice.ui.theme.Violet
import com.example.musicservice.ui.theme.White
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.musicservice.R
import com.example.musicservice.ui.theme.DeepGray
import com.example.musicservice.ui.theme.LightBlueGray
import com.example.musicservice.ui.theme.LightGray
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import com.example.musicservice.data.catalog.Song

@Composable
fun MusicItem(song: Song, action: () -> Unit) {
    Column(
        modifier = Modifier
            .height(240.dp)
            .width(160.dp)
            .border(1.dp, Color.Transparent, RoundedCornerShape(10.dp))
            .fillMaxSize()
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .clickable { action() }
    ) {
        ImageFromUrl(song.imageUrl)

        Spacer(Modifier.height(15.dp))
        Row {
            Column {
                Text(
                    song.artist,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(5.dp))

                Text(
                    song.title,
                    fontSize = 13.sp,
                    color = LightGray
                )
            }
        }
//        Icon(
//            imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
//            contentDescription = "Favorite",
//            modifier = Modifier
//                .size(45.dp)
//                .clickable { viewModel.toggleFavorite(songId) },
//            tint = if (favorite) Color.Red else Color.Gray
//        )
    }
}

@Composable
fun ImageFromUrl(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "The delasign logo",
        contentScale = ContentScale.Crop, // Обрезаем изображение, чтобы оно заполнило пространство
        modifier = Modifier
            .fillMaxWidth() // Ширина картинки будет равна ширине контейнера
            .height(160.dp) // Задаем фиксированную высоту для картинки
            .clip(RoundedCornerShape(10.dp)) // Скругление углов картинки
    )
}
