package ca.djmorton.aoc2024

class Translation(val x : Int, val y : Int) {
    fun invert() : Translation {
        return Translation(x * -1, y * -1)
    }
}