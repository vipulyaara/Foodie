package com.foodie.consumer.feature.favorites

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodie.consumer.ItemVenueBindingModel_
import com.foodie.consumer.R
import com.foodie.consumer.databinding.FragmentFavoriteVenueBinding
import com.foodie.consumer.feature.common.DataBindingFragment
import com.foodie.consumer.feature.entry.EntryEpoxyController
import com.foodie.data.entities.FavoriteEntryWithVenue

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Fragment to host a list of favorite venues.
 */
class FavoriteVenueFragment : DataBindingFragment<FragmentFavoriteVenueBinding>(
    R.layout.fragment_favorite_venue
) {
    private val controller = createController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setController(controller)
        }
    }

    private fun createController(): EntryEpoxyController<FavoriteEntryWithVenue> {
        return object : EntryEpoxyController<FavoriteEntryWithVenue>() {
            override fun buildItemModel(item: FavoriteEntryWithVenue): ItemVenueBindingModel_ {
                return ItemVenueBindingModel_()
            }
        }
    }
}
