package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.DescriptionBigDecimalAssertions
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include
import java.math.BigDecimal

abstract class BigDecimalAssertionsSpec(
    verbs: AssertionVerbFactory,
    isNumericallyEqualToPair: Pair<String, AssertionPlant<BigDecimal>.(BigDecimal) -> AssertionPlant<BigDecimal>>,
    isNotNumericallyEqualToPair: Pair<String, AssertionPlant<BigDecimal>.(BigDecimal) -> AssertionPlant<BigDecimal>>,
    toBePair: Pair<String, AssertionPlant<BigDecimal>.(BigDecimal) -> AssertionPlant<BigDecimal>>,
    notToBePair: Pair<String, AssertionPlant<BigDecimal>.(BigDecimal) -> AssertionPlant<BigDecimal>>,
    notToBeAnyFun: AssertionPlant<Any>.(Any) -> AssertionPlant<Any>,
    describePrefix: String = "[Atrium] "
) : Spek({


    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<BigDecimal>("$describePrefix[BigDecimal] ",
        isNumericallyEqualToPair.first to mapToCreateAssertion { isNumericallyEqualToPair.second(this, BigDecimal.TEN) },
        isNotNumericallyEqualToPair.first to mapToCreateAssertion { isNotNumericallyEqualToPair.second(this, BigDecimal.TEN) },
        toBePair.first to mapToCreateAssertion { toBePair.second(this, BigDecimal.TEN) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<BigDecimal>(verbs, "$describePrefix[BigDecimal] ",
        checkingTriple(isNumericallyEqualToPair.first, { isNumericallyEqualToPair.second(this, BigDecimal.TEN) }, BigDecimal("10.000"), BigDecimal("10.00001")),
        checkingTriple(isNotNumericallyEqualToPair.first, { isNotNumericallyEqualToPair.second(this, BigDecimal.TEN) }, BigDecimal("10.00001"), BigDecimal("10.000")),
        checkingTriple(toBePair.first, { toBePair.second(this, BigDecimal.TEN) }, BigDecimal("10"), BigDecimal("10.0"))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val assert: (BigDecimal) -> Assert<BigDecimal> = verbs::checkImmediately
    val (isNumericallyEqualTo, isNumericallyEqualToFun) = isNumericallyEqualToPair
    val (isNotNumericallyEqualTo, isNotNumericallyEqualToFun) = isNotNumericallyEqualToPair
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
                        message {
                            contains(subject,
                                "${DescriptionBigDecimalAssertions.IS_NOT_NUMERICALLY_EQUAL_TO.getDefault()}: $expected"
                            )
                        }
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
                        message {
                            contains(subject,
                                "${DescriptionBigDecimalAssertions.IS_NUMERICALLY_EQUAL_TO.getDefault()}: $expected"
                            )
                        }
                    }
                }
                test("`$isNotNumericallyEqualTo` does not throw") {
                    assert(subject).isNotNumericallyEqualToFun(expected)
                }
            }
        }
    }

    describeFun(toBe) {
        listOf(
            BigDecimal.TEN,
            BigDecimal("10.")
        ).forEach { expected ->
            context("subject is 10 and expected is $expected") {
                it("does not throw") {
                    assert(BigDecimal.TEN).toBeFun(expected)
                }
            }
        }

        val failureHint = String.format(DescriptionBigDecimalAssertions.FAILURE_TO_BE_BUT_NUMERICALLY_EQUAL.getDefault(), isNumericallyEqualTo)
        listOf(
            BigDecimal("10.0"),
            BigDecimal("10.00")
        ).forEach { expected ->
            context("subject is 10 and expected is $expected") {
                it("throws an AssertionError mentioning that $isNumericallyEqualTo could have been used") {
                    expect {
                        assert(BigDecimal.TEN).toBeFun(expected)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                subject,
                                "${DescriptionAnyAssertion.TO_BE.getDefault()}: $expected",
                                failureHint
                            )
                        }
                    }
                }
            }
        }

        context("subject is 10 and expected is 9") {
            it("throws an AssertionError and does not contain the hint") {
                val expected = BigDecimal("9.999999999999")
                expect {
                    assert(BigDecimal.TEN).toBeFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        contains(subject, "${DescriptionAnyAssertion.TO_BE.getDefault()}: $expected")
                        containsNot(failureHint)
                    }
                }
            }
        }
    }

    describeFun(notToBe) {
        listOf(
            BigDecimal("9"),
            BigDecimal("10.0")
        ).forEach { expected ->
            context("subject is 10 and expected is $expected") {
                test("overload with BigDecimal throws ${UnsupportedOperationException::class.simpleName}") {
                    expect {
                        assert(BigDecimal.TEN).notToBeFun(expected)
                    }.toThrow<UnsupportedOperationException>()
                }
                test("overload with Any does not throw") {
                    assert(BigDecimal.TEN).notToBeAnyFun(expected)
                }
            }
        }

        test("overload with BigDecimal where subject is 10 and expected is 10 -- still throws ${UnsupportedOperationException::class.simpleName}") {
            expect {
                assert(BigDecimal.TEN).notToBeFun(BigDecimal.TEN)
            }.toThrow<UnsupportedOperationException>()
        }
    }
})

