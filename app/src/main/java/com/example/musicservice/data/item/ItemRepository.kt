package com.example.musicservice.data.item

import android.util.Log
import com.example.musicservice.data.catalog.Song
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ItemRepository {
    private val db = FirebaseFirestore.getInstance()
    private val songsCollection = db.collection("songs")

    suspend fun getSongById(songId: String): Song? {
        var song: Song? = Song(
            id = "",
            artist = "",
            title = "",
            about = "",
            imageUrl = "",
            images = listOf("")
        );
        try {
            val document = songsCollection.document(songId).get().await()
            song = document.toObject(Song::class.java)?.copy(id = document.id)
        } catch (e: Exception) {
            Log.e("ItemScreen", "No connection")
        }
        return song
    }
}
