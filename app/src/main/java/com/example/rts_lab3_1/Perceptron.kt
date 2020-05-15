package com.example.rts_lab3_1

import kotlin.math.pow
import kotlin.random.Random

class Perceptron {
    var w1 = 0.0
    var w2 = 0.0
    var time: Long = 0
    var iter = 0

    fun train(
        points: Array<DoubleArray>,
        P: Double,
        learnRate: Double,
        iterNumber: Int,
        timeDeadline: Double
    ): Boolean {
        val ten = 10.0
        var y: Double
        w1 = Random.nextDouble()
        w2 = Random.nextDouble()
        val endCond = booleanArrayOf(false, false, false, false)
        val startT = System.currentTimeMillis()
        val nanoTime = timeDeadline * ten.pow(3)
        iter = 0
        while (iter < iterNumber) {
            y = w1 * points[iter % 4][0] + w2 * points[iter % 4][1]
            if (y > P) {
                endCond[iter % 4] = true
                var j = 0
                while (j < 4) {
                    if (!endCond[j++]) break
                }
                if (j == 4) {
                    iter++
                    return true
                }
            } else {
                endCond[iter % 4] = false
                w1 += (P - y) * points[iter % 4][0] * learnRate
                w2 += (P - y) * points[iter % 4][1] * learnRate
            }
            time = System.currentTimeMillis() - startT
            if (time >= nanoTime) break
            iter++
        }
        return false
    }
}

