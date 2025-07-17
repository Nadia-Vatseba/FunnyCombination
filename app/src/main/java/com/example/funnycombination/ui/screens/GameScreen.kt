package com.example.funnycombination.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.funnycombination.data.model.Emoji
import com.example.funnycombination.viewmodel.GameViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel = viewModel(),
    highScoreViewModel: com.example.funnycombination.viewmodel.HighScoreViewModel
) {
    val sequence = gameViewModel.sequence
    val playerInput = gameViewModel.playerInput
    val isShowingSequence = gameViewModel.isShowingSequence
    val isInputEnabled = gameViewModel.isInputEnabled
    val gameOver = gameViewModel.gameOver
    val currentLevel = gameViewModel.currentLevel

    var shownIndex by remember { mutableStateOf(-1) }

    // Анімація показу послідовності
    if (isShowingSequence) {
        LaunchedEffect(sequence, currentLevel) {
            shownIndex = -1
            for (i in sequence.indices) {
                shownIndex = i
                kotlinx.coroutines.delay(1000)
            }
            shownIndex = -1
            gameViewModel.onSequenceShown()
        }
    }

    // Перехід на GameOverScreen та збереження хайскору
    if (gameOver) {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        highScoreViewModel.saveIfHighScore(currentLevel - 1, date)
        navController.navigate("gameover/${currentLevel - 1}")
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Рівень: $currentLevel", fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
        // Показ послідовності або введення гравця
        Row {
            if (isShowingSequence) {
                sequence.forEachIndexed { i, emoji ->
                    Text(
                        text = if (i == shownIndex) emoji else "⬜",
                        fontSize = 32.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            } else {
                for (i in sequence.indices) {
                    Text(
                        text = playerInput.getOrNull(i) ?: "⬜",
                        fontSize = 32.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
        Spacer(Modifier.height(32.dp))
        // Кнопки емодзі

        if (isInputEnabled) {
            Row {
                Emoji.entries.forEach { emoji ->
                    Box(
                        modifier = Modifier
                            .weight(1F)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFD7D7D7))
                            .clickable(
                                interactionSource = null,
                                indication = null,
                                enabled = isInputEnabled,
                                onClick = { gameViewModel.onEmojiSelected(emoji.value) }
                            )
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(emoji.value, fontSize = 20.sp)
                    }
                }
            }
        }

    }
}