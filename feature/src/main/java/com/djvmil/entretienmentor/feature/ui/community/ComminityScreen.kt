package com.djvmil.entretienmentor.feature.ui.community

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ComminityScreen(openDashboard: () -> Unit) {
  Button(onClick = { openDashboard.invoke() }) { Text(text = "Back") }
}
