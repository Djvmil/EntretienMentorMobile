package com.djvmil.entretienmentor.core.domain.usecase

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.model.auth.ResponseAuthData
import com.djvmil.entretienmentor.core.data.repository.AuthRepository
import com.djvmil.entretienmentor.core.domain.util.UseCase
import kotlinx.coroutines.flow.Flow

class LoginUseCase internal constructor(private val repository: AuthRepository) :
    UseCase<AuthRequest, Flow<ResultEM<RequestResult<ResponseAuthData>, ErrorEM>>> {
  override suspend fun invoke(
      input: AuthRequest
  ): Flow<ResultEM<RequestResult<ResponseAuthData>, ErrorEM>> = repository.login(input) /*.map {
            it.map { it.toDomain() }
        }*/
}
