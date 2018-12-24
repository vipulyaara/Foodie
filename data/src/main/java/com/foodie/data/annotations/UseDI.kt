package com.foodie.data.annotations

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Annotation to denote that a class should be provided by DI (currently Kodein).
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class UseDI
