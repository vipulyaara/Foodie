package com.foodie.consumer.config.initializers

import android.app.Application
import com.foodie.data.config.AppInitializer

/**
 * @author Vipul Kumar; dated 21/10/18.
 *
 */
class LeakCanaryInitializer : AppInitializer {
    override fun init(application: Application) {
//        if (BuildConfig.DEBUG) {
//            if (LeakCanary.isInAnalyzerProcess(application)) {
//                return
//            }
//            LeakCanary.install(application)
//        }
    }
}
