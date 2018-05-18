package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInOrderOnlyGroupedValuesSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, Assert<Iterable<Double>>.(GroupWithoutNullableEntries<Double>, GroupWithoutNullableEntries<Double>, Array<out GroupWithoutNullableEntries<Double>>) -> Assert<Iterable<Double>>>,
    groupFactory: (Array<out Double>) -> GroupWithoutNullableEntries<Double>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    fun group(vararg doubles: Double): GroupWithoutNullableEntries<Double> = groupFactory(doubles.toTypedArray())

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, group(2.5), group(4.1), arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, group(2.5), group(1.2, 2.2), arrayOf()) }, listOf(2.5, 2.2, 1.2).asIterable(), listOf(2.2, 1.2, 2.5))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val oneToFour = listOf(1.0, 2.0, 3.0, 4.0, 4.0)
    val fluent = assert(oneToFour)

    val (contains, containsFunArr) = containsPair
    fun Assert<Iterable<Double>>.containsFun(t1: GroupWithoutNullableEntries<Double>, t2: GroupWithoutNullableEntries<Double>, vararg tX: GroupWithoutNullableEntries<Double>)
        = containsFunArr(t1,t2, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)
    val indentWarningBulletPoint = " ".repeat(warningBulletPoint.length)

    val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()
    val anEntryWhichIs = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.getDefault()

    val toBeWithFeature = "$indentFeatureArrow$featureBulletPoint${DescriptionAnyAssertion.TO_BE.getDefault()}"
    val toBeAfterSuccess = "$indentBulletPoint$indentSuccessfulBulletPoint$toBeWithFeature"
    val toBeAfterFailing = "$indentBulletPoint$indentFailingBulletPoint$toBeWithFeature"

    fun index(index: Int)
        = String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)

    fun entryWithIndex(index: Int)
        = String.format(DescriptionIterableAssertion.ENTRY_WITH_INDEX.getDefault(), index)

    fun index(fromIndex: Int, toIndex: Int)
        = String.format(DescriptionIterableAssertion.INDEX_FROM_TO.getDefault(), fromIndex, toIndex)

    fun entry(prefix: String, bulletPoint: String, expected: DoubleArray)
        = expected.joinToString(".*$separator") { "$prefix\\Q$bulletPoint$anEntryWhichIs: $it\\E" }

    fun size(prefix: String, bulletPoint: String, actual: Int, expected: Int)
        = "$prefix\\Q$bulletPoint\\E${featureArrow}size: $actual[^:]+: $expected"


    val afterFail = "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun failAfterFail(vararg expected: Double)
        = entry(afterFail, failingBulletPoint, expected)

    fun successAfterFail(vararg expected: Double)
        = entry(afterFail, successfulBulletPoint, expected)

    fun failSizeAfterFail(actual: Int, expected: Int)
        = size(afterFail, failingBulletPoint, actual, expected)

    fun successSizeAfterFail(size: Int)
        = size(afterFail, successfulBulletPoint, size, size)

    fun <T> warning(msg: String, values: Array<out T>, act: (T) -> String)
         = "$afterFail\\Q$warningBulletPoint$msg\\E: $separator" +
            values.joinToString(".*$separator") { "$afterFail$indentWarningBulletPoint$listBulletPoint${act(it)}" }

    fun mismatchesAfterFail(vararg mismatched: Double)
        = warning(mismatches, mismatched.toTypedArray()){ "$it" }

    fun additional(vararg entryWithValue: Pair<Int, Double>)
        = warning(additionalEntries, entryWithValue) { "${entryWithIndex(it.first)}: ${it.second}" }


    val afterSuccess = "$indentBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun successAfterSuccess(vararg expected: Double)
        = entry(afterSuccess, successfulBulletPoint, expected)

    fun successSizeAfterSuccess(size: Int)
        = size(afterSuccess, successfulBulletPoint, size, size)


    fun Assert<CharSequence>.indexSuccess(index: Int, actual: Any, expected: Double): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$toBeAfterSuccess: $expected")
    }

    fun Assert<CharSequence>.indexSuccess(fromIndex: Int, toIndex: Int, actual: List<Double>, vararg expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")

        )
    }

    fun Assert<CharSequence>.indexFail(index: Int, actual: Any, expected: Double): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$toBeAfterFailing: $expected")
    }

    fun Assert<CharSequence>.indexFail(fromIndex: Int, toIndex: Int, actual: List<Double>, vararg expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")
        )
    }

    describeFun(contains) {
        context("throws an $illegalArgumentException") {
            test("if an empty group is given as first parameter") {
                expect {
                    fluent.containsFun(group(), group(-1.2))
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
            test("if an empty group is given as second parameter") {
                expect {
                    fluent.containsFun(group(1.2), group())
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
            test("if an empty group is given as third parameter") {
                expect {
                    fluent.containsFun(group(1.2), group(4.3), group())
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
            test("if an empty group is given as fourth parameter") {
                expect {
                    fluent.containsFun(group(1.2), group(4.3), group(5.7), group())
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
        }

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            test("(1.0), (1.2) throws AssertionError") {
                expect {
                    fluentEmpty.containsFun(group(1.0), group(1.2))
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsInOrderOnlyGrouped:")
                        indexFail(0, sizeExceeded, 1.0)
                        indexFail(1, sizeExceeded, 1.2)
                        containsNot(additionalEntries)
                        containsSize(0, 2)
                    }
                }
            }
        }

        context("iterable $oneToFour") {

            describe("happy case $contains") {
                test("(1.0), (2.0, 3.0), (4.0, 4.0)") {
                    fluent.containsFun(group(1.0), group(2.0, 3.0), group(4.0, 4.0))
                }
                test("(2.0, 1.0), (4.0, 3.0), (4.0)") {
                    fluent.containsFun(group(2.0, 1.0), group(4.0, 3.0), group(4.0))
                }
                test("(2.0, 3.0, 1.0), (4.0), (4.0)") {
                    fluent.containsFun(group(2.0, 3.0, 1.0), group(4.0), group(4.0))
                }
                test("(1.0, 2.0), (4.0, 3.0, 4.0)") {
                    fluent.containsFun(group(1.0, 2.0), group(4.0, 3.0, 4.0))
                }
            }

            describe("error cases (throws AssertionError)") {

                test("(4.0, 1.0), (2.0, 3.0, 4.0) -- wrong order") {
                    expect {
                        fluent.containsFun(group(4.0, 1.0), group(2.0, 3.0, 4.0))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexFail(0, 1, listOf(1.0, 2.0),
                                failAfterFail(4.0),
                                successAfterFail(1.0),
                                successSizeAfterFail(2),
                                mismatchesAfterFail(2.0)
                            )
                            indexFail(2, 4, listOf(3.0, 4.0, 4.0),
                                failAfterFail(2.0),
                                successAfterFail(3.0),
                                successAfterFail(4.0),
                                successSizeAfterFail(3),
                                mismatchesAfterFail(4.0)
                            )
                            containsRegex(size(indentBulletPoint, successfulBulletPoint, 5, 5))
                        }
                    }
                }

                test("(1.0), (4.0, 3.0, 2.0) -- 4.0 was missing") {
                    expect {
                        fluent.containsFun(group(1.0), group(4.0, 2.0, 3.0))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexSuccess(0, 1.0, 1.0)
                            indexSuccess(1, 3, listOf(2.0, 3.0, 4.0),
                                successAfterSuccess(4.0, 2.0, 3.0),
                                successSizeAfterSuccess(3)
                            )
                            containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 4))
                            containsRegex(additional(4 to 4.0))
                        }
                    }
                }

                test("(1.0), (4.0) -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        fluent.containsFun(group(1.0), group(4.0))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexSuccess(0, 1.0, 1.0)
                            indexFail(1, 2.0, 4.0)
                            containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 2))
                            containsRegex(additional(2 to 3.0, 3 to 4.0, 4 to 4.0))
                        }
                    }
                }
                test("(1.0, 3.0), (5.0) -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        fluent.containsFun(group(1.0, 3.0), group(5.0))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexFail(0, 1, listOf(1.0, 2.0),
                                successAfterFail(1.0),
                                failAfterFail(3.0),
                                successSizeAfterFail(2),
                                mismatchesAfterFail(2.0)
                            )
                            indexFail(2, 3.0, 5.0)
                            containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 3))
                            containsRegex(additional(3 to 4.0, 4 to 4.0))
                        }
                    }
                }
                test("( 4.0, 1.0, 3.0, 2.0), (5.0, 4.0) -- 5.0 too much") {
                    expect {
                        fluent.containsFun(group(4.0, 1.0, 3.0, 2.0), group(5.0, 4.0))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexSuccess(0, 3, listOf(1.0, 2.0, 3.0, 4.0),
                                successAfterSuccess(4.0, 1.0, 3.0, 2.0),
                                successSizeAfterSuccess(4)
                            )
                            indexFail(4, 5, listOf(4.0),
                                failAfterFail(5.0),
                                successAfterFail(4.0),
                                failSizeAfterFail(1, 2)
                            )
                            containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 6))
                        }
                    }
                }
            }
        }
    }
})
