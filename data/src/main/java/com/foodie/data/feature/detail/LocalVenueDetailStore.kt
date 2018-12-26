package com.foodie.data.feature.detail

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.db.EntityInserter
import com.foodie.data.data.db.daos.VenueDetailDao
import com.foodie.data.entities.VenueDetail
import com.foodie.data.feature.common.LocalStore
import io.reactivex.Flowable
import org.kodein.di.generic.instance

class LocalVenueDetailStore : LocalStore() {
    private val entityInserter: EntityInserter by kodeinInstance.instance()
    private val dao: VenueDetailDao by kodeinInstance.instance()

    fun observeVenueDetail(venueId: String): Flowable<VenueDetail> =
        dao.getVenueDetailWithIdFlowable(venueId)

    fun saveVenueDetail(venueDetail: VenueDetail) =
        entityInserter.insertOrUpdate(dao, venueDetail)
}
