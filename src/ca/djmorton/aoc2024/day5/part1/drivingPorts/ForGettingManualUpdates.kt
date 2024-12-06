package ca.djmorton.aoc2024.day5.part1.drivingPorts

import ca.djmorton.aoc2024.day5.part1.UpdateList

interface ForGettingManualUpdates {
    fun getManualUpdates() : Sequence<UpdateList>
}