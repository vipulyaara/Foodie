//package com.foodie.data.repo
//
//import com.foodie.data.data.db.EntityInserter
//import com.foodie.data.data.db.RoomTransactionRunner
//import com.foodie.data.data.db.daos.NearbyVenueEntryDao
//import com.foodie.data.feature.nearby.NearbyVenueDataSource
//import com.foodie.data.feature.nearby.NearbyVenueRepository
//import com.foodie.data.utils.BaseDatabaseTest
//import com.foodie.data.utils.insertFollowedShow
//import com.foodie.data.utils.insertShow
//import com.foodie.data.utils.show2Id
//import com.foodie.data.utils.showId
//import com.foodie.data.utils.testCoroutineDispatchers
//import kotlinx.coroutines.runBlocking
//import org.hamcrest.Matchers.`is`
//import org.junit.Assert.assertThat
//import org.junit.Test
//import org.mockito.ArgumentMatchers.any
//import org.mockito.Mockito.`when`
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.times
//import org.mockito.Mockito.verify
//
//class FollowedShowRepositoryTest : BaseDatabaseTest() {
//    private lateinit var nearbyVenueEntryDao: NearbyVenueEntryDao
//
//    private lateinit var nearbyVenueDataSource: NearbyVenueDataSource
//    private lateinit var nearbyVenueRepository: NearbyVenueRepository
//
//    private lateinit var repository: FollowedShowsRepository
//
//    override fun setup() {
//        super.setup()
//        // We'll assume that there's a show in the db
//        insertShow(db)
//
//        followShowsDao = db.followedShowsDao()
//
//        showRepository = mock(ShowRepository::class.java)
//        `when`(showRepository.needsUpdate(any(Long::class.java))).thenReturn(true)
//
//        traktDataSource = mock(FollowedShowsDataSource::class.java)
//
//        val txRunner = RoomTransactionRunner(db)
//
//        repository = FollowedShowsRepository(
//                testCoroutineDispatchers,
//                LocalFollowedShowsStore(txRunner, EntityInserter(txRunner),
//                        db.followedShowsDao(), db.showDao(), db.lastRequestDao()),
//                LocalShowStore(EntityInserter(txRunner), db.showDao(), db.lastRequestDao(), txRunner),
//                traktDataSource,
//                showRepository,
//                Provider { TraktAuthState.LOGGED_IN }
//        )
//    }
//
//    @Test
//    fun testSync() = runBlocking {
//        `when`(traktDataSource.getFollowedListId()).thenReturn(0)
//        `when`(traktDataSource.getListShows(0)).thenReturn(Success(listOf(followedShow1 to show)))
//
//        assertThat(repository.getFollowedShows(), `is`(listOf(followedShow1)))
//
//        // Verify that a show update was triggered
//        verify(showRepository, times(1)).updateShow(showId)
//    }
//
//    @Test
//    fun testSync_emptyResponse() = runBlocking {
//        insertFollowedShow(db)
//
//        `when`(traktDataSource.getFollowedListId()).thenReturn(0)
//        `when`(traktDataSource.getListShows(0)).thenReturn(Success(emptyList()))
//
//        assertThat(repository.getFollowedShows(), `is`(emptyList()))
//    }
//
//    @Test
//    fun testSync_responseDifferentShow() = runBlocking {
//        insertFollowedShow(db)
//
//        `when`(traktDataSource.getFollowedListId()).thenReturn(0)
//        `when`(traktDataSource.getListShows(0)).thenReturn(Success(listOf(followedShow2 to show2)))
//
//        assertThat(repository.getFollowedShows(), `is`(listOf(followedShow2)))
//
//        // Verify that a show update was triggered
//        verify(showRepository, times(1)).updateShow(show2Id)
//    }
//
//    @Test
//    fun testSync_pendingDelete() = runBlocking {
//        followShowsDao.insert(followedShow1PendingDelete)
//
//        // Return null for the list ID so that we disable syncing
//        `when`(traktDataSource.getFollowedListId()).thenReturn(null)
//
//        assertThat(repository.getFollowedShows(), `is`(emptyList()))
//    }
//
//    @Test
//    fun testSync_pendingAdd() = runBlocking {
//        followShowsDao.insert(followedShow1PendingUpload)
//
//        // Return null for the list ID so that we disable syncing
//        `when`(traktDataSource.getFollowedListId()).thenReturn(null)
//
//        assertThat(repository.getFollowedShows(), `is`(listOf(followedShow1)))
//    }
//}
