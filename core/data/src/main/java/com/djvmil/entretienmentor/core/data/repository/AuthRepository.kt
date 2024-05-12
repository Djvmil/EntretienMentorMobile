package com.djvmil.entretienmentor.core.data.repository

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.model.auth.ResponseAuthData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
  suspend fun login(
      loginRequest: AuthRequest
  ): Flow<ResultEM<RequestResult<ResponseAuthData>, ErrorEM>>

  suspend fun register(registerRequest: AuthRequest): Flow<ResultEM<RequestResult<String>, ErrorEM>>
}
