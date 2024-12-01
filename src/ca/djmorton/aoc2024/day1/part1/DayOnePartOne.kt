package ca.djmorton.aoc2024.day1.part1

import java.io.File
import kotlin.math.abs

fun main() {
    val parser = LineParser()

    val leftList = ArrayList<Int>()
    val rightList = ArrayList<Int>()

    File("data/day1/part1/locations.txt").bufferedReader().use {
        while(true) {
            val inputString = it.readLine() ?: break
            val parsedLine = parser.parseLine(inputString)

            leftList.add(parsedLine[0])
            rightList.add(parsedLine[1])
        }
    }

    leftList.sort()
    rightList.sort()

    val distance = leftList.foldIndexed(0) { index, acc, element ->
        acc + abs(element - rightList[index])
    }

    println("Distance = $distance")
}
