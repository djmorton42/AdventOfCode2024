package ca.djmorton.aoc2024.day8.part1

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class AntinodeCalculatorTest {

    @Test
    fun partOneUsingSampleFileShouldReturnCorrectValue() {
        var antinodeCalculator = AntinodeCalculator("data/day8/antenna-map-small.txt")

        assertEquals(antinodeCalculator.calculateDistinctAntinodes(), 14)
    }

    @Test
    fun partOneUsingFullFileShouldReturnCorrectValue() {
        var antinodeCalculator = AntinodeCalculator("data/day8/antenna-map.txt")

        assertEquals(antinodeCalculator.calculateDistinctAntinodes(), 293)
    }

    @Test
    fun partTwoUsingSampleFileShouldReturnCorrectValue() {
        var antinodeCalculator = AntinodeCalculator("data/day8/antenna-map-small.txt")

        assertEquals(antinodeCalculator.calculateDistinctTFrequencyAntinodes(), 34)
    }

    @Test
    fun partTwoUsingOtherSampleFileShouldReturnCorrectValue() {
        var antinodeCalculator = AntinodeCalculator("data/day8/antenna-map-small2.txt")

        assertEquals(antinodeCalculator.calculateDistinctTFrequencyAntinodes(), 9)
    }

    @Test
    fun partTwoUsingFullFileShouldReturnCorrectValue() {
        var antinodeCalculator = AntinodeCalculator("data/day8/antenna-map.txt")

        assertEquals(antinodeCalculator.calculateDistinctTFrequencyAntinodes(), 934)
    }
}