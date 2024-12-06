package ca.djmorton.aoc2024.day5.part1

import ca.djmorton.aoc2024.day5.part1.drivenPorts.ForCalculatingPrintList
import ca.djmorton.aoc2024.day5.part1.drivingPorts.ForGettingManualUpdates
import ca.djmorton.aoc2024.day5.part1.drivingPorts.ForGettingUpdateRules

class PrintListCalculator (
    private val forGettingManualUpdates: ForGettingManualUpdates,
    private val forGettingUpdateRules: ForGettingUpdateRules
) : ForCalculatingPrintList
{
    private val emptySet = HashSet<Int>()

    override fun calculateValidUpdateMidPointSum(): Int {
        val updateRules = forGettingUpdateRules.getUpdateRules()
        val updates = forGettingManualUpdates.getManualUpdates()

        val precedingRuleSet = HashMap<Int, MutableSet<Int>>()
        val followingRuleSet = HashMap<Int, MutableSet<Int>>()

        updateRules.forEach { rule ->
            precedingRuleSet.getOrPut(rule.preceedingUpdate) { -> HashSet<Int>() }.add(rule.followingUpdate)
            followingRuleSet.getOrPut(rule.followingUpdate) { -> HashSet<Int>() }.add(rule.preceedingUpdate)
        }

        return updates.filterNot { update ->
            update.updates.foldIndexed(false) { index, acc, element ->
                acc || getPreceedingElements(update.updates, index).any { e ->
                    precedingRuleSet.getOrDefault(element, emptySet).contains(e)
                } || getFollowingElements(update.updates, index).any { e ->
                    followingRuleSet.getOrDefault(element, emptySet).contains(e)
                }
            }
        }.sumOf { e -> e.middleValue() }
    }

    override fun calculateReordedInvalidUpdateMidPointSum(): Int {
        val updateRules = forGettingUpdateRules.getUpdateRules()
        val updates = forGettingManualUpdates.getManualUpdates()

        val precedingRuleSet = HashMap<Int, MutableSet<Int>>()
        val followingRuleSet = HashMap<Int, MutableSet<Int>>()

        updateRules.forEach { rule ->
            precedingRuleSet.getOrPut(rule.preceedingUpdate) { -> HashSet<Int>() }.add(rule.followingUpdate)
            followingRuleSet.getOrPut(rule.followingUpdate) { -> HashSet<Int>() }.add(rule.preceedingUpdate)
        }

        var invalidUpdates = updates.filter { update ->
            update.updates.foldIndexed(false) { index, acc, element ->
                acc || getPreceedingElements(update.updates, index).any { e ->
                    precedingRuleSet.getOrDefault(element, emptySet).contains(e)
                } || getFollowingElements(update.updates, index).any { e ->
                    followingRuleSet.getOrDefault(element, emptySet).contains(e)
                }
            }
        }

        val comparator = UpdatePageComparator(updateRules)

        return invalidUpdates.map { update ->
            update.updates.sortedWith(comparator)
        }.map { sortedList ->
            UpdateList(sortedList)
        }.sumOf { e -> e.middleValue() }
    }

    private fun getPreceedingElements(list : List<Int>, index : Int) : List<Int> {
        return list.subList(0, index)
    }

    private fun getFollowingElements(list : List<Int>, index : Int) : List<Int> {
        if (index < list.size - 1) {
            return list.subList(index + 1, list.size - 1)
        } else {
            return ArrayList<Int>()
        }
    }
}