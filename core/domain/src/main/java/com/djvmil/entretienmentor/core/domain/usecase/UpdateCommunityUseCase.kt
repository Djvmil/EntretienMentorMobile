package com.djvmil.entretienmentor.core.domain.usecase

import com.djvmil.entretienmentor.core.data.repository.CommunityRepository
import com.djvmil.entretienmentor.core.domain.model.CommunityDomainModel
import com.djvmil.entretienmentor.core.domain.model.toData
import com.djvmil.entretienmentor.core.domain.util.UseCase

class UpdateCommunityUseCase internal constructor(private val repository: CommunityRepository) :
    UseCase<CommunityDomainModel, Unit> {
  override suspend fun invoke(input: CommunityDomainModel) = repository.update(input.toData())
}
