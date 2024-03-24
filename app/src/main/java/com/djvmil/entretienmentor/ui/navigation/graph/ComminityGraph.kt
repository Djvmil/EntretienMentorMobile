package com.djvmil.entretienmentor.ui.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.djvmil.entretienmentor.presentation.presentation.chat.ChatScreen
import com.djvmil.entretienmentor.ui.navigation.Destinations
import com.djvmil.entretienmentor.ui.navigation.NavigationActions

fun NavGraphBuilder.comminity(
    modifier: Modifier,
    navActions: NavigationActions
) {
    composable(Destinations.COMMINITY_ROUTE) {
        ChatScreen(
            openDashboard = {
                navActions.navigateToDashboard()
            }
        )
    }
}