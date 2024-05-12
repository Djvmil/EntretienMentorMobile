package com.djvmil.entretienmentor.feature.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djvmil.entretienmentor.core.common.dispatcher.AppDispatchers
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.domain.usecase.LoginUseCase
import com.djvmil.entretienmentor.feature.ui.ScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val dispatchers: AppDispatchers,
) : ViewModel() {
  private var _uiState = MutableStateFlow<ScreenUiState<String>>(ScreenUiState.Init)
  val uiState = _uiState.asStateFlow()

  fun onLoginClick(username: String, password: String) =
      viewModelScope.launch(dispatchers.io) {
        _uiState.emit(ScreenUiState.Loading)
        loginUseCase.invoke(AuthRequest(username = username, password = password)).collect { result
          ->
          // Simulate delay
          // delay(2000)
          when (result) {
            is ResultEM.Loading -> {
              _uiState.emit(ScreenUiState.Loading)
            }
            is ResultEM.Success -> {
              _uiState.emit(ScreenUiState.Success("Success Login"))
            }
            is ResultEM.Failure -> {
              result.error.throwable?.let { ScreenUiState.Failure(it) }?.let { _uiState.emit(it) }
            }
          }
        }
      }
}
