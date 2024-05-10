package com.djvmil.entretienmentor.core.domain

import app.cash.turbine.test
import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.common.test.MainDispatcherRule
import com.djvmil.entretienmentor.core.data.repository.AuthRepository
import com.djvmil.entretienmentor.core.domain.usecase.LoginUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var authRepository: AuthRepository
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup(){
        loginUseCase = LoginUseCase(authRepository)
    }

    @Test
    fun test_invoke_success_login_use_case() = runTest{
        //GIVEN
        val expectedDataRequest = FAKE_DATA.fakeAuthRequest
        coEvery { authRepository.login(expectedDataRequest) } returns flowOf (
            ResultEM.Success(
                FAKE_DATA.fakeRequestResult
            )
        )

        //WHEN
        val actualResponse = loginUseCase.invoke(expectedDataRequest)

        //THEN
        actualResponse.test {
            Truth.assertThat(awaitItem()).isEqualTo(ResultEM.Success(FAKE_DATA.fakeRequestResult))
            awaitComplete()
        }
    }

    @Test
    fun test_invoke_error_login_use_case() = runTest{
        //GIVEN
        val expectedDataRequest = FAKE_DATA.fakeAuthRequest
        coEvery {
            authRepository.login(FAKE_DATA.fakeAuthRequest)
        } returns flowOf (
            ResultEM.Failure(
                ErrorEM("error")
            )
        )

        //WHEN
        val actualResponse = loginUseCase.invoke(expectedDataRequest)

        //THEN
        actualResponse.test {
            Truth.assertThat(awaitItem()).isEqualTo(ResultEM.Failure(ErrorEM("error")))
            awaitComplete()
        }
    }
}