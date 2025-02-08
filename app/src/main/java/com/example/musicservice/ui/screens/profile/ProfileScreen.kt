package com.example.musicservice.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musicservice.data.profile.UserProfile
import com.example.musicservice.ui.components.ProfileTextField
import com.example.musicservice.ui.theme.DeepGray

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel) {
    val userProfile = viewModel.userProfile.collectAsState().value ?: UserProfile(id = "")

    var name = remember { mutableStateOf(userProfile.name) }
    var surname = remember { mutableStateOf(userProfile.surname) }
    var email = remember { mutableStateOf(userProfile.email) }
    var phone = remember { mutableStateOf(userProfile.phone) }
    var address = remember { mutableStateOf(userProfile.address) }
    var dateOfBirth = remember { mutableStateOf(userProfile.dateOfBirth) }
    var gender = remember { mutableStateOf(userProfile.gender) }
    var country = remember { mutableStateOf(userProfile.country) }
    var city = remember { mutableStateOf(userProfile.city) }
    var zipCode = remember { mutableStateOf(userProfile.zipCode) }

    var scrollState = rememberScrollState()

    LaunchedEffect(userProfile) {
        name.value = userProfile.name
        surname.value = userProfile.surname
        email.value = userProfile.email
        phone.value = userProfile.phone
        address.value = userProfile.address
        dateOfBirth.value = userProfile.dateOfBirth
        gender.value = userProfile.gender
        country.value = userProfile.country
        city.value = userProfile.city
        zipCode.value = userProfile.zipCode
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(DeepGray)
            .verticalScroll(scrollState)
            .padding(vertical = 10.dp, horizontal = 5.dp)
    ) {
        Text("Profile",
            Modifier.align(Alignment.Start)
            .padding(10.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        ProfileTextField(
            label = "Name",
            value = TextFieldValue(name.value),
            onValueChange = { newValue -> name.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        ProfileTextField(
            label = "Surname",
            value = TextFieldValue(surname.value),
            onValueChange = { newValue -> surname.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        ProfileTextField(
            label = "Email",
            value = TextFieldValue(email.value),
            onValueChange = { newValue -> email.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        ProfileTextField(
            label = "Phone",
            value = TextFieldValue(phone.value),
            onValueChange = { newValue -> phone.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        ProfileTextField(
            label = "Address",
            value = TextFieldValue(address.value),
            onValueChange = { newValue -> address.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        ProfileTextField(
            label = "Date of Birth",
            value = TextFieldValue(dateOfBirth.value),
            onValueChange = { newValue -> dateOfBirth.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        ProfileTextField(
            label = "Gender",
            value = TextFieldValue(gender.value),
            onValueChange = { newValue -> gender.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        ProfileTextField(
            label = "Country",
            value = TextFieldValue(country.value),
            onValueChange = { newValue -> country.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        ProfileTextField(
            label = "City",
            value = TextFieldValue(city.value),
            onValueChange = { newValue -> city.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        ProfileTextField(
            label = "Zip Code",
            value = TextFieldValue(zipCode.value),
            onValueChange = { newValue -> zipCode.value = newValue.text }
        )
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {
                viewModel.updateUserProfile(
                    userProfile.copy(
                        name = name.value,
                        surname = surname.value,
                        email = email.value,
                        phone = phone.value,
                        address = address.value,
                        dateOfBirth = dateOfBirth.value,
                        gender = gender.value,
                        country = country.value,
                        city = city.value,
                        zipCode = zipCode.value
                    )
                )
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Save Profile")
        }

        Button(
            onClick = {
                viewModel.signOut()
                navController.navigate("authorization") {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Log Out")
        }

        Button(
            onClick = {
                viewModel.deleteUserProfile()
                viewModel.signOut()
                navController.navigate("authorization") {
                    popUpTo("authorization") { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Delete Account")
        }
    }
}

