package com.example.funlearn.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WinScreen(onBackToMenu: ()->Unit) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("You Win! 🎉")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onBackToMenu) { Text("Back to Menu") }
    }
}
