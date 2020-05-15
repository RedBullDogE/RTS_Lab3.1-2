package com.example.rts_lab3_12

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

fun factorization(n: Long): LongArray {
    if (n <= 0)
        throw IllegalArgumentException("Number should be positive!")

    if (n % 2 == 0L)
        return longArrayOf(n / 2, 2)

    if (isPerfectSquare(n))
        return longArrayOf(
            sqrt(n.toDouble()).toLong(),
            sqrt(n.toDouble()).toLong()
        )

    var x: Long = ceil(sqrt(n.toDouble())).toLong()
    var y: Long

    while (true) {
        val y1: Long = x * x - n
        y = sqrt(y1.toDouble()).toLong()
        println(y)
        if (y * y == y1) break else x += 1
    }

    return longArrayOf(x - y, x + y)
}

fun isPerfectSquare(n: Long): Boolean {
    val sr = sqrt(n.toDouble())

    return sr - floor(sr) == 0.0
}