package com.example.funnycombination.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.funnycombination.data.AppDatabase
import com.example.funnycombination.data.HighScoreRepository
import com.example.funnycombination.data.model.Emoji
import com.example.funnycombination.ui.screens.*
import com.example.funnycombination.viewmodel.GameViewModel
import com.example.funnycombination.viewmodel.HighScoreViewModel
import com.example.funnycombination.viewmodel.HighScoreViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val dao = AppDatabase.getDatabase(context).highScoreDao()
    val repository = HighScoreRepository(dao)
    val highScoreViewModel: HighScoreViewModel = viewModel(factory = HighScoreViewModelFactory(repository))

    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("main_menu") { MainMenuScreen(navController) }
        composable("game") {
            val gameViewModel: GameViewModel = viewModel()
            GameScreen(navController, gameViewModel, highScoreViewModel)
        }
        composable("gameover/{score}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            GameOverScreen(navController, score)
        }
        composable("high_score") { HighScoreScreen(navController, highScoreViewModel) }
        composable("privacy_policy") { PrivacyPolicyScreen(navController) }
    }
}
@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel = viewModel()
) {
    val sequence = gameViewModel.sequence
    val playerInput = gameViewModel.playerInput
    val isShowingSequence = gameViewModel.isShowingSequence
    val isInputEnabled = gameViewModel.isInputEnabled
    val gameOver = gameViewModel.gameOver
    val currentLevel = gameViewModel.currentLevel

    var shownIndex by remember { mutableStateOf(-1) }

    LaunchedEffect(Unit) {
        gameViewModel.startNewGame()
    }

    if (isShowingSequence) {
        LaunchedEffect(sequence, currentLevel) {
            shownIndex = -1
            for (i in sequence.indices) {
                shownIndex = i
                delay(1000)
            }
            shownIndex = -1
            gameViewModel.onSequenceShown()
        }
    }

    if (gameOver) {
        navController.navigate("gameover/${currentLevel - 1}")
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Рівень: $currentLevel", fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
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
                playerInput.forEach { emoji ->
                    Text(text = emoji, fontSize = 32.sp, modifier = Modifier.padding(4.dp))
                }
            }
        }
        Spacer(Modifier.height(32.dp))
        Row {
            Emoji.entries.forEach { emoji ->
                Button(
                    onClick = { gameViewModel.onEmojiSelected(emoji.value) },
                    enabled = isInputEnabled,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(emoji.value, fontSize = 32.sp)
                }
            }
        }
    }
}