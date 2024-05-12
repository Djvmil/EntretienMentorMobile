package com.djvmil.entretienmentor.feature.ui.auth.auth

import android.content.res.Configuration
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.djvmil.entretienmentor.feature.R
import com.djvmil.entretienmentor.feature.navigation.NavigationHelpers
import com.djvmil.entretienmentor.feature.ui.auth.login.navigation.navigateToLogin
import com.djvmil.entretienmentor.feature.ui.auth.register.navigation.navigateToRegister
import com.djvmil.entretienmentor.feature.ui.home.navigation.navigateToHomeGuest

@Composable
fun AuthScreen(navActions: NavigationHelpers) {
  Column(Modifier.fillMaxSize()) {
    TopAuthPage(navActions)
    BottomAuthPage(navActions)
  }
}

@Composable
fun TopAuthPage(navActions: NavigationHelpers) {
  Column {
    Box(
        modifier =
            Modifier.padding(10.dp)
                .padding(top = 30.dp)
                .height(200.dp)
                .fillMaxWidth()
                .graphicsLayer {
                  shape = RoundedCornerShape(20.dp)
                  clip = true
                }
                .background(Color.White),
        contentAlignment = Alignment.Center) {
          Image(
              modifier = Modifier,
              contentScale = ContentScale.Fit,
              painter = painterResource(id = R.drawable.header_auth),
              contentDescription = "Image Header")
          TextButton(
              modifier = Modifier.align(Alignment.TopEnd),
              onClick = { navActions.navigateToHomeGuest() }) {
                Icon(
                    modifier = Modifier.size(15.dp),
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "skip authentification")

                Text(modifier = Modifier, text = "Skip")
              }
        }
  }
}

@Composable
fun BottomAuthPage(navActions: NavigationHelpers) {
  Box(
      modifier =
          Modifier.padding(10.dp)
              .fillMaxWidth()
              .graphicsLayer {
                shape = RoundedCornerShape(20.dp)
                clip = true
              }
              .background(MaterialTheme.colorScheme.onPrimary),
      contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                  modifier = Modifier.padding(top = 20.dp),
                  text = "Hello, Welcome !",
                  color = MaterialTheme.colorScheme.primary,
                  style =
                      TextStyle(
                          fontSize = 24.sp,
                          fontWeight = FontWeight.Bold,
                          fontFamily = FontFamily(Font(R.font.helvetica_neue_bold))))
              Text(
                  modifier = Modifier.padding(8.dp),
                  text = "De l'entraînement à la réussite : \nEntretienMentor vous accompagne.!",
                  style =
                      TextStyle(
                          textAlign = TextAlign.Center,
                          color = MaterialTheme.colorScheme.primary,
                          fontWeight = FontWeight.Normal,
                          fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))),
              )

              Spacer(modifier = Modifier.height(120.dp))
              Box(
                  modifier =
                      Modifier.fillMaxWidth()
                          .height(50.dp)
                          .graphicsLayer {
                            shape = RoundedCornerShape(10.dp)
                            clip = true
                          }
                          .clickable { navActions.navigateToLogin() }
                          .background(MaterialTheme.colorScheme.primary),
                  contentAlignment = Alignment.Center) {
                    Text(
                        text = "LOGIN",
                        style =
                            TextStyle(
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.helvetica_neue_bold))))
                  }

              Spacer(modifier = Modifier.height(10.dp))
              Box(
                  modifier =
                      Modifier.fillMaxWidth()
                          .height(50.dp)
                          .graphicsLayer {
                            shape = RoundedCornerShape(10.dp)
                            clip = true
                          }
                          .clickable { navActions.navigateToRegister() }
                          .background(MaterialTheme.colorScheme.secondary),
                  contentAlignment = Alignment.Center) {
                    Text(
                        text = "REGISTER",
                        style =
                            TextStyle(
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.helvetica_neue_bold))))
                  }

              Spacer(modifier = Modifier.height(30.dp))

              Row(
                  modifier = Modifier,
                  horizontalArrangement = Arrangement.Center,
                  verticalAlignment = Alignment.CenterVertically) {
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Black)
                    Text(
                        modifier = Modifier.weight(1.5f),
                        text = "Or via social media",
                        style =
                            TextStyle(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.W900,
                                fontFamily = FontFamily(Font(R.font.helvetica_neue_bold))))

                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Black)
                  }

              Spacer(modifier = Modifier.height(8.dp))
              Row(
                  modifier = Modifier,
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

              Spacer(modifier = Modifier.height(30.dp))
            }
      }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark", showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight", showSystemUi = true)
@Composable
fun AuthScreenPreview() {
  AuthScreen(NavigationHelpers(rememberNavController()))
}
