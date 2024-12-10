package ca.djmorton.aoc2024.day7.part1

class CalibrationEquation(calibrationLine : String) {
    var calculationTotal = 0L
    val operands = ArrayList<Long>()

    init {
        val result = Regex("^([0-9]+): (.*)\$").matchEntire(calibrationLine)
        if (result == null) {
            throw IllegalArgumentException("Invalid calibration line: $calibrationLine")
        } else {
            calculationTotal = result.groups[1]!!.value.toLong()
            operands.addAll(result.groups[2]!!.value.split(" ").map { e -> e.toLong() })
        }
    }
}