package ca.djmorton.aoc2024.day5.part1

class UpdateList(val updates : List<Int>) {
    fun middleValue() : Int {
        return updates[updates.size / 2]
    }

    override fun toString() : String {
        return "Page Updates: " + updates.joinToString(" - ")
    }
}