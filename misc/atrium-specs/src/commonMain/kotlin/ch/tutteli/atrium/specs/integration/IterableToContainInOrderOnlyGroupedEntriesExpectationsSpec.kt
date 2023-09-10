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
import org.spekframework.spek2.style.specification.Suite

abstract class IterableToContainInOrderOnlyGroupedEntriesExpectationsSpec(
    toContainInOrderOnlyGroupedEntries: Fun5<
        Iterable<Double?>,
        Group<(Expect<Double>.() -> Unit)?>,
        Group<(Expect<Double>.() -> Unit)?>,
        Array<out Group<(Expect<Double>.() -> Unit)?>>,
        InOrderOnlyReportingOptions.() -> Unit,
        InAnyOrderOnlyReportingOptions.() -> Unit
        >,
    groupFactory: (Array<out (Expect<Double>.() -> Unit)?>) -> Group<(Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    fun context(vararg assertionCreators: (Expect<Double>.() -> Unit)?) = groupFactory(assertionCreators)

    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInOrderOnlyGroupedEntries.forSubjectLess(
            context({ toEqual(2.5) }),
            context({ toEqual(4.1) }),
            arrayOf(),
            emptyInOrderOnlyReportOptions,
            emptyInAnyOrderOnlyReportOptions
        )
    ) {})
    //@formatter:off
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        describePrefix, listOf(1.2, 2.0, 3.0),
        assertionCreatorSpecTriple(toContainInOrderOnlyGroupedEntries.name + " [first empty]", "$toEqualDescr: 1.2",
            { toContainInOrderOnlyGroupedEntries(this, Entry { toEqual(1.2) }, Entry { toEqual(2.0) }, arrayOf( Entry { toEqual(3.0) }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) },
            { toContainInOrderOnlyGroupedEntries(this, Entry { }, Entry { toEqual(2.0) }, arrayOf( Entry { toEqual(3.0) }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) }
        ),
        assertionCreatorSpecTriple(toContainInOrderOnlyGroupedEntries.name + " [second empty]", "$toEqualDescr: 2.0",
            { toContainInOrderOnlyGroupedEntries(this, Entry { toEqual(1.2) }, Entry { toEqual(2.0) }, arrayOf( Entry { toEqual(3.0) }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) },
            { toContainInOrderOnlyGroupedEntries(this, Entry { toEqual(1.2) }, Entry { }, arrayOf( Entry { toEqual(3.0) }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) }
        ),
        assertionCreatorSpecTriple(toContainInOrderOnlyGroupedEntries.name + " [third empty]", "$toEqualDescr: 3.0",
            { toContainInOrderOnlyGroupedEntries(this, Entry { toEqual(1.2) }, Entry { toEqual(2.0) }, arrayOf( Entry { }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) },
            { toContainInOrderOnlyGroupedEntries(this, Entry { toEqual(1.2) }, Entry { toEqual(2.0) }, arrayOf( Entry {  }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) }
        )
    ) {})
    //@formatter:on

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun Expect<Iterable<Double?>>.toContainInOrderOnlyGroupedEntriesFun(
        t1: Group<(Expect<Double>.() -> Unit)?>,
        t2: Group<(Expect<Double>.() -> Unit)?>,
        vararg tX: Group<(Expect<Double>.() -> Unit)?>,
        report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions,
        reportInGroup: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
    ) = toContainInOrderOnlyGroupedEntries(this, t1, t2, tX, report, reportInGroup)

    fun element(prefix: String, bulletPoint: String, indentRootBulletPoint: String, expected: Array<out String>) =
        expected.joinToString(".*$separator") {
            "$prefix\\Q$bulletPoint$anElementWhichNeedsDescr: \\E$separator" +
                "$prefix$indentRootBulletPoint$indentListBulletPoint$explanatoryBulletPoint$it"
        }

    fun size(prefix: String, bulletPoint: String, actual: Int?, expected: Int) =
        "$prefix\\Q$bulletPoint\\E${featureArrow}${DescriptionCollectionExpectation.SIZE.getDefault()}:${actual?.let { " $it" } ?: ""}[^:]+: $expected"

    fun sizeCheck(actual: Int, expected: Int) = size(
        "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow", featureBulletPoint, actual, expected
    )

    val afterFail = "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun failAfterFail(vararg expected: String, withBulletPoint: Boolean = true) =
        element(afterFail, if (withBulletPoint) failingBulletPoint else listBulletPoint, indentFailingBulletPoint, expected)

    fun successAfterFail(vararg expected: String) =
        element(afterFail, successfulBulletPoint, indentSuccessfulBulletPoint, expected)


    fun Expect<String>.notToContainIndex(from: Int, to: Int) =
        notToContain.regex("\\Q${DescriptionIterableLikeExpectation.INDEX.getDefault().format("$from..$to")}")

    val additionalElementsFail = "$indentRootBulletPoint$indentFailingBulletPoint"

    fun <T> additionalElementsWarning(msg: String, values: Array<out T>, act: (T) -> String) =
        "$additionalElementsFail\\Q$warningBulletPoint$msg\\E: $separator" +
            values.joinToString(".*$separator") {
                "$additionalElementsFail$indentWarningBulletPoint$listBulletPoint${
                    act(
                        it
                    )
                }"
            }

    fun <T> warning(msg: String, values: Array<out T>, act: (T) -> String) =
        "$afterFail\\Q$warningBulletPoint$msg\\E: $separator" +
            values.joinToString(".*$separator") { "$afterFail$indentWarningBulletPoint$listBulletPoint${act(it)}" }

    fun mismatchesAfterFail(vararg mismatched: Double) = warning(mismatches, mismatched.toTypedArray()) { "$it" }

    fun additional(vararg entryWithValue: Pair<Int, Double>) =
        additionalElementsWarning(additionalElements, entryWithValue) { "${elementWithIndex(it.first)}: ${it.second}" }


    val afterSuccess = "$indentRootBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun successAfterSuccess(vararg expected: String) =
        element(afterSuccess, successfulBulletPoint, indentSuccessfulBulletPoint, expected)
    fun failAfterSuccess(vararg expected: String) = element(afterSuccess, failingBulletPoint, indentFailingBulletPoint, expected)


    fun Expect<String>.indexSuccess(index: Int, actual: Any, expected: String): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$indentRootBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$featureBulletPoint$expected"
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
        expected: String,
        withBulletPoint: Boolean = true
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$expected"
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

    fun Expect<String>.indexNonExisting(index: Int, expected: String, withBulletPoint: Boolean = true): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${IterableToContainSpecBase.index(index)}: $sizeExceeded\\E.*$separator" +
                "$afterFail$explanatoryBulletPoint$expected"
        )
    }

    fun Expect<String>.indexNonExisting(
        fromIndex: Int, toIndex: Int,
        sizeCheck: String,
        vararg expected: String,
        withBulletPoint: Boolean = true
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${
                index(fromIndex, toIndex)
            }: $sizeExceeded\\E.*$separator" +
                "$afterFail$sizeCheck.*$separator" +
                "$indentRootBulletPoint${if (withBulletPoint) indentFailingBulletPoint else indentListBulletPoint}$indentFeatureArrow$indentFeatureBulletPoint$explanatoryBulletPoint$toContainInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator") { ".*$anElementWhichNeedsDescr:.*$separator.*$it" }
        )
    }


    describeFun(toContainInOrderOnlyGroupedEntries) {
        context("describe non-nullable cases") {

            context("throws an $illegalArgumentException") {
                it("if an empty group is given as first parameter") {
                    expect {
                        expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                            context(),
                            context({ toEqual(-1.2) })
                        )
                    }.toThrow<IllegalArgumentException> { messageToContain("a group of values cannot be empty") }
                }
                it("if an empty group is given as second parameter") {
                    expect {
                        expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toEqual(1.2) }),
                            context()
                        )
                    }.toThrow<IllegalArgumentException> { messageToContain("a group of values cannot be empty") }
                }
                it("if an empty group is given as third parameter") {
                    expect {
                        expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toEqual(1.2) }),
                            context({ toEqual(4.3) }),
                            context()
                        )
                    }.toThrow<IllegalArgumentException> { messageToContain("a group of values cannot be empty") }
                }
                it("if an empty group is given as fourth parameter") {
                    expect {
                        expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toEqual(1.2) }),
                            context({ toEqual(4.3) }),
                            context({ toEqual(5.7) }),
                            context()
                        )
                    }.toThrow<IllegalArgumentException> { messageToContain("a group of values cannot be empty") }
                }
            }

            context("empty collection") {
                val fluentEmpty = expect(setOf<Double?>().asIterable())
                it("(1.0), (1.2) throws AssertionError") {
                    expect {
                        fluentEmpty.toContainInOrderOnlyGroupedEntriesFun(context({ toEqual(1.0) }), context({
                            toEqual(
                                1.2
                            )
                        }))
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                            indexNonExisting(0, "$toEqualDescr: 1.0")
                            indexNonExisting(1, "$toEqualDescr: 1.2")
                            notToContain(additionalElements)
                            toContainSize(0, 2)
                        }
                    }
                }
            }

            context("iterable ${oneToFour().toList()}") {

                context("happy case") {
                    it("(1.0), (2.0, 3.0), (4.0, 4.0)") {
                        expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toEqual(1.0) }),
                            context({ toEqual(2.0) }, { toEqual(3.0) }),
                            context({ toEqual(4.0) }, { toEqual(4.0) })
                        )
                    }
                    it("(2.0, 1.0), (4.0, 3.0), (4.0)") {
                        expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toEqual(2.0) }, { toEqual(1.0) }),
                            context({ toEqual(4.0) }, { toEqual(3.0) }),
                            context({ toEqual(4.0) })
                        )
                    }
                    it("(2.0, 3.0, 1.0), (4.0), (4.0)") {
                        expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toEqual(2.0) }, { toEqual(3.0) }, { toEqual(1.0) }),
                            context({ toEqual(4.0) }),
                            context({ toEqual(4.0) })
                        )
                    }
                    it("(1.0, 2.0), (4.0, 3.0, 4.0)") {
                        expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toEqual(1.0) }, { toEqual(2.0) }),
                            context({ toEqual(4.0) }, { toEqual(3.0) }, { toEqual(4.0) })
                        )
                    }
                    it("[$toBeLessThanFun(2.1) && $toBeGreaterThanFun(1.0), $toBeLessThanFun(2.0)], [$toBeGreaterThanFun(3.0), $toBeGreaterThanFun(2.0), $toBeGreaterThanFun(1.0) && $toBeLessThanFun(5.0)]") {
                        expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toBeLessThan(2.1).and.toBeGreaterThan(1.0) }, { toBeLessThan(2.0) }),
                            context(
                                { toBeGreaterThan(3.0) },
                                { toBeGreaterThan(2.0) },
                                { toBeGreaterThan(1.0); toBeLessThan(5.0) })
                        )
                    }
                }

                context("error cases (throws AssertionError)") {

                    it("(4.0, 1.0), (2.0, 3.0, 4.0) -- wrong order") {
                        expect {
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(4.0) }, { toEqual(1.0) }),
                                context({ toEqual(2.0) }, { toEqual(3.0) }, { toEqual(4.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    sizeCheck(2, 2),
                                    failAfterFail("$toEqualDescr: 4.0"),
                                    successAfterFail("$toEqualDescr: 1.0"),
                                    mismatchesAfterFail(2.0)
                                )
                                indexFail(
                                    2, 4, listOf(3.0, 4.0, 4.0),
                                    sizeCheck(3, 3),
                                    failAfterFail("$toEqualDescr: 2.0"),
                                    successAfterFail("$toEqualDescr: 3.0"),
                                    successAfterFail("$toEqualDescr: 4.0"),
                                    mismatchesAfterFail(4.0)
                                )
                                notToContain(size(indentRootBulletPoint, successfulBulletPoint, 5, 5))
                            }
                        }
                    }


                    it("[$toBeLessThanFun(2.1), $toBeLessThanFun(2.0)], (4.0, 3.0, 4.0) -- first win also applies here, $toBeLessThanFun(2.1) matches 1.0 and not 2.0") {
                        expect {
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toBeLessThan(2.1) }, { toBeLessThan(2.0) }),
                                context({ toEqual(4.0) }, { toEqual(3.0) }, { toEqual(4.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    sizeCheck(2, 2),
                                    successAfterFail("$toBeLessThanDescr: 2.1"),
                                    failAfterFail("$toBeLessThanDescr: 2.0"),
                                    mismatchesAfterFail(2.0)
                                )
                                indexSuccess(
                                    2, 4, listOf(3.0, 4.0, 4.0),
                                    sizeCheck(3, 3),
                                    successAfterSuccess("$toEqualDescr: 4.0"),
                                    successAfterSuccess("$toEqualDescr: 3.0"),
                                    successAfterSuccess("$toEqualDescr: 4.0")
                                )
                                notToContain(size(indentRootBulletPoint, successfulBulletPoint, 5, 5))
                            }
                        }
                    }

                    it("(1.0), (4.0, 3.0, 2.0) -- 4.0 was missing") {
                        expect {
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(1.0) }),
                                context({ toEqual(4.0) }, { toEqual(2.0) }, { toEqual(3.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContainSize(5, 4)
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexSuccess(0, 1.0, "$toEqualDescr: 1.0")
                                indexSuccess(
                                    1, 3, listOf(2.0, 3.0, 4.0),
                                    sizeCheck(3, 3),
                                    successAfterSuccess(
                                        "$toEqualDescr: 4.0",
                                        "$toEqualDescr: 2.0",
                                        "$toEqualDescr: 3.0"
                                    )
                                )
                                toContainRegex(additional(4 to 4.0))
                            }
                        }
                    }

                    it("(1.0), (4.0) -- 2.0, 3.0 and 4.0 was missing") {
                        expect {
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(context({
                                toEqual(
                                    1.0
                                )
                            }), context({ toEqual(4.0) }))
                        }.toThrow<AssertionError> {
                            message {
                                toContainSize(5, 2)
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexSuccess(0, 1.0, "$toEqualDescr: 1.0")
                                indexFail(1, 2.0, "$toEqualDescr: 4.0")
                                toContainRegex(additional(2 to 3.0, 3 to 4.0, 4 to 4.0))
                            }
                        }
                    }
                    it("(1.0, 3.0), (5.0) -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                        expect {
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(1.0) }, { toEqual(3.0) }),
                                context({ toEqual(5.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    sizeCheck(2, 2),
                                    successAfterFail("$toEqualDescr: 1.0"),
                                    failAfterFail("$toEqualDescr: 3.0"),
                                    mismatchesAfterFail(2.0)
                                )
                                indexFail(2, 3.0, "$toEqualDescr: 5.0")
                                toContainRegex(additional(3 to 4.0, 4 to 4.0))
                            }
                        }
                    }
                    it("(4.0, 1.0, 3.0, 2.0), (5.0, 4.0) -- 5.0 too much") {
                        expect {
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(4.0) }, { toEqual(1.0) }, { toEqual(3.0) }, { toEqual(2.0) }),
                                context({ toEqual(5.0) }, { toEqual(4.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContainSize(5, 6)
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexSuccess(
                                    0, 3, listOf(1.0, 2.0, 3.0, 4.0),
                                    sizeCheck(4, 4),
                                    successAfterSuccess(
                                        "$toEqualDescr: 4.0",
                                        "$toEqualDescr: 1.0",
                                        "$toEqualDescr: 3.0",
                                        "$toEqualDescr: 2.0"
                                    )
                                )
                                indexFail(
                                    4, 5, listOf(4.0),
                                    sizeCheck(1, 2),
                                    failAfterFail("$toEqualDescr: 5.0"),
                                    successAfterFail("$toEqualDescr: 4.0")
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
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(1.0) }),
                                context({ toEqual(2.0) }, { toEqual(4.0) }),
                                context(
                                    { toEqual(1.0) },
                                    { toEqual(2.0) },
                                    { toEqual(3.0) },
                                    { toEqual(4.0) },
                                    { toEqual(5.0) },
                                    { toEqual(6.0) },
                                    { toEqual(7.0) },
                                    { toEqual(8.0) },
                                    { toEqual(9.0) },
                                    { toEqual(10.0) },
                                    { toEqual(11.0) }
                                ),
                                report = { showOnlyFailing() }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContainSize(5, 14)
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                notToContainIndex(0, 0)
                                indexFail(
                                    1, 2, listOf(2.0, 3.0),
                                    null,
                                    successAfterSuccess("$toEqualDescr: 2.0"),
                                    failAfterSuccess("$toEqualDescr: 4.0"),
                                    withBulletPoint = false
                                )
                                indexFail(
                                    3,
                                    13,
                                    listOf(4.0, 4.0),
                                    sizeCheck(2, 11),
                                    failAfterFail("$toEqualDescr: 1.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 2.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 3.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 5.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 6.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 7.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 8.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 9.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 10.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 11.0", withBulletPoint = false),
                                    withBulletPoint = false
                                )
                                mismatchesAfterFail(4.0)
                            }
                        }
                    }

                    it("shows only failing indices with report option `showOnlyFailing` and only failing elements with reportInGroup option `showOnlyFailing` ") {
                        expect {
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(1.0) }, { toEqual(2.0) }, { toEqual(3.0) }),
                                context({ toEqual(4.0) }, { toEqual(4.0) }, { toEqual(5.0) }),
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
                                    failAfterFail("$toEqualDescr: 5.0", withBulletPoint = false),
                                    withBulletPoint = false
                                )
                                notToContain("$anElementWhichEquals: 4.0")
                            }
                        }
                    }

                    it("shows only failing indices with report option `showOnlyFailingIfMoreExpectedElementsThan(3)` because there are 5 but still all elements in group") {
                        expect {
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(1.0) }, { toEqual(2.0) }, { toEqual(3.0) }),
                                context({ toEqual(4.0) }, { toEqual(4.0) }, { toEqual(5.0) }),
                                report = { showOnlyFailingIfMoreExpectedElementsThan(3) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContainSize(5, 6)
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                notToContainIndex(1, 2)
                                indexFail(
                                    3, 5, listOf(4.0, 4.0),
                                    sizeCheck(2, 3),
                                    successAfterSuccess("$toEqualDescr: 4.0", "$toEqualDescr: 4.0"),
                                    failAfterFail("$toEqualDescr: 5.0"),
                                    withBulletPoint = false
                                )
                            }
                        }
                    }
                }

                context("iterable $oneToEleven") {
                    it("shows only failing indices per default as there are more than 10 expected groups, yet still summary in group") {
                        expect {
                            expect(oneToEleven as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(1.0) }, { toEqual(1.0) }),
                                context({ toEqual(2.0) }),
                                context({ toEqual(3.0) }, { toEqual(-3.0) }),
                                context({ toEqual(1.0) }, { toEqual(4.0) }),
                                context({ toEqual(8.0) }),
                                context({ toEqual(10.0) }, { toEqual(9.0) }),
                                context({ toEqual(-1.0) }, { toEqual(-2.0) }, { toEqual(9.0) }),
                                context({ toEqual(7.0) }, { toEqual(8.0) }),
                                context({ toEqual(9.0) }, { toEqual(-8.0) }),
                                context({ toEqual(10.0) }, { toEqual(11.0) }),
                                context({ toEqual(12.0) })
                            )
                        }.toThrow<AssertionError> {

                            message {
                                toContainSize(11, 20)
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    successAfterFail("$toEqualDescr: 1.0"),
                                    failAfterSuccess("$toEqualDescr: 1.0"),
                                    withBulletPoint = false
                                )
                                mismatchesAfterFail(2.0)
                                indexFail(2, 3.0, "$toEqualDescr: 2.0", withBulletPoint = false)
                                indexFail(
                                    3, 4, listOf(4.0, 5.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failAfterSuccess("$toEqualDescr: 3.0"),
                                    failAfterFail("$toEqualDescr: -3.0"),
                                    withBulletPoint = false
                                )
                                mismatchesAfterFail(4.0, 5.0)
                                indexFail(
                                    5, 6, listOf(6.0, 7.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failAfterSuccess("$toEqualDescr: 1.0"),
                                    failAfterFail("$toEqualDescr: 4.0"),
                                    withBulletPoint = false
                                )
                                mismatchesAfterFail(6.0, 7.0)
                                notToContainIndex(7, 7)
                                notToContainIndex(8, 9)
                                indexFail(
                                    10, 12, listOf(11.0),
                                    sizeCheck(1, 3),
                                    failAfterSuccess("$toEqualDescr: -1.0"),
                                    failAfterFail("$toEqualDescr: -2.0"),
                                    failAfterFail("$toEqualDescr: 9.0"),
                                    withBulletPoint = false
                                )
                                mismatchesAfterFail(11.0)
                                indexNonExisting(
                                    13, 14,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr: 7.0", "$toEqualDescr: 8.0",
                                    withBulletPoint = false
                                )
                                indexNonExisting(
                                    15, 16,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr: 9.0", "$toEqualDescr: -8.0",
                                    withBulletPoint = false
                                )
                                indexNonExisting(
                                    17, 18,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr: 10.0", "$toEqualDescr: 11.0",
                                    withBulletPoint = false
                                )
                                indexNonExisting(19, "$toEqualDescr: 12.0", withBulletPoint = false)
                            }
                        }
                    }
                    it("shows all indices with report option `showAlwaysSummary`") {
                        expect {
                            expect(oneToEleven as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(1.0) }, { toEqual(1.0) }),
                                context({ toEqual(2.0) }),
                                context({ toEqual(3.0) }, { toEqual(-3.0) }),
                                context({ toEqual(1.0) }, { toEqual(4.0) }),
                                context({ toEqual(8.0) }),
                                context({ toEqual(10.0) }, { toEqual(9.0) }),
                                context({ toEqual(-1.0) }, { toEqual(-2.0) }, { toEqual(9.0) }),
                                context({ toEqual(7.0) }, { toEqual(8.0) }),
                                context({ toEqual(9.0) }, { toEqual(-8.0) }),
                                context({ toEqual(10.0) }, { toEqual(11.0) }),
                                context({ toEqual(12.0) }),
                                report = { showAlwaysSummary() }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContainSize(11, 20)
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    sizeCheck(2, 2),
                                    successAfterFail("$toEqualDescr: 1.0"),
                                    failAfterSuccess("$toEqualDescr: 1.0")
                                )
                                mismatchesAfterFail(2.0)
                                indexFail(2, 3.0, "$toEqualDescr: 2.0")
                                indexFail(
                                    3, 4, listOf(4.0, 5.0),
                                    sizeCheck(2, 2),
                                    failAfterSuccess("$toEqualDescr: 3.0"),
                                    failAfterFail("$toEqualDescr: -3.0")
                                )
                                mismatchesAfterFail(4.0, 5.0)
                                indexFail(
                                    5, 6, listOf(6.0, 7.0),
                                    sizeCheck(2, 2),
                                    failAfterSuccess("$toEqualDescr: 1.0"),
                                    failAfterFail("$toEqualDescr: 4.0")
                                )
                                mismatchesAfterFail(6.0, 7.0)
                                indexSuccess(7, 8.0, "$toEqualDescr: 8.0")
                                indexSuccess(
                                    8, 9, listOf(9.0, 10.0),
                                    //TODO 1.2.0: https://github.com/robstoll/atrium/issues/724 should not be shown, is enough to show the indices
                                    sizeCheck(2, 2),
                                    successAfterSuccess("$toEqualDescr: 10.0"),
                                    successAfterSuccess("$toEqualDescr: 9.0")
                                )
                                indexFail(
                                    10, 12, listOf(11.0),
                                    sizeCheck(1, 3),
                                    failAfterFail("$toEqualDescr: -1.0"),
                                    failAfterFail("$toEqualDescr: -2.0"),
                                    failAfterFail("$toEqualDescr: 9.0")
                                )
                                mismatchesAfterFail(11.0)
                                indexNonExisting(
                                    13, 14,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr: 7.0", "$toEqualDescr: 8.0"
                                )
                                indexNonExisting(
                                    15, 16,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr: 9.0", "$toEqualDescr: -8.0"
                                )
                                indexNonExisting(
                                    17, 18,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr: 10.0", "$toEqualDescr: 11.0"
                                )
                                indexNonExisting(19, "$toEqualDescr: 12.0")
                            }
                        }
                    }

                    it("shows only failing indices per default and only failing in group with reportInGroup `showOnlyFailing`") {
                        expect {
                            expect(oneToEleven as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(1.0) }, { toEqual(1.0) }),
                                context({ toEqual(2.0) }),
                                context({ toEqual(3.0) }, { toEqual(-3.0) }),
                                context({ toEqual(1.0) }, { toEqual(4.0) }),
                                context({ toEqual(8.0) }),
                                context({ toEqual(10.0) }, { toEqual(9.0) }),
                                context({ toEqual(-1.0) }, { toEqual(-2.0) }, { toEqual(9.0) }),
                                context({ toEqual(7.0) }, { toEqual(8.0) }),
                                context({ toEqual(9.0) }, { toEqual(-8.0) }),
                                context({ toEqual(10.0) }, { toEqual(11.0) }),
                                context({ toEqual(12.0) }),
                                reportInGroup = { showOnlyFailing() }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContainSize(11, 20)
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failAfterFail("$toEqualDescr: 1.0", withBulletPoint = false),
                                    withBulletPoint = false
                                )
                                mismatchesAfterFail(2.0)
                                indexFail(2, 3.0, "$toEqualDescr: 2.0", withBulletPoint = false)
                                indexFail(
                                    3, 4, listOf(4.0, 5.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failAfterFail("$toEqualDescr: 3.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: -3.0", withBulletPoint = false),
                                    withBulletPoint = false
                                )
                                mismatchesAfterFail(4.0, 5.0)
                                indexFail(
                                    5, 6, listOf(6.0, 7.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failAfterFail("$toEqualDescr: 1.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 4.0", withBulletPoint = false),
                                    withBulletPoint = false
                                )
                                mismatchesAfterFail(6.0, 7.0)
                                notToContainIndex(7, 7)
                                notToContainIndex(8, 9)
                                indexFail(
                                    10, 12, listOf(11.0),
                                    sizeCheck(1, 3),
                                    failAfterFail("$toEqualDescr: -1.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: -2.0", withBulletPoint = false),
                                    failAfterFail("$toEqualDescr: 9.0", withBulletPoint = false),
                                    withBulletPoint = false
                                )
                                mismatchesAfterFail(11.0)
                                indexNonExisting(
                                    13, 14,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr: 7.0", "$toEqualDescr: 8.0",
                                    withBulletPoint = false
                                )
                                indexNonExisting(
                                    15, 16,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr: 9.0", "$toEqualDescr: -8.0",
                                    withBulletPoint = false
                                )
                                indexNonExisting(
                                    17, 18,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr: 10.0", "$toEqualDescr: 11.0",
                                    withBulletPoint = false
                                )
                                indexNonExisting(19, "$toEqualDescr: 12.0", withBulletPoint = false)
                            }
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {
        describeFun(toContainInOrderOnlyGroupedEntries) {
            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {

                context("happy case") {
                    it("[$toEqualFun(1.0), null], [null, $toEqualFun(3.0)]") {
                        expect(null1null3()).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toEqual(1.0) }, null),
                            context(null, { toEqual(3.0) })
                        )
                    }
                    it("[null], [null, $toBeGreaterThanFun(2.0), $toBeLessThanFun(5.0)]") {
                        expect(null1null3()).toContainInOrderOnlyGroupedEntriesFun(
                            context(null),
                            context(null, { toBeGreaterThan(2.0) }, { toBeLessThan(5.0) })
                        )
                    }
                }

                context("error cases (throws AssertionError)") {

                    it("[null, null], [$toBeLessThanFun(5.0), $toBeGreaterThanFun(2.0)] -- wrong order") {
                        expect {
                            expect(null1null3()).toContainInOrderOnlyGroupedEntriesFun(
                                context(null, null),
                                context({ toBeLessThan(5.0) }, { toBeGreaterThan(2.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexFail(
                                    0, 1, listOf(null, 1.0),
                                    sizeCheck(2, 2),
                                    successAfterFail("$toEqualDescr: null"),
                                    failAfterFail("$toEqualDescr: null")
                                )
                                indexFail(
                                    2, 3, listOf(null, 3.0),
                                    sizeCheck(2, 2),
                                    successAfterFail("$toBeLessThanDescr: 5.0"),
                                    failAfterFail("$toBeGreaterThanDescr: 2.0")
                                )
                            }
                        }
                    }

                    it("[null, $toEqualFun(1.0)], [$toEqualFun(3.0), null, null] -- null too much") {
                        expect {
                            expect(null1null3()).toContainInOrderOnlyGroupedEntriesFun(
                                context(null, { toEqual(1.0) }),
                                context({ toEqual(3.0) }, null, null)
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnlyGrouped:")
                                indexSuccess(
                                    0, 1, listOf(null, 1.0),
                                    sizeCheck(2, 2),
                                    successAfterSuccess("$toEqualDescr: null"),
                                    successAfterSuccess("$toEqualDescr: 1.0")
                                )
                                indexFail(
                                    2, 4, listOf(null, 3.0),
                                    sizeCheck(2, 3),
                                    successAfterFail("$toEqualDescr: 3.0"),
                                    successAfterFail("$toEqualDescr: null"),
                                    failAfterFail("$toEqualDescr: null")

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
