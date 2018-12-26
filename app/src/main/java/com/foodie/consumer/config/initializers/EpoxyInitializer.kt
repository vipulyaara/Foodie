package com.foodie.consumer.config.initializers

import android.app.Application
import android.os.HandlerThread
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.foodie.data.config.AppInitializer

/**
 * @author Vipul Kumar; dated 21/10/18.
 *
 * Initializes Epoxy.
 *
 * - assigns a separate [HandlerThread] for diffing and building models.
 * - sets snapping behaviour
 * - sets logging
 *
 */
class EpoxyInitializer : AppInitializer {
    override fun init(application: Application) {
        // Make EpoxyController async
//        EpoxyAsyncUtil.getAsyncBackgroundHandler().also {
//            EpoxyController.defaultDiffingHandler = it
//            EpoxyController.defaultModelBuildingHandler = it
//        }


        // Also setup Carousel to use a more sane snapping behavior
        Carousel.setDefaultGlobalSnapHelperFactory(null)

        EpoxyController.setGlobalDebugLoggingEnabled(true)
    }
}
