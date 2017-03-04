package ch.tutteli.assertk.reporting

import ch.tutteli.assertk.IAssertionFactory
import ch.tutteli.assertk.describe
import ch.tutteli.assertk.reporting.DetailedObjectFormatter
import ch.tutteli.assertk.reporting.RawString
import ch.tutteli.assertk.toBe
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
                ch.tutteli.assertk.assert(result).toBe("null")
            }
        }

        on(RawString::class.java.simpleName){
            val result = testee.format(RawString("hello"))
            it("returns the containing string") {
                ch.tutteli.assertk.assert(result).toBe("hello")
            }
        }

        on("class type") {
            val result = testee.format(DetailedObjectFormatterSpec::class.java)
            it("returns its simpleName and name in parenthesis") {
                val clazz = DetailedObjectFormatterSpec::class.java
                ch.tutteli.assertk.assert(result).toBe("${clazz.simpleName} (${clazz.name})")
            }
        }

        on("CharSequence") {
            it("returns two quotes including identity hash if empty CharSequence") {
                val string = ""
                val result = testee.format(string)
                ch.tutteli.assertk.assert(result).toBe("\"\"   <${System.identityHashCode(string)}>")
            }
            it("returns CharSequence in quotes including identity hash") {
                val string = "assertK"
                val result = testee.format(string)
                ch.tutteli.assertk.assert(result).toBe("\"$string\"   <${System.identityHashCode(string)}>")
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
                it("returns ${IAssertionFactory<*>::subject.name}.toString() including type name and identity hash") {
                    ch.tutteli.assertk.assert(result).toBe(it.value.toString()
                        + "   (" + it.value::class.java.name
                        + "<" + System.identityHashCode(it.value) + ">"
                        + ")")
                }
            }
        }
    }
})
