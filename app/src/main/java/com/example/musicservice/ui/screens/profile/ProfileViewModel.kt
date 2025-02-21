package com.example.musicservice.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicservice.data.profile.ProfileRepository
import com.example.musicservice.data.profile.UserProfile
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileViewModel() : ViewModel() {
    private val repository: ProfileRepository = ProfileRepository()

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    private val _userEmail = MutableStateFlow<String?>(null)
    val userEmail: StateFlow<String?> = _userEmail

    init {
        if (FirebaseAuth.getInstance().currentUser != null) {
            loadUserProfile()
            loadUserEmail()
        }
    }

    private fun loadUserProfile() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        viewModelScope.launch {
            val profile = repository.getUserProfile(userId)
            if (profile != null) {
                _userProfile.value = profile
            } else {
                val newProfile = UserProfile(id = userId)
                repository.updateUserProfile(newProfile)
                _userProfile.value = newProfile
            }
        }
    }

    private fun loadUserEmail() {
        val user = FirebaseAuth.getInstance().currentUser
        _userEmail.value = user?.email
    }

    fun updateUserProfile(updatedProfile: UserProfile) {
        viewModelScope.launch {
            repository.updateUserProfile(updatedProfile)
            _userProfile.value = updatedProfile
        }
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }

    fun deleteUserProfile() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            viewModelScope.launch {
                repository.deleteUserProfile(it)
                FirebaseAuth.getInstance().signOut()
            }
        }
    }
}

