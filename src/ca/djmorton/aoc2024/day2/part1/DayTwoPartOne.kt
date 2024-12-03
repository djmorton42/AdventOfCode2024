package ca.djmorton.aoc2024.day2.part1

import java.io.File

fun main() {
    val counter = File("data/day2/part1/reports.txt").bufferedReader().useLines {
        it.filter { line -> Report(line).isSafe() }.count()
    }

    println("Number of safe reports: $counter")
}

