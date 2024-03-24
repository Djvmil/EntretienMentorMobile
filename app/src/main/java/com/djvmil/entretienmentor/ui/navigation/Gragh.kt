package com.djvmil.entretienmentor.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.djvmil.entretienmentor.ui.navigation.graph.blog
import com.djvmil.entretienmentor.ui.navigation.graph.chat
import com.djvmil.entretienmentor.ui.navigation.graph.comminity
import com.djvmil.entretienmentor.ui.navigation.graph.detail
import com.djvmil.entretienmentor.ui.navigation.graph.home
import com.djvmil.entretienmentor.ui.navigation.graph.dashboard
import com.djvmil.entretienmentor.ui.navigation.graph.login
import com.djvmil.entretienmentor.ui.navigation.graph.profile
import com.djvmil.entretienmentor.ui.navigation.graph.register

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    },
    startDestination: String = Destinations.HOME_ROUTE,
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        login(
            modifier = modifier,
            navActions = navActions
        )

        register(
            modifier = modifier,
            navActions = navActions
        )

        dashboard()

        home(
            modifier = modifier,
            navActions = navActions
        )

        detail(
            modifier = modifier,
            navActions = navActions
        )

        blog(
            modifier = modifier,
            navActions = navActions
        )

        comminity(
            modifier = modifier,
            navActions = navActions
        )

        chat(
            modifier = modifier,
            navActions = navActions
        )

        profile(
            modifier = modifier,
            navActions = navActions
        )

    }
}