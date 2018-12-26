package com.foodie.data.config.di

import androidx.room.Room
import com.foodie.data.data.db.DatabaseTransactionRunner
import com.foodie.data.data.db.EntityInserter
import com.foodie.data.data.db.FoodieDb
import com.foodie.data.data.db.RoomTransactionRunner
import com.foodie.data.data.db.daos.BlockedVenueEntryDao
import com.foodie.data.data.db.daos.FavoriteVenueEntryDao
import com.foodie.data.data.db.daos.NearbyVenueEntryDao
import com.foodie.data.data.db.daos.VenueDao
import com.foodie.data.data.db.daos.VenueDetailDao
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * @author Vipul Kumar; dated 21/10/18.
 */

val databaseModule = Kodein.Module("databaseModule") {

    bind<DatabaseTransactionRunner>() with singleton {
        RoomTransactionRunner(instance())
    }

    bind<FoodieDb>() with singleton {
        Room.databaseBuilder(instance(), FoodieDb::class.java, "Foodie.db").build()
    }

    bind<NearbyVenueEntryDao>() with singleton {
        instance<FoodieDb>().nearbyVenueEntryDao()
    }

    bind<VenueDao>() with singleton {
        instance<FoodieDb>().venueDao()
    }

    bind<VenueDetailDao>() with singleton {
        instance<FoodieDb>().venueDetailDao()
    }

    bind<FavoriteVenueEntryDao>() with singleton {
        instance<FoodieDb>().favoriteVenueEntryDao()
    }

    bind<BlockedVenueEntryDao>() with singleton {
        instance<FoodieDb>().blockedVenueEntryDao()
    }

    bind<EntityInserter>() with singleton {
        EntityInserter()
    }
}
