import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("inputs", name).readLines()

fun readInputAll(name: String) = File("inputs", name).readText()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun <T> T.inspect(): T {
        println(this)
        return this
    }

    
    fun <T> List<List<T>>.transpose(): List<List<T>> {
        val result = mutableListOf<List<T>>()
        for (i in 0 until this[0].size) {
            val row = mutableListOf<T>()
            for (j in 0 until this.size) {
                row.add(this[j][i])
            }
            result.add(row)
        }
        return result
    }