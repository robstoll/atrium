package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInAnyOrderOnlyValuesPair: Pair<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    containsInAnyOrderOnlyNullableValuesPair: Pair<String, Assert<Iterable<Double?>>.(Double?, Array<out Double?>) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsInAnyOrderOnlyValuesPair.first to mapToCreateAssertion { containsInAnyOrderOnlyValuesPair.second(this, 2.5, arrayOf()) },
        containsInAnyOrderOnlyNullableValuesPair.first to mapToCreateAssertion { containsInAnyOrderOnlyNullableValuesPair.second(this, 2.5, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsInAnyOrderOnlyValuesPair.first, { containsInAnyOrderOnlyValuesPair.second(this, 2.5, arrayOf()) }, listOf(2.5).asIterable(), listOf(2.5, 2.2)),
        checkingTriple(containsInAnyOrderOnlyNullableValuesPair.first, { containsInAnyOrderOnlyNullableValuesPair.second(this, 2.5, arrayOf()) }, listOf(2.5).asIterable(), listOf(2.5, 2.2))
    ) {})

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException

    val (containsInOrderNullableValues, containsInOrderNullableValuesFunArr) = containsInAnyOrderOnlyNullableValuesPair
    fun Assert<Iterable<Double?>>.containsInOrderNullableValuesFun(t: Double?, vararg tX: Double?)
        = containsInOrderNullableValuesFunArr(t, tX)

    val anEntryWhichIs = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.getDefault()


    nonNullableCases(describePrefix,
        containsInAnyOrderOnlyValuesPair,
        containsInAnyOrderOnlyNullableValuesPair
    ){ containsValuesFunArr ->

        fun Assert<Iterable<Double>>.containsFun(t: Double, vararg tX: Double) =
            containsValuesFunArr(t, tX.toTypedArray())

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            test("1.0 throws AssertionError") {
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
            test("1.0 and 4.0 throws AssertionError") {
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
            val fluent = assert(oneToFour)

            describe("happy cases") {
                listOf(
                    arrayOf(1.0, 2.0, 3.0, 4.0, 4.0),
                    arrayOf(1.0, 3.0, 2.0, 4.0, 4.0),
                    arrayOf(3.0, 4.0, 2.0, 1.0, 4.0),
                    arrayOf(2.0, 4.0, 4.0, 1.0, 3.0),
                    arrayOf(2.0, 4.0, 1.0, 4.0, 3.0),
                    arrayOf(4.0, 4.0, 3.0, 2.0, 1.0)
                ).forEach {
                    test(it.joinToString()) {
                        fluent.containsFun(it.first(), *it.drop(1).toDoubleArray())
                    }
                }
            }

            describe("error cases (throws AssertionError)") {

                context("additional entries") {
                    test("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
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

                    test("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
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
                    test("1.0, 2.0, 3.0, 4.0, 5.0") {
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
                    test("1.0, 3.0, 5.0 -- 5.0 is wrong and 2.0, 4.0 and 4.0 are missing") {
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
                    test("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 was too much") {
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
        describeFun(containsInOrderNullableValues) {

            val list = listOf(null, 1.0, null, 3.0)
            val fluent = verbs.checkImmediately(list)

            context("iterable $list") {
                context("happy cases (do not throw)") {
                    test("null, 1.0, null, 3.0") {
                        fluent.containsInOrderNullableValuesFun(null, 1.0, null, 3.0)
                    }
                    test("1.0, null, null, 3.0") {
                        fluent.containsInOrderNullableValuesFun(1.0, null, null, 3.0)
                    }
                    test("1.0, null, 3.0, null") {
                        fluent.containsInOrderNullableValuesFun(1.0, null, 3.0, null)
                    }
                    test("1.0, 3.0, null, null") {
                        fluent.containsInOrderNullableValuesFun(1.0, 3.0, null, null)
                    }
                }

                context("failing cases") {
                    test("null, 1.0, 3.0 -- null was missing") {
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
