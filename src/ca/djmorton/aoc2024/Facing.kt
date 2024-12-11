package ca.djmorton.aoc2024

enum class Facing(var stepDirectionX : Int, var stepDiectionY : Int) {
    North(0, -1) {
        override fun turnRight(): Facing {
            return East
        }

        override fun turnLeft() : Facing {
            return West
        }
    },
    East(1, 0) {
        override fun turnRight(): Facing {
            return South
        }

        override fun turnLeft() : Facing {
            return South
        }
    },
    South(0, 1) {
        override fun turnRight(): Facing {
            return West
        }

        override fun turnLeft() : Facing {
            return East
        }
    },
    West(-1, 0) {
        override fun turnRight(): Facing {
            return North
        }

        override fun turnLeft() : Facing {
            return South
        }
    };

    abstract fun turnRight(): Facing
    abstract fun turnLeft(): Facing
}