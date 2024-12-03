package ca.djmorton.aoc2024.day3.part2

import java.io.File

fun main() {
    val state = State(isEnabled = true, initialValue = 0)

    File("data/day3/part1/instructions.txt").bufferedReader().useLines {
        it.flatMap { line ->
            DisableableInstructionParser.parse(line)
        }.forEach { instruction ->
            instruction.apply(state)
        }
    }

    val result = state.currentValue

    println("The result is $result")
}
