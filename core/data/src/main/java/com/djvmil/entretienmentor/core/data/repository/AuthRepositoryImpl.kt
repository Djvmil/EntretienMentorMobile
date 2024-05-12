package com.djvmil.entretienmentor.core.data.repository

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.model.auth.ResponseAuthData
import com.djvmil.entretienmentor.core.data.model.onFailure
import com.djvmil.entretienmentor.core.data.model.onSuccess
import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsDataStoreSource
import com.djvmil.entretienmentor.core.data.source.datastore.model.StepsStarting
import com.djvmil.entretienmentor.core.data.source.remote.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val dataStoreSource: AppSettingsDataStoreSource,
) : AuthRepository {

  override suspend fun login(
      loginRequest: AuthRequest
  ): Flow<ResultEM<RequestResult<ResponseAuthData>, ErrorEM>> = flow {
    emit(ResultEM.Loading)
    apiService
        .login(loginRequest)
        .onSuccess { response ->
          // Save token on the data store
          response.data?.accessToken?.let { token ->
            dataStoreSource.setLogin(
                status = true, accessToken = token, steps = StepsStarting.ON_HOME_USER)
          }
          // Send success response
          emit(ResultEM.Success(response))
        }
        .onFailure { error -> emit(ResultEM.Failure(ErrorEM(throwable = error))) }
  }

  override suspend fun register(
      registerRequest: AuthRequest
  ): Flow<ResultEM<RequestResult<String>, ErrorEM>> = flow {
    emit(ResultEM.Loading)
    apiService
        .register(registerRequest)
        .onSuccess { response -> emit(ResultEM.Success(response)) }
        .onFailure { error -> emit(ResultEM.Failure(ErrorEM(throwable = error))) }
  }
}
