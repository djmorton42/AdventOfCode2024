package ca.djmorton.aoc2024.day9.part1

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class DiskDefragmenterTest {

    @Test
    fun part1UsingSampleFileShouldReturnCorrectChecksum() {
        val diskMap = DiskMapLoader.loadDiskMap("data/day9/diskmap-small.txt")

        diskMap.defragment()

        assertEquals(diskMap.calculateChecksum(), "1928")
    }

    @Test
    fun part1UsingRealFileShouldReturnCorrectChecksum() {
        val diskMap = DiskMapLoader.loadDiskMap("data/day9/diskmap.txt")

        diskMap.defragment()

        assertEquals(diskMap.calculateChecksum(), "6288599492129")
    }

    @Test
    fun part2UsingSampleFileShouldReturnCorrectChecksum() {
        val diskMap = DiskMapLoader.loadDiskMap("data/day9/diskmap-small.txt")

        diskMap.defragment(true)

        assertEquals(diskMap.calculateChecksum(), "2858")
    }

    @Test
    fun part2UsingRealFileShouldReturnCorrectChecksum() {
        val diskMap = DiskMapLoader.loadDiskMap("data/day9/diskmap.txt")

        diskMap.defragment(true)

        assertEquals(diskMap.calculateChecksum(), "6321896265143")
    }
}
