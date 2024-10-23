package com.example.fizzbuzz.ui.presentation.play_game_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fizzbuzz.ui.presentation.play_game_screen.components.PlayButtons
import com.example.fizzbuzz.ui.presentation.play_game_screen.components.PlayGameTimer
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameIntent
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun PlayGameScreen(
    navigateToEndScreen: (score: Int) -> Unit,
    viewModel: PlayGameViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    var resetTimer by rememberSaveable { mutableStateOf(false) }

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
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 200.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            PlayGameTimer(
                modifier = Modifier,
                totalTime = 5,
                onTimerFinished = {
                        viewModel.triggerGameOverEvent()
                },
                inactiveBarColor = MaterialTheme.colorScheme.secondary,
                activeBarColor = MaterialTheme.colorScheme.tertiary,
                onTimerReset = { resetTimer = false },
                resetTimer = resetTimer,
                displayText = "${state.currentNumber}",
                onTimeTick = {}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 200.dp)
        ) {
            PlayButtons(
                onClickFizz = {
                    resetTimer = true
                    viewModel.processIntent(PlayGameIntent.FizzClicked)
                },
                onClickBuzz = {
                    resetTimer = true
                    viewModel.processIntent(PlayGameIntent.BuzzClicked)
                },
                onClickFizzBuzz = {
                    resetTimer = true
                    viewModel.processIntent(PlayGameIntent.FizzBuzzClicked)
                },
                onClickNext = {
                    resetTimer = true
                    viewModel.processIntent(PlayGameIntent.NextClicked)
                }
            )

        }

    }
}

@Preview
@Composable
private fun PreviewPlayGame() {
    FizzBuzzTheme {
        PlayGameScreen(navigateToEndScreen = {})
    }

}