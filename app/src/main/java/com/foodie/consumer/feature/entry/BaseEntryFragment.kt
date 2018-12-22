package com.foodie.consumer.feature.entry

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodie.consumer.R
import com.foodie.consumer.databinding.FragmentNearbyVenueBinding
import com.foodie.consumer.feature.common.DataBindingFragment
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.extensions.observeK
import com.foodie.data.model.Status

/**
 * @author Vipul Kumar;
 *
 * Parent for fragments that contain paginated list of items.
 * */
@SuppressLint("ValidFragment")
abstract class BaseEntryFragment<
        VM : EntryViewModel<NearbyEntryWithVenue, out Any>>(
    private val vmClass: Class<VM>
) : DataBindingFragment<FragmentNearbyVenueBinding>(
    R.layout.fragment_nearby_venue
) {

    protected lateinit var viewModel: VM

    private lateinit var controller: EntryEpoxyController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(vmClass)

        controller = createController()
        controller.callbacks = object : EntryEpoxyController.Callbacks<NearbyEntryWithVenue> {
            override fun onItemClicked(item: NearbyEntryWithVenue) {
                this@BaseEntryFragment.onItemClicked(item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvNearby.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setController(controller)
        }

        viewModel.liveList.observeK(this) {
            controller.setList(it)
        }

        viewModel.viewState.observeK(this) { uiResource ->
            uiResource?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        controller.isLoading = false
                    }
                    Status.ERROR -> {
                        controller.isLoading = false
                    }
                    Status.LOADING_MORE -> controller.isLoading = true
                }
            }
        }
    }

    abstract fun onItemClicked(item: NearbyEntryWithVenue)

    open fun createController(): EntryEpoxyController {
        return EntryEpoxyController()
    }
}
