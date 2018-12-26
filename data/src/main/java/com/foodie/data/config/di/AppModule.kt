package com.foodie.data.config.di

import com.foodie.data.config.AppInitializers
import com.foodie.data.config.TimberLogger
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.data.Logger
import com.foodie.data.data.api.FoodieApi
import com.foodie.data.data.api.RetrofitProvider
import com.foodie.data.data.api.RetrofitRunner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.rx2.asCoroutineDispatcher
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * DI module that provides objects which will live during the application lifecycle.
 */

val networkModule = Kodein.Module("networkModule") {
    bind<Logger>() with singleton {
        TimberLogger()
    }

    bind<FoodieApi>() with singleton {
        RetrofitProvider
            .provideDefaultRetrofit()
            .create(FoodieApi::class.java)
    }

    bind<AppInitializers>() with singleton {
        AppInitializers()
    }

    bind<RetrofitRunner>() with singleton {
        RetrofitRunner()
    }

    bind<com.foodie.data.feature.location.LocationProvider>() with singleton {
        com.foodie.data.feature.location.LocationProvider(instance())
    }

    bind<AppRxSchedulers>() with singleton {
        AppRxSchedulers(
            io = Schedulers.io(),
            computation = Schedulers.computation(),
            main = AndroidSchedulers.mainThread()
        )
    }

    bind<AppCoroutineDispatchers>() with singleton {
        AppCoroutineDispatchers(
            io = instance<AppRxSchedulers>().io.asCoroutineDispatcher(),
            computation = instance<AppRxSchedulers>().computation.asCoroutineDispatcher(),
            main = Dispatchers.Default
        )
    }
}
