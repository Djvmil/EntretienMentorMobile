package com.djvmil.entretienmentor.feature.ui.chat

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ChatScreen(openDashboard: () -> Unit) {
  Button(onClick = { openDashboard.invoke() }) { Text(text = "Back") }
}
