package ca.djmorton.aoc2024.day3.part2

class State(var isEnabled : Boolean, initialValue : Int) {
    var currentValue = initialValue

    fun enable() {
        isEnabled = true
    }

    fun disable() {
        isEnabled = false
    }

    fun addValue(value : Int) {
        currentValue += value
    }
}