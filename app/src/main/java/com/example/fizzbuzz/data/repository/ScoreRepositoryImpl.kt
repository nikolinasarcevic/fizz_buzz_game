package com.example.fizzbuzz.data.repository

import com.example.fizzbuzz.database.Score
import com.example.fizzbuzz.database.ScoreDao
import com.example.fizzbuzz.domain.repository.ScoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(
    private val dao: ScoreDao
) : ScoreRepository {

    override suspend fun saveScore(score: Score) {
        dao.saveScore(score)
    }

    override suspend fun getLeaderboard(): Flow<List<Score>> {
        return dao.getLeaderboard()
    }

    override suspend fun getScoreByNickname(nickname: String): Score? {
        return dao.getScoreByNickname(nickname)
    }

}