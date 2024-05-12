package com.djvmil.entretienmentor.core.data.model

import com.djvmil.entretienmentor.core.data.model.auth.RequestExceptionResult
import kotlinx.serialization.Serializable

@Serializable
sealed interface ApiOperation<T> {
  data class Success<T>(val data: T) : ApiOperation<T>

  data class Failure<T>(val exception: RequestExceptionResult) : ApiOperation<T>
}

inline fun <T, R> ApiOperation<T>.mapSuccess(transform: (T) -> R): ApiOperation<R> {
  return when (this) {
    is ApiOperation.Success -> ApiOperation.Success(transform(data))
    is ApiOperation.Failure -> ApiOperation.Failure(exception)
  }
}

inline fun <T> ApiOperation<T>.onFailure(block: (RequestExceptionResult) -> Unit): ApiOperation<T> {
  if (this is ApiOperation.Failure) block(exception)
  return this
}

inline fun <T> ApiOperation<T>.onSuccess(block: (T) -> Unit): ApiOperation<T> {
  if (this is ApiOperation.Success) block(data)
  return this
}

inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
  return try {
    ApiOperation.Success(data = apiCall())
  } catch (e: RequestExceptionResult) {
    ApiOperation.Failure(exception = e)
  }
}
