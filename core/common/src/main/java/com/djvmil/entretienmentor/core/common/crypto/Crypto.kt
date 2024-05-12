package com.djvmil.entretienmentor.core.common.crypto

interface Crypto {
  fun encrypt(text: ByteArray): ByteArray

  fun decrypt(encryptedData: ByteArray): ByteArray
}
