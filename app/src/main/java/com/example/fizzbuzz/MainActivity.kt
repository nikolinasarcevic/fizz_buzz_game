package com.example.fizzbuzz

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fizzbuzz.ui.presentation.screens.home_screen.HomeViewModel
import com.example.fizzbuzz.ui.presentation.navigation.Navigation
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FizzBuzzTheme {
                FullScreenApp()
            }
        }
    }
}

@Composable
fun FullScreenApp() {

    val view = LocalView.current
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        view.windowInsetsController?.hide(android.view.WindowInsets.Type.systemBars())
    }

    Navigation()
}