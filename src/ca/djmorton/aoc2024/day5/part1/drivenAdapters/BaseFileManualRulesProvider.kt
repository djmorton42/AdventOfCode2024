package ca.djmorton.aoc2024.day5.part1.drivenAdapters

import ca.djmorton.aoc2024.day5.part1.UpdateRule
import java.io.File

class BaseFileManualRulesProvider(private val filename : String) {
    fun getUpdateRules(): Sequence<UpdateRule> {
        var foundSeparator = false
        return File(filename).useLines {
            it.fold(ArrayList<String>()) { acc, line ->
                if (line.trim().isBlank() || foundSeparator) {
                    foundSeparator = true;
                    acc
                } else {
                    acc.add(line)
                    acc
                }
            }.asSequence().map { ruleLine ->
                val ruleElements = ruleLine.split("|")
                UpdateRule(ruleElements[0].toInt(), ruleElements[1].toInt())
            }
        }
    }
}