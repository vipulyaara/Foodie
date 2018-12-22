package com.foodie.consumer.feature.entry

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.foodie.consumer.feature.common.BaseViewModel
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.data.Logger
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.extensions.distinctUntilChanged
import com.foodie.data.extensions.toFlowable
import com.foodie.data.model.Status
import com.foodie.data.model.UiResource
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

abstract class EntryViewModel<LI : NearbyEntryWithVenue, P : Any>(
    private val pageSize: Int = 50
) : BaseViewModel() {
    private val schedulers: AppRxSchedulers by kodeinInstance.instance()
    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    abstract val dataSource: DataSource.Factory<Int, LI>
    private val logger: Logger by kodeinInstance.instance()
    protected lateinit var params: P

    private val messages = BehaviorSubject.create<UiResource>()

    val liveList by lazy(mode = LazyThreadSafetyMode.NONE) {
        LivePagedListBuilder<Int, LI>(
            dataSource,
            PagedList.Config.Builder().run {
                setPageSize(pageSize)
                setPrefetchDistance(pageSize)
                setEnablePlaceholders(false)
                build()
            }
        ).setBoundaryCallback(object : PagedList.BoundaryCallback<LI>() {
            override fun onItemAtEndLoaded(itemAtEnd: LI) {
                logger.d("Entry onItemAtEndLoaded ${itemAtEnd.relations[0]}")
                onListScrolledToEnd(params)
            }
        }).build().distinctUntilChanged()
    }

    val viewState = LiveDataReactiveStreams
        .fromPublisher(messages.toFlowable())

    fun onListScrolledToEnd(params: P) {
        logger.d("onListScrolledToEnd $params")
        scope.launch {
            sendMessage(UiResource(Status.LOADING_MORE))
            try {
                callLoadMore(params)
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun refresh() {
        logger.d("$this refresh $params")
        scope.launch {
            try {
                callRefresh(params)
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected open suspend fun callRefresh(params: P) = Unit

    protected open suspend fun callLoadMore(params: P) = Unit

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
