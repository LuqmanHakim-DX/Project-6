package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameMenuScreen(
    onStartGame: (String) -> Unit,
    onSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "GAME MENU",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(40.dp))

        Button(onClick = { onStartGame("letters") }) {
            Text("Letters")
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = { onStartGame("numbers") }) {
            Text("Numbers")
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = { onStartGame("shapes") }) {
            Text("Shapes")
        }

        Spacer(Modifier.height(30.dp))

        Button(onClick = onSettings) {
            Text("Settings")
        }
    }
}
