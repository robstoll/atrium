package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInOrderOnlyObjectsSpec(
    verbs: IAssertionVerbFactory,
    containsPair: Pair<String, IAssertionPlant<Iterable<Double>>.(Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String
) : IterableContainsSpecBase({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double>>(
        containsPair.first to mapToCreateAssertion { containsPair.second(this, 2.5, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Iterable<Double>>(verbs,
        checkingTriple(containsPair.first, { containsPair.second(this, 2.5, arrayOf(1.2)) }, listOf(2.5, 1.2) as Iterable<Double>, listOf(2.5, 2.2))
    ) {})

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val oneToFour = listOf(1.0, 2.0, 3.0, 4.0, 4.0)
    val fluent = assert(oneToFour)

    val (contains, containsFunArr) = containsPair
    fun IAssertionPlant<Iterable<Double>>.containsFun(t: Double, vararg tX: Double)
        = containsFunArr(t, tX.toTypedArray())

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)

    val entryWithIndex = DescriptionIterableAssertion.ENTRY_WITH_INDEX.getDefault()
    val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()

    val toBeWithFeature = "$indentFeatureArrow$featureBulletPoint${DescriptionAnyAssertion.TO_BE.getDefault()}"
    val toBeAfterSuccess = "$indentBulletPoint$indentSuccessfulBulletPoint$toBeWithFeature"
    val toBeAfterFailing = "$indentBulletPoint$indentFailingBulletPoint$toBeWithFeature"

    fun entry(index: Int)
        = String.format(entryWithIndex, index)

    fun IAssertionPlant<CharSequence>.entrySuccess(index: Int, actual: Any, expected: Double): IAssertionPlant<CharSequence> {
        return this.contains.exactly(1).regex(
            "$successfulBulletPoint$featureArrow${entry(index)}: \\Q$actual\\E.*$separator" +
                "$toBeAfterSuccess: $expected")
    }

    fun IAssertionPlant<CharSequence>.entryFailing(index: Int, actual: Any, expected: Double): IAssertionPlant<CharSequence> {
        return this.contains.exactly(1).regex(
            "$failingBulletPoint$featureArrow${entry(index)}: \\Q$actual\\E.*$separator" +
                "$toBeAfterFailing: $expected")
    }

    describe("fun $contains") {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$contains 1.0 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, 1.0)
                        containsNot(additionalEntries)
                    }
                }
            }
            test("$contains 1.0 and 4.0 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.0, 4.0)
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, 1.0)
                        entryFailing(1, sizeExceeded, 4.0)
                        containsNot(additionalEntries)
                    }
                }
            }
        }

        context("iterable '$oneToFour'") {

            describe("happy case $contains") {
                test("1.0, 2.0, 3.0, 4.0, 4.0") {
                    fluent.containsFun(1.0, 2.0, 3.0, 4.0, 4.0)
                }
            }

            describe("error cases $contains ... throws AssertionError") {

                test("4.0, 1.0, 2.0, 3.0, 4.0 -- wrong order") {
                    expect {
                        fluent.containsFun(4.0, 1.0, 2.0, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entryFailing(0, 1.0, 4.0)
                            entryFailing(1, 2.0, 1.0)
                            entryFailing(2, 3.0, 2.0)
                            entryFailing(3, 4.0, 3.0)
                            entrySuccess(4, 4.0, 4.0)
                            containsSize(5, 5)
                        }
                    }
                }

                test("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                    expect {
                        fluent.containsFun(1.0, 2.0, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, 1.0)
                            entrySuccess(1, 2.0, 2.0)
                            entrySuccess(2, 3.0, 3.0)
                            entrySuccess(3, 4.0, 4.0)
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
                        fluent.containsFun(1.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, 1.0)
                            entryFailing(1, 2.0, 4.0)
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
                test("1.0, 3.0, 5.0 -- 5.0 is wrong and 2.0, 4.0 and 4.0 are missing") {
                    expect {
                        fluent.containsFun(1.0, 3.0, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, 1.0)
                            entryFailing(1, 2.0, 3.0)
                            entryFailing(2, 3.0, 5.0)
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
                        fluent.containsFun(1.0, 2.0, 3.0, 4.0, 4.0, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, 1.0)
                            entrySuccess(1, 2.0, 2.0)
                            entrySuccess(2, 3.0, 3.0)
                            entrySuccess(3, 4.0, 4.0)
                            entrySuccess(4, 4.0, 4.0)
                            entryFailing(5, sizeExceeded, 5.0)
                            containsSize(5, 6)
                        }
                    }
                }
            }
        }
    }
})
