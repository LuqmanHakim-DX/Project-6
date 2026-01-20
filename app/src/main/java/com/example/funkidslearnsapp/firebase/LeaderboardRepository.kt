package com.example.funkidslearnsapp.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

data class LeaderboardEntry(
    val name: String = "",
    val score: Int = 0
)

object LeaderboardRepository {

    private val db = FirebaseFirestore.getInstance()
    private val uid get() = FirebaseAuth.getInstance().uid ?: "unknown"

    suspend fun submitScore(
        game: String,
        name: String,
        score: Int
    ) {
        val ref = db
            .collection("leaderboards")
            .document(game)
            .collection("scores")
            .document(uid)

        val existing = ref.get().await()
        val oldScore = existing.getLong("score")?.toInt() ?: 0

        if (score > oldScore) {
            ref.set(
                mapOf(
                    "name" to name,
                    "score" to score
                )
            )
        }
    }

    suspend fun getTopScores(game: String): List<LeaderboardEntry> {
        return db
            .collection("leaderboards")
            .document(game)
            .collection("scores")
            .orderBy("score", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(10)
            .get()
            .await()
            .toObjects(LeaderboardEntry::class.java)
    }
}
