package com.foodie.data.extensions

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

fun <T> Observable<T>.toFlowable() = toFlowable(BackpressureStrategy.LATEST)!!

fun <T> emptyFlowableList() = Flowable.just(emptyList<T>())
