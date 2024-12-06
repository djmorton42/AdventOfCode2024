package ca.djmorton.aoc2024.day5.part1

class UpdatePageComparator(updateRules : Sequence<UpdateRule>) : Comparator<Int> {
    private val precedingRuleSet = HashMap<Int, MutableSet<Int>>()
    private val followingRuleSet = HashMap<Int, MutableSet<Int>>()
    private val emptySet = HashSet<Int>()

    init {
        updateRules.forEach { rule ->
            precedingRuleSet.getOrPut(rule.preceedingUpdate) { -> HashSet<Int>() }.add(rule.followingUpdate)
            followingRuleSet.getOrPut(rule.followingUpdate) { -> HashSet<Int>() }.add(rule.preceedingUpdate)
        }
    }

    override fun compare(first: Int?, second: Int?): Int {
        if (first == null || second == null) {
            throw IllegalArgumentException("Numbers to compare can't be null!")
        }

        if (precedingRuleSet.getOrDefault(first, emptySet).contains(second)) {
            return -1
        }
        if (followingRuleSet.getOrDefault(first, emptySet).contains(second)) {
            return 1
        }
        return 0
    }

}