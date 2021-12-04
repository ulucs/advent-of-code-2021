class BingoCard(card: String) {
  val rows: List<List<Int>> = card.lines().map {it.split(" ").filter {it.length > 0}.map {it.toInt()}}.filter{it.size>0}
  val checked: MutableSet<Int> = mutableSetOf()
  var last: Int = 0
  
  fun isComplete() : Boolean { 
   return  rows.any { it.all { checked.contains(it) } } || rows.transpose().any { it.all { checked.contains(it) } }
   }
  fun uncheckedNums() : List<Int> {
    return rows.flatten()
        .filter { !checked.contains(it) }
  }
  fun check(num: Int) { 
    val before = isComplete()
    checked.add(num)
    val after = isComplete()
    if (!before && after) {
      last = num
    }
  }
}

fun List<Int>.prod(): Int {
  return this.reduce { it, acc -> it * acc }
}

fun main() {
  fun parseInp(inp: String): Pair<List<Int>, List<BingoCard>> {
    val i = inp.split("\n\n")
    val nums = i.first().split(",").map {it.toInt()}
    val cards = i.drop(1).map {BingoCard(it)}

    return Pair(nums, cards)
  }

  fun part1(inp: String): Int {
    val (nums, cards) = parseInp(inp)
    for (num in nums) {
      cards.forEach { it.check(num) }
      val complete = cards.find { it.isComplete() }
      if (complete !== null) {
        complete.checked.inspect()
        complete.rows.inspect()
        return num.inspect() * complete.uncheckedNums().inspect().sum()
      }
    }
    return 0
  }

  fun part2(inp: String): Int {
    val (nums, cards) = parseInp(inp)
    nums.forEach { num ->
      cards.forEach { it.check(num) }
      if (cards.all() {it.isComplete()}) {
        cards.filter { it.last == num}.inspect()
        val last = cards.find { it.last == num }
        return num.inspect() * last!!.uncheckedNums().inspect().sum()
      }
    }
    return 0
  }

  val input = readInputAll("day4")
  println(part1(input))
  println(part2(input))

}