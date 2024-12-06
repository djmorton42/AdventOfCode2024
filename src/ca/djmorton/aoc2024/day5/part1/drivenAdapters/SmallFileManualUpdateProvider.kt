package ca.djmorton.aoc2024.day5.part1.drivenAdapters

import ca.djmorton.aoc2024.day5.part1.UpdateList
import ca.djmorton.aoc2024.day5.part1.drivingPorts.ForGettingManualUpdates

class SmallFileManualUpdateProvider : ForGettingManualUpdates {
    private val manualUpdateProvider = BaseFileManualUpdateProvider("data/day5/safety-manual-updates-small.txt")

    override fun getManualUpdates(): Sequence<UpdateList> {
        return manualUpdateProvider.getManualUpdates()
    }
}