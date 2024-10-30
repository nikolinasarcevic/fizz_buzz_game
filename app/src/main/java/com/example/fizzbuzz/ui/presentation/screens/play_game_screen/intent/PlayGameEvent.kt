package com.example.fizzbuzz.ui.presentation.screens.play_game_screen.intent

sealed interface PlayGameEvent {
    data class GameOver(val score: Int, val isHighScore: Boolean) : PlayGameEvent
}