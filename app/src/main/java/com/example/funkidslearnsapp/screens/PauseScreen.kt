package com.example.funlearn.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PauseScreen(onResume: ()->Unit, onQuit: ()->Unit) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Paused")
        Spacer(Modifier.height(12.dp))
        Button(onClick = onResume) { Text("Resume") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = onQuit) { Text("Quit to Menu") }
    }
}
