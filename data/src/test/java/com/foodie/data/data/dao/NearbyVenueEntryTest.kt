//package com.foodie.data.data.dao
//
//import android.database.sqlite.SQLiteConstraintException
//import com.foodie.data.data.db.daos.NearbyVenueEntryDao
//import com.foodie.data.utils.BaseDatabaseTest
//import com.foodie.data.utils.insertShow
//import com.foodie.data.utils.nearbyVenueEntry1
//import com.foodie.data.utils.showId
//import org.hamcrest.CoreMatchers.`is`
//import org.hamcrest.CoreMatchers.nullValue
//import org.junit.Assert.assertThat
//import org.junit.Test
//
//class NearbyVenueEntryTest : BaseDatabaseTest() {
//    private lateinit var nearbyVenueEntryDao: NearbyVenueEntryDao
//
//    override fun setup() {
//        super.setup()
//
//        nearbyVenueEntryDao = db.episodeWatchesDao()
//
//        // We'll assume that there's a show, season and episodes in the db
//        insertShow(db)
//        insertSeason(db)
//        insertEpisodes(db)
//    }
//
//    @Test
//    fun insert() {
//        nearbyVenueEntryDao.insert(nearbyVenueEntry1)
//        assertThat(nearbyVenueEntryDao.(nearbyVenueEntry1Id), `is`(nearbyVenueEntry1))
//    }
//
//    @Test(expected = SQLiteConstraintException::class)
//    fun insert_withSameTraktId() {
//        nearbyVenueEntryDao.insert(nearbyVenueEntry1)
//        // Make a copy with a 0 id
//        val copy = nearbyVenueEntry1.copy(id = 0)
//        nearbyVenueEntryDao.insert(copy)
//    }
//
//    @Test
//    fun fetchEntries_WithPendingSendAction() {
//        nearbyVenueEntryDao.insertAll(nearbyVenueEntry1, episodeWatch2PendingSend)
//        assertThat(nearbyVenueEntryDao.entriesForShowIdWithSendPendingActions(showId),
//                `is`(listOf(episodeWatch2PendingSend))
//        )
//    }
//
//    @Test
//    fun fetchEntries_WithPendingDeleteAction() {
//        nearbyVenueEntryDao.insertAll(nearbyVenueEntry1, episodeWatch2PendingDelete)
//        assertThat(nearbyVenueEntryDao.entriesForShowIdWithDeletePendingActions(showId),
//                `is`(listOf(episodeWatch2PendingDelete))
//        )
//    }
//
//    @Test
//    fun delete() {
//        nearbyVenueEntryDao.insert(nearbyVenueEntry1)
//        nearbyVenueEntryDao.delete(nearbyVenueEntry1)
//        assertThat(nearbyVenueEntryDao.entryWithId(nearbyVenueEntry1Id), `is`(nullValue()))
//    }
//
//    @Test
//    fun deleteEpisode_deletesWatch() {
//        nearbyVenueEntryDao.insert(nearbyVenueEntry1)
//        // Now delete episode
//        deleteEpisodes(db)
//        assertThat(nearbyVenueEntryDao.entryWithId(nearbyVenueEntry1Id), `is`(nullValue()))
//    }
//}
