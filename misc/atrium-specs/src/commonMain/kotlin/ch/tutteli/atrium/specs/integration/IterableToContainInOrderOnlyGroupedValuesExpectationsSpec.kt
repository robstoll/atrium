package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCollectionProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator

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


    fun element(prefix: String, bulletPoint: String, expected: Array<out Double?>) =
        expected.joinToString(lineSeparator) { "$prefix\\Q$bulletPoint\\E$anElementWhichEquals\\s+: \\Q$it\\E" }

    fun size(prefix: String, bulletPoint: String, actual: Int?, expected: Int) =
        "$prefix\\Q$bulletPoint\\E${f}${DescriptionCollectionProof.SIZE.string}\\s+:${actual?.let { " $it" } ?: ""}[^:]+: $expected"

    val inFail = "$indentGg$indentF"
    fun failInFail(vararg expected: Double?) =
        element("$inFail$indentG", x, expected)

    fun successInFail(vararg expected: Double?) = element("$inFail$indentG", "$s ", expected)

    fun sizeFail(actual: Int, expected: Int) = size("$indentG$indentF", g, actual, expected)
    fun sizeSuccessInFail(actual: Int, expected: Int) = size("$indentG$indentF", s, actual, expected)
    fun sizeSuccessInSuccess(actual: Int, expected: Int) = size("$indentS$indentF", s, actual, expected)

    fun Expect<String>.notToContainIndex(from: Int, to: Int) =
        notToContain.regex("\\Q${DescriptionIterableLikeProof.INDEX.string.format("$from..$to")}")

    fun mismatches(vararg mismatched: Double) =
        "$inFail\\Q$bb$mismatches\\E : $lineSeparator" +
            mismatched.toTypedArray().joinToString(lineSeparator) { "$inFail$indentBb$listBulletPoint$it" }

    fun Expect<String>.additional(vararg entryWithValue: Pair<Int, Double>) =
        toContainRegex(
            "$indentG$bb$additionalElements : $lineSeparator" +
                entryWithValue
                    .joinToString(lineSeparator) {
                        "$indentG$indentBb$listBulletPoint${elementWithIndex(it.first)}\\s+: ${it.second}"
                    }
        )


    val inSuccess = "$indentG$indentS$indentF$indentS"
    fun successInSuccess(vararg expected: Double?) = element(inSuccess, s, expected)
    fun failInSuccess(vararg expected: Double?) = element(inSuccess, x, expected)


    fun Expect<String>.indexSuccess(index: Int, expected: Double): Expect<String> {
        return this.toContain.exactly(1).regex(
            "$indentG\\Q$s$f\\E${index(index)}\\s+: \\Q$expected\\E$lineSeparator" +
                "$indentG$indentS$indentF$s$toEqualDescr : $expected"
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
            "$indentG\\Q$s$f${index(fromIndex, toIndex)} : $actual\\E.*$lineSeparator" +
                "$indentG$sizeCheck$lineSeparator" +
                "$indentG$indentS$indentF\\Q$s\\E$toContainInAnyOrderOnly : $lineSeparator" +
                expected.joinToString(lineSeparator)
        )
    }

    fun Expect<String>.indexFail(
        index: Int,
        actual: Any,
        expected: Double,
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "$indentG\\Q$g\\E$f${IterableToContainEntriesSpecBase.Companion.index(index)}\\s+: \\Q$actual\\E$lineSeparator" +
                "$indentGg$indentF$x$toEqualDescr : $expected"
        )
    }

    fun Expect<String>.indexFail(
        fromIndex: Int,
        toIndex: Int,
        actual: List<Double?>,
        sizeCheck: String?,
        vararg expected: String,
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "$indentG\\Q$g$f\\E${index(fromIndex, toIndex)} : \\Q$actual\\E.*$lineSeparator" +
                (sizeCheck?.let { "$indentG$sizeCheck$lineSeparator" } ?: "") +
                "$indentGg$indentF\\Q$g\\E$toContainInAnyOrderOnly : $lineSeparator" +
                expected.joinToString(lineSeparator)
        )
    }

    fun Expect<String>.indexNonExisting(index: Int, expected: Double): Expect<String> {
        return this.toContain.exactly(1).regex(
            //TODO 1.3.0 should be x and not g
            "\\Q$g$f${index(index)} : $sizeExceeded\\E$lineSeparator" +
                "$inFail$explanatoryBulletPoint$toEqualDescr : $expected"
        )
    }

    fun Expect<String>.indexNonExisting(
        fromIndex: Int, toIndex: Int,
        sizeCheck: String,
        vararg expected: Double,
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            //TODO 1.3.0 should be $x instead of $g
            "\\Q$g$f${index(fromIndex, toIndex)} : $sizeExceeded\\E$lineSeparator" +
                "$inFail$sizeCheck$lineSeparator" +
                "$inFail$explanatoryBulletPoint$toContainInAnyOrderOnly : $lineSeparator" +
                expected.joinToString(lineSeparator) {
                    "$inFail$indentExplanatory$listBulletPoint$anElementWhichEquals\\s+: $it"
                }
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

        context("empty iterable") {
            it("(1.0), (1.2) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(context(1.0), context(1.2))
                }.toThrow<AssertionError> {
                    message {
                        toContainSize(0, 2)
                        toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
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
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
                                sizeSuccessInFail(2, 2),
                                failInFail(4.0),
                                successInFail(1.0),
                                mismatches(2.0)
                            )
                            indexFail(
                                2, 4, listOf(3.0, 4.0, 4.0),
                                sizeSuccessInFail(3, 3),
                                failInFail(2.0),
                                successInFail(3.0),
                                successInFail(4.0),
                                mismatches(4.0)
                            )
                            notToContain(size(indentRoot, s, 5, 5))
                        }
                    }
                }

                it("(1.0), (4.0, 3.0, 2.0) -- 4.0 was missing") {
                    expect {
                        expect(oneToFour()).toContainFun(context(1.0), context(4.0, 2.0, 3.0))
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 4)
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            indexSuccess(0, 1.0)
                            indexSuccess(
                                1, 3, listOf(2.0, 3.0, 4.0),
                                sizeSuccessInSuccess(3, 3),
                                successInSuccess(4.0, 2.0, 3.0)
                            )
                            additional(4 to 4.0)
                        }
                    }
                }

                it("(1.0), (4.0) -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        expect(oneToFour()).toContainFun(context(1.0), context(4.0))
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 2)
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            indexSuccess(0, 1.0)
                            indexFail(1, 2.0, 4.0)
                            additional(2 to 3.0, 3 to 4.0, 4 to 4.0)
                        }
                    }
                }
                it("(1.0, 3.0), (5.0) -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        expect(oneToFour()).toContainFun(context(1.0, 3.0), context(5.0))
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 3)
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
                                sizeSuccessInFail(2, 2),
                                successInFail(1.0),
                                failInFail(3.0),
                                mismatches(2.0)
                            )
                            indexFail(2, 3.0, 5.0)
                            additional(3 to 4.0, 4 to 4.0)
                        }
                    }
                }
                it("( 4.0, 1.0, 3.0, 2.0), (5.0, 4.0) -- 5.0 too much") {
                    expect {
                        expect(oneToFour()).toContainFun(context(4.0, 1.0, 3.0, 2.0), context(5.0, 4.0))
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            indexSuccess(
                                0, 3, listOf(1.0, 2.0, 3.0, 4.0),
                                sizeSuccessInSuccess(4, 4),
                                successInSuccess(4.0, 1.0, 3.0, 2.0)
                            )
                            indexFail(
                                4, 5, listOf(4.0),
                                sizeFail(1, 2),
                                failInFail(5.0),
                                successInFail(4.0)
                            )
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
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            notToContainIndex(0, 0)
                            indexFail(
                                1, 2, listOf(2.0, 3.0),
                                null,
                                successInFail(2.0),
                                failInFail(4.0)
                            )
                            indexFail(
                                3,
                                13,
                                listOf(4.0, 4.0),
                                sizeFail(2, 11),
                                failInFail(1.0),
                                failInFail(2.0),
                                failInFail(3.0),
                                failInFail(5.0),
                                failInFail(6.0),
                                failInFail(7.0),
                                failInFail(8.0),
                                failInFail(9.0),
                                failInFail(10.0),
                                failInFail(11.0),
                                mismatches(4.0)
                            )

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
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            notToContainIndex(0, 2)
                            indexFail(
                                3, 5, listOf(4.0, 4.0),
                                sizeFail(2, 3),
                                failInFail(5.0)
                            )
                            notToContain.regex("$anElementWhichEquals\\s+: 4.0")
                        }
                    }
                }

                it("shows only failing indices with report option `showOnlyFailingIfMoreExpectedElementsThan(1)` because we have 2 groups but still all elements in group (as no has more than 10 expected elements)") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            context(1.0, 2.0, 3.0),
                            context(4.0, 4.0, 5.0),
                            report = { showOnlyFailingIfMoreExpectedElementsThan(1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            notToContainIndex(0, 2)
                            indexFail(
                                3, 5, listOf(4.0, 4.0),
                                sizeFail(2, 3),
                                successInFail(4.0, 4.0),
                                failInFail(5.0)
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
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                successInFail(1.0),
                                failInFail(1.0),
                                mismatches(2.0)
                            )
                            indexFail(2, 3.0, 2.0)
                            indexFail(
                                3, 4, listOf(4.0, 5.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failInFail(3.0),
                                failInFail(-3.0),
                                mismatches(4.0, 5.0)
                            )
                            indexFail(
                                5, 6, listOf(6.0, 7.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failInFail(1.0),
                                failInFail(4.0),
                                mismatches(6.0, 7.0)
                            )
                            notToContainIndex(7, 7)
                            notToContainIndex(8, 9)
                            indexFail(
                                10, 12, listOf(11.0),
                                sizeFail(1, 3),
                                failInFail(-1.0),
                                failInFail(-2.0),
                                failInFail(9.0),
                                mismatches(11.0)
                            )
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
                                sizeSuccessInFail(2, 2),
                                successInFail(1.0),
                                failInFail(1.0),
                                mismatches(2.0)
                            )
                            indexFail(2, 3.0, 2.0)
                            indexFail(
                                3, 4, listOf(4.0, 5.0),
                                sizeSuccessInFail(2, 2),
                                failInFail(3.0),
                                failInFail(-3.0),
                                mismatches(4.0, 5.0)
                            )
                            indexFail(
                                5, 6, listOf(6.0, 7.0),
                                sizeSuccessInFail(2, 2),
                                failInFail(1.0),
                                failInFail(4.0),
                                mismatches(6.0, 7.0)
                            )
                            indexSuccess(7, 8.0)
                            indexSuccess(
                                8, 9, listOf(9.0, 10.0),
                                //TODO 1.3.0: https://github.com/robstoll/atrium/issues/724 should not be shown, is enough to show the indices
                                sizeSuccessInSuccess(2, 2),
                                successInSuccess(10.0),
                                successInSuccess(9.0)
                            )
                            indexFail(
                                10, 12, listOf(11.0),
                                sizeFail(1, 3),
                                failInFail(-1.0),
                                failInFail(-2.0),
                                failInFail(9.0),
                                mismatches(11.0)
                            )
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
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            indexFail(
                                0, 1, listOf(1.0, 2.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failInFail(1.0),
                                mismatches(2.0)
                            )
                            indexFail(2, 3.0, 2.0)
                            indexFail(
                                3, 4, listOf(4.0, 5.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failInFail(3.0),
                                failInFail(-3.0),
                                mismatches(4.0, 5.0)
                            )
                            indexFail(
                                5, 6, listOf(6.0, 7.0),
                                null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                failInFail(1.0),
                                failInFail(4.0),
                                mismatches(6.0, 7.0)
                            )
                            notToContainIndex(7, 7)
                            notToContainIndex(8, 9)
                            indexFail(
                                10, 12, listOf(11.0),
                                sizeFail(1, 3),
                                failInFail(-1.0),
                                failInFail(-2.0),
                                failInFail(9.0),
                                mismatches(11.0)
                            )
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexFail(
                                    0, 1, listOf(null, 1.0),
                                    sizeSuccessInFail(2, 2),
                                    successInFail(null),
                                    failInFail(null)
                                )
                                indexFail(
                                    2, 3, listOf(null, 3.0),
                                    sizeSuccessInFail(2, 2),
                                    successInFail(3.0),
                                    failInFail(1.0)
                                )
                                notToContain(size(indentRoot, s, 4, 4))
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexSuccess(
                                    0, 1, listOf(null, 1.0),
                                    sizeSuccessInSuccess(2, 2),
                                    successInSuccess(null),
                                    successInSuccess(1.0)
                                )
                                indexFail(
                                    2, 4, listOf(null, 3.0),
                                    sizeFail(2, 3),
                                    successInFail(3.0),
                                    successInFail(null),
                                    failInFail(null)
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
