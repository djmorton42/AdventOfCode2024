package ca.djmorton.aoc2024.day3.part2

interface Instruction {
    fun apply(state : State)
}