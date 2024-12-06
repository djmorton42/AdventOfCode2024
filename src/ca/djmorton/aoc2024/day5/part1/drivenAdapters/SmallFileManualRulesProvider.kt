package ca.djmorton.aoc2024.day5.part1.drivenAdapters

import ca.djmorton.aoc2024.day5.part1.UpdateRule
import ca.djmorton.aoc2024.day5.part1.drivingPorts.ForGettingUpdateRules
import java.io.File

class SmallFileManualRulesProvider : ForGettingUpdateRules {
    private val manualRulesProvider = BaseFileManualRulesProvider("data/day5/safety-manual-updates-small.txt")

    override fun getUpdateRules(): Sequence<UpdateRule> {
        return manualRulesProvider.getUpdateRules()
    }
}