package com.foodie.data.extensions

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

suspend fun <A, B> Collection<A>.parallelMap(
    block: suspend (A) -> B
) = coroutineScope {
    map {
        async { block(it) }
    }.map {
        it.await()
    }
}

suspend fun <A, B> Collection<A>.parallelForEach(
    block: suspend (A) -> B
) = coroutineScope {
    map {
        async { block(it) }
    }.forEach {
        it.await()
    }
}
