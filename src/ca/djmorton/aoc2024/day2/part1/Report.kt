    package ca.djmorton.aoc2024.day2.part1

import kotlin.math.abs
import ca.djmorton.aoc2024.Pair

class Report(private val reportLine : String, private val enableDampener : Boolean = false) {
    fun isSafe() : Boolean {
        val reportNumbers = reportLine.split(" ").map { s -> s.toInt() }

        if (isSafe(buildPairList(reportNumbers))) {
            return true
        } else if (enableDampener && dumbSafe(reportNumbers)) {
            return true
        }

        return false
    }

    private fun isSafe(pairs : List<Pair<Int>>): Boolean {
        val firstIsGreater = pairs.first().firstIsGreater()

        return pairs.all { pair ->
            val delta = abs(pair.first - pair.second)
            firstIsGreater == pair.firstIsGreater() && delta in 1..3
        }
    }

    private fun dumbSafe(numbers : List<Int>) : Boolean {
        for (i in numbers.indices) {
            val candidateList = numbers.take(i) + numbers.takeLast(numbers.size - (i + 1))
            if (isSafe(buildPairList(candidateList))) {
                return true
            }
        }

        return false
    }

    private fun buildPairList(reportNumbers : List<Int>): List<Pair<Int>> {
        return reportNumbers.dropLast(1).mapIndexed { index, value ->
            Pair(value, reportNumbers[index + 1])
        }
    }
}