package com.djvmil.entretienmentor.core.domain.usecase

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.common.model.map
import com.djvmil.entretienmentor.core.data.repository.CommunityRepository
import com.djvmil.entretienmentor.core.domain.model.CommunityDomainModel
import com.djvmil.entretienmentor.core.domain.model.toDomain
import com.djvmil.entretienmentor.core.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAndStoreAllCommunityUseCase
internal constructor(private val repository: CommunityRepository) :
    UseCaseNoInput<Flow<ResultEM<List<CommunityDomainModel>, ErrorEM>>> {
  override suspend fun invoke(): Flow<ResultEM<List<CommunityDomainModel>, ErrorEM>> {
    repository.getFromApi()
    return repository.getAll().map { result -> result.map { list -> list.map { it.toDomain() } } }
  }
}
