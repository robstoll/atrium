package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.*
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceExpectationsSpec(
    toBeEmpty: Fun0<CharSequence>,
    notToBeEmpty: Fun0<CharSequence>,
    notToBeBlank: Fun0<CharSequence>,
    toStartWith: Fun1<CharSequence, CharSequence>,
    notToStartWith: Fun1<CharSequence, CharSequence>,
    toEndWith: Fun1<CharSequence, CharSequence>,
    notToEndWith: Fun1<CharSequence, CharSequence>,
    toMatch: Fun1<CharSequence, Regex>,
    notToMatch: Fun1<CharSequence, Regex>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        toBeEmpty.forSubjectLessTest(),
        notToBeEmpty.forSubjectLessTest(),
        notToBeBlank.forSubjectLessTest(),
        toStartWith.forSubjectLessTest(""),
        notToStartWith.forSubjectLessTest(""),
        toEndWith.forSubjectLessTest(""),
        notToEndWith.forSubjectLessTest(""),
        toMatch.forSubjectLessTest(Regex("")),
        notToMatch.forSubjectLessTest(Regex(""))
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val emptyString: CharSequence = ""
    val blankString: CharSequence = "   "
    val notBlankString: CharSequence = "not blank string"

    val emptyStringBuilder: CharSequence = StringBuilder()
    val blankStringBuilder: CharSequence = StringBuilder(blankString)
    val notBlankStringBuilder: CharSequence = StringBuilder("not blank string")

    describeFun(toBeEmpty.name, notToBeEmpty.name) {
        val toBeEmptyFun = toBeEmpty.lambda
        val notToBeEmptyFun = notToBeEmpty.lambda

        context("string is empty") {
            it("${toBeEmpty.name} does not throw") {
                expect(emptyString).toBeEmptyFun()
                expect(emptyStringBuilder).toBeEmptyFun()
            }
            it("${notToBeEmpty.name} throws an AssertionError") {
                expect {
                    expect(emptyString).notToBeEmptyFun()
                }.toThrow<AssertionError> { message { toContainNotToBeDescr("empty") } }
                expect {
                    expect(emptyStringBuilder).notToBeEmptyFun()
                }.toThrow<AssertionError> { message { toContainNotToBeDescr("empty") } }
            }
        }
        context("string is not empty") {

            it("${toBeEmpty.name} throws an AssertionError") {
                expect {
                    expect(blankString).toBeEmptyFun()
                }.toThrow<AssertionError> { message { toContainToBeDescr("empty") } }
                expect {
                    expect(blankStringBuilder).toBeEmptyFun()
                }.toThrow<AssertionError> { message { toContainToBeDescr("empty") } }
            }
            it("${notToBeEmpty.name} does not throw") {
                expect(blankString).notToBeEmptyFun()
                expect(blankStringBuilder).notToBeEmptyFun()
            }
        }
    }

    describeFun(notToBeBlank.name) {
        val notToBeBlankFun = notToBeBlank.lambda

        context("string is blank") {
            it("throws an AssertionError") {

                expect {
                    expect(blankString).notToBeBlankFun()
                }.toThrow<AssertionError> { message { toContainNotToBeDescr("blank") } }
                expect {
                    expect(blankStringBuilder).notToBeBlankFun()
                }.toThrow<AssertionError> { message { toContainNotToBeDescr("blank") } }
            }
        }
        context("string is not blank") {
            it("does not throw") {
                expect(notBlankString).notToBeBlankFun()
                expect(notBlankStringBuilder).notToBeBlankFun()
            }
        }
    }

    describeFun(toStartWith.name, notToStartWith.name) {
        val toStartWithFun = toStartWith.lambda
        val notToStartWithFun = notToStartWith.lambda

        context("text '$text'") {
            it("${toStartWith.name} 'Hello' does not throw") {
                expect(text).toStartWithFun("Hello")
            }
            it("${notToStartWith.name} 'Hello' throws an AssertionError") {
                expect {
                    expect(text).notToStartWithFun("Hello")
                }.toThrow<AssertionError> { message { toContainDescr(NOT_TO_START_WITH, "\"Hello\"") } }
            }

            it("${toStartWith.name} 'Robert' throws an AssertionError") {
                expect {
                    expect(text).toStartWithFun("goodbye")
                }.toThrow<AssertionError> { message { toContainDescr(TO_START_WITH, "\"goodbye\"") } }
            }
            it("${notToStartWith.name} 'Robert' does not throw") {
                expect(text).notToStartWithFun("goodbye")
            }
        }
    }

    describeFun(toEndWith.name, notToEndWith.name) {
        val toEndWithFun = toEndWith.lambda
        val notToEndWithFun = notToEndWith.lambda

        context("text '$text'") {
            it("${toEndWith.name} 'Hello' throws an AssertionError") {
                expect {
                    expect(text).toEndWithFun("Hello")
                }.toThrow<AssertionError> {
                    message { toContainDescr(TO_END_WITH, "\"Hello\"") }
                }
            }
            it("${notToEndWith.name} 'Hello' does not throw") {
                expect(text).notToEndWithFun("Hello")
            }

            it("${toEndWith.name} 'Robert' does not throw") {
                expect(text).toEndWithFun("Robert")
            }
            it("${notToEndWith.name} 'Robert' throws an AssertionError") {
                expect {
                    expect(text).notToEndWithFun("Robert")
                }.toThrow<AssertionError> { message { toContainDescr(NOT_TO_END_WITH, "\"Robert\"") } }
            }
        }
    }

    describeFun(toMatch.name) {
        val toMatchFun = toMatch.lambda

        context("text '$text'") {
            it("${toMatch.name} '^Hello.+' does not throw") {
                expect(text).toMatchFun(Regex("^Hello.+"))
            }

            it("${toMatch.name} 'Hello' throws an AssertionError") {
                expect {
                    expect(text).toMatchFun(Regex("Hello"))
                }.toThrow<AssertionError> { message { toContainDescr(TO_MATCH, "Hello") } }
            }
        }
    }

    describeFun(notToMatch.name) {
        val notToMatchFun = notToMatch.lambda

        context("text '$text") {
            it("${notToMatch.name} 'Hello' does not throw") {
                expect(text).notToMatchFun(Regex("Hello"))
            }

            it("${notToMatch.name} 'Hello my name is Robert' throws an AssertionError") {
                expect {
                    expect(text).notToMatchFun(Regex("Hello my name is Robert"))
                }.toThrow<AssertionError> { message { toContainDescr(NOT_TO_MATCH, "Hello my name is Robert") } }
            }
        }
    }
})
