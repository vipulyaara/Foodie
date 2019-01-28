package com.foodie.consumer.algorithms

/**
 * @author Vipul Kumar; dated 04/01/19.
 */
fun main(args: Array<String>) {
    print(rotLeftWithReverse(arrayOf(1, 2, 3, 4, 5), 4).joinToString(" "))
}

fun rotLeftWithReverse(a: Array<Int>, d: Int): Array<Int> {
    val n = a.size

    for (i in 0 until d / 2) {
        a[i] = a[d - 1 - i].also { a[d - 1 - i] = a[i] }
    }

    for (i in d until n / 2) {
        a[i] = a[n - 1 - i].also { a[n - 1 - i] = a[i] }
    }

    for (i in 0 until n / 2) {
        a[i] = a[n - 1 - i].also { a[n - 1 - i] = a[i] }
    }

    return a
}

fun rotLeft(a: Array<Int>, d: Int): Array<Int> {
    for (i in 0 until d) {
        rotateByOne(a, a.size)
    }
    return a
}

fun rotateByOne(a: Array<Int>, n: Int) {
    val temp = a[0]
    for (i in 0..n - 2) {
        a[i] = a[i + 1]
    }
    a[n - 1] = temp
}
