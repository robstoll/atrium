package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toMatch
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionResultProof
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.translations.DescriptionResultExpectation
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ResultExpectationsSpec(
    toBeASuccessFeature: Feature0<Result<Int>, Int>,
    toBeASuccess: Fun1<Result<Int>, Expect<Int>.() -> Unit>,
    toBeASuccessFeatureNullable: Feature0<Result<Int?>, Int?>,
    toBeASuccessNullable: Fun1<Result<Int?>, Expect<Int?>.() -> Unit>,
    toBeAFailureFeature: Feature0<Result<Int>, IllegalArgumentException>,
    toBeAFailure: Feature1<Result<Int>, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Result<Int>>(
        describePrefix,
        toBeASuccessFeature.forSubjectLessTest(),
        toBeASuccess.forSubjectLessTest { toEqual(1) },
        toBeAFailureFeature.forSubjectLessTest(),
        toBeAFailure.forSubjectLessTest { messageToContain("message") }
    ) {})
    include(object : SubjectLessSpec<Result<Int?>>(
        "$describePrefix[nullable] ",
        toBeASuccessFeatureNullable.forSubjectLessTest(),
        toBeASuccessNullable.forSubjectLessTest { toEqual(1) }
    ) {})

    include(object : AssertionCreatorSpec<Result<Int>>(
        describePrefix, Result.success(2),
        toBeASuccess.forExpectationCreatorTest("$toEqualDescr\\s+: 2") { toEqual(2) }
    ) {})
    include(object : AssertionCreatorSpec<Result<Int?>>(
        "$describePrefix[nullable] ", Result.success(2),
        toBeASuccessNullable.forExpectationCreatorTest("$toEqualDescr\\s+: 2") { toEqual(2) }
    ) {})

    include(object : AssertionCreatorSpec<Result<Int>>(
        "$describePrefix[failure] ", Result.failure(IllegalArgumentException("oh no...")),
        ExpectationCreatorTriple(
            toBeAFailure.name,
            "${DescriptionCharSequenceProof.VALUE.string}\\s+: \"oh no...\"",
            { apply { toBeAFailure.invoke(this) { messageToContain("oh no...") } } },
            { apply { toBeAFailure.invoke(this) {} } }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val resultSuccess = Result.success(1)
    val resultFailure = Result.failure<Int>(IllegalArgumentException("oh no..."))
    val resultNullSuccess = Result.success(null as Int?)
    val resultNullableFailure = Result.failure<Int?>(IllegalArgumentException("oh no nullable..."))

    val isNotSuccessDescr = DescriptionResultProof.IS_NOT_SUCCESS.string
    val isNotFailureDescr = DescriptionResultProof.IS_NOT_FAILURE.string

    describeFun(
        toBeASuccessFeature,
        toBeASuccess,
        toBeASuccessFeatureNullable,
        toBeASuccessNullable,
        toBeAFailureFeature,
        toBeAFailure
    ) {
        val successFunctions = uncheckedToNonNullable(
            unifySignatures(toBeASuccessFeature, toBeASuccess),
            unifySignatures(toBeASuccessFeatureNullable, toBeASuccessNullable)
        )
        val failureFunctions = unifySignatures(toBeAFailureFeature, toBeAFailure)

        context("subject is $resultSuccess") {
            successFunctions.forEach { (name, toBeASuccessFun, _) ->
                it("$name - can perform sub-assertion which holds") {
                    expect(resultSuccess).toBeASuccessFun { toEqual(1) }
                }
                it("$name - can perform sub-assertion which fails, throws AssertionError") {
                    expect {
                        expect(resultSuccess).toBeASuccessFun { toEqual(2) }
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            "$expectationVerb : .*$lineSeparator" +
                                "$g$f${DescriptionResultProof.VALUE.string}\\s+: 1$lineSeparator" +
                                "$indentG$indentF$x$toEqualDescr : 2"
                        )
                    )
                }
            }

            failureFunctions.forEach { (name, toBeAFailureFun, hasExtraHint) ->
                it("$name - throws AssertionError showing the expected type" + if (hasExtraHint) " and the expected message" else "") {
                    expect {
                        expect(resultSuccess).toBeAFailureFun { messageToContain("oh yes...") }
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr(DescriptionResultProof.EXCEPTION, isNotFailureDescr)
                            toContainDescr(
                                DescriptionAnyProof.TO_BE_AN_INSTANCE_OF,
                                IllegalArgumentException::class.simpleName
                            )
                            if (hasExtraHint) {
                                toContain(DescriptionCharSequenceProof.TO_CONTAIN.string)
                                toContainDescr(DescriptionCharSequenceProof.VALUE, "\"oh yes...\"")
                            }
                        }
                    }
                }
            }
        }

        context("subject is $resultFailure") {
            successFunctions.forEach { (name, toBeASuccessFun, hasExtraHint) ->
                it("$name throws AssertionError" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        expect(resultFailure).toBeASuccessFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr(DescriptionResultProof.VALUE, isNotSuccessDescr)
                            if (hasExtraHint) {
                                toContainToEqualDescr(1)
                            }
                        }
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
                        message {
                            toContainDescr(DescriptionResultProof.EXCEPTION, IllegalArgumentException::class.fullName)
                            toContain(DescriptionCharSequenceProof.TO_CONTAIN.string)
                            toContainDescr(DescriptionCharSequenceProof.VALUE, "\"oh yes...\"")
                        }
                    }
                }
            }
        }
    }

    describeFun(toBeASuccessFeatureNullable, toBeASuccessNullable) {
        val successFunctions = unifySignatures(toBeASuccessFeatureNullable, toBeASuccessNullable)

        successFunctions.forEach { (name, toBeASuccessFun, hasExtraHint) ->
            context("subject is $resultNullSuccess") {
                it("$name - can perform sub-assertion which holds") {
                    expect(resultNullSuccess).toBeASuccessFun { toEqual(null) }
                }
                it("$name - can perform sub-assertion which fails, throws AssertionError") {
                    expect {
                        expect(resultNullSuccess).toBeASuccessFun { toEqual(2) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr(DescriptionResultProof.VALUE, "null")
                            toContainToEqualDescr(2)
                        }
                    }
                }
            }

            context("subject is $resultNullableFailure") {
                it("${toBeASuccessFeature.name} throws AssertionError" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        expect(resultNullableFailure).toBeASuccessFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr(DescriptionResultProof.VALUE, isNotSuccessDescr)
                            if (hasExtraHint) toContainToEqualDescr(1)
                        }
                    }
                }
            }
        }
    }
})
