package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS_NOT
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class IterableContainsNullSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, AssertionPlant<Iterable<Double?>>.(Double?, Array<out Double?>) -> AssertionPlant<Iterable<Double?>>>,
    containsNotPair: Pair<String, AssertionPlant<Iterable<Double?>>.(Double?, Array<out Double?>) -> AssertionPlant<Iterable<Double?>>>,
    containsInAnyOrderNullableEntriesPair: Pair<String, AssertionPlant<Iterable<Double?>>.((AssertionPlant<Double>.() -> Unit)?, Array<out (AssertionPlant<Double>.() -> Unit)?>) -> AssertionPlant<Iterable<Double?>>>,
    containsInAnyOrderOnlyNullableEntriesPair: Pair<String, AssertionPlant<Iterable<Double?>>.((AssertionPlant<Double>.() -> Unit)?, Array<out (AssertionPlant<Double>.() -> Unit)?>) -> AssertionPlant<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double?>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, null, arrayOf()) },
        containsNotPair.first to mapToCreateAssertion { containsNotPair.second(this, null, arrayOf()) },
        containsInAnyOrderNullableEntriesPair.first to mapToCreateAssertion { containsInAnyOrderNullableEntriesPair.second(this, null, arrayOf()) },
        containsInAnyOrderOnlyNullableEntriesPair.first to mapToCreateAssertion { containsInAnyOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Iterable<Double?>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2)),
        checkingTriple(containsNotPair.first, { containsNotPair.second(this, null, arrayOf()) }, listOf(1.2) as Iterable<Double?>, listOf(null)),
        checkingTriple(containsInAnyOrderNullableEntriesPair.first, { containsInAnyOrderNullableEntriesPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2)),
        checkingTriple(containsInAnyOrderOnlyNullableEntriesPair.first, { containsInAnyOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2))
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


    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)

    val anEntryAfterSuccess = "$anEntryWhich: $separator$indentBulletPoint$indentSuccessfulBulletPoint$listBulletPoint"
    val anEntryAfterFailing = "$anEntryWhich: $separator$indentBulletPoint$indentFailingBulletPoint$listBulletPoint"

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

        context("iterable $oneToSeven"){
            test("null") {
                expect {
                    assert(oneToSeven).containsInAnyOrderNullableEntriesFun(null)
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$containsInAnyOrder: $separator",
                            "$anEntryWhich: $separator",
                            "${DescriptionBasic.IS.getDefault()}: null",
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
                    fluent.containsInAnyOrderOnlyNullableEntriesFun(null, { toBe(1.0) },  null, { toBe(3.0) })
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
                                "$successfulBulletPoint$anEntryAfterSuccess${DescriptionBasic.IS.getDefault()}: null",
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
                                "$successfulBulletPoint$anEntryAfterSuccess${DescriptionBasic.IS.getDefault()}: null",
                                "$successfulBulletPoint$anEntryAfterSuccess${DescriptionBasic.IS.getDefault()}: null",
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
})
