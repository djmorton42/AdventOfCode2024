package ca.djmorton.aoc2024.day5.part1

import ca.djmorton.aoc2024.day5.part1.drivenAdapters.FileManualRulesProvider
import ca.djmorton.aoc2024.day5.part1.drivenAdapters.FileManualUpdateProvider
import ca.djmorton.aoc2024.day5.part1.drivenAdapters.SmallFileManualRulesProvider
import ca.djmorton.aoc2024.day5.part1.drivenAdapters.SmallFileManualUpdateProvider
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class PrintListCalculatorTest {

    @Test
    fun usingSmallFilesShouldReturnCorrectNumber() {
        val subject = PrintListCalculator(SmallFileManualUpdateProvider(), SmallFileManualRulesProvider())

        assertEquals(subject.calculateValidUpdateMidPointSum(), 143)
    }

    @Test
    fun usingFullFilesShouldReturnCorrectNumber() {
        val subject = PrintListCalculator(FileManualUpdateProvider(), FileManualRulesProvider())

        assertEquals(subject.calculateValidUpdateMidPointSum(), 5275)
    }

    @Test
    fun usingSmallFilesShouldReturnCorrectNumberForInvalid() {
        val subject = PrintListCalculator(SmallFileManualUpdateProvider(), SmallFileManualRulesProvider())

        assertEquals(subject.calculateReordedInvalidUpdateMidPointSum(), 123)
    }

    @Test
    fun usingFullFilesShouldReturnCorrectNumberForInvalid() {
        val subject = PrintListCalculator(FileManualUpdateProvider(), FileManualRulesProvider())

        assertEquals(subject.calculateReordedInvalidUpdateMidPointSum(), 6191)
    }
}