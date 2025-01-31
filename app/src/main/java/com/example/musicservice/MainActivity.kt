package com.example.musicservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicservice.ui.screens.AuthorizationScreen
import com.example.musicservice.ui.screens.CatalogScreen
import com.example.musicservice.ui.screens.RegistrationScreen


//import com.example.musicservice.ui.theme.MusicServiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MusicApp();
        }
    }
}

@Composable
fun MusicApp() {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "registration",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("registration") { RegistrationScreen(navController) }
            composable("catalog") { CatalogScreen(navController) }
            composable("authorization") { AuthorizationScreen(navController) }
        }
    }
}
