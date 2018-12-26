package com.foodie.data.feature.common

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 24/12/18.
 *
 * Repositories are responsible for the business logic on data layer.
 * They interact with [LocalStore]s and [DataSource]s; fetch and merge data; and update the interactor.
 */
open class Repository {
    internal val logger: Logger by kodeinInstance.instance()
}
