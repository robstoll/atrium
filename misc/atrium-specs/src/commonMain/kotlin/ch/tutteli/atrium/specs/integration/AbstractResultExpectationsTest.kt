package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.translations.DescriptionResultExpectation

@Suppress("FunctionName")
abstract class AbstractResultExpectationsTest(
    private val toBeASuccessFeatureSpec: Feature0<Result<Int>, Int>,
    private val toBeASuccessSpec: Fun1<Result<Int>, Expect<Int>.() -> Unit>,
    private val toBeASuccessFeatureNullableSpec: Feature0<Result<Int?>, Int?>,
    private val toBeASuccessNullableSpec: Fun1<Result<Int?>, Expect<Int?>.() -> Unit>,
    private val toBeAFailureFeatureSpec: Feature0<Result<Int>, IllegalArgumentException>,
    private val toBeAFailureSpec: Feature1<Result<Int>, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>
) : ExpectationFunctionBaseTest() {

    private val resultSuccess = Result.success(1)
    private val resultFailure = Result.failure<Int>(IllegalArgumentException("oh no..."))
    private val resultNullSuccess = Result.success(null as Int?)
    private val resultNullableFailure = Result.failure<Int?>(IllegalArgumentException("oh no nullable..."))

    private val valueDescr = DescriptionResultExpectation.VALUE.getDefault()
    private val isNotSuccessDescr = DescriptionResultExpectation.IS_NOT_SUCCESS.getDefault()
    private val isNotFailureDescr = DescriptionResultExpectation.IS_NOT_FAILURE.getDefault()
    private val exceptionDescr = DescriptionResultExpectation.EXCEPTION.getDefault()

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toBeASuccessFeatureSpec.forSubjectLessTest(),
            toBeASuccessSpec.forSubjectLessTest { toEqual(1) },
            toBeAFailureFeatureSpec.forSubjectLessTest(),
            toBeAFailureSpec.forSubjectLessTest { messageToContain("message") }
        ),
        SubjectLessTestData(
            toBeASuccessFeatureNullableSpec.forSubjectLessTest(),
            toBeASuccessNullableSpec.forSubjectLessTest { toEqual(1) },
            groupPrefix = "[nullable]"
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            Result.success(2),
            toBeASuccessSpec.forExpectationCreatorTest("$toEqualDescr: 2") { toEqual(2) }
        ),
        ExpectationCreatorTestData<Result<Int?>>(
            Result.success(2),
            toBeASuccessNullableSpec.forExpectationCreatorTest("$toEqualDescr: 2") { toEqual(2) },
            groupPrefix = "[nullable]"
        ),
        ExpectationCreatorTestData(
            Result.failure<Int>(IllegalArgumentException("oh no...")),
            ExpectationCreatorTriple(
                toBeAFailureSpec.name,
                "${DescriptionCharSequenceExpectation.VALUE.getDefault()}: \"oh no...\"",
                { apply { toBeAFailureSpec.invoke(this) { messageToContain("oh no...") } } },
                { apply { toBeAFailureSpec.invoke(this) {} } }
            ),
            groupPrefix = "[failure]"
        )
    )

    @TestFactory
    fun toBeASuccess_and_toBeAFailure() = testFactory(
        toBeASuccessFeatureSpec,
        toBeASuccessSpec,
        toBeASuccessFeatureNullableSpec,
        toBeASuccessNullableSpec,
        toBeAFailureFeatureSpec,
        toBeAFailureSpec
    ) {
        val successFunctions = uncheckedToNonNullable(
            unifySignatures(toBeASuccessFeatureSpec, toBeASuccessSpec),
            unifySignatures(toBeASuccessFeatureNullableSpec, toBeASuccessNullableSpec)
        )
        val failureFunctions = unifySignatures(toBeAFailureFeatureSpec, toBeAFailureSpec)

        describe("subject is $resultSuccess") {
            successFunctions.forEach { (name, toBeASuccessFun, _) ->
                it("$name - can perform sub-assertion which holds") {
                    expect(resultSuccess).toBeASuccessFun { toEqual(1) }
                }
                it("$name - can perform sub-assertion which fails, throws AssertionError") {
                    expect {
                        expect(resultSuccess).toBeASuccessFun { toEqual(2) }
                    }.toThrow<AssertionError> {
                        messageToContain("$valueDescr: 1", "$toEqualDescr: 2")
                    }
                }
            }

            failureFunctions.forEach { (name, toBeAFailureFun, hasExtraHint) ->
                it("$name - throws AssertionError showing the expected type" + if (hasExtraHint) " and the expected message" else "") {
                    expect {
                        expect(resultSuccess).toBeAFailureFun { messageToContain("oh yes...") }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            "$exceptionDescr: $isNotFailureDescr",
                            "$toBeAnInstanceOfDescr: ${IllegalArgumentException::class.simpleName}"
                        )
                        if (hasExtraHint) {
                            messageToContain(
                                DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault(),
                                "${DescriptionCharSequenceExpectation.VALUE.getDefault()}: \"oh yes...\""
                            )
                        }
                    }
                }
            }
        }

        describe("subject is $resultFailure") {
            successFunctions.forEach { (name, toBeASuccessFun, hasExtraHint) ->
                it("$name throws AssertionError" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        expect(resultFailure).toBeASuccessFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        messageToContain("$valueDescr: $isNotSuccessDescr")
                        if (hasExtraHint) messageToContain("$toEqualDescr: 1")
                    }
                }
            }

            failureFunctions.forEach { (name, toBeAFailureFun, _) ->
                it("$name - can perform sub-assertion which holds") {
                    expect(resultFailure).toBeAFailureFun { messageToContain("oh no...") }
                }
                it("$name - can perform sub-assertion which fails, throws AssertionError") {
                    expect {
                        expect(resultFailure).toBeAFailureFun { messageToContain("oh yes...") }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            "$exceptionDescr: ${IllegalArgumentException::class.fullName}",
                            DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault(), "${DescriptionCharSequenceExpectation.VALUE.getDefault()}: \"oh yes...\""
                        )
                    }
                }
            }
        }
    }

    @TestFactory
    fun toBeASuccess_nullable() = testFactoryForFeatureNonFeature(
        toBeASuccessFeatureNullableSpec, toBeASuccessNullableSpec
    ) { name, toBeASuccessFun, hasExtraHint ->
        describe("subject is $resultNullSuccess") {
            it("$name - can perform sub-assertion which holds") {
                expect(resultNullSuccess).toBeASuccessFun { toEqual(null) }
            }
            it("$name - can perform sub-assertion which fails, throws AssertionError") {
                expect {
                    expect(resultNullSuccess).toBeASuccessFun { toEqual(2) }
                }.toThrow<AssertionError> {
                    messageToContain("$valueDescr: null", "$toEqualDescr: 2")
                }
            }
        }

        describe("subject is $resultNullableFailure") {
            it("$name throws AssertionError" + showsSubExpectationIf(hasExtraHint)) {
                expect {
                    expect(resultNullableFailure).toBeASuccessFun { toEqual(1) }
                }.toThrow<AssertionError> {
                    messageToContain("$valueDescr: $isNotSuccessDescr")
                    if (hasExtraHint) messageToContain("$toEqualDescr: 1")
                }
            }
        }
    }
}
