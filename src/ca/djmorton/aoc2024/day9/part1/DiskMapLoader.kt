package ca.djmorton.aoc2024.day9.part1

import java.io.File

object DiskMapLoader {
    fun loadDiskMap(filename : String) : DiskMap {
        return DiskMap(File(filename).readLines().first())
    }
}