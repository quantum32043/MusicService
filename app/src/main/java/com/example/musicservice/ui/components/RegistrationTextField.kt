package com.example.musicservice.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicservice.ui.theme.BlueGray
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.ui.graphics.Color
import com.example.musicservice.ui.theme.LightBlueGray
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationTextField(text: String) {
    var textFieldValue = remember { TextFieldValue() }

    TextField(textFieldValue,
        onValueChange = { textFieldValue = it },
        label = { Text(text) },
        modifier = Modifier.height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor  = BlueGray,
            unfocusedTextColor = Color.Red,
            focusedTextColor = Color.Red,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent),
        textStyle = TextStyle(fontSize = 10.sp),
        shape = RoundedCornerShape(12.dp))
}