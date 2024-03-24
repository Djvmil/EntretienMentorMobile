package com.djvmil.entretienmentor.ui.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.djvmil.entretienmentor.presentation.presentation.auth.register.RegisterScreen
import com.djvmil.entretienmentor.ui.navigation.Destinations
import com.djvmil.entretienmentor.ui.navigation.NavigationActions

fun NavGraphBuilder.register(
    modifier: Modifier,
    navActions: NavigationActions
) {
    composable(Destinations.REGISTER_ROUTE) {
        RegisterScreen(
            openDashboard = {
                navActions.navigateToDashboard()
            }
        )
    }
}