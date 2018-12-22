package com.foodie.consumer.config.initializers

import android.app.Application
import com.foodie.data.BuildConfig
import com.foodie.data.config.AppInitializer
import com.foodie.data.config.TimberLogger
import com.foodie.data.data.Logger

class TimberInitializer constructor(private val timberLogger: Logger) :
    AppInitializer {
    override fun init(application: Application) {
        if (timberLogger is TimberLogger) {
            timberLogger.setup(BuildConfig.DEBUG)
        }
    }
}
