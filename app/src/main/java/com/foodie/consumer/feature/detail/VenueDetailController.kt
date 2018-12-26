package com.foodie.consumer.feature.detail

import com.airbnb.epoxy.TypedEpoxyController
import com.foodie.consumer.ItemFavoriteVenueBindingModel_
import com.foodie.consumer.itemAttribute
import com.foodie.consumer.itemEmptyState
import com.foodie.consumer.itemFooter
import com.foodie.consumer.itemLoader
import com.foodie.consumer.itemRowHeader
import com.foodie.consumer.itemSeperator
import com.foodie.consumer.itemTip
import com.foodie.consumer.itemVenueDetail
import com.foodie.consumer.ui.epoxy.carousel
import com.foodie.consumer.ui.epoxy.withModelsFrom
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.entities.letEmpty
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 22/12/18.
 *
 * Controller to lay out items for [VenueDetailFragment].
 */
class VenueDetailController constructor(
    private val callbacks: Callbacks
) : TypedEpoxyController<VenueDetailViewState>() {
    private val logger: Logger by kodeinInstance.instance()

    override fun buildModels(data: VenueDetailViewState?) {
        if (data?.isLoading == true && data.venueDetail.isEmpty()) {
            itemLoader { id("loader") }
        }

        if (data?.isLoading == false && data.venueDetail.isEmpty()) {
            itemEmptyState { id("empty-state") }
        }

        data?.venueDetail?.letEmpty { venueDetail ->
            itemVenueDetail {
                id(venueDetail.venueId)
                venue(venueDetail)
                isFavorite(data.isFavorite)
                isBlocked(data.isBlocked)
                onFavoriteListener { model, _, _, _ ->
                    callbacks.onItemFavorited(model.venue().venueId, !model.isFavorite)
                }
                onBlockListener { model, _, _, _ ->
                    callbacks.onItemBlocked(model.venue().venueId, !model.isBlocked)
                }
            }

            // add tips
            itemTip {
                id("tip")
                tip(venueDetail.tip)
            }

            // add attributes
            data.venueDetail.attributes?.forEach {
                itemAttribute {
                    id("it")
                    key(it.key)
                    value(it.value)
                }
            }

            itemSeperator { id("separator") }

            if (data.favoriteVenues.isNotEmpty()) {
                itemRowHeader {
                    id("favorite header")
                    text("Favorites")
                }
                carousel {
                    id("Favorites")
                    withModelsFrom(data.favoriteVenues) {
                        ItemFavoriteVenueBindingModel_()
                            .id(it.venue.venueId)
                            .venue(it.venue)
                    }
                }
            }

            itemFooter { id("footer") }
        }
    }

    interface Callbacks {
        fun onItemBlocked(venueId: String, markBlocked: Boolean)
        fun onItemFavorited(venueId: String, markFavorite: Boolean)
    }
}
