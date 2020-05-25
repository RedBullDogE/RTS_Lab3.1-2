package com.example.rts_lab3_1

import kotlin.math.abs
import kotlin.random.Random

const val N: Int = 4
const val MUTATION_PROBABILITY = .5

fun generateRandomGenotype(a: Int, b: Int): List<Int> {
    return (1..N).map { Random.nextInt(a, b) }
}

fun calcDelta(constList: List<Int>, varList: List<Int>, y: Int): Int {
    if (constList.size != varList.size)
        throw IllegalArgumentException()

    var delta = y
    for (i in constList.indices) {
        delta -= constList[i] * varList[i]
    }

    return abs(delta)
}

fun calcProbability(deltaList: List<Int>): List<Double> {
    val totalDelta = deltaList.map { 1 / it.toDouble() }.sum()

    val probList: MutableList<Double> =
        (1..deltaList.size).mapIndexed { idx, _ -> deltaList[idx].toDouble() }.toMutableList()

    probList.mapInPlace { 1 / it / totalDelta }

    return probList
}

fun mutation(genList: MutableList<Int>): MutableList<Int> {
    val roulette = Random.nextDouble()

    if (roulette < MUTATION_PROBABILITY) {
        genList.mapInPlace {
            it + Random.nextInt(-10, 10)
        }
    }

    return genList
}

fun genotypeCrossover(g1: List<Int>, g2: List<Int>, cIndex: Int): List<List<Int>> {
    val child1 = g1.slice(0 until cIndex) + g2.slice(cIndex until g2.size)
    val child2 = g2.slice(0 until cIndex) + g1.slice(cIndex until g1.size)

    return listOf(child1, child2)
}

fun breeding(population: List<Int>, probList: List<Double>): List<Int> {
    for (i in population.indices) {
        val random = Random.nextDouble()
        val randomList = mutableListOf<Double>()
        var acc = 0.0
        for (el in probList) {
            acc += el
            randomList.add(acc)
        }
        val pair = mutableListOf<Int>()
        val choice: MutableList<Int> = population as MutableList<Int>
        for (j in randomList.indices) {
            if (random < randomList[j]) {
                pair.add(choice.removeAt(j))
            }
        }
    }
    return population
}

fun geneticAlgorithm(a: Int, b: Int, c: Int, d: Int, y: Int): List<Int> {
    val equation = listOf<Int>(a, b, c, d)

    for (n in 0..100000) {
        val genList = (1..N + 1).map { generateRandomGenotype(1, y) }

        val deltaList = mutableListOf<Int>()

        for (i in genList.indices) {
            val cur = calcDelta(equation, genList[i], y)
            if (cur == 0) {
                println("Result" + genList[i])
                return genList[i]
            }

            deltaList.add(cur)
        }

        val probList = calcProbability(deltaList)
    }
    throw IllegalArgumentException()
}

fun main() {
    geneticAlgorithm(1, 2, 3, 4, 123)
}

inline fun <T> MutableList<T>.mapInPlace(mutator: (T) -> T) {
    val iterate = this.listIterator()
    while (iterate.hasNext()) {
        val oldValue = iterate.next()
        val newValue = mutator(oldValue)
        if (newValue !== oldValue) {
            iterate.set(newValue)
        }
    }
}