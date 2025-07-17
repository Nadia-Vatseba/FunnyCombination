package com.example.funnycombination.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun GameOverScreen(navController: NavController, score: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Гра закінчена!", fontSize = 32.sp)
        Spacer(Modifier.height(16.dp))
        Text("Ваш результат: $score", fontSize = 24.sp)
        Spacer(Modifier.height(32.dp))
        Button(onClick = { navController.navigate("main_menu") }) {
            Text("Головне меню")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("game") }) {
            Text("Грати знову")
        }
    }
}