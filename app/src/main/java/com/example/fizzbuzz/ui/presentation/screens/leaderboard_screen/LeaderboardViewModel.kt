package com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.domain.repository.NicknameRepository
import com.example.fizzbuzz.domain.repository.ScoreRepository
import com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.intent.LeaderboardIntent
import com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.intent.LeaderboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository,
    private val nicknameRepository: NicknameRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LeaderboardState())

    val state: StateFlow<LeaderboardState>
        get() = _state

    init {
        processIntent(LeaderboardIntent.LoadLeaderboard)
    }

    private fun processIntent(intent: LeaderboardIntent) {
        when (intent) {
            is LeaderboardIntent.LoadLeaderboard -> getLeaderboard()
        }
    }

    private fun getLeaderboard() {
        viewModelScope.launch {
            scoreRepository.getLeaderboard().collect { scores ->
                _state.update {
                    it.copy(
                        leaderboard = scores
                    )
                }
            }
        }
    }
}