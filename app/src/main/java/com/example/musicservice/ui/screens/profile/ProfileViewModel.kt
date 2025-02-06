package com.example.musicservice.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicservice.data.profile.UserProfile
import com.example.musicservice.data.profile.UserProfileRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserProfileRepository): ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    fun loadUserProfile() {
        val userId = auth.currentUser?.uid
        userId?.let {
            viewModelScope.launch {
                repository.getUserProfile(it).collect { profile ->
                    _userProfile.value = profile
                }
            }
        }
    }

    fun updateUserProfile(userProfile: UserProfile) {
        val userId = auth.currentUser?.uid
        userId?.let {
            viewModelScope.launch {
                repository.updateUserProfile(it, userProfile)
            }
        }
    }

    fun deleteUserProfile() {
        val userId = auth.currentUser?.uid
        userId?.let {
            viewModelScope.launch {
                repository.deleteUserProfile(it)
            }
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
