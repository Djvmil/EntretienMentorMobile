package com.djvmil.entretienmentor.feature.ui.auth.forgetpassword

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djvmil.entretienmentor.core.common.dispatcher.AppDispatchersImpl
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsDataStoreSource
import com.djvmil.entretienmentor.core.domain.usecase.GetCommunityUseCase
import com.djvmil.entretienmentor.core.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class ForgetPasswordViewModel(
    private val loginUseCase: LoginUseCase,
    private val getMovieUseCase: GetCommunityUseCase,
    private val dispatchers: AppDispatchersImpl,
    private val dataStoreSource: AppSettingsDataStoreSource
) : ViewModel() {

  fun login() =
      viewModelScope.launch(dispatchers.io) {
        loginUseCase.invoke(AuthRequest(username = "admin@em.com", password = "1234")).collect {}
        getMovieUseCase.invoke(1)
      }

  fun login1() =
      viewModelScope.launch(dispatchers.io) {
        dataStoreSource.isLogin().collect { Log.e("TAG", "isLogin: $it") }
      }

  fun login2() =
      viewModelScope.launch(dispatchers.io) {
        dataStoreSource.appSetting().collect { Log.e("TAG", "appSetting: $it") }
      }
}
