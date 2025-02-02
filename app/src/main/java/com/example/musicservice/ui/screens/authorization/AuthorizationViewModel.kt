package com.example.musicservice.ui.screens.authorization

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthorizationViewModel(private val auth: FirebaseAuth = FirebaseAuth.getInstance()): ViewModel() {
    private val _isUserAuthenticated = MutableStateFlow(false)
    val isUserAuthenticated: StateFlow<Boolean> = _isUserAuthenticated

    fun signIn(email: String, password: String) {
        com.example.musicservice.data.firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if(task.isSuccessful) {
                    Log.d("FirebaseAuth", "Авторизация успешна: ${auth.currentUser?.email}}")
                    _isUserAuthenticated.value = true;
                } else {
                    Log.e("FirebaseAuth", "Ошибка: ${task.exception?.message}")
                }
            }
    }
}