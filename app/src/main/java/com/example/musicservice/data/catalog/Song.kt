package com.example.musicservice.data.catalog

data class Song(
    val id: String = "",
    val title: String = "",
    val artist: String = "",
    val about: String = "",
    val imageUrl: String = "",
    val images: List<String> = emptyList()
)
