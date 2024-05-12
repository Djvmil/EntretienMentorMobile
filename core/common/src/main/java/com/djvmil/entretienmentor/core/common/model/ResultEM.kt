package com.djvmil.entretienmentor.core.common.model

/*
V = Value
E = Error
*/
sealed class ResultEM<out V, out E> {

  object Loading : ResultEM<Nothing, Nothing>()

  data class Success<out V : Any?>(val value: V) : ResultEM<V, Nothing>()

  data class Failure<out E : Any?>(val error: E) : ResultEM<Nothing, E>()
}

inline fun <V, reified E : Any?> ResultEM<V, E>.fold(
    loading: (Unit) -> Unit,
    success: (V) -> Unit,
    failure: (E) -> Unit
) =
    when (this) {
      is ResultEM.Loading -> loading(Unit)
      is ResultEM.Success -> success(value)
      is ResultEM.Failure -> failure(error)
    }

inline fun <V, U, reified E : Any?> ResultEM<V, E>.map(transform: (V) -> U): ResultEM<U, E> {

  return when (this) {
    ResultEM.Loading -> ResultEM.Loading
    is ResultEM.Success -> ResultEM.Success(transform(value))
    is ResultEM.Failure -> ResultEM.Failure(error)
  }
}
