package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GameplayScreen(
    gameId: String,
    onWin: ()->Unit,
    onLose: ()->Unit,
    onPause: ()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFF9C4), Color(0xFFB3E5FC))
                )
            )
    ) {
    Column(Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Gameplay - Game $gameId")
        Spacer(Modifier.height(12.dp))
        // TODO: Replace with real game canvas / Composable
        Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text("Game canvas placeholder")
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = onPause) { Text("Pause") }
            Button(onClick = onWin) { Text("Win") }
            Button(onClick = onLose) { Text("Lose") }
        }
    }
    }
}
