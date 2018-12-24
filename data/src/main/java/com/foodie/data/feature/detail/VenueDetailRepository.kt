package com.foodie.data.feature.detail

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.data.Logger
import com.foodie.data.model.Success
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.kodein.di.generic.instance

class VenueDetailRepository {
    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    private val localStore: LocalVenueDetailStore by kodeinInstance.instance()
    private val dataSource: VenueDetailDataSource by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()

    fun observeContentDetail(contentId: String) = localStore.observeVenueDetail(contentId)

    suspend fun updateContentDetail(contentId: String) {
        val apiResult = coroutineScope {
            async(dispatchers.io) {
                dataSource.getVenueDetail(contentId)
            }
        }

        val remote = apiResult.await().let {
            logger.d("venue detail response $it")
            (it as? Success)?.data
        }

        remote?.let { localStore.saveVenueDetail(remote) }
    }
}
