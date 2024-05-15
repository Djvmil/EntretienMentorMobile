package com.djvmil.entretienmentor.feature.ui.blog

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BlogScreen(openDashboard: () -> Unit) {
  Button(onClick = { openDashboard.invoke() }) { Text(text = "Back") }
}
