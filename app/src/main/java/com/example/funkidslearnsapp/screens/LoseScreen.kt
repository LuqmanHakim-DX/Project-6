package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import com.example.funkidslearnsapp.R
import com.example.funkidslearnsapp.data.HighScoreManager
import com.example.funkidslearnsapp.firebase.LeaderboardRepository

@Composable
fun LoseScreen(
    score: Int,
    gameKey: Preferences.Key<Int>,
    gameId: String,
    onRetry: () -> Unit
) {
    val context = LocalContext.current
    val manager = remember { HighScoreManager(context) }
    val highScore by manager.getHighScore(gameKey).collectAsState(initial = 0)

    LaunchedEffect(score) {
        manager.saveHighScore(gameKey, score)
        if (score > highScore) {
            LeaderboardRepository.submitScore(
                game = gameId,
                name = "Player",
                score = score
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.losescreen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("You Lost!")
            Text("Score: $score")
            Text("High Score: $highScore")
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }
}
