package com.example.musicservice.ui.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musicservice.data.firebase.signUp
import com.example.musicservice.ui.components.CustomButton
import com.example.musicservice.ui.components.RegistrationTextField
import com.example.musicservice.ui.components.TextWithClickableLink
import com.example.musicservice.ui.theme.DeepGray

@Composable
fun RegistrationScreen(navController: NavController, registrationViewModel: RegistrationViewModel) {
    var emailFieldValue = remember { mutableStateOf(TextFieldValue()) }
    var passwordFieldValue = remember { mutableStateOf(TextFieldValue()) }

    val isUserAuthenticated by registrationViewModel.isUserAuthenticated.collectAsState()

    LaunchedEffect(isUserAuthenticated) {
        if (isUserAuthenticated) {
            navController.navigate("catalog") {
                popUpTo("registration") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(DeepGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create account",
            fontSize = 40.sp,
            fontWeight = FontWeight.W700,
            color = Color.White
        )
        Spacer(Modifier.height(40.dp))
        RegistrationTextField(
            "Enter email address",
            emailFieldValue.value,
            onValueChange = {
                emailFieldValue.value = it
            }
        )
        Spacer(Modifier.height(40.dp))
        RegistrationTextField(
            "Create a password",
            passwordFieldValue.value,
            onValueChange = {
                passwordFieldValue.value = it
            }
        )
        Spacer(Modifier.height(40.dp))
        CustomButton("Sign Up") {
                registrationViewModel.signUp(
                    emailFieldValue.value.text.trim(),
                    passwordFieldValue.value.text.trim()
                )
        }
        Spacer(Modifier.height(40.dp))
        TextWithClickableLink(
            "Alredy registred? ",
            "Log in",
            {
                navController.navigate("authorization")
            }
        )
    }
}