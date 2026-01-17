package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.funkidslearnsapp.game.LetterGameData
import com.example.funkidslearnsapp.game.generateLetterOptions
import com.example.funkidslearnsapp.game.maskedWord
import com.example.funkidslearnsapp.FunLearnTheme
import kotlinx.coroutines.delay

@Composable
fun LetterGameScreen(
    onBackToMenu: () -> Unit
) {
    
    var currentIndex by remember { mutableStateOf(0) }
    val game = LetterGameData.games[currentIndex]

    val correctLetter = game.word[game.missingIndex]

    var options by remember { mutableStateOf(generateLetterOptions(correctLetter)) }
    var resultText by remember { mutableStateOf("") }

    LaunchedEffect(currentIndex) {
        options = generateLetterOptions(correctLetter)
        resultText = ""
    }

    Button(
        onClick = onBackToMenu,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Text("Back")
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = game.imageRes),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = maskedWord(game.word, game.missingIndex),
            fontSize = 40.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        options.chunked(2).forEach { row ->
            Row {
                row.forEach { letter ->
                    Button(
                        onClick = {
                            if (letter == correctLetter) {
                                resultText = "Correct 🎉"
                            } else {
                                resultText = "Try again ❌"
                            }
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(80.dp)
                    ) {
                        Text(letter.toString(), fontSize = 24.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = resultText, fontSize = 20.sp)
    }

    // 👉 Auto move to next word
    LaunchedEffect(resultText) {
        if (resultText == "Correct 🎉") {
            delay(1000)
            currentIndex = (currentIndex + 1) % LetterGameData.games.size
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LetterGameScreenPreview() {
    FunLearnTheme {
        LetterGameScreen(
            onBackToMenu = TODO()
        )
    }
}
