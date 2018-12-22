package com.foodie.consumer.config.di

import com.foodie.consumer.config.initializers.EpoxyInitializer
import com.foodie.consumer.config.initializers.LeakCanaryInitializer
import com.foodie.consumer.config.initializers.StethoInitializer
import com.foodie.consumer.config.initializers.TimberInitializer
import com.foodie.data.config.AppInitializer
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * @author Vipul Kumar; dated 21/12/18.
 */
val appModule = Kodein.Module("appModule") {

    bind<AppInitializer>("TimberInitializer") with singleton {
        TimberInitializer(instance())
    }

    bind<AppInitializer>("StethoInitializer") with singleton {
        StethoInitializer()
    }

    bind<AppInitializer>("EpoxyInitializer") with singleton {
        EpoxyInitializer()
    }

    bind<AppInitializer>("LeakCanaryInitializer") with singleton {
        LeakCanaryInitializer()
    }
}
