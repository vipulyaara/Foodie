package com.foodie.consumer.config

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.extensions.toFlowable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.kodein.di.generic.instance

class RxLoadingCounter {
    private var loaders = 0
    private val loadingState = BehaviorSubject.createDefault(loaders)
    private val logger: Logger by kodeinInstance.instance()

    val observable: Observable<Boolean>
        get() = loadingState.map { it > 0 }

    val flowable by lazy(LazyThreadSafetyMode.NONE) { observable.toFlowable() }

    fun addLoader() {
        logger.d("loader added")
        loadingState.onNext(++loaders)
    }

    fun removeLoader() {
        logger.d("loader removed")
        loadingState.onNext(--loaders)
    }
}
