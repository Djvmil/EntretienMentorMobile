package com.djvmil.entretienmentor.core.common.crypto

import com.google.crypto.tink.Aead

class CryptoImpl(private val aead: Aead) : Crypto {
  override fun encrypt(text: ByteArray): ByteArray {
    return aead.encrypt(text, null)
  }

  override fun decrypt(encryptedData: ByteArray): ByteArray {
    return if (encryptedData.isNotEmpty()) {
      aead.decrypt(encryptedData, null)
    } else {
      encryptedData
    }
  }
}
