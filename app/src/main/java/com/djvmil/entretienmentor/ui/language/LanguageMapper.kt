package com.djvmil.entretienmentor.ui.language

import java.util.Locale

fun Locale.toAppLanguage(): AppLanguage {
    // removes country code and variants if present
    val localeLanguageOnly = Locale.forLanguageTag(this.language)

    return AppLanguage.fromLocale(localeLanguageOnly)
}
