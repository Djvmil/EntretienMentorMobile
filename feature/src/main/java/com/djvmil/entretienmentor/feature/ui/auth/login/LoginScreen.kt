package com.djvmil.entretienmentor.feature.ui.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.djvmil.entretienmentor.feature.R
import com.djvmil.entretienmentor.feature.ui.CustomTextField
import com.djvmil.entretienmentor.feature.ui.CustomTextFieldPassword
import com.djvmil.entretienmentor.feature.ui.HeaderComponent
import com.djvmil.entretienmentor.feature.ui.LoadingAnimation
import com.djvmil.entretienmentor.feature.ui.ScreenUiState
import com.djvmil.entretienmentor.feature.ui.auth.login.model.PasswordState
import com.djvmil.entretienmentor.feature.ui.auth.login.model.TextFieldState
import com.djvmil.entretienmentor.feature.ui.auth.login.model.UsernameState
import com.djvmil.entretienmentor.feature.ui.auth.login.model.UsernameStateSaver
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(openDashboard: () -> Unit, viewModel: LoginViewModel = koinViewModel()) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val usernameState by
      rememberSaveable(stateSaver = UsernameStateSaver) {
        mutableStateOf(UsernameState(label = "Username or Email"))
      }
  val passwordState = remember { PasswordState(label = "Password") }

  val onSubmit = {
    if (usernameState.isValid && passwordState.isValid) {
      viewModel.onLoginClick(usernameState.text, passwordState.text)
    }
  }

  Box(
      modifier = Modifier.fillMaxSize(),
  ) {
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(30.dp),
    ) {
      HeaderComponent(modifier = Modifier.padding(bottom = 20.dp)) { openDashboard.invoke() }

      Column(
          modifier =
              Modifier.imePadding() // padding for the bottom for the IME
                  // .imeNestedScroll() // fill the entire window
                  .verticalScroll(rememberScrollState())) {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Let's Sign you in",
                fontSize = 30.sp,
                lineHeight = 30.sp,
                fontFamily = FontFamily(Font(R.font.josefin_sans_semibold)))

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Welcome back",
                fontSize = 25.sp,
                lineHeight = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily(Font(R.font.helvetica_neue_regular)))

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "You've been missed!",
                fontSize = 25.sp,
                lineHeight = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily(Font(R.font.helvetica_neue_regular)))

            LoginForm(
                usernameState,
                passwordState,
                onSubmit,
            )

            Row(modifier = Modifier.fillMaxWidth().height(60.dp)) {
              if (uiState.isFail) {
                val error = uiState as? ScreenUiState.Failure

                error?.error?.message?.let { TextFieldError(textError = it) }
              }
            }

            Box(
                modifier =
                    Modifier.fillMaxWidth()
                        .height(50.dp)
                        .clickable(usernameState.isValid && passwordState.isValid) {
                          onSubmit.invoke()
                        }
                        .graphicsLayer {
                          shape = RoundedCornerShape(10.dp)
                          clip = true
                        }
                        .then(
                            if ((usernameState.isValid && passwordState.isValid))
                                Modifier.background(MaterialTheme.colorScheme.primary)
                            else Modifier.background(MaterialTheme.colorScheme.primary.copy(0.5f))),
                contentAlignment = Alignment.Center) {
                  Text(
                      modifier = Modifier,
                      text = "Login",
                      style =
                          TextStyle(
                              fontSize = 24.sp,
                              color = MaterialTheme.colorScheme.onPrimary,
                              fontWeight = FontWeight.W900,
                              fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))))
                }

            BottomLogin()
          }
    }

    if (uiState.isLoading) {
      LoadingAnimation(
          modifier =
              Modifier.background(MaterialTheme.colorScheme.primary.copy(0.5f))
                  .align(Alignment.Center),
          isLoading = true)
    }
  }
}

@Composable
fun LoginForm(usernameState: TextFieldState, passwordState: PasswordState, onSubmit: () -> Unit) {
  CustomTextField(modifier = Modifier.padding(top = 30.dp), textFieldState = usernameState)

  CustomTextFieldPassword(
      modifier = Modifier,
      passwordState = passwordState,
      keyboardActions = KeyboardActions(onDone = { onSubmit.invoke() }),
  )
}

@Composable
private fun BottomLogin() {
  HorizontalDivider(Modifier.padding(vertical = 20.dp))
  Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.padding(5.dp).size(40.dp),
            imageVector = Icons.Rounded.Face,
            contentDescription = stringResource(id = R.string.Message))

        Icon(
            modifier = Modifier.padding(5.dp).size(40.dp),
            imageVector = Icons.Rounded.Face,
            contentDescription = stringResource(id = R.string.Message))

        Icon(
            modifier = Modifier.padding(5.dp).size(40.dp),
            imageVector = Icons.Rounded.Face,
            contentDescription = stringResource(id = R.string.Message))
      }
  Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.BottomCenter,
  ) {
    Column(modifier = Modifier) {
      Row(
          modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.dont_have_an_account_text),
                modifier = Modifier,
                style =
                    TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))))
            Text(
                text = "Register",
                modifier = Modifier,
                style =
                    TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))))
          }
    }
  }
}

@Composable
fun TextFieldError(textError: String) {
  Row(modifier = Modifier.fillMaxWidth()) {
    Spacer(modifier = Modifier.width(16.dp))
    Text(
        text = textError,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.error)
  }
}
