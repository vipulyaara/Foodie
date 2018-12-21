package com.foodie.data.annotations

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Annotation that denotes that a class, method, or variable is part of the internal api
 * used by Foodie and should not be exposed.
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.SOURCE)
annotation class InternalFoodieApi
