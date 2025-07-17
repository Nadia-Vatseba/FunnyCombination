package com.example.funnycombination.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedEmoji(
    emoji: String,
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        modifier = modifier
    ) {
        Text(emoji, fontSize = 32.sp)
    }
}