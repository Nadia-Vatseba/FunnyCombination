 package com.example.funnycombination.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HighScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(highScore: HighScoreEntity)

    @Query("SELECT * FROM high_scores ORDER BY score DESC")
    suspend fun getAllHighScores(): List<HighScoreEntity>

    @Query("SELECT MAX(score) FROM high_scores")
    suspend fun getMaxScore(): Int?
}