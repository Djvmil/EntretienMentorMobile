package com.djvmil.entretienmentor.core.data.source.datastore

import androidx.datastore.core.CorruptionException
import com.djvmil.entretienmentor.core.common.test.MainDispatcherRule
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.testDouble.FakeCrypto
import com.google.common.truth.Truth
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class AppSettingsSerializerTest {
  private val appSettingsSerializer = AppSettingsSerializer(FakeCrypto())

  @get:Rule val mainDispatcherRule = MainDispatcherRule()

  @Test
  fun defaultAppSettingsPreferences_Assert() {
    Truth.assertThat(appSettingsSerializer.defaultValue).isEqualTo(AppSettings())
  }

  @Test
  fun writingAndReadingAppSettingsPreferences_outputsCorrectValue() = runTest {
    // GIVEN
    val expectedAppSettings = appSettingsSerializer.defaultValue
    val outputStream = ByteArrayOutputStream()

    // WHEN
    appSettingsSerializer.writeTo(expectedAppSettings, outputStream)
    val inputStream = ByteArrayInputStream(outputStream.toByteArray())

    val actualAppSettings = appSettingsSerializer.readFrom(inputStream)

    // THEN
    Truth.assertThat(actualAppSettings).isEqualTo(expectedAppSettings)
  }

  @Test(expected = CorruptionException::class)
  fun readingInvalidAppSettingsPreferences_throwsCorruptionException() = runTest {
    appSettingsSerializer.readFrom(ByteArrayInputStream(byteArrayOf(0)))
  }
}
