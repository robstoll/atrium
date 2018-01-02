package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS_NOT
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsNullSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, AssertionPlant<Iterable<Double?>>.(Double?, Array<out Double?>) -> AssertionPlant<Iterable<Double?>>>,
    containsNotPair: Pair<String, AssertionPlant<Iterable<Double?>>.(Double?, Array<out Double?>) -> AssertionPlant<Iterable<Double?>>>,
    containsInAnyOrderNullableEntriesPair: Pair<String, AssertionPlant<Iterable<Double?>>.((AssertionPlant<Double>.() -> Unit)?, Array<out (AssertionPlant<Double>.() -> Unit)?>) -> AssertionPlant<Iterable<Double?>>>,
    containsInAnyOrderOnlyNullableEntriesPair: Pair<String, AssertionPlant<Iterable<Double?>>.((AssertionPlant<Double>.() -> Unit)?, Array<out (AssertionPlant<Double>.() -> Unit)?>) -> AssertionPlant<Iterable<Double?>>>,
    containsInOrderOnlyNullableEntriesPair: Pair<String, AssertionPlant<Iterable<Double?>>.((AssertionPlant<Double>.() -> Unit)?, Array<out (AssertionPlant<Double>.() -> Unit)?>) -> AssertionPlant<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double?>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, null, arrayOf()) },
        containsNotPair.first to mapToCreateAssertion { containsNotPair.second(this, null, arrayOf()) },
        containsInAnyOrderNullableEntriesPair.first to mapToCreateAssertion { containsInAnyOrderNullableEntriesPair.second(this, null, arrayOf()) },
        containsInAnyOrderOnlyNullableEntriesPair.first to mapToCreateAssertion { containsInAnyOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) },
        containsInOrderOnlyNullableEntriesPair.first to mapToCreateAssertion { containsInOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Iterable<Double?>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2)),
        checkingTriple(containsNotPair.first, { containsNotPair.second(this, null, arrayOf()) }, listOf(1.2) as Iterable<Double?>, listOf(null)),
        checkingTriple(containsInAnyOrderNullableEntriesPair.first, { containsInAnyOrderNullableEntriesPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2)),
        checkingTriple(containsInAnyOrderOnlyNullableEntriesPair.first, { containsInAnyOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2)),
        checkingTriple(containsInOrderOnlyNullableEntriesPair.first, { containsInOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double?>) -> AssertionPlant<Iterable<Double?>> = verbs::checkImmediately
    val expect = verbs::checkException
    val list = listOf(null, 1.0, null, 3.0)
    val fluent = assert(list)

    val (containsNullable, containsFunArr) = containsPair
    fun AssertionPlant<Iterable<Double?>>.containsFun(t: Double?, vararg tX: Double?)
        = containsFunArr(t, tX)

    val (containsNot, containsNotFunArr) = containsNotPair
    fun AssertionPlant<Iterable<Double?>>.containsNotFun(t: Double?, vararg tX: Double?)
        = containsNotFunArr(t, tX)

    val (containsInAnyOrderNullableEntries, containsInAnyOrderNullableEntriesArr) = containsInAnyOrderNullableEntriesPair
    fun AssertionPlant<Iterable<Double?>>.containsInAnyOrderNullableEntriesFun(t: (AssertionPlant<Double>.() -> Unit)?, vararg tX: (AssertionPlant<Double>.() -> Unit)?)
        = containsInAnyOrderNullableEntriesArr(t, tX)

    val (containsInAnyOrderOnlyNullableEntries, containsInAnyOrderOnlyNullableEntriesArr) = containsInAnyOrderOnlyNullableEntriesPair
    fun AssertionPlant<Iterable<Double?>>.containsInAnyOrderOnlyNullableEntriesFun(t: (AssertionPlant<Double>.() -> Unit)?, vararg tX: (AssertionPlant<Double>.() -> Unit)?)
        = containsInAnyOrderOnlyNullableEntriesArr(t, tX)

    val (containsInOrderOnlyNullableEntries, containsInOrderOnlyNullableEntriesArr) = containsInOrderOnlyNullableEntriesPair
    fun AssertionPlant<Iterable<Double?>>.containsInOrderOnlyNullableEntriesFun(t: (AssertionPlant<Double>.() -> Unit)?, vararg tX: (AssertionPlant<Double>.() -> Unit)?)
        = containsInOrderOnlyNullableEntriesArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)

    val anEntryAfterSuccess = "$anEntryWhich: $separator$indentBulletPoint$indentSuccessfulBulletPoint$listBulletPoint"
    val anEntryAfterFailing = "$anEntryWhich: $separator$indentBulletPoint$indentFailingBulletPoint$listBulletPoint"

    val entryWithIndex = DescriptionIterableAssertion.ENTRY_WITH_INDEX.getDefault()
    val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()
    val listBulletPointWithIndent = "$indentFeatureArrow$indentFeatureBulletPoint$listBulletPoint"

    val entryWhichWithFeature = "$indentFeatureArrow$featureBulletPoint$anEntryWhich"
    val anEntryWithFeatureAfterSuccess = "$entryWhichWithFeature: $separator$indentBulletPoint$indentSuccessfulBulletPoint$listBulletPointWithIndent"
    val anEntryWithFeatureAfterFailing = "$entryWhichWithFeature: $separator$indentBulletPoint$indentFailingBulletPoint$listBulletPointWithIndent"

    fun entry(index: Int)
        = String.format(entryWithIndex, index)

    fun AssertionPlant<CharSequence>.entrySuccess(index: Int, actual: Any, expected: String): AssertionPlant<CharSequence> {
        return this.contains.exactly(1).regex(
            "$successfulBulletPoint$featureArrow${entry(index)}: \\Q$actual\\E.*$separator" +
                "$indentBulletPoint$indentSuccessfulBulletPoint$anEntryWithFeatureAfterSuccess$expected")
    }

    fun AssertionPlant<CharSequence>.entryFailing(index: Int, actual: Any, expected: String): AssertionPlant<CharSequence> {
        return this.contains.exactly(1).regex(
            "$failingBulletPoint$featureArrow${entry(index)}: \\Q$actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$anEntryWithFeatureAfterFailing$expected")
    }

    val isDescr = DescriptionBasic.IS.getDefault()

    describeFun(containsNullable, containsNot) {

        context("iterable $list") {
            listOf(
                1.0 to arrayOf<Double>(),
                3.0 to arrayOf<Double>(),
                null to arrayOf<Double>(),
                null to arrayOf(3.0, null),
                null to arrayOf(1.0),
                1.0 to arrayOf(3.0, null)
            ).forEach { (first, rest) ->
                val restText = if (rest.isEmpty()) {
                    ""
                } else {
                    ", ${rest.joinToString()}"
                }
                context("search for $first$restText") {
                    test("$containsNullable $first$restText  does not throw") {
                        fluent.containsFun(first, *rest)
                    }
                    test("$containsNot $first$restText throws AssertionError") {
                        expect {
                            fluent.containsNotFun(first, *rest)
                        }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS_NOT) } }
                    }
                }

            }

            context("search for 2.5") {
                test("$containsNullable 2.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(2.5)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS) } }
                }
                test("$containsNot 2.5 throws AssertionError") {
                    fluent.containsNotFun(2.5)
                }
            }
        }
    }

    describeFun(containsInAnyOrderNullableEntries) {
        context("iterable $list") {
            context("happy cases (do not throw)") {
                test("$toBeFun(1.0)") {
                    fluent.containsInAnyOrderNullableEntriesFun({ toBe(1.0) })
                }
                test("null") {
                    fluent.containsInAnyOrderNullableEntriesFun(null)
                }
                test("$toBeFun(1.0) and null") {
                    fluent.containsInAnyOrderNullableEntriesFun({ toBe(1.0) }, null)
                }
                test("$toBeFun(3.0), null and $toBeFun(1.0)") {
                    fluent.containsInAnyOrderNullableEntriesFun({ toBe(3.0) }, null, { toBe(1.0) })
                }
            }

            context("failing cases") {
                test("$toBeFun(2.0)") {
                    expect {
                        fluent.containsInAnyOrderNullableEntriesFun({ toBe(2.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrder: $separator",
                                "$anEntryWhich: $separator",
                                "$toBeDescr: 2.0",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                        }
                    }
                }

                test("$isLessThanFun(1.0) and $isLessThanFun(3.0)") {
                    expect {
                        fluent.containsInAnyOrderNullableEntriesFun({ isLessThan(1.0) }, { isGreaterThan(3.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(2).values(
                                "$anEntryWhich: $separator",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                            contains.exactly(1).values(
                                "$containsInAnyOrder: $separator",
                                "$isLessThanDescr: 1.0",
                                "$isGreaterThanDescr: 3.0"
                            )
                        }
                    }
                }
            }
        }

        context("iterable $oneToSeven") {
            test("null") {
                expect {
                    assert(oneToSeven).containsInAnyOrderNullableEntriesFun(null)
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$containsInAnyOrder: $separator",
                            "$anEntryWhich: $separator",
                            "$isDescr: null",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
            }
        }
    }

    describeFun(containsInAnyOrderOnlyNullableEntries) {
        context("iterable $list") {
            context("happy cases (do not throw)") {
                test("null, $toBeFun(1.0), null, $toBeFun(3.0)") {
                    fluent.containsInAnyOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, null, { toBe(3.0) })
                }
                test("$toBeFun(1.0), null, null, $toBeFun(3.0)") {
                    fluent.containsInAnyOrderOnlyNullableEntriesFun({ toBe(1.0) }, null, null, { toBe(3.0) })
                }
                test("$toBeFun(1.0), null, $toBeFun(3.0), null") {
                    fluent.containsInAnyOrderOnlyNullableEntriesFun({ toBe(1.0) }, null, { toBe(3.0) }, null)
                }
                test("$toBeFun(1.0), $toBeFun(3.0), null, null") {
                    fluent.containsInAnyOrderOnlyNullableEntriesFun({ toBe(1.0) }, { toBe(3.0) }, null, null)
                }
            }

            context("failing cases") {
                test("null, $toBeFun(1.0), $toBeFun(3.0) -- null was missing") {
                    expect {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, { toBe(3.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrderOnly:",
                                "$successfulBulletPoint$anEntryAfterSuccess$isDescr: null",
                                "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 1.0",
                                "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 3.0",
                                "$warningBulletPoint$additionalEntries:",
                                "${listBulletPoint}null"
                            )
                            containsSize(4, 3)
                        }
                    }
                }

                test("first wins: $isLessThanFun(5.0), 1.0, 2.0, 3.0, 4.0") {
                    expect {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun({ isLessThan(4.0) }, null, null, { toBe(1.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrderOnly:",
                                "$successfulBulletPoint$anEntryAfterSuccess$isLessThanDescr: 4.0",
                                "$successfulBulletPoint$anEntryAfterSuccess$isDescr: null",
                                "$successfulBulletPoint$anEntryAfterSuccess$isDescr: null",
                                "$failingBulletPoint$anEntryAfterFailing$toBeDescr: 1.0",
                                "$warningBulletPoint$mismatches:",
                                "${listBulletPoint}3.0"
                            )
                            containsSize(4, 4)
                        }
                    }
                }
            }
        }
    }

    describeFun(containsInOrderOnlyNullableEntries) {
        context("iterable $list") {

            describe("happy case") {
                test("null, 1.0, null, 3.0") {
                    fluent.containsInOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, null, { toBe(3.0) })
                }
                test("null, $isLessThanFun(5.0), null, $isLessThanFun(5.0)") {
                    fluent.containsInOrderOnlyNullableEntriesFun(null, { isLessThan(5.0) }, null, { isLessThan(5.0) })
                }
            }

            describe("error cases (throws AssertionError)") {

                test("null, null, $isLessThanFun(5.0), $isGreaterThanFun(2.0) -- wrong order") {
                    expect {
                        fluent.containsInOrderOnlyNullableEntriesFun(null, null, { isLessThan(5.0) }, { isGreaterThan(2.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, "null", "$isDescr: null")
                            entryFailing(1, 1.0, "$isDescr: null")
                            entryFailing(2, "null", "$isLessThanDescr: 5.0")
                            entrySuccess(3, 3.0, "$isGreaterThanDescr: 2.0")
                            containsSize(4, 4)
                        }
                    }
                }

                test("null, 1.0, null, 3.0, null -- null too much") {
                    expect {
                        fluent.containsInOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, null, { toBe(3.0) }, null)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, "null", "$isDescr: null")
                            entrySuccess(1, 1.0, "$toBeDescr: 1.0")
                            entrySuccess(2, "null", "$isDescr: null")
                            entrySuccess(3, 3.0, "$toBeDescr: 3.0")
                            entryFailing(4, sizeExceeded, "$isDescr: null")
                            containsSize(4, 5)
                        }
                    }
                }
            }
        }
    }
})
