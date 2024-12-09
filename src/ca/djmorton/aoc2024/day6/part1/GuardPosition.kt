package ca.djmorton.aoc2024.day6.part1

class GuardPosition(var x : Int, var y : Int, var facing : Facing) {
    fun stepForward() : GuardPosition {
        return GuardPosition(x + facing.stepDirectionX, y + facing.stepDiectionY, facing)
    }

    fun turnRight() : GuardPosition {
        return GuardPosition(x, y, facing.turnRight())
    }

    fun toCoordString() : String {
        return "($x,$y)"
    }

    fun toPositionString() : String {
        return "($x,$y,$facing)"
    }

    fun isSamePosition(guardPosition : GuardPosition) : Boolean {
        return guardPosition.x == x && guardPosition.y == y
    }
}