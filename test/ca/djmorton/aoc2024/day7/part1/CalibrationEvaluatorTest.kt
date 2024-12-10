package ca.djmorton.aoc2024.day7.part1

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class CalibrationEvaluatorTest {
    @Test
    fun usingSmallTestFileShouldReturnCorrectResult() {
        val calibrationEvaluator = CalibrationEvaluator("data/day7/calibrations-small.txt")

        assertEquals(calibrationEvaluator.evaluate(false), 3749)
    }

    @Test
    fun usingFullTestFileShouldReturnCorrectResult() {
        val calibrationEvaluator = CalibrationEvaluator("data/day7/calibrations.txt")

        assertEquals(calibrationEvaluator.evaluate(false), 12940396350192)
    }

    @Test
    fun usingSmallTestFileShouldReturnCorrectResultForPart2() {
        val calibrationEvaluator = CalibrationEvaluator("data/day7/calibrations-small.txt")

        assertEquals(calibrationEvaluator.evaluate(true), 11387)
    }

    @Test
    fun usingFullTestFileShouldReturnCorrectResultForPart2() {
        val calibrationEvaluator = CalibrationEvaluator("data/day7/calibrations.txt")

        assertEquals(calibrationEvaluator.evaluate(true), 106016735664498)
    }
}