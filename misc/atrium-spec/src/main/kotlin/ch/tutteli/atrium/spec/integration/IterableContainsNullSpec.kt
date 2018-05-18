package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsNullSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, Assert<Iterable<Double?>>.(Double?, Array<out Double?>) -> Assert<Iterable<Double?>>>,
    containsInAnyOrderNullableEntriesPair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>>,
    containsInOrderOnlyNullableEntriesPair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>>,
    //TODO add pair for inOrderOnlyGrouped
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Iterable<Double?>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, null, arrayOf()) },
        containsInAnyOrderNullableEntriesPair.first to mapToCreateAssertion { containsInAnyOrderNullableEntriesPair.second(this, null, arrayOf()) },
        containsInOrderOnlyNullableEntriesPair.first to mapToCreateAssertion { containsInOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double?>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2)),
        checkingTriple(containsInAnyOrderNullableEntriesPair.first, { containsInAnyOrderNullableEntriesPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2)),
        checkingTriple(containsInOrderOnlyNullableEntriesPair.first, { containsInOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double?>) -> Assert<Iterable<Double?>> = verbs::checkImmediately
    val expect = verbs::checkException
    val list = listOf(null, 1.0, null, 3.0)
    val fluent = assert(list)

    val (containsInAnyOrderNullableEntries, containsInAnyOrderNullableEntriesArr) = containsInAnyOrderNullableEntriesPair
    fun Assert<Iterable<Double?>>.containsInAnyOrderNullableEntriesFun(t: (Assert<Double>.() -> Unit)?, vararg tX: (Assert<Double>.() -> Unit)?)
        = containsInAnyOrderNullableEntriesArr(t, tX)

    val (containsInOrderOnlyNullableEntries, containsInOrderOnlyNullableEntriesArr) = containsInOrderOnlyNullableEntriesPair
    fun Assert<Iterable<Double?>>.containsInOrderOnlyNullableEntriesFun(t: (Assert<Double>.() -> Unit)?, vararg tX: (Assert<Double>.() -> Unit)?)
        = containsInOrderOnlyNullableEntriesArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)

    val entryWithIndex = DescriptionIterableAssertion.ENTRY_WITH_INDEX.getDefault()
    val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()
    val explanatoryPointWithIndent = "$indentFeatureArrow$indentFeatureBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    val entryWhichWithFeature = "$indentFeatureArrow$featureBulletPoint$anEntryWhich"
    val anEntryWithFeatureAfterSuccess = "$entryWhichWithFeature: $separator$indentBulletPoint$indentSuccessfulBulletPoint$explanatoryPointWithIndent"
    val anEntryWithFeatureAfterFailing = "$entryWhichWithFeature: $separator$indentBulletPoint$indentFailingBulletPoint$explanatoryPointWithIndent"

    fun entry(index: Int)
        = String.format(entryWithIndex, index)

    fun Assert<CharSequence>.entrySuccess(index: Int, actual: Any, expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentSuccessfulBulletPoint$anEntryWithFeatureAfterSuccess$expected")
    }

    fun Assert<CharSequence>.entryFailing(index: Int, actual: Any, expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$anEntryWithFeatureAfterFailing$expected")
    }

    val isDescr = DescriptionBasic.IS.getDefault()


    describeFun(containsInAnyOrderNullableEntries) {
        absentSubjectTests(verbs, Assert<Iterable<Double?>>::containsInAnyOrderNullableEntriesFun)

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
                test("null, null, null") {
                    //finds twice the same entry with null but that is fine since we do not search for unique entries in this case
                    fluent.containsInAnyOrderNullableEntriesFun(null, null, null)
                }
            }

            context("failing cases") {
                test("$toBeFun(2.0)") {
                    expect {
                        fluent.containsInAnyOrderNullableEntriesFun({ toBe(2.0) })
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$containsInAnyOrder: $separator",
                            "$anEntryWhich: $separator",
                            "$toBeDescr: 2.0",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
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
                    messageContains(
                        "$containsInAnyOrder: $separator",
                        "$anEntryWhich: $separator",
                        "$isDescr: null",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }
        }

        context("search for entry where the lambda does not specify any assertion") {
            it("throws an ${IllegalArgumentException::class.simpleName}") {
                expect {
                    fluent.containsInAnyOrderNullableEntriesFun({})
                }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
            }
        }
    }

    describeFun(containsInOrderOnlyNullableEntries) {
        absentSubjectTests(verbs, Assert<Iterable<Double?>>::containsInOrderOnlyNullableEntriesFun)

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

        context("search for entry where the lambda does not specify any assertion") {
            it("throws an ${IllegalArgumentException::class.simpleName}") {
                expect {
                    fluent.containsInOrderOnlyNullableEntriesFun({})
                }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
            }
        }
    }
})
