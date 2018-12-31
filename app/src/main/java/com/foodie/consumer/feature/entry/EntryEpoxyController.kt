package com.foodie.consumer.feature.entry

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.foodie.consumer.ItemVenueBindingModel_
import com.foodie.consumer.itemEmptyState
import com.foodie.consumer.itemLoader
import com.foodie.consumer.itemNearbyHeader
import com.foodie.consumer.itemSeeFavorites
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.entities.Entry
import com.foodie.data.entities.EntryWithVenue
import org.kodein.di.generic.instance

abstract class EntryEpoxyController<LI : EntryWithVenue<out Entry>> :
    PagedListEpoxyController<LI>() {
    private val logger: Logger by kodeinInstance.instance()

    override fun buildItemModel(currentPosition: Int, item: LI?): EpoxyModel<*> {
        return if (item != null) {
            buildItemModel(currentPosition, item)
        } else {
            buildItemPlaceholder(currentPosition)
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        itemNearbyHeader { id("nearby-header") }

        itemSeeFavorites {
            id("see-favorites")
            seeFavoritesListener { _, _, _, _ ->
                callbacks?.onSeeFavoriteClicked()
            }
        }

        super.addModels(models.filterNot { model ->
            if (model is ItemVenueBindingModel_) {
                model.isBlocked
            } else {
                false
            }
        })

        if (isLoading) itemLoader { id("loader") }

        if (models.isNullOrEmpty() && !isLoading) {
            itemEmptyState { id("empty-state") }
        }
    }

    internal var callbacks: Callbacks<LI>? = null

    var isLoading = true
        set(value) {
            if (value != field) {
                field = value
                requestModelBuild()
            }
        }

    interface Callbacks<in LI> {
        fun onItemClicked(viewHolderId: Long, item: LI)
        fun onItemFavorited(venueId: String, markFavorite: Boolean)
        fun onSeeFavoriteClicked()
    }

    abstract fun buildItemModel(position: Int, item: LI): ItemVenueBindingModel_

    protected open fun buildItemPlaceholder(index: Int): ItemVenueBindingModel_ {
        return ItemVenueBindingModel_()
            .id("placeholder_$index")
    }
}
