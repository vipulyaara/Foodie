package com.foodie.data.config.di

import com.foodie.data.feature.block.AddToBlockedVenues
import com.foodie.data.feature.block.BlockedVenueRepository
import com.foodie.data.feature.block.LocalBlockedVenueStore
import com.foodie.data.feature.block.RemoveFromBlockedVenues
import com.foodie.data.feature.block.UpdateBlockedVenues
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

/**
 * @author Vipul Kumar; dated 21/10/18.
 */

val blockedVenueModule = Kodein.Module("blockedVenueModule") {

    bind<UpdateBlockedVenues>() with provider {
        UpdateBlockedVenues()
    }

    bind<BlockedVenueRepository>() with provider {
        BlockedVenueRepository()
    }

    bind<LocalBlockedVenueStore>() with provider {
        LocalBlockedVenueStore()
    }

    bind<AddToBlockedVenues>() with provider {
        AddToBlockedVenues()
    }

    bind<RemoveFromBlockedVenues>() with provider {
        RemoveFromBlockedVenues()
    }
}
