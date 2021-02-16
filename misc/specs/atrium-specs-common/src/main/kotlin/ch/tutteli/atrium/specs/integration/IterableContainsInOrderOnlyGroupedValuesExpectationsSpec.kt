package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion

abstract class IterableContainsInOrderOnlyGroupedValuesExpectationsSpec(
    containsInOrderOnlyGroupedValues: Fun3<Iterable<Double>, Group<Double>, Group<Double>, Array<out Group<Double>>>,
    groupFactory: (Array<out Double>) -> Group<Double>,
    containsInOrderOnlyGroupedNullableValues: Fun3<Iterable<Double?>, Group<Double?>, Group<Double?>, Array<out Group<Double?>>>,
    nullableGroupFactory: (Array<out Double?>) -> Group<Double?>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
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

    val indentRootBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)
    val indentWarningBulletPoint = " ".repeat(warningBulletPoint.length)

    val toBeWithFeature = "$indentFeatureArrow$featureBulletPoint$toBeDescr"
    val toBeAfterSuccess = "$indentRootBulletPoint$indentSuccessfulBulletPoint$toBeWithFeature"
    val toBeAfterFailing = "$indentRootBulletPoint$indentFailingBulletPoint$toBeWithFeature"


    fun element(prefix: String, bulletPoint: String, expected: Array<out Double?>) =
        expected.joinToString(".*$separator") { "$prefix\\Q$bulletPoint$anElementWhichIs: $it\\E" }

    fun size(prefix: String, bulletPoint: String, actual: Int, expected: Int) =
        "$prefix\\Q$bulletPoint\\E${featureArrow}${DescriptionCollectionAssertion.SIZE.getDefault()}: $actual[^:]+: $expected"

    val afterFail = "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    val additionalElementsFail = "$indentRootBulletPoint$indentFailingBulletPoint"
    fun failAfterFail(vararg expected: Double?) = element(afterFail, failingBulletPoint, expected)

    fun successAfterFail(vararg expected: Double?) = element(afterFail, successfulBulletPoint, expected)

    fun sizeCheck(actual: Int, expected: Int) = size(
        "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow", featureBulletPoint, actual, expected)

    fun <T> mismatchesWarning(msg: String, values: Array<out T>, act: (T) -> String) =
        "$afterFail\\Q$warningBulletPoint$msg\\E: $separator" +
            values.joinToString(".*$separator") { "$afterFail$indentWarningBulletPoint$listBulletPoint${act(it)}" }

    fun <T> additionalElementsWarning(msg: String, values: Array<out T>, act: (T) -> String) =
        "$additionalElementsFail\\Q$warningBulletPoint$msg\\E: $separator" +
            values.joinToString(".*$separator") { "$additionalElementsFail$indentWarningBulletPoint$listBulletPoint${act(it)}" }

    fun mismatchesAfterFail(vararg mismatched: Double) = mismatchesWarning(mismatches, mismatched.toTypedArray()) { "$it" }

    fun additional(vararg entryWithValue: Pair<Int, Double>) =
        additionalElementsWarning(additionalElements, entryWithValue) { "${elementWithIndex(it.first)}: ${it.second}" }


    val afterSuccess = "$indentRootBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun successAfterSuccess(vararg expected: Double?) = element(afterSuccess, successfulBulletPoint, expected)


    fun Expect<String>.indexSuccess(index: Int, expected: Double): Expect<String> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(index)}: $expected\\E.*$separator" +
                "$toBeAfterSuccess: $expected"
        )
    }

    fun Expect<String>.indexSuccess(
        fromIndex: Int,
        toIndex: Int,
        actual: List<Double?>,
        sizeCheck: String,
        vararg expected: String
    ): Expect<String> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$sizeCheck.*$separator" +
                "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")

        )
    }

    fun Expect<String>.indexFail(index: Int, actual: Any, expected: Double): Expect<String> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$toBeAfterFailing: $expected"
        )
    }

    fun Expect<String>.indexFail(
        fromIndex: Int,
        toIndex: Int,
        actual: List<Double?>,
        sizeCheck: String,
        vararg expected: String
    ): Expect<String> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$sizeCheck.*$separator" +
                "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")
        )
    }

    fun Expect<String>.indexNonExisting(index: Int, expected: Double): Expect<String> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(index)}: $sizeExceeded\\E.*$separator" +
                "$afterFail$explanatoryBulletPoint$toBeDescr: $expected"
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
                        indexNonExisting(0, 1.0)
                        indexNonExisting(1, 1.2)
                        containsNot(additionalElements)
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
                                sizeCheck(2, 2),
                                failAfterFail(4.0),
                                successAfterFail(1.0),
                                mismatchesAfterFail(2.0)
                            )
                            indexFail(
                                2, 4, listOf(3.0, 4.0, 4.0),
                                sizeCheck(3, 3),
                                failAfterFail(2.0),
                                successAfterFail(3.0),
                                successAfterFail(4.0),
                                mismatchesAfterFail(4.0)
                            )
                            containsNot(size(indentRootBulletPoint, successfulBulletPoint, 5, 5))
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
                                sizeCheck(3, 3),
                                successAfterSuccess(4.0, 2.0, 3.0)
                            )
                            sizeCheck(5, 4)
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
                            sizeCheck(5, 2)
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
                                sizeCheck(2, 2),
                                successAfterFail(1.0),
                                failAfterFail(3.0),
                                mismatchesAfterFail(2.0)
                            )
                            indexFail(2, 3.0, 5.0)
                            sizeCheck(5, 3)
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
                                sizeCheck(4, 4),
                                successAfterSuccess(4.0, 1.0, 3.0, 2.0)
                            )
                            indexFail(
                                4, 5, listOf(4.0),
                                sizeCheck(1, 2),
                                failAfterFail(5.0),
                                successAfterFail(4.0)
                            )
                            sizeCheck(5, 6)
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
                                    sizeCheck(2, 2),
                                    successAfterFail(null),
                                    failAfterFail(null)
                                )
                                indexFail(
                                    2, 3, listOf(null, 3.0),
                                    sizeCheck(2, 2),
                                    successAfterFail(3.0),
                                    failAfterFail(1.0)
                                )
                                containsNot(size(indentRootBulletPoint, successfulBulletPoint, 4, 4))
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
                                    sizeCheck(2,2),
                                    successAfterSuccess(null),
                                    successAfterSuccess(1.0)
                                )
                                indexFail(
                                    2, 4, listOf(null, 3.0),
                                    sizeCheck(2, 3),
                                    successAfterFail(3.0),
                                    successAfterFail(null),
                                    failAfterFail(null)
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
