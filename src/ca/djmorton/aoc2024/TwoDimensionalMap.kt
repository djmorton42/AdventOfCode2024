package ca.djmorton.aoc2024

import java.io.File

class TwoDimensionalMap(private val lines : List<String>) {
    private lateinit var map : List<MutableList<Char>>;

    init {
        map = lines.map { line ->
            line.map { c -> c }.toMutableList()
        }.toList()
    }

    fun printMap(filename : String) {
        File(filename).writeText(map.map { line -> line.joinToString("") }.joinToString("\n"))
    }

    fun duplicate() : TwoDimensionalMap {
        return TwoDimensionalMap(lines)
    }

    fun width() : Int {
        return map.first().size
    }

    fun height() : Int {
        return map.size
    }

    fun getCoord(coord : Coord) : Char {
        return getCoord(coord.x, coord.y)
    }

    fun isOnMap(coord : Coord) : Boolean {
        return !(coord.x >= width() || coord.y >= height() || coord.x < 0 || coord.y < 0)
    }

    fun getCoord(x : Int, y : Int) : Char {
        if (x >= width() || y >= height() || x < 0 || y < 0) {
            throw CoordinateOffMapException(x, y, width(), height())
        }
        return map[y][x]
    }

    fun setCharAtCoord(coord : Coord, char : Char) {
        setCharAtCoord(coord.x, coord.y, char)
    }

    fun setCharAtCoord(x : Int, y : Int, char : Char) {
        map[y][x] = char
    }

    fun getDistinctChars() : Set<Char> {
        return map.map { l ->
            HashSet<Char>(l)
        }.fold(HashSet<Char>()) { acc, lineset ->
            acc.addAll(lineset)
            acc
        }
    }

    fun getCoordsForChar(char : Char) : List<Coord> {
        val coords = ArrayList<Coord>()
        map.forEachIndexed { index, chars ->
            chars.forEachIndexed { jndex, mapChar ->
                if (mapChar == char) {
                    coords.add(Coord(jndex, index))
                }
            }
        }
        return coords
    }
}