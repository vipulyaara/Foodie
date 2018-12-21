package com.foodie.data.extensions

import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.internal.functions.Functions

fun <T> Maybe<T>.emptySubscribe() = subscribe(Functions.emptyConsumer(), Functions.ERROR_CONSUMER)
fun <T> Single<T>.emptySubscribe() = subscribe(Functions.emptyConsumer(), Functions.ERROR_CONSUMER)
fun <T> Flowable<T>.emptySubscribe() = subscribe(Functions.emptyConsumer(), Functions.ERROR_CONSUMER)
fun <T> Observable<T>.emptySubscribe() = subscribe(Functions.emptyConsumer(), Functions.ERROR_CONSUMER)
fun Completable.emptySubscribe() = subscribe(Functions.EMPTY_ACTION, Functions.ERROR_CONSUMER)

fun <T> Observable<T>.toFlowable() = toFlowable(BackpressureStrategy.LATEST)!!

fun <T> emptyFlowableList() = Flowable.just(emptyList<T>())
