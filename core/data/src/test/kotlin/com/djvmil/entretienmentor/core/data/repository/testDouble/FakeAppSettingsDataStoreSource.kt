package com.djvmil.entretienmentor.core.data.repository.testDouble

import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsDataStoreSource
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppTheme
import com.djvmil.entretienmentor.core.data.source.datastore.model.StepsStarting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeAppSettingsDataStoreSource : AppSettingsDataStoreSource {

  private var appSettings = AppSettings()

  override fun appSetting(): Flow<AppSettings?> = flowOf(appSettings)

  override suspend fun update(transform: suspend (current: AppSettings?) -> AppSettings?) =
      appSettings

  override suspend fun setTheme(theme: AppTheme) {
    appSettings = appSettings.copy(theme = theme)
  }

  override fun getTheme(): Flow<AppTheme?> = flowOf(appSettings.theme)

  override suspend fun setIsLogin(status: Boolean) {
    appSettings = appSettings.copy(isLogin = status)
  }

  override suspend fun setLogin(status: Boolean, accessToken: String, steps: StepsStarting) {
    appSettings =
        appSettings.copy(isLogin = status, accessToken = accessToken, stepsStarting = steps)
  }

  override fun isLogin(): Flow<Boolean?> = flowOf(appSettings.isLogin)

  override suspend fun setStepsStarting(steps: StepsStarting) {
    appSettings = appSettings.copy(stepsStarting = steps)
  }

  override fun getStepsStarting(): Flow<StepsStarting?> = flowOf(appSettings.stepsStarting)

  override suspend fun setAccessToken(accessToken: String) {
    appSettings =
        appSettings.copy(
            accessToken = accessToken,
        )
  }

  override fun getAccessToken(): Flow<String?> = flowOf(appSettings.accessToken)

  override suspend fun getAccessTokenForAuth(): String? = appSettings.accessToken
}
