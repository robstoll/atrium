package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.robstoll.lib.reporting.DetailedObjectFormatterCommon.Companion.INDENT
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.reporting.ObjectFormatterSpec
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KClass

object DetailedObjectFormatterSpec : Spek({
    include(AtriumsObjectFormatterSpec)

    val testee = DetailedObjectFormatter(UsingDefaultTranslator())

    describe("format") {

        context("a ${Char::class.simpleName}") {
            val result = testee.format('a')
            it("returns the ${Char::class.simpleName} in apostrophes") {
                expect(result).toBe("'a'")
            }
        }

        context("a ${Boolean::class.simpleName}") {
            it("returns the toString representation of the ${Boolean::class.simpleName}") {
                expect(testee.format(true)).toBe("true")
                expect(testee.format(false)).toBe("false")
            }
        }

        context("a ${String::class.simpleName}") {
            it("returns two quotes including identity hash if empty ${String::class.simpleName}") {
                val string = ""
                val result = testee.format(string)
                expect(result).toBe("\"\"" + INDENT + "<${System.identityHashCode(string)}>")
            }
            it("returns the ${String::class.simpleName} in quotes including identity hash") {
                val string = "atrium"
                val result = testee.format(string)
                expect(result).toBe("\"$string\"" + INDENT + "<${System.identityHashCode(string)}>")
            }
            it("returns line breaks (does not escape") {
                val string = "atrium\nAn assertion framework for Kotlin"
                val result = testee.format(string)
                expect(result).toBe("\"$string\"" + INDENT + "<${System.identityHashCode(string)}>")
            }
        }

        val typeNameAndHash = "including type name and identity hash"

        context("a ${CharSequence::class.simpleName} besides ${String::class.simpleName}") {
            it("returns two quotes $typeNameAndHash if empty ${CharSequence::class.simpleName}") {
                val value = StringBuilder("")
                val result = testee.format(value)
                expect(result).toBe(
                    "\"\"" + INDENT
                        + "(${value::class.qualifiedName} <${System.identityHashCode(value)}>)"
                )
            }
            it("returns ${CharSequence::class.simpleName} in quotes $typeNameAndHash") {
                val value = StringBuilder("atrium")
                val result = testee.format(value)
                expect(result).toBe(
                    "\"$value\"" + INDENT
                        + "(${value::class.qualifiedName} <${System.identityHashCode(value)}>)"
                )
            }
        }

        context("an enum") {
            val enum = Color.Red
            val result = testee.format(Color.Red)
            it("returns its toString representation together with its Class.name but without System.identityHash") {
                expect(result).toBe("Red" + INDENT + "(${enum::class.java.name})")
            }
        }

        context("a Throwable") {
            val result = testee.format(AssertionError("blablabla"))
            it("returns only its Class.name") {
                expect(result).toBe(AssertionError::class.java.name)
            }
        }

        context("a ${Class::class.simpleName}") {
            val result = testee.format(DetailedObjectFormatterSpec::class.java)
            it("returns its simpleName and name in parenthesis") {
                val clazz = DetailedObjectFormatterSpec::class.java
                expect(result).toBe("${clazz.simpleName} (${clazz.name})")
            }
        }

        context("on a ${KClass::class.simpleName}") {

            context("java Class is the same (no special Kotlin class)") {
                val result = testee.format(DetailedObjectFormatterSpec::class)
                it("returns the simpleName and qualified in parenthesis") {
                    val clazz = DetailedObjectFormatterSpec::class
                    expect(result).toBe("${clazz.java.simpleName} (${clazz.java.name})")
                    expect(result).toBe("${clazz.simpleName} (${clazz.qualifiedName})")
                }
            }

            context("a primitive Kotlin wrapper class (java Class is not the same)") {
                val result = testee.format(Int::class)
                it("returns the simpleName and qualified in parenthesis including java's simpleName") {
                    val clazz = Int::class
                    expect(result).toBe("${clazz.simpleName} (${clazz.qualifiedName}) -- Class: ${clazz.java.simpleName}")
                }
            }

            context("a non primitive Kotlin class (java Class is not the same)") {
                val result = testee.format(CharSequence::class)
                it("returns the simpleName and qualified in parenthesis including java's name") {
                    val clazz = CharSequence::class
                    expect(result).toBe("${clazz.simpleName} (${clazz.qualifiedName}) -- Class: ${clazz.java.name}")
                }
            }
        }

        mapOf(
            java.lang.Byte::class.java.simpleName to 1.toByte(),
            java.lang.Short::class.java.simpleName to 1.toShort(),
            java.lang.Integer::class.java.simpleName to 1,
            java.lang.Long::class.java.simpleName to 1L,
            java.lang.Float::class.java.simpleName to 1.0f,
            java.lang.Double::class.java.simpleName to 1.0
        ).forEach { (typeName, value) ->
            context(typeName) {
                val result = testee.format(value)
                it("returns subject's toString() $typeNameAndHash") {
                    expect(result).toBe(
                        value.toString() + INDENT
                            + "(${value::class.qualifiedName} <${System.identityHashCode(value)}>)"
                    )
                }
            }
        }

        context("an anonymous class") {
            val anonymous = object : Any() {
                override fun toString(): String = "anonymous type"
            }
            val result = testee.format(anonymous)
            it("returns subject's toString() $typeNameAndHash") {
                expect(result).toBe(
                    anonymous.toString() + INDENT
                        + "(${anonymous::class.java.name} <${System.identityHashCode(anonymous)}>)"
                )
            }
        }
    }
}) {
    object AtriumsObjectFormatterSpec : ObjectFormatterSpec(::DetailedObjectFormatter)
}

private enum class Color {
    Red //, Blue, Green
}
