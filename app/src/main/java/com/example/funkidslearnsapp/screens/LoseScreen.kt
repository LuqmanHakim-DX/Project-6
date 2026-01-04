package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoseScreen(
    onRetry: () -> Unit
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("TRY AGAIN!",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF44336)
            )

            Spacer(Modifier.height(30.dp))

            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }
}
