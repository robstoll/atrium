package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInOrderOnlyGroupedValuesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInOrderOnlyGroupedValuesPair: Pair<String, Assert<Iterable<Double>>.(GroupWithoutNullableEntries<Double>, GroupWithoutNullableEntries<Double>, Array<out GroupWithoutNullableEntries<Double>>) -> Assert<Iterable<Double>>>,
    groupFactory: (Array<out Double>) -> GroupWithoutNullableEntries<Double>,
    containsInOrderOnlyGroupedNullableValuesPair: Pair<String, Assert<Iterable<Double?>>.(GroupWithNullableEntries<Double?>, GroupWithNullableEntries<Double?>, Array<out GroupWithNullableEntries<Double?>>) -> Assert<Iterable<Double?>>>,
    nullableGroupFactory: (Array<out Double?>) -> GroupWithNullableEntries<Double?>,
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
    fun nullableGroup(vararg assertionCreators: Double?) = nullableGroupFactory(assertionCreators)

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsInOrderOnlyGroupedValuesPair.first to mapToCreateAssertion { containsInOrderOnlyGroupedValuesPair.second(this, group(2.5), group(4.1), arrayOf()) },
        containsInOrderOnlyGroupedNullableValuesPair.first to mapToCreateAssertion { containsInOrderOnlyGroupedNullableValuesPair.second(this, nullableGroup(2.5), nullableGroup(4.1), arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsInOrderOnlyGroupedValuesPair.first, { containsInOrderOnlyGroupedValuesPair.second(this, group(2.5), group(1.2, 2.2), arrayOf()) }, listOf(2.5, 2.2, 1.2).asIterable(), listOf(2.2, 1.2, 2.5)),
        checkingTriple(containsInOrderOnlyGroupedNullableValuesPair.first, { containsInOrderOnlyGroupedNullableValuesPair.second(this, nullableGroup(2.5), nullableGroup(1.2, 2.2), arrayOf()) }, listOf(2.5, 2.2, 1.2).asIterable(), listOf(2.2, 1.2, 2.5))
    ) {})

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException

    val (containsInOrderOnlyGroupedValues, containsInOrderOnlyGroupedValuesFunArr) = containsInOrderOnlyGroupedValuesPair

    val (containsInOrderOnlyGroupedNullableValues, containsInOrderOnlyGroupedNullableValuesFunArr) = containsInOrderOnlyGroupedNullableValuesPair
    fun Assert<Iterable<Double?>>.containsInOrderOnlyGroupedNullableValuesFun(t1: GroupWithNullableEntries<Double?>, t2: GroupWithNullableEntries<Double?>, vararg tX: GroupWithNullableEntries<Double?>)
        = containsInOrderOnlyGroupedNullableValuesFunArr(t1,t2, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)
    val indentWarningBulletPoint = " ".repeat(warningBulletPoint.length)

    val anEntryWhichIs = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.getDefault()

    val toBeWithFeature = "$indentFeatureArrow$featureBulletPoint${DescriptionAnyAssertion.TO_BE.getDefault()}"
    val toBeAfterSuccess = "$indentBulletPoint$indentSuccessfulBulletPoint$toBeWithFeature"
    val toBeAfterFailing = "$indentBulletPoint$indentFailingBulletPoint$toBeWithFeature"

    fun index(index: Int)
        = String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)

    fun entryWithIndex(index: Int)
        = String.format(entryWithIndex, index)

    fun index(fromIndex: Int, toIndex: Int)
        = String.format(DescriptionIterableAssertion.INDEX_FROM_TO.getDefault(), fromIndex, toIndex)

    fun entry(prefix: String, bulletPoint: String, expected: Array<out Double?>)
        = expected.joinToString(".*$separator") { "$prefix\\Q$bulletPoint$anEntryWhichIs: $it\\E" }

    fun size(prefix: String, bulletPoint: String, actual: Int, expected: Int)
        = "$prefix\\Q$bulletPoint\\E${featureArrow}size: $actual[^:]+: $expected"


    val afterFail = "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun failAfterFail(vararg expected: Double?)
        = entry(afterFail, failingBulletPoint, expected)

    fun successAfterFail(vararg expected: Double?)
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
    fun successAfterSuccess(vararg expected: Double?)
        = entry(afterSuccess, successfulBulletPoint, expected)

    fun successSizeAfterSuccess(size: Int)
        = size(afterSuccess, successfulBulletPoint, size, size)


    fun Assert<CharSequence>.indexSuccess(index: Int, expected: Double): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(index)}: $expected\\E.*$separator" +
                "$toBeAfterSuccess: $expected")
    }

    fun Assert<CharSequence>.indexSuccess(fromIndex: Int, toIndex: Int, actual: List<Double?>, vararg expected: String): Assert<CharSequence> {
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

    fun Assert<CharSequence>.indexFail(fromIndex: Int, toIndex: Int, actual: List<Double?>, vararg expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")
        )
    }

    fun groupToNullableGroup(group: GroupWithoutNullableEntries<Double>) = nullableGroup(*group.toList().toTypedArray())

    group("$describePrefix describe non-nullable cases") {
        mapOf<String, Assert<Iterable<Double>>.(GroupWithoutNullableEntries<Double>, GroupWithoutNullableEntries<Double>, Array<out GroupWithoutNullableEntries<Double>>) -> Any>(
            containsInOrderOnlyGroupedValues to { g1, g2, gX -> containsInOrderOnlyGroupedValuesFunArr(this, g1, g2, gX) },
            containsInOrderOnlyGroupedNullableValues to { g1, g2, gX -> containsInOrderOnlyGroupedNullableValuesFunArr(this, groupToNullableGroup(g1), groupToNullableGroup(g2), gX.map { groupToNullableGroup(it)}.toTypedArray()) }
        ).forEach { (describe, containsFunArr) ->

            fun Assert<Iterable<Double>>.containsFun(t1: GroupWithoutNullableEntries<Double>, t2: GroupWithoutNullableEntries<Double>, vararg tX: GroupWithoutNullableEntries<Double>)
                = containsFunArr(t1, t2, tX)

            describeFun(describe) {
                val fluent = assert(oneToFour)
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
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                indexFail(0, sizeExceeded, 1.0)
                                indexFail(1, sizeExceeded, 1.2)
                                containsNot(additionalEntries)
                                containsSize(0, 2)
                            }
                        }
                    }
                }

                context("iterable $oneToFour") {

                    describe("happy case") {
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
                                    contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
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
                                    contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                    indexSuccess(0, 1.0)
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
                                    contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                    indexSuccess(0, 1.0)
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
                                    contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
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
                                    contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
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
        }
    }

    nullableCases(describePrefix){

        describeFun(containsInOrderOnlyGroupedNullableValues){
            val list = listOf(null, 1.0, null, 3.0)
            val fluent = verbs.checkImmediately(list)

            context("iterable $list") {

                describe("happy case") {
                    test("[1.0, null], [null, 3.0]") {
                        fluent.containsInOrderOnlyGroupedNullableValuesFun(
                            nullableGroup(1.0, null),
                            nullableGroup(null, 3.0)
                        )
                    }
                    test("[null], [null, 3.0, 1.0]") {
                        fluent.containsInOrderOnlyGroupedNullableValuesFun(
                            nullableGroup(null),
                            nullableGroup(null, 3.0, 1.0)
                        )
                    }
                }

                describe("error cases (throws AssertionError)") {

                    test("[null, null], [3.0, 1.0] -- wrong order") {
                        expect {
                            fluent.containsInOrderOnlyGroupedNullableValuesFun(
                                nullableGroup(null, null),
                                nullableGroup(3.0, 1.0))
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                indexFail(0, 1, listOf(null, 1.0),
                                    successAfterFail(null),
                                    failAfterFail(null),
                                    successSizeAfterFail(2)
                                )
                                indexFail(2, 3, listOf(null, 3.0),
                                    successAfterFail(3.0),
                                    failAfterFail(1.0),
                                    successSizeAfterFail(2)
                                )
                                containsRegex(size(indentBulletPoint, successfulBulletPoint, 4, 4))
                            }
                        }
                    }

                    test("[null, 1.0], [3.0, null, null] -- null too much") {
                        expect {
                            fluent.containsInOrderOnlyGroupedNullableValuesFun(
                                nullableGroup(null, 1.0),
                                nullableGroup(3.0, null, null)
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                indexSuccess(0, 1, listOf(null, 1.0),
                                    successAfterSuccess(null),
                                    successAfterSuccess(1.0),
                                    successSizeAfterSuccess(2)
                                )
                                indexFail(2, 4, listOf(null, 3.0),
                                    successAfterFail(3.0),
                                    successAfterFail(null),
                                    failAfterFail(null),
                                    failSizeAfterFail(2, 3)
                                )
                                containsSize(4, 5)
                            }
                        }
                    }
                }
            }
        }
    }
})
