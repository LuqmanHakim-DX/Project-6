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
    onLose: () -> Unit
) {
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Color(0xFFFFF9C4), Color(0xFFB3E5FC)))
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("What is:", fontSize = 24.sp)
            Spacer(Modifier.height(8.dp))

            Text(
                "$a + $b = ?",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(32.dp))

            options.forEach { option ->
                Button(
                    onClick = {
                        if (option == answer) onWin()
                        else onLose()
                    },
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(60.dp)
                        .padding(6.dp)
                ) {
                    Text(option.toString(), fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumberQuizPreview() {
    FunKidsLearnsAppTheme {
        NumberQuizScreen(onWin = {}, onLose = {})
    }
}
