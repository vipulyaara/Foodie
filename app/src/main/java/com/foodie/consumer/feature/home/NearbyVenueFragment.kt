package com.foodie.consumer.feature.home

import android.os.Bundle
import com.foodie.consumer.feature.entry.BaseEntryFragment
import com.foodie.consumer.feature.entry.EntryEpoxyController
import com.foodie.consumer.itemVenue
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.feature.nearby.UpdateNearbyVenues

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Fragment to host a list of nearby venues.
 */
class NearbyVenueFragment :
    BaseEntryFragment<NearbyVenueViewModel>(NearbyVenueViewModel::class.java) {

    override fun onItemClicked(item: NearbyEntryWithVenue) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setParams(UpdateNearbyVenues.Params("40.7484,-73.9857"))
        viewModel.refresh()
    }

    override fun createController(): EntryEpoxyController {
        return object : EntryEpoxyController() {
            override fun buildModels(items: MutableList<NearbyEntryWithVenue>) {
                super.buildModels(items)
//                itemNearbyHeader {
//                    id("header")
//                }

                items.forEach {
                    //                    if (items.indexOf(it) == 2) {
//                        carousel {
//                            id("favorites")
//                            withModelsFrom(items) {
//                                ItemFavoriteVenueBindingModel_()
//                                    .id("f" + it.venue.venueId)
//                                    .venue(it.venue)
//                            }
//                        }
//                    }
                    itemVenue {
                        id(it.venue.venueId)
                        venue(it.venue)
                    }
                }
            }
        }
    }
}
