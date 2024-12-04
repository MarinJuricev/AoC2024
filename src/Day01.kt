fun main() {
    fun part1(input: List<String>): Int {
        val filteredInput = input.map { it.split("  ") }
        val firstList = filteredInput.map { it[0].trim().toInt() }.sorted()
        val secondList = filteredInput.map { it[1].trim().toInt() }.sorted()

        return firstList.zip(secondList).sumOf {
            if (it.first > it.second)
                it.first - it.second
            else
                it.second - it.first
        }
    }

    fun part2(input: List<String>): Int {
        val filteredInput = input.map { it.split("  ") }
        val firstList = filteredInput.map { it[0].trim().toInt() }
        val secondList = filteredInput.map { it[1].trim().toInt() }
        var result = 0

        for (element in firstList) {
            result += element * secondList.count { it == element }
        }

        return result
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")

    part1(input).println()
    part2(input).println()
}