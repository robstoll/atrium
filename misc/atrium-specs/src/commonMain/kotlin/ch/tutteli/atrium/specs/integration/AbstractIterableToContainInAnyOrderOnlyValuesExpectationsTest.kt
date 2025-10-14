package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.*

abstract class AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest(
    toContainInAnyOrderOnlyValues: Fun3<Iterable<Double>, Double, Array<out Double>, InAnyOrderOnlyReportingOptions.() -> Unit>,
    toContainInAnyOrderOnlyNullableValues: Fun3<Iterable<Double?>, Double?, Array<out Double?>, InAnyOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInAnyOrderOnlyValues.forSubjectLessTest(2.5, arrayOf(), emptyInAnyOrderOnlyReportOptions)

    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInAnyOrderOnlyNullableValues.forSubjectLessTest(2.5, arrayOf(), emptyInAnyOrderOnlyReportOptions)
    ) {})

    nonNullableCases(
        describePrefix,
        toContainInAnyOrderOnlyValues,
        toContainInAnyOrderOnlyNullableValues
    ) { toContainValuesFunArr ->

        fun Expect<Iterable<Double>>.toContainFun(
            t: Double,
            vararg tX: Double,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainValuesFunArr(t, tX.toTypedArray(), report)

        context("empty collection") {
            it("1.1 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.1)
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainInAnyOrderOnly:",
                            "$failingBulletPoint$anElementWhichEquals: 1.1"
                        )
                        notToContain(additionalElements)
                        toContainSize(0, 1)
                    }
                }
            }
            it("1.1 and 4.1 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.1, 4.1)
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrderOnly:",
                            "$failingBulletPoint$anElementWhichEquals: 1.1",
                            "$failingBulletPoint$anElementWhichEquals: 4.1"
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
                    arrayOf(1.1, 2.1, 3.1, 4.1, 4.1),
                    arrayOf(1.1, 3.1, 2.1, 4.1, 4.1),
                    arrayOf(3.1, 4.1, 2.1, 1.1, 4.1),
                    arrayOf(2.1, 4.1, 4.1, 1.1, 3.1),
                    arrayOf(2.1, 4.1, 1.1, 4.1, 3.1),
                    arrayOf(4.1, 4.1, 3.1, 2.1, 1.1)
                ).forEach {
                    it(it.joinToString()) {
                        expect(oneToFour()).toContainFun(it.first(), *it.drop(1).toDoubleArray())
                    }
                }
            }

            context("error cases (throws AssertionError)") {

                context("additional entries") {
                    it("1.1, 2.1, 3.1, 4.1 -- 4.1 was missing") {
                        expect {
                            expect(oneToFour()).toContainFun(1.1, 2.1, 3.1, 4.1)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichEquals: 1.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 2.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 3.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 4.1",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}4.1"
                                )
                                toContainSize(5, 4)
                            }
                        }
                    }

                    it("1.1, 4.1 -- 2.1, 3.1 and 4.1 was missing") {
                        expect {
                            expect(oneToFour()).toContainFun(1.1, 4.1)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichEquals: 1.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 4.1",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}2.1",
                                    "${listBulletPoint}3.1",
                                    "${listBulletPoint}4.1"
                                )
                                toContainSize(5, 2)
                            }
                        }
                    }
                }

                context("mismatches") {
                    it("1.1, 2.1, 3.1, 4.1, 5.1") {
                        expect {
                            expect(oneToFour()).toContainFun(1.1, 2.1, 3.1, 4.1, 5.1)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichEquals: 1.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 2.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 3.1",
                                    "$failingBulletPoint$anElementWhichEquals: 5.1",
                                    "$warningBulletPoint$mismatches:",
                                    "${listBulletPoint}4.1"
                                )
                                notToContain(sizeDescr)
                            }
                        }
                    }
                }

                context("mismatches and additional entries") {
                    it("1.1, 3.1, 5.1 -- 5.1 is wrong and 2.1, 4.1 and 4.1 are missing") {
                        expect {
                            expect(oneToFour()).toContainFun(1.1, 3.1, 5.1)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichEquals: 1.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 3.1",
                                    "$failingBulletPoint$anElementWhichEquals: 5.1",
                                    "$warningBulletPoint$mismatchesAdditionalElements:",
                                    "${listBulletPoint}2.1"
                                )
                                toContain.exactly(2).value("${listBulletPoint}4.1")
                                toContainSize(5, 3)
                            }
                        }
                    }
                }

                context("too many matcher") {
                    it("1.1, 2.1, 3.1, 4.1, 4.1, 5.1 -- 5.1 was too much") {
                        expect {
                            expect(oneToFour()).toContainFun(1.1, 2.1, 3.1, 4.1, 4.1, 5.1)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichEquals: 1.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 2.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 3.1",
                                    "$failingBulletPoint$anElementWhichEquals: 5.1"
                                )
                                toContain.exactly(2).value("$successfulBulletPoint$anElementWhichEquals: 4.1")
                                toContainSize(5, 6)
                                notToContain(additionalElements, mismatches, mismatchesAdditionalElements)
                            }
                        }
                    }
                }
            }
        }

        context("report options") {
            context("iterable ${oneToFour().toList()}") {
                it("shows only failing with report option `showOnlyFailing`") {
                    expect {
                        expect(oneToFour()).toContainFun(2.1, report = { showOnlyFailing() })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 1)
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrderOnly:",
                                "$warningBulletPoint$additionalElements:",
                                "${listBulletPoint}1.1",
                                "${listBulletPoint}3.1"
                            )
                            toContain.exactly(2).value("${listBulletPoint}4.1")
                            notToContain("$anElementWhichEquals: 2.1")

                        }
                    }
                }
                it("shows only failing with report option `showOnlyFailingIfMoreExpectedElementsThan(3)` because there are 5") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            1.1,
                            2.1,
                            3.1,
                            4.1,
                            4.1,
                            5.1,
                            report = { showOnlyFailingIfMoreExpectedElementsThan(3) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            toContain.exactly(1).values("$listBulletPoint$anElementWhichEquals: 5.1")
                            notToContain(
                                "$anElementWhichEquals: 1.1",
                                "$anElementWhichEquals: 2.1",
                                "$anElementWhichEquals: 3.1",
                                "$anElementWhichEquals: 4.1"
                            )
                            notToContain(additionalElements, mismatches, mismatchesAdditionalElements)
                        }
                    }
                }

            }

            context("iterable $oneToEleven") {
                it("shows only failing per default as there are more than 10 expected elements") {
                    expect {
                        expect(oneToEleven).toContainFun(
                            1.1,
                            2.1,
                            3.1,
                            4.1,
                            -1.1,
                            6.1,
                            7.1,
                            -2.1,
                            9.1,
                            10.1,
                            11.1
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$listBulletPoint$anElementWhichEquals: -1.1",
                                "$listBulletPoint$anElementWhichEquals: -2.1",
                                "$warningBulletPoint$mismatches:",
                                "${listBulletPoint}5.1",
                                "${listBulletPoint}8.1"
                            )
                            notToContain(
                                "$anElementWhichEquals: 1.1",
                                "$anElementWhichEquals: 2.1",
                                "$anElementWhichEquals: 3.1",
                                "$anElementWhichEquals: 4.1",
                                "$anElementWhichEquals: 6.1",
                                "$anElementWhichEquals: 7.1",
                                "$anElementWhichEquals: 9.1",
                                "$anElementWhichEquals: 10.1",
                                "$anElementWhichEquals: 11.1",

                                additionalElements, mismatchesAdditionalElements
                            )
                        }
                    }
                }
                it("shows all with report option `showAlwaysSummary`") {
                    expect {
                        expect(oneToEleven).toContainFun(
                            1.1,
                            2.1,
                            3.1,
                            4.1,
                            -1.1,
                            6.1,
                            7.1,
                            -2.1,
                            9.1,
                            10.1,
                            11.1,
                            report = { showAlwaysSummary() }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$successfulBulletPoint$anElementWhichEquals: 1.1",
                                "$successfulBulletPoint$anElementWhichEquals: 2.1",
                                "$successfulBulletPoint$anElementWhichEquals: 3.1",
                                "$successfulBulletPoint$anElementWhichEquals: 4.1",
                                "$failingBulletPoint$anElementWhichEquals: -1.1",
                                "$successfulBulletPoint$anElementWhichEquals: 6.1",
                                "$successfulBulletPoint$anElementWhichEquals: 7.1",
                                "$failingBulletPoint$anElementWhichEquals: -2.1",
                                "$successfulBulletPoint$anElementWhichEquals: 9.1",
                                "$successfulBulletPoint$anElementWhichEquals: 10.1",
                                "$successfulBulletPoint$anElementWhichEquals: 11.1",
                                "$warningBulletPoint$mismatches:",
                                "${listBulletPoint}5.1",
                                "${listBulletPoint}8.1"
                            )
                            notToContain(additionalElements, mismatchesAdditionalElements)
                        }
                    }
                }
            }
        }
    }


    nullableCases(describePrefix) {


        fun Expect<Iterable<Double?>>.toContainFun(
            t: Double?,
            vararg tX: Double?,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInAnyOrderOnlyNullableValues(this, t, tX, report)


        describeFun(toContainInAnyOrderOnlyValues) {

            val null1null3 = { sequenceOf(null, 1.1, null, 3.1).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, 1.1, null, 3.1") {
                        expect(null1null3()).toContainFun(null, 1.1, null, 3.1)
                    }
                    it("1.1, null, null, 3.1") {
                        expect(null1null3()).toContainFun(1.1, null, null, 3.1)
                    }
                    it("1.1, null, 3.1, null") {
                        expect(null1null3()).toContainFun(1.1, null, 3.1, null)
                    }
                    it("1.1, 3.1, null, null") {
                        expect(null1null3()).toContainFun(1.1, 3.1, null, null)
                    }
                }

                context("failing cases") {
                    it("null, 1.1, 3.1 -- null was missing") {
                        expect {
                            expect(null1null3()).toContainFun(null, 1.1, 3.1)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anElementWhichEquals: null",
                                    "$successfulBulletPoint$anElementWhichEquals: 1.1",
                                    "$successfulBulletPoint$anElementWhichEquals: 3.1",
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
