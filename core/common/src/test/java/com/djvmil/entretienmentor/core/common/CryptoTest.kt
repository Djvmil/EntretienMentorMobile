package com.djvmil.entretienmentor.core.common

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.djvmil.entretienmentor.core.common.crypto.Crypto
import com.djvmil.entretienmentor.core.common.crypto.CryptoImpl
import com.djvmil.entretienmentor.core.common.test.MainDispatcherRule
import com.google.common.truth.Truth
import com.google.crypto.tink.Aead
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.AesGcmKeyManager
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import kotlin.test.Test
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CryptoTest : KoinTest {

  @get:Rule val mainDispatcherRule = MainDispatcherRule()
  private lateinit var crypto: Crypto

  @Before
  fun setup() {
    val appContext = ApplicationProvider.getApplicationContext<Context>()

    AeadConfig.register()
    val aeed =
        AndroidKeysetManager.Builder()
            .withSharedPref(appContext, KEY_SET_NAME, PREFERENCE_FILE)
            .withKeyTemplate(AesGcmKeyManager.aes256GcmTemplate())
            .withMasterKeyUri(MASTER_KEY_URI)
            .build()
            .keysetHandle
            .getPrimitive(Aead::class.java)

    crypto = CryptoImpl(aeed)

    //  startKoin { modules(commonModule) }
  }

  @Test
  fun crypt_data_string_Assert() {
    // GIVEN
    val expectedString = "String Encryption"
    val expectedStringByteArray = expectedString.encodeToByteArray()

    // WHEN
    val stringEncryptByteArray = crypto.encrypt(expectedStringByteArray)
    val actualStringByteArray = crypto.decrypt(stringEncryptByteArray)
    val actualString = actualStringByteArray.decodeToString()

    // THEN
    Truth.assertThat(actualString).isEqualTo(expectedString)
  }

  @After
  fun tearDown() {
    // stopKoin()
  }
}
