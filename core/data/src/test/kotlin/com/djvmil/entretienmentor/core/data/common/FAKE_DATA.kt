package com.djvmil.entretienmentor.core.data.common

import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.model.auth.ResponseAuthData
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppTheme
import com.djvmil.entretienmentor.core.data.source.datastore.model.StepsStarting
import com.djvmil.entretienmentor.core.data.source.db.util.DEFAULT_ID
import com.djvmil.entretienmentor.core.data.source.remote.model.UserApiModel
import com.djvmil.sqldelight.CommunityTable

object FAKE_DATA {
  val INVALID_RESPONSE = "remote/invalid_response_file.json".readFile()
  val EMPTY_RESPONSE = "remote/empty_response_file.json".readFile()
  val LOGIN_RESPONSE_SUCCESS = "remote/login_response_success_file.json".readFile()
  val LOGIN_RESPONSE_UNAUTHORIZED = "remote/login_response_unauthorized_file.json".readFile()

  val fakeAuthRequest = AuthRequest(username = "admin@em.com", password = "1234")
  private const val ACCESS_TOKEN = "ashfbhsfhshfksh.kjshfkjhjkshjkfhkshjkf.ioeu82h3dfbm"
  val fakeRequestResult =
      RequestResult(
          code = 200,
          message = "success login",
          data =
              ResponseAuthData(
                  accessToken = ACCESS_TOKEN,
                  user =
                      UserApiModel(
                          firstname = "admin",
                          lastname = "admin",
                          username = "admn@em.com",
                      )),
          timestamp = "123454872")

  val fakeAppSettingData =
      AppSettings(
          theme = AppTheme.Dark,
          isLogin = true,
          stepsStarting = StepsStarting.ON_AUTH_PAGE,
          accessToken = ACCESS_TOKEN)

  val fakeCommunityData =
      CommunityTable(
          name = "Community Name",
          description = "description description description",
          dateCreated = "12/04/2024 22:04",
          dateUpdated = "12/04/2024 22:04",
          id = DEFAULT_ID,
      )
}
