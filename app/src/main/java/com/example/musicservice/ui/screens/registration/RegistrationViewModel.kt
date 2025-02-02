package com.example.musicservice.ui.screens.registration

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.internal.concurrent.Task

class RegistrationViewModel(private val auth: FirebaseAuth = FirebaseAuth.getInstance()): ViewModel() {
    private val _isUserAuthenticated = MutableStateFlow(false)
    val isUserAuthenticated: StateFlow<Boolean> = _isUserAuthenticated

    private val _isEmailAlreadyRegistred = MutableStateFlow(false)
    val isEmailAlreadyRegistred: StateFlow<Boolean> = _isEmailAlreadyRegistred

    fun signUp(email: String, password: String) {
        com.example.musicservice.data.firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if(task.isSuccessful) {
                    Log.d("FirebaseAuth", "Регистрация успешна: ${auth.currentUser?.email}}")
                    _isUserAuthenticated.value = true;
                } else {
                    Log.e("FirebaseAuth", "Ошибка: ${task.exception?.message}")
                    _isEmailAlreadyRegistred.value = true;
                }
            }
    }
}