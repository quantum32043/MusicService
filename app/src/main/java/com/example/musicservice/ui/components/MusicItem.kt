package com.example.musicservice.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicservice.ui.theme.Violet
import com.example.musicservice.ui.theme.White

@Composable
fun MusicItem(name: String, modifier: Modifier) {
    Box(
        modifier = modifier.then(Modifier
                .fillMaxWidth()
                .requiredHeight(40.dp)
                .border(width = 2.dp, color = Violet, shape = RoundedCornerShape(30.dp))
                .background(White))
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            color = White
        )
    }
}