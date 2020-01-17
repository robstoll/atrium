package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.CONTAINS
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.VALUE
import ch.tutteli.atrium.translations.DescriptionResultAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ResultFeatureAssertionsSpec(
    isSuccessFeature: Feature0<Result<Int>, Int>,
    isSuccess: Fun1<Result<Int>, Expect<Int>.() -> Unit>,
    isSuccessNullableFeature: Feature0<Result<Int?>, Int?>,
    isSuccessNullable: Fun1<Result<Int?>, Expect<Int?>.() -> Unit>,
    isFailureFeature: Feature0<Result<Int>, IllegalArgumentException>,
    isFailure: Feature1<Result<Int>, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Result<Int>>(
        describePrefix,
        isSuccessFeature.forSubjectLess(),
        isSuccess.forSubjectLess { toBe(1) },
        isFailureFeature.forSubjectLess(),
        isFailure.forSubjectLess { messageContains("message") }
    ) {})
    include(object : SubjectLessSpec<Result<Int?>>(
        "$describePrefix[nullable] ",
        isSuccessNullableFeature.forSubjectLess(),
        isSuccessNullable.forSubjectLess { toBe(1) }
    ) {})

    include(object : AssertionCreatorSpec<Result<Int>>(
        describePrefix, Result.success(2),
        isSuccess.forAssertionCreatorSpec("$toBeDescr: 2") { toBe(2) }
    ) {})
    include(object : AssertionCreatorSpec<Result<Int?>>(
        "$describePrefix[nullable] ", Result.success(2),
        isSuccessNullable.forAssertionCreatorSpec("$toBeDescr: 2") { toBe(2) }
    ) {})

    include(object : AssertionCreatorSpec<Result<Int>>(
        "$describePrefix[failure] ", Result.failure(IllegalArgumentException("oh no...")),
        assertionCreatorSpecTriple(
            isFailure.name,
            "${VALUE.getDefault()}: \"oh no...\"",
            { apply { isFailure.invoke(this) { messageContains("oh no...") } } },
            { apply { isFailure.invoke(this) {} } }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val resultSuccess = Result.success(1)
    val resultFailure = Result.failure<Int>(IllegalArgumentException("oh no..."))
    val resultNullableSuccess = Result.success(1 as Int?)
    val resultNullSuccess = Result.success(null as Int?)
    val resultNullableFailure = Result.failure<Int?>(IllegalArgumentException("oh no..."))

    val isNotSuccessDescr = DescriptionResultAssertion.IS_NOT_SUCCESS.getDefault()
    val isNotFailureDescr = DescriptionResultAssertion.IS_NOT_FAILURE.getDefault()
    val exceptionDescr = DescriptionResultAssertion.EXCEPTION.getDefault()
    val isADescr = DescriptionAnyAssertion.IS_A.getDefault()

    describeFun(isSuccessFeature, isSuccess, isFailureFeature, isFailure) {
        val successFunctions = unifySignatures(isSuccessFeature, isSuccess)
        val failureFunctions = unifySignatures(isFailureFeature, isFailure)

        context("subject is $resultSuccess") {
            successFunctions.forEach { (name, isSuccessFun, _) ->
                it("$name - can perform sub-assertion which holds") {
                    expect(resultSuccess).isSuccessFun { toBe(1) }
                }
                it("$name - can perform sub-assertion which fails, throws AssertionError") {
                    expect {
                        expect(resultSuccess).isSuccessFun { toBe(2) }
                    }.toThrow<AssertionError> {
                        messageContains("value: 1", "$toBeDescr: 2")
                    }
                }
            }

            failureFunctions.forEach { (name, isFailureFun, hasExtraHint) ->
                it("$name - throws AssertionError showing the expected type" + if (hasExtraHint) " and the expected message" else "") {
                    expect {
                        expect(resultSuccess).isFailureFun { messageContains("oh yes...") }
                    }.toThrow<AssertionError> {
                        messageContains(
                            "exception: $isNotFailureDescr",
                            "$isADescr: ${IllegalArgumentException::class.simpleName}"
                        )
                        if (hasExtraHint) {
                            messageContains(
                                CONTAINS.getDefault(),
                                "${VALUE.getDefault()}: \"oh yes...\""
                            )
                        }
                    }
                }
            }
        }

        context("subject is $resultFailure") {
            successFunctions.forEach { (name, isSuccessFun, hasExtraHint) ->
                it("$name throws AssertionError" + if (hasExtraHint) " but shows intended sub-assertion" else "") {
                    expect {
                        expect(resultFailure).isSuccessFun { toBe(1) }
                    }.toThrow<AssertionError> {
                        messageContains("value: $isNotSuccessDescr")
                        if (hasExtraHint) messageContains("$toBeDescr: 1")
                    }
                }
            }
            failureFunctions.forEach { (name, isFailureFun, _) ->
                it("$name - can perform sub-assertion which holds") {
                    expect(resultFailure).isFailureFun { messageContains("oh no...") }
                }
                it("$name - can perform sub-assertion which fails, throws AssertionError") {
                    expect {
                        expect(resultFailure).isFailureFun { messageContains("oh yes...") }
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$exceptionDescr: ${IllegalArgumentException::class.fullName}",
                            CONTAINS.getDefault(), "${VALUE.getDefault()}: \"oh yes...\""
                        )
                    }
                }
            }
        }
    }


    describeFun(isSuccessNullableFeature, isSuccessNullable) {
        val successFunctions = unifySignatures(isSuccessNullableFeature, isSuccessNullable)

        successFunctions.forEach { (name, isSuccessFun, hasExtraHint) ->
            context("$resultNullableSuccess") {
                it("$name - can perform sub-assertion which holds") {
                    expect(resultNullableSuccess).isSuccessFun { toBe(1) }
                }
                it("$name - can perform sub-assertion which fails, throws AssertionError") {
                    expect {
                        expect(resultNullableSuccess).isSuccessFun { toBe(2) }
                    }.toThrow<AssertionError> {
                        messageContains("value: 1", "$toBeDescr: 2")
                    }
                }
            }
            context("$resultNullSuccess") {
                it("$name - can perform sub-assertion which holds") {
                    expect(resultNullSuccess).isSuccessFun { toBe(null) }
                }
                it("$name - can perform sub-assertion which fails, throws AssertionError") {
                    expect {
                        expect(resultNullSuccess).isSuccessFun { toBe(2) }
                    }.toThrow<AssertionError> {
                        messageContains("value: null", "$toBeDescr: 2")
                    }
                }
            }

            context("$resultNullableFailure") {
                it("${isSuccessFeature.name} throws AssertionError" + if (hasExtraHint) " but shows intended sub-assertion" else "") {
                    expect {
                        expect(resultNullableFailure).isSuccessFun { toBe(1) }
                    }.toThrow<AssertionError> {
                        messageContains("value: $isNotSuccessDescr")
                        if (hasExtraHint) messageContains("$toBeDescr: 1")
                    }
                }
            }
        }
    }
})
