package com.example.funlearn.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartUpScreen(onContinue: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("HELLO FRIEND!", modifier = Modifier.padding(8.dp))
            Spacer(Modifier.height(16.dp))
            Text("Welcome to FunLearn")
            Spacer(Modifier.height(24.dp))
            Button(onClick = onContinue) {
                Text("Continue")
            }
        }
    }
}
