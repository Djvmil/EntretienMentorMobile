package com.djvmil.entretienmentor.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.djvmil.entretienmentor.presentation.presentation.dashboard.DashboardScreen
import com.djvmil.entretienmentor.ui.navigation.Destinations

fun NavGraphBuilder.dashboard() {
    composable(Destinations.DASHBOARD_ROUTE) {
        DashboardScreen()
    }
}