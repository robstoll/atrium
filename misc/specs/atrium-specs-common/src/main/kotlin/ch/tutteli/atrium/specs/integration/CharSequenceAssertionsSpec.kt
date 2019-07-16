package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.endsWith
import ch.tutteli.atrium.api.cc.en_GB.message
import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.specs.CheckingAssertionSpec
import ch.tutteli.atrium.specs.SubjectLessSpec
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.include
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
    startsNotWithPair: Fun1<CharSequence, CharSequence>,
    endsWithPair: Fun1<CharSequence, CharSequence>,
    endsNotWithPair: Fun1<CharSequence, CharSequence>,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        isEmptyPair.forSubjectLess(),
        isNotEmptyPair.forSubjectLess(),
        isNotBlankPair.forSubjectLess(),
        startsWithPair.forSubjectLess(""),
        startsNotWithPair.forSubjectLess(""),
        endsWithPair.forSubjectLess(""),
        endsNotWithPair.forSubjectLess("")
    ) {})

    include(object : CheckingAssertionSpec<CharSequence>(
        verbs, describePrefix,
        isEmptyPair.forChecking("", "not empty"),
        isNotEmptyPair.forChecking("not empty", ""),
        isNotBlankPair.forChecking("not blank", ""),
        startsWithPair.forChecking("a", "abc", "xyz"),
        startsNotWithPair.forChecking("a", "xyz", "abc"),
        endsWithPair.forChecking("c", "abc", "xyz"),
        endsNotWithPair.forChecking("c", "xyz", "abc")
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)

    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair
    val (isNotBlank, isNotBlankFun) = isNotBlankPair
    val (startsWith, startsWithFun) = startsWithPair
    val (startsNotWith, startsNotWithFun) = startsNotWithPair
    val (endsWith, endsWithFun) = endsWithPair
    val (endsNotWith, endsNotWithFun) = endsNotWithPair

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
})
