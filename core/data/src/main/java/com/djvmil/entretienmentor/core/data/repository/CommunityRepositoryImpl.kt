package com.djvmil.entretienmentor.core.data.repository

import com.djvmil.entretienmentor.core.common.model.ErrorEM
import com.djvmil.entretienmentor.core.common.model.ResultEM
import com.djvmil.entretienmentor.core.data.model.CommunityDataModel
import com.djvmil.entretienmentor.core.data.model.toData
import com.djvmil.entretienmentor.core.data.model.toDatabase
import com.djvmil.entretienmentor.core.data.source.datastore.AppSettingsDataStoreSource
import com.djvmil.entretienmentor.core.data.source.db.dao.CommunityDao
import com.djvmil.entretienmentor.core.data.source.remote.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class CommunityRepositoryImpl(
    val api: ApiService,
    val dao: CommunityDao,
    val dataStore: AppSettingsDataStoreSource
) : CommunityRepository {
  override suspend fun getFromApi() {
    api.getCommunities().collect { result ->
      if (result is ResultEM.Success) dao.insertAll(result.value.map { it.toData().toDatabase() })
    }
  }

  override fun getAll(): Flow<ResultEM<List<CommunityDataModel>, ErrorEM>> {
    return dao.getAll()
        .map { list -> list.map { it.toData() } }
        .map { ResultEM.Success(value = it) }
        .catch { throwable -> ResultEM.Failure(ErrorEM(throwable = throwable)) }
  }

  override fun get(id: Int): Flow<ResultEM<CommunityDataModel, ErrorEM>> =
      dao.getById(id)
          .map { it.toData() }
          .map { ResultEM.Success(value = it) }
          .catch { throwable -> ResultEM.Failure(ErrorEM(throwable = throwable)) }

  override suspend fun update(community: CommunityDataModel) {
    dao.update(community.toDatabase())
  }

  override suspend fun insertAll(communities: List<CommunityDataModel>) =
      dao.insertAll(communities.map { model -> model.toDatabase() })

  override suspend fun deleteAll() {
    dao.deleteAll()
  }
}
