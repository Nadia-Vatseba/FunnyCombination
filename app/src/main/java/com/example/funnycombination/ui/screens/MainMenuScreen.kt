package com.example.funnycombination.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainMenuScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("game") }, modifier = Modifier.fillMaxWidth()) {
            Text("Грати")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("high_score") }, modifier = Modifier.fillMaxWidth()) {
            Text("High Score")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("privacy_policy") }, modifier = Modifier.fillMaxWidth()) {
            Text("Privacy Policy")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { activity?.finish() }, modifier = Modifier.fillMaxWidth()) {
            Text("Вихід")
        }
    }
}