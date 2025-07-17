package com.example.funnycombination.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.funnycombination.viewmodel.HighScoreViewModel

@Composable
fun HighScoreScreen(navController: NavController, viewModel: HighScoreViewModel) {
    val highScores by viewModel.highScores.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHighScores()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("High Scores", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        if (highScores.isEmpty()) {
            Text("Немає результатів")
        } else {
            LazyColumn {
                items(highScores) { score ->
                    Row(
                        Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(score.date)
                        Text(score.score.toString())
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Назад")
        }
    }
}