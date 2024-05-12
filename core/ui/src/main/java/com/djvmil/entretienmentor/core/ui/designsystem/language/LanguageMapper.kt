package com.djvmil.entretienmentor.core.ui.designsystem.language

import java.util.Locale

fun Locale.toAppLanguage(): AppLanguage {
  // removes country code and variants if present
  val localeLanguageOnly = Locale.forLanguageTag(this.language)

  return AppLanguage.fromLocale(localeLanguageOnly)
}
