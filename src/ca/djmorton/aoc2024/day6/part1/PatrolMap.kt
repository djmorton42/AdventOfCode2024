package ca.djmorton.aoc2024.day6.part1

import java.io.File

class PatrolMap(private var newMapLines : List<String>, skipGuardCheck: Boolean = true) {
    constructor(filename : String) : this(File(filename).readLines(), false)

    lateinit var mapLines : List<String>
    private var mapWidth : Int = 0
    var startingPosition : GuardPosition?

    init {
        mapLines = newMapLines.map { line -> line }
        startingPosition = processInitialMap(skipGuardCheck)
    }

    fun withBlockedCoord(x : Int, y : Int) : PatrolMap {
        return PatrolMap(mapLines.mapIndexed { index, line ->
            if (index == y) {
                line.substring(0,x) + "O" + line.substring(x + 1)
            } else {
                line
            }
        })
    }

    fun isPositionOffMap(guardPosition : GuardPosition) : Boolean {
        return guardPosition.x < 0 ||
                guardPosition.y < 0 ||
                guardPosition.x >= mapWidth ||
                guardPosition.y >= mapLines.size
    }

    fun isPositionBlocked(guardPosition : GuardPosition) : Boolean {
        return mapLines[guardPosition.y][guardPosition.x] != '.'
    }

    private fun processInitialMap(skipGuardCheck : Boolean) : GuardPosition? {
        var x : Int = -1
        var y : Int = -1

        mapLines = mapLines.mapIndexed { index, line ->
            mapWidth = line.length
            val location = line.indexOf("^")
            if (location >= 0) {
                y = index
                x = location
                line.replace("^", ".")
            } else {
                line
            }
        }

        if (x >= 0) {
            return GuardPosition(x, y, Facing.North)
        } else if (skipGuardCheck) {
            return null
        } else {
            throw IllegalStateException("No guard position found")
        }
    }
}