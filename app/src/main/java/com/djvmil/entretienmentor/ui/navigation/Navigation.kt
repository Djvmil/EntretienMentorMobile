package com.djvmil.entretienmentor.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.djvmil.entretienmentor.ui.navigation.DestinationsArgs.MOVIE_DETAIL_ID_ARG
import com.djvmil.entretienmentor.ui.navigation.Screens.BLOG_SCREEN
import com.djvmil.entretienmentor.ui.navigation.Screens.CHAT_SCREEN
import com.djvmil.entretienmentor.ui.navigation.Screens.COMMINITY_SCREEN
import com.djvmil.entretienmentor.ui.navigation.Screens.DASHBOARD_SCREEN
import com.djvmil.entretienmentor.ui.navigation.Screens.DETAIL_SCREEN
import com.djvmil.entretienmentor.ui.navigation.Screens.HOME_SCREEN
import com.djvmil.entretienmentor.ui.navigation.Screens.LOGIN_SCREEN
import com.djvmil.entretienmentor.ui.navigation.Screens.PROFILE_SCREEN
import com.djvmil.entretienmentor.ui.navigation.Screens.REGISTER_SCREEN

private object Screens {
    const val DASHBOARD_SCREEN = "dashboard"
    const val HOME_SCREEN = "home"
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val PROFILE_SCREEN = "profile"
    const val BLOG_SCREEN = "blog"
    const val COMMINITY_SCREEN = "comminity"
    const val CHAT_SCREEN = "chat"
    const val DETAIL_SCREEN = "detail"
}

object DestinationsArgs {
    const val MOVIE_DETAIL_ID_ARG = "id"
}

object Destinations {
    const val LOGIN_ROUTE = LOGIN_SCREEN
    const val REGISTER_ROUTE = REGISTER_SCREEN
    const val PROFILE_ROUTE = PROFILE_SCREEN
    const val BLOG_ROUTE = BLOG_SCREEN
    const val COMMINITY_ROUTE = COMMINITY_SCREEN
    const val CHAT_ROUTE = CHAT_SCREEN
    const val DASHBOARD_ROUTE = DASHBOARD_SCREEN
    const val HOME_ROUTE = HOME_SCREEN
    const val DETAIL_ROUTE = "$DETAIL_SCREEN?" + "$MOVIE_DETAIL_ID_ARG={$MOVIE_DETAIL_ID_ARG}"
}

class NavigationActions(private val navController: NavHostController) {
    fun navigateToLogin() {
        navController.navigate(Destinations.LOGIN_ROUTE)
    }

    fun navigateToRegister() {
        navController.navigate(Destinations.REGISTER_ROUTE)
    }

    fun navigateToProfile() {
        navController.navigate(Destinations.PROFILE_ROUTE)
    }

    fun navigateToComminity() {
        navController.navigate(Destinations.COMMINITY_ROUTE)
    }

    fun navigateToBlog() {
        navController.navigate(Destinations.BLOG_ROUTE)
    }

    fun navigateToChat() {
        navController.navigate(Destinations.CHAT_ROUTE)
    }

    fun navigateToHome() {
        navController.navigate(Destinations.HOME_ROUTE)
    }

    fun navigateToDashboard() {
        navController.navigate(Destinations.DASHBOARD_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    fun navigateToDetail(movieId: Int) {
        navController.navigate(
            "$DETAIL_SCREEN?" +
                "${MOVIE_DETAIL_ID_ARG}=${movieId}"
        )
    }
}