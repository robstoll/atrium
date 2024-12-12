package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.*

abstract class IterableToContainInAnyOrderOnlyValuesExpectationsSpec(
    toContainInAnyOrderOnlyValues: Fun3<Iterable<Double>, Double, Array<out Double>, InAnyOrderOnlyReportingOptions.() -> Unit>,
    toContainInAnyOrderOnlyNullableValues: Fun3<Iterable<Double?>, Double?, Array<out Double?>, InAnyOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInAnyOrderOnlyValues.forSubjectLess(2.5, arrayOf(), emptyInAnyOrderOnlyReportOptions)

    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInAnyOrderOnlyNullableValues.forSubjectLess(2.5, arrayOf(), emptyInAnyOrderOnlyReportOptions)
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
            it("1.0 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        // TODO 1.3.0 we should be able to switch to the following, i.e. the elements should define an own level
//                        toContain(
//                            "$g$toContainInAnyOrderOnly :",
//                            "$x$anElementWhichEquals : 1.0"
//                        )
                        toContainRegex(
                            "\\Q$g\\E$toContainInAnyOrderOnly :",
                            "\\Q$x\\E$anElementWhichEquals\\s+: 1.0"
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
//                        toContain.exactly(1).values(
//                            "$g$toContainInAnyOrderOnly :",
//                            "$x$anElementWhichEquals : 1.0",
//                            "$x$anElementWhichEquals : 4.0"
//                        )
                        toContain.exactly(1).regex(
                            "\\Q$g\\E$toContainInAnyOrderOnly :",
                            "\\Q$x\\E$anElementWhichEquals\\s+: 1.0",
                            "\\Q$x\\E$anElementWhichEquals\\s+: 4.0"
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
                                    "$g$toContainInAnyOrderOnly :",
                                    "$s$anElementWhichEquals : 1.0",
                                    "$s$anElementWhichEquals : 2.0",
                                    "$s$anElementWhichEquals : 3.0",
                                    "$s$anElementWhichEquals : 4.0",
                                    "$bb$additionalElements :",
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
                                    "$g$toContainInAnyOrderOnly :",
                                    "$s$anElementWhichEquals : 1.0",
                                    "$s$anElementWhichEquals : 4.0",
                                    "$bb$additionalElements :",
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
                                    "$g$toContainInAnyOrderOnly :",
                                    "$s$anElementWhichEquals : 1.0",
                                    "$s$anElementWhichEquals : 2.0",
                                    "$s$anElementWhichEquals : 3.0",
                                    "$x$anElementWhichEquals : 5.0",
                                    "$bb$mismatches:",
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
                                    "$s$anElementWhichEquals: 1.0",
                                    "$s$anElementWhichEquals: 3.0",
                                    "$x$anElementWhichEquals: 5.0",
                                    "$bb$mismatchesAdditionalElements :",
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
                                    "$g$toContainInAnyOrderOnly :",
                                    "$s$anElementWhichEquals : 1.0",
                                    "$s$anElementWhichEquals : 2.0",
                                    "$s$anElementWhichEquals : 3.0",
                                    "$x$anElementWhichEquals : 5.0"
                                )
                                toContain.exactly(2).value("$s$anElementWhichEquals : 4.0")
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
                        expect(oneToFour()).toContainFun(2.0, report = { showOnlyFailing() })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 1)
                            toContain.exactly(1).values(
                                "$g$toContainInAnyOrderOnly :",
                                "$bb$additionalElements :",
                                "${listBulletPoint}1.0",
                                "${listBulletPoint}3.0"
                            )
                            toContain.exactly(2).value("${listBulletPoint}4.0")
                            notToContain("$anElementWhichEquals : 2.0")

                        }
                    }
                }
                it("shows only failing with report option `showOnlyFailingIfMoreExpectedElementsThan(3)` because there are 5") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            1.0,
                            2.0,
                            3.0,
                            4.0,
                            4.0,
                            5.0,
                            report = { showOnlyFailingIfMoreExpectedElementsThan(3) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            toContain.exactly(1).values("$listBulletPoint$anElementWhichEquals : 5.0")
                            notToContain(
                                "$anElementWhichEquals : 1.0",
                                "$anElementWhichEquals : 2.0",
                                "$anElementWhichEquals : 3.0",
                                "$anElementWhichEquals : 4.0"
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
                            1.0,
                            2.0,
                            3.0,
                            4.0,
                            -1.0,
                            6.0,
                            7.0,
                            -2.0,
                            9.0,
                            10.0,
                            11.0
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$listBulletPoint$anElementWhichEquals : -1.0",
                                "$listBulletPoint$anElementWhichEquals : -2.0",
                                "$bb$mismatches:",
                                "${listBulletPoint}5.0",
                                "${listBulletPoint}8.0"
                            )
                            notToContain(
                                "$anElementWhichEquals : 1.0",
                                "$anElementWhichEquals : 2.0",
                                "$anElementWhichEquals : 3.0",
                                "$anElementWhichEquals : 4.0",
                                "$anElementWhichEquals : 6.0",
                                "$anElementWhichEquals : 7.0",
                                "$anElementWhichEquals : 9.0",
                                "$anElementWhichEquals : 10.0",
                                "$anElementWhichEquals : 11.0",

                                additionalElements, mismatchesAdditionalElements
                            )
                        }
                    }
                }
                it("shows all with report option `showAlwaysSummary`") {
                    expect {
                        expect(oneToEleven).toContainFun(
                            1.0,
                            2.0,
                            3.0,
                            4.0,
                            -1.0,
                            6.0,
                            7.0,
                            -2.0,
                            9.0,
                            10.0,
                            11.0,
                            report = { showAlwaysSummary() }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$s$anElementWhichEquals : 1.0",
                                "$s$anElementWhichEquals : 2.0",
                                "$s$anElementWhichEquals : 3.0",
                                "$s$anElementWhichEquals : 4.0",
                                "$x$anElementWhichEquals : -1.0",
                                "$s$anElementWhichEquals : 6.0",
                                "$s$anElementWhichEquals : 7.0",
                                "$x$anElementWhichEquals : -2.0",
                                "$s$anElementWhichEquals : 9.0",
                                "$s$anElementWhichEquals : 10.0",
                                "$s$anElementWhichEquals : 11.0",
                                "$bb$mismatches:",
                                "${listBulletPoint}5.0",
                                "${listBulletPoint}8.0"
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

            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, 1.0, null, 3.0") {
                        expect(null1null3()).toContainFun(null, 1.0, null, 3.0)
                    }
                    it("1.0, null, null, 3.0") {
                        expect(null1null3()).toContainFun(1.0, null, null, 3.0)
                    }
                    it("1.0, null, 3.0, null") {
                        expect(null1null3()).toContainFun(1.0, null, 3.0, null)
                    }
                    it("1.0, 3.0, null, null") {
                        expect(null1null3()).toContainFun(1.0, 3.0, null, null)
                    }
                }

                context("failing cases") {
                    it("null, 1.0, 3.0 -- null was missing") {
                        expect {
                            expect(null1null3()).toContainFun(null, 1.0, 3.0)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$g$toContainInAnyOrderOnly :",
                                    "$s$anElementWhichEquals : null",
                                    "$s$anElementWhichEquals : 1.0",
                                    "$s$anElementWhichEquals : 3.0",
                                    "$bb$additionalElements :",
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
