package com.foodie.consumer.feature.nearby

import android.os.Bundle
import android.view.View
import com.foodie.consumer.ItemVenueBindingModel_
import com.foodie.consumer.extensions.roundOffTo
import com.foodie.consumer.feature.entry.EntryEpoxyController
import com.foodie.consumer.feature.entry.EntryFragment
import com.foodie.consumer.feature.entry.EntryViewState
import com.foodie.consumer.feature.main.MainActivity
import com.foodie.consumer.ui.transition.ListItemSharedElementHelper
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.extensions.observeK
import com.foodie.data.feature.location.LocationProvider
import com.foodie.data.feature.nearby.UpdateNearbyVenues
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Implementation of [EntryFragment] to host a list of nearby venues.
 */
class NearbyVenueFragment :
    EntryFragment<NearbyEntryWithVenue, EntryViewState<NearbyEntryWithVenue>, NearbyVenueViewModel>(
        NearbyVenueViewModel::class.java
    ) {

    private val locationProvider: LocationProvider by kodeinInstance.instance()

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

    override fun onItemFavorited(venueId: String, markFavorite: Boolean) {
        if (markFavorite) {
            viewModel.addToFavorites(venueId = venueId)
        } else {
            viewModel.removeFromFavorites(venueId = venueId)
        }
    }

    override fun createController(): EntryEpoxyController<NearbyEntryWithVenue> {
        return object : EntryEpoxyController<NearbyEntryWithVenue>() {
            override fun buildItemModel(
                position: Int,
                item: NearbyEntryWithVenue
            ): ItemVenueBindingModel_ {
                return ItemVenueBindingModel_()
                    .id(item.generateStableId())
                    .venue(item.venue)
                    .isFavorite(item.favorite?.isNotEmpty())
                    .isBlocked(item.blockedVenues?.isNotEmpty())
                    .onclickListener(View.OnClickListener { callbacks?.onItemClicked(0, item) })
                    .onFavoriteListener { model, _, _, _ ->
                        callbacks?.onItemFavorited(model.venue().venueId, !model.isFavorite)
                    }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationProvider.locationLiveData.observeK(this) { resource ->
            // delay to prevent flashes of progress bar on screen
            controller.isLoading = true
            resource?.let { location ->
                locationProvider.stopLocationUpdates()
                viewModel.setParams(
                    UpdateNearbyVenues.Params(
                        "${location.latitude.roundOffTo(2)}" +
                                ",${location.longitude.roundOffTo(2)}"
                    )
                )
                viewModel.refresh()
            }
        }

        if (locationProvider.locationLiveData.value == null) {
            locationProvider.fetchCoarseLocation(activity!!)
        }
    }
}
