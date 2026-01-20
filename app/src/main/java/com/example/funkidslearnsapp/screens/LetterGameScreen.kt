package com.example.funkidslearnsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.funkidslearnsapp.data.HighScoreManager
import com.example.funkidslearnsapp.game.LetterGameData
import com.example.funkidslearnsapp.game.generateLetterOptions
import com.example.funkidslearnsapp.game.maskedWord
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme
import kotlinx.coroutines.launch

@Composable
fun LetterGameScreen(
    onWin: (score: Int) -> Unit,
    onLose: () -> Unit,
    onPause: () -> Unit
) {
    val context = LocalContext.current
    val highScoreManager = remember { HighScoreManager(context) }
    val scope = rememberCoroutineScope() // ✅ CORRECT

    val games = remember { LetterGameData.games.shuffled() }
    if (games.isEmpty()) return

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var locked by remember { mutableStateOf(false) }

    val game = games[currentIndex]
    val correctLetter = game.word[game.missingIndex]
    val options = remember(game) { generateLetterOptions(correctLetter) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
                            if (locked) return@Button
                            locked = true

                            if (letter == correctLetter) {
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
    }
}




@Preview(showBackground = true)
@Composable
fun LetterGameScreenPreview() {
    FunKidsLearnsAppTheme {
        LetterGameScreen(
            onWin = {},
            onLose = {},
            onPause = {}
        )
    }
}
