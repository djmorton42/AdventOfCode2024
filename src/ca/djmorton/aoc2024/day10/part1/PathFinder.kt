package ca.djmorton.aoc2024.day10.part1

import ca.djmorton.aoc2024.Coord
import ca.djmorton.aoc2024.Facing
import ca.djmorton.aoc2024.TwoDimensionalMap

class PathFinder(private val map : TwoDimensionalMap) {

    fun calculateTrailheadScoresByReachablePeaks() : Int {
        val trailHeads = map.getCoordsForChar('0')

        return trailHeads.map { t -> calculateReachablePeaks(t) }.sumOf { p -> p.size }
    }

    fun calculateTrailheadScoreByDistinctTrails() : Int {
        val trailHeads = map.getCoordsForChar('0')

        val pathsToPeaks = HashSet<String>()

        pathsToPeaks.addAll(trailHeads.flatMap { t ->
            calculatePathsToPeaks("", t)
        })

        return pathsToPeaks.size
    }

    private fun calculatePathsToPeaks(history : String, coord : Coord) : Set<String> {
        val resultSet = HashSet<String>()

        val validNextSteps = validNextSteps(coord)
        resultSet.addAll(validNextSteps.filter { c -> map.getCoord(c) == '9' }.map { c -> history + coord.toString() + c.toString() })
        resultSet.addAll(validNextSteps.filterNot { c -> map.getCoord(c) == '9' }.flatMap { s -> calculatePathsToPeaks(history + coord.toString(), s) })

        return resultSet
    }

    private fun calculateReachablePeaks(coord : Coord) : Set<Coord> {
        val reachablePeaks = HashSet<Coord>();

        val validNextSteps = validNextSteps(coord)
        reachablePeaks.addAll(validNextSteps.filter { c -> map.getCoord(c) == '9' })
        reachablePeaks.addAll(validNextSteps.filterNot { c -> map.getCoord(c) == '9' }.flatMap { s -> calculateReachablePeaks(s) })
        return reachablePeaks
}

    private fun validNextSteps(coord : Coord) : List<Coord> {
        val charAtCoord = map.getCoord(coord)
        val height = charAtCoord.digitToInt()
        val targetHeight = height + 1
        return listOf(
            coord.moveDirection(Facing.North),
            coord.moveDirection(Facing.East),
            coord.moveDirection(Facing.South),
            coord.moveDirection(Facing.West)
        ).filter { c -> map.isOnMap(c) && map.getCoord(c).isDigit() && map.getCoord(c).digitToInt() == targetHeight }
    }
}