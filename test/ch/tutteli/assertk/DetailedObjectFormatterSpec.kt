package ch.tutteli.assertk

import org.jetbrains.spek.api.Spek
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
            it("returns two quotes including identity hash if empty CharSequence") {
                val string = ""
                val result = testee.format(string)
                assert(result).toBe("\"\"   <${System.identityHashCode(string)}>")
            }
            it("returns CharSequence in quotes including identity hash") {
                val string = "assertK"
                val result = testee.format(string)
                assert(result).toBe("\"$string\"   <${System.identityHashCode(string)}>")
            }
        }

        on("CharSequence as any") {
            it("returns two quotes  including identity hash if empty CharSequence") {
                val string : Any = ""
                val result = testee.format(string)
                assert(result).toBe("\"\"   <${System.identityHashCode(string)}>")
            }
            it("returns CharSequence in quotes including identity hash") {
                val string: Any = "assertK"
                val result = testee.format(string)
                assert(result).toBe("\"$string\"   <${System.identityHashCode(string)}>")
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
                it("returns ${IAssertionFactory<Any>::subject.name}.toString() including type name and identity hash") {
                    assert(result).toBe(it.value.toString()
                        + "   (" + it.value::class.java.name
                        + "<" + System.identityHashCode(it.value) + ">"
                        + ")")
                }
            }
        }
    }
})
