package com.djvmil.entretienmentor.core.data.source.datastore.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val isLogin: Boolean = false,
    val accessToken: String? = null,
    val stepsStarting: StepsStarting = StepsStarting.ON_BOARDING,
    val theme: AppTheme = AppTheme.default()
)

internal val APP_SETTING_DEFAULT: AppSettings = AppSettings()
