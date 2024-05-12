package com.djvmil.entretienmentor.core.domain

import app.cash.turbine.test
import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.common.test.MainDispatcherRule
import com.djvmil.entretienmentor.core.data.repository.AuthRepository
import com.djvmil.entretienmentor.core.domain.usecase.RegisterUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisterUseCaseTest {

  @get:Rule val mockkRule = MockKRule(this)
  @get:Rule val mainDispatcherRule = MainDispatcherRule()

  @MockK lateinit var authRepository: AuthRepository
  private lateinit var registerUseCase: RegisterUseCase

  @Before
  fun setup() {
    registerUseCase = RegisterUseCase(authRepository)
  }

  @Test
  fun test_invoke_success_register_use_case() = runTest {
    // GIVEN
    val expectedDataRequest = FAKE_DATA.fakeAuthRequest
    coEvery { authRepository.register(expectedDataRequest) } returns
        flowOf(ResultEM.Success(FAKE_DATA.fakeRequestRegisterResult))

    // WHEN
    val actualResponse = registerUseCase.invoke(expectedDataRequest)

    // THEN
    actualResponse.test {
      Truth.assertThat(awaitItem()).isEqualTo(ResultEM.Success(FAKE_DATA.fakeRequestRegisterResult))
      awaitComplete()
    }
  }

  @Test
  fun test_invoke_error_register_use_case() = runTest {
    // GIVEN
    val expectedDataRequest = FAKE_DATA.fakeAuthRequest
    coEvery { authRepository.register(expectedDataRequest) } returns
        flowOf(ResultEM.Failure(ErrorEM("error")))

    // WHEN
    val actualResponse = registerUseCase.invoke(expectedDataRequest)

    // THEN
    actualResponse.test {
      Truth.assertThat(awaitItem()).isEqualTo(ResultEM.Failure(ErrorEM("error")))
      awaitComplete()
    }
  }
}
