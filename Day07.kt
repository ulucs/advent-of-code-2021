import java.math.BigInteger

fun main() {
  fun part1(input: List<Int>) =
    input.map { a -> input.map {Math.abs(it - a)}.reduce {c, b -> c + b}}.minOrNull()!!
  
  fun part2(input: List<Int>) =
    (input.minOrNull()!!..input.maxOrNull()!!).map {
      a -> input
        .map { Math.abs(it - a) }
        .map {it.toBigInteger()}
        .map { it*(it+1.toBigInteger())/2.toBigInteger() }
        .reduce {c, b -> c + b}
      }.minOrNull()!!

  val input = readInputAll("day7").split(",").filter{it.length > 0}.map { it.toInt() }

  println("Part 1: ${part1(input)}")
  println("Part 2: ${part2(input)}")
}