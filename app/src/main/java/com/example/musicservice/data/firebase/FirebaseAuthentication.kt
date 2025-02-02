package com.example.musicservice.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

val auth = FirebaseAuth.getInstance()

fun signUp(email: String, password: String) {
    println(email)
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener() { task ->
            if(task.isSuccessful) {
                Log.d("FirebaseAuth", "Регистрация успешна: ${auth.currentUser?.email}")
            } else {
                Log.e("FirebaseAuth", "Ошибка: ${task.exception?.message}")
            }
        }
}


fun signIn(email: String, password: String) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener() { task ->
            if(task.isSuccessful) {
                Log.d("FirebaseAuth", "Регистрация успешна: ${auth.currentUser?.email}")
            } else {
                Log.e("FirebaseAuth", "Ошибка: ${task.exception?.message}")
            }
        }
}