package com.foodie.data.api

import com.foodie.data.di.kodeinInstance
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * configures the API module and provides services to interact with the APIs.
 */
object FoodieApi {
    internal val fourSquareApi: FourSquareService by kodeinInstance.instance()
}
