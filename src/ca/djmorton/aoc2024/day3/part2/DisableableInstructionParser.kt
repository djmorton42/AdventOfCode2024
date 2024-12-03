package ca.djmorton.aoc2024.day3.part2

object DisableableInstructionParser{
    private val PATTERN = Regex("(do)\\(\\)|(don't)\\(\\)|(mul)\\(([1-9][0-9]*),([1-9][0-9]*)\\)")

    fun parse(instructionLine : String) : Sequence<Instruction> {
        return PATTERN.findAll(instructionLine).map { matchResult ->
            val instruction = (matchResult.groups[1] ?: matchResult.groups[2] ?: matchResult.groups[3])!!.value

            when (instruction) {
                "mul" -> {
                    MultiplicationInstruction(matchResult.groups[4]!!.value.toInt(), matchResult.groups[5]!!.value.toInt())
                }
                "do" -> {
                    EnableInstruction()
                }
                "don't" -> {
                    DisableInstruction()
                }
                else -> {
                    throw IllegalStateException("Invalid instruction parsed")
                }
            }
        }
    }
}