package com.foodie.consumer.feature.favorites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.TypedEpoxyController
import com.foodie.consumer.ItemVenueBindingModel_
import com.foodie.consumer.R
import com.foodie.consumer.databinding.FragmentFavoriteVenueBinding
import com.foodie.consumer.feature.common.DataBindingFragment
import com.foodie.consumer.feature.main.MainActivity
import com.foodie.consumer.itemEmptyState
import com.foodie.consumer.itemVenue
import com.foodie.data.extensions.observeK

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Fragment to host a list of favorite venues.
 */
class FavoriteVenueFragment : DataBindingFragment<FragmentFavoriteVenueBinding>(
    R.layout.fragment_favorite_venue
) {
    private val controller = FavoriteVenueController(object : FavoriteVenueController.Callbacks {
        override fun onItemClicked(venueId: String) {
            if (activity is MainActivity) {
                (activity as MainActivity).launchDetailFragment(
                    venueId, null
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
    })

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(FavoriteVenueViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setController(controller)
        }

        viewModel.observableState.observeK(this) {
            controller.setData(it)
        }

        binding.fabBack.setOnClickListener { activity?.onBackPressed() }
    }

    class FavoriteVenueController constructor(
        private val callbacks: Callbacks
    ) :
        TypedEpoxyController<FavoriteVenueViewState>() {
        override fun buildModels(data: FavoriteVenueViewState?) {
            if (data?.favoriteVenues.isNullOrEmpty()) {
                itemEmptyState { id("empty-state") }
            }
            data?.favoriteVenues?.forEach { item ->
                itemVenue {
                    id(item.generateStableId())
                    venue(item.venue)
                    isFavorite(true)
                    onclickListener { model, _, _, _ -> callbacks.onItemClicked(model.venue().venueId) }
                    onFavoriteListener { model, _, _, _ ->
                        callbacks.onItemFavorited(model.venue().venueId, !model.isFavorite)
                    }
                }
            }
            ItemVenueBindingModel_()
        }

        interface Callbacks {
            fun onItemClicked(venueId: String)
            fun onItemFavorited(venueId: String, markFavorite: Boolean)
        }
    }
}
