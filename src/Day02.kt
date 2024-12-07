import kotlin.math.abs

fun main() {
    val testInput = readInput("Day02_test")
    val realInput = readInput("Day02")

    check(part1(testInput) == 2)
    part1(realInput).println()

    check(part2(testInput) == 4)
    part2(realInput).println()
}

fun part1(input: List<String>): Int = input
    .map { it.split(" ").map(String::toInt) }
    .map { it.processLine() }
    .reduce { acc, i -> acc + i }

fun part2(input: List<String>): Int =
    input
        .map { it.split(" ").map(String::toInt) }
        .map { it.processAllLines() }
        .reduce { acc, i -> acc + i }

private fun List<Int>.processLine(): Int {
    var isIncrease = false
    for ((index, i) in withIndex()) {
        when (index) {
            0 -> {
                isIncrease = i < get(1)
                val diff = abs(get(1) - i)
                if (diff.isInvalid()) return 0
            }

            lastIndex -> return 1
            else -> {
                val prev = get(index - 1)
                val next = get(index + 1)
                val prevDiff = abs(prev - i)
                val nextDiff = abs(next - i)
                if (prevDiff.isInvalid() || nextDiff.isInvalid()) return 0

                if (isIncrease) {
                    if (prev > i) return 0
                    if (next < i) return 0
                } else {
                    if (prev < i) return 0
                    if (next > i) return 0
                }
            }
        }
    }

    return 0
}

private fun List<Int>.processAllLines(): Int {
    if (processLine() == 1) return 1

    for (removalIndex in indices) {
        val modified = toMutableList()
        modified.removeAt(removalIndex)
        if (modified.processLine() == 1) return 1
    }

    return 0
}

private fun Int.isInvalid() = this > MAX_DIFF || this == 0

private const val MAX_DIFF = 3
