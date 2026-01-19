package com.example.funkidslearnsapp.game

import androidx.compose.ui.graphics.Color

object ColorGameData {

    val games = listOf(
        ColorGameItem(
            question = "WHAT COLOUR IS THIS?",
            displayColor = Color(0xFFFF00FF), // pink/magenta
            options = listOf("RED", "PINK", "GREEN", "BLUE"),
            correctAnswer = "PINK"
        ),

        ColorGameItem(
                question = "What color is this?",
        displayColor = Color.Red,
        options = listOf("Red", "Blue", "Green", "Yellow"),
        correctAnswer = "Red"
        ),

        ColorGameItem(
            question = "What color is this?",
            displayColor = Color.Blue,
            options = listOf("Purple", "Blue", "Orange", "Green"),
            correctAnswer = "Blue"
        ),

        ColorGameItem(
            question = "What color is this?",
            displayColor = Color.Green,
            options = listOf("Red", "Green", "Pink", "Yellow"),
            correctAnswer = "Green"
        ),

        ColorGameItem(
            question = "What color is this?",
            displayColor = Color.Yellow,
            options = listOf("Yellow", "Blue", "Brown", "Purple"),
            correctAnswer = "Yellow"
        ),

        ColorGameItem(
            question = "What color is this?",
            displayColor = Color.Magenta,
            options = listOf("Pink", "Magenta", "Red", "Blue"),
            correctAnswer = "Magenta"
        ),

        ColorGameItem(
            question = "What color is this?",
            displayColor = Color.Cyan,
            options = listOf("Green", "Blue", "Cyan", "Gray"),
            correctAnswer = "Cyan"
        )
    )
}
