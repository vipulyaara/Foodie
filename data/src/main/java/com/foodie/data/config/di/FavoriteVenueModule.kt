package com.foodie.data.config.di

import com.foodie.data.feature.favorite.AddToFavoriteVenues
import com.foodie.data.feature.favorite.FavoriteVenueRepository
import com.foodie.data.feature.favorite.LocalFavoriteVenueStore
import com.foodie.data.feature.favorite.RemoveFromFavoriteVenues
import com.foodie.data.feature.favorite.UpdateFavoriteVenues
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

/**
 * @author Vipul Kumar; dated 21/10/18.
 */

val favoriteVenueModule = Kodein.Module("favoriteVenueModule") {

    bind<UpdateFavoriteVenues>() with provider {
        UpdateFavoriteVenues(instance(), instance(), instance())
    }

    bind<FavoriteVenueRepository>() with provider {
        FavoriteVenueRepository()
    }

    bind<LocalFavoriteVenueStore>() with provider {
        LocalFavoriteVenueStore()
    }

    bind<AddToFavoriteVenues>() with provider {
        AddToFavoriteVenues()
    }

    bind<RemoveFromFavoriteVenues>() with provider {
        RemoveFromFavoriteVenues()
    }
}
