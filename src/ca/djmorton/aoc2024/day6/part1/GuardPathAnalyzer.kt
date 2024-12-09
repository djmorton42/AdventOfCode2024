package ca.djmorton.aoc2024.day6.part1

class GuardPathAnalyzer(private val patrolMap : PatrolMap) {
    private val obstructionPossibility = HashSet<String>()
    private val distinctPatrolPoints = ArrayList<GuardPosition>()

    fun calculateDistinctPatrolledPaths() : Int {
        val guardPositionHistory = HashSet<String>()

        var guardPosition = patrolMap.startingPosition
            ?: throw IllegalStateException("No starting position found for the guard")

        guardPositionHistory.add(guardPosition.toCoordString())

        while(true) {
            val candidatePosition = guardPosition.stepForward()

            if (patrolMap.isPositionOffMap(candidatePosition)) {
                return guardPositionHistory.size
            } else if (patrolMap.isPositionBlocked(candidatePosition)) {
                guardPosition = guardPosition.turnRight()
            } else {
                if (guardPositionHistory.add(candidatePosition.toCoordString())) {
                    distinctPatrolPoints.add(candidatePosition)
                }
                guardPosition = candidatePosition
            }
        }
    }

    private fun endsInLoop(map : PatrolMap, startingPosition : GuardPosition) : Boolean {
        val positionHistory = HashSet<String>()

        var guardPosition = startingPosition

        while (true) {
            if (!positionHistory.add(guardPosition.toPositionString())) {
                return true
            }
            val candidateGuardPosition = guardPosition.stepForward()
            guardPosition = if (map.isPositionOffMap(candidateGuardPosition)) {
                return false
            } else if (map.isPositionBlocked(candidateGuardPosition)) {
                guardPosition.turnRight()
            } else {
                candidateGuardPosition
            }
        }
    }

    fun calculateObstructionPossibilities() : Int {
        calculateDistinctPatrolledPaths()

        val startingPosition = patrolMap.startingPosition
            ?: throw IllegalStateException("No starting position in initial map")

        return distinctPatrolPoints.filterNot { point ->
            startingPosition.isSamePosition(point)
        }.filter { obstructionPoint ->
            endsInLoop(patrolMap.withBlockedCoord(obstructionPoint.x, obstructionPoint.y), startingPosition)
        }.size
    }
}