package com.djvmil.entretienmentor.core.data.repository

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.CommunityDataModel
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
  suspend fun getFromApi()

  fun getAll(): Flow<ResultEM<List<CommunityDataModel>, ErrorEM>>

  fun get(id: Int): Flow<ResultEM<CommunityDataModel, ErrorEM>>

  suspend fun update(community: CommunityDataModel)

  suspend fun insertAll(communities: List<CommunityDataModel>)

  suspend fun deleteAll()
}
