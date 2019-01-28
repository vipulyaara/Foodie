package com.foodie.data.feature.detail

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.feature.common.Repository
import com.foodie.data.model.Success
import org.kodein.di.generic.instance

class VenueDetailRepository : Repository() {
    private val localStore: LocalVenueDetailStore by kodeinInstance.instance()
    private val dataSource: VenueDetailDataSource by kodeinInstance.instance()

    fun observeContentDetail(contentId: String) = localStore.observeVenueDetail(contentId)

    suspend fun updateContentDetail(contentId: String) {

        val result = dataSource.getVenueDetail(contentId)

        val remote = result.let {
            (it as? Success)?.data
        }

        remote?.let { localStore.saveVenueDetail(remote) }
    }
}
