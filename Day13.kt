enum class Dir { x , y }

fun fold(points: Set<Pair<Int, Int>>, instr: Pair<Dir, Int>): Set<Pair<Int, Int>> {
  val (direction, foldp) = instr;
  return points.map { (x, y) ->
    when (direction) {
      Dir.x -> Pair(if (x < foldp) x else 2*foldp - x, y)
      Dir.y -> Pair(x, if (y < foldp) y else 2*foldp - y)
    }
  }.toSet()
}

fun main() {
  fun part1(points: Set<Pair<Int, Int>>, instructions: List<Pair<Dir, Int>>) =
    instructions.take(1).fold(points) { acc, instr -> fold(acc, instr) }.size

  fun part2(points: Set<Pair<Int, Int>>, instructions: List<Pair<Dir, Int>>): String {
    val folded = instructions.fold(points) { acc, instr -> fold(acc, instr) }
    val (sx, sy) = folded.reduce { (x,y), (z,q) -> Pair(Math.max(x,z), Math.max(y,q)) }
    return (0..sy).map { y ->
      (0..sx).map { x -> if (folded.contains(Pair(x,y))) '#' else ' ' }.joinToString(" ")
    }.joinToString("\n")
  }
    
  val (pts, insts) = readInputAll("day13").split("\n\n")
  
  val points = pts.split("\n")
    .filter{ it.length > 0 }
    .map { it.split(",").map { it.toInt() } }
    .map{ Pair(it[0], it[1])}.toSet()
    
  val instructions = insts.split("\n")
    .filter { it.length > 0 }
    .map { it.split("=") }
    .map { Pair(Dir.valueOf(it[0].takeLast(1)), it[1].toInt()) }

  println("Part 1: ${part1(points, instructions)}")
  println("Part 2:\n${part2(points, instructions)}")
}