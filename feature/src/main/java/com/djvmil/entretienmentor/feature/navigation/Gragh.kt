package com.djvmil.entretienmentor.feature.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.model.StepsStarting
import com.djvmil.entretienmentor.feature.ui.auth.auth.navigation.auth
import com.djvmil.entretienmentor.feature.ui.auth.forgetpassword.navigation.forgetPassword
import com.djvmil.entretienmentor.feature.ui.auth.login.navigation.login
import com.djvmil.entretienmentor.feature.ui.auth.register.navigation.register
import com.djvmil.entretienmentor.feature.ui.blog.navigation.blog
import com.djvmil.entretienmentor.feature.ui.chat.navigation.chat
import com.djvmil.entretienmentor.feature.ui.community.navigation.community
import com.djvmil.entretienmentor.feature.ui.detail.navigation.detail
import com.djvmil.entretienmentor.feature.ui.home.navigation.home
import com.djvmil.entretienmentor.feature.ui.onboarding.navigation.onBoarding
import com.djvmil.entretienmentor.feature.ui.profile.navigation.profile

@Composable
fun NavGraph(
    navController: NavHostController,
    navActions: NavigationHelpers,
    appSettings: AppSettings,
    modifier: Modifier = Modifier,
) {
  val startDestination: String =
      when (appSettings.stepsStarting) {
        StepsStarting.ON_BOARDING -> {
          Destinations.ON_BOARDING_ROUTE
        }
        StepsStarting.ON_HOME_USER -> {
          Destinations.HOME_ROUTE
        }
        StepsStarting.ON_GUEST_PAGE -> {
          Destinations.HOME_GUEST_ROUTE
        }
        StepsStarting.ON_AUTH_PAGE -> {
          Destinations.AUTH_ROUTE
        }
      }

  Log.e(
      "NavGraph",
      "startDestination: $startDestination ",
  )
  val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination
  NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
    auth(navActions = navActions)
    login(navActions = navActions)
    register(navActions = navActions)
    forgetPassword(navActions = navActions)
    home(navActions = navActions, isGuest = startDestination == Destinations.HOME_GUEST_ROUTE)
    onBoarding(navActions = navActions)
    detail(navActions = navActions)
    blog(navActions = navActions)
    community(navActions = navActions)
    chat(navActions = navActions)
    profile(navActions = navActions)
  }
}
