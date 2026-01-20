package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import com.example.funkidslearnsapp.data.HighScoreManager
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme


@Composable
fun WinScreen(
    gameKey: Preferences.Key<Int>,
    onBackToMenu: () -> Unit
) {
    val context = LocalContext.current
    val manager = remember { HighScoreManager(context) }
    val highScore by manager.getHighScore(gameKey).collectAsState(initial = 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFB2FF59), Color(0xFF81D4FA))
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "YOU WIN 🎉",
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(Modifier.height(16.dp))

        Text(
            "High Score: $highScore",
            fontSize = 24.sp
        )

        Spacer(Modifier.height(24.dp))

        Button(onClick = onBackToMenu) {
            Text("Back to Menu")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun WinScreenPreview() {
    FunKidsLearnsAppTheme {
        WinScreen(
            gameKey = HighScoreManager.COLOR_HIGH,
            onBackToMenu = {})
    }
}
