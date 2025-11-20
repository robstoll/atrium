package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloMyNameIsRobert
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation.*

@Suppress("FunctionName")
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
    private val lengthFeatureSpec: Feature0<CharSequence, Int>,
    private val lengthSpec: Fun1<CharSequence, Expect<Int>.() -> Unit>
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
        notToMatchSpec.forSubjectLessTest(Regex("")),
        lengthFeatureSpec.forSubjectLessTest(),
        lengthSpec.forSubjectLessTest { toBeLessThan(1) }
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        "hello",
        lengthSpec.forExpectationCreatorTest("$toEqualDescr: 5") { toEqual(5) }
    )

    @TestFactory
    fun length_feature_and_function() = testFactoryForFeatureNonFeature(
        lengthFeatureSpec,
        lengthSpec
    ) { name, lengthFun, _ ->
        it("$name - passes") {
            expect("Hello" as CharSequence).lengthFun { toBeLessThan(30) }
        }
        it("$name - fails") {
            expect {
                expect("Hi" as CharSequence).lengthFun { toBeLessThan(2) }
            }.toThrow<AssertionError> {
                messageToContain("length: 2", "$toBeLessThanDescr: 2")
            }
        }
    }

    val emptyString: CharSequence = ""
    val blankString: CharSequence = "   "
    val notBlankString: CharSequence = "not blank string"

    val emptyStringBuilder: CharSequence = StringBuilder()
    val blankStringBuilder: CharSequence = StringBuilder(blankString)
    val notBlankStringBuilder: CharSequence = StringBuilder("not blank string")

    @TestFactory
    fun toBeEmpty_notToBeEmpty() = testFactory(toBeEmptySpec, notToBeEmptySpec) { toBeEmptyFun, notToBeEmptyFun ->
        describe("empty String") {
            itFun(toBeEmptySpec, "does not throw") {
                expect(emptyString).toBeEmptyFun()
                expect(emptyStringBuilder).toBeEmptyFun()
            }
            itFun(notToBeEmptySpec, "throws") {
                expect {
                    expect(emptyString).notToBeEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: empty") } }
                expect {
                    expect(emptyStringBuilder).notToBeEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: empty") } }
            }
        }

        describe("blank String") {
            itFun(toBeEmptySpec, "throws") {
                expect {
                    expect(blankString).toBeEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$toBeDescr: empty") } }
                expect {
                    expect(blankStringBuilder).toBeEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$toBeDescr: empty") } }
            }
            itFun(notToBeEmptySpec, "does not throw") {
                expect(blankString).notToBeEmptyFun()
                expect(blankStringBuilder).notToBeEmptyFun()
            }
        }

        describe("non-blank string") {
            itFun(toBeEmptySpec, "throws") {
                expect {
                    expect(notBlankString).toBeEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$toBeDescr: empty") } }
                expect {
                    expect(notBlankStringBuilder).toBeEmptyFun()
                }.toThrow<AssertionError> { message { toEndWith("$toBeDescr: empty") } }
            }
            itFun(notToBeEmptySpec, "does not throw") {
                expect(notBlankString).notToBeEmptyFun()
                expect(notBlankStringBuilder).notToBeEmptyFun()
            }
        }
    }

    @TestFactory
    fun notToBeBlank() = testFactory(notToBeBlankSpec) { notToBeBlankFun ->
        it("empty string - does not throw") {
            expect {
                expect(emptyString).notToBeBlankFun()
            }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: blank") } }
            expect {
                expect(emptyStringBuilder).notToBeBlankFun()
            }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: blank") } }
        }

        it("blank string - throws") {
            expect {
                expect(blankString).notToBeBlankFun()
            }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: blank") } }
            expect {
                expect(blankStringBuilder).notToBeBlankFun()
            }.toThrow<AssertionError> { message { toEndWith("$notToBeDescr: blank") } }
        }

        it("non-blank string - does not throw") {
            expect(notBlankString).notToBeBlankFun()
            expect(notBlankStringBuilder).notToBeBlankFun()
        }
    }

    @TestFactory
    fun toStartWith_notToStartWith() = testFactory(
        toStartWithSpec, notToStartWithSpec
    ) { toStartWithFun, notToStartWithFun ->
        describe("text '$helloMyNameIsRobert'") {
            itFun(toStartWithSpec, "'Hello' - does not throw") {
                expect(helloMyNameIsRobert).toStartWithFun("Hello")
            }
            itFun(notToStartWithSpec, "'Robert' - does not throw") {
                expect(helloMyNameIsRobert).notToStartWithFun("Robert")
            }

            itFun(toStartWithSpec, "'Robert' - throws") {
                expect {
                    expect(helloMyNameIsRobert).toStartWithFun("Robert")
                }.toThrow<AssertionError> { messageToContain(TO_START_WITH.getDefault()) }
            }
            itFun(notToStartWithSpec, "'Hello' - throws") {
                expect {
                    expect(helloMyNameIsRobert).notToStartWithFun("Hello")
                }.toThrow<AssertionError> { messageToContain(NOT_TO_START_WITH.getDefault()) }
            }
        }
    }

    @TestFactory
    fun toEndWith_notToEndWith() = testFactory(toEndWithSpec, notToEndWithSpec) { toEndWithFun, notToEndWithFun ->
        describe("text '$helloMyNameIsRobert'") {
            itFun(toEndWithSpec, "'Robert' - does not throw") {
                expect(helloMyNameIsRobert).toEndWithFun("Robert")
            }
            itFun(notToEndWithSpec, "'Robert' - throws") {
                expect {
                    expect(helloMyNameIsRobert).notToEndWithFun("Robert")
                }.toThrow<AssertionError> { messageToContain(NOT_TO_END_WITH.getDefault()) }
            }

            itFun(toEndWithSpec, "'Hello' - throws") {
                expect {
                    expect(helloMyNameIsRobert).toEndWithFun("Hello")
                }.toThrow<AssertionError> { messageToContain(TO_END_WITH.getDefault()) }
            }
            itFun(notToEndWithSpec, "'Hello' - does not throw") {
                expect(helloMyNameIsRobert).notToEndWithFun("Hello")
            }
        }
    }

    @TestFactory
    fun toMatch() = testFactory(toMatchSpec) { toMatchFun ->
        describe("text '$helloMyNameIsRobert'") {
            it("'^Hello.+' - does not throw") {
                expect(helloMyNameIsRobert).toMatchFun(Regex("^Hello.+"))
            }

            it("'Hello' - throws") {
                expect {
                    expect(helloMyNameIsRobert).toMatchFun(Regex("Hello"))
                }.toThrow<AssertionError> { messageToContain(TO_MATCH.getDefault()) }
            }
        }
    }


    @TestFactory
    fun notToMatch() = testFactory(notToMatchSpec) { notToMatchFun ->
        describe("text '$helloMyNameIsRobert'") {
            it("'Hello' - does not throw") {
                expect(helloMyNameIsRobert).notToMatchFun(Regex("Hello"))
            }

            it("'Hello my name is Robert' - throws") {
                expect {
                    expect(helloMyNameIsRobert).notToMatchFun(Regex("Hello my name is Robert"))
                }.toThrow<AssertionError> { messageToContain(NOT_TO_MATCH.getDefault()) }
            }
        }
    }
}
