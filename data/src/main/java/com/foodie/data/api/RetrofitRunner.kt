package com.foodie.data.api

import com.foodie.data.mapper.Mapper
import com.foodie.data.model.ErrorResult
import com.foodie.data.model.Result
import com.foodie.data.model.Success
import com.foodie.data.extensions.bodyOrThrow
import com.foodie.data.extensions.toException
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitRunner @Inject constructor() {
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
