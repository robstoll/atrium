package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionFunLikeExpectation
import ch.tutteli.atrium.translations.DescriptionFunLikeExpectation.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.translations.DescriptionFunLikeExpectation.THROWN_EXCEPTION_WHEN_CALLED
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation

@Suppress("FunctionName")
abstract class AbstractFun0ExpectationsTest(
    private val toThrowFeatureSpec: Feature0<out () -> Any?, IllegalArgumentException>,
    private val toThrowSpec: Feature1<out () -> Any?, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>,
    private val notToThrowFeatureSpec: Feature0<() -> Int, Int>,
    private val notToThrowSpec: Feature1<() -> Int, Expect<Int>.() -> Unit, Int>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toThrowFeatureSpec.forSubjectLessTest(),
            toThrowSpec.forSubjectLessTest { messageToContain("bla") }
        ),
        SubjectLessTestData(
            notToThrowFeatureSpec.forSubjectLessTest(),
            notToThrowSpec.forSubjectLessTest { toEqual(2) }
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData<() -> Any?>(
            { throw IllegalArgumentException("bla") },
            ExpectationCreatorTriple(
                toThrowSpec.name,
                "length",
                { apply { toThrowSpec.invoke(this) { message.feature(String::length).toEqual(3) } } },
                { apply { toThrowSpec.invoke(this) {} } }
            )
        ),
        ExpectationCreatorTestData<() -> Int>(
            { 1 },
            ExpectationCreatorTriple(
                notToThrowSpec.name,
                "$toEqualDescr: 1",
                { apply { notToThrowSpec.invoke(this) { toEqual(1) } } },
                { apply { notToThrowSpec.invoke(this) {} } }
            )
        )
    )

    val messageDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_MESSAGE.getDefault()
    val stackTraceDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_STACKTRACE.getDefault()
    val causeDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_CAUSE.getDefault()
    val separator = lineSeparator

    val errMessage = "oho... error occurred"
    fun messageAndStackTrace(message: String) =
        "\\s+\\Q$explanatoryBulletPoint\\E$messageDescr: \"$message\".*$separator" +
            "\\s+\\Q$explanatoryBulletPoint\\E$stackTraceDescr: $separator" +
            "\\s+\\Q$listBulletPoint\\E${AbstractFun0ExpectationsTest::class.fullName}"

    @TestFactory
    fun toThrow__no_exception_occurs() = testFactoryForFeatureNonFeature(
        toThrowFeatureSpec, toThrowSpec
    ) { name, toThrowFun, hasExtraHint ->
        it("$name - throws an AssertionError" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<() -> Any?> { /* no exception occurs */ 1 }.toThrowFun {
                    toEqual(IllegalArgumentException("what"))
                }
            }.toThrow<AssertionError> {
                message {
                    toContain.exactly(1).values(
                        "${THROWN_EXCEPTION_WHEN_CALLED.getDefault()}: " +
                            NO_EXCEPTION_OCCURRED.getDefault(),
                        "$toBeAnInstanceOfDescr: ${IllegalArgumentException::class.simpleName}"
                    )
                    if (hasExtraHint) toContain("$toEqualDescr: ${IllegalArgumentException::class.fullName}")
                }
            }
        }
    }

    @TestFactory
    fun notToThrow__no_exception_occurs() = testFactoryForFeatureNonFeature(
        notToThrowFeatureSpec, notToThrowSpec
    ) { name, notToThrowFun, hasExtraHint ->
        it("$name - does not throw, allows to make a sub-expectation") {
            expect {
                expect { 1 }.notToThrowFun { toEqual(1) }
            }.notToThrow()
        }

        it("$name - shows return value in case sub-expectation fails") {
            expect {
                expect { 123456789 }.notToThrowFun { toEqual(1) }
            }.toThrow<AssertionError> {
                message {
                    toContain("123456789")
                    if (hasExtraHint) toContain("$toEqualDescr: 1")
                }
            }
        }
    }

    @TestFactory
    fun toThrow__exception_occurs() = testFactoryForFeatureNonFeature(
        toThrowFeatureSpec, toThrowSpec
    ) { name, toThrowFun, hasExtraHint ->

        it("$name - allows to define expectations for the Throwable if the correct exception is thrown") {
            expect<() -> Any?> {
                throw IllegalArgumentException("hello")
            }.toThrowFun { message.toEqual("hello") }
        }

        it(
            "$name - throws an AssertionError in case of a wrong exception and shows message and stacktrace as extra hint" +
                showsSubExpectationIf(hasExtraHint)
        ) {
            expect {
                expect<() -> Any?> {
                    throw UnsupportedOperationException(errMessage)
                }.toThrowFun { message.toEqual("hello") }
            }.toThrow<AssertionError> {
                message {
                    toContainRegex(
                        "$toBeAnInstanceOfDescr:.+" + IllegalArgumentException::class.fullName,
                        UnsupportedOperationException::class.simpleName + separator +
                            messageAndStackTrace(errMessage)
                    )
                    if (hasExtraHint) toContain("$toEqualDescr: \"hello\"")
                }
            }
        }
    }

    @TestFactory
    fun notToThrow__exception_occurs() = testFactoryForFeatureNonFeature(
        notToThrowFeatureSpec, notToThrowSpec
    ) { name, notToThrowFun, hasExtraHint ->
        it(
            "$name - throws an AssertionError and shows message and stacktrace as extra hint" +
                showsSubExpectationIf(hasExtraHint)
        ) {
            expect {
                expect<() -> Int> {
                    throw UnsupportedOperationException(errMessage)
                }.notToThrowFun { toEqual(2) }
            }.toThrow<AssertionError> {
                message {
                    toContainRegex(
                        "\\Qinvoke()\\E: ${
                            DescriptionFunLikeExpectation.THREW.getDefault()
                                .format(UnsupportedOperationException::class.fullName)
                        }",
                        UnsupportedOperationException::class.simpleName + separator +
                            messageAndStackTrace(errMessage)
                    )
                    if (hasExtraHint) toContain("$toEqualDescr: 2")
                }
            }
        }
    }

    @TestFactory
    fun toThrow__exception_occurs_with_a_cause() = testFactoryForFeatureNonFeature(
        toThrowFeatureSpec, toThrowSpec
    ) { name, toThrowFun, hasExtraHint ->
        it("$name - shows cause as extra hint in case of a wrong exception" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<() -> Any?> {
                    throw UnsupportedOperationException("not supported", IllegalStateException(errMessage))
                }.toThrowFun { message.toEqual("hello") }
            }.toThrow<AssertionError> {
                expectCauseInReporting()
                if (hasExtraHint) messageToContain("$toEqualDescr: \"hello\"")
            }
        }
    }

    @TestFactory
    fun notToThrow__exception_occurs_with_a_cause() = testFactoryForFeatureNonFeature(
        notToThrowFeatureSpec, notToThrowSpec
    ) { name, notToThrowFun, hasExtraHint ->
        it("$name - shows cause as extra hint" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<() -> Int> {
                    throw UnsupportedOperationException("not supported", IllegalStateException(errMessage))
                }.notToThrowFun { toEqual(2) }
            }.toThrow<AssertionError> {
                expectCauseInReporting()
                if (hasExtraHint) messageToContain("$toEqualDescr: 2")
            }
        }
    }


    @TestFactory
    fun toThrow__exception_occurs_with_a_nested_cause() = testFactoryForFeatureNonFeature(
        toThrowFeatureSpec, toThrowSpec
    ) { name, toThrowFun, hasExtraHint ->
        it("$name - shows both causes as extra hint" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<() -> Any?> {
                    throw UnsupportedOperationException(
                        "not supported",
                        RuntimeException("io", IllegalStateException(errMessage))
                    )
                }.toThrowFun { message.toEqual("hello") }
            }.toThrow<AssertionError> {
                expectCauseAndNestedInReporting()
                if (hasExtraHint) messageToContain("$toEqualDescr: \"hello\"")
            }
        }
    }

    @TestFactory
    fun notToThrow__exception_occurs_with_a_nested_cause() = testFactoryForFeatureNonFeature(
        notToThrowFeatureSpec, notToThrowSpec
    ) { name, notToThrowFun, hasExtraHint ->
        it("$name - shows both causes as extra hint" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<() -> Int> {
                    throw UnsupportedOperationException(
                        "not supported",
                        RuntimeException("io", IllegalStateException(errMessage))
                    )
                }.notToThrowFun { toEqual(2) }
            }.toThrow<AssertionError> {
                expectCauseAndNestedInReporting()
                if (hasExtraHint) messageToContain("$toEqualDescr: 2")
            }
        }
    }

    private fun Expect<AssertionError>.expectCauseInReporting() =
        message {
            toContainRegex(
                UnsupportedOperationException::class.simpleName + separator +
                    messageAndStackTrace("not supported"),
                "\\s+\\Q$explanatoryBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                    messageAndStackTrace(errMessage)
            )

        }

    private fun Expect<AssertionError>.expectCauseAndNestedInReporting() =
        message {
            toContainRegex(
                UnsupportedOperationException::class.simpleName + separator +
                    messageAndStackTrace("not supported"),
                "\\s+\\Q$explanatoryBulletPoint\\E$causeDescr: ${RuntimeException::class.fullName}" +
                    messageAndStackTrace("io"),
                "\\s+\\Q$explanatoryBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                    messageAndStackTrace(errMessage)
            )
        }
}
