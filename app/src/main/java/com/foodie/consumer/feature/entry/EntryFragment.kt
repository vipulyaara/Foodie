package com.foodie.consumer.feature.entry

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.toLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodie.consumer.R
import com.foodie.consumer.databinding.FragmentListBinding
import com.foodie.consumer.feature.common.DataBindingFragment
import com.foodie.consumer.feature.main.MainActivity
import com.foodie.data.entities.Entry
import com.foodie.data.entities.EntryWithVenue
import com.foodie.data.extensions.observeK
import com.foodie.data.model.Status

/**
 * @author Vipul Kumar;
 *
 * Generic implementation for fragments that contain paginated list of items.
 *
 * The same fragment can be used for all kinds of lists that are paginated,
 * simply by providing different viewModel and data model types.
 *
 */
@SuppressLint("ValidFragment")
abstract class EntryFragment<LI : EntryWithVenue<out Entry>,
        S : EntryViewState<LI>,
        VM : EntryViewModel<LI, S>>(
    private val vmClass: Class<VM>
) : DataBindingFragment<FragmentListBinding>(
    R.layout.fragment_list
) {

    protected lateinit var viewModel: VM

    /** controller for the list items.
     * child fragments can provide different implementation for items to print on screen */
    protected lateinit var controller: EntryEpoxyController<LI>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(vmClass)

        controller = createController()
        controller.callbacks = object : EntryEpoxyController.Callbacks<LI> {
            override fun onItemFavorited(venueId: String, markFavorite: Boolean) {
                this@EntryFragment.onItemFavorited(venueId, markFavorite)
            }

            override fun onItemClicked(viewHolderId: Long, item: LI) {
                this@EntryFragment.onItemClicked(viewHolderId, item)
            }

            override fun onSeeFavoriteClicked() {
                if (activity is MainActivity) {
                    (activity as MainActivity).launchFavoritesFragment()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setController(controller)
        }

        viewModel.list.toLiveData().observeK(this) {
            logger.d("EntryFragment pagedList updated")
            controller.submitList(it)
        }

        /** toggle loading state */
        viewModel.viewState.observeK(this) { uiResource ->
            uiResource?.let {
                when (it.status) {
                    Status.SUCCESS -> controller.isLoading = false
                    Status.ERROR -> controller.isLoading = false
                    Status.LOADING_MORE -> controller.isLoading = true
                }
            }
        }
    }

    abstract fun onItemClicked(viewHolderId: Long, item: LI)

    abstract fun onItemFavorited(venueId: String, markFavorite: Boolean)

    abstract fun createController(): EntryEpoxyController<LI>
}
