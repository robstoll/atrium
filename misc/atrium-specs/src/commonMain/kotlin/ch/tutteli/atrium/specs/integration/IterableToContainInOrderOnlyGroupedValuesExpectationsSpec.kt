package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCollectionExpectation
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

abstract class IterableToContainInOrderOnlyGroupedValuesExpectationsSpec(
    toContainInOrderOnlyGroupedValues: Fun5<
        Iterable<Double>, Group<Double>,
        Group<Double>,
        Array<out Group<Double>>,
        InOrderOnlyReportingOptions.() -> Unit,
        InAnyOrderOnlyReportingOptions.() -> Unit
        >,
    groupFactory: (Array<out Double>) -> Group<Double>,
    toContainInOrderOnlyGroupedNullableValues: Fun5<
        Iterable<Double?>,
        Group<Double?>,
        Group<Double?>,
        Array<out Group<Double?>>,
        InOrderOnlyReportingOptions.() -> Unit,
        InAnyOrderOnlyReportingOptions.() -> Unit
        >,
    nullableGroupFactory: (Array<out Double?>) -> Group<Double?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    fun context(vararg doubles: Double) = groupFactory(doubles.toTypedArray())
    fun nullableGroup(vararg assertionCreators: Double?) = nullableGroupFactory(assertionCreators)

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInOrderOnlyGroupedValues.forSubjectLess(
            context(2.5),
            context(4.1),
            arrayOf(),
            emptyInOrderOnlyReportOptions,
            emptyInAnyOrderOnlyReportOptions
        )
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInOrderOnlyGroupedNullableValues.forSubjectLess(
            nullableGroup(2.5),
            nullableGroup(4.1),
            arrayOf(),
            emptyInOrderOnlyReportOptions,
            emptyInAnyOrderOnlyReportOptions
        )
    ) {})

    val toEqualWithFeature = "$indentFeatureArrow$featureBulletPoint$toEqualDescr"
    val toEqualAfterSuccess = "$indentRootBulletPoint$indentSuccessfulBulletPoint$toEqualWithFeature"
    val toEqualAfterFailing = "$indentRootBulletPoint$indentFailingBulletPoint$toEqualWithFeature"


    fun element(prefix: String, bulletPoint: String, expected: Array<out Double?>) =
        expected.joinToString(".*$separator") { "$prefix\\Q$bulletPoint$anElementWhichEquals: $it\\E" }

    fun size(prefix: String, bulletPoint: String, actual: Int?, expected: Int) =
        "$prefix\\Q$bulletPoint\\E${featureArrow}${DescriptionCollectionExpectation.SIZE.getDefault()}:${actual?.let { " $it" } ?: ""}[^:]+: $expected"

    val afterFail = "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    val additionalElementsFail = "$indentRootBulletPoint$indentFailingBulletPoint"
    fun failAfterFail(vararg expected: Double?, withBulletPoint: Boolean = true) =
        element(afterFail, if (withBulletPoint) failingBulletPoint else listBulletPoint, expected)

    fun successAfterFail(vararg expected: Double?) = element(afterFail, successfulBulletPoint, expected)

    fun sizeCheck(actual: Int?, expected: Int, bulletPoint: String = featureBulletPoint) = size(
        "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow", bulletPoint, actual, expected
    )

    fun Expect<String>.notToContainIndex(from: Int, to: Int) =
        notToContain.regex("\\Q${DescriptionIterableLikeExpectation.INDEX.getDefault().format("$from..$to")}")

    fun <T> mismatchesWarning(msg: String, values: Array<out T>, act: (T) -> String) =
        "$afterFail\\Q$warningBulletPoint$msg\\E: $separator" +
            values.joinToString(".*$separator") { "$afterFail$indentWarningBulletPoint$listBulletPoint${act(it)}" }

    fun <T> additionalElementsWarning(msg: String, values: Array<out T>, act: (T) -> String) =
        "$additionalElementsFail\\Q$warningBulletPoint$msg\\E: $separator" +
            values.joinToString(".*$separator") {
                "$additionalElementsFail$indentWarningBulletPoint$listBulletPoint${act(it)}"
            }

    fun mismatchesAfterFail(vararg mismatched: Double) =
        mismatchesWarning(mismatches, mismatched.toTypedArray()) { "$it" }

    fun additional(vararg entryWithValue: Pair<Int, Double>) =
        additionalElementsWarning(additionalElements, entryWithValue) { "${elementWithIndex(it.first)}: ${it.second}" }


    val afterSuccess = "$indentRootBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun successAfterSuccess(vararg expected: Double?) = element(afterSuccess, successfulBulletPoint, expected)
    fun failAfterSuccess(vararg expected: Double?) = element(afterSuccess, failingBulletPoint, expected)


    fun Expect<String>.indexSuccess(index: Int, expected: Double): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(index)}: $expected\\E.*$separator" +
                "$toEqualAfterSuccess: $expected"
        )
    }

    fun Expect<String>.indexSuccess(
        fromIndex: Int,
        toIndex: Int,
        actual: List<Double?>,
        sizeCheck: String,
        vararg expected: String
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$sizeCheck.*$separator" +
                "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$toContainInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")

        )
    }

    fun Expect<String>.indexFail(
        index: Int,
        actual: Any,
        expected: Double,
        withBulletPoint: Boolean = true
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$toEqualAfterFailing: $expected"
        )
    }

    fun Expect<String>.indexFail(
        fromIndex: Int,
        toIndex: Int,
        actual: List<Double?>,
        sizeCheck: String?,
        vararg expected: String,
        withBulletPoint: Boolean = true
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${
                index(fromIndex, toIndex)
            }: $actual\\E.*$separator" +
                (sizeCheck?.let { "$sizeCheck.*$separator" } ?: "") +
                "$indentRootBulletPoint${if (withBulletPoint) indentFailingBulletPoint else indentListBulletPoint}$indentFeatureArrow$featureBulletPoint$toContainInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")
        )
    }

    fun Expect<String>.indexNonExisting(index: Int, expected: Double, withBulletPoint: Boolean = true): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${index(index)}: $sizeExceeded\\E.*$separator" +
                "$afterFail$explanatoryBulletPoint$toEqualDescr: $expected"
        )
    }

    fun Expect<String>.indexNonExisting(
        fromIndex: Int, toIndex: Int,
        sizeCheck: String,
        vararg expected: Double,
        withBulletPoint: Boolean = true
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${
                index(fromIndex, toIndex)
            }: $sizeExceeded\\E.*$separator" +
                "$afterFail$sizeCheck.*$separator" +
                "$indentRootBulletPoint${if (withBulletPoint) indentFailingBulletPoint else indentListBulletPoint}$indentFeatureArrow$indentFeatureBulletPoint$explanatoryBulletPoint$toContainInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator") { ".*$anElementWhichEquals: $it" }
        )
    }


    nonNullableCases(
        describePrefix,
        toContainInOrderOnlyGroupedValues,
        toContainInOrderOnlyGroupedNullableValues
    ) { toContainFunArr ->

        fun Expect<Iterable<Double>>.toContainFun(
            t1: Group<Double>,
            t2: Group<Double>,
            vararg tX: Group<Double>,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions,
            reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainFunArr(t1, t2, tX, report, reportInGroup)

        context("throws an $illegalArgumentException") {
            it("if an empty group is given as first parameter") {
                expect {
                    expect(oneToFour()).toContainFun(context(), context(-1.2))
                }.toThrow<IllegalArgumentException> { messageToContain("a group of values cannot be empty") }
            }
            it("if an empty group is given as second parameter") {
                expect {
                    expect(oneToFour()).toContainFun(context(1.2), context())
                }.toThrow<IllegalArgumentException> { messageToContain("a group of values cannot be empty") }
            }
            it("if an empty group is given as third parameter") {
                expect {
                    expect(oneToFour()).toContainFun(context(1.2), context(4.3), context())
                }.toThrow<IllegalArgumentException> { messageToContain("a group of values cannot be empty") }
            }
            it("if an empty group is given as fourth parameter") {
                expect {
                    expect(oneToFour()).toContainFun(context(1.2), context(4.3), context(5.7), context())
                }.toThrow<IllegalArgumentException> { messageToContain("a group of values cannot be empty") }
            }
        }

        context("empty collection") {
            it("(1.0), (1.2) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(context(1.0), context(1.2))
                }.toThrow<AssertionError> {
                    message {
                        toContainSize(0, 2)
                        toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                        indexNonExisting(0, 1.0)
                        indexNonExisting(1, 1.2)
                        notToContain(additionalElements)
                    }
                }
            }
        }

        context("iterable ${oneToFour().toList()}") {

            context("happy case") {
                it("(1.0), (2.0, 3.0), (4.0, 4.0)") {
                    expect(oneToFour()).toContainFun(context(1.0), context(2.0, 3.0), context(4.0, 4.0))
                }
                it("(2.0, 1.0), (4.0, 3.0), (4.0)") {
                    expect(oneToFour()).toContainFun(context(2.0, 1.0), context(4.0, 3.0), context(4.0))
                }
                it("(2.0, 3.0, 1.0), (4.0), (4.0)") {
                    expect(oneToFour()).toContainFun(context(2.0, 3.0, 1.0), context(4.0), context(4.0))
                }
                it("(1.0, 2.0), (4.0, 3.0, 4.0)") {
                    expect(oneToFour()).toContainFun(context(1.0, 2.0), context(4.0, 3.0, 4.0))
                }
            }

            context("error cases (throws AssertionError)") {

                it("(4.0, 1.0), (2.0, 3.0, 4.0) -- wrong order") {
                    expect {
                        expect(oneToFour()).toContainFun(context(4.0, 1.0), context(2.0, 3.0, 4.0))
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
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
                            notToContain(size(indentRootBulletPoint, successfulBulletPoint, 5, 5))
                        }
                    }
                }

                it("(1.0), (4.0, 3.0, 2.0) -- 4.0 was missing") {
                    expect {
                        expect(oneToFour()).toContainFun(context(1.0), context(4.0, 2.0, 3.0))
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                            indexSuccess(0, 1.0)
                            indexSuccess(
                                1, 3, listOf(2.0, 3.0, 4.0),
                                sizeCheck(3, 3),
                                successAfterSuccess(4.0, 2.0, 3.0)
                            )
                            sizeCheck(5, 4)
                            toContainRegex(additional(4 to 4.0))
                        }
                    }
                }

                it("(1.0), (4.0) -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        expect(oneToFour()).toContainFun(context(1.0), context(4.0))
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                            indexSuccess(0, 1.0)
                            indexFail(1, 2.0, 4.0)
                            sizeCheck(5, 2)
                            toContainRegex(additional(2 to 3.0, 3 to 4.0, 4 to 4.0))
                        }
                    }
                }
                it("(1.0, 3.0), (5.0) -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        expect(oneToFour()).toContainFun(context(1.0, 3.0), context(5.0))
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
                                sizeCheck(2, 2),
                                successAfterFail(1.0),
                                failAfterFail(3.0),
                                mismatchesAfterFail(2.0)
                            )
                            indexFail(2, 3.0, 5.0)
                            sizeCheck(5, 3)
                            toContainRegex(additional(3 to 4.0, 4 to 4.0))
                        }
                    }
                }
                it("( 4.0, 1.0, 3.0, 2.0), (5.0, 4.0) -- 5.0 too much") {
                    expect {
                        expect(oneToFour()).toContainFun(context(4.0, 1.0, 3.0, 2.0), context(5.0, 4.0))
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
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
                            sizeCheck(5, 7) // TODO change back to 5,6
                        }
                    }
                }
            }
        }

        context("report options") {
            context("iterable ${oneToFour().toList()}") {
                it("shows only failing indices with report option `showOnlyFailing` but still default behaviour per group (i.e. only failing if more than 10") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            context(1.0),
                            context(2.0, 4.0),
                            context(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0),
                            report = { showOnlyFailing() })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 14)
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                            notToContainIndex(0, 0)
                            indexFail(
                                1, 2, listOf(2.0, 3.0),
                                null,
                                successAfterSuccess(2.0),
                                failAfterSuccess(4.0),
                                withBulletPoint = false
                            )
                            indexFail(
                                3,
                                13,
                                listOf(4.0, 4.0),
                                sizeCheck(2, 11),
                                failAfterFail(1.0, withBulletPoint = false),
                                failAfterFail(2.0, withBulletPoint = false),
                                failAfterFail(3.0, withBulletPoint = false),
                                failAfterFail(5.0, withBulletPoint = false),
                                failAfterFail(6.0, withBulletPoint = false),
                                failAfterFail(7.0, withBulletPoint = false),
                                failAfterFail(8.0, withBulletPoint = false),
                                failAfterFail(9.0, withBulletPoint = false),
                                failAfterFail(10.0, withBulletPoint = false),
                                failAfterFail(11.0, withBulletPoint = false),
                                withBulletPoint = false
                            )
                            mismatchesAfterFail(4.0)
                        }
                    }
                }

                it("shows only failing indices with report option `showOnlyFailing` and only failing elements with reportInGroup option `showOnlyFailing` ") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            context(1.0, 2.0, 3.0),
                            context(4.0, 4.0, 5.0),
                            report = { showOnlyFailing() },
                            reportInGroup = { showOnlyFailing() }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                            notToContainIndex(1, 2)
                            indexFail(
                                3, 5, listOf(4.0, 4.0),
                                sizeCheck(2, 3),
                                failAfterFail(5.0, withBulletPoint = false),
                                withBulletPoint = false
                            )
                            notToContain("$anElementWhichEquals: 4.0")
                        }
                    }
                }

                it("shows only failing indices with report option `showOnlyFailingIfMoreExpectedElementsThan(3)` because there are 5 but still all elements in group") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            context(1.0, 2.0, 3.0),
                            context(4.0, 4.0, 5.0),
                            report = { showOnlyFailingIfMoreExpectedElementsThan(3) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                            notToContainIndex(1, 2)
                            indexFail(
                                3, 5, listOf(4.0, 4.0),
                                sizeCheck(2, 3),
                                successAfterSuccess(4.0, 4.0),
                                failAfterFail(5.0),
                                withBulletPoint = false
                            )
                        }
                    }
                }
            }

            context("iterable $oneToEleven") {
                it("shows only failing indices per default as there are more than 10 expected groups, yet still summary in group") {
                    expect {
                        expect(oneToEleven).toContainFun(
                            context(1.0, 1.0),
                            context(2.0),
                            context(3.0, -3.0),
                            context(1.0, 4.0),
                            context(8.0),
                            context(10.0, 9.0),
                            context(-1.0, -2.0, 9.0),
                            context(7.0, 8.0),
                            context(9.0, -8.0),
                            context(10.0, 11.0),
                            context(12.0)
                        )
                    }.toThrow<AssertionError> {

                        message {
                            toContainSize(11, 20)
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                successAfterFail(1.0),
                                failAfterSuccess(1.0),
                                withBulletPoint = false
                            )
                            mismatchesAfterFail(2.0)
                            indexFail(2, 3.0, 2.0, withBulletPoint = false)
                            indexFail(
                                3, 4, listOf(4.0, 5.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failAfterSuccess(3.0),
                                failAfterFail(-3.0),
                                withBulletPoint = false
                            )
                            mismatchesAfterFail(4.0, 5.0)
                            indexFail(
                                5, 6, listOf(6.0, 7.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failAfterSuccess(1.0),
                                failAfterFail(4.0),
                                withBulletPoint = false
                            )
                            mismatchesAfterFail(6.0, 7.0)
                            notToContainIndex(7, 7)
                            notToContainIndex(8, 9)
                            indexFail(
                                10, 12, listOf(11.0),
                                sizeCheck(1, 3),
                                failAfterSuccess(-1.0),
                                failAfterFail(-2.0),
                                failAfterFail(9.0),
                                withBulletPoint = false
                            )
                            mismatchesAfterFail(11.0)
                            indexNonExisting(
                                13, 14,
                                size("", explanatoryBulletPoint, null, 2),
                                7.0, 8.0,
                                withBulletPoint = false
                            )
                            indexNonExisting(
                                15, 16,
                                size("", explanatoryBulletPoint, null, 2),
                                9.0, -8.0,
                                withBulletPoint = false
                            )
                            indexNonExisting(
                                17, 18,
                                size("", explanatoryBulletPoint, null, 2),
                                10.0, 11.0,
                                withBulletPoint = false
                            )
                            indexNonExisting(19, 12.0, withBulletPoint = false)
                        }
                    }
                }
                it("shows all indices with report option `showAlwaysSummary`") {
                    expect {
                        expect(oneToEleven).toContainFun(
                            context(1.0, 1.0),
                            context(2.0),
                            context(3.0, -3.0),
                            context(1.0, 4.0),
                            context(8.0),
                            context(10.0, 9.0),
                            context(-1.0, -2.0, 9.0),
                            context(7.0, 8.0),
                            context(9.0, -8.0),
                            context(10.0, 11.0),
                            context(12.0),
                            report = { showAlwaysSummary() }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(11, 20)
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
                                sizeCheck(2, 2),
                                successAfterFail(1.0),
                                failAfterSuccess(1.0)
                            )
                            mismatchesAfterFail(2.0)
                            indexFail(2, 3.0, 2.0)
                            indexFail(
                                3, 4, listOf(4.0, 5.0),
                                sizeCheck(2, 2),
                                failAfterSuccess(3.0),
                                failAfterFail(-3.0)
                            )
                            mismatchesAfterFail(4.0, 5.0)
                            indexFail(
                                5, 6, listOf(6.0, 7.0),
                                sizeCheck(2, 2),
                                failAfterSuccess(1.0),
                                failAfterFail(4.0)
                            )
                            mismatchesAfterFail(6.0, 7.0)
                            indexSuccess(7, 8.0)
                            indexSuccess(
                                8, 9, listOf(9.0, 10.0),
                                //TODO 1.2.0: https://github.com/robstoll/atrium/issues/724 should not be shown, is enough to show the indices
                                sizeCheck(2, 2),
                                successAfterSuccess(10.0),
                                successAfterSuccess(9.0)
                            )
                            indexFail(
                                10, 12, listOf(11.0),
                                sizeCheck(1, 3),
                                failAfterFail(-1.0),
                                failAfterFail(-2.0),
                                failAfterFail(9.0)
                            )
                            mismatchesAfterFail(11.0)
                            indexNonExisting(
                                13, 14,
                                size("", explanatoryBulletPoint, null, 2),
                                7.0, 8.0
                            )
                            indexNonExisting(
                                15, 16,
                                size("", explanatoryBulletPoint, null, 2),
                                9.0, -8.0
                            )
                            indexNonExisting(
                                17, 18,
                                size("", explanatoryBulletPoint, null, 2),
                                10.0, 11.0
                            )
                            indexNonExisting(19, 12.0)
                        }
                    }
                }

                it("shows only failing indices per default and only failing in group with reportInGroup `showOnlyFailing`") {
                    expect {
                        expect(oneToEleven).toContainFun(
                            context(1.0, 1.0),
                            context(2.0),
                            context(3.0, -3.0),
                            context(1.0, 4.0),
                            context(8.0),
                            context(10.0, 9.0),
                            context(-1.0, -2.0, 9.0),
                            context(7.0, 8.0),
                            context(9.0, -8.0),
                            context(10.0, 11.0),
                            context(12.0),
                            reportInGroup = { showOnlyFailing() }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(11, 20)
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failAfterFail(1.0, withBulletPoint = false),
                                withBulletPoint = false
                            )
                            mismatchesAfterFail(2.0)
                            indexFail(2, 3.0, 2.0, withBulletPoint = false)
                            indexFail(
                                3, 4, listOf(4.0, 5.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failAfterFail(3.0, withBulletPoint = false),
                                failAfterFail(-3.0, withBulletPoint = false),
                                withBulletPoint = false
                            )
                            mismatchesAfterFail(4.0, 5.0)
                            indexFail(
                                5, 6, listOf(6.0, 7.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failAfterFail(1.0, withBulletPoint = false),
                                failAfterFail(4.0, withBulletPoint = false),
                                withBulletPoint = false
                            )
                            mismatchesAfterFail(6.0, 7.0)
                            notToContainIndex(7, 7)
                            notToContainIndex(8, 9)
                            indexFail(
                                10, 12, listOf(11.0),
                                sizeCheck(1, 3),
                                failAfterFail(-1.0, withBulletPoint = false),
                                failAfterFail(-2.0, withBulletPoint = false),
                                failAfterFail(9.0, withBulletPoint = false),
                                withBulletPoint = false
                            )
                            mismatchesAfterFail(11.0)
                            indexNonExisting(
                                13, 14,
                                size("", explanatoryBulletPoint, null, 2),
                                7.0, 8.0,
                                withBulletPoint = false
                            )
                            indexNonExisting(
                                15, 16,
                                size("", explanatoryBulletPoint, null, 2),
                                9.0, -8.0,
                                withBulletPoint = false
                            )
                            indexNonExisting(
                                17, 18,
                                size("", explanatoryBulletPoint, null, 2),
                                10.0, 11.0,
                                withBulletPoint = false
                            )
                            indexNonExisting(19, 12.0, withBulletPoint = false)
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        fun Expect<Iterable<Double?>>.toContainInOrderOnlyGroupedNullableValuesFun(
            t1: Group<Double?>,
            t2: Group<Double?>,
            vararg tX: Group<Double?>,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions,
            reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInOrderOnlyGroupedNullableValues(this, t1, t2, tX, report, reportInGroup)

        describeFun(toContainInOrderOnlyGroupedNullableValues) {
            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {

                context("happy case") {
                    it("[1.0, null], [null, 3.0]") {
                        expect(null1null3()).toContainInOrderOnlyGroupedNullableValuesFun(
                            nullableGroup(1.0, null),
                            nullableGroup(null, 3.0)
                        )
                    }
                    it("[null], [null, 3.0, 1.0]") {
                        expect(null1null3()).toContainInOrderOnlyGroupedNullableValuesFun(
                            nullableGroup(null),
                            nullableGroup(null, 3.0, 1.0)
                        )
                    }
                }

                context("error cases (throws AssertionError)") {

                    it("[null, null], [3.0, 1.0] -- wrong order") {
                        expect {
                            expect(null1null3()).toContainInOrderOnlyGroupedNullableValuesFun(
                                nullableGroup(null, null),
                                nullableGroup(3.0, 1.0)
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
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
                                notToContain(size(indentRootBulletPoint, successfulBulletPoint, 4, 4))
                            }
                        }
                    }

                    it("[null, 1.0], [3.0, null, null] -- null too much") {
                        expect {
                            expect(null1null3()).toContainInOrderOnlyGroupedNullableValuesFun(
                                nullableGroup(null, 1.0),
                                nullableGroup(3.0, null, null)
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexSuccess(
                                    0, 1, listOf(null, 1.0),
                                    sizeCheck(2, 2),
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
                                toContainSize(4, 5)
                            }
                        }
                    }
                }
            }
        }
    }
})
