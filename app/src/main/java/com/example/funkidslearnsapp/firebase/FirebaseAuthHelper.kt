package com.example.funkidslearnsapp.firebase

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthHelper {

    fun signInAnonymously() {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            auth.signInAnonymously()
        }
    }
}
