package com.djvmil.entretienmentor.core.data.repository

import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppTheme
import com.djvmil.entretienmentor.core.data.source.datastore.model.StepsStarting
import kotlinx.coroutines.flow.Flow

interface DataSourceRepository {
  fun appSetting(): Flow<AppSettings?>

  suspend fun update(transform: suspend (current: AppSettings?) -> AppSettings?): AppSettings?

  suspend fun setTheme(theme: AppTheme)

  fun getTheme(): Flow<AppTheme?>

  suspend fun setIsLogin(status: Boolean)

  fun isLogin(): Flow<Boolean?>

  suspend fun setStepsStarting(steps: StepsStarting)

  fun getStepsStarting(): Flow<StepsStarting?>

  suspend fun setAccessToken(accessToken: String)

  fun getAccessToken(): Flow<String?>

  suspend fun getAccessTokenForAuth(): String?
}
