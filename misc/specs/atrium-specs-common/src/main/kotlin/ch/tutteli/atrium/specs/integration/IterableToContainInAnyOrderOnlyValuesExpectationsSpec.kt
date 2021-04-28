package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

abstract class IterableToContainInAnyOrderOnlyValuesExpectationsSpec(
    toContainInAnyOrderOnlyValues: Fun2<Iterable<Double>, Double, Array<out Double>>,
    toContainInAnyOrderOnlyNullableValues: Fun2<Iterable<Double?>, Double?, Array<out Double?>>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInAnyOrderOnlyValues.forSubjectLess(2.5, arrayOf())

    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInAnyOrderOnlyNullableValues.forSubjectLess(2.5, arrayOf())
    ) {})

    fun Expect<Iterable<Double?>>.toContainInAnyOrderNullableValuesFun(t: Double?, vararg tX: Double?) =
        toContainInAnyOrderOnlyNullableValues(this, t, tX)

    nonNullableCases(
        describePrefix,
        toContainInAnyOrderOnlyValues,
        toContainInAnyOrderOnlyNullableValues
    ) { toContainValuesFunArr ->

        fun Expect<Iterable<Double>>.toContainFun(t: Double, vararg tX: Double) =
            toContainValuesFunArr(t, tX.toTypedArray())

        context("empty collection") {
            it("1.0 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainInAnyOrderOnly:",
                            "$failingBulletPoint$anElementWhichIs: 1.0"
                        )
                        notToContain(additionalElements)
                        toContainSize(0, 1)
                    }
                }
            }
            it("1.0 and 4.0 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.0, 4.0)
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrderOnly:",
                            "$failingBulletPoint$anElementWhichIs: 1.0",
                            "$failingBulletPoint$anElementWhichIs: 4.0"
                        )
                        notToContain(additionalElements)
                        toContainSize(0, 2)
                    }
                }
            }
        }

        context("iterable ${oneToFour().toList()}") {

            context("happy cases") {
                listOf(
                    arrayOf(1.0, 2.0, 3.0, 4.0, 4.0),
                    arrayOf(1.0, 3.0, 2.0, 4.0, 4.0),
                    arrayOf(3.0, 4.0, 2.0, 1.0, 4.0),
                    arrayOf(2.0, 4.0, 4.0, 1.0, 3.0),
                    arrayOf(2.0, 4.0, 1.0, 4.0, 3.0),
                    arrayOf(4.0, 4.0, 3.0, 2.0, 1.0)
                ).forEach {
                    it(it.joinToString()) {
                        expect(oneToFour()).toContainFun(it.first(), *it.drop(1).toDoubleArray())
                    }
                }
            }

            context("error cases (throws AssertionError)") {

                context("additional entries") {
                    it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                        expect {
                            expect(oneToFour()).toContainFun(1.0, 2.0, 3.0, 4.0)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichIs: 1.0",
                                    "$successfulBulletPoint$anElementWhichIs: 2.0",
                                    "$successfulBulletPoint$anElementWhichIs: 3.0",
                                    "$successfulBulletPoint$anElementWhichIs: 4.0",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}4.0"
                                )
                                toContainSize(5, 4)
                            }
                        }
                    }

                    it("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                        expect {
                            expect(oneToFour()).toContainFun(1.0, 4.0)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichIs: 1.0",
                                    "$successfulBulletPoint$anElementWhichIs: 4.0",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}2.0",
                                    "${listBulletPoint}3.0",
                                    "${listBulletPoint}4.0"
                                )
                                toContainSize(5, 2)
                            }
                        }
                    }
                }

                context("mismatches") {
                    it("1.0, 2.0, 3.0, 4.0, 5.0") {
                        expect {
                            expect(oneToFour()).toContainFun(1.0, 2.0, 3.0, 4.0, 5.0)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichIs: 1.0",
                                    "$successfulBulletPoint$anElementWhichIs: 2.0",
                                    "$successfulBulletPoint$anElementWhichIs: 3.0",
                                    "$failingBulletPoint$anElementWhichIs: 5.0",
                                    "$warningBulletPoint$mismatches:",
                                    "${listBulletPoint}4.0"
                                )
                                notToContain(sizeDescr)
                            }
                        }
                    }
                }

                context("mismatches and additional entries") {
                    it("1.0, 3.0, 5.0 -- 5.0 is wrong and 2.0, 4.0 and 4.0 are missing") {
                        expect {
                            expect(oneToFour()).toContainFun(1.0, 3.0, 5.0)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichIs: 1.0",
                                    "$successfulBulletPoint$anElementWhichIs: 3.0",
                                    "$failingBulletPoint$anElementWhichIs: 5.0",
                                    "$warningBulletPoint$mismatchesAdditionalElements:",
                                    "${listBulletPoint}2.0"
                                )
                                toContain.exactly(2).value("${listBulletPoint}4.0")
                                toContainSize(5, 3)
                            }
                        }
                    }
                }

                context("too many matcher") {
                    it("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 was too much") {
                        expect {
                            expect(oneToFour()).toContainFun(1.0, 2.0, 3.0, 4.0, 4.0, 5.0)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichIs: 1.0",
                                    "$successfulBulletPoint$anElementWhichIs: 2.0",
                                    "$successfulBulletPoint$anElementWhichIs: 3.0",
                                    "$failingBulletPoint$anElementWhichIs: 5.0"
                                )
                                toContain.exactly(2).value("$successfulBulletPoint$anElementWhichIs: 4.0")
                                toContainSize(5, 6)
                                notToContain(additionalElements, mismatches, mismatchesAdditionalElements)
                            }
                        }
                    }
                }
            }
        }
    }


    nullableCases(describePrefix) {
        describeFun(toContainInAnyOrderOnlyValues) {

            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, 1.0, null, 3.0") {
                        expect(null1null3()).toContainInAnyOrderNullableValuesFun(null, 1.0, null, 3.0)
                    }
                    it("1.0, null, null, 3.0") {
                        expect(null1null3()).toContainInAnyOrderNullableValuesFun(1.0, null, null, 3.0)
                    }
                    it("1.0, null, 3.0, null") {
                        expect(null1null3()).toContainInAnyOrderNullableValuesFun(1.0, null, 3.0, null)
                    }
                    it("1.0, 3.0, null, null") {
                        expect(null1null3()).toContainInAnyOrderNullableValuesFun(1.0, 3.0, null, null)
                    }
                }

                context("failing cases") {
                    it("null, 1.0, 3.0 -- null was missing") {
                        expect {
                            expect(null1null3()).toContainInAnyOrderNullableValuesFun(null, 1.0, 3.0)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichIs: null",
                                    "$successfulBulletPoint$anElementWhichIs: 1.0",
                                    "$successfulBulletPoint$anElementWhichIs: 3.0",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}null"
                                )
                                toContainSize(4, 3)
                            }
                        }
                    }
                }
            }
        }
    }
})
