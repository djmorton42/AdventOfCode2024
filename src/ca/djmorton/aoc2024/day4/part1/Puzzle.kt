package ca.djmorton.aoc2024.day4.part1

import java.security.MessageDigest

class Puzzle(private val puzzleLines : List<String>) {
    val foundList = HashSet<String>()
    val maxY = puzzleLines.size - 1
    val maxX = puzzleLines.first().length - 1
    val searchWord = "XMAS"

    fun calculateOccurrences() : Int {
        for(y in puzzleLines.indices) {
            for(x in puzzleLines[y].indices) {
                if (puzzleLines[y][x] == 'X') {
                    analyzePosition(x, y)
                }
            }
        }

        return foundList.size
    }

    private fun analyzePosition(x : Int, y : Int) {
        val validPositionHashes = Direction.entries.map { direction ->
            calculateCoordsToCheck(direction, x, y)
        }.filter { coordList ->
            coordList.size == 4
        }.map { coords ->
            checkCoords(coords)
        }.filterNot { s -> s == "" }

        foundList.addAll(validPositionHashes)
    }

    private fun checkCoords(coords : List<Pair<Int, Int>>) : String {
        val foundWord = coords.mapIndexed { index, pair ->
            coordHasChar(pair, searchWord[index])
        }.reduce { acc, hasCorrectChar ->
            acc && hasCorrectChar
        }

        if (foundWord) {
            return calculateCoordsHash(coords)
        } else {
            return ""
        }
    }

    private fun coordHasChar(coord : Pair<Int, Int>, char : Char) : Boolean {
        return puzzleLines[coord.second][coord.first] == char
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun calculateCoordsHash(coords : List<Pair<Int, Int>>) : String {
        return MessageDigest.getInstance("MD5").digest(generateCoordString(coords).toByteArray()).toHexString()
    }

    private fun generateCoordString(coords : List<Pair<Int, Int>>) : String {
        return coords.map { pair ->
            "(" + pair.first + "," + pair.second + ")"
        }.reduce { acc, coordStringh ->
            acc + coordStringh
        }
    }

    private fun calculateCoordsToCheck(direction : Direction, x : Int, y: Int) : List<Pair<Int, Int>> {
        val coords = ArrayList<Pair<Int, Int>>()
        for(i in 0..3) {
            coords.add(Pair(x + direction.xDelta * i, y + direction.yDelta *i))
        }
        if (anyOutOfBounds(coords)) {
            return ArrayList<Pair<Int, Int>>()
        } else {
            return coords
        }
    }

    private fun anyOutOfBounds(coords : List<Pair<Int, Int>>) : Boolean {
        return coords.any { pair ->
            val x = pair.first
            val y = pair.second

            x < 0 || x > maxX || y < 0 || y > maxY
        }
    }
}