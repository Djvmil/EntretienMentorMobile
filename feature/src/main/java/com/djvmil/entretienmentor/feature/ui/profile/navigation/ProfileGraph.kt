package com.djvmil.entretienmentor.feature.ui.profile.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.djvmil.entretienmentor.feature.navigation.Destinations
import com.djvmil.entretienmentor.feature.navigation.NavigationHelpers
import com.djvmil.entretienmentor.feature.ui.profile.ProfileScreen

fun NavGraphBuilder.profile(navActions: NavigationHelpers) {
  composable(
      Destinations.PROFILE_ROUTE,
      enterTransition = {
        return@composable fadeIn(tween(1000))
      },
      exitTransition = {
        return@composable slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
      },
      popEnterTransition = {
        return@composable slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.End, tween(700))
      }) {
        ProfileScreen(openDashboard = { navActions.navigateUp() })
      }
}

fun NavigationHelpers.navigateToProfile() {
  navController.navigate(Destinations.PROFILE_ROUTE)
}
