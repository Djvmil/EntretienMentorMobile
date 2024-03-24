package com.djvmil.entretienmentor.ui.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.djvmil.entretienmentor.presentation.presentation.profile.ProfileScreen
import com.djvmil.entretienmentor.ui.navigation.Destinations
import com.djvmil.entretienmentor.ui.navigation.NavigationActions

fun NavGraphBuilder.profile(
    modifier: Modifier,
    navActions: NavigationActions
) {
    composable(Destinations.PROFILE_ROUTE) {
        ProfileScreen(
            openDashboard = {
                navActions.navigateToDashboard()
            }
        )
    }
}