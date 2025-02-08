package com.example.musicservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicservice.data.catalog.CatalogRepository
import com.example.musicservice.data.catalog.Song
import com.example.musicservice.data.item.ItemRepository
import com.example.musicservice.data.profile.ProfileRepository
import com.example.musicservice.ui.screens.auth.AuthorizationScreen
import com.example.musicservice.ui.screens.catalog.CatalogScreen
import com.example.musicservice.ui.screens.auth.AuthViewModel
import com.example.musicservice.ui.screens.catalog.CatalogViewModel
import com.example.musicservice.ui.screens.profile.ProfileScreen
import com.example.musicservice.ui.screens.profile.ProfileViewModel
import com.example.musicservice.ui.screens.auth.RegistrationScreen
import com.example.musicservice.ui.screens.item.ItemScreen
import com.example.musicservice.ui.screens.item.ItemViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicApp();
        }
    }
}

@Composable
fun MusicApp() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = AuthViewModel()
    val itemRepository = ItemRepository()
    val itemViewModel = ItemViewModel(itemRepository)

//    val song = Song(
//        artist = "Bad Omens",
//        title = "Dethrone",
//        about = "Bad Omens is an American heavy metal band from Richmond, Virginia, formed in 2015 by vocalist and producer Noah Sebastian, guitarist Nicholas Ruffilo, and bassist Vincent Riquier",
//        imageUrl = "https://upload.wikimedia.org/wikipedia/en/a/a8/Finding_God_Before_God_Finds_Me.jpg",
//        images = listOf(
//            "https://cdn-images.dzcdn.net/images/artist/229b8b59aaf6f86c949696bdb07ea050/1900x1900-000000-80-0-0.jpg",
//            "https://www.themetalverseofficial.com/content/images/2024/09/Bad-Omens-Cover.jpg",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoZOKERLrjtqtNMJjT4Bxd4Dp6Z-Zq0kANAw&s",
//            "https://images.squarespace-cdn.com/content/v1/5b0dd7581aef1d319395b854/c65120e6-4a78-46c5-b701-9b8a3db5bc7e/Omens+x+Poppy+%28Bryan+Kirks%29.jpg"
//        )
//    )

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "authorization",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("registration") {
                RegistrationScreen(navController, authViewModel)
            }
            composable("catalog") {
                val catalogRepository = CatalogRepository()
                val catalogViewModel: CatalogViewModel = CatalogViewModel(catalogRepository)
                //catalogViewModel.addSong(song)
                CatalogScreen(navController, catalogViewModel)
            }
            composable("authorization") {
                AuthorizationScreen(navController, authViewModel)
            }
            composable("profile") {
                val profileRepository: ProfileRepository = ProfileRepository()
                val profileViewModel: ProfileViewModel = ProfileViewModel(profileRepository)
                ProfileScreen(navController, profileViewModel)
            }
            composable("itemScreen/{songId}") { backStackEntry ->
                val songId = backStackEntry.arguments?.getString("songId")
                if (songId != null) {
                    ItemScreen(itemViewModel, songId)
                }
            }
        }
    }
}


@Preview
@Composable
fun MusicAppPreview() {
    MusicApp();
}
