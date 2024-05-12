package com.djvmil.entretienmentor.feature.ui.auth.login.model

class TextFieldNotEmptyState(val value: String? = null, override val label: String? = null) :
    TextFieldState(validator = ::isValueValid, errorFor = ::validationError) {
  init {
    value?.let { text = it }
  }
}

/** Returns an error to be displayed or null if no error was found */
private fun validationError(value: String): String {
  return "Required value"
}

private fun isValueValid(value: String): Boolean {
  return value.isNotBlank()
}

val TextFieldNotEmptyStateSaver = textFieldStateSaver(TextFieldNotEmptyState())
