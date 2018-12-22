package com.foodie.data.feature.detail

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.db.DatabaseTransactionRunner
import com.foodie.data.data.db.EntityInserter
import com.foodie.data.data.db.daos.VenueDetailDao
import com.foodie.data.entities.VenueDetail
import io.reactivex.Flowable
import org.kodein.di.generic.instance

class LocalVenueDetailStore {
    private val entityInserter: EntityInserter by kodeinInstance.instance()
    private val dao: VenueDetailDao by kodeinInstance.instance()
    private val transactionRunner: DatabaseTransactionRunner by kodeinInstance.instance()

    fun getContentDetail(venueId: String) = dao.getVenueDetailWithId(venueId)

    fun observeVenueDetail(venueId: String): Flowable<VenueDetail> =
        dao.getVenueDetailWithIdFlowable(venueId)

    fun saveVenueDetail(venueDetail: VenueDetail) =
        entityInserter.insertOrUpdate(dao, venueDetail)
}
