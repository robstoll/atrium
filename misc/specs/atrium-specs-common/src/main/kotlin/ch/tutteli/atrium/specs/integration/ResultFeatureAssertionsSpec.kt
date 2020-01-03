package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionResultAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ResultFeatureAssertionsSpec(
    isSuccessFeature: Feature0<Result<Int>, Int>,
    isSuccess: Fun1<Result<Int>, Expect<Int>.() -> Unit>,
    isSuccessNullableFeature: Feature0<Result<Int?>, Int?>,
    isSuccessNullable: Fun1<Result<Int?>, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Result<Int>>(
        describePrefix,
        isSuccessFeature.forSubjectLess().adjustName { "$it feature" },
        isSuccess.forSubjectLess { toBe(1) }
    ) {})
    include(object : SubjectLessSpec<Result<Int?>>(
        describePrefix,
        isSuccessNullableFeature.forSubjectLess().adjustName { "$it feature" },
        isSuccessNullable.forSubjectLess { toBe(1) }
    ) {})

    include(object : AssertionCreatorSpec<Result<Int>>(
        describePrefix, Result.success(2),
        isSuccess.forAssertionCreatorSpec("$toBeDescr: 2") { toBe(2) }
    ) {})
    include(object : AssertionCreatorSpec<Result<Int?>>(
        describePrefix, Result.success(2),
        isSuccessNullable.forAssertionCreatorSpec("$toBeDescr: 2") { toBe(2) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val resultSuccess = Result.success(1)
    val resultFailure = Result.failure<Int>(IllegalArgumentException("oh no..."))
    val resultNullableSuccess = Result.success(1 as Int?)
    val resultNullSuccess = Result.success(null as Int?)
    val resultNullableFailure = Result.failure<Int?>(IllegalArgumentException("oh no..."))

    val isNotSuccessDescr = DescriptionResultAssertion.IS_NOT_SUCCESS.getDefault()

    describeFun("${isSuccessFeature.name} feature") {
        val isSuccessFun = isSuccessFeature.lambda

        context("$resultSuccess") {
            it("${isSuccessFeature.name} - can perform sub-assertion which holds") {
                expect(resultSuccess).isSuccessFun().toBe(1)
            }
            it("${isSuccessFeature.name} - can perform sub-assertion which fails, throws AssertionError") {
                expect {
                    expect(resultSuccess).isSuccessFun().toBe(2)
                }.toThrow<AssertionError> {
                    messageContains("value: 1", "$toBeDescr: 2")
                }
            }
        }

        context("$resultFailure") {
            it("${isSuccessFeature.name} throws AssertionError") {
                expect {
                    expect(resultFailure).isSuccessFun().toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("value: $isNotSuccessDescr")
                }
            }
        }
    }

    describeFun(isSuccess.name) {
        val isSuccessFun = isSuccess.lambda

        context("$resultSuccess") {
            it("${isSuccessFeature.name} - can perform sub-assertion which holds") {
                expect(resultSuccess).isSuccessFun { toBe(1) }
            }
            it("${isSuccessFeature.name} - can perform sub-assertion which fails, throws AssertionError") {
                expect {
                    expect(resultSuccess).isSuccessFun { toBe(2) }
                }.toThrow<AssertionError> {
                    messageContains("value: 1", "$toBeDescr: 2")
                }
            }
        }

        context("$resultFailure") {
            it("${isSuccessFeature.name} throws AssertionError but shows intended sub-assertion") {
                expect {
                    expect(resultFailure).isSuccessFun { toBe(1) }
                }.toThrow<AssertionError> {
                    messageContains("value: $isNotSuccessDescr", "$toBeDescr: 1")
                }
            }
        }
    }

    describeFun("${isSuccessNullableFeature.name} nullable feature") {
        val isSuccessFun = isSuccessNullableFeature.lambda

        context("$resultNullableSuccess") {
            it("${isSuccessFeature.name} - can perform sub-assertion which holds") {
                expect(resultNullableSuccess).isSuccessFun().toBe(1)
            }
            it("${isSuccessFeature.name} - can perform sub-assertion which fails, throws AssertionError") {
                expect {
                    expect(resultNullableSuccess).isSuccessFun().toBe(2)
                }.toThrow<AssertionError> {
                    messageContains("value: 1", "$toBeDescr: 2")
                }
            }
        }
        context("$resultNullSuccess") {
            it("${isSuccessFeature.name} - can perform sub-assertion which holds") {
                expect(resultNullSuccess).isSuccessFun().toBe(null)
            }
            it("${isSuccessFeature.name} - can perform sub-assertion which fails, throws AssertionError") {
                expect {
                    expect(resultNullSuccess).isSuccessFun().toBe(2)
                }.toThrow<AssertionError> {
                    messageContains("value: null", "$toBeDescr: 2", "${DescriptionAnyAssertion.IS_A.getDefault()}: Int")
                }
            }
        }

        context("$resultNullableFailure") {
            it("${isSuccessFeature.name} throws AssertionError") {
                expect {
                    expect(resultNullableFailure).isSuccessFun().toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("value: $isNotSuccessDescr")
                }
            }
        }
    }

    describeFun("${isSuccessNullable.name} nullable") {
        val isSuccessFun = isSuccessNullable.lambda

        context("$resultNullableSuccess") {
            it("${isSuccessFeature.name} - can perform sub-assertion which holds") {
                expect(resultNullableSuccess).isSuccessFun { toBe(1) }
            }
            it("${isSuccessFeature.name} - can perform sub-assertion which fails, throws AssertionError") {
                expect {
                    expect(resultNullableSuccess).isSuccessFun { toBe(2) }
                }.toThrow<AssertionError> {
                    messageContains("value: 1", "$toBeDescr: 2")
                }
            }
        }
        context("$resultNullSuccess") {
            it("${isSuccessFeature.name} - can perform sub-assertion which holds") {
                expect(resultNullSuccess).isSuccessFun { toBe(null) }
            }
            it("${isSuccessFeature.name} - can perform sub-assertion which fails, throws AssertionError") {
                expect {
                    expect(resultNullSuccess).isSuccessFun { toBe(2) }
                }.toThrow<AssertionError> {
                    messageContains("value: null", "$toBeDescr: 2", "${DescriptionAnyAssertion.IS_A.getDefault()}: Int")
                }
            }
        }

        context("$resultNullableFailure") {
            it("${isSuccessFeature.name} throws AssertionError but shows intended sub-assertion") {
                expect {
                    expect(resultNullableFailure).isSuccessFun { toBe(1) }
                }.toThrow<AssertionError> {
                    messageContains("value: $isNotSuccessDescr", "$toBeDescr: 1")
                }
            }
        }
    }
})
