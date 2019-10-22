package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun2
import ch.tutteli.atrium.specs.SubjectLessSpec
import ch.tutteli.atrium.specs.forSubjectLess

abstract class IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
    containsInAnyOrderOnlyValues: Fun2<Iterable<Double>, Double, Array<out Double>>,
    containsInAnyOrderOnlyNullableValues: Fun2<Iterable<Double?>, Double?, Array<out Double?>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsInAnyOrderOnlyValues.forSubjectLess(2.5, arrayOf())

    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        containsInAnyOrderOnlyNullableValues.forSubjectLess(2.5, arrayOf())
    ) {})

    val (containsInOrderNullableValues, containsInOrderNullableValuesFunArr) = containsInAnyOrderOnlyNullableValues
    fun Expect<Iterable<Double?>>.containsInOrderNullableValuesFun(t: Double?, vararg tX: Double?) =
        containsInOrderNullableValuesFunArr(t, tX)

    nonNullableCases(
        describePrefix,
        containsInAnyOrderOnlyValues,
        containsInAnyOrderOnlyNullableValues
    ) { containsValuesFunArr ->

        fun Expect<Iterable<Double>>.containsFun(t: Double, vararg tX: Double) =
            containsValuesFunArr(t, tX.toTypedArray())

        context("empty collection") {
            it("1.0 throws AssertionError") {
                expect {
                    fluentEmpty.containsFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$rootBulletPoint$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryWhichIs: 1.0"
                        )
                        containsNot(additionalEntries)
                        containsSize(0, 1)
                    }
                }
            }
            it("1.0 and 4.0 throws AssertionError") {
                expect {
                    fluentEmpty.containsFun(1.0, 4.0)
                }.toThrow<AssertionError> {
                    message {
                        contains.exactly(1).values(
                            "$rootBulletPoint$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryWhichIs: 1.0",
                            "$failingBulletPoint$anEntryWhichIs: 4.0"
                        )
                        containsNot(additionalEntries)
                        containsSize(0, 2)
                    }
                }
            }
        }

        context("iterable $oneToFour") {
            val fluent = expect(oneToFour)

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
                        fluent.containsFun(it.first(), *it.drop(1).toDoubleArray())
                    }
                }
            }

            context("error cases (throws AssertionError)") {

                context("additional entries") {
                    it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                        expect {
                            fluent.containsFun(1.0, 2.0, 3.0, 4.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 2.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 4.0",
                                    "$warningBulletPoint$additionalEntries:",
                                    "${listBulletPoint}4.0"
                                )
                                containsSize(5, 4)
                            }
                        }
                    }

                    it("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                        expect {
                            fluent.containsFun(1.0, 4.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 4.0",
                                    "$warningBulletPoint$additionalEntries:",
                                    "${listBulletPoint}2.0",
                                    "${listBulletPoint}3.0",
                                    "${listBulletPoint}4.0"
                                )
                                containsSize(5, 2)
                            }
                        }
                    }
                }

                context("mismatches") {
                    it("1.0, 2.0, 3.0, 4.0, 5.0") {
                        expect {
                            fluent.containsFun(1.0, 2.0, 3.0, 4.0, 5.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 2.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                                    "$failingBulletPoint$anEntryWhichIs: 5.0",
                                    "$warningBulletPoint$mismatches:",
                                    "${listBulletPoint}4.0"
                                )
                                containsSize(5, 5)
                            }
                        }
                    }
                }

                context("mismatches and additional entries") {
                    it("1.0, 3.0, 5.0 -- 5.0 is wrong and 2.0, 4.0 and 4.0 are missing") {
                        expect {
                            fluent.containsFun(1.0, 3.0, 5.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                                    "$failingBulletPoint$anEntryWhichIs: 5.0",
                                    "$warningBulletPoint$mismatchesAdditionalEntries:",
                                    "${listBulletPoint}2.0"
                                )
                                contains.exactly(2).value("${listBulletPoint}4.0")
                                containsSize(5, 3)
                            }
                        }
                    }
                }

                context("too many matcher") {
                    it("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 was too much") {
                        expect {
                            fluent.containsFun(1.0, 2.0, 3.0, 4.0, 4.0, 5.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 2.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                                    "$failingBulletPoint$anEntryWhichIs: 5.0"
                                )
                                contains.exactly(2).value("$successfulBulletPoint$anEntryWhichIs: 4.0")
                                containsSize(5, 6)
                                containsNot(additionalEntries, mismatches, mismatchesAdditionalEntries)
                            }
                        }
                    }
                }
            }
        }
    }


    nullableCases(describePrefix) {
        describeFun("$containsInOrderNullableValues for nullable") {

            val list = listOf(null, 1.0, null, 3.0).asIterable()
            val fluent = expect(list)

            context("iterable $list") {
                context("happy cases (do not throw)") {
                    it("null, 1.0, null, 3.0") {
                        fluent.containsInOrderNullableValuesFun(null, 1.0, null, 3.0)
                    }
                    it("1.0, null, null, 3.0") {
                        fluent.containsInOrderNullableValuesFun(1.0, null, null, 3.0)
                    }
                    it("1.0, null, 3.0, null") {
                        fluent.containsInOrderNullableValuesFun(1.0, null, 3.0, null)
                    }
                    it("1.0, 3.0, null, null") {
                        fluent.containsInOrderNullableValuesFun(1.0, 3.0, null, null)
                    }
                }

                context("failing cases") {
                    it("null, 1.0, 3.0 -- null was missing") {
                        expect {
                            fluent.containsInOrderNullableValuesFun(null, 1.0, 3.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: null",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                                    "$warningBulletPoint$additionalEntries:",
                                    "${listBulletPoint}null"
                                )
                                containsSize(4, 3)
                            }
                        }
                    }
                }
            }
        }
    }
})
