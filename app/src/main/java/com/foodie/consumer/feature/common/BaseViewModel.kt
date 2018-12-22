package com.foodie.consumer.feature.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * @author Vipul Kumar; dated 22/10/18.
 *
 * Simple ViewModel which exposes a [CompositeDisposable] and [Job]
 * which are automatically cleared/stopped when the ViewModel is cleared.
 */
open class BaseViewModel<S : BaseViewState> : ViewModel(), IBaseViewModel {
    private val job = Job()

    override val disposables = CompositeDisposable()
    override val scope = CoroutineScope(Dispatchers.Main + job)
    val observableState = MutableLiveData<S>()

    fun copy(s: S) {
        observableState.value = s
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        job.cancel()
    }
}
