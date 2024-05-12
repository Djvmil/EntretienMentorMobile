package com.djvmil.entretienmentor.feature.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeGuestScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onShowDetail: (movieId: Int) -> Unit
) {
  HomeContent(viewModel = viewModel, modifier = Modifier, onShowDetail = onShowDetail)
}
