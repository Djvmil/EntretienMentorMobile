package com.djvmil.entretienmentor.feature.navigation

import androidx.navigation.NavHostController

internal object DestinationsArgs {
  const val COMMUNITY_DETAIL_ID_ARG = "id"
}

internal object Destinations {
  const val AUTH_ROUTE = "auth"
  const val LOGIN_ROUTE = "login"
  const val REGISTER_ROUTE = "register"
  const val FORGET_PASSWORD_ROUTE = "forget_password"
  const val ON_BOARDING_ROUTE = "on_boarding"
  const val DASHBOARD_ROUTE = "dashboard"
  const val PROFILE_ROUTE = "profile"
  const val BLOG_ROUTE = "blog"
  const val COMMUNITY_ROUTE = "comminity"
  const val CHAT_ROUTE = "chat"
  const val HOME_ROUTE = "home"
  const val HOME_GUEST_ROUTE = "home"
  const val DETAIL_ROUTE =
      "detail?" +
          "${DestinationsArgs.COMMUNITY_DETAIL_ID_ARG}={${DestinationsArgs.COMMUNITY_DETAIL_ID_ARG}}"
}

class NavigationHelpers(internal val navController: NavHostController) {
  fun navigateUp() {
    navController.navigateUp()
  }

  /*fun navigateToDashboard() {
      navController.navigate(Destinations.DASHBOARD_ROUTE) {
          popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
          }
      }
  }*/
}
