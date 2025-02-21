package com.example.musicservice.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicservice.data.catalog.Song
import com.example.musicservice.data.favorite.FavoritesRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: FavoritesRepository) : ViewModel() {
    private val _favoriteSongs = MutableStateFlow<List<Song>>(emptyList())
    val favoriteSongs: StateFlow<List<Song>> = _favoriteSongs

    private val userId: String = Firebase.auth.currentUser?.uid ?: ""

    init { loadFavorites() }

    private fun loadFavorites() {
        viewModelScope.launch {
            repository.getFavoriteSongs(userId).collect { _favoriteSongs.value = it }
        }
    }
}
