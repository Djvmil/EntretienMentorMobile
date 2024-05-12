package com.djvmil.entretienmentor.core.domain

import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.model.auth.ResponseAuthData
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppSettings
import com.djvmil.entretienmentor.core.data.source.datastore.model.AppTheme
import com.djvmil.entretienmentor.core.data.source.datastore.model.StepsStarting
import com.djvmil.entretienmentor.core.data.source.remote.model.UserApiModel

object FAKE_DATA {

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
  val fakeRequestRegisterResult =
      RequestResult(code = 200, message = "success login", data = "", timestamp = "123454872")

  val fakeAppSettingData =
      AppSettings(
          theme = AppTheme.Dark,
          isLogin = true,
          stepsStarting = StepsStarting.ON_AUTH_PAGE,
          accessToken = ACCESS_TOKEN)
}
