package com.example.musicservice.ui.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
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

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if(task.isSuccessful) {
                    Log.d("FirebaseAuth", "Авторизация успешна: ${auth.currentUser?.email}}")
                    _isUserAuthenticated.value = true;
                } else {
                    Log.e("FirebaseAuth", "Ошибка: ${task.exception?.message}")
                }
            }
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