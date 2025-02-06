package com.example.musicservice.ui.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.musicservice.ui.components.CustomButton
import com.example.musicservice.ui.components.RegistrationTextField
import com.example.musicservice.ui.components.TextWithClickableLink
import com.example.musicservice.ui.theme.DeepGray
import com.google.firebase.auth.FirebaseAuth

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.([a-z]{2,})$".toRegex()
    return email.matches(emailRegex)
}

fun isValidPassword(password: String): Boolean {
    val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#\$%^&*])[A-Za-z\\d!@#\$%^&*]{6,}$".toRegex()
    return password.matches(passwordRegex)
}

@Composable
fun RegistrationScreen(navController: NavController, registrationViewModel: RegistrationViewModel) {
    var emailFieldValue = remember { mutableStateOf(TextFieldValue()) }
    var passwordFieldValue = remember { mutableStateOf(TextFieldValue()) }

    var isEmailCorrect = remember { mutableStateOf(true) }
    var isPasswordCorrect = remember { mutableStateOf(true) }

    val isEmailAlreadyRegistred by registrationViewModel.isEmailAlreadyRegistred.collectAsState()

    val onRegisterClick = {
        isEmailCorrect.value = isValidEmail(emailFieldValue.value.text.trim())
        isPasswordCorrect.value = isValidPassword(passwordFieldValue.value.text.trim())
        if(isEmailCorrect.value && isPasswordCorrect.value) {
            registrationViewModel.signUp(
                emailFieldValue.value.text.trim(),
                passwordFieldValue.value.text.trim()
            )
        }
    }

    LaunchedEffect(Unit) {
        if (FirebaseAuth.getInstance().currentUser != null) {
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
        Spacer(Modifier.height(20.dp))
        if (isEmailAlreadyRegistred) {
            Text(
                "The entered email is already registred",
                fontSize = 15.sp,
                color = Color.Red,
            )
        } else if (!isEmailCorrect.value) {
            Text(
                "Invalid email format",
                fontSize = 15.sp,
                color = Color.Red,
            )
        } else if (!isPasswordCorrect.value) {
            Text(
                "Password must contain at least 1 uppercase latter," +
                        "1 lowercase latter, 1 special symbol" +
                        "and be at least 6 symbols long",
                fontSize = 15.sp,
                color = Color.Red,
                modifier = Modifier.padding(15.dp)

            )
        }
        Spacer(Modifier.height(20.dp))
        CustomButton("Sign Up", onRegisterClick)
        Spacer(Modifier.height(40.dp))
        TextWithClickableLink(
            "Alredy registred? ",
            "Log in",
            {
                navController.navigate("catalog")
            }
        )
    }
}
