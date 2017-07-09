package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.reporting.IMethodCallFormatter
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

@Suppress("UNUSED_PARAMETER")
abstract class TextMethodCallFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: () -> IMethodCallFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val testee = testeeFactory()

    prefixedDescribe("fun ${testee::format.name}") {

        context("a method call without arguments") {
            it("returns the name of the method with parentheses") {
                val result = testee.format(TextMethodCallFormatterSpec::methodWithoutArgument, arrayOf())()
                verbs.checkImmediately(result).toBe("${TextMethodCallFormatterSpec::methodWithoutArgument.name}()")
            }
        }

        context("a method call with arguments") {

            context("one argument of type Int") {
                it("returns the name of the method with its argument in parentheses") {
                    val result = testee.format(TextMethodCallFormatterSpec::methodArg1, arrayOf(1))()
                    verbs.checkImmediately(result).toBe("${TextMethodCallFormatterSpec::methodArg1.name}(1)")
                }
            }

            context("two arguments fo type Int and Float") {
                it("returns the name of the method, followed by the first and second argument in parentheses and separated by a comma") {
                    val result = testee.format(TextMethodCallFormatterSpec::methodArg2, arrayOf(1, 1.2))()
                    verbs.checkImmediately(result).toBe("${TextMethodCallFormatterSpec::methodArg2.name}(1, 1.2)")
                }
            }

            context("an argument of type Char") {
                it("returns the name of the method with its argument in parentheses whereas the argument is wrapped in apostrophes") {
                    val result = testee.format(TextMethodCallFormatterSpec::methodWithCharArg, arrayOf('a'))()
                    verbs.checkImmediately(result).toBe("${TextMethodCallFormatterSpec::methodWithCharArg.name}('a')")
                }
            }

            context("an argument of type String") {

                context("without line breaks") {
                    it("returns the name of the method with its argument in parentheses whereas the argument is wrapped in quotes") {
                        val result = testee.format(TextMethodCallFormatterSpec::methodWithStringArg, arrayOf("a"))()
                        verbs.checkImmediately(result).toBe("${TextMethodCallFormatterSpec::methodWithStringArg.name}(\"a\")")
                    }
                }

                listOf(Triple("\r", "\\r", "\\\\r"),
                    Triple("\n", "\\n", "\\\\n"),
                    Triple("\r\n", "\\r\\n", "\\\\r\\\\n")
                ).forEach { (char, escapedChar, doubleEscapedChar) ->

                    context("with $escapedChar as line break") {
                        it("returns the argument on one line and $escapedChar is escaped with $doubleEscapedChar") {
                            val result = testee.format(TextMethodCallFormatterSpec::methodWithStringArg, arrayOf("a${char}b"))()
                            verbs.checkImmediately(result).toBe("${TextMethodCallFormatterSpec::methodWithStringArg.name}(\"a${escapedChar}b\")")
                        }
                    }

                    context("with multiple $escapedChar as line break") {
                        it("returns the argument on one line and $escapedChar is escaped with $doubleEscapedChar") {
                            val result = testee.format(TextMethodCallFormatterSpec::methodWithStringArg, arrayOf("a${char}b${char}c${char}d"))()
                            verbs.checkImmediately(result).toBe("${TextMethodCallFormatterSpec::methodWithStringArg.name}(\"a${escapedChar}b${escapedChar}c${escapedChar}d\")")
                        }
                    }
                }
            }
        }
    }
}) {
    private fun methodWithoutArgument() {}
    private fun methodArg1(i: Int) {}
    private fun methodArg2(i: Int, float: Float) {}
    private fun methodWithStringArg(s: String) {}
    private fun methodWithCharArg(s: Char) {}
}

