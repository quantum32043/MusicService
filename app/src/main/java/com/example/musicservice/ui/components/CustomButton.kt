package com.example.musicservice.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicservice.ui.theme.Violet
import com.example.musicservice.ui.theme.White

@Composable
fun CustomButton(text: String, action: () -> Unit) {
    Button(
        onClick = { action() },
        Modifier.fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 15.dp),
        colors = ButtonDefaults.buttonColors(Violet)
    ) {
        Text(text, fontSize = 20.sp, color = Color.White, textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton("f") { }
}