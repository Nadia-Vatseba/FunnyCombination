package com.example.funnycombination.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun EmojiButton(
    emoji: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(onClick = onClick, enabled = enabled, modifier = modifier) {
        Text(emoji, fontSize = 32.sp)
    }
}