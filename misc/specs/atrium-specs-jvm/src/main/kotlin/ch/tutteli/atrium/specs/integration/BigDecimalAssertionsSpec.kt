package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.math.BigDecimal

abstract class BigDecimalAssertionsSpec(
    verbs: AssertionVerbFactory,
    toBePair: Fun1<BigDecimal, BigDecimal>,
    toBeAnyFun: Expect<Any>.(Any) -> Expect<Any>,
    notToBePair: Fun1<BigDecimal, BigDecimal>,
    notToBeAnyFun: Expect<Any>.(Any) -> Expect<Any>,
    isNumericallyEqualToPair: Fun1<BigDecimal, BigDecimal>,
    isNotNumericallyEqualToPair: Fun1<BigDecimal, BigDecimal>,
    isEqualIncludingScalePair: Fun1<BigDecimal, BigDecimal>,
    isNotEqualIncludingScalePair: Fun1<BigDecimal, BigDecimal>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<BigDecimal>(
        "$describePrefix[BigDecimal] ",
        isNumericallyEqualToPair.forSubjectLess(BigDecimal.TEN),
        isNotNumericallyEqualToPair.forSubjectLess(BigDecimal.TEN),
        isEqualIncludingScalePair.forSubjectLess(BigDecimal.TEN),
        isNotEqualIncludingScalePair.forSubjectLess(BigDecimal.TEN)
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val assert: (BigDecimal) -> Expect<BigDecimal> = verbs::check
    val (isNumericallyEqualTo, isNumericallyEqualToFun) = isNumericallyEqualToPair
    val (isNotNumericallyEqualTo, isNotNumericallyEqualToFun) = isNotNumericallyEqualToPair
    val (isEqualIncludingScale, isEqualIncludingScaleFun) = isEqualIncludingScalePair
    val (isNotEqualIncludingScale, isNotEqualIncludingScaleFun) = isNotEqualIncludingScalePair
    val (toBe, toBeFun) = toBePair
    val (notToBe, notToBeFun) = notToBePair

    describeFun(isNumericallyEqualTo, isNotNumericallyEqualTo) {
        mapOf(
            BigDecimal.TEN to BigDecimal("10.00"),
            BigDecimal.ZERO to BigDecimal("0.0"),
            BigDecimal.ZERO to BigDecimal("0."),
            BigDecimal.ZERO to BigDecimal("0.00"),
            BigDecimal.ZERO to BigDecimal("00.0")
        ).forEach { subject, expected ->
            context("subject $subject and expected $expected") {
                it("`$isNumericallyEqualTo` does not throw") {
                    assert(subject).isNumericallyEqualToFun(expected)
                }
                it("`$isNotNumericallyEqualTo` throws AssertionError") {
                    expect {
                        assert(subject).isNotNumericallyEqualToFun(expected)
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
        ).forEach { subject, expected ->
            context("subject $subject and expected $expected") {
                it("`$isNumericallyEqualTo` throws AssertionError") {
                    expect {
                        assert(subject).isNumericallyEqualToFun(expected)
                    }.toThrow<AssertionError> {
                        messageContains(
                            subject,
                            "${DescriptionBigDecimalAssertion.IS_NUMERICALLY_EQUAL_TO.getDefault()}: $expected"
                        )
                    }
                }
                it("`$isNotNumericallyEqualTo` does not throw") {
                    assert(subject).isNotNumericallyEqualToFun(expected)
                }
            }
        }
    }

    val assertTen = assert(BigDecimal.TEN)
    val assertTenAny = verbs.check(BigDecimal.TEN as Any)
    describeFun(toBe, isEqualIncludingScale, notToBe, isNotEqualIncludingScale) {

        val failureHintNotNumerically = String.format(
            DescriptionBigDecimalAssertion.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.getDefault(),
            isNotNumericallyEqualTo
        )
        context("subject is 10 and expected is 10") {
            val expected = BigDecimal("10")
            it("$toBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.toBeFun(expected)
                }.toThrow<PleaseUseReplacementException> {}
            }
            it("$notToBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.notToBeFun(expected)
                }.toThrow<PleaseUseReplacementException> {}
            }

            it("$toBe with Any overload does not throw") {
                assertTenAny.toBeAnyFun(expected)
            }
            it("$isEqualIncludingScale does not throw") {
                assertTen.isEqualIncludingScaleFun(expected)
            }

            it("$notToBe with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    assertTenAny.notToBeAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(BigDecimal.TEN, "${DescriptionAnyAssertion.NOT_TO_BE.getDefault()}: $expected")
                        containsNot(failureHintNotNumerically)
                    }
                }
            }
            it("$isNotEqualIncludingScale throws an AssertionError and does not contain the hint") {
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
            isNumericallyEqualTo
        )
        listOf(
            BigDecimal("10.0"),
            BigDecimal("10.00")
        ).forEach { expected ->
            context("subject is 10 and expected is $expected") {
                it("$toBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        assertTen.toBeFun(expected)
                    }.toThrow<PleaseUseReplacementException> {}
                }
                it("$notToBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        assertTen.notToBeFun(expected)
                    }.toThrow<PleaseUseReplacementException> {}
                }

                it("$toBe with Any overload throws an AssertionError and does not contain the hint") {
                    expect {
                        assertTenAny.toBeAnyFun(expected)
                    }.toThrow<AssertionError> {
                        message {
                            contains(BigDecimal.TEN, "${DescriptionAnyAssertion.TO_BE.getDefault()}: $expected")
                            containsNot(failureHintNumerically)
                        }
                    }
                }
                it("$isEqualIncludingScale throws an AssertionError mentioning that $isNumericallyEqualTo could have been used") {
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

                it("$notToBe with Any overload does not throw") {
                    assertTenAny.notToBeAnyFun(expected)
                }
                it("$isEqualIncludingScale does not throw") {
                    assertTen.isNotEqualIncludingScaleFun(expected)
                }
            }
        }

        context("subject is 10 and expected is 9") {
            val expected = BigDecimal("9.999999999999")
            it("$toBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.toBeFun(expected)
                }.toThrow<PleaseUseReplacementException> {}
            }
            it("$notToBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.notToBeFun(expected)
                }.toThrow<PleaseUseReplacementException> {}
            }

            it("$toBe with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    assertTenAny.toBeAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(BigDecimal.TEN, "${DescriptionAnyAssertion.TO_BE.getDefault()}: $expected")
                        containsNot(failureHintNumerically)
                    }
                }
            }
            it("$isEqualIncludingScale throws an AssertionError and does not contain the hint") {
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

            it("$notToBe with Any overload does not throw") {
                assertTenAny.notToBeAnyFun(expected)
            }
            it("$isEqualIncludingScale does not throw") {
                assertTen.isNotEqualIncludingScaleFun(expected)
            }
        }
    }
})

