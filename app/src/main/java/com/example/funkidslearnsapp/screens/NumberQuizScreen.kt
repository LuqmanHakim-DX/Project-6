package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme
import kotlin.random.Random

@Composable
fun NumberQuizScreen(
    onWin: () -> Unit,
    onLose: () -> Unit,
    onPause: () -> Unit
) {
    var score by remember { mutableStateOf(0) }
    var questionCount by remember { mutableStateOf(0) }

    var a by remember { mutableStateOf(Random.nextInt(1, 10)) }
    var b by remember { mutableStateOf(Random.nextInt(1, 10)) }

    val answer = a + b
    val options = remember(answer) {
        listOf(
            answer,
            answer + Random.nextInt(1, 4),
            answer - Random.nextInt(1, 4),
            Random.nextInt(1, 20)
        ).shuffled()
    }

    fun nextQuestion() {
        a = Random.nextInt(1, 10)
        b = Random.nextInt(1, 10)
        questionCount++
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // 🔢 SCORE + PAUSE
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Score: $score", fontSize = 20.sp)
            Button(onClick = onPause) { Text("PAUSE") }
        }

        Spacer(Modifier.height(24.dp))

        Text("$a + $b = ?", fontSize = 40.sp)

        Spacer(Modifier.height(32.dp))

        options.forEach { option ->
            Button(
                onClick = {
                    if (option == answer) {
                        score++
                        if (questionCount == 9) onWin() // 10 questions
                        else nextQuestion()
                    } else {
                        onLose()
                    }
                },
                modifier = Modifier.fillMaxWidth(0.7f).padding(6.dp)
            ) {
                Text(option.toString(), fontSize = 20.sp)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun NumberQuizPreview() {
    FunKidsLearnsAppTheme {
        NumberQuizScreen(
            onWin = {},
            onLose = {},
            onPause = {}
        )
    }
}
