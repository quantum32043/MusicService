//package com.example.musicservice.ui.screens.profile
//
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.detectHorizontalDragGestures
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.musicservice.data.profile.UserProfile
//import com.example.musicservice.ui.components.ProfileTextField
//import com.example.musicservice.ui.theme.DeepGray
//
//import android.app.DatePickerDialog
//import android.widget.DatePicker
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowDropDown
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.text.TextStyle
//import com.example.musicservice.ui.theme.BlueGray
//import com.example.musicservice.ui.theme.Violet
//import java.util.*
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel) {
//    val userProfile by viewModel.userProfile.collectAsState()
//    val userEmail by viewModel.userEmail.collectAsState()
//
//    var name by remember { mutableStateOf(userProfile?.name ?: "") }
//    var surname by remember { mutableStateOf(userProfile?.surname ?: "") }
//    var email by remember { mutableStateOf(userEmail ?: "") }
//    var phone by remember { mutableStateOf(userProfile?.phone ?: "") }
//    var address by remember { mutableStateOf(userProfile?.address ?: "") }
//    var dateOfBirth by remember { mutableStateOf(userProfile?.dateOfBirth ?: "") }
//    var gender by remember { mutableStateOf(userProfile?.gender ?: "") }
//    var country by remember { mutableStateOf(userProfile?.country ?: "") }
//    var city by remember { mutableStateOf(userProfile?.city ?: "") }
//    var zipCode by remember { mutableStateOf(userProfile?.zipCode ?: "") }
//
//    var scrollState = rememberScrollState()
//
//    LaunchedEffect(userProfile) {
//        userProfile?.let {
//            name = it.name
//            surname = it.surname
//            phone = it.phone
//            address = it.address
//            dateOfBirth = it.dateOfBirth
//            gender = it.gender
//            country = it.country
//            city = it.city
//            zipCode = it.zipCode
//        }
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize()
//            .background(DeepGray)
//            .verticalScroll(scrollState)
//            .padding(vertical = 10.dp, horizontal = 5.dp)
//    ) {
//        Text("Profile",
//            Modifier.align(Alignment.Start)
//                .padding(10.dp),
//            fontSize = 30.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.White
//        )
//
//        ProfileTextField(
//            label = "Name",
//            value = name,
//            onValueChange = { newValue -> name = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        ProfileTextField(
//            label = "Surname",
//            value = surname,
//            onValueChange = { newValue -> surname = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        ProfileTextField(
//            label = "Email",
//            value = email,
//            onValueChange = { newValue -> email = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        ProfileTextField(
//            label = "Phone",
//            value = phone,
//            onValueChange = { newValue -> phone = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        ProfileTextField(
//            label = "Address",
//            value = address,
//            onValueChange = { newValue -> address = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        ProfileTextField(
//            label = "Date of Birth",
//            value = dateOfBirth,
//            onValueChange = { newValue -> dateOfBirth = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        ProfileTextField(
//            label = "Gender",
//            value = gender,
//            onValueChange = { newValue -> gender = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        ProfileTextField(
//            label = "Country",
//            value = country,
//            onValueChange = { newValue -> country = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        ProfileTextField(
//            label = "City",
//            value = city,
//            onValueChange = { newValue -> city = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        ProfileTextField(
//            label = "Zip Code",
//            value = zipCode,
//            onValueChange = { newValue -> zipCode = newValue }
//        )
//        Spacer(Modifier.height(10.dp))
//        Button(
//            onClick = {
//                userProfile?.let {
//                    viewModel.updateUserProfile(
//                        it.copy(
//                            name = name,
//                            surname = surname,
//                            email = email,
//                            phone = phone,
//                            address = address,
//                            dateOfBirth = dateOfBirth,
//                            gender = gender,
//                            country = country,
//                            city = city,
//                            zipCode = zipCode
//                        )
//                    )
//                }
//            },
//            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//        ) {
//            Text("Save Profile")
//        }
//
//        Button(
//            onClick = {
//                viewModel.signOut()
//                navController.navigate("authorization") {
//                    popUpTo(0) { inclusive = true }
//                    launchSingleTop = true
//                }
//            },
//            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//        ) {
//            Text("Log Out")
//        }
//
//        Button(
//            onClick = {
//                viewModel.deleteUserProfile()
//                viewModel.signOut()
//                navController.navigate("authorization") {
//                    popUpTo("authorization") { inclusive = true }
//                    launchSingleTop = true
//                }
//            },
//            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//        ) {
//            Text("Delete Account")
//        }
//    }
//}





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

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import com.example.musicservice.ui.theme.BlueGray
import com.example.musicservice.ui.theme.Violet
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel) {
    val userProfile by viewModel.userProfile.collectAsState()
    val userEmail by viewModel.userEmail.collectAsState()

    var name by remember { mutableStateOf(userProfile?.name ?: "") }
    var surname by remember { mutableStateOf(userProfile?.surname ?: "") }
    var email by remember { mutableStateOf(userEmail ?: "") }
    var phone by remember { mutableStateOf(userProfile?.phone ?: "") }
    var address by remember { mutableStateOf(userProfile?.address ?: "") }
    var dateOfBirth by remember { mutableStateOf(userProfile?.dateOfBirth ?: "") }
    var gender by remember { mutableStateOf(userProfile?.gender ?: "") }
    var country by remember { mutableStateOf(userProfile?.country ?: "") }
    var city by remember { mutableStateOf(userProfile?.city ?: "") }
    var zipCode by remember { mutableStateOf(userProfile?.zipCode ?: "") }

    val context = LocalContext.current
    var scrollState = rememberScrollState()

    LaunchedEffect(userProfile) {
        userProfile?.let {
            name = it.name
            surname = it.surname
            phone = it.phone
            address = it.address
            dateOfBirth = it.dateOfBirth
            gender = it.gender
            country = it.country
            city = it.city
            zipCode = it.zipCode
        }
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

        // Основной контент с отступами
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = {},
                label = { Text("Mail") },
                trailingIcon = { Icon(Icons.Default.AlternateEmail, contentDescription = null) },
                readOnly = true,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = BlueGray,
                    unfocusedTextColor = Violet,
                    focusedTextColor = Violet,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                trailingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = BlueGray,
                    unfocusedTextColor = Violet,
                    focusedTextColor = Violet,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Surname") },
                trailingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = BlueGray,
                    unfocusedTextColor = Violet,
                    focusedTextColor = Violet,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(12.dp)
            )
            // Выпадающий список для выбора пола
            var expanded by remember { mutableStateOf(false) }
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = gender,
                    onValueChange = {},
                    label = { Text("Gender") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = BlueGray,
                        unfocusedTextColor = Violet,
                        focusedTextColor = Violet,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(fontSize = 18.sp),
                    shape = RoundedCornerShape(12.dp)
                )
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    listOf("None", "Male", "Female").forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                gender = option
                                expanded = false
                            }
                        )
                    }
                }
            }
            // Поле даты рождения с DatePickerDialog
            val calendar = Calendar.getInstance()
            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = {},
                label = { Text("Date of birth") },
                trailingIcon = {
                    IconButton(onClick = {
                        val datePicker = DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                dateOfBirth = String.format("%02d.%02d.%d", dayOfMonth, month + 1, year)
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        )
                        datePicker.show()
                    }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = null)
                    }
                },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = BlueGray,
                    unfocusedTextColor = Violet,
                    focusedTextColor = Violet,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(12.dp),
                readOnly = true
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                trailingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = BlueGray,
                    unfocusedTextColor = Violet,
                    focusedTextColor = Violet,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                trailingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = BlueGray,
                    unfocusedTextColor = Violet,
                    focusedTextColor = Violet,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = country,
                onValueChange = { country = it },
                label = { Text("Country") },
                trailingIcon = { Icon(Icons.Default.Map, contentDescription = null) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = BlueGray,
                    unfocusedTextColor = Violet,
                    focusedTextColor = Violet,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("City") },
                trailingIcon = { Icon(Icons.Default.LocationCity, contentDescription = null) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = BlueGray,
                    unfocusedTextColor = Violet,
                    focusedTextColor = Violet,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = zipCode,
                onValueChange = { zipCode = it },
                label = { Text("Zip code") },
                trailingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = BlueGray,
                    unfocusedTextColor = Violet,
                    focusedTextColor = Violet,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = {
                    userProfile?.let {
                        viewModel.updateUserProfile(
                            it.copy(
                                name = name,
                                surname = surname,
                                email = email,
                                phone = phone,
                                address = address,
                                dateOfBirth = dateOfBirth,
                                gender = gender,
                                country = country,
                                city = city,
                                zipCode = zipCode
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 10.dp)
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
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 10.dp)
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
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 10.dp)
            ) {
                Text("Delete Account")
            }
        }
    }
}

