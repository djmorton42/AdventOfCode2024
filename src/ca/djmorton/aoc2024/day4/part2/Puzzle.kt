package ca.djmorton.aoc2024.day4.part2

class Puzzle(private val puzzleLines : List<String>) {
    val foundList = HashSet<String>()
    val maxY = puzzleLines.size - 1
    val maxX = puzzleLines.first().length - 1
    val searchWord = "MAS"

    fun calculateOccurrences() : Int {
        for(y in puzzleLines.indices) {
            for(x in puzzleLines[y].indices) {
                if (puzzleLines[y][x] == 'A') {
                    analyzePosition(x, y)
                }
            }
        }

        return foundList.size
    }

    private fun analyzePosition(x : Int, y : Int) {
        val matches = Direction.entries.map { direction ->
            calculateCoordsToCheck(direction, x, y)
        }.filter { coordList ->
            coordList.isNotEmpty()
        }.map { coords ->
            checkCoords(coords)
        }.filter { result ->
            result
        }.count()

        if (matches == 2) {
            foundList.add("($x,$y)")
        }
    }

    private fun calculateCoordsToCheck(direction : Direction, x : Int, y : Int) : List<Pair<Int, Int>> {
        var coords = ArrayList<Pair<Int, Int>>()

        coords.add(Pair(x + direction.firstPositionX, y + direction.firstPositionY))
        coords.add(Pair(x, y))
        coords.add(Pair(x + direction.secondPositionX, y + direction.secondPositionY))

        if (anyOutOfBounds(coords)) {
             return ArrayList<Pair<Int, Int>>()
        } else {
            return coords;
        }
    }

    private fun checkCoords(coords : List<Pair<Int, Int>>) : Boolean {
        return coords.mapIndexed { index, pair ->
            coordHasChar(pair, searchWord[index])
        }.reduce { acc, hasCorrectChar ->
            acc && hasCorrectChar
        }
    }

    private fun coordHasChar(coord : Pair<Int, Int>, char : Char) : Boolean {
        return puzzleLines[coord.second][coord.first] == char
    }

    private fun anyOutOfBounds(coords : List<Pair<Int, Int>>) : Boolean {
        return coords.any { pair ->
            val x = pair.first
            val y = pair.second
            
            x < 0 || x > maxX || y < 0 || y > maxY
        }
    }
}