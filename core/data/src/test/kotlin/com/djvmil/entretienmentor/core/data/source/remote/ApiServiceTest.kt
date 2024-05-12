package com.djvmil.entretienmentor.core.data.source.remote

import com.djvmil.entretienmentor.core.common.test.MainDispatcherRule
import com.djvmil.entretienmentor.core.data.common.ApiResponseType
import com.djvmil.entretienmentor.core.data.common.FAKE_DATA
import com.djvmil.entretienmentor.core.data.model.ApiOperation
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.mapSuccess
import com.djvmil.entretienmentor.core.data.model.onFailure
import com.djvmil.entretienmentor.core.data.source.remote.api.ApiService
import com.djvmil.entretienmentor.core.data.source.remote.testDouble.ApiServiceFake
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ApiServiceTest {

  @get:Rule val mainDispatcherRule = MainDispatcherRule()
  private lateinit var apiService: ApiService

  @Test
  fun login_Assert_Response_Success() = runTest {
    // GIVEN
    apiService = ApiServiceFake.build(ApiResponseType.RESPONSE_DATA_VALID)
    val bodyRequest = FAKE_DATA.fakeAuthRequest

    // WHEN
    val actualResult = apiService.login(bodyRequest)

    // THEN
    actualResult.mapSuccess {
      assertThat(it.code).isEqualTo(200)
      assertThat(it.data).isNotNull()
      assertThat(it.data?.accessToken).isEqualTo(FAKE_DATA.fakeRequestResult.data?.accessToken)
    }
  }

  @Test
  fun login_Assert_Response_Unauthorized() = runTest {
    // GIVEN
    apiService = ApiServiceFake.build(ApiResponseType.RESPONSE_UNAUTHORIZED)
    val bodyRequest = AuthRequest(username = "admin@em.com", password = "1234")

    // WHEN
    val result = apiService.login(bodyRequest)
    println("result => $result")

    // THEN
    assertThat(result).isInstanceOf(ApiOperation.Failure::class.java)

    result.onFailure { assertThat(it.error?.code).isEqualTo(401) }
  }
}
