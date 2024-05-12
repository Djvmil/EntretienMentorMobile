package com.djvmil.entretienmentor.core.domain.usecase

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.repository.AuthRepository
import com.djvmil.entretienmentor.core.domain.util.UseCase
import kotlinx.coroutines.flow.Flow

class RegisterUseCase internal constructor(private val repository: AuthRepository) :
    UseCase<AuthRequest, Flow<ResultEM<RequestResult<String>, ErrorEM>>> {
  override suspend fun invoke(input: AuthRequest): Flow<ResultEM<RequestResult<String>, ErrorEM>> =
      repository.register(input) /*.map {
            it.map { it.toDomain() }
        }*/
}
