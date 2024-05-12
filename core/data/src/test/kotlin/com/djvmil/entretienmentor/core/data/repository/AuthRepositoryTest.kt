package com.djvmil.entretienmentor.core.data.repository

import app.cash.turbine.test
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.common.model.map
import com.djvmil.entretienmentor.core.common.test.MainDispatcherRule
import com.djvmil.entretienmentor.core.data.common.FAKE_DATA
import com.djvmil.entretienmentor.core.data.model.ApiOperation
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.repository.testDouble.FakeAppSettingsDataStoreSource
import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsDataStoreSource
import com.djvmil.entretienmentor.core.data.source.remote.api.ApiService
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthRepositoryTest {
  @get:Rule val mockkRule = MockKRule(this)

  @get:Rule val mainDispatcherRule = MainDispatcherRule()

  @MockK private lateinit var apiService: ApiService
  private lateinit var dataStoreSource: AppSettingsDataStoreSource
  private lateinit var authRepository: AuthRepository
  private val bodyRequest = AuthRequest(username = "admin@em.com", password = "1234")

  @Before
  fun setup() {
    coEvery { apiService.login(bodyRequest) } returns
        ApiOperation.Success(FAKE_DATA.fakeRequestResult)

    dataStoreSource = FakeAppSettingsDataStoreSource()
    authRepository = AuthRepositoryImpl(apiService = apiService, dataStoreSource = dataStoreSource)
  }

  @Test
  fun login_Assert_Response_Success() = runTest {
    // GIVEN
    val expectedUserResponse = FAKE_DATA.fakeRequestResult.data?.user
    val expectedAccessToken = FAKE_DATA.fakeRequestResult.data?.accessToken

    // WHEN
    val actualData = authRepository.login(bodyRequest)

    // mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
    // THEN
    actualData.test {
      Truth.assertThat(ResultEM.Loading).isEqualTo(awaitItem())

      when (val item = awaitItem()) {
        is ResultEM.Failure,
        is ResultEM.Loading,
        is ResultEM.Success -> {
          item.map { itemData ->
            Truth.assertThat(itemData.data).isNotNull()
            Truth.assertThat(itemData.data?.accessToken).isNotNull()
            Truth.assertThat(itemData.data?.accessToken).isEqualTo(expectedAccessToken)
            Truth.assertThat(itemData.data?.user).isEqualTo(expectedUserResponse)
          }
        }
      }
      awaitComplete()
    }
  }

  @Test
  fun login_Assert_Saved_OnDataStore() = runTest {
    // GIVEN
    val expectedAppSettingDataTest = FAKE_DATA.fakeAppSettingData

    // WHEN
    dataStoreSource.setLogin(
        status = expectedAppSettingDataTest.isLogin,
        accessToken = expectedAppSettingDataTest.accessToken!!,
        steps = expectedAppSettingDataTest.stepsStarting)
    val actualDataStore = dataStoreSource.appSetting()

    // THEN
    actualDataStore.test {
      val item = awaitItem()
      Truth.assertThat(item?.isLogin).isEqualTo(expectedAppSettingDataTest.isLogin)
      Truth.assertThat(item?.accessToken).isEqualTo(expectedAppSettingDataTest.accessToken!!)
      Truth.assertThat(item?.stepsStarting).isEqualTo(expectedAppSettingDataTest.stepsStarting)

      awaitComplete()
    }
  }
}
