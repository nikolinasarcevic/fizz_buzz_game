package com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.intent

import com.example.fizzbuzz.database.Score

data class LeaderboardState (
    val leaderboard: List<Score> = emptyList(),
    val isRefreshing: Boolean = false,
)