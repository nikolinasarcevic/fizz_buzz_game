package com.example.fizzbuzz.ui.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
        startDestination = Screen.Home,
    ) {
        composable<Screen.Home>(
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) },
        ) {
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
        composable<Screen.Play>(
            enterTransition = { slideInVertically(initialOffsetY = { 3000 }, animationSpec = tween(400)) },
            exitTransition = { slideOutVertically(targetOffsetY = { -3000 }, animationSpec = tween(1000)) },
            popEnterTransition = { slideInVertically(initialOffsetY = { 3000 }, animationSpec = tween(300)) },
            popExitTransition = { slideOutVertically(targetOffsetY = { -3000 }, animationSpec = tween(300)) },
        ) {
            PlayGameScreen(
                navigateToEndScreen = { score, isHighScore ->
                    navController.navigate(Screen.End(score = score, isHighScore = isHighScore)) {
                        popUpTo<Screen.Home> { inclusive = false }
                    }
                }
            )

        }
        composable<Screen.End>(
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) }
        ) {
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

        composable<Screen.Leaderboard>(
            enterTransition = { slideInVertically(initialOffsetY = { -3000 }, animationSpec = tween(1000)) },
            exitTransition = { slideOutVertically(targetOffsetY = { -it/2 }, animationSpec = tween(1000)) },
            popEnterTransition = { slideInVertically(initialOffsetY = { -3000 }, animationSpec = tween(1000)) },
            popExitTransition = { slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(1000)) },
        ) {
            LeaderboardScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
