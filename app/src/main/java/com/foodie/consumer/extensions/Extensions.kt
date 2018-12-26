package com.foodie.consumer.extensions

/**
 * @author Vipul Kumar; dated 26/12/18.
 */
fun Double.roundOffTo(numDecimalPlaces: Int): Double {
    val factor = Math.pow(10.0, numDecimalPlaces.toDouble())
    return Math.round(this * factor) / factor
}
