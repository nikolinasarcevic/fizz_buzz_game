package com.example.fizzbuzz.ui.presentation.leaderboard_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.database.Score
import com.example.fizzbuzz.database.ScoreDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val scoreDao: ScoreDao
) : ViewModel() {

    private val _leaderboard = MutableStateFlow<List<Score>>(emptyList())
    val leaderboard: StateFlow<List<Score>> = _leaderboard

    init {
        getLeaderboard()
    }

    private fun getLeaderboard() {
        viewModelScope.launch {
            scoreDao.getLeaderboard().collect { scores ->
                _leaderboard.value = scores
            }
        }
    }
}