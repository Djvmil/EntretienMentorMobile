package com.djvmil.entretienmentor.feature.ui

sealed class ScreenUiState<out T> {
  data object Loading : ScreenUiState<Nothing>()

  data class Success<out T>(val data: T) : ScreenUiState<T>()

  data class Failure(val error: Throwable) : ScreenUiState<Nothing>()

  data object Init : ScreenUiState<Nothing>()

  val isInit
    get() = this is Init

  val isLoading
    get() = this is Loading

  val isFail
    get() = this is Failure

  val valueOrNull
    get() = (this as? Success)?.data
}
