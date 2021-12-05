import kotlin.math.sign

fun<T> List<T>.elongate(n: Int): List<T> {
  if (n == size) return this
  return generateSequence { this[0] }.take(n).toList()
}

fun Int.towards(other: Int): List<Int> {
  if (this == other) return listOf(this)
  return generateSequence(this) { it + (other - this).sign }
    .takeWhile { (other - it)*(other - this) >= 0 }.toList()
}

data class Point(val x: Int, val y: Int) {
  fun rangeTo(other: Point): List<Point> {
    val llen = Math.max(Math.abs(x - other.x), Math.abs(y - other.y)) + 1 
    return x.towards(other.x).elongate(llen)
      .zip(y.towards(other.y).elongate(llen)) { x, y -> Point(x, y) }
  }
}

data class Line(val start: Point, val end: Point) {
  val isStraight : Boolean
    get() = start.x == end.x || start.y == end.y

  val points : List<Point> 
    get() = start.rangeTo(end)
}

fun main() {
  fun part1(lines: List<Line>) =
    lines.filter { it.isStraight }.flatMap { it.points }.groupBy { it }.count { it.value.size > 1 }

  fun part2(lines: List<Line>) =
    lines.flatMap { it.points }.groupBy { it }.count { it.value.size > 1 }

  val input = readInput("day5").map {it.split(" -> ")
      .map { it.split(",").filter{it.length>0}.map{it.toInt()} }
      .map { Point(it[0], it[1]) } }
      .map { Line(it[0], it[1]) }
      
  println(part1(input))
  println(part2(input))
}