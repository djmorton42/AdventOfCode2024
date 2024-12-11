package ca.djmorton.aoc2024

class CoordinateOffMapException(message : String) : RuntimeException(message) {

    constructor(
        x : Int,
        y : Int,
        width : Int,
        height : Int
    ) : this("Map coordinate ($x,$y) is out of map bounds. Map width is $width and map height is $height")
}