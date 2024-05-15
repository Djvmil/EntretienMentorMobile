package com.djvmil.entretienmentor.feature.ui.profile

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(openDashboard: () -> Unit) {

  Button(onClick = { openDashboard.invoke() }) { Text(text = "Back") }
}
