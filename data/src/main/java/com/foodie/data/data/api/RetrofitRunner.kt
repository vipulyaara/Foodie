package com.foodie.data.data.api

import com.foodie.data.annotations.UseSingleton
import com.foodie.data.extensions.bodyOrThrow
import com.foodie.data.extensions.toException
import com.foodie.data.mapper.Mapper
import com.foodie.data.model.ErrorResult
import com.foodie.data.model.Result
import com.foodie.data.model.Success
import retrofit2.Response

/**
 * Utility to execute Retrofit calls and map them to returnable entities.
 */
@UseSingleton
class RetrofitRunner {
    suspend fun <T, E> executeForResponse(
        mapper: Mapper<T, E>,
        request: suspend () -> Response<T>
    ): Result<E> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                val okHttpNetworkResponse = response.raw().networkResponse()
                val notModified =
                    okHttpNetworkResponse == null || okHttpNetworkResponse.code() == 304
                Success(
                    data = mapper.map(response.bodyOrThrow()),
                    responseModified = !notModified
                )
            } else {
                ErrorResult(response.toException())
            }
        } catch (e: Exception) {
            ErrorResult(e)
        }
    }

    suspend fun <T> executeForResponse(request: suspend () -> Response<T>): Result<Unit> {
        val unitMapper = object : Mapper<T, Unit> {
            override fun map(from: T) = Unit
        }
        return executeForResponse(unitMapper, request)
    }
}
