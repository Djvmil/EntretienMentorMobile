package com.djvmil.entretienmentor.core.data.source.remote.testDouble

import com.djvmil.entretienmentor.core.data.common.ApiResponseType
import com.djvmil.entretienmentor.core.data.common.FAKE_DATA
import com.djvmil.entretienmentor.core.data.source.remote.util.Route
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort

object MockEngineLogin {
  private val Url.hostWithPortIfRequired: String
    get() = if (port == protocol.defaultPort) host else hostWithPort

  private val Url.fullUrl: String
    get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

  var type = ApiResponseType.RESPONSE_DATA_VALID

  val engine = MockEngine { request ->
    if (request.url.fullUrl.endsWith(Route.LOGIN_URL)) {
      val responseHeaders = headersOf("Content-Type" to listOf("application/json", "charset=utf-8"))

      when (type) {
        /** define what the response should be based on which [type] is passed */
        ApiResponseType.RESPONSE_DATA_VALID -> {
          respond(
              content = FAKE_DATA.LOGIN_RESPONSE_SUCCESS,
              status = HttpStatusCode.OK,
              headers = responseHeaders)
        }
        ApiResponseType.RESPONSE_UNAUTHORIZED -> {
          respond(
              content = FAKE_DATA.LOGIN_RESPONSE_UNAUTHORIZED,
              status = HttpStatusCode.Unauthorized,
              headers = responseHeaders)
        }
        ApiResponseType.RESPONSE_DATA_INVALID -> {
          respond(
              content = FAKE_DATA.INVALID_RESPONSE,
              status = HttpStatusCode.OK,
              headers = responseHeaders)
        }
        ApiResponseType.RESPONSE_DATA_EMPTY -> {
          respond(
              content = FAKE_DATA.EMPTY_RESPONSE,
              status = HttpStatusCode.OK,
              headers = responseHeaders)
        }
      }
    } else {
      throw Exception("Stub!")
    }
  }
}
