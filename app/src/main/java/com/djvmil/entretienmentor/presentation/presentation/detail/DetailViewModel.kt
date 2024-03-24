package com.djvmil.entretienmentor.presentation.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djvmil.core.ErrorEM
import com.djvmil.core.ResultEM
import com.djvmil.core.map
import com.djvmil.domain.usecase.GetMovieUseCase
import com.djvmil.domain.usecase.UpdateMovieUseCase
import com.djvmil.entretienmentor.presentation.model.MovieUiModel
import com.djvmil.entretienmentor.presentation.model.toDomain
import com.djvmil.entretienmentor.presentation.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    val getMovieUseCase: GetMovieUseCase,
    val updateMovieUseCase: UpdateMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ResultEM<MovieUiModel, ErrorEM>>(ResultEM.Loading)
    val uiState = _uiState.asStateFlow()

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            getMovieUseCase(movieId)
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    updateError(throwable)
                }
                .collect { result ->
                    _uiState.value =
                        result.map { it.toUi() }
                }
        }
    }

    fun updateMovie(movie: MovieUiModel) {
        viewModelScope.launch {
            updateMovieUseCase(movie.toDomain())
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        _uiState.update { ResultEM.Loading }
    }

    private fun updateError(throwable: Throwable) {
        _uiState.update { ResultEM.Failure(
            ErrorEM(
                throwable = throwable
            )
        ) }
    }
}