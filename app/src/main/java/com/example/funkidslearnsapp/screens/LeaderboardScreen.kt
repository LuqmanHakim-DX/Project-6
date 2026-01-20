package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funkidslearnsapp.R
import com.example.funkidslearnsapp.firebase.LeaderboardEntry
import com.example.funkidslearnsapp.firebase.LeaderboardRepository

@Composable
fun LeaderboardScreen(
    onBack: () -> Unit
) {
    var selectedGame by remember { mutableStateOf("letter") }
    var scores by remember { mutableStateOf<List<LeaderboardEntry>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(selectedGame) {
        loading = true
        scores = LeaderboardRepository.getTopScores(selectedGame)
        loading = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.leaderboardscreen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🏆 Leaderboard", fontSize = 28.sp)

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { selectedGame = "letter" }) {
                    Text("Letters")
                }
                Button(onClick = { selectedGame = "number" }) {
                    Text("Numbers")
                }
                Button(onClick = { selectedGame = "color" }) {
                    Text("Colors")
                }
            }

            Spacer(Modifier.height(16.dp))

            if (loading) {
                Text("Loading...")
            } else if (scores.isEmpty()) {
                Text("No scores yet for ${selectedGame.replaceFirstChar { it.uppercase() }}")
            } else {
                scores.forEachIndexed { index, entry ->
                    Text(
                        text = "${index + 1}. ${entry.name} - ${entry.score}",
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}
