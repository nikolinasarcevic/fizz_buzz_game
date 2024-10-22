package com.example.fizzbuzz.ui.presentation.play_game_screen.intent

sealed interface PlayGameEvent {
    data object GameOver: PlayGameEvent
}