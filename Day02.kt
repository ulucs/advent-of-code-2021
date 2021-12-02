enum class Direction {
    forward,
    down,
    up
}

fun main() {
    fun parseInput(input: List<String>): List<Pair<Direction, Int>> {
        return input.map {
            val (direction, distance) = it.split(" ")
            Pair(Direction.valueOf(direction), distance.toInt())
        }
    }

    fun part1(input: List<String>): Int {
        val parsedInput = parseInput(input)
        val (x, y) = parsedInput.fold(Pair(0, 0)) { acc, (direction, distance) ->
            when (direction) {
                Direction.forward -> Pair(acc.first + distance, acc.second)
                Direction.down -> Pair(acc.first, acc.second - distance)
                Direction.up -> Pair(acc.first, acc.second + distance)
            }
        }
        return x * Math.abs(y)
    }

    fun part2(input: List<String>): Int {
        val parsedInput = parseInput(input)
        val (x, _, z) = parsedInput.fold(Triple(0, 0, 0)) { acc, (direction, distance) ->
            when (direction) {
                Direction.forward -> Triple(acc.first + distance, acc.second, acc.third + acc.second * distance)
                Direction.down -> Triple(acc.first, acc.second - distance, acc.third)
                Direction.up -> Triple(acc.first, acc.second + distance, acc.third)
            }
        }
        return x * Math.abs(z)
    }

    val input = readInput("day2")
    println(part1(input))
    println(part2(input))
}
