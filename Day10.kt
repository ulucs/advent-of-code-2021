fun main() {
    val errors = mapOf(")" to 3, "}" to 1197, "]" to 57, ">" to 25137).withDefault { 0 }
    val matches = mapOf(')' to '(', '}' to '{', ']' to '[', '>' to '<')
    var costs = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)

  fun part1(input: List<String>) : Int {
    return input.map { line ->
        line.fold("") { opens, n ->
            if ( matches.containsKey(n) ) {
                val last = opens.last()
                if (last == matches.getValue(n)) {
                  return@fold opens.dropLast(1)
                }
                return@map n.toString()
            } else {
                opens + n
            }
          }
        }.map { errors.getValue(it) }.sum()
  }

  fun part2(input: List<String>): Long {
    val scores = input.map { line ->
        line.fold("") { opens, n ->
            if ( matches.containsKey(n) ) {
                val last = opens.last()
                if (last == matches.getValue(n)) {
                  return@fold opens.dropLast(1)
                }
                return@map n.toString()
            } else {
                opens + n
            }
          }
        }.filter { it.length > 0 && errors[it] == null }
        .map { it.reversed().fold(0L) { acc, n -> acc*5 + costs.getValue(n) } }
        .sorted().inspect()

        return scores[scores.size/2]
  }

  val input = readInput("day10");

  println("Part 1: ${part1(input)}")
  println("Part 2: ${part2(input)}")
}