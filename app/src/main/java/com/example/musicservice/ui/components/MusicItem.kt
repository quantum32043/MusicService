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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import com.example.musicservice.data.catalog.Song

@Composable
fun MusicItem(song:Song, action: () -> Unit) {
    Column(
        modifier = Modifier.height(240.dp)
            .width(160.dp)
            .border(1.dp, Color.Transparent, RoundedCornerShape(10.dp))
            .fillMaxSize()
            .padding(5.dp)
            .clickable { action() }
    ) {
        ImageFromUrl(song.imageUrl)
        Spacer(Modifier.height(15.dp))
        Text(song.artist, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(5.dp))
        Text(song.title, fontSize = 13.sp, color = LightGray)
//        Spacer(Modifier.height(20.dp))
//        Button(onClick = { action() },
//            elevation = ButtonDefaults.buttonElevation(0.dp),
//            shape = RoundedCornerShape(5.dp),
//            modifier = Modifier.fillMaxWidth()
//                .padding(0.dp)
//                .height(35.dp)
//                .border(2.dp, Violet, RoundedCornerShape(10.dp))
//                .background(Color.Transparent),
//            colors = ButtonDefaults.buttonColors(Color.Transparent)
//                ) {
//            Text("View details", color = Violet, fontSize = 15.sp)
//        }
    }

}

@Composable
fun ImageFromUrl(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "The delasign logo",
        contentScale =  ContentScale.FillBounds,
        modifier = Modifier.fillMaxWidth()
            .height(160.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
    )
}

//@Preview
//@Composable
//fun PreviewImageFromUrl() {
//    Column {
//        MusicItem("Some name", "Some name", Modifier)
//    }
//}