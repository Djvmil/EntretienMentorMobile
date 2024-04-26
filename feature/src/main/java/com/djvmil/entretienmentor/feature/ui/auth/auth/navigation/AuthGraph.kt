package com.djvmil.entretienmentor.feature.ui.auth.auth.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.djvmil.entretienmentor.feature.ui.auth.auth.AuthScreen
import com.djvmil.feature.navigation.Destinations
import com.djvmil.feature.navigation.NavigationHelpers

fun NavGraphBuilder.auth(
    navActions: NavigationHelpers
) {
    composable(Destinations.AUTH_ROUTE, enterTransition = {
        return@composable fadeIn(tween(1000))
    }, exitTransition = {
        return@composable slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
        )
    }, popEnterTransition = {
        return@composable slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
        )
    }) {
        AuthScreen(
            navActions
        )
    }
}


fun NavigationHelpers.navigateToAuth() {
    navController.navigate(Destinations.AUTH_ROUTE)
}