package ca.djmorton.aoc2024

class Coord(val x : Int, val y : Int) {
    fun moveDirection(facing : Facing) : Coord {
        return Coord(x + facing.stepDirectionX, y + facing.stepDiectionY)
    }

    fun apply(translation : Translation) : Coord {
        return Coord(x + translation.x, y + translation.y)
    }

    fun translation(coord : Coord) : Translation {
        return Translation(coord.x - x, coord.y - y)
    }

    override fun toString() : String {
        return "($x, $y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coord

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}