import kotlin.math.*

fun main() {
    fun List<Float>.mean(): Float {
        return this.sum() / this.size
    }

    fun List<Int>.b2i(k:Int=2): Int {
        return this
            .reversed()
            .reduceIndexed { i, acc, v -> acc + v * k.toFloat().pow(i).toInt() }
    }

    fun parseInp(input:List<String>):List<List<Float>> {
        return  input.map { it.split("").filter { it.length > 0 }.map {it.toFloat()} }
    }

    val inp = parseInp(readInput("day3"))

    fun part1(inp: List<List<Float>>) : Int {
        val gam = inp
            .transpose()
            .map { it.mean().roundToInt() }

        val ep = inp
            .transpose()
            .map { 1 - it.mean().roundToInt() }

        return  gam.b2i()*ep.b2i()
    }


    fun part2(inp: List<List<Float>>): Int {
        fun ox(keys: List<List<Float>>): List<Int> {
            if (keys.isEmpty()) {return listOf()}
            
            keys.transpose().map {it.first()}.mean()

        }


        val ox = input.map { it.split("").filter { it.length > 0 }.map {it.toInt()} }
                .minByOrNull { it.zip(gam) {a,b->Math.abs(a-b)}.b2i() } ?: listOf(0)

        val co = input.map  { it.split("").filter { it.length > 0 }.map {it.toInt()} }
                .minByOrNull { it.zip(ep) {a,b->Math.abs(a-b)}.b2i() } ?: listOf(0)

        return ox.inspect().b2i()*co.inspect().b2i()
    }

    println()
    println(part2(input))
}
