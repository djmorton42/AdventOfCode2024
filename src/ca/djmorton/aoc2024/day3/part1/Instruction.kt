package ca.djmorton.aoc2024.day3.part1

class Instruction(var firstValue : Int, var secondValue : Int) {
    fun getProduct() : Int {
        return firstValue * secondValue
    }
}