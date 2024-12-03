package ca.djmorton.aoc2024

class Pair<T>(val first: T, val second: T) where T : Comparable<T> {
    fun firstIsGreater(): Boolean {
        return first.compareTo(second) >= 1
    }

    fun secondIsGreater(): Boolean {
        return first.compareTo(second) <= -1
    }

    override fun toString() : String {
        return "[$first, $second]"
    }
}