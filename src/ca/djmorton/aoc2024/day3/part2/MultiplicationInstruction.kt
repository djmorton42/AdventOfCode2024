package ca.djmorton.aoc2024.day3.part2

class MultiplicationInstruction(private val multiplicand : Int, private val multiplier : Int) : Instruction {
    override fun apply(state : State) {
        if (state.isEnabled) {
            state.addValue(multiplicand * multiplier)
        }
    }
}