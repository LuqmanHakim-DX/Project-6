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
    onLose: () -> Unit,
    onPause: () -> Unit
) {
    var a by remember { mutableStateOf(0) }
    var b by remember { mutableStateOf(0) }

    fun generateQuestion() {
        a = Random.nextInt(1, 10)
        b = Random.nextInt(1, 10)
    }

    LaunchedEffect(Unit) {
        generateQuestion()
    }

    val answer = a + b
    val options = remember(answer) {
        listOf(
            answer,
            answer + Random.nextInt(1, 4),
            answer - Random.nextInt(1, 4),
            Random.nextInt(1, 20)
        ).shuffled()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = onPause) { Text("PAUSE") }

        Spacer(Modifier.height(16.dp))

        Text("$a + $b = ?", fontSize = 40.sp)

        Spacer(Modifier.height(24.dp))

        options.forEach { option ->
            Button(
                onClick = {
                    if (option == answer) {
                        generateQuestion()
                    } else {
                        onLose()
                    }
                },
                modifier = Modifier.padding(6.dp)
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

            onLose = {},
            onPause = {}
        )
    }
}
