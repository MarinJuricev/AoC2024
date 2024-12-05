import kotlin.math.abs

fun main() {
    val testInput = readInput("Day02_test")
    val realInput = readInput("Day02")

//    check(part1(testInput) == 2)
//    part1(realInput).println()

    check(part2(testInput) == 4)
//    part2(realInput).println()
}

fun part1(input: List<String>): Int {
    val processedLines = input.map { it.split(" ").map(String::toInt) }
    var result = 0

    for (line in processedLines) {
        var isIncrease = false
        for ((index, i) in line.withIndex()) {
            when (index) {
                0 -> {
                    isIncrease = i < line[1]
                    val diff = abs(line[1] - i)
                    if (diff.isInvalid()) break
                }

                line.lastIndex -> result++
                else -> {
                    val prev = line[index - 1]
                    val next = line[index + 1]
                    val prevDiff = abs(prev - i)
                    val nextDiff = abs(next - i)
                    if (prevDiff.isInvalid() || nextDiff.isInvalid())
                        break

                    if (isIncrease) {
                        if (prev > i)
                            break
                        if (next < i)
                            break
                    } else {
                        if (prev < i)
                            break
                        if (next > i)
                            break
                    }
                }
            }
        }
    }

    return result
}

fun part2(input: List<String>): Int {

    return 0
}

private fun Int.isInvalid() = this > MAX_DIFF || this == 0

private const val MAX_DIFF = 3