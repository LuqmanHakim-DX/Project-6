package com.example.funkidslearnsapp.game

fun maskedWord(word: String, missingIndex: Int): String {
    return word.mapIndexed { index, letter ->
        if (index == missingIndex) "_" else letter
    }.joinToString(" ")
}

fun generateLetterOptions(correctLetter: Char): List<Char> {
    val alphabet = ('A'..'Z').filter { it != correctLetter }
    val wrongLetters = alphabet.shuffled().take(3)

    return (wrongLetters + correctLetter).shuffled()
}
