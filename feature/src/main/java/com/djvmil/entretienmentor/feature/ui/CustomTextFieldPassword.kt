package com.djvmil.entretienmentor.feature.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.djvmil.entretienmentor.feature.ui.auth.login.TextFieldError
import com.djvmil.entretienmentor.feature.ui.auth.login.model.TextFieldState

@Composable
fun CustomTextFieldPassword(
    modifier: Modifier = Modifier,
    passwordState: TextFieldState,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp),
    readOnly: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
    @DrawableRes leadingIconId: Int? = null,
    disableTrailingIcon: Boolean = false,
) {
  var passwordVisible by rememberSaveable { mutableStateOf(false) }

  val leadingIcon: (@Composable () -> Unit)? =
      if (leadingIconId != null) {
        { Icon(painter = painterResource(id = leadingIconId), "Icon") }
      } else null

  val trailingIcon: (@Composable () -> Unit)? =
      if (!disableTrailingIcon) {
        {
          val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

          // Localized description for accessibility services
          val description = if (passwordVisible) "Hide password" else "Show password"

          // Toggle button to hide or display password
          IconButton(onClick = { passwordVisible = !passwordVisible }) {
            Icon(imageVector = image, description)
          }
        }
      } else null

  OutlinedTextField(
      modifier =
          modifier.padding(vertical = 2.dp).fillMaxWidth().onFocusChanged { focusState ->
            passwordState.onFocusChange(focusState.isFocused)
            if (!focusState.isFocused) {
              passwordState.enableShowErrors()
            }
          },
      shape = shape,
      label = { passwordState.label?.let { Text(text = it) } },
      readOnly = readOnly,
      value = passwordState.text,
      onValueChange = {
        passwordState.text = it
        passwordState.enableShowErrors()
      },
      singleLine = true,
      isError = passwordState.showErrors(),
      supportingText = {
        passwordState.getError()?.let { error -> TextFieldError(textError = error) }
      },
      visualTransformation =
          if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
      keyboardOptions = keyboardOptions,
      keyboardActions = keyboardActions,
      trailingIcon = trailingIcon,
      leadingIcon = leadingIcon,
  )
}
