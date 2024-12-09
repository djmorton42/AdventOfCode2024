package ca.djmorton.aoc2024.day6.part1

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class GuardPathAnalyzerTest {

    @Test
    fun whenUsingTestMapShouldReturnCorrectValue() {
        val patrolMap = PatrolMap("data/day6/map-small.txt")
        val pathAnalyzer = GuardPathAnalyzer(patrolMap)
        assertEquals(pathAnalyzer.calculateDistinctPatrolledPaths(), 41)
    }

    @Test
    fun whenUsingFullMapShouldReturnCorrectValue() {
        val patrolMap = PatrolMap("data/day6/map.txt")
        val pathAnalyzer = GuardPathAnalyzer(patrolMap)
        assertEquals(pathAnalyzer.calculateDistinctPatrolledPaths(), 4826)
    }

    @Test
    fun whenUsingTestMapShouldCalculateNumberOfPossibleObstructionsCorrectly() {
        val patrolMap = PatrolMap("data/day6/map-small.txt")
        val pathAnalyzer = GuardPathAnalyzer(patrolMap)
        assertEquals(pathAnalyzer.calculateObstructionPossibilities(), 6)
    }

    @Test
    fun whenUsingFullMapShouldCalculateNumberOfPossibleObstructionsCorrectly() {
        val patrolMap = PatrolMap("data/day6/map.txt")
        val pathAnalyzer = GuardPathAnalyzer(patrolMap)
        assertEquals(pathAnalyzer.calculateObstructionPossibilities(), 1721)
    }
}