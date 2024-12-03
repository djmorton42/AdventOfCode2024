package ca.djmorton.aoc2024.day2.part2

import ca.djmorton.aoc2024.day2.part1.Report
import java.io.File

fun main() {
    val counter = File("data/day2/part1/reports.txt").bufferedReader().useLines {
        it.filter { line -> Report(line, true).isSafe() }.count()
    }

    println("Number of safe reports: $counter")
}

