package com.djvmil.entretienmentor.feature.ui.detail.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.djvmil.entretienmentor.feature.navigation.Destinations
import com.djvmil.entretienmentor.feature.navigation.DestinationsArgs
import com.djvmil.entretienmentor.feature.navigation.NavigationHelpers
import com.djvmil.entretienmentor.feature.ui.detail.DetailScreen
import com.djvmil.entretienmentor.feature.ui.home.navigation.navigateToHome

fun NavGraphBuilder.detail(navActions: NavigationHelpers) {
  composable(
      Destinations.DETAIL_ROUTE,
      arguments =
          listOf(navArgument(DestinationsArgs.COMMUNITY_DETAIL_ID_ARG) { type = NavType.IntType }),
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
      }) { entry ->
        DetailScreen(
            communityId = entry.arguments?.getInt(DestinationsArgs.COMMUNITY_DETAIL_ID_ARG)!!,
            onBackClicked = { navActions.navigateToHome() })
      }
}

fun NavigationHelpers.navigateToDetail(movieId: Int) {
  navController.navigate(
      "${Destinations.DETAIL_ROUTE}?" + "${DestinationsArgs.COMMUNITY_DETAIL_ID_ARG}=${movieId}")
}
