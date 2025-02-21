package com.example.musicservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicservice.data.catalog.CatalogRepository
import com.example.musicservice.data.catalog.Song
import com.example.musicservice.data.favorite.FavoritesRepository
import com.example.musicservice.data.item.ItemRepository
import com.example.musicservice.data.profile.ProfileRepository
import com.example.musicservice.ui.screens.auth.AuthorizationScreen
import com.example.musicservice.ui.screens.catalog.CatalogScreen
import com.example.musicservice.ui.screens.auth.AuthViewModel
import com.example.musicservice.ui.screens.catalog.CatalogViewModel
import com.example.musicservice.ui.screens.profile.ProfileScreen
import com.example.musicservice.ui.screens.profile.ProfileViewModel
import com.example.musicservice.ui.screens.auth.RegistrationScreen
import com.example.musicservice.ui.screens.favorite.FavoritesScreen
import com.example.musicservice.ui.screens.favorite.FavoritesViewModel
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
                val catalogViewModel: CatalogViewModel = viewModel()
                CatalogScreen(navController, catalogViewModel)
            }
            composable("authorization") {
                AuthorizationScreen(navController, authViewModel)
            }
            composable("profile") {
                val profileViewModel: ProfileViewModel = viewModel()
                ProfileScreen(navController, profileViewModel)
            }
            composable("favorites") {
                val favoritesViewModel: FavoritesViewModel = viewModel()
                FavoritesScreen(navController, favoritesViewModel)
            }

            composable("itemScreen/{songId}") { backStackEntry ->
                val songId = backStackEntry.arguments?.getString("songId")
                val itemViewModel: ItemViewModel = viewModel()
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
