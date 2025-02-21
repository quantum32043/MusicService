package com.example.musicservice.data.favorite

import android.util.Log
import com.example.musicservice.data.catalog.Song
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FavoritesRepository {
    private val db = Firebase.firestore
    private val favoritesCollection = db.collection("favorites")

    fun getFavoriteSongs(userId: String): Flow<List<Song>> = callbackFlow {
        val userFavoritesCollection = favoritesCollection.document(userId).collection("songs")
        val listener = userFavoritesCollection.addSnapshotListener { snapshot, _ ->
            val songs = snapshot?.documents?.mapNotNull { it.toObject(Song::class.java)?.copy(id = it.id) } ?: emptyList()
            trySend(songs)
        }
        awaitClose { listener.remove() }
    }

    suspend fun addToFavorites(userId: String, song: Song) {
        val userFavoritesCollection = favoritesCollection.document(userId).collection("songs")
        userFavoritesCollection.document(song.id).set(song)
    }

    suspend fun removeFromFavorites(userId: String, songId: String) {
        val userFavoritesCollection = favoritesCollection.document(userId).collection("songs")
        userFavoritesCollection.document(songId).delete()
    }

    suspend fun isFavorite(userId: String, songId: String): Boolean {
        val userFavoritesCollection = favoritesCollection.document(userId).collection("songs")
        return userFavoritesCollection.document(songId).get().await().exists()
    }
}
