package ch.tutteli.assertk

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class DetailedObjectFormatterSpec : Spek({
    val testee = DetailedObjectFormatter()

    describe("format") {
        on("null type") {
            val i: Int? = null
            val result = testee.format(i)
            it("returns null") {
                assert(result).toBe("null")
            }
        }

        on("class type") {
            val result = testee.format(DetailedObjectFormatterSpec::class.java)
            it("returns simple name and name in parenthesis") {
                val clazz = DetailedObjectFormatterSpec::class.java
                assert(result).toBe("${clazz.simpleName} (${clazz.name})")
            }
        }

        on("class type as any") {
            val result = testee.format(DetailedObjectFormatterSpec::class.java as Any)
            it("returns simple name and name in parenthesis") {
                val clazz = DetailedObjectFormatterSpec::class.java
                assert(result).toBe("${clazz.simpleName} (${clazz.name})")
            }
        }

        on("CharSequence") {
            it("returns two quotes if empty CharSequence") {
                val result = testee.format("")
                assert(result).toBe("\"\"")
            }
            it("returns CharSequence in quotes"){
                val result = testee.format("assertK")
                assert(result).toBe("\"assertK\"")
            }
        }

        on("CharSequence as any") {
            it("returns two quotes if empty CharSequence") {
                val result = testee.format("" as Any)
                assert(result).toBe("\"\"")
            }
            it("returns CharSequence in quotes"){
                val result = testee.format("assertK" as Any)
                assert(result).toBe("\"assertK\"")
            }
        }

        mapOf(
            Boolean::class.java.simpleName to false,
            Short::class.java.simpleName to 1.toShort(),
            Int::class.java.simpleName to 1,
            Long::class.java.simpleName to 1L,
            Float::class.java.simpleName to 1.0f,
            Double::class.java.simpleName to 1.0
        ).forEach {
            on(it.key) {
                val result = testee.format(it.value)
                it("returns toString representation including type name and identity hash") {
                    assert(result).toBe(it.value.toString()
                        + "   (" + it.value.javaClass.name
                        + "<" + System.identityHashCode(it.value) + ">"
                        + ")")
                }
            }
        }
    }
})
