package com.foodie.consumer.feature.entry

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.foodie.consumer.feature.common.BaseViewModel
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.entities.Entry
import com.foodie.data.entities.EntryWithVenue
import com.foodie.data.extensions.toFlowable
import com.foodie.data.model.Status
import com.foodie.data.model.UiResource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar;
 *
 * Generic viewModel that is used with paginated entries.
 */
const val entryPageSize = 10

abstract class EntryViewModel<LI : EntryWithVenue<out Entry>, S : EntryViewState<LI>>(
    private val pageSize: Int = entryPageSize,
    entryViewState: S
) : BaseViewModel<S>(entryViewState) {
    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    private val messages = BehaviorSubject.create<UiResource>()

    private var dataSource: DataSource.Factory<Int, LI>? = null

    private val pageListConfig = PagedList.Config.Builder().run {
        setPageSize(pageSize * 3)
//        setPrefetchDistance(pageSize)
        setEnablePlaceholders(false)
        build()
    }

    /** builds pagedList based on the data source */
    fun build(dataSource: DataSource.Factory<Int, LI>) {
        this.dataSource = dataSource
        list = RxPagedListBuilder<Int, LI>(dataSource, pageListConfig)
            .setBoundaryCallback(object : PagedList.BoundaryCallback<LI>() {
                override fun onItemAtEndLoaded(itemAtEnd: LI) {
                    logger.d("Item End $itemAtEnd")
                    onListScrolledToEnd()
                }
            })
            .buildFlowable(BackpressureStrategy.LATEST)
            .distinctUntilChanged()
    }

    lateinit var list: Flowable<PagedList<LI>>

    val viewState = LiveDataReactiveStreams
        .fromPublisher(messages.toFlowable())

    /** called when last item is fetched */
    fun onListScrolledToEnd() {
        scope.launch {
            sendMessage(UiResource(Status.LOADING_MORE))
            try {
                logger.d("BaseViewModel loadMore")
                callLoadMore()
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun refresh() {
        scope.launch {
            try {
                callRefresh()
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected open suspend fun callRefresh() = Unit

    protected open suspend fun callLoadMore() = Unit

    private fun onError(t: Throwable) {
        logger.e(t)
        sendMessage(UiResource(Status.ERROR, t.localizedMessage))
    }

    private fun onSuccess() {
        sendMessage(UiResource(Status.SUCCESS))
    }

    private fun sendMessage(uiResource: UiResource) {
        messages.onNext(uiResource)
    }
}
