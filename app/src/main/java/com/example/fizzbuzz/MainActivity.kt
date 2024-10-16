package com.example.fizzbuzz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fizzbuzz.ui.presentation.navigation.Navigation
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FizzBuzzTheme {
                Navigation()
            }
        }
    }
}


