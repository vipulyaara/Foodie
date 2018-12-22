package com.foodie.data.config.di

import com.foodie.data.feature.detail.LocalVenueDetailStore
import com.foodie.data.feature.detail.UpdateVenueDetail
import com.foodie.data.feature.detail.VenueDetailDataSource
import com.foodie.data.feature.detail.VenueDetailRepository
import com.foodie.data.mapper.VenueDetailMapper
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

/**
 * @author Vipul Kumar; dated 21/10/18.
 */

val venueDetailModule = Kodein.Module("venueDetailModule") {

    bind<UpdateVenueDetail>() with provider {
        UpdateVenueDetail()
    }

    bind<VenueDetailRepository>() with provider {
        VenueDetailRepository()
    }

    bind<LocalVenueDetailStore>() with provider {
        LocalVenueDetailStore()
    }

    bind<VenueDetailDataSource>() with provider {
        VenueDetailDataSource()
    }

    bind<VenueDetailMapper>() with provider {
        VenueDetailMapper()
    }
}
