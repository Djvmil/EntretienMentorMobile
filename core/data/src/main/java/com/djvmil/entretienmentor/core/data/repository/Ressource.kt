package com.djvmil.entretienmentor.core.data.repository

sealed class Ressource<out T> {
  object Loading : Ressource<Nothing>()

  data class Success<out T>(val value: T) : Ressource<T>()

  data class Failure(val error: Throwable) : Ressource<Nothing>()

  val isLoading
    get() = this is Loading

  val isFail
    get() = this is Failure

  val valueOrNull
    get() = (this as? Success)?.value
}
