package com.djvmil.entretienmentor.feature.ui.auth.login.model

import java.util.regex.Pattern

// Consider an email valid if there's some text before and after a "@"
private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

class UsernameState(val username: String? = null, override val label: String? = null) :
    TextFieldState(validator = ::isEmailValid, errorFor = ::emailValidationError) {
  init {
    username?.let { text = it }
  }
}

/** Returns an error to be displayed or null if no error was found */
private fun emailValidationError(email: String): String {
  return "Invalid email: $email"
}

private fun isEmailValid(email: String): Boolean {
  return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}

val UsernameStateSaver = textFieldStateSaver(UsernameState())
