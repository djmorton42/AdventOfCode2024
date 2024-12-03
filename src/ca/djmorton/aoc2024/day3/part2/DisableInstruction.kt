package ca.djmorton.aoc2024.day3.part2

class DisableInstruction : Instruction {
    override fun apply(state : State) {
        state.disable()
    }
}