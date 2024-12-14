package ca.djmorton.aoc2024.day10.part1

import ca.djmorton.aoc2024.TwoDimensionalMap
import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import java.io.File

class PathfinderTest {

    @Test
    fun part1WhenUsingSampleFileShouldReturnCorrectScore() {
        val pathFinder = PathFinder(TwoDimensionalMap(File("data/day10/topographical-map-small.txt").readLines()))

        assertEquals(pathFinder.calculateTrailheadScoresByReachablePeaks(), 36)
    }

    @Test
    fun part1WhenUsingRealFileShouldReturnCorrectScore() {
        val pathFinder = PathFinder(TwoDimensionalMap(File("data/day10/topographical-map.txt").readLines()))

        assertEquals(pathFinder.calculateTrailheadScoresByReachablePeaks(), 517)
    }

    @Test
    fun part2WhenUsingSampleFileShouldReturnCorrectScore() {
        val pathFinder = PathFinder(TwoDimensionalMap(File("data/day10/topographical-map-small.txt").readLines()))

        assertEquals(pathFinder.calculateTrailheadScoreByDistinctTrails(), 81)
    }

    @Test
    fun part2WhenUsingReailFileShouldReturnCorrectScore() {
        val pathFinder = PathFinder(TwoDimensionalMap(File("data/day10/topographical-map.txt").readLines()))

        assertEquals(pathFinder.calculateTrailheadScoreByDistinctTrails(), 1116)
    }
}
