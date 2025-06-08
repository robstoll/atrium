package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toEndWith
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation.*
import ch.tutteli.atrium.testfactories.TestFactory

abstract class AbstractCharSequenceExpectationsTest(
    private val toBeEmptySpec: Fun0<CharSequence>,
    private val notToBeEmptySpec: Fun0<CharSequence>,
    private val notToBeBlankSpec: Fun0<CharSequence>,
    private val toStartWithSpec: Fun1<CharSequence, CharSequence>,
    private val notToStartWithSpec: Fun1<CharSequence, CharSequence>,
    private val toEndWithSpec: Fun1<CharSequence, CharSequence>,
    private val notToEndWithSpec: Fun1<CharSequence, CharSequence>,
    private val toMatchSpec: Fun1<CharSequence, Regex>,
    private val notToMatchSpec: Fun1<CharSequence, Regex>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toBeEmptySpec.forSubjectLessTest(),
        notToBeEmptySpec.forSubjectLessTest(),
        notToBeBlankSpec.forSubjectLessTest(),
        toStartWithSpec.forSubjectLessTest(""),
        notToStartWithSpec.forSubjectLessTest(""),
        toEndWithSpec.forSubjectLessTest(""),
        notToEndWithSpec.forSubjectLessTest(""),
        toMatchSpec.forSubjectLessTest(Regex("")),
        notToMatchSpec.forSubjectLessTest(Regex(""))
    )

    val emptyString: CharSequence = ""
    val blankString: CharSequence = "   "
    val notBlankString: CharSequence = "not blank string"

    val emptyStringBuilder: CharSequence = StringBuilder()
    val blankStringBuilder: CharSequence = StringBuilder(blankString)
    val notBlankStringBuilder: CharSequence = StringBuilder("not blank string")

    val text: CharSequence = "Hello my name is Robert"

    @TestFactory
    fun toBeEmpty() = testFactory(toBeEmptySpec) { toBeEmptyFun ->
        it("empty string - does not throw") {
            expect(emptyString).toBeEmptyFun()
            expect(emptyStringBuilder).toBeEmptyFun()
        }
        it("blank string - throws") {
            expect {
                expect(blankString).toBeEmptyFun()
            }.toThrow<AssertionError> { message { toEndWith("$toBeDescr: empty") } }
            expect {
                expect(blankStringBuilder).toBeEmptyFun()
            }.toThrow<AssertionError> { message { toEndWith("$toBeDescr: empty") } }
        }
    }

    @TestFactory
    fun notToBeEmpty() = testFactory(notToBeEmptySpec) { notToBeEmptyFun ->
        it("blank string - does not throw") {
            expect(blankString).notToBeEmptyFun()
            expect(blankStringBuilder).notToBeEmptyFun()
        }
        it("empty string - throws") {
            expect {
                expect(emptyString).notToBeEmptyFun()
            }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: empty") } }
            expect {
                expect(emptyStringBuilder).notToBeEmptyFun()
            }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: empty") } }
        }
    }

    @TestFactory
    fun notToBeBlank() = testFactory(notToBeBlankSpec) { notToBeBlankFun ->
        it("non-blank string - does not throw") {
            expect(notBlankString).notToBeBlankFun()
            expect(notBlankStringBuilder).notToBeBlankFun()
        }
        it("blank string - throws") {
            expect {
                expect(blankString).notToBeBlankFun()
            }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: blank") } }
            expect {
                expect(blankStringBuilder).notToBeBlankFun()
            }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: blank") } }
        }
    }

    @TestFactory
    fun toStartWith() = testFactory(toStartWithSpec) { toStartWithFun ->
        it("'$text' ${toStartWithSpec.name} 'Hello' - does not throw") {
            expect(text).toStartWithFun("Hello")
        }
        it("'$text' ${toStartWithSpec.name} 'Robert' - throws") {
            expect {
                expect(text).toStartWithFun("Robert")
            }.toThrow<AssertionError> { messageToContain(TO_START_WITH.getDefault()) }
        }
    }

    @TestFactory
    fun notToStartWith() = testFactory(notToStartWithSpec) { notToStartWithFun ->
        it("'$text' ${notToStartWithSpec.name} 'Robert' - does not throw") {
            expect(text).notToStartWithFun("Robert")
        }
        it("'$text' ${notToStartWithSpec.name} 'Hello' - throws") {
            expect {
                expect(text).notToStartWithFun("Hello")
            }.toThrow<AssertionError> { messageToContain(NOT_TO_START_WITH.getDefault()) }
        }
    }

    @TestFactory
    fun toEndWith() = testFactory(toEndWithSpec) { toEndWithFun ->
        it("'$text' ${toEndWithSpec.name} 'Robert' - does not throw") {
            expect(text).toEndWithFun("Robert")
        }
        it("'$text' ${toEndWithSpec.name} 'Hello' - throws") {
            expect {
                expect(text).toEndWithFun("Hello")
            }.toThrow<AssertionError> { messageToContain(TO_END_WITH.getDefault()) }
        }
    }

    @TestFactory
    fun notToEndWith() = testFactory(notToEndWithSpec) { notToEndWithFun ->
        it("'$text' ${notToEndWithSpec.name} 'Hello' - does not throw") {
            expect(text).notToEndWithFun("Hello")
        }
        it("'$text' ${notToEndWithSpec.name} 'Robert' - throws") {
            expect {
                expect(text).notToEndWithFun("Robert")
            }.toThrow<AssertionError> { messageToContain(NOT_TO_END_WITH.getDefault()) }
        }
    }

    @TestFactory
    fun toMatch() = testFactory(toMatchSpec) { toMatchFun ->
        it("'$text' ${toMatchSpec.name} '^Hello.+' - does not throw") {
            expect(text).toMatchFun(Regex("^Hello.+"))
        }
        it("'$text' ${toMatchSpec.name} 'Hello' - throws") {
            expect {
                expect(text).toMatchFun(Regex("Hello"))
            }.toThrow<AssertionError> { messageToContain(TO_MATCH.getDefault()) }
        }
    }

    @TestFactory
    fun notToMatch() = testFactory(notToMatchSpec) { notToMatchFun ->
        it("'$text' ${notToMatchSpec.name} 'Hello' - does not throw") {
            expect(text).notToMatchFun(Regex("Hello"))
        }
        it("'$text' ${notToMatchSpec.name} 'Hello my name is Robert' - throws") {
            expect {
                expect(text).notToMatchFun(Regex("Hello my name is Robert"))
            }.toThrow<AssertionError> { messageToContain(NOT_TO_MATCH.getDefault()) }
        }
    }
}
