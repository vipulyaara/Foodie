package com.foodie.consumer.feature.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodie.consumer.R
import com.foodie.consumer.databinding.FragmentVenueDetailBinding
import com.foodie.consumer.feature.common.DataBindingFragment
import com.foodie.data.extensions.observeK

/**
 * @author Vipul Kumar; dated 22/12/18.
 *
 * Hosts venue detail.
 * @see [VenueDetailViewModel] and [VenueDetailController].
 */
class VenueDetailFragment : DataBindingFragment<FragmentVenueDetailBinding>(
    R.layout.fragment_venue_detail
) {

    private val controller = VenueDetailController(object : VenueDetailController.Callbacks {
        override fun onItemBlocked(venueId: String, markBlocked: Boolean) {
            if (markBlocked) {
                viewModel.addToBlockedVenues(venueId)
            } else {
                viewModel.removeFromBlockedVenues(venueId)
            }
        }

        override fun onItemFavorited(venueId: String, markFavorite: Boolean) {
            if (markFavorite) {
                viewModel.addToFavorites(venueId)
            } else {
                viewModel.removeFromFavorites(venueId)
            }
        }
    })

    private val venueId by lazy { arguments?.getString("venueId") }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(VenueDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initUi()
    }

    private fun initObservers() {
        viewModel.observableState.observeK(this) {
            controller.setData(it)
        }

        viewModel.setParams(venueId ?: "")
        viewModel.updateContentDetail()
    }

    private fun initUi() {
        binding.fabBack.setOnClickListener { activity?.onBackPressed() }
        binding.rvVenueDetail.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setController(controller)
        }
    }
}
