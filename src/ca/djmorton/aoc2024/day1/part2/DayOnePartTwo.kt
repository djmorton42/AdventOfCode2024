package ca.djmorton.aoc2024.day1.part2

import ca.djmorton.aoc2024.day1.part1.LineParser
import java.io.File

fun main() {
    val parser = LineParser()

    val leftList = ArrayList<Int>()
    val rightListCounter = HashMap<Int, Int>()

    File("data/day1/part1/locations.txt").bufferedReader().use {
        while(true) {
            val inputString = it.readLine() ?: break
            val parsedLine = parser.parseLine(inputString)

            val leftValue = parsedLine.get(0)
            val rightValue = parsedLine.get(1)

            leftList.add(leftValue)
            rightListCounter.compute(rightValue, { k, v ->
                (v ?: 0) + 1
            })
        }
    }

    val similarity = leftList.fold(0) { acc, element -> acc + element * rightListCounter.getOrDefault(element, 0) }

    println("Similarity = $similarity")
}
