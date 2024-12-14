package ca.djmorton.aoc2024.day9.part1

class DiskRecord(val startingIndex : Int, val length : Int, val fileId : Int) : Comparable<DiskRecord> {

    override fun compareTo(other: DiskRecord): Int {
        return Integer.compare(startingIndex, other.startingIndex)
    }

}