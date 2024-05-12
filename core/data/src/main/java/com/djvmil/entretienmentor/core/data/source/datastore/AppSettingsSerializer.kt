package com.djvmil.entretienmentor.core.data.source.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.djvmil.entretienmentor.core.common.crypto.Crypto
import com.djvmil.entretienmentor.core.data.source.datastore.model.APP_SETTING_DEFAULT
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.protobuf.ProtoBuf

@OptIn(ExperimentalSerializationApi::class)
class AppSettingsSerializer(private val crypto: Crypto) : Serializer<AppSettings> {
  override val defaultValue: AppSettings = APP_SETTING_DEFAULT

  override suspend fun readFrom(input: InputStream): AppSettings {
    return try {
      val encryptedInput = input.readBytes()

      val decryptedInput =
          if (encryptedInput.isNotEmpty()) {
            crypto.decrypt(encryptedInput)
          } else {
            encryptedInput
          }

      ProtoBuf.decodeFromByteArray(AppSettings.serializer(), decryptedInput)
    } catch (e: IOException) {
      throw CorruptionException("Error deserializing proto", e)
    } catch (e: SerializationException) {
      throw CorruptionException("Error deserializing proto", e)
    }
  }

  override suspend fun writeTo(t: AppSettings, output: OutputStream) {
    try {
      val byteArray = ProtoBuf.encodeToByteArray(AppSettings.serializer(), t)
      val encryptedBytes = crypto.encrypt(byteArray)

      withContext(Dispatchers.IO) { output.write(encryptedBytes) }
    } catch (e: IOException) {
      throw CorruptionException("Error serializing proto", e)
    } catch (e: SerializationException) {
      throw CorruptionException("Error serializing proto", e)
    }
  }
}
