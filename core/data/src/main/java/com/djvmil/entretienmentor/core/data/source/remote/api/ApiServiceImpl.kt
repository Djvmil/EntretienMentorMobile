package com.djvmil.entretienmentor.core.data.source.remote.api

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.ApiOperation
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.model.auth.ResponseAuthData
import com.djvmil.entretienmentor.core.data.model.safeApiCall
import com.djvmil.entretienmentor.core.data.source.remote.model.CommunityApiModel
import com.djvmil.entretienmentor.core.data.source.remote.util.Route.COMMUNITY_URL
import com.djvmil.entretienmentor.core.data.source.remote.util.Route.LOGIN_URL
import com.djvmil.entretienmentor.core.data.source.remote.util.Route.REGISTER_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiServiceImpl(val httpClient: HttpClient) : ApiService {
  override suspend fun login(body: AuthRequest): ApiOperation<RequestResult<ResponseAuthData>> {
    return safeApiCall {
      httpClient
          .post {
            url(LOGIN_URL)
            setBody(body)
            contentType(ContentType.Application.Json)
          }
          .body()
    }
  }

  override suspend fun register(body: AuthRequest): ApiOperation<RequestResult<String>> {
    return safeApiCall {
      httpClient
          .post {
            url(REGISTER_URL)
            setBody(body)
            contentType(ContentType.Application.Json)
          }
          .body()
    }
  }

  override fun getCommunities(): Flow<ResultEM<List<CommunityApiModel>, ErrorEM>> = flow {
    emit(ResultEM.Loading)
    try {
      emit(ResultEM.Success(httpClient.get(COMMUNITY_URL).body()))
    } catch (ex: Exception) {
      ex.printStackTrace()
      emit(ResultEM.Failure(ErrorEM(message = ex.message ?: "", throwable = ex)))
    }
  }
}
