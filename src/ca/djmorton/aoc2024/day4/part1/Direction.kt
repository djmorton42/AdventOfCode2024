package ca.djmorton.aoc2024.day4.part1

enum class Direction(val xDelta : Int, val yDelta :Int) {
    UP(0, 1),
    UP_RIGHT(1, 1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, -1),
    DOWN(0, -1),
    DOWN_LEFT(-1, -1),
    LEFT(-1, 0),
    UP_LEFT(-1, 1)
}