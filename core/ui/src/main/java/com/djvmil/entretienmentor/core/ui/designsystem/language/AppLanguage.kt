package com.djvmil.entretienmentor.core.ui.designsystem.language

import java.util.Locale

enum class AppLanguage(val locale: Locale) {
  English(locale = Locale.ENGLISH),
  French(locale = Locale.FRENCH);

  companion object {
    fun fromLocale(locale: Locale): AppLanguage {
      return entries.find { it.locale == locale } ?: default()
    }

    fun default(): AppLanguage {
      return English
    }
  }
}
