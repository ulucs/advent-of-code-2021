fun main() {
    fun part1(input: List<String>): Int {
         return input.map { it.toInt() }
             .windowed(2)
             .count { it[0] < it[1] }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toInt() }
            .windowed(3)
            .map { it[0] + it[1] + it[2] }
            .windowed(2)
            .count { it[0] < it[1] }
    }

    val input = readInput("day1")
    println(part1(input))
    println(part2(input))
}
