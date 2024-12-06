package ca.djmorton.aoc2024.day5.part1

class UpdateRule(var preceedingUpdate : Int, var followingUpdate : Int) {

    override fun toString() : String {
        return "[$preceedingUpdate before $followingUpdate]"
    }
}