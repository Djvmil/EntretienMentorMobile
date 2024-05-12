package com.djvmil.entretienmentor.feature.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djvmil.entretienmentor.core.common.dispatcher.AppDispatchers
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.domain.usecase.RegisterUseCase
import com.djvmil.entretienmentor.feature.ui.ScreenUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val dispatchers: AppDispatchers,
) : ViewModel() {

  private var _uiState = MutableStateFlow<ScreenUiState<String>>(ScreenUiState.Init)
  val uiState = _uiState.asStateFlow()

  fun onRegisterClick() =
      viewModelScope.launch(dispatchers.io) {
        registerUseCase
            .invoke(
                AuthRequest(
                    username = "registerState.usernameInput",
                    password = "registerState.passwordInput"))
            .collect { result ->
              delay(4000)
              when (result) {
                is ResultEM.Loading -> {
                  _uiState.emit(ScreenUiState.Loading)
                }
                is ResultEM.Success -> {
                  _uiState.emit(ScreenUiState.Success("Success Register"))
                }
                is ResultEM.Failure -> {
                  result.error.throwable
                      ?.let { ScreenUiState.Failure(it) }
                      ?.let { _uiState.emit(it) }
                }
              }
            }
      }
}
