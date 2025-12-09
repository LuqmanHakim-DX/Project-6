package com.example.funlearn.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameMenuScreen(onStartGame: (String)->Unit, onSettings: ()->Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Game Menu", modifier = Modifier.padding(8.dp))
        Spacer(Modifier.height(12.dp))
        // Example: game 1..5 buttons
        for (i in 1..5) {
            Button(onClick = { onStartGame(i.toString()) }, modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                Text("Play Game $i")
            }
        }
        Spacer(Modifier.height(12.dp))
        Button(onClick = onSettings, modifier = Modifier.align(Alignment.End)) {
            Text("Settings")
        }
    }
}
