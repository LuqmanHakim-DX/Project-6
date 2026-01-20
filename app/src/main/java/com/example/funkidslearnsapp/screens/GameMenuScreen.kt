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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview
import com.example.funkidslearnsapp.Routes
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme

@Composable
fun GameMenuScreen(
    onStartGame: (String) -> Unit,
    onSettings: () -> Unit
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "GAME MENU",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF0288D1)
            )

            Spacer(Modifier.height(40.dp))

            Button(
                onClick = { onStartGame(Routes.LETTER_GAME) },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                Text("LETTERS", fontSize = 20.sp)
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { onStartGame(Routes.NumberQuiz) },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                Text("NUMBERS", fontSize = 20.sp)
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { onStartGame(Routes.COLOR_GAME) },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                Text("COLORS", fontSize = 20.sp)
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { onStartGame(Routes.LEADERBOARD) },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                Text("LEADERBOARD", fontSize = 20.sp)
            }

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = onSettings,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("SETTINGS", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameMenuPreview() {
    FunKidsLearnsAppTheme {
        GameMenuScreen(
            onStartGame = {},
            onSettings = {}
        )
    }
}
