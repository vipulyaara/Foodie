package com.foodie.consumer.ui.transition

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.foodie.data.entities.Entry
import com.foodie.data.entities.EntryWithVenue

class ListItemSharedElementHelper(
    private val recyclerView: RecyclerView,
    private val viewFinder: (View) -> View = { it }
) {
    fun createForItem(
        item: EntryWithVenue<out Entry>,
        transitionName: String
    ): SharedElementHelper {
        return createForId(item.generateStableId(), transitionName)
    }

    fun createForId(viewHolderId: Long, transitionName: String): SharedElementHelper {
        val sharedElementHelper = SharedElementHelper()
        addSharedElement(sharedElementHelper, viewHolderId, transitionName)
        return sharedElementHelper
    }

    fun createForItems(items: List<EntryWithVenue<out Entry>>?): SharedElementHelper {
        val sharedElementHelper = SharedElementHelper()
        items?.forEach {
            val homepage = it.venue.venueId
            addSharedElement(sharedElementHelper, it.generateStableId(), homepage)
        }
        return sharedElementHelper
    }

    private fun addSharedElement(
        helper: SharedElementHelper,
        viewHolderId: Long,
        transitionName: String
    ) {
        recyclerView.findViewHolderForItemId(viewHolderId)?.also {
            helper.addSharedElement(viewFinder(it.itemView), transitionName)
        }
    }
}
