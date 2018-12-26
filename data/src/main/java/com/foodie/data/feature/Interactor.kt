package com.foodie.data.feature

import androidx.paging.DataSource
import com.foodie.data.extensions.toFlowable
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface Interactor<in P> {
    val dispatcher: CoroutineDispatcher
    suspend operator fun invoke(executeParams: P)
}

interface PagingInteractor<T> {
    fun dataSourceFactory(): DataSource.Factory<Int, T>
}

abstract class SubjectInteractor<P : Any, EP, T> : Interactor<EP> {
    private var disposable: Disposable? = null
    private val subject: BehaviorSubject<T> = BehaviorSubject.create()

    val loading = BehaviorSubject.createDefault(false)

    private lateinit var params: P

    fun throwError(exception: Exception) {
        subject.onError(exception)
    }

    fun setParams(params: P) {
        this.params = params
        setSource(createObservable(params))
    }

    fun getParams() = params

    final override suspend fun invoke(executeParams: EP) {
        loading.onNext(true)
        execute(params, executeParams)
        loading.onNext(false)
    }

    protected abstract suspend fun execute(params: P, executeParams: EP)

    protected abstract fun createObservable(params: P): Flowable<T>

    fun clear() {
        disposable?.dispose()
        disposable = null
    }

    fun observe(): Flowable<T> = subject.toFlowable()

    private fun setSource(source: Flowable<T>) {
        disposable?.dispose()
        disposable = source.subscribe(subject::onNext, subject::onError)
    }
}

fun <P> CoroutineScope.launchInteractor(interactor: Interactor<P>, param: P): Job {
    return launch(context = interactor.dispatcher, block = { interactor(param) })
}

fun CoroutineScope.launchInteractor(interactor: Interactor<Unit>) =
    launchInteractor(interactor, Unit)
