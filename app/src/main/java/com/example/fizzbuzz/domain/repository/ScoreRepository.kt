package com.example.fizzbuzz.domain.repository

import com.example.fizzbuzz.database.Score
import kotlinx.coroutines.flow.Flow

interface ScoreRepository {
    suspend fun saveScore(score: Score): Boolean
    suspend fun getLeaderboard(): Flow<List<Score>>
    suspend fun getScoreByNickname(nickname: String): Score?
    suspend fun getHighestScoreForNickname(nickname: String): Int?
}