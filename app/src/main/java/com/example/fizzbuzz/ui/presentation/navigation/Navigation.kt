package com.example.fizzbuzz.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fizzbuzz.ui.presentation.end_screen.EndScreen
import com.example.fizzbuzz.ui.presentation.home_screen.HomeScreen
import com.example.fizzbuzz.ui.presentation.home_screen.HomeViewModel
import com.example.fizzbuzz.ui.presentation.leaderboard_screen.LeaderboardScreen
import com.example.fizzbuzz.ui.presentation.play_game_screen.PlayGameScreen

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
                    navController.navigate(Screen.Play)
                },
                navigateToLeaderboardScreen = {
                    navController.navigate(Screen.Leaderboard)
                }
            )
        }
        composable<Screen.Play> {
            PlayGameScreen(
                navigateToLeaderboardScreen = {
                    navController.navigate(Screen.Leaderboard)
                }
            )
        }
        composable<Screen.End> {
            EndScreen(
                navController = navController
            )
        }
        composable<Screen.Leaderboard> {
            LeaderboardScreen(
                navigateToLeaderboardScreen = {
                    navController.navigate(Screen.Leaderboard)
                }
            )
        }
    }
}
