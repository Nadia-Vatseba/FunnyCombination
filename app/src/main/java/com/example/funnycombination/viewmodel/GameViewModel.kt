package com.example.funnycombination.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.funnycombination.data.model.Emoji

class GameViewModel : ViewModel() {
    private val emojiList = Emoji.entries

    private val _sequence = mutableStateListOf<String>()
    val sequence: List<String> get() = _sequence

    private val _playerInput = mutableStateListOf<String>()
    val playerInput: List<String> get() = _playerInput

    var isShowingSequence by mutableStateOf(true)
    var currentLevel by mutableStateOf(1)
    var gameOver by mutableStateOf(false)
    var isInputEnabled by mutableStateOf(false)

    init {
        startNewGame()
    }

    fun startNewGame() {
        currentLevel = 1
        gameOver = false
        nextLevel()
    }

    fun nextLevel() {
        _sequence.clear()
        _playerInput.clear()
        repeat(currentLevel) {
            _sequence.add(emojiList.random().value)
        }
        isShowingSequence = true
        isInputEnabled = false
    }

    fun onSequenceShown() {
        isShowingSequence = false
        isInputEnabled = true
    }

    fun onEmojiSelected(emoji: String) {
        if (!isInputEnabled) return
        _playerInput.add(emoji)
        val idx = _playerInput.lastIndex
        if (_playerInput[idx] != _sequence[idx]) {
            gameOver = true
            isInputEnabled = false
        } else if (_playerInput.size == _sequence.size) {
            currentLevel++
            nextLevel()
        }
    }
}