package com.djvmil.entretienmentor.feature.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djvmil.entretienmentor.feature.ui.auth.login.model.TextFieldState

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp),
    readOnly: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
    @DrawableRes leadingIconId: Int? = null,
    @DrawableRes trailingIconId: Int? = null,
) {
  val leadingIcon: (@Composable () -> Unit)? =
      if (leadingIconId != null) {
        { Icon(painter = painterResource(id = leadingIconId), "Icon") }
      } else null

  val trailingIcon: (@Composable () -> Unit)? =
      if (trailingIconId != null) {
        { Icon(painter = painterResource(id = trailingIconId), "Icon") }
      } else null

  OutlinedTextField(
      modifier =
          modifier.padding(vertical = 2.dp).fillMaxWidth().onFocusChanged { focusState ->
            textFieldState.onFocusChange(focusState.isFocused)
            if (!focusState.isFocused) {
              textFieldState.enableShowErrors()
            }
          },
      shape = shape,
      label = { textFieldState.label?.let { Text(text = it) } },
      value = textFieldState.text,
      supportingText = { textFieldState.getError()?.let { Text(text = it) } },
      onValueChange = { textFieldState.text = it },
      readOnly = readOnly,
      singleLine = true,
      keyboardOptions = keyboardOptions,
      keyboardActions = keyboardActions,
      isError = textFieldState.showErrors(),
      trailingIcon = trailingIcon,
      leadingIcon = leadingIcon,
  )
}

@Preview
@Composable
fun CustumTextFieldPreview() {
  /*
  Column {
      CustumTextField(
          modifier = Modifier,
          value = ""
      ){}
      CustumTextField(
          modifier = Modifier,
          value = ""
      ){}
      CustumTextField(
          modifier = Modifier,
          value = ""
      ){}
  }*/
}
