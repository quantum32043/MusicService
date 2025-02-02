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
import com.example.musicservice.ui.screens.authorization.AuthorizationScreen
import com.example.musicservice.ui.screens.CatalogScreen
import com.example.musicservice.ui.screens.authorization.AuthorizationViewModel
import com.example.musicservice.ui.screens.registration.RegistrationScreen
import com.example.musicservice.ui.screens.registration.RegistrationViewModel


//import com.example.musicservice.ui.theme.MusicServiceTheme

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

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "registration",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("registration") {
                val registrationViewModel: RegistrationViewModel = RegistrationViewModel()
                RegistrationScreen(navController, registrationViewModel)
            }
            composable("catalog") { CatalogScreen(navController) }
            composable("authorization") {
                val authorizationViewModel: AuthorizationViewModel = AuthorizationViewModel()
                AuthorizationScreen(navController, authorizationViewModel)
            }
        }
    }
}


@Preview
@Composable
fun MusicAppPreview() {
    MusicApp();
}
