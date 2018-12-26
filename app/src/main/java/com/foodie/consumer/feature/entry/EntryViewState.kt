package com.foodie.consumer.feature.entry

import androidx.paging.PagedList
import com.foodie.consumer.feature.common.BaseViewState
import com.foodie.data.model.UiResource

data class EntryViewState<LI>(
    val uiResource: UiResource,
    val pagedList: PagedList<LI>?
) : BaseViewState()
