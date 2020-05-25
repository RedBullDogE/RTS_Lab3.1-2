package com.example.rts_lab3_1

import java.util.*
import java.util.concurrent.TimeoutException
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

    val startTime = Date().time
    while (true) {
        val y1: Long = x * x - n
        y = sqrt(y1.toDouble()).toLong()
        println(y)
        if (y * y == y1) break else x += 1
    }
    if (Date().time - startTime > 1000) {
        throw TimeoutException()
    }
    return longArrayOf(x - y, x + y)
}

fun isPerfectSquare(n: Long): Boolean {
    val sr = sqrt(n.toDouble())

    return sr - floor(sr) == 0.0
}