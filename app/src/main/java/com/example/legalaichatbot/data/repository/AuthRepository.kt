package com.legal.aichatbot.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.legal.aichatbot.data.model.User
import com.legal.aichatbot.utils.NetworkResult
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    suspend fun login(email: String, password: String): NetworkResult<User> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user

            if (user != null) {
                val userData = getUserData(user.uid)
                NetworkResult.Success(userData)
            } else {
                NetworkResult.Error("Login failed")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Unknown error occurred")
        }
    }

    suspend fun register(email: String, password: String, displayName: String): NetworkResult<User> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user

            if (user != null) {
                val userData = User(
                    uid = user.uid,
                    email = email,
                    displayName = displayName,
                    preferredLanguage = "en"
                )

                // Save to Firestore
                firestore.collection("users")
                    .document(user.uid)
                    .set(userData)
                    .await()

                NetworkResult.Success(userData)
            } else {
                NetworkResult.Error("Registration failed")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Unknown error occurred")
        }
    }

    private suspend fun getUserData(uid: String): User {
        val doc = firestore.collection("users").document(uid).get().await()
        return doc.toObject(User::class.java) ?: User(uid = uid)
    }

    fun logout() {
        auth.signOut()
    }
}