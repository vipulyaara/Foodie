package com.foodie.data.data.dao

import android.database.sqlite.SQLiteConstraintException
import com.foodie.data.data.db.daos.VenueDao
import com.foodie.data.utils.BaseDatabaseTest
import com.foodie.data.utils.insertShow
import com.foodie.data.utils.showId
import com.foodie.data.utils.venue1
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.assertThat
import org.junit.Test

class VenuesTest : BaseDatabaseTest() {
    private lateinit var venueDao: VenueDao

    override fun setup() {
        super.setup()
        venueDao = db.venueDao()
        // We'll assume that there's a venue1 in db
        insertShow(db)
    }

    @Test
    fun insert() {
        venueDao.insert(venue1)
        assertThat(venueDao.getVenueWithId(venue1.venueId), `is`(venue1))
    }

    @Test(expected = SQLiteConstraintException::class)
    fun insert_withId() {
        venueDao.insert(venue1)
        // Make a copy with a 0 id
        val copy = venue1.copy(id = showId)
        venueDao.insert(copy)
    }

    @Test
    fun delete() {
        venueDao.insert(venue1)
        venueDao.delete(venue1)
        assertThat(venueDao.getVenueWithId(venue1.venueId), `is`(nullValue()))
    }
}
