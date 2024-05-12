package com.djvmil.entretienmentor.core.data.source.remote.api

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.ApiOperation
import com.djvmil.entretienmentor.core.data.model.auth.AuthRequest
import com.djvmil.entretienmentor.core.data.model.auth.RequestResult
import com.djvmil.entretienmentor.core.data.model.auth.ResponseAuthData
import com.djvmil.entretienmentor.core.data.source.remote.model.CommunityApiModel
import kotlinx.coroutines.flow.Flow

interface ApiService {
  suspend fun login(body: AuthRequest): ApiOperation<RequestResult<ResponseAuthData>>

  suspend fun register(body: AuthRequest): ApiOperation<RequestResult<String>>

  fun getCommunities(): Flow<ResultEM<List<CommunityApiModel>, ErrorEM>>
  // fun getComments(): Flow<ResultEM<List<CommentApiModel>, ErrorEM>>
}
