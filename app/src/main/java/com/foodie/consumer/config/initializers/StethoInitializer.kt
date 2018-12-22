package com.foodie.consumer.config.initializers

import android.app.Application
import com.facebook.stetho.Stetho
import com.foodie.data.config.AppInitializer

class StethoInitializer : AppInitializer {
    override fun init(application: Application) =
        Stetho.initializeWithDefaults(application)
}
