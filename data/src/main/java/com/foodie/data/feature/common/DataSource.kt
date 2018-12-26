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
