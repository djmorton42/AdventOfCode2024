package ca.djmorton.aoc2024.day1.part1

class LineParser {
    val PATTERN = Regex("^([0-9]+)[\\s]+([0-9]+)$")

    fun parseLine(line : String) : Array<Int> {
        val match = PATTERN.matchEntire(line)
        if (match != null && match.groups.size == 3) {
            val firstNumber = match.groups.get(1)?.value?.toInt()
            val secondNumber = match.groups.get(2)?.value?.toInt()
            if (firstNumber == null || secondNumber == null) {
                throw IllegalArgumentException("Couldn't parse line: " + line)
            }
            return arrayOf(firstNumber, secondNumber)
        } else {
            throw IllegalArgumentException("Invalid input line: " + line)
        }
    }
}