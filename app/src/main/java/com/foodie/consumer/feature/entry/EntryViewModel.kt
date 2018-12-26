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

abstract class EntryViewModel<LI : EntryWithVenue<out Entry>, S : EntryViewState<LI>>(
    private val pageSize: Int = 10,
    entryViewState: S
) : BaseViewModel<S>(entryViewState) {
    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()

    val messages = BehaviorSubject.create<UiResource>()

    var dataSource: DataSource.Factory<Int, LI>? = null
        set(value) {
            list = RxPagedListBuilder<Int, LI>(value!!, pageListConfig)
                .setBoundaryCallback(object : PagedList.BoundaryCallback<LI>() {
                    override fun onItemAtEndLoaded(itemAtEnd: LI) {
                        onListScrolledToEnd()
                    }
                })
//                .setNotifyScheduler(AndroidSchedulers
//                    .from(EpoxyAsyncUtil.getAsyncBackgroundHandler().looper))
                .buildFlowable(BackpressureStrategy.LATEST)
                .distinctUntilChanged()
        }

    private val pageListConfig = PagedList.Config.Builder().run {
        setPageSize(pageSize)
        setPrefetchDistance(pageSize)
        setEnablePlaceholders(false)
        build()
    }

    lateinit var list: Flowable<PagedList<LI>>

    val viewState = LiveDataReactiveStreams
        .fromPublisher(messages.toFlowable())

    fun onListScrolledToEnd() {
        scope.launch {
            sendMessage(UiResource(Status.LOADING_MORE))
            try {
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
