package com.example.musicservice.data.profile

import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ProfileRepository {

    private val db = Firebase.firestore
    private val userCollection = db.collection("users")

    suspend fun getUserProfile(userId: String): UserProfile? {
        return try {
            val document = userCollection.document(userId).get().await()
            document.toObject(UserProfile::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateUserProfile(userProfile: UserProfile) {
        userCollection.document(userProfile.id).set(userProfile).await()
    }

    suspend fun deleteUserProfile(userId: String) {
        userCollection.document(userId).delete().await()
    }
}
