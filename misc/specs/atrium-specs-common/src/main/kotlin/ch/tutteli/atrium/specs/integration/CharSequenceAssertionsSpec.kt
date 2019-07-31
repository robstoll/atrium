package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.endsWith
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceAssertionsSpec(
    verbs: AssertionVerbFactory,
    isEmptyPair: Fun0<CharSequence>,
    isNotEmptyPair: Fun0<CharSequence>,
    isNotBlankPair: Fun0<CharSequence>,
    startsWithPair: Fun1<CharSequence, CharSequence>,
    startsWithCharPair: Fun1<CharSequence, Char>,
    startsNotWithPair: Fun1<CharSequence, CharSequence>,
    startsNotWithCharPair: Fun1<CharSequence, Char>,
    endsWithPair: Fun1<CharSequence, CharSequence>,
    endsWithCharPair: Fun1<CharSequence, Char>,
    endsNotWithPair: Fun1<CharSequence, CharSequence>,
    endsNotWithCharPair: Fun1<CharSequence, Char>,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        isEmptyPair.forSubjectLess(),
        isNotEmptyPair.forSubjectLess(),
        isNotBlankPair.forSubjectLess(),
        startsWithPair.forSubjectLess(""),
        startsWithCharPair.forSubjectLess('\u0000'),
        startsNotWithPair.forSubjectLess(""),
        startsNotWithCharPair.forSubjectLess('\u0000'),
        endsWithPair.forSubjectLess(""),
        endsWithCharPair.forSubjectLess('\u0000'),
        endsNotWithPair.forSubjectLess(""),
        endsNotWithCharPair.forSubjectLess('\u0000')
    ) {})

    include(object : CheckingAssertionSpec<CharSequence>(
        verbs, describePrefix,
        isEmptyPair.forChecking("", "not empty"),
        isNotEmptyPair.forChecking("not empty", ""),
        isNotBlankPair.forChecking("not blank", ""),
        startsWithPair.forChecking("a", "abc", "xyz"),
        startsWithCharPair.forChecking('a', "a", "x"),
        startsNotWithPair.forChecking("a", "xyz", "abc"),
        startsNotWithCharPair.forChecking('a', "x", "a"),
        endsWithPair.forChecking("c", "abc", "xyz"),
        endsWithCharPair.forChecking('c', "c", "z"),
        endsNotWithPair.forChecking("c", "xyz", "abc"),
        endsNotWithCharPair.forChecking('c', "z", "c")
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)

    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair
    val (isNotBlank, isNotBlankFun) = isNotBlankPair
    val (startsWith, startsWithFun) = startsWithPair
    val (startsWithChar, startsWithCharFun) = startsWithCharPair
    val (startsNotWith, startsNotWithFun) = startsNotWithPair
    val (startsNotWithChar, startsNotWithCharFun) = startsNotWithCharPair
    val (endsWith, endsWithFun) = endsWithPair
    val (endsWithChar, endsWithCharFun) = endsWithCharPair
    val (endsNotWith, endsNotWithFun) = endsNotWithPair
    val (endsNotWithChar, endsNotWithCharFun) = endsNotWithCharPair

    val isNot = DescriptionBasic.IS_NOT.getDefault()
    val itIs = DescriptionBasic.IS.getDefault()
    val emptyString: CharSequence = ""
    val blankString: CharSequence = "   "
    val notBlankString: CharSequence = "not blank string"

    val emptyStringBuilder: CharSequence = StringBuilder()
    val blankStringBuilder: CharSequence = StringBuilder(blankString)
    val notBlankStringBuilder: CharSequence = StringBuilder("not blank string")


    describeFun(isEmpty, isNotEmpty) {
        context("string is empty") {
            it("$isEmpty does not throw") {
                verbs.check(emptyString).isEmptyFun()
                verbs.check(emptyStringBuilder).isEmptyFun()
            }
            it("$isNotEmpty throws an AssertionError") {
                expect {
                    verbs.check(emptyString).isNotEmptyFun()
                }.toThrow<AssertionError> { message { endsWith("$isNot: empty") } }
                expect {
                    verbs.check(emptyStringBuilder).isNotEmptyFun()
                }.toThrow<AssertionError> { message { endsWith("$isNot: empty") } }
            }
        }
        context("string is not empty") {

            it("$isEmpty throws an AssertionError") {
                expect {
                    verbs.check(blankString).isEmptyFun()
                }.toThrow<AssertionError> { message { endsWith("$itIs: empty") } }
                expect {
                    verbs.check(blankStringBuilder).isEmptyFun()
                }.toThrow<AssertionError> { message { endsWith("$itIs: empty") } }
            }
            it("$isNotEmpty does not throw") {
                verbs.check(blankString).isNotEmptyFun()
                verbs.check(blankStringBuilder).isNotEmptyFun()
            }
        }
    }

    describeFun(isNotBlank) {
        context("string is blank") {
            it("$isNotBlank throws an AssertionError") {

                expect {
                    verbs.check(blankString).isNotBlankFun()
                }.toThrow<AssertionError> { message { endsWith("$isNot: blank") } }
                expect {
                    verbs.check(blankStringBuilder).isNotBlankFun()
                }.toThrow<AssertionError> { message { endsWith("$isNot: blank") } }
            }
        }
        context("string is not blank") {
            it("$isNotBlank does not throw") {
                verbs.check(notBlankString).isNotBlankFun()
                verbs.check(notBlankStringBuilder).isNotBlankFun()
            }
        }
    }

    describeFun(startsWith, startsNotWith) {
        context("text '$text'") {
            it("$startsWith 'Hello' does not throw") {
                fluent.startsWithFun("Hello")
            }
            it("$startsNotWith 'Hello' throws an AssertionError") {
                expect {
                    fluent.startsNotWithFun("Hello")
                }.toThrow<AssertionError> { messageContains(STARTS_NOT_WITH.getDefault()) }
            }

            it("$startsWith 'Robert' throws an AssertionError") {
                expect {
                    fluent.startsWithFun("goodbye")
                }.toThrow<AssertionError> { messageContains(STARTS_WITH.getDefault()) }
            }
            it("$startsNotWith 'Robert' does not throw") {
                fluent.startsNotWithFun("goodbye")
            }
        }
    }

    describeFun(startsWithChar, startsNotWithChar) {
        context("text '$text'") {
            it("$startsWithChar 'H' does not throw") {
                fluent.startsWithCharFun('H')
            }
            it("$startsNotWithChar 'H' throws an AssertionError") {
                expect {
                    fluent.startsNotWithCharFun('H')
                }.toThrow<AssertionError> { messageContains(STARTS_NOT_WITH.getDefault()) }
            }

            it("$startsWithChar 't' throws an AssertionError") {
                expect {
                    fluent.startsWithCharFun('t')
                }.toThrow<AssertionError> { messageContains(STARTS_WITH.getDefault()) }
            }
            it("$startsNotWithChar 't' does not throw") {
                fluent.startsNotWithCharFun('t')
            }
        }
    }

    describeFun(endsWith, endsNotWith) {
        context("text '$text'") {
            it("$endsWith 'Hello' throws an AssertionError") {
                expect {
                    fluent.endsWithFun("Hello")
                }.toThrow<AssertionError> { messageContains(ENDS_WITH.getDefault()) }
            }
            it("$endsNotWith 'Hello' does not throw") {
                fluent.endsNotWithFun("Hello")
            }

            it("$endsWith 'Robert' does not throw") {
                fluent.endsWithFun("Robert")
            }
            it("$endsNotWith 'Robert' throws an AssertionError") {
                expect {
                    fluent.endsNotWithFun("Robert")
                }.toThrow<AssertionError> { messageContains(ENDS_NOT_WITH.getDefault()) }
            }
        }
    }

    describeFun(endsWithChar, endsNotWithChar) {
        context("text '$text'") {
            it("$endsWithChar 'H' throws an AssertionError") {
                expect {
                    fluent.endsWithCharFun('H')
                }.toThrow<AssertionError> { messageContains(ENDS_WITH.getDefault()) }
            }
            it("$endsNotWithChar 'H' does not throw") {
                fluent.endsNotWithCharFun('H')
            }

            it("$endsWithChar 't' does not throw") {
                fluent.endsWithCharFun('t')
            }
            it("$endsNotWithChar 't' throws an AssertionError") {
                expect {
                    fluent.endsNotWithCharFun('t')
                }.toThrow<AssertionError> { messageContains(ENDS_NOT_WITH.getDefault()) }
            }
        }
    }
})
