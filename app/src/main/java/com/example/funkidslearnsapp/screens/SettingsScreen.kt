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
fun SettingsScreen(onBack: ()->Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFF9C4), Color(0xFFB3E5FC))
                )
            )
    ) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Text("Settings")
            Spacer(Modifier.height(12.dp))
            // Add toggles, volume controls etc.
            Box(modifier = Modifier.weight(1f))
            Button(onClick = onBack, modifier = Modifier.align(Alignment.End)) {
                Text("Back")
            }
        }
    }
}
