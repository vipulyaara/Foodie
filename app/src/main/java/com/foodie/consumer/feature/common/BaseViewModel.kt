package com.foodie.consumer.feature.common

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.facebook.stetho.common.LogUtil
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 22/10/18.
 *
 * Simple ViewModel which exposes a [CompositeDisposable] and [Job]
 * which are automatically cleared/stopped when the ViewModel is cleared.
 */
open class BaseViewModel<S : BaseViewState> : ViewModel(), IBaseViewModel {
    private val job = Job()

    private val logger: Logger by kodeinInstance.instance()
    override val disposables = CompositeDisposable()
    override val scope = CoroutineScope(Dispatchers.Main + job)
    val observableState = MediatorLiveData<S>()

    fun copy(s: S) {
        logger.d("state $s")
        observableState.postValue(s)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        job.cancel()
    }
}

fun <T, V> Observable<T>.execute(
    mapper: (T) -> V
): Disposable {
    return map {
        mapper(it)
    }
        .subscribe { LogUtil.d("it") }
}

