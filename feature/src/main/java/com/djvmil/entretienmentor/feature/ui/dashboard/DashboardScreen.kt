package com.djvmil.entretienmentor.feature.ui.dashboard

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.ui.designsystem.theme.EntretienMentorTheme
import com.djvmil.entretienmentor.core.ui.widget.bottombar.AnimatedNavigationBar
import com.djvmil.entretienmentor.core.ui.widget.bottombar.animation.balltrajectory.Straight
import com.djvmil.entretienmentor.core.ui.widget.bottombar.animation.indendshape.StraightIndent
import com.djvmil.entretienmentor.core.ui.widget.bottombar.animation.indendshape.shapeCornerRadius
import com.djvmil.entretienmentor.feature.navigation.NavGraph
import com.djvmil.entretienmentor.feature.navigation.NavigationHelpers
import com.djvmil.entretienmentor.feature.ui.blog.navigation.navigateToBlog
import com.djvmil.entretienmentor.feature.ui.chat.navigation.navigateToChat
import com.djvmil.entretienmentor.feature.ui.community.navigation.navigateToCommunity
import com.djvmil.entretienmentor.feature.ui.dashboard.colorButtons.ColorButton
import com.djvmil.entretienmentor.feature.ui.home.navigation.navigateToHome
import com.djvmil.entretienmentor.feature.ui.profile.navigation.navigateToProfile

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    darkTheme: Boolean = false,
    androidTheme: Boolean = false,
    appSettings: AppSettings,
    snackbarHostState: SnackbarHostState,
) {
  val navController: NavHostController = rememberNavController()
  val navActions: NavigationHelpers = remember(navController) { NavigationHelpers(navController) }

  EntretienMentorTheme(useDarkTheme = darkTheme, useAndroidTheme = androidTheme) {
    Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
      Scaffold(
          containerColor = Color.Transparent,
          contentColor = MaterialTheme.colorScheme.onBackground,
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
          bottomBar = { if (appSettings.isLogin) ColorButtonNavBar(navActions) },
          modifier = Modifier,
          snackbarHost = { SnackbarHost(snackbarHostState) },
          contentWindowInsets = WindowInsets(0, 0, 0, 0),
          floatingActionButton = {},
          content = { padding ->
            Row(
                Modifier.fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
              NavGraph(
                  navController = navController,
                  navActions = navActions,
                  appSettings = appSettings,
                  /*modifier = if (appSettings.isLogin) {
                      Modifier.consumeWindowInsets(
                          WindowInsets.safeDrawing.only(WindowInsetsSides.Top),
                      )
                  } else {
                      Modifier
                  },*/
              )
            }
          })
    }
  }
}

@Composable
fun ColorButtonNavBar(navActions: NavigationHelpers) {
  var selectedItem by remember { mutableIntStateOf(0) }
  var prevSelectedIndex by remember { mutableIntStateOf(0) }

  AnimatedNavigationBar(
      modifier =
          Modifier
              // .padding(horizontal = 8.dp, vertical = 8.dp)
              .height(70.dp),
      selectedIndex = selectedItem,
      ballColor = Color.White,
      cornerRadius = shapeCornerRadius(25.dp),
      ballAnimation = Straight(spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessVeryLow)),
      indentAnimation =
          StraightIndent(indentWidth = 56.dp, indentHeight = 15.dp, animationSpec = tween(1000))) {
        colorButtons.forEachIndexed { index, it ->
          ColorButton(
              modifier = Modifier.fillMaxSize(),
              prevSelectedIndex = prevSelectedIndex,
              selectedIndex = selectedItem,
              index = index,
              onClick = {
                prevSelectedIndex = selectedItem
                selectedItem = index
                when (selectedItem) {
                  0 -> navActions.navigateToHome()
                  1 -> navActions.navigateToChat()
                  2 -> navActions.navigateToBlog()
                  3 -> navActions.navigateToCommunity()
                  4 -> navActions.navigateToProfile()
                }
              },
              icon = it.icon,
              contentDescription = stringResource(id = it.description),
              animationType = it.animationType,
              background = it.animationType.background)
        }
      }
}
