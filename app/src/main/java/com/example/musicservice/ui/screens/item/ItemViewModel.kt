package com.example.musicservice.ui.screens.item

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicservice.data.item.ItemRepository
import com.example.musicservice.data.catalog.Song
import com.example.musicservice.data.favorite.FavoritesRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemViewModel() : ViewModel() {
    private val itemRepository: ItemRepository = ItemRepository()
    private val favoritesRepository: FavoritesRepository = FavoritesRepository()

    private val _song = MutableStateFlow<Song?>(null)
    val song: StateFlow<Song?> = _song

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    private val userId: String = Firebase.auth.currentUser?.uid ?: ""

    fun loadSongInfo(songId: String) {
        viewModelScope.launch {
            _song.value = itemRepository.getSongById(songId)
        }
    }

    fun checkIfFavorite(songId: String) {
        viewModelScope.launch {
            _isFavorite.value = favoritesRepository.isFavorite(userId, songId)
        }
    }

    fun toggleFavorite(song: Song) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                favoritesRepository.removeFromFavorites(userId, song.id)
            } else {
                favoritesRepository.addToFavorites(userId, song)
            }
            _isFavorite.value = !_isFavorite.value
        }
    }
}




