import java.math.BigInteger

fun <K, V> Map<K, V>.zip(other: Map<K, V>, onConflict: (V, V)-> V)=
  (this.asSequence() + other.asSequence()).groupBy { it.key }.mapValues { it.value.map { it.value }.reduce(onConflict) }

fun Map<Int, BigInteger>.processBirths(): Map<Int, BigInteger> {
  if (all { it.key >= 0 }) {
    return this
  } else {
    val toBirth = this.filter {it.key < 0}.mapKeys { it.key + 7 }
    return toBirth.zip(toBirth.mapKeys {it.key + 2}) { a, b -> a + b }.zip(filter {it.key >= 0}) { a, b -> a + b }.processBirths()
  }
}

fun withDays(input: Map<Int, BigInteger>, days: Int) =
   input.mapKeys { it.key - days }.processBirths().values.reduce { a, b -> a + b }

fun main() {
  val input = readInputAll("day6").trim().split(",").map { it.toInt() }.groupBy { it }.mapValues { it.value.size.toBigInteger() }

  listOf(80, 256, 10000).forEach{ println("${it.toString().padStart(6)}: ${withDays(input, it)}") }
}