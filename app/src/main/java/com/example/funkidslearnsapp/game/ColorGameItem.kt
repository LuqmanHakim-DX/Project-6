package com.example.funkidslearnsapp.game

import androidx.compose.ui.graphics.Color

data class ColorGameItem(
    val question: String,
    val displayColor: Color,
    val options: List<String>,
    val correctAnswer: String
)
