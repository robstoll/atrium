package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInOrderOnlyEntriesSpec(
    verbs: IAssertionVerbFactory,
    containsEntriesPair: Pair<String, IAssertionPlant<Iterable<Double>>.(IAssertionPlant<Double>.() -> Unit, Array<out IAssertionPlant<Double>.() -> Unit>) -> IAssertionPlant<Iterable<Double>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double>>(
        containsEntriesPair.first to mapToCreateAssertion { containsEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Iterable<Double>>(verbs,
        checkingTriple(containsEntriesPair.first, { containsEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }, listOf(2.5) as Iterable<Double>, listOf())
    ) {})

    fun describeFun(description: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, description, body)

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val oneToFour = listOf(1.0, 2.0, 3.0, 4.0, 4.0)
    val fluent = assert(oneToFour)

    val (containsEntries, containsEntriesFunArr) = containsEntriesPair
    fun IAssertionPlant<Iterable<Double>>.containsEntriesFun(t: IAssertionPlant<Double>.() -> Unit, vararg tX: IAssertionPlant<Double>.() -> Unit)
        = containsEntriesFunArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)

    val entryWithIndex = DescriptionIterableAssertion.ENTRY_WITH_INDEX.getDefault()
    val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()
    val listBulletPointWithIndent = "$indentFeatureArrow$indentFeatureBulletPoint$listBulletPoint"

    val entryWhichWithFeature = "$indentFeatureArrow$featureBulletPoint$anEntryWhich"
    val anEntryAfterSuccess = "$entryWhichWithFeature: $separator$indentBulletPoint$indentSuccessfulBulletPoint$listBulletPointWithIndent"
    val anEntryAfterFailing = "$entryWhichWithFeature: $separator$indentBulletPoint$indentFailingBulletPoint$listBulletPointWithIndent"

    fun entry(index: Int)
        = String.format(entryWithIndex, index)

    fun IAssertionPlant<CharSequence>.entrySuccess(index: Int, actual: Any, expected: String): IAssertionPlant<CharSequence> {
        return this.contains.exactly(1).regex(
            "$successfulBulletPoint$featureArrow${entry(index)}: \\Q$actual\\E.*$separator" +
                "$indentBulletPoint$indentSuccessfulBulletPoint$anEntryAfterSuccess$expected")
    }

    fun IAssertionPlant<CharSequence>.entryFailing(index: Int, actual: Any, expected: String): IAssertionPlant<CharSequence> {
        return this.contains.exactly(1).regex(
            "$failingBulletPoint$featureArrow${entry(index)}: \\Q$actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$anEntryAfterFailing$expected")
    }

    describeFun(containsEntries) {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmptyString.containsEntriesFun({ isLessThan(1.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, "$isLessThanDescr: 1.0")
                        containsNot(additionalEntries)
                        containsSize(0, 1)
                    }
                }
            }
            test("$isLessThanFun(1.0) and $isGreaterThanFun(4.0) throws AssertionError") {
                expect {
                    fluentEmptyString.containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, "$isLessThanDescr: 1.0")
                        entryFailing(1, sizeExceeded, "$isGreaterThanDescr: 4.0")
                        containsNot(additionalEntries)
                        containsSize(0, 2)
                    }
                }
            }
            test("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmptyString.containsEntriesFun({ returnValueOf(subject::dec).toBe(1.0) })
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.WARNING_SUBJECT_NOT_SET) } }
            }
        }

        context("iterable '$oneToFour'") {

            describe("happy case $containsEntries") {
                test("1.0, 2.0, 3.0, 4.0, 4.0") {
                    fluent.containsEntriesFun({ toBe(1.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) }, { toBe(4.0) })
                }
                test("$isLessThanFun(5.0), $isLessThanFun(5.0), $isLessThanFun(5.0), $isLessThanFun(5.0), $isLessThanFun(5.0)") {
                    fluent.containsEntriesFun({ isLessThan(5.0) }, { isLessThan(5.0) }, { isLessThan(5.0) }, { isLessThan(5.0) }, { isLessThan(5.0) })
                }
            }

            describe("error cases $containsEntries ... throws AssertionError") {

                test("$isLessThanFun(5.0), 1.0, 2.0, $isGreaterThanFun(2.0), 4.0 -- wrong order") {
                    expect {
                        fluent.containsEntriesFun({ isLessThan(5.0) }, { toBe(1.0) }, { toBe(2.0) }, { isGreaterThan(2.0) }, { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$isLessThanDescr: 5.0")
                            entryFailing(1, 2.0, "$toBeDescr: 1.0")
                            entryFailing(2, 3.0, "$toBeDescr: 2.0")
                            entrySuccess(3, 4.0, "$isGreaterThanDescr: 2.0")
                            entrySuccess(4, 4.0, "$toBeDescr: 4.0")
                            containsSize(5, 5)
                        }
                    }
                }

                test("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$toBeDescr: 1.0")
                            entrySuccess(1, 2.0, "$toBeDescr: 2.0")
                            entrySuccess(2, 3.0, "$toBeDescr: 3.0")
                            entrySuccess(3, 4.0, "$toBeDescr: 4.0")
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            containsSize(5, 4)
                        }
                    }
                }

                test("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$toBeDescr: 1.0")
                            entryFailing(1, 2.0, "$toBeDescr: 4.0")
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(2)}: 3.0",
                                "$listBulletPoint${entry(3)}: 4.0",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            containsSize(5, 2)
                        }
                    }
                }
                test("1.0, 3.0, $isGreaterThanFun(4.0) -- $isGreaterThanFun(4.0) is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(3.0) }, { isGreaterThan(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$toBeDescr: 1.0")
                            entryFailing(1, 2.0, "$toBeDescr: 3.0")
                            entryFailing(2, 3.0, "$isGreaterThanDescr: 4.0")
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(3)}: 4.0",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            containsSize(5, 3)
                        }
                    }
                }
                test("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 too much") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) }, { toBe(4.0) }, { toBe(5.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$toBeDescr: 1.0")
                            entrySuccess(1, 2.0, "$toBeDescr: 2.0")
                            entrySuccess(2, 3.0, "$toBeDescr: 3.0")
                            entrySuccess(3, 4.0, "$toBeDescr: 4.0")
                            entrySuccess(4, 4.0, "$toBeDescr: 4.0")
                            entryFailing(5, sizeExceeded, "$toBeDescr: 5.0")
                            containsSize(5, 6)
                        }
                    }
                }
            }
        }
    }
})
