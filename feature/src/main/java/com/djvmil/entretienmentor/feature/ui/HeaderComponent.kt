package com.djvmil.entretienmentor.feature.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderComponent(modifier: Modifier, onBack: () -> Unit) {
  Row(
      modifier = modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Start,
      verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.padding(vertical = 10.dp).clickable { onBack.invoke() },
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Login Button")
      }
}
