package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.reporting.MethodCallFormatter
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

@Suppress("UNUSED_PARAMETER")
abstract class TextMethodCallFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: () -> MethodCallFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val testee = testeeFactory()

    describeFun(testee::formatCall.name) {

        context("a method call without arguments") {
            it("returns the name of the method with parentheses") {
                val result = testee. formatCall("withoutArg", arrayOf())
                verbs.checkImmediately(result).toBe("withoutArg()")
            }
        }

        context("a method call with arguments") {

            context("one argument of type Int") {
                it("returns the name of the method with its argument in parentheses") {
                    val result = testee. formatCall("withArg1", arrayOf(1))
                    verbs.checkImmediately(result).toBe("withArg1(1)")
                }
            }

            context("two arguments of type Int and Float") {
                it("returns the name of the method, followed by the first and second argument in parentheses and separated by a comma") {
                    val result = testee. formatCall("withArg2", arrayOf(1, 1.2))
                    verbs.checkImmediately(result).toBe("withArg2(1, 1.2)")
                }
            }

            context("an argument of type Char") {
                it("returns the name of the method with its argument in parentheses whereas the argument is wrapped in apostrophes") {
                    val result = testee. formatCall("withArg1", arrayOf('a'))
                    verbs.checkImmediately(result).toBe("withArg1('a')")
                }
            }

            context("`null`") {
                it("returns the name of the method with its argument in parentheses whereas the argument is wrapped in apostrophes") {
                    val result = testee. formatCall("withArg1", arrayOf<Int?>(null))
                    verbs.checkImmediately(result).toBe("withArg1(null)")
                }
            }

            context("an argument of type String") {

                context("without line breaks") {
                    it("returns the name of the method with its argument in parentheses whereas the argument is wrapped in quotes") {
                        val result = testee. formatCall("withArg1", arrayOf("a"))
                        verbs.checkImmediately(result).toBe("withArg1(\"a\")")
                    }
                }

                listOf(Triple("\r", "\\r", "\\\\r"),
                    Triple("\n", "\\n", "\\\\n"),
                    Triple("\r\n", "\\r\\n", "\\\\r\\\\n")
                ).forEach { (char, escapedChar, doubleEscapedChar) ->

                    context("with $escapedChar as line break") {
                        it("returns the argument on one line and $escapedChar is escaped with $doubleEscapedChar") {
                            val result = testee. formatCall("withArg1", arrayOf("a${char}b"))
                            verbs.checkImmediately(result).toBe("withArg1(\"a${escapedChar}b\")")
                        }
                    }

                    context("with multiple $escapedChar as line break") {
                        it("returns the argument on one line and $escapedChar is escaped with $doubleEscapedChar") {
                            val result = testee. formatCall("withArg1", arrayOf("a${char}b${char}c${char}d"))
                            verbs.checkImmediately(result).toBe("withArg1(\"a${escapedChar}b${escapedChar}c${escapedChar}d\")")
                        }
                    }
                }
            }
        }
    }
})

