package com.example.musicservice.data.profile

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserProfileRepository {
    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("users")

    fun getUserProfile(userId: String): Flow<UserProfile?> = callbackFlow {
        val listener = userCollection.document(userId)
            .addSnapshotListener { snapshot, _ ->
                val profile = snapshot?.toObject(UserProfile::class.java)
                trySend(profile)
            }
        awaitClose { listener.remove() }
    }

    suspend fun updateUserProfile(userId: String, userProfile: UserProfile) {
        userCollection.document(userId).set(userProfile)
    }

    suspend fun deleteUserProfile(userId: String) {
        userCollection.document(userId).delete()
    }
}
