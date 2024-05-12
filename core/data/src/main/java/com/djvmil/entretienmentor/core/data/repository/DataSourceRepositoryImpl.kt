package com.djvmil.entretienmentor.core.data.repository

import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsDataStoreSource
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppTheme
import com.djvmil.entretienmentor.core.data.source.datastore.model.StepsStarting

class DataSourceRepositoryImpl(private val dataStoreSource: AppSettingsDataStoreSource) :
    DataSourceRepository {
  override fun appSetting() = dataStoreSource.appSetting()

  override suspend fun update(transform: suspend (current: AppSettings?) -> AppSettings?) =
      dataStoreSource.update(transform)

  override suspend fun setTheme(theme: AppTheme) = dataStoreSource.setTheme(theme)

  override fun getTheme() = dataStoreSource.getTheme()

  override suspend fun setIsLogin(status: Boolean) = dataStoreSource.setIsLogin(status)

  override fun isLogin() = dataStoreSource.isLogin()

  override suspend fun setStepsStarting(steps: StepsStarting) =
      dataStoreSource.setStepsStarting(steps)

  override fun getStepsStarting() = dataStoreSource.getStepsStarting()

  override suspend fun setAccessToken(accessToken: String) =
      dataStoreSource.setAccessToken(accessToken)

  override fun getAccessToken() = dataStoreSource.getAccessToken()

  override suspend fun getAccessTokenForAuth() = dataStoreSource.getAccessTokenForAuth()
}
