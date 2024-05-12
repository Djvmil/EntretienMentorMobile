package com.djvmil.entretienmentor.core.data.source.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.djvmil.entretienmentor.core.data.source.datastore.model.APP_SETTING_DEFAULT
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppTheme
import com.djvmil.entretienmentor.core.data.source.datastore.model.StepsStarting
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class AppSettingsDataStoreSourceImpl(
    private val appSettings: DataStore<AppSettings>,
) : AppSettingsDataStoreSource {
  override fun appSetting() = appSettings.data

  override suspend fun update(transform: suspend (current: AppSettings?) -> AppSettings?) =
      appSettings.updateData { current ->
        transform(current.takeIf { it != APP_SETTING_DEFAULT }) ?: APP_SETTING_DEFAULT
      }

  override suspend fun setTheme(theme: AppTheme) {
    appSettings.updateData { it.copy(theme = theme) }
  }

  override fun getTheme() =
      appSettings.data
          .onEach { Log.d("getTheme", "getTheme=${it.theme}") }
          .map { v -> v.theme }
          .catch { cause: Throwable ->
            if (cause is IOException) {
              emit(AppTheme.default())
            } else {
              throw cause
            }
          }

  override suspend fun setIsLogin(status: Boolean) {
    appSettings.updateData { it.copy(isLogin = status) }
  }

  override suspend fun setLogin(status: Boolean, accessToken: String, steps: StepsStarting) {
    appSettings.updateData {
      it.copy(isLogin = status, accessToken = accessToken, stepsStarting = steps)
    }
  }

  override fun isLogin() =
      appSettings.data
          .map { v -> v.isLogin }
          .catch { cause: Throwable ->
            if (cause is IOException) {
              emit(false)
            } else {
              throw cause
            }
          }

  override suspend fun setStepsStarting(steps: StepsStarting) {
    appSettings.updateData { it.copy(stepsStarting = steps) }
  }

  override fun getStepsStarting() =
      appSettings.data
          .map { v -> v.stepsStarting }
          .catch { cause: Throwable ->
            if (cause is IOException) {
              emit(StepsStarting.ON_BOARDING)
            } else {
              throw cause
            }
          }

  override suspend fun setAccessToken(accessToken: String) {
    appSettings.updateData { it.copy(accessToken = accessToken) }
  }

  override fun getAccessToken() =
      appSettings.data
          .map { v -> v.accessToken }
          .catch { cause: Throwable ->
            if (cause is IOException) {
              emit(null)
            } else {
              throw cause
            }
          }

  override suspend fun getAccessTokenForAuth(): String? =
      appSettings.data
          .map { v -> v.accessToken }
          .catch { cause: Throwable ->
            if (cause is IOException) {
              emit(null)
            } else {
              throw cause
            }
          }
          .first()

  companion object {
    const val DATASTORE_FILE = "app_settings.pb"
  }
}
