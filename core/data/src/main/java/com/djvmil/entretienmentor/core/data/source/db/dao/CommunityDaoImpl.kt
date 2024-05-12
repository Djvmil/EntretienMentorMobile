package com.djvmil.entretienmentor.core.data.source.db.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.djvmil.entretienmentor.EMDatabaseSource
import com.djvmil.entretienmentor.core.data.source.db.util.THROW_QUERY_INSERT_COMMUNITY_EXCEPTION
import com.djvmil.sqldelight.CommunityTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class CommunityDaoImpl(db: EMDatabaseSource) : CommunityDao {

  private val query = db.community_tableQueries

  override fun getAll(): Flow<List<CommunityTable>> =
      query.getAll().asFlow().mapToList(Dispatchers.IO)

  override fun getById(id: Int): Flow<CommunityTable> =
      query.getById(id.toLong()).asFlow().mapToOne(Dispatchers.IO)

  override suspend fun update(community: CommunityTable) {
    query.update(
        id = community.id,
        name = community.name,
        description = community.description,
        dateCreated = community.dateCreated,
        dateUpdated = community.dateUpdated)
  }

  override suspend fun insert(community: CommunityTable) {
    query.insert(
        id = if (community.id == -1L) null else community.id,
        name = community.name,
        description = community.description,
        dateCreated = community.dateCreated,
        dateUpdated = community.dateUpdated)
  }

  override suspend fun insertAll(communities: List<CommunityTable>) {
    query.transaction {
      afterRollback { throw Exception(THROW_QUERY_INSERT_COMMUNITY_EXCEPTION) }

      communities.forEach { community ->
        query.insert(
            id = if (community.id == -1L) null else community.id,
            name = community.name,
            description = community.description,
            dateCreated = community.dateCreated,
            dateUpdated = community.dateUpdated)
      }
    }
  }

  override suspend fun delete(id: Long) {
    query.delete(id)
  }

  override suspend fun deleteAll() {
    query.deleteAll()
  }
}
