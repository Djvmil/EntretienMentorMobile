package com.djvmil.domain.di

import com.djvmil.core.ErrorEM
import com.djvmil.core.ResultEM
import com.djvmil.domain.model.CommentDomainModel
import com.djvmil.domain.model.MovieDomainModel
import com.djvmil.domain.usecase.GetAndStoreAllMovieUseCase
import com.djvmil.domain.usecase.GetMoviesOrFavoriteMoviesUseCase
import com.djvmil.domain.usecase.GetMovieUseCase
import com.djvmil.domain.usecase.GetCommentByIdUseCase
import com.djvmil.domain.usecase.UpdateMovieUseCase
import com.djvmil.domain.util.UseCase
import com.djvmil.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetAndStoreAllMovieUseCase) { bind<UseCaseNoInput<Flow<ResultEM<List<MovieDomainModel>, ErrorEM>>>>() }
    singleOf(::GetMoviesOrFavoriteMoviesUseCase) { bind<UseCase<Boolean, Flow<ResultEM<List<MovieDomainModel>, ErrorEM>>>>() }
    singleOf(::GetMovieUseCase) { bind<UseCase<Int, Flow<ResultEM<MovieDomainModel, ErrorEM>>>>() }
    singleOf(::GetCommentByIdUseCase) { bind<UseCase<Int, Flow<ResultEM<List<CommentDomainModel>, ErrorEM>>>>() }
    singleOf(::UpdateMovieUseCase) { bind<UseCase<MovieDomainModel, Unit>>() }
}