package com.example.fizzbuzz.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fizzbuzz.ui.presentation.end_screen.EndScreen
import com.example.fizzbuzz.ui.presentation.home_screen.HomeScreen
import com.example.fizzbuzz.ui.presentation.leaderboard_screen.LeaderboardScreen
import com.example.fizzbuzz.ui.presentation.play_game_screen.PlayGameScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) { HomeScreen(navigateToPlayGameScreen = { navController.navigate(Screen.Play.route) }) }
        composable(route = Screen.Play.route) { PlayGameScreen(navController = navController) }
        composable(route = Screen.End.route) { EndScreen(navController = navController) }
        composable(route = Screen.Leaderboard.route) { LeaderboardScreen(navController = navController) }
    }
}
