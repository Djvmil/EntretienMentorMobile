package com.djvmil.entretienmentor.ui.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.djvmil.entretienmentor.presentation.presentation.detail.DetailScreen
import com.djvmil.entretienmentor.ui.navigation.Destinations
import com.djvmil.entretienmentor.ui.navigation.DestinationsArgs
import com.djvmil.entretienmentor.ui.navigation.NavigationActions

fun NavGraphBuilder.detail(
    modifier: Modifier,
    navActions: NavigationActions
) {
    composable(
        Destinations.DETAIL_ROUTE,
        arguments = listOf(
            navArgument(DestinationsArgs.MOVIE_DETAIL_ID_ARG) { type = NavType.IntType }
        )
    ) { entry ->
        DetailScreen(
            modifier = modifier,
            movieId = entry.arguments?.getInt(DestinationsArgs.MOVIE_DETAIL_ID_ARG)!!,
            onBackClicked = {
                navActions.navigateToHome()
            }
        )
    }
}