package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceExpectationsSpec(
    isEmpty: Fun0<CharSequence>,
    isNotEmpty: Fun0<CharSequence>,
    isNotBlank: Fun0<CharSequence>,
    startsWith: Fun1<CharSequence, CharSequence>,
    startsNotWith: Fun1<CharSequence, CharSequence>,
    endsWith: Fun1<CharSequence, CharSequence>,
    endsNotWith: Fun1<CharSequence, CharSequence>,
    matches: Fun1<CharSequence, Regex>,
    mismatches: Fun1<CharSequence, Regex>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        isEmpty.forSubjectLess(),
        isNotEmpty.forSubjectLess(),
        isNotBlank.forSubjectLess(),
        startsWith.forSubjectLess(""),
        startsNotWith.forSubjectLess(""),
        endsWith.forSubjectLess(""),
        endsNotWith.forSubjectLess(""),
        matches.forSubjectLess(Regex("")),
        mismatches.forSubjectLess(Regex(""))
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(text as CharSequence)

    val emptyString: CharSequence = ""
    val blankString: CharSequence = "   "
    val notBlankString: CharSequence = "not blank string"

    val emptyStringBuilder: CharSequence = StringBuilder()
    val blankStringBuilder: CharSequence = StringBuilder(blankString)
    val notBlankStringBuilder: CharSequence = StringBuilder("not blank string")

    describeFun(isEmpty.name, isNotEmpty.name) {
        val isEmptyFun = isEmpty.lambda
        val isNotEmptyFun = isNotEmpty.lambda

        context("string is empty") {
            it("${isEmpty.name} does not throw") {
                expect(emptyString).isEmptyFun()
                expect(emptyStringBuilder).isEmptyFun()
            }
            it("${isNotEmpty.name} throws an AssertionError") {
                expect {
                    expect(emptyString).isNotEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: empty") } }
                expect {
                    expect(emptyStringBuilder).isNotEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: empty") } }
            }
        }
        context("string is not empty") {

            it("${isEmpty.name} throws an AssertionError") {
                expect {
                    expect(blankString).isEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$toBeDescr: empty") } }
                expect {
                    expect(blankStringBuilder).isEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$toBeDescr: empty") } }
            }
            it("${isNotEmpty.name} does not throw") {
                expect(blankString).isNotEmptyFun()
                expect(blankStringBuilder).isNotEmptyFun()
            }
        }
    }

    describeFun(isNotBlank.name) {
        val isNotBlankFun = isNotBlank.lambda

        context("string is blank") {
            it("throws an AssertionError") {

                expect {
                    expect(blankString).isNotBlankFun()
                }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: blank") } }
                expect {
                    expect(blankStringBuilder).isNotBlankFun()
                }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: blank") } }
            }
        }
        context("string is not blank") {
            it("does not throw") {
                expect(notBlankString).isNotBlankFun()
                expect(notBlankStringBuilder).isNotBlankFun()
            }
        }
    }

    describeFun(startsWith.name, startsNotWith.name) {
        val startsWithFun = startsWith.lambda
        val startsNotWithFun = startsNotWith.lambda

        context("text '$text'") {
            it("${startsWith.name} 'Hello' does not throw") {
                fluent.startsWithFun("Hello")
            }
            it("${startsNotWith.name} 'Hello' throws an AssertionError") {
                expect {
                    fluent.startsNotWithFun("Hello")
                }.toThrow<AssertionError> { messageToContain(STARTS_NOT_WITH.getDefault()) }
            }

            it("${startsWith.name} 'Robert' throws an AssertionError") {
                expect {
                    fluent.startsWithFun("goodbye")
                }.toThrow<AssertionError> { messageToContain(STARTS_WITH.getDefault()) }
            }
            it("${startsNotWith.name} 'Robert' does not throw") {
                fluent.startsNotWithFun("goodbye")
            }
        }
    }

    describeFun(endsWith.name, endsNotWith.name) {
        val endsWithFun = endsWith.lambda
        val endsNotWithFun = endsNotWith.lambda

        context("text '$text'") {
            it("${endsWith.name} 'Hello' throws an AssertionError") {
                expect {
                    fluent.endsWithFun("Hello")
                }.toThrow<AssertionError> { messageToContain(ENDS_WITH.getDefault()) }
            }
            it("${endsNotWith.name} 'Hello' does not throw") {
                fluent.endsNotWithFun("Hello")
            }

            it("${endsWith.name} 'Robert' does not throw") {
                fluent.endsWithFun("Robert")
            }
            it("${endsNotWith.name} 'Robert' throws an AssertionError") {
                expect {
                    fluent.endsNotWithFun("Robert")
                }.toThrow<AssertionError> { messageToContain(ENDS_NOT_WITH.getDefault()) }
            }
        }
    }

    describeFun(matches.name) {
        val matchesFun = matches.lambda

        context("text '$text'") {
            it("${matches.name} '^Hello.+' does not throw") {
                fluent.matchesFun(Regex("^Hello.+"))
            }

            it("${matches.name} 'Hello' throws an AssertionError") {
                expect {
                    fluent.matchesFun(Regex("Hello"))
                }.toThrow<AssertionError> { messageToContain(MATCHES.getDefault()) }
            }
        }
    }

    describeFun(mismatches.name){
        val mismatchesFun = mismatches.lambda

        context("text '$text"){
            it("${mismatches.name} 'Hello' does not throw"){
                fluent.mismatchesFun(Regex("Hello"))
            }

            it("${mismatches.name} 'Hello my name is Robert' throws an AssertionError"){
                expect {
                    fluent.mismatchesFun(Regex("Hello my name is Robert"))
                }.toThrow<AssertionError> { messageToContain(MISMATCHES.getDefault()) }
            }
        }
    }
})
