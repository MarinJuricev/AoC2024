import kotlin.collections.flatten

fun main() {
    val testInput = readInput("Day03_test")
    val testInput2 = readInput("Day03Part2_test")
    val realInput = readInput("Day03")

    check(part1(testInput) == 161)
    part1(realInput).println()

    check(part2(testInput2) == 48)
    part2(realInput).println()
}

private fun part1(lines: List<String>) = lines.sumOf { line ->
    regex.findAll(line)
        .map(MatchResult::multiply)
        .sum()
}

private fun part2(lines: List<String>): Int {
    var isEnabled = true

    return lines.sumOf { line ->
        regexPart2.findAll(line)
            .map { group ->
                when (group.value) {
                    "do()" -> {
                        isEnabled = true
                        return@map 0
                    }

                    "don't()" -> {
                        isEnabled = false
                        return@map 0
                    }
                }
                if (!isEnabled) return@map 0
                group.multiply()
            }.sum()
    }
}

private fun MatchResult.multiply(): Int {
    val firstNumber = value.substring(
        startIndex = value.indexOf('(') + 1,
        endIndex = value.indexOf(','),
    )
    val secondNumber = value.substring(
        startIndex = value.indexOf(',') + 1,
        endIndex = value.indexOf(')'),
    )

    return firstNumber.toInt() * secondNumber.toInt()
}


val regex = Regex("mul\\((\\d+),(\\d+)\\)")
val regexPart2 = Regex("(don't\\(\\)|do\\(\\))|mul\\((\\d+),(\\d+)\\)")

