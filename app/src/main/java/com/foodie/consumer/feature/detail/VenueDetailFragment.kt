package com.foodie.consumer.feature.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
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
        override fun onItemBlocked(venueId: String) {
            Toast.makeText(context, "Blocked", Toast.LENGTH_SHORT).show()
            viewModel.removeFromFavorites(venueId)
        }

        override fun onItemFavorited(venueId: String) {
            Toast.makeText(context, "Favorited", Toast.LENGTH_SHORT).show()
            viewModel.addToFavorites(venueId)
        }
    })

    private val venueId by lazy { arguments?.getString("venueId") }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(VenueDetailViewModel::class.java)
    }

    private val result = MediatorLiveData<VenueDetailViewState>()

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
    }

    private fun initUi() {
        binding.rvVenueDetail.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setController(controller)
        }
    }
}
