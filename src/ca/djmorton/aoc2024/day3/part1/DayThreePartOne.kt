package ca.djmorton.aoc2024.day3.part1

import java.io.File

fun main() {
    val result = File("data/day3/part1/instructions.txt").bufferedReader().useLines {
        it.flatMap { line ->
            InstructionParser.parse(line)
        }.fold(0) { acc, instruction ->
            acc + instruction.getProduct()
        }
    }

    println("The result is $result")
}
