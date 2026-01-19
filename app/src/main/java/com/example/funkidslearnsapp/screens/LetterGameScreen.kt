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
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme

@Composable
fun LetterGameScreen(
    onLose: () -> Unit,
    onPause: () -> Unit
) {
    var currentIndex by remember { mutableStateOf(0) }
    val game = LetterGameData.games[currentIndex]

    val correctLetter = game.word[game.missingIndex]
    var options by remember { mutableStateOf(generateLetterOptions(correctLetter)) }

    LaunchedEffect(currentIndex) {
        options = generateLetterOptions(correctLetter)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = onPause) { Text("PAUSE") }

        Spacer(Modifier.height(16.dp))

        Image(
            painter = painterResource(game.imageRes),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Spacer(Modifier.height(24.dp))

        Text(maskedWord(game.word, game.missingIndex), fontSize = 40.sp)

        Spacer(Modifier.height(24.dp))

        options.chunked(2).forEach { row ->
            Row {
                row.forEach { letter ->
                    Button(
                        onClick = {
                            if (letter == correctLetter) {
                                currentIndex =
                                    (currentIndex + 1) % LetterGameData.games.size
                            } else {
                                onLose()
                            }
                        },
                        modifier = Modifier.padding(8.dp).size(80.dp)
                    ) {
                        Text(letter.toString(), fontSize = 24.sp)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LetterGameScreenPreview() {
    FunKidsLearnsAppTheme {
        LetterGameScreen(

            onLose = {},
            onPause = {}
        )
    }
}
