package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.additionalElements
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.anElementWhichEquals
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyIterable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.mismatches
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.mismatchesAdditionalElements
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToEleven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToFour
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.sizeDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainInAnyOrderOnly
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainSize
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractIterableToContainInAnyOrderOnlyValuesExpectationsTest(
    private val toContainInAnyOrderOnlyValuesSpec: Fun3<Iterable<Double>, Double, Array<out Double>, InAnyOrderOnlyReportingOptions.() -> Unit>,
    private val toContainInAnyOrderOnlyNullableValuesSpec: Fun3<Iterable<Double?>, Double?, Array<out Double?>, InAnyOrderOnlyReportingOptions.() -> Unit>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toContainInAnyOrderOnlyValuesSpec.forSubjectLessTest(
                2.5, arrayOf(), emptyInAnyOrderOnlyReportOptions
            )

        ), SubjectLessTestData(
            toContainInAnyOrderOnlyNullableValuesSpec.forSubjectLessTest(
                2.5, arrayOf(), emptyInAnyOrderOnlyReportOptions
            )
        )
    )


    @TestFactory
    fun empty_collection() = testFactoryNonNullable(
        toContainInAnyOrderOnlyValuesSpec, toContainInAnyOrderOnlyNullableValuesSpec
    ) { toContainInAnyOrderOnlyValuesFunArr ->

        fun Expect<Iterable<Double>>.toContainInAnyOrderOnlyValuesFun(
            t: Double,
            vararg tX: Double,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInAnyOrderOnlyValuesFunArr(t, tX.toTypedArray(), report)

        it("1.0 throws AssertionError") {
            expect {
                expect(emptyIterable()).toContainInAnyOrderOnlyValuesFun(1.1)
            }.toThrow<AssertionError> {
                message {
                    toContain(
                        "$rootBulletPoint$toContainInAnyOrderOnly:", "$failingBulletPoint$anElementWhichEquals: 1.1"
                    )
                    notToContain(additionalElements)
                    toContainSize(0, 1)
                }
            }
        }

        it("1.0 and 4.0 throws AssertionError") {
            expect {
                expect(emptyIterable()).toContainInAnyOrderOnlyValuesFun(1.1, 4.1)
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

    @TestFactory
    fun one_to_four__different_order__1_to_4__works() = testFactoryNonNullable(
        toContainInAnyOrderOnlyValuesSpec, toContainInAnyOrderOnlyNullableValuesSpec
    ) { toContainInAnyOrderOnlyValuesFunArr ->
        listOf(
            arrayOf(1.1, 2.1, 3.1, 4.1, 4.1),
            arrayOf(1.1, 3.1, 2.1, 4.1, 4.1),
            arrayOf(3.1, 4.1, 2.1, 1.1, 4.1),
            arrayOf(2.1, 4.1, 4.1, 1.1, 3.1),
            arrayOf(2.1, 4.1, 1.1, 4.1, 3.1),
            arrayOf(4.1, 4.1, 3.1, 2.1, 1.1)
        ).forEach {
            it(it.joinToString()) {
                expect(oneToFour()).toContainInAnyOrderOnlyValuesFunArr(
                    it.first(), it.drop(1).toTypedArray(), emptyInAnyOrderOnlyReportOptions
                )
            }
        }
    }

    @TestFactory
    fun additional_elements_and_mismatches() = testFactoryNonNullable(
        toContainInAnyOrderOnlyValuesSpec, toContainInAnyOrderOnlyNullableValuesSpec
    ) { toContainInAnyOrderOnlyValuesFunArr ->
        fun Expect<Iterable<Double>>.toContainInAnyOrderOnlyValuesFun(
            t: Double,
            vararg tX: Double,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInAnyOrderOnlyValuesFunArr(t, tX.toTypedArray(), report)

        it("1.1, 2.1, 3.1, 4.1 -- 4.1 was missing") {
            expect {
                expect(oneToFour()).toContainInAnyOrderOnlyValuesFun(1.1, 2.1, 3.1, 4.1)
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
                expect(oneToFour()).toContainInAnyOrderOnlyValuesFun(1.1, 4.1)
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

        it("1.1, 2.1, 3.1, 4.1, 5.1") {
            expect {
                expect(oneToFour()).toContainInAnyOrderOnlyValuesFun(1.1, 2.1, 3.1, 4.1, 5.1)
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

        it("1.1, 3.1, 5.1 -- 5.1 is wrong and 2.1, 4.1 and 4.1 are missing") {
            expect {
                expect(oneToFour()).toContainInAnyOrderOnlyValuesFun(1.1, 3.1, 5.1)
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

        it("1.1, 2.1, 3.1, 4.1, 4.1, 5.1 -- 5.1 was too much") {
            expect {
                expect(oneToFour()).toContainInAnyOrderOnlyValuesFun(1.1, 2.1, 3.1, 4.1, 4.1, 5.1)
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

    @TestFactory
    fun report_options() = testFactoryNonNullable(
        toContainInAnyOrderOnlyValuesSpec, toContainInAnyOrderOnlyNullableValuesSpec
    ) { toContainInAnyOrderOnlyValuesFunArr ->
        fun Expect<Iterable<Double>>.toContainInAnyOrderOnlyValuesFun(
            t: Double,
            vararg tX: Double,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInAnyOrderOnlyValuesFunArr(t, tX.toTypedArray(), report)

        it("shows only failing with report option `showOnlyFailing`") {
            expect {
                expect(oneToFour()).toContainInAnyOrderOnlyValuesFun(2.1, report = { showOnlyFailing() })
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
                expect(oneToFour()).toContainInAnyOrderOnlyValuesFun(
                    1.1, 2.1, 3.1, 4.1, 4.1, 5.1,
                    report = { showOnlyFailingIfMoreExpectedElementsThan(3) }
                )
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

        it("shows only failing per default as there are more than 10 expected elements") {
            expect {
                expect(oneToEleven).toContainInAnyOrderOnlyValuesFun(
                    1.1, 2.1, 3.1, 4.1, -1.1, 6.1, 7.1, -2.1, 9.1, 10.1, 11.1,
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

                        additionalElements,
                        mismatchesAdditionalElements
                    )
                }
            }
        }

        it("shows all with report option `showAlwaysSummary`") {
            expect {
                expect(oneToEleven).toContainInAnyOrderOnlyValuesFun(
                    1.1, 2.1, 3.1, 4.1, -1.1, 6.1, 7.1, -2.1, 9.1, 10.1, 11.1,
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

    private val null1null3 = { sequenceOf(null, 1.1, null, 3.1).constrainOnce().asIterable() }

    @TestFactory
    fun nullable() = testFactory(toContainInAnyOrderOnlyNullableValuesSpec) { toContainInAnyOrderOnlyValuesFunArr ->
        fun Expect<Iterable<Double?>>.toContainInAnyOrderOnlyValuesFun(
            t: Double?,
            vararg tX: Double?,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInAnyOrderOnlyValuesFunArr(this, t, tX, report)

        describeIterable(::null1null3) {
            it("happy cases") {
                expect(null1null3()).toContainInAnyOrderOnlyValuesFun(null, 1.1, null, 3.1)
                expect(null1null3()).toContainInAnyOrderOnlyValuesFun(1.1, null, null, 3.1)
                expect(null1null3()).toContainInAnyOrderOnlyValuesFun(1.1, null, 3.1, null)
                expect(null1null3()).toContainInAnyOrderOnlyValuesFun(1.1, 3.1, null, null)
            }

            it("null, 1.0, 3.0 -- null was missing") {
                expect {
                    expect(null1null3()).toContainInAnyOrderOnlyValuesFun(null, 1.1, 3.1)
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
