package ca.djmorton.aoc2024.day5.part1.drivenAdapters

import ca.djmorton.aoc2024.day5.part1.UpdateList
import ca.djmorton.aoc2024.day5.part1.UpdateRule
import java.io.File

class BaseFileManualUpdateProvider(private val filename : String) {
    fun getManualUpdates() : Sequence<UpdateList> {
        var foundSeparator = false
        return File(filename).useLines {
            it.fold(ArrayList<String>()) { acc, line ->
                if (line.trim().isBlank()) {
                    foundSeparator = true
                } else if (foundSeparator) {
                    acc.add(line)
                }
                acc
            }.asSequence().map { updateLine ->
                UpdateList(updateLine.split(",").map { e -> e.trim().toInt() })
            }
        }

    }
}