package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
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
        toBeASuccessFeature.forSubjectLess(),
        toBeASuccess.forSubjectLess { toEqual(1) },
        toBeAFailureFeature.forSubjectLess(),
        toBeAFailure.forSubjectLess { messageToContain("message") }
    ) {})
    include(object : SubjectLessSpec<Result<Int?>>(
        "$describePrefix[nullable] ",
        toBeASuccessFeatureNullable.forSubjectLess(),
        toBeASuccessNullable.forSubjectLess { toEqual(1) }
    ) {})

    include(object : AssertionCreatorSpec<Result<Int>>(
        describePrefix, Result.success(2),
        toBeASuccess.forAssertionCreatorSpec("$toEqualDescr: 2") { toEqual(2) }
    ) {})
    include(object : AssertionCreatorSpec<Result<Int?>>(
        "$describePrefix[nullable] ", Result.success(2),
        toBeASuccessNullable.forAssertionCreatorSpec("$toEqualDescr: 2") { toEqual(2) }
    ) {})

    include(object : AssertionCreatorSpec<Result<Int>>(
        "$describePrefix[failure] ", Result.failure(IllegalArgumentException("oh no...")),
        assertionCreatorSpecTriple(
            toBeAFailure.name,
            "${DescriptionCharSequenceExpectation.VALUE.getDefault()}: \"oh no...\"",
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

    val valueDescr = DescriptionResultExpectation.VALUE.getDefault()
    val isNotSuccessDescr = DescriptionResultExpectation.IS_NOT_SUCCESS.getDefault()
    val isNotFailureDescr = DescriptionResultExpectation.IS_NOT_FAILURE.getDefault()
    val exceptionDescr = DescriptionResultExpectation.EXCEPTION.getDefault()

    describeFun(toBeASuccessFeature, toBeASuccess, toBeASuccessFeatureNullable, toBeASuccessNullable, toBeAFailureFeature, toBeAFailure) {
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

        context("subject is $resultFailure") {
            successFunctions.forEach { (name, toBeASuccessFun, hasExtraHint) ->
                it("$name throws AssertionError" + showsSubAssertionIf(hasExtraHint)) {
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
                        messageToContain("$valueDescr: null", "$toEqualDescr: 2")
                    }
                }
            }

            context("subject is $resultNullableFailure") {
                it("${toBeASuccessFeature.name} throws AssertionError" + showsSubAssertionIf(hasExtraHint)) {
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
})
