package com.example.musicservice.ui.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel(private val auth: FirebaseAuth = FirebaseAuth.getInstance()): ViewModel() {
    private val _isUserAuthenticated = MutableStateFlow(false)
    val isUserAuthenticated: StateFlow<Boolean> = _isUserAuthenticated

    private val _isEmailAlreadyRegistred = MutableStateFlow(false)
    val isEmailAlreadyRegistred: StateFlow<Boolean> = _isEmailAlreadyRegistred

    init {
        auth.addAuthStateListener { firebaseAuth ->
            _isUserAuthenticated.value = firebaseAuth.currentUser != null
        }
    }

    private val _authError = MutableStateFlow<String?>(null)
    val authError: StateFlow<String?> = _authError

    fun signIn(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isUserAuthenticated.value = true
                } else {
                    _authError.value = when {
                        task.exception is FirebaseAuthInvalidCredentialsException -> "Incorrect password"
                        task.exception is FirebaseAuthInvalidUserException -> "Entered email doesn't exist"
                        else -> "Authentication failed"
                    }
                }
            }
    }

    fun clearError() {
        _authError.value = null
    }

    fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
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