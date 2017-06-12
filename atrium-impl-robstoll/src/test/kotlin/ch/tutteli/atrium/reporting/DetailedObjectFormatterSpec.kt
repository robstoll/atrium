package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerb
import ch.tutteli.atrium.assert
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.DetailedObjectFormatter.Companion.INDENT
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.toBe
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.reflect.KClass

object DetailedObjectFormatterSpec : Spek({
    val testee = DetailedObjectFormatter(UsingDefaultTranslator())

    describe("format") {
        on("null type") {
            val i: Int? = null
            val result = testee.format(i)
            it("returns null") {
                assert(result).toBe("null")
            }
        }

        on("a ${RawString::class.simpleName}") {
            val result = testee.format(RawString("hello"))
            it("returns the containing string") {
                assert(result).toBe("hello")
            }
        }

        on("a ${TranslatableRawString::class.simpleName}") {
            val translation = "es gilt"
            val translator = mock<ITranslator> {
                on { translate(AssertionVerb.ASSERT) } doReturn translation
            }
            val testeeWithMockedTranslation = DetailedObjectFormatter(translator)
            val result = testeeWithMockedTranslation.format(TranslatableRawString(AssertionVerb.ASSERT))
            it("returns the translated string") {
                assert(result).toBe(translation)
            }
        }

        on("a ${Class::class.simpleName}") {
            val result = testee.format(DetailedObjectFormatterSpec::class.java)
            it("returns its simpleName and name in parenthesis") {
                val clazz = DetailedObjectFormatterSpec::class.java
                assert(result).toBe("${clazz.simpleName} (${clazz.name})")
            }
        }

        group("on a ${KClass::class.simpleName}") {

            context("java Class is the same (no special Kotlin class)") {
                val result = testee.format(DetailedObjectFormatterSpec::class)
                it("returns the simpleName and qualified in parenthesis") {
                    val clazz = DetailedObjectFormatterSpec::class
                    assert(result).toBe("${clazz.java.simpleName} (${clazz.java.name})")
                    assert(result).toBe("${clazz.simpleName} (${clazz.qualifiedName})")
                }
            }

            context("a primitive Kotlin wrapper class (java Class is not the same)") {
                val result = testee.format(Int::class)
                it("returns the simpleName and qualified in parenthesis including java's simpleName") {
                    val clazz = Int::class
                    assert(result).toBe("${clazz.simpleName} (${clazz.qualifiedName}) -- Class: ${clazz.java.simpleName}")
                }
            }

            context("a non primitive Kotlin class (java Class is not the same)") {
                val result = testee.format(CharSequence::class)
                it("returns the simpleName and qualified in parenthesis including java's simpleName") {
                    val clazz = CharSequence::class
                    assert(result).toBe("${clazz.simpleName} (${clazz.qualifiedName}) -- Class: ${clazz.java.simpleName} (${clazz.java.name})")
                }
            }
        }

        on("a ${String::class.simpleName}") {
            it("returns two quotes including identity hash if empty ${String::class.simpleName}") {
                val string = ""
                val result = testee.format(string)
                assert(result).toBe("\"\"" + INDENT + "<${System.identityHashCode(string)}>")
            }
            it("returns ${String::class.simpleName} in quotes including identity hash") {
                val string = "atrium"
                val result = testee.format(string)
                assert(result).toBe("\"$string\"" + INDENT + "<${System.identityHashCode(string)}>")
            }
            it("return line breaks (does not escape") {
                val string = "atrium\nAn assertion framework for Kotlin"
                val result = testee.format(string)
                assert(result).toBe("\"$string\"" + INDENT + "<${System.identityHashCode(string)}>")
            }
        }

        val typeNameAndHash = "including type name and identity hash"

        on("a ${CharSequence::class.simpleName} besides ${String::class.simpleName}") {
            it("returns two quotes $typeNameAndHash if empty ${CharSequence::class.simpleName}") {
                val value = StringBuilder("")
                val result = testee.format(value)
                assert(result).toBe("\"\"" + INDENT
                    + "(${value::class.java.name} <${System.identityHashCode(value)}>)")
            }
            it("returns ${CharSequence::class.simpleName} in quotes $typeNameAndHash") {
                val value = StringBuilder("atrium")
                val result = testee.format(value)
                assert(result).toBe("\"$value\"" + INDENT
                    + "(${value::class.java.name} <${System.identityHashCode(value)}>)")
            }
        }

        mapOf(
            Boolean::class.java.simpleName to false,
            Byte::class.java.simpleName to 1.toByte(),
            Short::class.java.simpleName to 1.toShort(),
            Int::class.java.simpleName to 1,
            Long::class.java.simpleName to 1L,
            Float::class.java.simpleName to 1.0f,
            Double::class.java.simpleName to 1.0
        ).forEach { (typeName, value) ->
            on(typeName) {
                val result = testee.format(value)
                it("returns ${IAssertionPlant<*>::subject.name}.toString() $typeNameAndHash") {
                    assert(result).toBe(value.toString() + INDENT
                        + "(${value::class.java.name} <${System.identityHashCode(value)}>)")
                }
            }
        }
    }
})
