package com.foodie.data.feature.common

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 24/12/18.
 *
 * Data sources are light-weight components,
 * responsible for fetching data from remote api and mapping.
 */
open class DataSource {
    internal val logger: Logger by kodeinInstance.instance()
}

const val api_section_food = "food"
const val api_nearby_count = 10
const val openNow = 1

fun offset(page: Int) = page * api_nearby_count
