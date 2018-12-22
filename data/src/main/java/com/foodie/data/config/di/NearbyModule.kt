package com.foodie.data.config.di

import com.foodie.data.feature.entry.VenueRepository
import com.foodie.data.feature.nearby.LocalNearbyVenueStore
import com.foodie.data.feature.nearby.LocalVenueStore
import com.foodie.data.feature.nearby.NearbyVenueDataSource
import com.foodie.data.feature.nearby.NearbyVenueRepository
import com.foodie.data.feature.nearby.UpdateNearbyVenues
import com.foodie.data.feature.nearby.VenueVenueRepository
import com.foodie.data.mapper.NearbyApiMapper
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

/**
 * @author Vipul Kumar; dated 21/10/18.
 */

val nearbyModule = Kodein.Module("nearbyModule") {

    bind<UpdateNearbyVenues>() with provider {
        UpdateNearbyVenues(instance(), instance(), instance())
    }

    bind<NearbyVenueRepository>() with provider {
        NearbyVenueRepository()
    }

    bind<LocalNearbyVenueStore>() with provider {
        LocalNearbyVenueStore()
    }

    bind<NearbyVenueDataSource>() with provider {
        NearbyVenueDataSource()
    }

    bind<LocalVenueStore>() with provider {
        LocalVenueStore()
    }

    bind<VenueRepository>() with provider {
        VenueVenueRepository()
    }

    bind<NearbyApiMapper>() with provider {
        NearbyApiMapper()
    }
}
