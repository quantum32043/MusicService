package com.example.musicservice.ui.screens.item

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicservice.data.item.ItemRepository
import com.example.musicservice.data.catalog.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    private val _song = MutableStateFlow<Song?>(null)
    val song: StateFlow<Song?> = _song

    fun loadSongInfo(songId: String) {
        Log.d("ItemViewModel", "Loading song info for id: $songId")

        viewModelScope.launch {
            val songInfo = repository.getSongById(songId)
            Log.d("ItemViewModel", "Song loaded: $songInfo")
            _song.emit(songInfo)
        }
    }
}



