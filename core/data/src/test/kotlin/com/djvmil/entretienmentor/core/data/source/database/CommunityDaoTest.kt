package com.djvmil.entretienmentor.core.data.source.database

import app.cash.turbine.test
import com.djvmil.entretienmentor.core.common.test.MainDispatcherRule
import com.djvmil.entretienmentor.core.data.common.FAKE_DATA
import com.djvmil.entretienmentor.core.data.source.database.testDouble.FakeDatabaseModule
import com.djvmil.entretienmentor.core.data.source.db.dao.CommunityDao
import com.djvmil.entretienmentor.core.data.source.db.dao.CommunityDaoImpl
import com.google.common.truth.Truth
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule

class CommunityDaoTest {
  @get:Rule val mainDispatcherRule = MainDispatcherRule()
  private val database = FakeDatabaseModule.provideInMemoryDatabaseInstance()
  private lateinit var communityDao: CommunityDao

  @Before
  fun setUp() {
    communityDao = CommunityDaoImpl(database)
  }

  @Test
  fun test_insert_select_community_in_db() = runTest {
    // GIVEN
    val communityExpected = FAKE_DATA.fakeCommunityData.copy(id = 1)

    // WHEN
    communityDao.insert(communityExpected)
    val communityActual = communityDao.getById(1)

    // THEN
    communityActual.test { Truth.assertThat(awaitItem()).isEqualTo(communityExpected.copy(id = 1)) }
  }

  @Test
  fun test_insert_all_select_all_community_in_db() = runTest {
    // GIVEN
    val communitiesExpected =
        listOf(
            FAKE_DATA.fakeCommunityData.copy(id = 1),
            FAKE_DATA.fakeCommunityData.copy(id = 2),
            FAKE_DATA.fakeCommunityData.copy(id = 3),
            FAKE_DATA.fakeCommunityData.copy(id = 4),
        )

    // WHEN
    communityDao.insertAll(communitiesExpected)
    val communitiesActual = communityDao.getAll()

    // THEN
    communitiesActual.test { Truth.assertThat(awaitItem()).isEqualTo(communitiesExpected) }
  }

  @Test
  fun test_update_community_in_db() = runTest {
    // GIVEN
    val communityInsert = FAKE_DATA.fakeCommunityData.copy(id = 1)
    val communityUpdated =
        communityInsert.copy(name = FAKE_DATA.fakeCommunityData.name.plus(" Updated"))

    // WHEN
    communityDao.insert(communityInsert)
    communityDao.update(communityUpdated)

    // mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
    val communityActual = communityDao.getById(communityUpdated.id.toInt())

    // THEN
    communityActual.test { Truth.assertThat(awaitItem()).isEqualTo(communityUpdated) }
  }

  @Test
  fun test_delete_community_in_db() = runTest {
    // GIVEN
    val communityInsert = FAKE_DATA.fakeCommunityData.copy(id = 1)

    // WHEN
    communityDao.insert(communityInsert)
    val communityActual = communityDao.getById(communityInsert.id.toInt())
    communityActual.test { Truth.assertThat(awaitItem()).isEqualTo(communityInsert) }
    communityDao.delete(communityInsert.id)

    // THEN
    communityDao.getById(communityInsert.id.toInt()).test {
      Truth.assertThat(awaitError()).isInstanceOf(NullPointerException::class.java)
    }
  }
}
