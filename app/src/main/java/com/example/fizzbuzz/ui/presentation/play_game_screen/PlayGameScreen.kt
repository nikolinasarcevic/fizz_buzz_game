package com.example.fizzbuzz.ui.presentation.play_game_screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fizzbuzz.ui.presentation.play_game_screen.components.PlayButtons
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameIntent
import timber.log.Timber

@Composable
fun PlayGameScreen(
    navigateToEndScreen: (score: Int) -> Unit,
    viewModel: PlayGameViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.gameOverChannel.collect {
            Toast.makeText(context, "Game Over!", Toast.LENGTH_SHORT).show()
            navigateToEndScreen(state.score)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier
                .weight(1f)
                .padding(top = 200.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(180.dp)
                    .border(
                        border = BorderStroke(4.dp, MaterialTheme.colorScheme.secondary),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${state.currentNumber}",
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 80.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 200.dp)
        ) {
            PlayButtons(
                onClickFizz = {
                    viewModel.processIntent(PlayGameIntent.FizzClicked)
                },
                onClickBuzz = { viewModel.processIntent(PlayGameIntent.BuzzClicked) },
                onClickFizzBuzz = { viewModel.processIntent(PlayGameIntent.FizzBuzzClicked) },
                onClickNext = { viewModel.processIntent(PlayGameIntent.NextClicked) }
            )
        }

    }
}