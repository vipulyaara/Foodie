package com.foodie.consumer

import android.app.Application
import com.facebook.stetho.common.LogUtil
import com.foodie.consumer.config.di.appModule
import com.foodie.data.config.AppInitializers
import com.foodie.data.config.di.databaseModule
import com.foodie.data.config.di.favoriteVenueModule
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.config.di.nearbyModule
import com.foodie.data.config.di.networkModule
import com.foodie.data.config.di.venueDetailModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 22/10/18.
 *
 * Application class for the project.
 */
class FoodieApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidModule(this@FoodieApplication))
        import(appModule)
        import(databaseModule)
        import(networkModule)
        import(nearbyModule)
        import(venueDetailModule)
        import(favoriteVenueModule)
    }

    // initializes Timber, Stetho, FrameMetrics
    private val initializers: AppInitializers by kodein.instance()

    override fun onCreate() {
        super.onCreate()
        kodeinInstance = kodein
        initializers.init(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {
            TRIM_MEMORY_MODERATE,
            TRIM_MEMORY_RUNNING_LOW,
            TRIM_MEMORY_RUNNING_MODERATE,
            TRIM_MEMORY_BACKGROUND,
            TRIM_MEMORY_UI_HIDDEN,
            TRIM_MEMORY_COMPLETE,
            TRIM_MEMORY_RUNNING_CRITICAL -> LogUtil.d("onTrimMemory $level")
        }
    }
}
