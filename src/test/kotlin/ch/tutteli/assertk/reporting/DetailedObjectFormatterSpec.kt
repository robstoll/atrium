package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.creating.IAssertionPlant
import ch.tutteli.assertk.describe
import ch.tutteli.assertk.toBe
import ch.tutteli.assertk.verbs.assert.assert
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

        on("a ${RawString::class.java.simpleName}") {
            val result = testee.format(RawString("hello"))
            it("returns the containing string") {
                assert(result).toBe("hello")
            }
        }

        on("class type") {
            val result = testee.format(DetailedObjectFormatterSpec::class.java)
            it("returns its simpleName and name in parenthesis") {
                val clazz = DetailedObjectFormatterSpec::class.java
                assert(result).toBe("${clazz.simpleName} (${clazz.name})")
            }
        }

        on("a ${String::class.java.simpleName}") {
            it("returns two quotes including identity hash if empty ${String::class.java.simpleName}") {
                val string = ""
                val result = testee.format(string)
                assert(result).toBe("\"\"   <${System.identityHashCode(string)}>")
            }
            it("returns ${String::class.java.simpleName} in quotes including identity hash") {
                val string = "assertK"
                val result = testee.format(string)
                assert(result).toBe("\"$string\"   <${System.identityHashCode(string)}>")
            }
        }

        val typeNameAndHash = "including type name and identity hash"

        on("a ${CharSequence::class.java.simpleName} besides ${String::class.java.simpleName}") {
            it("returns two quotes $typeNameAndHash if empty ${CharSequence::class.java.simpleName}") {
                val value = StringBuilder("")
                val result = testee.format(value)
                assert(result).toBe("\"\""
                    + "   (${value::class.java.name} <${System.identityHashCode(value)}>)")
            }
            it("returns ${CharSequence::class.java.simpleName} in quotes $typeNameAndHash") {
                val value = StringBuilder("assertK")
                val result = testee.format(value)
                assert(result).toBe("\"$value\""
                    + "   (${value::class.java.name} <${System.identityHashCode(value)}>)")
            }
        }

        mapOf(
            Boolean::class.java.simpleName to false,
            Short::class.java.simpleName to 1.toShort(),
            Int::class.java.simpleName to 1,
            Long::class.java.simpleName to 1L,
            Float::class.java.simpleName to 1.0f,
            Double::class.java.simpleName to 1.0
        ).forEach { (typeName, value) ->
            on(typeName) {
                val result = testee.format(value)
                it("returns ${IAssertionPlant<*>::subject.name}.toString() $typeNameAndHash") {
                    assert(result).toBe(value.toString()
                        + "   (${value::class.java.name} <${System.identityHashCode(value)}>)")
                }
            }
        }
    }
})
