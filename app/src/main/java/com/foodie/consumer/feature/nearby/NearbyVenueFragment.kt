package com.foodie.consumer.feature.nearby

import android.os.Bundle
import android.view.View
import com.foodie.consumer.ItemVenueBindingModel_
import com.foodie.consumer.MainActivity
import com.foodie.consumer.feature.entry.EntryEpoxyController
import com.foodie.consumer.feature.entry.EntryFragment
import com.foodie.consumer.feature.entry.EntryViewState
import com.foodie.consumer.ui.transition.ListItemSharedElementHelper
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.feature.nearby.UpdateNearbyVenues

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Implementation of [EntryFragment] to host a list of nearby venues.
 */
class NearbyVenueFragment :
    EntryFragment<NearbyEntryWithVenue, EntryViewState<NearbyEntryWithVenue>, NearbyVenueViewModel>(
        NearbyVenueViewModel::class.java
    ) {
    override fun createController(): EntryEpoxyController<NearbyEntryWithVenue> {
        return object : EntryEpoxyController<NearbyEntryWithVenue>() {
            override fun buildItemModel(item: NearbyEntryWithVenue): ItemVenueBindingModel_ {
                return ItemVenueBindingModel_()
                    .id(item.generateStableId())
                    .venue(item.venue)
                    .isFavorite(item.favorite?.isNotEmpty())
                    .onclickListener(View.OnClickListener { callbacks?.onItemClicked(0, item) })
                    .onFavoriteListener { _, _, _, _ ->
                        callbacks?.onItemFavorited(item)
                    }
            }
        }
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
        viewModel.addToFavorites(item.venue.venueId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setParams(UpdateNearbyVenues.Params("40.7484,-73.9857"))
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}
