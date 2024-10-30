package com.example.fizzbuzz.ui.presentation.screens.play_game_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.database.Score
import com.example.fizzbuzz.domain.repository.NicknameRepository
import com.example.fizzbuzz.domain.repository.ScoreRepository
import com.example.fizzbuzz.ui.presentation.screens.play_game_screen.intent.PlayGameEvent
import com.example.fizzbuzz.ui.presentation.screens.play_game_screen.intent.PlayGameIntent
import com.example.fizzbuzz.ui.presentation.screens.play_game_screen.intent.PlayGameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class PlayGameViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository,
    private val nicknameRepository: NicknameRepository
) : ViewModel() {

    private val _gameOverChannel = Channel<PlayGameEvent>()
    val gameOverChannel = _gameOverChannel.receiveAsFlow()

    private var _state = MutableStateFlow(PlayGameState())

    val state: StateFlow<PlayGameState>
        get() = _state


    fun processIntent(intent: PlayGameIntent) {
        when (intent) {
            is PlayGameIntent.FizzClicked -> onFizzClick(intent.remainingSeconds)
            is PlayGameIntent.BuzzClicked -> onBuzzClick(intent.remainingSeconds)
            is PlayGameIntent.FizzBuzzClicked -> onFizzBuzzClick(intent.remainingSeconds)
            is PlayGameIntent.NextClicked -> onNextClick(intent.remainingSeconds)
            PlayGameIntent.OnTimerFinished -> onTimerFinished()
        }
    }

    private fun endGame() {
        viewModelScope.launch {
            val latestNickname = nicknameRepository.getNickname() ?: "Player"

                val newScore = Score(
                    nickname = latestNickname,
                    scoreValue = _state.value.score,
                    playedAt = LocalDateTime.now()
                )
                val isHighScore = scoreRepository.saveScore(newScore)


            _gameOverChannel.send(PlayGameEvent.GameOver(_state.value.score, isHighScore))
        }
    }

    private fun onTimerFinished() {
        endGame()
    }

    private fun scoreUpdateButtons(
        isCorrectAnswer: Boolean,
        remainingSeconds: Long
    ) {
        if (isCorrectAnswer) {
            val points = ceil(remainingSeconds / 1000.0).toInt()
            _state.update {
                it.copy(
                    score = it.score + points,
                    currentNumber = it.currentNumber + 1
                )
            }
        } else {
            endGame()
        }
    }

    private fun onFizzClick(remainingSeconds: Long) {
        scoreUpdateButtons(
            isCorrectAnswer = _state.value.currentNumber % 3 == 0 && _state.value.currentNumber % 5 != 0,
            remainingSeconds = remainingSeconds
        )
    }

    private fun onBuzzClick(remainingSeconds: Long) {
        scoreUpdateButtons(
            isCorrectAnswer = _state.value.currentNumber % 5 == 0 && _state.value.currentNumber % 3 != 0,
            remainingSeconds = remainingSeconds
        )
    }

    private fun onFizzBuzzClick(remainingSeconds: Long) {
        scoreUpdateButtons(
            isCorrectAnswer = _state.value.currentNumber % 15 == 0,
            remainingSeconds = remainingSeconds
        )
    }

    private fun onNextClick(remainingSeconds: Long) {
        scoreUpdateButtons(
            isCorrectAnswer = _state.value.currentNumber % 3 != 0 && _state.value.currentNumber % 5 != 0,
            remainingSeconds = remainingSeconds
        )
    }
}
