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
import com.example.funkidslearnsapp.game.ColorGameData.games
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme

@Composable
fun ColorGameScreen(
    onWin: (score: Int) -> Unit,
    onLose: () -> Unit,
    onPause: () -> Unit
) {
    val games = remember { ColorGameData.games.shuffled() }
    if (games.isEmpty()) return

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var locked by remember { mutableStateOf(false) }

    val game = games[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔢 SCORE + PAUSE
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Score: $score", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Button(onClick = onPause) { Text("PAUSE") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFD600), RoundedCornerShape(20.dp))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(game.question, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(game.displayColor, CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        game.options.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { option ->
                    ColorAnswerButton(
                        text = option,
                        onClick = {
                            if (locked) return@ColorAnswerButton
                            locked = true

                            if (option == game.correctAnswer) {
                                score++

                                if (currentIndex == games.lastIndex) {
                                    onWin(score)
                                } else {
                                    currentIndex++
                                    locked = false
                                }
                            } else {
                                onLose()
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
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
