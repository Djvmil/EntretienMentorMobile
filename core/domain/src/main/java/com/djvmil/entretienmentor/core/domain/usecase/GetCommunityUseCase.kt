package com.djvmil.entretienmentor.core.domain.usecase

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.common.model.map
import com.djvmil.entretienmentor.core.data.repository.CommunityRepository
import com.djvmil.entretienmentor.core.domain.model.CommunityDomainModel
import com.djvmil.entretienmentor.core.domain.model.toDomain
import com.djvmil.entretienmentor.core.domain.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCommunityUseCase internal constructor(private val repository: CommunityRepository) :
    UseCase<Int, Flow<ResultEM<CommunityDomainModel, ErrorEM>>> {
  override suspend fun invoke(input: Int): Flow<ResultEM<CommunityDomainModel, ErrorEM>> =
      repository.get(input).map { it.map { it.toDomain() } }
}
