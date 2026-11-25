package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.PleaseUseReplacementException
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.NOT_TO_EQUAL
import ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion
import java.math.BigDecimal

@Suppress("FunctionName")
abstract class AbstractBigDecimalExpectationsTest(
    private val toEqualDontUseSpec: Fun1<BigDecimal, BigDecimal>,
    private val toEqualNullableDontUseSpec: Fun1<BigDecimal?, BigDecimal?>,
    private val toEqualNullSpec: Fun1<BigDecimal?, Nothing?>,
    private val toEqualAnyFun: Expect<Any>.(Any) -> Expect<Any>,
    private val notToEqualSpec: Fun1<BigDecimal, BigDecimal>,
    private val notToEqualAnyFun: Expect<Any>.(Any) -> Expect<Any>,
    private val toEqualNumericallySpec: Fun1<BigDecimal, BigDecimal>,
    private val notToEqualNumericallySpec: Fun1<BigDecimal, BigDecimal>,
    private val toEqualIncludingScaleSpec: Fun1<BigDecimal, BigDecimal>,
    private val notToEqualIncludingScaleSpec: Fun1<BigDecimal, BigDecimal>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toEqualNumericallySpec.forSubjectLessTest(BigDecimal.TEN),
        notToEqualNumericallySpec.forSubjectLessTest(BigDecimal.TEN),
        toEqualIncludingScaleSpec.forSubjectLessTest(BigDecimal.TEN),
        notToEqualIncludingScaleSpec.forSubjectLessTest(BigDecimal.TEN)
    )

    @TestFactory
    fun nullableSubjectLessTest() = subjectLessTestFactory(
        toEqualNullSpec.forSubjectLessTest(null),
        groupPrefix = "[nullable] "
    )

    @TestFactory
    fun toEqualNumerically_notToEqualNumerically() = testFactory(
        toEqualNumericallySpec,
        notToEqualNumericallySpec
    ) { toEqualNumericallyFun, notToEqualNumericallyFun ->
        mapOf(
            BigDecimal.TEN to BigDecimal("10.00"),
            BigDecimal.ZERO to BigDecimal("0.0"),
            BigDecimal.ZERO to BigDecimal("0."),
            BigDecimal.ZERO to BigDecimal("0.00"),
            BigDecimal.ZERO to BigDecimal("00.0")
        ).forEach { (subject, expected) ->
            describe("subject $subject and expected $expected") {
                it("`${toEqualNumericallySpec.name}` does not throw") {
                    expect(subject).toEqualNumericallyFun(expected)
                }
                it("`${notToEqualNumericallySpec.name}` throws AssertionError") {
                    expect {
                        expect(subject).notToEqualNumericallyFun(expected)
                    }.toThrow<AssertionError> {
                        messageToContain(
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
            describe("subject $subject and expected $expected") {
                it("`${toEqualNumericallySpec.name}` throws AssertionError") {
                    expect {
                        expect(subject).toEqualNumericallyFun(expected)
                    }.toThrow<AssertionError> {
                        messageToContain(
                            subject,
                            "${DescriptionBigDecimalAssertion.IS_NUMERICALLY_EQUAL_TO.getDefault()}: $expected"
                        )
                    }
                }
                it("`${notToEqualNumericallySpec.name}` does not throw") {
                    expect(subject).notToEqualNumericallyFun(expected)
                }
            }
        }
    }

    @TestFactory
    fun toEqual_overloads() = testFactory(
        toEqualDontUseSpec,
        toEqualNullableDontUseSpec,
        toEqualIncludingScaleSpec,
        notToEqualSpec,
        notToEqualIncludingScaleSpec
    ) {
        val toEqualFun = toEqualDontUseSpec.lambda
        val toEqualNullableFun = toEqualNullableDontUseSpec.lambda
        val toEqualIncludingScaleFun = toEqualIncludingScaleSpec.lambda
        val notToEqualFun = notToEqualSpec.lambda
        val notToEqualIncludingScaleFun = notToEqualIncludingScaleSpec.lambda

        val failureHintNotNumerically = String.format(
            DescriptionBigDecimalAssertion.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.getDefault(),
            notToEqualNumericallySpec.name
        )
        describe("subject is 10 and expected is 10") {
            val expected = BigDecimal("10")
            it("${toEqualDontUseSpec.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).toEqualFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }
            it("${toEqualDontUseSpec.name} with BigDecimal? overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    @Suppress("USELESS_CAST")
                    expect(BigDecimal.TEN as BigDecimal?).toEqualNullableFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }
            it("${notToEqualSpec.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).notToEqualFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }

            it("${toEqualDontUseSpec.name} with Any overload does not throw") {
                expect(BigDecimal.TEN as Any).toEqualAnyFun(expected)
            }
            it("${toEqualIncludingScaleSpec.name} does not throw") {
                expect(BigDecimal.TEN as BigDecimal).toEqualIncludingScaleFun(expected)
            }

            it("${notToEqualSpec.name} with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    expect(BigDecimal.TEN as Any).notToEqualAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        toContain(BigDecimal.TEN, "${NOT_TO_EQUAL.getDefault()}: $expected")
                        notToContain(failureHintNotNumerically)
                    }
                }
            }
            it("${notToEqualIncludingScaleSpec.name} throws an AssertionError and does not contain the hint") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).notToEqualIncludingScaleFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            BigDecimal.TEN,
                            "${DescriptionBigDecimalAssertion.IS_NOT_EQUAL_INCLUDING_SCALE.getDefault()}: $expected"
                        )
                        notToContain(failureHintNotNumerically)
                    }
                }
            }
        }

        val failureHintNumerically = String.format(
            DescriptionBigDecimalAssertion.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.getDefault(),
            toEqualNumericallySpec.name
        )
        listOf(
            BigDecimal("10.0"),
            BigDecimal("10.00")
        ).forEach { expected ->
            describe("subject is 10 and expected is $expected") {
                it("${toEqualDontUseSpec.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        expect(BigDecimal.TEN as BigDecimal).toEqualFun(expected)
                    }.toThrow<PleaseUseReplacementException>()
                }
                it("${notToEqualSpec.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        expect(BigDecimal.TEN as BigDecimal).notToEqualFun(expected)
                    }.toThrow<PleaseUseReplacementException>()
                }

                it("${toEqualDontUseSpec.name} with Any overload throws an AssertionError and does not contain the hint") {
                    expect {
                        expect(BigDecimal.TEN as Any).toEqualAnyFun(expected)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(BigDecimal.TEN, "$toEqualDescr: $expected")
                            notToContain(failureHintNumerically)
                        }
                    }
                }
                it("${toEqualIncludingScaleSpec.name} throws an AssertionError mentioning that ${toEqualNumericallySpec.name} could have been used") {
                    expect {
                        expect(BigDecimal.TEN as BigDecimal).toEqualIncludingScaleFun(expected)
                    }.toThrow<AssertionError> {
                        messageToContain(
                            BigDecimal.TEN,
                            "${DescriptionBigDecimalAssertion.IS_EQUAL_INCLUDING_SCALE.getDefault()}: $expected",
                            "${hintBulletPoint}$failureHintNumerically"
                        )
                    }
                }

                it("${notToEqualSpec.name} with Any overload does not throw") {
                    expect(BigDecimal.TEN as Any).notToEqualAnyFun(expected)
                }
                it("${toEqualIncludingScaleSpec.name} does not throw") {
                    expect(BigDecimal.TEN as BigDecimal).notToEqualIncludingScaleFun(expected)
                }
            }
        }

        describe("subject is 10 and expected is 9") {
            val expected = BigDecimal("9.999999999999")
            it("${toEqualDontUseSpec.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).toEqualFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }
            it("${notToEqualSpec.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).notToEqualFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }

            it("${toEqualDontUseSpec.name} with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    expect(BigDecimal.TEN as Any).toEqualAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        toContain(BigDecimal.TEN, "$toEqualDescr: $expected")
                        notToContain(failureHintNumerically)
                    }
                }
            }
            it("${toEqualIncludingScaleSpec.name} throws an AssertionError and does not contain the hint") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).toEqualIncludingScaleFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            BigDecimal.TEN,
                            "${DescriptionBigDecimalAssertion.IS_EQUAL_INCLUDING_SCALE.getDefault()}: $expected"
                        )
                        notToContain(failureHintNumerically)
                    }
                }
            }

            it("${notToEqualSpec.name} with Any overload does not throw") {
                expect(BigDecimal.TEN as Any).notToEqualAnyFun(expected)
            }
            it("${toEqualIncludingScaleSpec.name} does not throw") {
                expect(BigDecimal.TEN as BigDecimal).notToEqualIncludingScaleFun(expected)
            }
        }
    }

    @TestFactory
    fun toEqualNull() = testFactory(toEqualNullSpec) { toEqualFun ->
        it("does not throw if subject is null") {
            expect(null as BigDecimal?).toEqualFun(null)
        }
    }
}
