package com.example.musicservice.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel) {
    val userProfile = viewModel.userProfile.collectAsState().value

    // Добавим логирование или обработку случая, если profile пустой
    LaunchedEffect(Unit) {
        viewModel.loadUserProfile()
        if (userProfile == null) {
            Log.d("ProfileScreen", "User profile is null.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Profile", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        if (userProfile == null) {
            Text("Loading profile...") // Если профиль еще не загружен
        } else {
            val fields = listOf(
                "Name", "Email", "Phone", "Address", "Date of Birth", "Gender",
                "Profile Picture", "Country", "City", "Zip Code"
            )

            fields.forEachIndexed { index, field ->
                val value = when (index) {
                    0 -> userProfile.name
                    1 -> userProfile.email
                    2 -> userProfile.phone
                    3 -> userProfile.address
                    4 -> userProfile.dateOfBirth
                    5 -> userProfile.gender
                    6 -> userProfile.profilePicture
                    7 -> userProfile.country
                    8 -> userProfile.city
                    9 -> userProfile.zipCode
                    else -> ""
                }

                TextField(
                    value = value,
                    onValueChange = { newValue ->
                        when (index) {
                            0 -> viewModel.updateUserProfile(userProfile.copy(name = newValue))
                            1 -> viewModel.updateUserProfile(userProfile.copy(email = newValue))
                            2 -> viewModel.updateUserProfile(userProfile.copy(phone = newValue))
                            3 -> viewModel.updateUserProfile(userProfile.copy(address = newValue))
                            4 -> viewModel.updateUserProfile(userProfile.copy(dateOfBirth = newValue))
                            5 -> viewModel.updateUserProfile(userProfile.copy(gender = newValue))
                            6 -> viewModel.updateUserProfile(userProfile.copy(profilePicture = newValue))
                            7 -> viewModel.updateUserProfile(userProfile.copy(country = newValue))
                            8 -> viewModel.updateUserProfile(userProfile.copy(city = newValue))
                            9 -> viewModel.updateUserProfile(userProfile.copy(zipCode = newValue))
                        }
                    },
                    label = { Text(field) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
        }

        // Кнопки для выхода и удаления аккаунта
        Button(
            onClick = {
                viewModel.signOut() // Выполняем выход
//                navController.popBackStack("authorization", false)
//                navController.navigate("authorization") {
//                    popUpTo("authorization") { inclusive = true }
//                    launchSingleTop = true
//                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Log Out")
        }

        Button(
            onClick = {
                viewModel.deleteUserProfile()
                viewModel.signOut()
//                navController.popBackStack("authorization", false)
//                navController.navigate("authorization") {
//                    popUpTo("authorization") { inclusive = true }
//                    launchSingleTop = true
//                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Delete Account")
        }
    }
}
