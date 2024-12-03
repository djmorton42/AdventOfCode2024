package ca.djmorton.aoc2024.day3.part2

class EnableInstruction : Instruction {
    override fun apply(state : State) {
        state.enable()
    }
}