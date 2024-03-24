package com.djvmil.entretienmentor.ui.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.djvmil.entretienmentor.presentation.presentation.home.HomeScreen
import com.djvmil.entretienmentor.ui.navigation.Destinations
import com.djvmil.entretienmentor.ui.navigation.NavigationActions

fun NavGraphBuilder.home(
    modifier: Modifier,
    navActions: NavigationActions
) {
    composable(Destinations.HOME_ROUTE) {
        HomeScreen(
            modifier = modifier,
            onShowDetail = { movieId ->
                navActions.navigateToDetail(movieId)
            }
        )
    }
}