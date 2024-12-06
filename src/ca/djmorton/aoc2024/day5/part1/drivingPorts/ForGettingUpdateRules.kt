package ca.djmorton.aoc2024.day5.part1.drivingPorts

import ca.djmorton.aoc2024.day5.part1.UpdateRule

interface ForGettingUpdateRules {
    fun getUpdateRules() : Sequence<UpdateRule>
}