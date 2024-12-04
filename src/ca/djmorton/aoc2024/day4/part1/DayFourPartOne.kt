package ca.djmorton.aoc2024.day4.part1

import java.io.File

fun main() {
    val searchText = File("data/day4/wordsearch.txt").bufferedReader().readLines()

    val puzzle = Puzzle(searchText)

    val occurrences = puzzle.calculateOccurrences()

    println("XMAS appears $occurrences times")
}