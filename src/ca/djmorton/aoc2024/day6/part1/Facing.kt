package ca.djmorton.aoc2024.day6.part1

enum class Facing(var stepDirectionX : Int, var stepDiectionY : Int) {
    North(0, -1) {
        override fun turnRight() : Facing {
            return East
        }
    },
    East(1, 0) {
        override fun turnRight() : Facing {
            return South
        }
    },
    South(0, 1) {
        override fun turnRight() : Facing {
            return West
        }
    },
    West(-1, 0) {
        override fun turnRight() : Facing {
            return North
        }
    };

    abstract fun turnRight(): Facing
}