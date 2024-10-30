package com.example.fizzbuzz.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.fizzbuzz.ui.presentation.screens.end_screen.EndScreen
import com.example.fizzbuzz.ui.presentation.screens.home_screen.HomeScreen
import com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.LeaderboardScreen
import com.example.fizzbuzz.ui.presentation.screens.play_game_screen.PlayGameScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeScreen(
                navigateToPlayGameScreen = {
                    navController.navigate(Screen.Play) {
                        popUpTo<Screen.Home> { inclusive = false }
                    }
                },
                navigateToLeaderboardScreen = {
                    navController.navigate(Screen.Leaderboard) {
                        popUpTo<Screen.Home> { inclusive = false }
                    }
                }
            )
        }
        composable<Screen.Play> {
            PlayGameScreen(
                navigateToEndScreen = { score, isHighScore ->
                    navController.navigate(Screen.End(score = score, isHighScore = isHighScore)) {
                        popUpTo<Screen.Home> { inclusive = false }
                    }
                }
            )

        }
        composable<Screen.End> {
            val endScreenArguments = it.toRoute<Screen.End>()
            EndScreen(
                endScreenArguments.score,
                endScreenArguments.isHighScore,
                navigateToHomeScreen = {
                    navController.navigate(Screen.Home) {
                        popUpTo<Screen.Home> { inclusive = true }
                    }
                },
                navigateToPlayScreen = {
                    navController.navigate(Screen.Play) {
                        popUpTo<Screen.End> { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.Leaderboard> {
            LeaderboardScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
