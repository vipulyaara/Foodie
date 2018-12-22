package com.foodie.data.config

import android.app.Application
import com.foodie.data.config.di.kodeinInstance
import org.kodein.di.generic.instance

class AppInitializers {
    private val initializers: MutableSet<@JvmSuppressWildcards AppInitializer> = hashSetOf()

    init {
        val timber: AppInitializer by kodeinInstance.instance("TimberInitializer")
        val stetho: AppInitializer by kodeinInstance.instance("StethoInitializer")
        val epoxy: AppInitializer by kodeinInstance.instance("EpoxyInitializer")
        val canary: AppInitializer by kodeinInstance.instance("LeakCanaryInitializer")
        initializers.add(timber)
        initializers.add(stetho)
        initializers.add(epoxy)
        initializers.add(canary)
    }

    fun init(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }
}
