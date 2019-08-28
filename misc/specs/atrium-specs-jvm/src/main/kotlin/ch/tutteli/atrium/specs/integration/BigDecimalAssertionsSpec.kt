package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.math.BigDecimal

abstract class BigDecimalAssertionsSpec(
    toBeDontUse: Fun1<BigDecimal, BigDecimal>,
    toBeNullableDontUse: Fun1<BigDecimal?, BigDecimal?>,
    toBeNull: Fun1<BigDecimal?, Nothing?>,
    toBeAnyFun: Expect<Any>.(Any) -> Expect<Any>,
    notToBe: Fun1<BigDecimal, BigDecimal>,
    notToBeAnyFun: Expect<Any>.(Any) -> Expect<Any>,
    isNumericallyEqualTo: Fun1<BigDecimal, BigDecimal>,
    isNotNumericallyEqualTo: Fun1<BigDecimal, BigDecimal>,
    isEqualIncludingScale: Fun1<BigDecimal, BigDecimal>,
    isNotEqualIncludingScale: Fun1<BigDecimal, BigDecimal>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<BigDecimal>(
        describePrefix,
        isNumericallyEqualTo.forSubjectLess(BigDecimal.TEN),
        isNotNumericallyEqualTo.forSubjectLess(BigDecimal.TEN),
        isEqualIncludingScale.forSubjectLess(BigDecimal.TEN),
        isNotEqualIncludingScale.forSubjectLess(BigDecimal.TEN)
    ) {})

    include(object : SubjectLessSpec<BigDecimal?>(
        "$describePrefix[nullable] ",
        toBeNull.forSubjectLess(null)
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    describeFun(isNumericallyEqualTo.name, isNotNumericallyEqualTo.name) {
        val isNumericallyEqualToFun = isNumericallyEqualTo.lambda
        val isNotNumericallyEqualToFun = isNotNumericallyEqualTo.lambda

        mapOf(
            BigDecimal.TEN to BigDecimal("10.00"),
            BigDecimal.ZERO to BigDecimal("0.0"),
            BigDecimal.ZERO to BigDecimal("0."),
            BigDecimal.ZERO to BigDecimal("0.00"),
            BigDecimal.ZERO to BigDecimal("00.0")
        ).forEach { (subject, expected) ->
            context("subject $subject and expected $expected") {
                it("`${isNumericallyEqualTo.name}` does not throw") {
                    expect(subject).isNumericallyEqualToFun(expected)
                }
                it("`${isNotNumericallyEqualTo.name}` throws AssertionError") {
                    expect {
                        expect(subject).isNotNumericallyEqualToFun(expected)
                    }.toThrow<AssertionError> {
                        messageContains(
                            subject,
                            "${DescriptionBigDecimalAssertion.IS_NOT_NUMERICALLY_EQUAL_TO.getDefault()}: $expected"
                        )
                    }
                }
            }
        }

        mapOf(
            BigDecimal.TEN to BigDecimal("10.00000001"),
            BigDecimal.ZERO to BigDecimal("0.0000001")
        ).forEach { (subject, expected) ->
            context("subject $subject and expected $expected") {
                it("`${isNumericallyEqualTo.name}` throws AssertionError") {
                    expect {
                        expect(subject).isNumericallyEqualToFun(expected)
                    }.toThrow<AssertionError> {
                        messageContains(
                            subject,
                            "${DescriptionBigDecimalAssertion.IS_NUMERICALLY_EQUAL_TO.getDefault()}: $expected"
                        )
                    }
                }
                it("`$isNotNumericallyEqualTo` does not throw") {
                    expect(subject).isNotNumericallyEqualToFun(expected)
                }
            }
        }
    }

    val assertTen = expect(BigDecimal.TEN as BigDecimal)
    val assertNullableTen: Expect<BigDecimal?> = expect(BigDecimal.TEN)
    val assertTenAny = expect(BigDecimal.TEN as Any)
    describeFun(
        toBeDontUse.name,
        "${toBeNullableDontUse.name} nullable",
        isEqualIncludingScale.name,
        notToBe.name,
        isNotEqualIncludingScale.name
    ) {
        val toBeFun = toBeDontUse.lambda
        val toBeNullableFun = toBeNullableDontUse.lambda
        val isEqualIncludingScaleFun = isEqualIncludingScale.lambda
        val notToBeFun = notToBe.lambda
        val isNotEqualIncludingScaleFun = isNotEqualIncludingScale.lambda

        val failureHintNotNumerically = String.format(
            DescriptionBigDecimalAssertion.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.getDefault(),
            isNotNumericallyEqualTo.name
        )
        context("subject is 10 and expected is 10") {
            val expected = BigDecimal("10")
            it("${toBeDontUse.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.toBeFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }
            it("${toBeDontUse.name} with BigDecimal? overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertNullableTen.toBeNullableFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }
            it("${notToBe.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.notToBeFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }

            it("${toBeDontUse.name} with Any overload does not throw") {
                assertTenAny.toBeAnyFun(expected)
            }
            it("$isEqualIncludingScale does not throw") {
                assertTen.isEqualIncludingScaleFun(expected)
            }

            it("${notToBe.name} with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    assertTenAny.notToBeAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(BigDecimal.TEN, "${NOT_TO_BE.getDefault()}: $expected")
                        containsNot(failureHintNotNumerically)
                    }
                }
            }
            it("${isNotEqualIncludingScale.name} throws an AssertionError and does not contain the hint") {
                expect {
                    assertTen.isNotEqualIncludingScaleFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            BigDecimal.TEN,
                            "${DescriptionBigDecimalAssertion.IS_NOT_EQUAL_INCLUDING_SCALE.getDefault()}: $expected"
                        )
                        containsNot(failureHintNotNumerically)
                    }
                }
            }
        }

        val failureHintNumerically = String.format(
            DescriptionBigDecimalAssertion.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.getDefault(),
            isNumericallyEqualTo.name
        )
        listOf(
            BigDecimal("10.0"),
            BigDecimal("10.00")
        ).forEach { expected ->
            context("subject is 10 and expected is $expected") {
                it("${toBeDontUse.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        assertTen.toBeFun(expected)
                    }.toThrow<PleaseUseReplacementException>()
                }
                it("${notToBe.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        assertTen.notToBeFun(expected)
                    }.toThrow<PleaseUseReplacementException>()
                }

                it("${toBeDontUse.name} with Any overload throws an AssertionError and does not contain the hint") {
                    expect {
                        assertTenAny.toBeAnyFun(expected)
                    }.toThrow<AssertionError> {
                        message {
                            contains(BigDecimal.TEN, "${TO_BE.getDefault()}: $expected")
                            containsNot(failureHintNumerically)
                        }
                    }
                }
                it("${isEqualIncludingScale.name} throws an AssertionError mentioning that ${isNumericallyEqualTo.name} could have been used") {
                    expect {
                        assertTen.isEqualIncludingScaleFun(expected)
                    }.toThrow<AssertionError> {
                        messageContains(
                            BigDecimal.TEN,
                            "${DescriptionBigDecimalAssertion.IS_EQUAL_INCLUDING_SCALE.getDefault()}: $expected",
                            failureHintNumerically
                        )
                    }
                }

                it("${notToBe.name} with Any overload does not throw") {
                    assertTenAny.notToBeAnyFun(expected)
                }
                it("${isEqualIncludingScale.name} does not throw") {
                    assertTen.isNotEqualIncludingScaleFun(expected)
                }
            }
        }

        context("subject is 10 and expected is 9") {
            val expected = BigDecimal("9.999999999999")
            it("${toBeDontUse.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.toBeFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }
            it("${notToBe.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.notToBeFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }

            it("${toBeDontUse.name} with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    assertTenAny.toBeAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(BigDecimal.TEN, "${TO_BE.getDefault()}: $expected")
                        containsNot(failureHintNumerically)
                    }
                }
            }
            it("${isEqualIncludingScale.name} throws an AssertionError and does not contain the hint") {
                expect {
                    assertTen.isEqualIncludingScaleFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            BigDecimal.TEN,
                            "${DescriptionBigDecimalAssertion.IS_EQUAL_INCLUDING_SCALE.getDefault()}: $expected"
                        )
                        containsNot(failureHintNumerically)
                    }
                }
            }

            it("${notToBe.name} with Any overload does not throw") {
                assertTenAny.notToBeAnyFun(expected)
            }
            it("${isEqualIncludingScale.name} does not throw") {
                assertTen.isNotEqualIncludingScaleFun(expected)
            }
        }
    }

    describeFun("${toBeNull.name} null") {
        val toBeFun = toBeNull.lambda
        it("does not throw if subject is null") {
            expect(null as BigDecimal?).toBeFun(null)
        }
    }
})

