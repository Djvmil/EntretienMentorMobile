package com.djvmil.entretienmentor.core.data.source.datastore.testDouble

import com.djvmil.entretienmentor.core.common.crypto.Crypto

class FakeCrypto : Crypto {

  override fun encrypt(text: ByteArray): ByteArray {
    return text
  }

  override fun decrypt(encryptedData: ByteArray): ByteArray {
    return encryptedData
  }
}
