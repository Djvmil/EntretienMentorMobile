package com.djvmil.entretienmentor.core.common.model

data class ErrorEM(val message: String = "", val code: Int = 0, val throwable: Throwable? = null) {
  fun isMessage(): Boolean = message.isNotEmpty()

  fun isThrowable(): Boolean = throwable != null
}
