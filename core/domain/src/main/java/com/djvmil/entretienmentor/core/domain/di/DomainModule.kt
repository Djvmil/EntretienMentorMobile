package com.djvmil.entretienmentor.core.domain.di

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.model.auth.ResponseAuthData
import com.djvmil.entretienmentor.core.domain.model.CommunityDomainModel
import com.djvmil.entretienmentor.core.domain.usecase.GetAndStoreAllCommunityUseCase
import com.djvmil.entretienmentor.core.domain.usecase.GetCommunityUseCase
import com.djvmil.entretienmentor.core.domain.usecase.LoginUseCase
import com.djvmil.entretienmentor.core.domain.usecase.RegisterUseCase
import com.djvmil.entretienmentor.core.domain.usecase.UpdateCommunityUseCase
import com.djvmil.entretienmentor.core.domain.util.UseCase
import com.djvmil.entretienmentor.core.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
  singleOf(::LoginUseCase) {
    bind<UseCase<AuthRequest, Flow<ResultEM<RequestResult<ResponseAuthData>, ErrorEM>>>>()
  }
  singleOf(::RegisterUseCase) {
    bind<UseCase<AuthRequest, Flow<ResultEM<RequestResult<String>, ErrorEM>>>>()
  }
  singleOf(::GetCommunityUseCase) {
    bind<UseCase<Int, Flow<ResultEM<CommunityDomainModel, ErrorEM>>>>()
  }
  singleOf(::UpdateCommunityUseCase) { bind<UseCase<CommunityDomainModel, Unit>>() }
  singleOf(::GetAndStoreAllCommunityUseCase) {
    bind<UseCaseNoInput<Flow<ResultEM<List<CommunityDomainModel>, ErrorEM>>>>()
  }
}
