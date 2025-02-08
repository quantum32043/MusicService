package com.example.musicservice.data.catalog

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CatalogRepository {
    private val db = Firebase.firestore
    private val songCollection = db.collection("songs")

    fun getSongs(): Flow<List<Song>> = callbackFlow {
        val listener = songCollection.addSnapshotListener { snapshot, _ ->
            val songs = snapshot?.documents?.mapNotNull { it.toObject(Song::class.java)?.copy(id = it.id) } ?: emptyList()
            trySend(songs)
        }
        awaitClose { listener.remove() }
    }

    suspend fun addSong(song: Song) {
        songCollection.add(song)
    }
}