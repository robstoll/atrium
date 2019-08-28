@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include
import java.math.BigDecimal

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class BigDecimalAssertionsSpec(
    verbs: AssertionVerbFactory,
    toBePair: Pair<String, Assert<BigDecimal>.(BigDecimal) -> Assert<BigDecimal>>,
    toBeAnyFun: Assert<Any>.(Any) -> Assert<Any>,
    notToBePair: Pair<String, Assert<BigDecimal>.(BigDecimal) -> Assert<BigDecimal>>,
    notToBeAnyFun: Assert<Any>.(Any) -> Assert<Any>,
    isNumericallyEqualToPair: Pair<String, Assert<BigDecimal>.(BigDecimal) -> Assert<BigDecimal>>,
    isNotNumericallyEqualToPair: Pair<String, Assert<BigDecimal>.(BigDecimal) -> Assert<BigDecimal>>,
    isEqualIncludingScalePair: Pair<String, Assert<BigDecimal>.(BigDecimal) -> Assert<BigDecimal>>,
    isNotEqualIncludingScalePair: Pair<String, Assert<BigDecimal>.(BigDecimal) -> Assert<BigDecimal>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<BigDecimal>("$describePrefix[BigDecimal] ",
        isNumericallyEqualToPair.first to mapToCreateAssertion { isNumericallyEqualToPair.second(this, BigDecimal.TEN) },
        isNotNumericallyEqualToPair.first to mapToCreateAssertion { isNotNumericallyEqualToPair.second(this, BigDecimal.TEN) },
        isEqualIncludingScalePair.first to mapToCreateAssertion { isEqualIncludingScalePair.second(this, BigDecimal.TEN) },
        isNotEqualIncludingScalePair.first to mapToCreateAssertion { isNotEqualIncludingScalePair.second(this, BigDecimal.TEN) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<BigDecimal>(verbs, "$describePrefix[BigDecimal] ",
        checkingTriple(isNumericallyEqualToPair.first, { isNumericallyEqualToPair.second(this, BigDecimal.TEN) }, BigDecimal("10.000"), BigDecimal("10.00001")),
        checkingTriple(isNotNumericallyEqualToPair.first, { isNotNumericallyEqualToPair.second(this, BigDecimal.TEN) }, BigDecimal("10.00001"), BigDecimal("10.000")),
        checkingTriple(isEqualIncludingScalePair.first, { isEqualIncludingScalePair.second(this, BigDecimal.TEN) }, BigDecimal("10"), BigDecimal("10.0")),
        checkingTriple(isNotEqualIncludingScalePair.first, { isNotEqualIncludingScalePair.second(this, BigDecimal.TEN) }, BigDecimal("10.0"), BigDecimal("10"))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) = describeFun(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val assert: (BigDecimal) -> Assert<BigDecimal> = verbs::checkImmediately
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
                test("`$isNumericallyEqualTo` does not throw") {
                    assert(subject).isNumericallyEqualToFun(expected)
                }
                test("`$isNotNumericallyEqualTo` throws AssertionError") {
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
                test("`$isNumericallyEqualTo` throws AssertionError") {
                    expect {
                        assert(subject).isNumericallyEqualToFun(expected)
                    }.toThrow<AssertionError> {
                        messageContains(
                            subject,
                            "${DescriptionBigDecimalAssertion.IS_NUMERICALLY_EQUAL_TO.getDefault()}: $expected"
                        )
                    }
                }
                test("`$isNotNumericallyEqualTo` does not throw") {
                    assert(subject).isNotNumericallyEqualToFun(expected)
                }
            }
        }
    }

    val assertTen = assert(BigDecimal.TEN)
    describeFun(toBe, isEqualIncludingScale, notToBe, isNotEqualIncludingScale) {

        val failureHintNotNumerically = String.format(DescriptionBigDecimalAssertion.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.getDefault(), isNotNumericallyEqualTo)
        context("subject is 10 and expected is 10") {
            val expected = BigDecimal("10")
            test("$toBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.toBeFun(expected)
                }.toThrow<PleaseUseReplacementException> {}
            }
            test("$notToBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.notToBeFun(expected)
                }.toThrow<PleaseUseReplacementException> {}
            }

            test("$toBe with Any overload does not throw") {
                assertTen.toBeAnyFun(expected)
            }
            test("$isEqualIncludingScale does not throw") {
                assertTen.isEqualIncludingScaleFun(expected)
            }

            test("$notToBe with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    assertTen.notToBeAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(BigDecimal.TEN, "${NOT_TO_BE.getDefault()}: $expected")
                        containsNot(failureHintNotNumerically)
                    }
                }
            }
            test("$isNotEqualIncludingScale throws an AssertionError and does not contain the hint") {
                expect {
                    assertTen.isNotEqualIncludingScaleFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(BigDecimal.TEN, "${DescriptionBigDecimalAssertion.IS_NOT_EQUAL_INCLUDING_SCALE.getDefault()}: $expected")
                        containsNot(failureHintNotNumerically)
                    }
                }
            }
        }

        val failureHintNumerically = String.format(DescriptionBigDecimalAssertion.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.getDefault(), isNumericallyEqualTo)
        listOf(
            BigDecimal("10.0"),
            BigDecimal("10.00")
        ).forEach { expected ->
            context("subject is 10 and expected is $expected") {
                test("$toBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        assertTen.toBeFun(expected)
                    }.toThrow<PleaseUseReplacementException> {}
                }
                test("$notToBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        assertTen.notToBeFun(expected)
                    }.toThrow<PleaseUseReplacementException> {}
                }

                test("$toBe with Any overload throws an AssertionError and does not contain the hint") {
                    expect {
                        assertTen.toBeAnyFun(expected)
                    }.toThrow<AssertionError> {
                        message {
                            contains(BigDecimal.TEN, "${TO_BE.getDefault()}: $expected")
                            containsNot(failureHintNumerically)
                        }
                    }
                }
                test("$isEqualIncludingScale throws an AssertionError mentioning that $isNumericallyEqualTo could have been used") {
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

                test("$notToBe with Any overload does not throw") {
                    assertTen.notToBeAnyFun(expected)
                }
                test("$isEqualIncludingScale does not throw") {
                    assertTen.isNotEqualIncludingScaleFun(expected)
                }
            }
        }

        context("subject is 10 and expected is 9") {
            val expected = BigDecimal("9.999999999999")
            test("$toBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.toBeFun(expected)
                }.toThrow<PleaseUseReplacementException> {}
            }
            test("$notToBe with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    assertTen.notToBeFun(expected)
                }.toThrow<PleaseUseReplacementException> {}
            }

            test("$toBe with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    assertTen.toBeAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(BigDecimal.TEN, "${TO_BE.getDefault()}: $expected")
                        containsNot(failureHintNumerically)
                    }
                }
            }
            test("$isEqualIncludingScale throws an AssertionError and does not contain the hint") {
                expect {
                    assertTen.isEqualIncludingScaleFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(BigDecimal.TEN, "${DescriptionBigDecimalAssertion.IS_EQUAL_INCLUDING_SCALE.getDefault()}: $expected")
                        containsNot(failureHintNumerically)
                    }
                }
            }

            test("$notToBe with Any overload does not throw") {
                assertTen.notToBeAnyFun(expected)
            }
            test("$isEqualIncludingScale does not throw") {
                assertTen.isNotEqualIncludingScaleFun(expected)
            }
        }
    }
})

