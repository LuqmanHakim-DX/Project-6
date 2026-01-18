package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funkidslearnsapp.game.ColorGameData
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme

@Composable
fun ColorGameScreen(
    onWin: () -> Unit,
    onLose: () -> Unit,
    onPause: () -> Unit
) {
    val game = ColorGameData.games[0]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        // 🟨 Question card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFFD600),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = game.question,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(
                            color = game.displayColor,
                            shape = CircleShape
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 🎯 Answer buttons
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ColorAnswerButton(game.options[0]) {
                    if (game.options[0] == game.correctAnswer) onWin() else onLose()
                }
                ColorAnswerButton(game.options[1]) {
                    if (game.options[1] == game.correctAnswer) onWin() else onLose()
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ColorAnswerButton(game.options[2]) {
                    if (game.options[2] == game.correctAnswer) onWin() else onLose()
                }
                ColorAnswerButton(game.options[3]) {
                    if (game.options[3] == game.correctAnswer) onWin() else onLose()
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = onPause) {
                Text("PAUSE")
            }
        }
    }
}

@Composable
fun ColorAnswerButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 70.dp)
            .clickable { onClick() }
            .background(
                color = Color(0xFFB2FF59),
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ColorGamePreview() {
    FunKidsLearnsAppTheme {
        ColorGameScreen(
            onWin = {},
            onLose = {},
            onPause = {}
        )
    }
}
