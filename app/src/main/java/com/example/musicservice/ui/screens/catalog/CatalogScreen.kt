package com.example.musicservice.ui.screens.catalog

import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musicservice.ui.components.MusicItem
import com.example.musicservice.ui.components.RegistrationTextField
import com.example.musicservice.ui.theme.BlueGray
import com.example.musicservice.ui.theme.DeepGray
import com.example.musicservice.ui.theme.Violet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(navController: NavController, viewModel: CatalogViewModel) {
    val songs = viewModel.songs.collectAsState()
    var searchQuery by rememberSaveable { mutableStateOf("") }

    val filteredSongs = songs.value.filter { song ->
        song.title.contains(searchQuery, true) ||
                song.artist.contains(searchQuery, true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepGray)
            .padding(vertical = 10.dp, horizontal = 5.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            ) {
            Text(
                "Song catalog",
                Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Row()
            {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(45.dp)
                        .clickable { run { navController.navigate("favorites") } },
                    tint = Color.Red,
                )
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.size(45.dp)
                        .clickable { run { navController.navigate("profile") } },
                    tint = Violet
                )
            }
        }
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search by title or artist") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = BlueGray,
                unfocusedTextColor = Violet,
                focusedTextColor = Violet,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        LazyVerticalGrid (
            columns = GridCells.Adaptive(150.dp),
        ) {
            items(filteredSongs) { song ->
                MusicItem(song) {
                    navController.navigate("itemScreen/${song.id}")
                }
            }
        }
    }
}