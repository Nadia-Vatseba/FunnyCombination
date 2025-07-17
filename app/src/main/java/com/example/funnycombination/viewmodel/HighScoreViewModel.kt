package com.example.funnycombination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.funnycombination.data.HighScoreEntity
import com.example.funnycombination.data.HighScoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HighScoreViewModel(private val repository: HighScoreRepository) : ViewModel() {
    private val _highScores = MutableStateFlow<List<HighScoreEntity>>(emptyList())
    val highScores: StateFlow<List<HighScoreEntity>> = _highScores

    fun loadHighScores() {
        viewModelScope.launch {
            _highScores.value = repository.getAllHighScores()
        }
    }

    fun saveIfHighScore(score: Int, date: String) {
        viewModelScope.launch {
            val maxScore = repository.getMaxScore()
            if (score > maxScore) {
                repository.insert(HighScoreEntity(date = date, score = score))
            }
        }
    }
}

class HighScoreViewModelFactory(private val repository: HighScoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighScoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HighScoreViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}