package com.foodie.consumer.feature.nearby

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.foodie.consumer.ItemFavoriteVenueBindingModel_
import com.foodie.consumer.MainActivity
import com.foodie.consumer.feature.entry.BaseEntryFragment
import com.foodie.consumer.feature.entry.EntryEpoxyController
import com.foodie.consumer.feature.favorites.FavoriteVenueViewModel
import com.foodie.consumer.itemNearbyHeader
import com.foodie.consumer.itemVenue
import com.foodie.consumer.ui.epoxy.carousel
import com.foodie.consumer.ui.epoxy.withModelsFrom
import com.foodie.consumer.ui.transition.ListItemSharedElementHelper
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.feature.nearby.UpdateNearbyVenues

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Implementation of [BaseEntryFragment] to host a list of nearby venues.
 */
class NearbyVenueFragment :
    BaseEntryFragment<NearbyEntryWithVenue, NearbyVenueViewModel>(NearbyVenueViewModel::class.java) {

    private val favoriteViewModel by lazy {
        ViewModelProviders.of(this).get(FavoriteVenueViewModel::class.java)
    }

    private val listItemSharedElementHelper by lazy(LazyThreadSafetyMode.NONE) {
        ListItemSharedElementHelper(binding.recyclerView)
    }

    override fun onItemClicked(viewHolderId: Long, item: NearbyEntryWithVenue) {
        if (activity is MainActivity) {
            (activity as MainActivity).launchDetailFragment(
                item.venue.venueId,
                listItemSharedElementHelper.createForId(viewHolderId, "quick")
            )
        }
    }

    override fun onItemFavorited(item: NearbyEntryWithVenue) {
        favoriteViewModel.addToFavorites(item.venue.venueId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setParams(UpdateNearbyVenues.Params("40.7484,-73.9857"))
        viewModel.refresh()
    }

    override fun createController(): EntryEpoxyController<NearbyEntryWithVenue> {
        return object : EntryEpoxyController<NearbyEntryWithVenue>() {
            override fun buildModels(items: MutableList<NearbyEntryWithVenue>) {
                super.buildModels(items)
                itemNearbyHeader {
                    id("header")
                }

                items.forEach { nearbyEntryWithVenue ->
                    if (items.indexOf(nearbyEntryWithVenue) == 2) {
                        carousel {
                            id("favorites")
                            withModelsFrom(items) {
                                ItemFavoriteVenueBindingModel_()
                                    .id("favorite" + it.venue.venueId)
                                    .venue(it.venue)
                            }
                        }
                    }
                    if (nearbyEntryWithVenue.venue.venueId.isNotEmpty()) {
                        itemVenue {
                            id(nearbyEntryWithVenue.venue.venueId)
                            venue(nearbyEntryWithVenue.venue)
                            onclickListener { model, _, _, _ ->
                                callbacks?.onItemClicked(model.id(), nearbyEntryWithVenue)
                            }
                            onFavoriteListener { _, _, _, _ ->
                                callbacks?.onItemFavorited(nearbyEntryWithVenue)
                            }
                        }
                    }
                }
            }
        }
    }
}
