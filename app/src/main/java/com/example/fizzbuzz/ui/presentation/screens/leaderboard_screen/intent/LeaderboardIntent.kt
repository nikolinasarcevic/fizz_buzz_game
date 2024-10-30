package com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.intent

sealed interface LeaderboardIntent {
    data object LoadLeaderboard : LeaderboardIntent
}