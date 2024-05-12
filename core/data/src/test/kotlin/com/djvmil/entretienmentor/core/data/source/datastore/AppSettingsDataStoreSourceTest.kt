package com.djvmil.entretienmentor.core.data.source.datastore

import androidx.datastore.core.DataStoreFactory
import app.cash.turbine.test
import com.djvmil.entretienmentor.core.common.test.MainDispatcherRule
import com.djvmil.entretienmentor.core.data.common.FAKE_DATA.fakeAppSettingData
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.testDouble.FakeCrypto
import com.google.common.truth.Truth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AppSettingsDataStoreSourceTest {

  private lateinit var appSettingsSerializer: AppSettingsSerializer

  private lateinit var appSettingsDataStore: AppSettingsDataStoreSource

  @get:Rule val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

  @get:Rule val mainDispatcherRule = MainDispatcherRule()

  private fun TemporaryFolder.testAppSettingsDataStore(
      coroutineScope: CoroutineScope,
      appSettingsSerializer: AppSettingsSerializer,
  ) =
      DataStoreFactory.create(
          serializer = appSettingsSerializer,
          scope = coroutineScope,
      ) {
        newFile("app_settings_preferences_test.pb")
      }

  @Before
  fun setup() {
    val testScope = TestScope(mainDispatcherRule.testDispatcher)
    appSettingsSerializer = AppSettingsSerializer(FakeCrypto())

    appSettingsDataStore =
        AppSettingsDataStoreSourceImpl(
            tmpFolder.testAppSettingsDataStore(
                appSettingsSerializer = appSettingsSerializer, coroutineScope = testScope))
  }

  @Test
  fun checkDefaultValuesAppSettings() = runTest {
    // GIVEN
    val expectedAppSetting = AppSettings()

    // WHEN
    val res = appSettingsDataStore.appSetting()

    // THEN
    res.test { Truth.assertThat(awaitItem()).isEqualTo(expectedAppSetting) }
  }

  @Test
  fun check_update_appSetting() = runTest {
    // GIVEN
    val expectedAppSetting = fakeAppSettingData

    // WHEN
    appSettingsDataStore.update { expectedAppSetting }
    val actualAppSettings = appSettingsDataStore.appSetting()

    // THEN
    actualAppSettings.test { Truth.assertThat(awaitItem()).isEqualTo(expectedAppSetting) }
  }
}
