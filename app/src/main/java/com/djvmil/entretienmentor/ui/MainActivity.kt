package com.djvmil.entretienmentor.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.djvmil.entretienmentor.R
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppTheme
import com.djvmil.entretienmentor.feature.ui.ScreenUiState
import com.djvmil.entretienmentor.feature.ui.dashboard.DashboardScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
  private val viewModel: MainActivityViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    val splashScreen = installSplashScreen()
    super.onCreate(savedInstanceState)

    var uiState: ScreenUiState<AppSettings?> by mutableStateOf(ScreenUiState.Loading)

    // Update the uiState
    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState
            .onEach {
              Log.e("MainActivity", "onCreate: appSettings $it")
              uiState = it
            }
            .collect()
      }
    }

    // Keep the splash screen on-screen until the UI state is loaded. This condition is
    // evaluated each time the app needs to be redrawn so it should be fast to avoid blocking
    // the UI.
    splashScreen.setKeepOnScreenCondition {
      when (uiState) {
        ScreenUiState.Loading -> true
        is ScreenUiState.Success -> false
        else -> true
      }
    }

    // Turn off the decor fitting system windows, which allows us to handle insets,
    // including IME animations, and go edge-to-edge
    // This also sets up the initial system bar style based on the platform theme
    // enableEdgeToEdge()

    setContent {
      val darkTheme = shouldUseDarkTheme(uiState)
      val androidTheme = shouldUseAndroidTheme(uiState)

      val snackbarHostState = remember { SnackbarHostState() }

      val isOnline by viewModel.networkMonitor.collectAsStateWithLifecycle(false)

      // If user is not connected to the internet show a snack bar to inform them.
      val notConnectedMessage = stringResource(R.string.not_connected)
      LaunchedEffect(isOnline) {
        Log.e("MainActivity", "onCreate: isOnline = $isOnline ")
        if (!isOnline) {
          snackbarHostState.showSnackbar(
              message = notConnectedMessage,
              duration = SnackbarDuration.Indefinite,
          )
        }
      }

      when (val result = uiState) {
        is ScreenUiState.Success -> {
          result.data?.let {
            DashboardScreen(
                darkTheme = darkTheme,
                androidTheme = androidTheme,
                appSettings = it,
                snackbarHostState,
            )
          }
        }
        else -> Log.i("MainActivity", "onCreate appSettings: ${result.valueOrNull}")
      }
    }
  }
}

/** Returns `true` if the Android theme should be used, as a function of the [uiState]. */
@Composable
private fun shouldUseAndroidTheme(uiState: ScreenUiState<AppSettings?>): Boolean =
    when (uiState) {
      ScreenUiState.Loading -> false
      is ScreenUiState.Success ->
          when (uiState.data?.theme) {
            AppTheme.default() -> false
            else -> true
          }
      else -> false
    }

/**
 * Returns `true` if dark theme should be used, as a function of the [uiState] and the current
 * system context.
 */
@Composable
private fun shouldUseDarkTheme(uiState: ScreenUiState<AppSettings?>): Boolean =
    when (uiState) {
      ScreenUiState.Loading -> isSystemInDarkTheme()
      is ScreenUiState.Success ->
          when (uiState.data?.theme) {
            AppTheme.FollowSystem -> isSystemInDarkTheme()
            AppTheme.Light -> false
            AppTheme.Dark -> true
            else -> isSystemInDarkTheme()
          }
      else -> isSystemInDarkTheme()
    }

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)
