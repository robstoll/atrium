package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.PleaseUseReplacementException
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBigDecimalProof
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.math.BigDecimal

abstract class BigDecimalExpectationsSpec(
    toEqualDontUse: Fun1<BigDecimal, BigDecimal>,
    toEqualNullableDontUse: Fun1<BigDecimal?, BigDecimal?>,
    toEqualNull: Fun1<BigDecimal?, Nothing?>,
    toEqualAnyFun: Expect<Any>.(Any) -> Expect<Any>,
    notToEqual: Fun1<BigDecimal, BigDecimal>,
    notToEqualAnyFun: Expect<Any>.(Any) -> Expect<Any>,
    toEqualNumerically: Fun1<BigDecimal, BigDecimal>,
    notToEqualNumerically: Fun1<BigDecimal, BigDecimal>,
    toEqualIncludingScale: Fun1<BigDecimal, BigDecimal>,
    notToEqualIncludingScale: Fun1<BigDecimal, BigDecimal>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<BigDecimal>(
        describePrefix,
        toEqualNumerically.forSubjectLess(BigDecimal.TEN),
        notToEqualNumerically.forSubjectLess(BigDecimal.TEN),
        toEqualIncludingScale.forSubjectLess(BigDecimal.TEN),
        notToEqualIncludingScale.forSubjectLess(BigDecimal.TEN)
    ) {})

    include(object : SubjectLessSpec<BigDecimal?>(
        "$describePrefix[nullable] ",
        toEqualNull.forSubjectLess(null)
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    describeFun(toEqualNumerically, notToEqualNumerically) {
        val toEqualNumericallyFun = toEqualNumerically.lambda
        val notToEqualNumericallyFun = notToEqualNumerically.lambda

        mapOf(
            BigDecimal.TEN to BigDecimal("10.00"),
            BigDecimal.ZERO to BigDecimal("0.0"),
            BigDecimal.ZERO to BigDecimal("0."),
            BigDecimal.ZERO to BigDecimal("0.00"),
            BigDecimal.ZERO to BigDecimal("00.0")
        ).forEach { (subject, expected) ->
            context("subject $subject and expected $expected") {
                it("`${toEqualNumerically.name}` does not throw") {
                    expect(subject).toEqualNumericallyFun(expected)
                }
                it("`${notToEqualNumerically.name}` throws AssertionError") {
                    expect {
                        expect(subject).notToEqualNumericallyFun(expected)
                    }.toThrow<AssertionError> {
                        message {
                            toContainSubject(subject)
                            toContainDescr(DescriptionBigDecimalProof.IS_NOT_NUMERICALLY_EQUAL_TO, expected)
                        }
                    }
                }
            }
        }

        mapOf(
            BigDecimal.TEN to BigDecimal("10.00000001"),
            BigDecimal.ZERO to BigDecimal("0.0000001")
        ).forEach { (subject, expected) ->
            context("subject $subject and expected $expected") {
                it("`${toEqualNumerically.name}` throws AssertionError") {
                    expect {
                        expect(subject).toEqualNumericallyFun(expected)
                    }.toThrow<AssertionError> {
                        message{
                            toContainSubject(subject)
                            toContainDescr(DescriptionBigDecimalProof.IS_NUMERICALLY_EQUAL_TO, expected)
                        }
                    }
                }
                it("`$notToEqualNumerically` does not throw") {
                    expect(subject).notToEqualNumericallyFun(expected)
                }
            }
        }
    }

    describeFun(
        toEqualDontUse,
        toEqualNullableDontUse,
        toEqualIncludingScale,
        notToEqual,
        notToEqualIncludingScale
    ) {
        val toEqualFun = toEqualDontUse.lambda
        val toEqualNullableFun = toEqualNullableDontUse.lambda
        val toEqualIncludingScaleFun = toEqualIncludingScale.lambda
        val notToEqualFun = notToEqual.lambda
        val notToEqualIncludingScaleFun = notToEqualIncludingScale.lambda

        //TODO 1.3.0 check this most likely changed and hence is not found (i.e. false positive)
        val failureHintNotNumerically = String.format(
            DescriptionBigDecimalProof.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.string,
            notToEqualNumerically.name
        )
        context("subject is 10 and expected is 10") {
            val expected = BigDecimal("10")
            it("${toEqualDontUse.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).toEqualFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }
            it("${toEqualDontUse.name} with BigDecimal? overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    @Suppress("USELESS_CAST")
                    expect(BigDecimal.TEN as BigDecimal?).toEqualNullableFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }
            it("${notToEqual.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).notToEqualFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }

            it("${toEqualDontUse.name} with Any overload does not throw") {
                expect(BigDecimal.TEN as Any).toEqualAnyFun(expected)
            }
            it("$toEqualIncludingScale does not throw") {
                expect(BigDecimal.TEN as BigDecimal).toEqualIncludingScaleFun(expected)
            }

            it("${notToEqual.name} with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    expect(BigDecimal.TEN as Any).notToEqualAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject(BigDecimal.TEN)
                        toContainDescr(DescriptionAnyProof.NOT_TO_EQUAL, expected)
                        notToContain(failureHintNotNumerically)
                    }
                }
            }
            it("${notToEqualIncludingScale.name} throws an AssertionError and does not contain the hint") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).notToEqualIncludingScaleFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject(
                            BigDecimal.TEN)
                        toContainDescr(DescriptionBigDecimalProof.IS_NOT_EQUAL_INCLUDING_SCALE, expected)
                        notToContain(failureHintNotNumerically)
                    }
                }
            }
        }

        val failureHintNumerically = String.format(
            DescriptionBigDecimalProof.FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL.string,
            toEqualNumerically.name
        )
        listOf(
            BigDecimal("10.0"),
            BigDecimal("10.00")
        ).forEach { expected ->
            context("subject is 10 and expected is $expected") {
                it("${toEqualDontUse.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        expect(BigDecimal.TEN as BigDecimal).toEqualFun(expected)
                    }.toThrow<PleaseUseReplacementException>()
                }
                it("${notToEqual.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                    expect {
                        expect(BigDecimal.TEN as BigDecimal).notToEqualFun(expected)
                    }.toThrow<PleaseUseReplacementException>()
                }

                it("${toEqualDontUse.name} with Any overload throws an AssertionError and does not contain the hint") {
                    expect {
                        expect(BigDecimal.TEN as Any).toEqualAnyFun(expected)
                    }.toThrow<AssertionError> {
                        message {
                            toContainSubject(BigDecimal.TEN)
                            toContainToEqualDescr(expected)
                            notToContain(failureHintNumerically)
                        }
                    }
                }
                it("${toEqualIncludingScale.name} throws an AssertionError mentioning that ${toEqualNumerically.name} could have been used") {
                    expect {
                        expect(BigDecimal.TEN as BigDecimal).toEqualIncludingScaleFun(expected)
                    }.toThrow<AssertionError> {
                        message{
                            toContainSubject(BigDecimal.TEN)
                            toContainDescr(DescriptionBigDecimalProof.IS_EQUAL_INCLUDING_SCALE, expected)
                            toContain( "${u}$failureHintNumerically")
                        }
                    }
                }

                it("${notToEqual.name} with Any overload does not throw") {
                    expect(BigDecimal.TEN as Any).notToEqualAnyFun(expected)
                }
                it("${toEqualIncludingScale.name} does not throw") {
                    expect(BigDecimal.TEN as BigDecimal).notToEqualIncludingScaleFun(expected)
                }
            }
        }

        context("subject is 10 and expected is 9") {
            val expected = BigDecimal("9.999999999999")
            it("${toEqualDontUse.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).toEqualFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }
            it("${notToEqual.name} with BigDecimal overload throws ${PleaseUseReplacementException::class.simpleName}") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).notToEqualFun(expected)
                }.toThrow<PleaseUseReplacementException>()
            }

            it("${toEqualDontUse.name} with Any overload throws an AssertionError and does not contain the hint") {
                expect {
                    expect(BigDecimal.TEN as Any).toEqualAnyFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject(BigDecimal.TEN)
                        toContainToEqualDescr(expected)
                        notToContain(failureHintNumerically)
                    }
                }
            }
            it("${toEqualIncludingScale.name} throws an AssertionError and does not contain the hint") {
                expect {
                    expect(BigDecimal.TEN as BigDecimal).toEqualIncludingScaleFun(expected)
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject(BigDecimal.TEN)
                        toContainDescr(DescriptionBigDecimalProof.IS_EQUAL_INCLUDING_SCALE, expected)
                        notToContain(failureHintNumerically)
                    }
                }
            }

            it("${notToEqual.name} with Any overload does not throw") {
                expect(BigDecimal.TEN as Any).notToEqualAnyFun(expected)
            }
            it("${toEqualIncludingScale.name} does not throw") {
                expect(BigDecimal.TEN as BigDecimal).notToEqualIncludingScaleFun(expected)
            }
        }
    }

    describeFun(toEqualNull) {
        val toEqualFun = toEqualNull.lambda
        it("does not throw if subject is null") {
            expect(null as BigDecimal?).toEqualFun(null)
        }
    }
})

