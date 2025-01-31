package com.example.musicservice.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.musicservice.ui.theme.Violet

@Composable
fun TextWithClickableLink(commonText: String, linkText: String, action: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(Color.White)) {
            append(commonText)
        }
        pushStringAnnotation(tag = "authorization", annotation = "authorization")
        withStyle(style = androidx.compose.ui.text.SpanStyle(color = Violet)) {
            append(linkText)
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { action() }
    )
}