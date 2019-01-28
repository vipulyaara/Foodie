package com.foodie.consumer.algorithms

/**
 * @author Vipul Kumar; dated 04/01/19.
 */
fun main(args: Array<String>) {
    print(
        xorOfArray(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)).xor(
            xorOfArray(arrayOf(1, 2, 3, 4, 5, 6, 8, 9))
        )
    )
}

fun xorOfArray(a: Array<Int>): Int {
    var x = a[0]
    for (i in 1 until a.size) {
        x = x.xor(a[i])
    }
    return x
}
