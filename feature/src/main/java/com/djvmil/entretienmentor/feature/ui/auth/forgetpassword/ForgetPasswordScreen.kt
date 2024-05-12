package com.djvmil.entretienmentor.feature.ui.auth.forgetpassword

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.djvmil.entretienmentor.feature.R
import com.djvmil.entretienmentor.feature.ui.CustomTextField
import com.djvmil.entretienmentor.feature.ui.HeaderComponent
import com.djvmil.entretienmentor.feature.ui.auth.login.model.UsernameState
import com.djvmil.entretienmentor.feature.ui.auth.login.model.UsernameStateSaver
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgetPasswordScreen(
    openDashboard: () -> Unit,
    viewModel: ForgetPasswordViewModel = koinViewModel()
) {
  ForgetPasswordContent(openDashboard)
}

@Composable
fun ForgetPasswordContent(openDashboard: () -> Unit) {
  Column(
      modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(30.dp),
  ) {
    HeaderComponent(modifier = Modifier.padding(bottom = 20.dp)) { openDashboard.invoke() }

    Text(
        modifier = Modifier.padding(vertical = 5.dp),
        text = "Let's Sign you in",
        fontSize = 30.sp,
        fontFamily = FontFamily(Font(R.font.josefin_sans_semibold)))

    Text(
        modifier = Modifier.padding(vertical = 5.dp),
        text = "Welcome back",
        fontSize = 25.sp,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular)))

    Text(
        modifier = Modifier.padding(vertical = 5.dp),
        text = "You've been missed!",
        fontSize = 25.sp,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular)))
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
              text = "Forgot Password",
              style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

          val usernameState by
              rememberSaveable(stateSaver = UsernameStateSaver) {
                mutableStateOf(UsernameState(label = "Username or Email"))
              }

          CustomTextField(modifier = Modifier.padding(top = 30.dp), textFieldState = usernameState)

          Spacer(modifier = Modifier.height(15.dp))

          Spacer(modifier = Modifier.height(15.dp))

          Box(
              modifier =
                  Modifier.fillMaxWidth()
                      .height(50.dp)
                      .graphicsLayer {
                        shape = RoundedCornerShape(10.dp)
                        clip = true
                      }
                      .background(MaterialTheme.colorScheme.primary),
              contentAlignment = Alignment.Center) {
                Text(
                    modifier = Modifier,
                    text = "Forgot Password",
                    style =
                        TextStyle(
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.W900,
                            fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))))
              }
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
                  text = "Don't have an account?  ",
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
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight")
@Composable
fun DefaultPreview() {
  ForgetPasswordContent({})
}
