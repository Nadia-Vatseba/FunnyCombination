package com.example.funnycombination.ui.screens

<<<<<<< HEAD
import androidx.activity.ComponentActivity
=======
>>>>>>> d86bbb42e88a770b7090b96d72b65cddb6b658b3
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.platform.LocalContext
=======
>>>>>>> d86bbb42e88a770b7090b96d72b65cddb6b658b3
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainMenuScreen(navController: NavController) {
<<<<<<< HEAD
    val context = LocalContext.current
    val activity = context as? ComponentActivity

=======
>>>>>>> d86bbb42e88a770b7090b96d72b65cddb6b658b3
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
<<<<<<< HEAD
        Button(onClick = { activity?.finish() }, modifier = Modifier.fillMaxWidth()) {
=======
        Button(onClick = { /* Додай логіку виходу, якщо потрібно */ }, modifier = Modifier.fillMaxWidth()) {
>>>>>>> d86bbb42e88a770b7090b96d72b65cddb6b658b3
            Text("Вихід")
        }
    }
}