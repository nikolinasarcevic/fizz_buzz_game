package com.example.fizzbuzz.data.repository

import com.example.fizzbuzz.database.Score
import com.example.fizzbuzz.database.ScoreDao
import com.example.fizzbuzz.domain.repository.ScoreRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(
    private val dao: ScoreDao,
) : ScoreRepository {

    /**
     * Ova funkcija provjerava postoji li već spremljeni rezultat za dani nickname
     * Ako postoji uspoređuje postojeći rezultat s trenutno osvojenim:
     *      Ako je osvojeni rezultat manji od upisanog, ostavlja upisani,
     *      Ako je osvojeni rezultat veći od upisanog, na njegovo mjesto upisuje novo osvojeni rezultat, što znači da je to High score
     * Ako rezultat ne postoji za dani nickname, sprema sve u bazu.
     *
     * Također, funkcija vraća true ukoliko je spremljeni rezultat High score, inaće vraća false
     **/
    override suspend fun saveScore(score: Score): Boolean {
        val existingScore = dao.getScoreByNickname(score.nickname)

        if (existingScore != null) {
            if (score.scoreValue > existingScore.scoreValue) {
                val updatedScore =
                    existingScore.copy(scoreValue = score.scoreValue, playedAt = LocalDateTime.now())
                dao.saveScore(updatedScore)
                return true
            }
            return false

        } else {
            dao.saveScore(score)
            return true
        }
    }

    override suspend fun getLeaderboard(): Flow<List<Score>> {
        return dao.getLeaderboard()
    }

    override suspend fun getScoreByNickname(nickname: String): Score? {
        return dao.getScoreByNickname(nickname)
    }

    override suspend fun getHighestScoreForNickname(nickname: String): Int? {
        return dao.getHighestScoreForNickname(nickname)
    }

}