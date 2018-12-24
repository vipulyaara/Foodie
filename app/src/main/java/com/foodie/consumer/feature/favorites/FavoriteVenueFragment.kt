package com.foodie.consumer.feature.favorites

import android.os.Bundle
import com.foodie.consumer.MainActivity
import com.foodie.consumer.R
import com.foodie.consumer.feature.entry.BaseEntryFragment
import com.foodie.consumer.feature.entry.EntryEpoxyController
import com.foodie.consumer.itemVenue
import com.foodie.consumer.ui.transition.ListItemSharedElementHelper
import com.foodie.data.entities.FavoriteEntryWithVenue
import com.foodie.data.feature.favorite.UpdateFavoriteVenues

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Fragment to host a list of favorite venues.
 */
class FavoriteVenueFragment :
    BaseEntryFragment<FavoriteEntryWithVenue, FavoriteVenueViewModel>(FavoriteVenueViewModel::class.java) {

    private val listItemSharedElementHelper by lazy(LazyThreadSafetyMode.NONE) {
        ListItemSharedElementHelper(binding.recyclerView) { it.findViewById(R.id.imageView) }
    }

    override fun onItemClicked(viewHolderId: Long, item: FavoriteEntryWithVenue) {
        if (activity is MainActivity) {
            (activity as MainActivity).launchDetailFragment(
                item.venue.venueId,
                listItemSharedElementHelper.createForItem(item, "quick")
            )
        }
    }

    override fun onItemFavorited(item: FavoriteEntryWithVenue) {
        viewModel.addToFavorites(item.venue.venueId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setParams(UpdateFavoriteVenues.Params())
        viewModel.refresh()
    }

    override fun createController(): EntryEpoxyController<FavoriteEntryWithVenue> {
        return object : EntryEpoxyController<FavoriteEntryWithVenue>() {
            override fun buildModels(items: MutableList<FavoriteEntryWithVenue>) {
                super.buildModels(items)
                items.forEach { entryWithVenue ->
                    if (entryWithVenue.venue.venueId.isNotEmpty()) {
                        itemVenue {
                            id(entryWithVenue.venue.venueId)
                            venue(entryWithVenue.venue)
                            onclickListener { model, parentView, clickedView, position ->
                                callbacks?.onItemClicked(model.id(), entryWithVenue)
                            }
                            onFavoriteListener { model, parentView, clickedView, position ->
                                callbacks?.onItemFavorited(entryWithVenue)
                            }
                        }
                    }
                }
            }
        }
    }
}
