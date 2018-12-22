package com.foodie.data.model

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
data class UiResource(val status: Status, val message: String? = null)
