package ca.djmorton.aoc2024.day7.part1

import java.io.File

class CalibrationEvaluator(filename : String) {
    private var equations = ArrayList<CalibrationEquation>()

    init {
        equations.addAll(File(filename).readLines().map { line ->
            CalibrationEquation(line)
        })
    }

    fun evaluate(includeConcat : Boolean) : Long {
        return equations.map { equation ->
            var firstOperand = equation.operands.first()
            var remainingOperands = equation.operands.drop(1)

            val plusValue = firstOperand + remainingOperands.first()
            val mulValue = firstOperand * remainingOperands.first()
            val concatValue = concat(firstOperand, remainingOperands.first())

            val valueList = ArrayList<Long>()
            valueList.add(plusValue)
            valueList.add(mulValue)
            if(includeConcat) {
                valueList.add(concatValue)
            }

            var results = calculate(valueList, remainingOperands.drop(1), includeConcat)
            if (results.any { result -> result == equation.calculationTotal }) {
                equation.calculationTotal
            } else {
                0
            }
        }.sum()
    }

    fun calculate(values : List<Long>, remainingOperands : List<Long>, includeConcat : Boolean) : List<Long> {
        if (remainingOperands.isEmpty()) {
            return values
        }

        val updatedValues = values.flatMap { v ->
            if (includeConcat) {
                listOf(v + remainingOperands.first(), v * remainingOperands.first(), concat(v, remainingOperands.first()))
            } else {
                listOf(v + remainingOperands.first(), v * remainingOperands.first())

            }
        }

        return calculate(updatedValues, remainingOperands.drop(1), includeConcat)
    }

    private fun concat(p1 : Long, p2 : Long) : Long {
        return (p1.toString() + p2.toString()).toLong();
    }
}