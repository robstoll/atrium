package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsInOrderOnlyGroupedValuesAssertionsSpec(
    containsInOrderOnlyGroupedValues: Fun3<Iterable<Double>, Group<Double>, Group<Double>, Array<out Group<Double>>>,
    groupFactory: (Array<out Double>) -> Group<Double>,
    containsInOrderOnlyGroupedNullableValues: Fun3<Iterable<Double?>, Group<Double?>, Group<Double?>, Array<out Group<Double?>>>,
    nullableGroupFactory: (Array<out Double?>) -> Group<Double?>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    fun context(vararg doubles: Double) = groupFactory(doubles.toTypedArray())
    fun nullableGroup(vararg assertionCreators: Double?) = nullableGroupFactory(assertionCreators)

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsInOrderOnlyGroupedValues.forSubjectLess(context(2.5), context(4.1), arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        containsInOrderOnlyGroupedNullableValues.forSubjectLess(nullableGroup(2.5), nullableGroup(4.1), arrayOf())
    ) {})

    fun Expect<Iterable<Double?>>.containsInOrderOnlyGroupedNullableValuesFun(
        t1: Group<Double?>,
        t2: Group<Double?>,
        vararg tX: Group<Double?>
    ) = containsInOrderOnlyGroupedNullableValues(this, t1, t2, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)
    val indentWarningBulletPoint = " ".repeat(warningBulletPoint.length)

    val toBeWithFeature = "$indentFeatureArrow$featureBulletPoint${TO_BE.getDefault()}"
    val toBeAfterSuccess = "$indentBulletPoint$indentSuccessfulBulletPoint$toBeWithFeature"
    val toBeAfterFailing = "$indentBulletPoint$indentFailingBulletPoint$toBeWithFeature"

    fun index(index: Int) = String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)

    fun entryWithIndex(index: Int) = String.format(entryWithIndex, index)

    fun index(fromIndex: Int, toIndex: Int) =
        String.format(DescriptionIterableAssertion.INDEX_FROM_TO.getDefault(), fromIndex, toIndex)

    fun entry(prefix: String, bulletPoint: String, expected: Array<out Double?>) =
        expected.joinToString(".*$separator") { "$prefix\\Q$bulletPoint$anEntryWhichIs: $it\\E" }

    fun size(prefix: String, bulletPoint: String, actual: Int, expected: Int) =
        "$prefix\\Q$bulletPoint\\E${featureArrow}size: $actual[^:]+: $expected"


    val afterFail = "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun failAfterFail(vararg expected: Double?) = entry(afterFail, failingBulletPoint, expected)

    fun successAfterFail(vararg expected: Double?) = entry(afterFail, successfulBulletPoint, expected)

    fun failSizeAfterFail(actual: Int, expected: Int) = size(afterFail, failingBulletPoint, actual, expected)

    fun successSizeAfterFail(size: Int) = size(afterFail, successfulBulletPoint, size, size)

    fun <T> warning(msg: String, values: Array<out T>, act: (T) -> String) =
        "$afterFail\\Q$warningBulletPoint$msg\\E: $separator" +
            values.joinToString(".*$separator") { "$afterFail$indentWarningBulletPoint$listBulletPoint${act(it)}" }

    fun mismatchesAfterFail(vararg mismatched: Double) = warning(mismatches, mismatched.toTypedArray()) { "$it" }

    fun additional(vararg entryWithValue: Pair<Int, Double>) =
        warning(additionalEntries, entryWithValue) { "${entryWithIndex(it.first)}: ${it.second}" }


    val afterSuccess = "$indentBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun successAfterSuccess(vararg expected: Double?) = entry(afterSuccess, successfulBulletPoint, expected)

    fun successSizeAfterSuccess(size: Int) = size(afterSuccess, successfulBulletPoint, size, size)


    fun Expect<String>.indexSuccess(index: Int, expected: Double): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(index)}: $expected\\E.*$separator" +
                "$toBeAfterSuccess: $expected"
        )
    }

    fun Expect<String>.indexSuccess(
        fromIndex: Int,
        toIndex: Int,
        actual: List<Double?>,
        vararg expected: String
    ): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")

        )
    }

    fun Expect<String>.indexFail(index: Int, actual: Any, expected: Double): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$toBeAfterFailing: $expected"
        )
    }

    fun Expect<String>.indexFail(
        fromIndex: Int,
        toIndex: Int,
        actual: List<Double?>,
        vararg expected: String
    ): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")
        )
    }

    nonNullableCases(
        describePrefix,
        containsInOrderOnlyGroupedValues,
        containsInOrderOnlyGroupedNullableValues
    ) { containsFunArr ->

        fun Expect<Iterable<Double>>.containsFun(t1: Group<Double>, t2: Group<Double>, vararg tX: Group<Double>) =
            containsFunArr(t1, t2, tX)

        context("throws an $illegalArgumentException") {
            it("if an empty group is given as first parameter") {
                expect {
                    expect(oneToFour()).containsFun(context(), context(-1.2))
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
            it("if an empty group is given as second parameter") {
                expect {
                    expect(oneToFour()).containsFun(context(1.2), context())
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
            it("if an empty group is given as third parameter") {
                expect {
                    expect(oneToFour()).containsFun(context(1.2), context(4.3), context())
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
            it("if an empty group is given as fourth parameter") {
                expect {
                    expect(oneToFour()).containsFun(context(1.2), context(4.3), context(5.7), context())
                }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
            }
        }

        context("empty collection") {
            it("(1.0), (1.2) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).containsFun(context(1.0), context(1.2))
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

        context("iterable ${oneToFour().toList()}") {

            context("happy case") {
                it("(1.0), (2.0, 3.0), (4.0, 4.0)") {
                    expect(oneToFour()).containsFun(context(1.0), context(2.0, 3.0), context(4.0, 4.0))
                }
                it("(2.0, 1.0), (4.0, 3.0), (4.0)") {
                    expect(oneToFour()).containsFun(context(2.0, 1.0), context(4.0, 3.0), context(4.0))
                }
                it("(2.0, 3.0, 1.0), (4.0), (4.0)") {
                    expect(oneToFour()).containsFun(context(2.0, 3.0, 1.0), context(4.0), context(4.0))
                }
                it("(1.0, 2.0), (4.0, 3.0, 4.0)") {
                    expect(oneToFour()).containsFun(context(1.0, 2.0), context(4.0, 3.0, 4.0))
                }
            }

            context("error cases (throws AssertionError)") {

                it("(4.0, 1.0), (2.0, 3.0, 4.0) -- wrong order") {
                    expect {
                        expect(oneToFour()).containsFun(context(4.0, 1.0), context(2.0, 3.0, 4.0))
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
                                failAfterFail(4.0),
                                successAfterFail(1.0),
                                successSizeAfterFail(2),
                                mismatchesAfterFail(2.0)
                            )
                            indexFail(
                                2, 4, listOf(3.0, 4.0, 4.0),
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

                it("(1.0), (4.0, 3.0, 2.0) -- 4.0 was missing") {
                    expect {
                        expect(oneToFour()).containsFun(context(1.0), context(4.0, 2.0, 3.0))
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                            indexSuccess(0, 1.0)
                            indexSuccess(
                                1, 3, listOf(2.0, 3.0, 4.0),
                                successAfterSuccess(4.0, 2.0, 3.0),
                                successSizeAfterSuccess(3)
                            )
                            containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 4))
                            containsRegex(additional(4 to 4.0))
                        }
                    }
                }

                it("(1.0), (4.0) -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        expect(oneToFour()).containsFun(context(1.0), context(4.0))
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
                it("(1.0, 3.0), (5.0) -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        expect(oneToFour()).containsFun(context(1.0, 3.0), context(5.0))
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
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
                it("( 4.0, 1.0, 3.0, 2.0), (5.0, 4.0) -- 5.0 too much") {
                    expect {
                        expect(oneToFour()).containsFun(context(4.0, 1.0, 3.0, 2.0), context(5.0, 4.0))
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                            indexSuccess(
                                0, 3, listOf(1.0, 2.0, 3.0, 4.0),
                                successAfterSuccess(4.0, 1.0, 3.0, 2.0),
                                successSizeAfterSuccess(4)
                            )
                            indexFail(
                                4, 5, listOf(4.0),
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

    nullableCases(describePrefix) {

        describeFun(containsInOrderOnlyGroupedNullableValues) {
            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {

                context("happy case") {
                    it("[1.0, null], [null, 3.0]") {
                        expect(null1null3()).containsInOrderOnlyGroupedNullableValuesFun(
                            nullableGroup(1.0, null),
                            nullableGroup(null, 3.0)
                        )
                    }
                    it("[null], [null, 3.0, 1.0]") {
                        expect(null1null3()).containsInOrderOnlyGroupedNullableValuesFun(
                            nullableGroup(null),
                            nullableGroup(null, 3.0, 1.0)
                        )
                    }
                }

                context("error cases (throws AssertionError)") {

                    it("[null, null], [3.0, 1.0] -- wrong order") {
                        expect {
                            expect(null1null3()).containsInOrderOnlyGroupedNullableValuesFun(
                                nullableGroup(null, null),
                                nullableGroup(3.0, 1.0)
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                indexFail(
                                    0, 1, listOf(null, 1.0),
                                    successAfterFail(null),
                                    failAfterFail(null),
                                    successSizeAfterFail(2)
                                )
                                indexFail(
                                    2, 3, listOf(null, 3.0),
                                    successAfterFail(3.0),
                                    failAfterFail(1.0),
                                    successSizeAfterFail(2)
                                )
                                containsRegex(size(indentBulletPoint, successfulBulletPoint, 4, 4))
                            }
                        }
                    }

                    it("[null, 1.0], [3.0, null, null] -- null too much") {
                        expect {
                            expect(null1null3()).containsInOrderOnlyGroupedNullableValuesFun(
                                nullableGroup(null, 1.0),
                                nullableGroup(3.0, null, null)
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                indexSuccess(
                                    0, 1, listOf(null, 1.0),
                                    successAfterSuccess(null),
                                    successAfterSuccess(1.0),
                                    successSizeAfterSuccess(2)
                                )
                                indexFail(
                                    2, 4, listOf(null, 3.0),
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
