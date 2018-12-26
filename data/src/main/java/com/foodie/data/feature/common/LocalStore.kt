package com.foodie.data.feature.common

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 24/12/18.
 *
 * Local stores ensure modularity and single-responsibility.
 *
 * Stores are responsible for interacting with daos and loading data from database.
 * These are usually light-weight mappers that access the database.
 */
open class LocalStore {
    internal val logger: Logger by kodeinInstance.instance()
}
