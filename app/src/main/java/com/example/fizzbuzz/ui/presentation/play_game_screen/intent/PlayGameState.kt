package com.example.fizzbuzz.ui.presentation.play_game_screen.intent

data class PlayGameState (
    val currentNumber: Int = 1,
    val score: Int = 0,
    var isHighScore: Boolean = false
)