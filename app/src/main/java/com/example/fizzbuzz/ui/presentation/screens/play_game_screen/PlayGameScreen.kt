package com.example.fizzbuzz.ui.presentation.screens.play_game_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.screens.play_game_screen.components.PlayButtons
import com.example.fizzbuzz.ui.presentation.screens.play_game_screen.components.PlayGameTimer
import com.example.fizzbuzz.ui.presentation.screens.play_game_screen.intent.PlayGameEvent
import com.example.fizzbuzz.ui.presentation.screens.play_game_screen.intent.PlayGameIntent

@Composable
fun PlayGameScreen(
    navigateToEndScreen: (score: Int, isHighScore: Boolean) -> Unit,
    viewModel: PlayGameViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    var resetTimer by rememberSaveable { mutableStateOf(false) }

    val totalTime: Long = 5000

    var remainingSeconds by rememberSaveable { mutableLongStateOf(totalTime) }


    LaunchedEffect(Unit) {
        viewModel.gameOverChannel.collect {event->
            when(event){
                is PlayGameEvent.GameOver -> {
                    Toast.makeText(context, context.getString(R.string.game_over_toast), Toast.LENGTH_SHORT).show()
                    navigateToEndScreen(event.score, event.isHighScore)
                }
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        Text(
            modifier = Modifier
                .padding(start = 20.dp, top = 50.dp)
                .weight(0.5f)
                .align(Alignment.Start),
            text = "Score: ${state.score}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
        )

        PlayGameTimer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            totalTime = totalTime,
            onTimerFinished = {
                viewModel.processIntent(PlayGameIntent.OnTimerFinished)
            },
            inactiveBarColor = MaterialTheme.colorScheme.secondary,
            activeBarColor = MaterialTheme.colorScheme.tertiary,
            onTimerReset = { resetTimer = false },
            resetTimer = resetTimer,
            displayText = "${state.currentNumber}",
            onTimeTick = { remainingSeconds = it }
        )

        PlayButtons(
            onClickFizz = {
                resetTimer = true
                viewModel.processIntent(PlayGameIntent.FizzClicked(remainingSeconds))
            },
            onClickBuzz = {
                resetTimer = true
                viewModel.processIntent(PlayGameIntent.BuzzClicked(remainingSeconds))
            },
            onClickFizzBuzz = {
                resetTimer = true
                viewModel.processIntent(PlayGameIntent.FizzBuzzClicked(remainingSeconds))
            },
            onClickNext = {
                resetTimer = true
                viewModel.processIntent(PlayGameIntent.NextClicked(remainingSeconds))
            }
        )
    }
}
