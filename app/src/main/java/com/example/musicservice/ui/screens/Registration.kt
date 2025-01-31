package com.example.musicservice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musicservice.ui.components.CustomButton
import com.example.musicservice.ui.components.RegistrationTextField
import com.example.musicservice.ui.components.TextWithClickableLink
import com.example.musicservice.ui.theme.Black
import com.example.musicservice.ui.theme.DeepGray

@Composable
fun RegistrationScreen(navController: NavController) {
    var textFieldValue = remember { TextFieldValue() }

    Column(
        modifier = Modifier.fillMaxSize().background(DeepGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Create account", fontSize = 40.sp, fontWeight = FontWeight.W700, color = Color.White)
        Spacer(Modifier.height(40.dp))
        RegistrationTextField("Enter email address")
        Spacer(Modifier.height(40.dp))
        RegistrationTextField("Create a password")
        Spacer(Modifier.height(40.dp))
        CustomButton("Sign Up", { navController.navigate("catalog")  })
        Spacer(Modifier.height(40.dp))
        TextWithClickableLink("Alredy registred? ", "Log in", { navController.navigate("authorization")})
    }
}