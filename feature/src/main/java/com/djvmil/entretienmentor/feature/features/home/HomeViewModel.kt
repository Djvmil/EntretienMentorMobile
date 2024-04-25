package com.djvmil.feature.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djvmil.common.model.ErrorEM
import com.djvmil.common.model.ResultEM
import com.djvmil.common.model.map
import com.djvmil.domain.usecase.GetAndStoreAllCommunityUseCase
import com.djvmil.entretienmentor.presentation.model.CommunityUiModel
import com.djvmil.entretienmentor.presentation.model.toUi
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAndStoreAllMovieUseCase: GetAndStoreAllCommunityUseCase,
) : ViewModel() {

    private val _uiMovies = MutableStateFlow<ResultEM<PersistentList<CommunityUiModel>, ErrorEM>>(
        ResultEM.Loading)
    val uiMovies = _uiMovies.asStateFlow()

    fun getCommunities() {
        viewModelScope.launch {
            getAndStoreAllMovieUseCase()
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    updateError(throwable)
                }
                .collect { result ->
                    _uiMovies.value = result.map { list ->
                            list.map {
                                it.toUi()
                            }
                        }.map {
                            it.toPersistentList()
                        }
                }
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        _uiMovies.update { ResultEM.Loading }
    }

    private fun updateError(throwable: Throwable) {
        _uiMovies.update { ResultEM.Failure(ErrorEM(throwable = throwable)) }
    }
}