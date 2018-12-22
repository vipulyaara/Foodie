package com.foodie.data.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Authored by vipulkumar on 21/12/18.
 *
 * Utility to provide instances of [Retrofit] and [OkHttpClient].
 */

object RetrofitProvider {
    private val genericInterceptor: Interceptor
        get() = Interceptor { chain ->
            val original = chain.request()

            val url = original.url().newBuilder()
                .addQueryParameter("client_id", "BMU2CX5FMPRDWRSETYCGXTCY5OMQ4XGATLEHEDEIX0JLUXGK")
                .addQueryParameter(
                    "client_secret",
                    "JM5OXWUU14LMCXVWN0SNJOVMZDPDD43F0XGVZTP4ETKBIQE2"
                )
                .addQueryParameter("v", "20180323")
                .build()

            val request = original.newBuilder()
                .url(url)
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }

    fun provideDefaultRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        builder.addInterceptor(genericInterceptor)

        // logging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }
}
