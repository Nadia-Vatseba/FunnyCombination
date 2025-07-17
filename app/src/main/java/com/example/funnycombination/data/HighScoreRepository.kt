package com.example.funnycombination.data

class HighScoreRepository(private val dao: HighScoreDao) {
    suspend fun insert(highScore: HighScoreEntity) = dao.insert(highScore)
    suspend fun getAllHighScores() = dao.getAllHighScores()
    suspend fun getMaxScore() = dao.getMaxScore() ?: 0
}