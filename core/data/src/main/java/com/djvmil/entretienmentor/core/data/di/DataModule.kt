package com.djvmil.entretienmentor.core.data.di

import android.util.Log
import androidx.datastore.core.DataStoreFactory
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.djvmil.entretienmentor.EMDatabaseSource
import com.djvmil.entretienmentor.core.common.di.commonModule
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestExceptionResult
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.model.auth.ResponseAuthData
import com.djvmil.entretienmentor.core.data.repository.AuthRepository
import com.djvmil.entretienmentor.core.data.repository.AuthRepositoryImpl
import com.djvmil.entretienmentor.core.data.repository.CommunityRepository
import com.djvmil.entretienmentor.core.data.repository.CommunityRepositoryImpl
import com.djvmil.entretienmentor.core.data.repository.DataSourceRepositoryImpl
import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsDataStoreSource
import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsDataStoreSourceImpl
import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsDataStoreSourceImpl.Companion.DATASTORE_FILE
import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsSerializer
import com.djvmil.entretienmentor.core.data.source.db.dao.CommunityDao
import com.djvmil.entretienmentor.core.data.source.db.dao.CommunityDaoImpl
import com.djvmil.entretienmentor.core.data.source.db.util.DATABASE_NAME
import com.djvmil.entretienmentor.core.data.source.remote.api.ApiService
import com.djvmil.entretienmentor.core.data.source.remote.api.ApiServiceImpl
import com.djvmil.entretienmentor.core.data.source.remote.util.CustomHttpLogger
import com.djvmil.entretienmentor.core.data.source.remote.util.Route
import com.djvmil.entretienmentor.core.data.source.remote.util.Route.REFRESH_TOKEN_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.encodedPath
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import java.io.File
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
  includes(commonModule)

  single { Json { ignoreUnknownKeys = true } }
  single<CommunityRepository> {
    CommunityRepositoryImpl(api = get(), dao = get(), dataStore = get())
  }
  single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
  single<CommunityDao> { CommunityDaoImpl(get()) }

  singleOf(::DataSourceRepositoryImpl)

  single {
    DataStoreFactory.create(
        produceFile = { File(androidContext().filesDir, "datastore/$DATASTORE_FILE") },
        serializer = AppSettingsSerializer(get()))
  }

  single<AppSettingsDataStoreSource> { AppSettingsDataStoreSourceImpl(get()) }

  single {
    val driver: SqlDriver =
        AndroidSqliteDriver(EMDatabaseSource.Schema, androidContext(), DATABASE_NAME)
    EMDatabaseSource(driver)
  }

  single<ApiService> { ApiServiceImpl(httpClient = get()) }
  single { provideKtorClient(get()) }
}

fun provideKtorClient(dataStoreSource: AppSettingsDataStoreSource): HttpClient {
  return HttpClient(Android) {
    install(Logging) {
      logger = CustomHttpLogger()
      level = LogLevel.ALL
      logger =
          object : Logger {
            override fun log(message: String) {
              Log.i("HttpClient", message)
            }
          }
    }

    install(ResponseObserver) {
      onResponse { response -> Log.i("HTTP status", response.status.value.toString()) }
    }

    HttpResponseValidator {
      handleResponseExceptionWithRequest { exception, request ->
        when (exception) {
          is JsonConvertException -> {
            val body = request.call.response.body<RequestResult<String>>() as? RequestResult<String>
            throw RequestExceptionResult(body)
          }
          else -> {
            val body = RequestResult<String>(code = 503, message = exception.message)
            throw RequestExceptionResult(body)
          }
        }
      }
    }

    install(Auth) {
      bearer {
        sendWithoutRequest { request ->
          request.url.encodedPath.startsWith("/login") ||
              request.url.encodedPath.startsWith("/register")
        }
        loadTokens {
          Log.d(
              "HttpResponseValidator",
              "loadTokens: ",
          )
          val tokenValue = dataStoreSource.getAccessTokenForAuth() ?: ""
          BearerTokens(accessToken = tokenValue, refreshToken = tokenValue)
        }
        refreshTokens {
          Log.d("HttpResponseValidator", "refreshTokens: ")
          val token =
              client
                  .post {
                    markAsRefreshTokenRequest()
                    url(Route.BASE_URL + Route.URI_URL + REFRESH_TOKEN_URL)
                    AuthRequest(username = "admin@em.com", password = "1234")
                  }
                  .body<RequestResult<ResponseAuthData>>()

          dataStoreSource.setAccessToken(token.data?.accessToken.toString())

          BearerTokens(token.data?.accessToken!!, token.data?.accessToken!!)
        }
      }
    }

    install(HttpTimeout) {
      requestTimeoutMillis = 10_000
      connectTimeoutMillis = 10_000
      socketTimeoutMillis = 10_000
    }
    install(DefaultRequest) {
      url(host = Route.BASE_URL, path = Route.URI_URL)
      header(HttpHeaders.ContentType, ContentType.Application.Json)
      header(HttpHeaders.Accept, ContentType.Application.Json)
    }
    install(ContentNegotiation) {
      json(
          Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            coerceInputValues = true
          })
    }
  }
}
