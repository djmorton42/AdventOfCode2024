package ca.djmorton.aoc2024.day8.part1

import ca.djmorton.aoc2024.Coord
import ca.djmorton.aoc2024.TwoDimensionalMap
import java.io.File

class AntinodeCalculator(private val filename : String) {
    private lateinit var antennaMap : TwoDimensionalMap

    init {
        antennaMap = TwoDimensionalMap(File(filename).readLines())
    }

    fun calculateDistinctTFrequencyAntinodes() : Int {
        var distinctChars = antennaMap.getDistinctChars().minus('.')

        var antinodes = HashSet<Coord>()
        distinctChars.map { c ->
            calculateTFrequencyAntinodePositions(c)
        }.forEach { antinodeList -> antinodes.addAll(antinodeList) }

        return antinodes.size
    }

    fun calculateDistinctAntinodes() : Int {
        var distinctChars = antennaMap.getDistinctChars().minus('.')

        var antinodes = HashSet<Coord>()
        distinctChars.map { c ->
            calculateAntinodePositions(c).filter { coord -> antennaMap.isOnMap(coord) }
        }.forEach { antinodeList -> antinodes.addAll(antinodeList) }

        return antinodes.size
    }

    private fun calculateTFrequencyAntinodePositions(antenna : Char) : List<Coord> {
        val coordsForChar = antennaMap.getCoordsForChar(antenna)

        return calculateTFrequencyAntinodePositions(coordsForChar.first(), coordsForChar.drop(1))
    }

    private fun calculateTFrequencyAntinodePositions(firstCoord : Coord, otherCoords : List<Coord>) : List<Coord> {
        val antinodes = ArrayList<Coord>()

        if (otherCoords.isEmpty()) {
            return antinodes
        }

        otherCoords.forEach { coord ->
            antinodes.add(firstCoord)

            var translation = firstCoord.translation(coord)
            var inverseTranslation = translation.invert()

            var antinodePosition = firstCoord.apply(translation)
            while (antennaMap.isOnMap(antinodePosition)) {
                antinodes.add(antinodePosition)
                antinodePosition = antinodePosition.apply(translation)
            }

            antinodePosition = firstCoord.apply(inverseTranslation)
            while (antennaMap.isOnMap(antinodePosition)) {
                antinodes.add(antinodePosition)
                antinodePosition = antinodePosition.apply(inverseTranslation)
            }
        }

        antinodes.addAll(calculateTFrequencyAntinodePositions(otherCoords.first(), otherCoords.drop(1)))
        return antinodes
    }


    private fun calculateAntinodePositions(antenna : Char) : List<Coord> {
        val coordsForChar = antennaMap.getCoordsForChar(antenna)

        return calculateAntinodePositions(coordsForChar.first(), coordsForChar.drop(1))
    }

    private fun calculateAntinodePositions(firstCoord : Coord, otherCoords : List<Coord>) : List<Coord> {
        val antinodes = ArrayList<Coord>()

        if (otherCoords.isEmpty()) {
            return antinodes
        }

        otherCoords.forEach { coord ->
            var translation = firstCoord.translation(coord)
            var inverseTranslation = translation.invert()

            antinodes.add(coord.apply(translation))
            antinodes.add(firstCoord.apply(inverseTranslation))
        }

        antinodes.addAll(calculateAntinodePositions(otherCoords.first(), otherCoords.drop(1)))
        return antinodes
    }
}