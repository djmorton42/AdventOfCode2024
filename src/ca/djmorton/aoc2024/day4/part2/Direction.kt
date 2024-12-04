package ca.djmorton.aoc2024.day4.part2

enum class Direction(val firstPositionX : Int, val firstPositionY : Int, val secondPositionX : Int, val secondPositionY : Int) {
    UP_RIGHT(-1, 1, 1, -1),
    DOWN_RIGHT(-1, -1, 1, 1),
    UP_LEFT(1, 1, -1, -1),
    DOWN_LEFT(1, -1, -1, 1)
}