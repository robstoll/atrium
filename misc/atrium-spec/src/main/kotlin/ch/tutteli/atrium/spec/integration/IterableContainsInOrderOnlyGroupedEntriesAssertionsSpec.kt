package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, Assert<Iterable<Double>>.(GroupWithoutNullableEntries<Assert<Double>.() -> Unit>, GroupWithoutNullableEntries<Assert<Double>.() -> Unit>, Array<out GroupWithoutNullableEntries<Assert<Double>.() -> Unit>>) -> Assert<Iterable<Double>>>,
    groupFactory: (Array<out Assert<Double>.() -> Unit>) -> GroupWithoutNullableEntries<Assert<Double>.() -> Unit>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    fun group(vararg assertionCreators: Assert<Double>.() -> Unit): GroupWithoutNullableEntries<Assert<Double>.() -> Unit> = groupFactory(assertionCreators)

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, group({ toBe(2.5) }), group({ toBe(4.1) }), arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, group({ toBe(2.5) }), group({ toBe(1.2) }, { toBe(2.2) }), arrayOf()) }, listOf(2.5, 2.2, 1.2).asIterable(), listOf(2.2, 1.2, 2.5))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToFour)

    val (contains, containsFunArr) = containsPair
    fun Assert<Iterable<Double>>.containsFun(t1: GroupWithoutNullableEntries<Assert<Double>.() -> Unit>, t2: GroupWithoutNullableEntries<Assert<Double>.() -> Unit>, vararg tX: GroupWithoutNullableEntries<Assert<Double>.() -> Unit>)
        = containsFunArr(t1,t2, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)
    val indentWarningBulletPoint = " ".repeat(warningBulletPoint.length)

    val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()

    fun index(index: Int)
        = String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)

    fun entryWithIndex(index: Int)
        = String.format(DescriptionIterableAssertion.ENTRY_WITH_INDEX.getDefault(), index)

    fun index(fromIndex: Int, toIndex: Int)
        = String.format(DescriptionIterableAssertion.INDEX_FROM_TO.getDefault(), fromIndex, toIndex)

    fun entry(prefix: String, bulletPoint: String, indentBulletPoint: String, expected: Array<out String>)
        = expected.joinToString(".*$separator") {
        "$prefix\\Q$bulletPoint$anEntryWhich: \\E$separator" +
            "$prefix$indentBulletPoint$indentListBulletPoint$explanatoryBulletPoint$it"
    }

    fun size(prefix: String, bulletPoint: String, actual: Int, expected: Int)
        = "$prefix\\Q$bulletPoint\\E${featureArrow}size: $actual[^:]+: $expected"


    val afterFail = "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun failAfterFail(vararg expected: String)
        = entry(afterFail, failingBulletPoint, indentFailingBulletPoint, expected)

    fun successAfterFail(vararg expected: String)
        = entry(afterFail, successfulBulletPoint, indentSuccessfulBulletPoint, expected)

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
    fun successAfterSuccess(vararg expected: String)
        = entry(afterSuccess, successfulBulletPoint, indentSuccessfulBulletPoint, expected)

    fun successSizeAfterSuccess(size: Int)
        = size(afterSuccess, successfulBulletPoint, size, size)


    fun Assert<CharSequence>.indexSuccess(index: Int, actual: Any, expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$featureBulletPoint$anEntryWhich: $separator" +
                "$afterSuccess$indentListBulletPoint$explanatoryBulletPoint$expected")
    }

    fun Assert<CharSequence>.indexSuccess(fromIndex: Int, toIndex: Int, actual: List<Double>, vararg expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")

        )
    }

    fun Assert<CharSequence>.indexFail(index: Int, actual: Any, expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$anEntryWhich: $separator" +
                "$afterFail$indentListBulletPoint$explanatoryBulletPoint$expected")
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
                    fluent.containsFun(group(), group({ toBe(-1.2) }))
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
            test("if an empty group is given as second parameter") {
                expect {
                    fluent.containsFun(group({ toBe(1.2) }), group())
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
            test("if an empty group is given as third parameter") {
                expect {
                    fluent.containsFun(group({ toBe(1.2) }), group({ toBe(4.3) }), group())
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
            test("if an empty group is given as fourth parameter") {
                expect {
                    fluent.containsFun(group({ toBe(1.2) }), group({ toBe(4.3) }), group({ toBe(5.7) }), group())
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
        }

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            test("(1.0), (1.2) throws AssertionError") {
                expect {
                    fluentEmpty.containsFun(group({ toBe(1.0) }), group({ toBe(1.2) }))
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsInOrderOnlyGrouped:")
                        indexFail(0, sizeExceeded, "$toBeDescr: 1.0")
                        indexFail(1, sizeExceeded, "$toBeDescr: 1.2")
                        containsNot(additionalEntries)
                        containsSize(0, 2)
                    }
                }
            }
        }

        context("iterable $oneToFour") {

            describe("happy case $contains") {
                test("(1.0), (2.0, 3.0), (4.0, 4.0)") {
                    fluent.containsFun(group({ toBe(1.0) }), group({ toBe(2.0) }, { toBe(3.0) }), group({ toBe(4.0) }, { toBe(4.0) }))
                }
                test("(2.0, 1.0), (4.0, 3.0), (4.0)") {
                    fluent.containsFun(group({ toBe(2.0) }, { toBe(1.0) }), group({ toBe(4.0) }, { toBe(3.0) }), group({ toBe(4.0) }))
                }
                test("(2.0, 3.0, 1.0), (4.0), (4.0)") {
                    fluent.containsFun(group({ toBe(2.0) }, { toBe(3.0) }, { toBe(1.0) }), group({ toBe(4.0) }), group({ toBe(4.0) }))
                }
                test("(1.0, 2.0), (4.0, 3.0, 4.0)") {
                    fluent.containsFun(group({ toBe(1.0) }, { toBe(2.0) }), group({ toBe(4.0) }, { toBe(3.0) }, { toBe(4.0) }))
                }
                test("[$isLessThanFun(2.1) && $isGreaterThanFun(1.0), $isLessThanFun(2.0)], [$isGreaterThanFun(3.0), $isGreaterThanFun(2.0), $isGreaterThanFun(1.0) && $isLessThanFun(5.0)]") {
                    fluent.containsFun(group({ isLessThan(2.1).and.isGreaterThan(1.0) }, { isLessThan(2.0) }), group({ isGreaterThan(3.0) }, { isGreaterThan(2.0) }, { isGreaterThan(1.0); isLessThan(5.0) }))
                }
            }

            describe("error cases (throws AssertionError)") {

                test("(4.0, 1.0), (2.0, 3.0, 4.0) -- wrong order") {
                    expect {
                        fluent.containsFun(group({ toBe(4.0) }, { toBe(1.0) }), group({ toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) }))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexFail(0, 1, listOf(1.0, 2.0),
                                failAfterFail("$toBeDescr: 4.0"),
                                successAfterFail("$toBeDescr: 1.0"),
                                successSizeAfterFail(2),
                                mismatchesAfterFail(2.0)
                            )
                            indexFail(2, 4, listOf(3.0, 4.0, 4.0),
                                failAfterFail("$toBeDescr: 2.0"),
                                successAfterFail("$toBeDescr: 3.0"),
                                successAfterFail("$toBeDescr: 4.0"),
                                successSizeAfterFail(3),
                                mismatchesAfterFail(4.0)
                            )
                            containsRegex(size(indentBulletPoint, successfulBulletPoint, 5, 5))
                        }
                    }
                }

                test("[$isLessThanFun(2.1), $isLessThanFun(2.0)], (4.0, 3.0, 4.0) -- first win also applies here, $isLessThanFun(2.1) matches 1.0 and not 2.0") {
                    expect {
                        fluent.containsFun(group({ isLessThan(2.1) }, { isLessThan(2.0) }),  group({ toBe(4.0) }, { toBe(3.0) }, { toBe(4.0) }))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexFail(0, 1, listOf(1.0, 2.0),
                                successAfterFail("$isLessThanDescr: 2.1"),
                                failAfterFail("$isLessThanDescr: 2.0"),
                                successSizeAfterFail(2),
                                mismatchesAfterFail(2.0)
                            )
                            indexSuccess(2, 4, listOf(3.0, 4.0, 4.0),
                                successAfterSuccess("$toBeDescr: 4.0"),
                                successAfterSuccess("$toBeDescr: 3.0"),
                                successAfterSuccess("$toBeDescr: 4.0"),
                                successSizeAfterFail(3)
                            )
                            containsRegex(size(indentBulletPoint, successfulBulletPoint, 5, 5))
                        }
                    }
                }

                test("(1.0), (4.0, 3.0, 2.0) -- 4.0 was missing") {
                    expect {
                        fluent.containsFun(group({ toBe(1.0) }), group({ toBe(4.0) }, { toBe(2.0) }, { toBe(3.0) }))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexSuccess(0, 1.0, "$toBeDescr: 1.0")
                            indexSuccess(1, 3, listOf(2.0, 3.0, 4.0),
                                successAfterSuccess("$toBeDescr: 4.0", "$toBeDescr: 2.0", "$toBeDescr: 3.0"),
                                successSizeAfterSuccess(3)
                            )
                            containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 4))
                            containsRegex(additional(4 to 4.0))
                        }
                    }
                }

                test("(1.0), (4.0) -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        fluent.containsFun(group({ toBe(1.0) }), group({ toBe(4.0) }))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexSuccess(0, 1.0, "$toBeDescr: 1.0")
                            indexFail(1, 2.0, "$toBeDescr: 4.0")
                            containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 2))
                            containsRegex(additional(2 to 3.0, 3 to 4.0, 4 to 4.0))
                        }
                    }
                }
                test("(1.0, 3.0), (5.0) -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        fluent.containsFun(group({ toBe(1.0) }, { toBe(3.0) }), group({ toBe(5.0) }))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexFail(0, 1, listOf(1.0, 2.0),
                                successAfterFail("$toBeDescr: 1.0"),
                                failAfterFail("$toBeDescr: 3.0"),
                                successSizeAfterFail(2),
                                mismatchesAfterFail(2.0)
                            )
                            indexFail(2, 3.0, "$toBeDescr: 5.0")
                            containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 3))
                            containsRegex(additional(3 to 4.0, 4 to 4.0))
                        }
                    }
                }
                test("(4.0, 1.0, 3.0, 2.0), (5.0, 4.0) -- 5.0 too much") {
                    expect {
                        fluent.containsFun(group({ toBe(4.0) }, { toBe(1.0) }, { toBe(3.0) }, { toBe(2.0) }), group({ toBe(5.0) }, { toBe(4.0) }))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexSuccess(0, 3, listOf(1.0, 2.0, 3.0, 4.0),
                                successAfterSuccess("$toBeDescr: 4.0", "$toBeDescr: 1.0", "$toBeDescr: 3.0", "$toBeDescr: 2.0"),
                                successSizeAfterSuccess(4)
                            )
                            indexFail(4, 5, listOf(4.0),
                                failAfterFail("$toBeDescr: 5.0"),
                                successAfterFail("$toBeDescr: 4.0"),
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
