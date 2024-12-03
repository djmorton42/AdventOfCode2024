package ca.djmorton.aoc2024.day3.part1

object InstructionParser {
    private val PATTERN = Regex("mul\\(([1-9][0-9]*),([1-9][0-9]*)\\)")

    fun parse(instructionLine : String) : Sequence<Instruction> {
        return PATTERN.findAll(instructionLine).map { matchResult ->
            val multiplicand = matchResult.groups[1]!!.value.toInt()
            val multiplier = matchResult.groups[2]!!.value.toInt()

            Instruction(multiplicand, multiplier)
        }
    }
}