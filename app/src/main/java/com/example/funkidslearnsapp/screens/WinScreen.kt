package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import com.example.funkidslearnsapp.R
import com.example.funkidslearnsapp.data.HighScoreManager
import com.example.funkidslearnsapp.firebase.LeaderboardRepository
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme


@Composable
fun WinScreen(
    score: Int,
    gameKey: Preferences.Key<Int>,
    gameId: String,
    onBackToMenu: () -> Unit
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
            painter = painterResource(id = R.drawable.winscreen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("YOU WIN 🎉", fontSize = 36.sp)
            Text("Score: $score", fontSize = 24.sp)
            Text("High Score: $highScore", fontSize = 24.sp)
            Button(onClick = onBackToMenu) {
                Text("Back to Menu")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun WinScreenPreview() {
    FunKidsLearnsAppTheme {
        WinScreen(
            score = 100,
            gameKey = HighScoreManager.COLOR_HIGH,
            gameId = "color",
            onBackToMenu = {})
    }
}
