package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.funkidslearnsapp.R

@Composable
fun SettingsScreen(onBack: ()->Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.settingscreen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
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
