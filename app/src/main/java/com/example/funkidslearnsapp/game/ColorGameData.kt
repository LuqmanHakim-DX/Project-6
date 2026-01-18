package com.example.funkidslearnsapp.game

import androidx.compose.ui.graphics.Color

object ColorGameData {

    val games = listOf(
        ColorGameItem(
            question = "WHAT COLOUR IS THIS?",
            displayColor = Color(0xFFFF00FF), // pink/magenta
            options = listOf("RED", "PINK", "GREEN", "BLUE"),
            correctAnswer = "PINK"
        )
    )
}
