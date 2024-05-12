package com.djvmil.entretienmentor.core.data.source.remote.testDouble

import com.djvmil.entretienmentor.core.data.common.ApiResponseType
import com.djvmil.entretienmentor.core.data.common.FAKE_DATA
import com.djvmil.entretienmentor.core.data.model.auth.RequestExceptionResult
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.source.remote.api.ApiService
import com.djvmil.entretienmentor.core.data.source.remote.api.ApiServiceImpl
import com.djvmil.entretienmentor.core.data.source.remote.util.Route
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiServiceFake {
  companion object {
    private val Url.hostWithPortIfRequired: String
      get() = if (port == protocol.defaultPort) host else hostWithPort

    private val Url.fullUrl: String
      get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

    fun build(type: ApiResponseType): ApiService {
      val client =
          HttpClient(MockEngine) {
            install(ContentNegotiation) {
              json(
                  Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    coerceInputValues = true
                  })
            }

            HttpResponseValidator {
              handleResponseExceptionWithRequest { exception, request ->
                when (exception) {
                  is JsonConvertException -> {
                    val body =
                        request.call.response.body<RequestResult<String>>()
                            as? RequestResult<String>
                    throw RequestExceptionResult(body)
                  }
                  else -> {
                    val body = RequestResult<String>(code = 503, message = exception.message)
                    throw RequestExceptionResult(body)
                  }
                }
              }
            }
            engine {
              addHandler { request ->
                println("request.url.fullUrl = ${request.url.fullUrl}")
                if (request.url.fullUrl.endsWith(Route.LOGIN_URL)) {
                  val responseHeaders =
                      headersOf("Content-Type" to listOf("application/json", "charset=utf-8"))

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
          }
      return ApiServiceImpl(client)
    }
  }
}
