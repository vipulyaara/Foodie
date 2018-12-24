package com.foodie.consumer.feature.entry

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodie.consumer.R
import com.foodie.consumer.databinding.FragmentListBinding
import com.foodie.consumer.feature.common.DataBindingFragment
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
 * simply by providing different viewModels into this.
 *
 */
@SuppressLint("ValidFragment")
abstract class BaseEntryFragment<LI : EntryWithVenue<out Entry>,
        VM : EntryViewModel<LI>>(
    private val vmClass: Class<VM>
) : DataBindingFragment<FragmentListBinding>(
    R.layout.fragment_list
) {

    protected lateinit var viewModel: VM

    /** generic controller for the list items.
     * fragments can provide different items to print on screen */
    private lateinit var controller: EntryEpoxyController<LI>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(vmClass)

        controller = createController()
        controller.callbacks = object : EntryEpoxyController.Callbacks<LI> {
            override fun onItemFavorited(item: LI) {
                this@BaseEntryFragment.onItemFavorited(item)
            }

            override fun onItemClicked(viewHolderId: Long, item: LI) {
                this@BaseEntryFragment.onItemClicked(viewHolderId, item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setController(controller)
        }

        /** list is updated by [VM] */
        viewModel.liveList.observeK(this) {
            it?.toMutableList()?.apply {
                controller.setList(this)
            }
        }

        /** toggle loading state */
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

    abstract fun onItemClicked(viewHolderId: Long, item: LI)

    abstract fun onItemFavorited(item: LI)

    open fun createController(): EntryEpoxyController<LI> {
        return EntryEpoxyController()
    }
}
