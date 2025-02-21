package com.example.musicservice.ui.screens.catalog

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicservice.data.catalog.CatalogRepository
import com.example.musicservice.data.catalog.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(): ViewModel() {
    private val repository = CatalogRepository()

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs

    private val _favoriteSongs = MutableStateFlow<Set<String>>(emptySet())
    val favoriteSongs: StateFlow<Set<String>> = _favoriteSongs

    fun toggleFavorite(songId: String) {
        _favoriteSongs.update { favorites ->
            if (favorites.contains(songId)) favorites - songId else favorites + songId
        }
    }

    init {
        loadSongs()
    }

    private fun loadSongs() {
        viewModelScope.launch {
            repository.getSongs().collect { _songs.value = it}
        }
    }

    fun addSong(song: Song) {
        viewModelScope.launch {
            repository.addSong(song)
            loadSongs()
        }
    }
}