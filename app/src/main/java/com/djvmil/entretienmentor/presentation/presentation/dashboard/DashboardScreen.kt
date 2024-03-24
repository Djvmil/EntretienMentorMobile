package com.djvmil.entretienmentor.presentation.presentation.dashboard

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.djvmil.common.bottombar.AnimatedNavigationBar
import com.djvmil.common.bottombar.animation.balltrajectory.Straight
import com.djvmil.common.bottombar.animation.indendshape.StraightIndent
import com.djvmil.common.bottombar.animation.indendshape.shapeCornerRadius
import com.djvmil.entretienmentor.presentation.presentation.dashboard.colorButtons.ColorButton
import com.djvmil.entretienmentor.ui.navigation.NavGraph
import com.djvmil.entretienmentor.ui.navigation.NavigationActions
import com.djvmil.entretienmentor.ui.theme.EntretienMentorTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen() {
    val navController: NavHostController = rememberNavController()
    val navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }

    EntretienMentorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                topBar = {
                    /*TopAppBar(
                        title = {
                            Box(Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center) {
                                Text(text = "Entretien Mentor", fontWeight = FontWeight.Bold)
                            }

                        },
                        Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        navigationIcon = {
                            IconButton(onClick = {  }) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back icon" )
                            }

                        },
                        actions = {

                            IconButton(onClick = {  }) {
                                Icon(painter = painterResource(id = R.drawable.outline_bell), contentDescription = "Notification icon" )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.White
                        )
                    )*/
                },
                bottomBar = {
                    ColorButtonNavBar(navActions)
                },
                modifier = Modifier,
                snackbarHost = {},
                floatingActionButton = {},
                content = {
                    NavGraph(
                        navController = navController,
                        navActions = navActions
                    )

                }

            )

        }
    }

}

@Composable
fun ColorButtonNavBar(navActions: NavigationActions) {
    var selectedItem by remember { mutableStateOf(0) }
    var prevSelectedIndex by remember { mutableStateOf(0) }

    AnimatedNavigationBar(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(70.dp),
        selectedIndex = selectedItem,

        ballColor = Color.White,
        cornerRadius = shapeCornerRadius(25.dp),
        ballAnimation = Straight(
            spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessVeryLow)
        ),
        indentAnimation = StraightIndent(
            indentWidth = 56.dp,
            indentHeight = 15.dp,
            animationSpec = tween(1000)
        )
    ) {
        colorButtons.forEachIndexed { index, it ->
            ColorButton(
                modifier = Modifier.fillMaxSize(),
                prevSelectedIndex = prevSelectedIndex,
                selectedIndex = selectedItem,
                index = index,
                onClick = {
                    prevSelectedIndex = selectedItem
                    selectedItem = index
                    when(selectedItem){
                        0 -> navActions.navigateToHome()
                        1 -> navActions.navigateToChat()
                        2 -> navActions.navigateToBlog()
                        3 -> navActions.navigateToComminity()
                        4 -> navActions.navigateToProfile()
                    }

                },
                icon = it.icon,
                contentDescription = stringResource(id = it.description),
                animationType = it.animationType,
                background = it.animationType.background
            )
        }
    }
}
