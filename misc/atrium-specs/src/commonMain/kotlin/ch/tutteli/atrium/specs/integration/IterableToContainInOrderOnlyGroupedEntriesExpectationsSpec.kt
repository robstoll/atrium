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
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
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
        toContainInOrderOnlyGroupedEntries.forSubjectLessTest(
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
        ExpectationCreatorTriple(toContainInOrderOnlyGroupedEntries.name + " [first empty]", "$toEqualDescr\\s+: 1.2",
            { toContainInOrderOnlyGroupedEntries(this, Entry { toEqual(1.2) }, Entry { toEqual(2.0) }, arrayOf( Entry { toEqual(3.0) }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) },
            { toContainInOrderOnlyGroupedEntries(this, Entry { }, Entry { toEqual(2.0) }, arrayOf( Entry { toEqual(3.0) }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) }
        ),
        ExpectationCreatorTriple(toContainInOrderOnlyGroupedEntries.name + " [second empty]", "$toEqualDescr\\s+: 2.0",
            { toContainInOrderOnlyGroupedEntries(this, Entry { toEqual(1.2) }, Entry { toEqual(2.0) }, arrayOf( Entry { toEqual(3.0) }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) },
            { toContainInOrderOnlyGroupedEntries(this, Entry { toEqual(1.2) }, Entry { }, arrayOf( Entry { toEqual(3.0) }), emptyInOrderOnlyReportOptions, emptyInAnyOrderOnlyReportOptions) }
        ),
        ExpectationCreatorTriple(toContainInOrderOnlyGroupedEntries.name + " [third empty]", "$toEqualDescr\\s+: 3.0",
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

    fun element(prefix: String, bulletPoint: String, indent: String, expected: Array<out String>) =
        expected.joinToString(lineSeparator) {
            "$prefix\\Q$bulletPoint$anElementWhichNeedsDescr : \\E$lineSeparator" +
                "$prefix$indent$explanatoryBulletPoint$it"
        }

    fun size(prefix: String, bulletPoint: String, actual: Int?, expected: Int) =
        "$prefix\\Q$bulletPoint\\E${f}${DescriptionCollectionProof.SIZE.string}\\s+:${actual?.let { " $it" } ?: ""}[^:]+: $expected"

    fun sizeFail(actual: Int, expected: Int) = size("$indentG$indentF", g, actual, expected)
    fun sizeSuccessInFail(actual: Int, expected: Int) = size("$indentG$indentF", s, actual, expected)
    fun sizeSuccessInSuccess(actual: Int, expected: Int) = size("$indentS$indentF", s, actual, expected)

    val inFail = "$indentGg$indentF"
    fun failInFail(vararg expected: String) =
        //TODO 1.3.0 should be x and not g
        element("$inFail$indentG", g, indentX, expected)

    fun successInFail(vararg expected: String) = element("$inFail$indentG", s, indentS, expected)

    fun Expect<String>.notToContainIndex(from: Int, to: Int) =
        notToContain.regex("\\Q${DescriptionIterableLikeProof.INDEX.string.format("$from..$to")}")

    fun mismatches(vararg mismatched: Double) =
        "$inFail\\Q$bb$mismatches\\E : $lineSeparator" +
            mismatched.toTypedArray().joinToString(lineSeparator) { "$inFail$indentBb$listBulletPoint$it" }

    fun Expect<String>.additional(vararg entryWithValue: Pair<Int, Double>) =
        //TODO 1.3.0 should be defined on an own level and hence \\s+ should not be necessary
        toContainRegex(
            "$indentG$bb$additionalElements : $lineSeparator" +
                entryWithValue
                    .joinToString(lineSeparator) {
                        "$indentG$indentBb$listBulletPoint${elementWithIndex(it.first)}\\s+: ${it.second}"
                    }
        )

    val inSuccess = "$indentG$indentS$indentF$indentS"
    fun successInSuccess(vararg expected: String) =
        element(inSuccess, s, indentS, expected)

    fun Expect<String>.indexSuccess(index: Int, actual: Any, expected: String): Expect<String> {
        return this.toContain.exactly(1).regex(
            "$indentG\\Q$s$f\\E${index(index)}\\s+: \\Q$actual\\E$lineSeparator" +
                "$indentG$indentS$indentF$s$expected"
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
        expected: String,
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "$indentG\\Q$g\\E$f${index(index)}\\s+: \\Q$actual\\E$lineSeparator" +
                "$indentGg$indentF$x$expected"
        )
    }

    fun Expect<String>.indexFail(
        fromIndex: Int,
        toIndex: Int,
        actual: List<Double?>,
        sizeCheck: String?,
        vararg expected: String
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "$indentG\\Q$g$f\\E${index(fromIndex, toIndex)} : \\Q$actual\\E.*$lineSeparator" +
                (sizeCheck?.let { "$indentG$sizeCheck$lineSeparator" } ?: "") +
                "$indentGg$indentF\\Q$g\\E$toContainInAnyOrderOnly : $lineSeparator" +
                expected.joinToString(lineSeparator)
        )
    }

    fun Expect<String>.indexNonExisting(index: Int, expected: String): Expect<String> {
        return this.toContain.exactly(1).regex(
            //TODO 1.3.0 should be x instead of g
            "\\Q$g$f${IterableToContainSpecBase.index(index)} : $sizeExceeded\\E$lineSeparator" +
                "$inFail$explanatoryBulletPoint$expected"
        )
    }

    fun Expect<String>.indexNonExisting(
        fromIndex: Int, toIndex: Int,
        sizeCheck: String,
        vararg expected: String,
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "$indentG\\Q$g$f${index(fromIndex, toIndex)} : $sizeExceeded\\E.*$lineSeparator" +
                "$inFail$sizeCheck.*$lineSeparator" +
                "$inFail$explanatoryBulletPoint$toContainInAnyOrderOnly : $lineSeparator" +
                expected.joinToString(lineSeparator) {
                    "$inFail$indentExplanatory$listBulletPoint$anElementWhichNeedsDescr : $lineSeparator" +
                        "$inFail$indentExplanatory$indentList$explanatoryBulletPoint$it"
                }
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

            context("empty iterable") {
                it("(1.0), (1.2) throws AssertionError") {
                    expect {
                        expect(setOf<Double?>().asIterable()).toContainInOrderOnlyGroupedEntriesFun(
                            context({ toEqual(1.0) }), context({ toEqual(1.2) })
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                            indexNonExisting(0, "$toEqualDescr : 1.0")
                            indexNonExisting(1, "$toEqualDescr : 1.2")
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    sizeSuccessInFail(2, 2),
                                    failInFail("$toEqualDescr : 4.0"),
                                    successInFail("$toEqualDescr : 1.0"),
                                    mismatches(2.0)
                                )
                                indexFail(
                                    2, 4, listOf(3.0, 4.0, 4.0),
                                    sizeSuccessInFail(3, 3),
                                    failInFail("$toEqualDescr : 2.0"),
                                    successInFail("$toEqualDescr : 3.0"),
                                    successInFail("$toEqualDescr : 4.0"),
                                    mismatches(4.0)
                                )
                                notToContain(size(indentRoot, s, 5, 5))
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    sizeSuccessInFail(2, 2),
                                    successInFail("$toBeLessThanDescr : 2.1"),
                                    failInFail("$toBeLessThanDescr : 2.0"),
                                    mismatches(2.0)
                                )
                                indexSuccess(
                                    2, 4, listOf(3.0, 4.0, 4.0),
                                    sizeSuccessInSuccess(3, 3),
                                    successInSuccess("$toEqualDescr : 4.0"),
                                    successInSuccess("$toEqualDescr : 3.0"),
                                    successInSuccess("$toEqualDescr : 4.0")
                                )
                                notToContain(size(indentRoot, s, 5, 5))
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexSuccess(0, 1.0, "$toEqualDescr : 1.0")
                                indexSuccess(
                                    1, 3, listOf(2.0, 3.0, 4.0),
                                    sizeSuccessInSuccess(3, 3),
                                    successInSuccess(
                                        "$toEqualDescr : 4.0",
                                        "$toEqualDescr : 2.0",
                                        "$toEqualDescr : 3.0"
                                    )
                                )
                                additional(4 to 4.0)
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexSuccess(0, 1.0, "$toEqualDescr : 1.0")
                                indexFail(1, 2.0, "$toEqualDescr : 4.0")
                                additional(2 to 3.0, 3 to 4.0, 4 to 4.0)
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    sizeSuccessInFail(2, 2),
                                    successInFail("$toEqualDescr : 1.0"),
                                    failInFail("$toEqualDescr : 3.0"),
                                    mismatches(2.0)
                                )
                                indexFail(2, 3.0, "$toEqualDescr : 5.0")
                                additional(3 to 4.0, 4 to 4.0)
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexSuccess(
                                    0, 3, listOf(1.0, 2.0, 3.0, 4.0),
                                    sizeSuccessInSuccess(4, 4),
                                    successInSuccess(
                                        "$toEqualDescr : 4.0",
                                        "$toEqualDescr : 1.0",
                                        "$toEqualDescr : 3.0",
                                        "$toEqualDescr : 2.0"
                                    )
                                )
                                indexFail(
                                    4, 5, listOf(4.0),
                                    sizeFail(1, 2),
                                    failInFail("$toEqualDescr : 5.0"),
                                    successInFail("$toEqualDescr : 4.0")
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                notToContainIndex(0, 0)
                                indexFail(
                                    1, 2, listOf(2.0, 3.0),
                                    null,
                                    successInFail("$toEqualDescr : 2.0"),
                                    failInFail("$toEqualDescr : 4.0"),
                                )
                                indexFail(
                                    3,
                                    13,
                                    listOf(4.0, 4.0),
                                    sizeFail(2, 11),
                                    failInFail("$toEqualDescr : 1.0"),
                                    failInFail("$toEqualDescr : 2.0"),
                                    failInFail("$toEqualDescr : 3.0"),
                                    failInFail("$toEqualDescr : 5.0"),
                                    failInFail("$toEqualDescr : 6.0"),
                                    failInFail("$toEqualDescr : 7.0"),
                                    failInFail("$toEqualDescr : 8.0"),
                                    failInFail("$toEqualDescr : 9.0"),
                                    failInFail("$toEqualDescr : 10.0"),
                                    failInFail("$toEqualDescr : 11.0"),
                                    mismatches(4.0)
                                )
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                notToContainIndex(0, 2)
                                indexFail(
                                    3, 5, listOf(4.0, 4.0),
                                    sizeFail(2, 3),
                                    failInFail("$toEqualDescr : 5.0"),
                                )
                                notToContain("$anElementWhichEquals : 4.0")
                            }
                        }
                    }

                    it("shows only failing indices with report option `showOnlyFailingIfMoreExpectedElementsThan(1)` because we have 2 groups but still all elements in group (as no has more than 10 expected elements)") {
                        expect {
                            expect(oneToFour() as Iterable<Double?>).toContainInOrderOnlyGroupedEntriesFun(
                                context({ toEqual(1.0) }, { toEqual(2.0) }, { toEqual(3.0) }),
                                context({ toEqual(4.0) }, { toEqual(4.0) }, { toEqual(5.0) }),
                                report = {
                                    // showOnlyFailingIfMoreExpectedElementsThan means if more than expected groups
                                    showOnlyFailingIfMoreExpectedElementsThan(1)
                                }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContainSize(5, 6)
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                notToContainIndex(0, 2)
                                indexFail(
                                    3, 5, listOf(4.0, 4.0),
                                    sizeFail(2, 3),
                                    successInFail("$toEqualDescr : 4.0", "$toEqualDescr : 4.0"),
                                    failInFail("$toEqualDescr : 5.0"),
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    successInFail("$toEqualDescr : 1.0"),
                                    failInFail("$toEqualDescr : 1.0"),
                                    mismatches(2.0)
                                )
                                indexFail(2, 3.0, "$toEqualDescr : 2.0")
                                indexFail(
                                    3, 4, listOf(4.0, 5.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failInFail("$toEqualDescr : 3.0"),
                                    failInFail("$toEqualDescr : -3.0"),
                                    mismatches(4.0, 5.0)
                                )
                                indexFail(
                                    5, 6, listOf(6.0, 7.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failInFail("$toEqualDescr : 1.0"),
                                    failInFail("$toEqualDescr : 4.0"),
                                    mismatches(6.0, 7.0)
                                )
                                notToContainIndex(7, 7)
                                notToContainIndex(8, 9)
                                indexFail(
                                    10, 12, listOf(11.0),
                                    sizeFail(1, 3),
                                    failInFail("$toEqualDescr : -1.0"),
                                    failInFail("$toEqualDescr : -2.0"),
                                    failInFail("$toEqualDescr : 9.0"),
                                    mismatches(11.0)
                                )
                                indexNonExisting(
                                    13, 14,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr : 7.0", "$toEqualDescr : 8.0",
                                )
                                indexNonExisting(
                                    15, 16,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr : 9.0", "$toEqualDescr : -8.0",
                                )
                                indexNonExisting(
                                    17, 18,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr : 10.0", "$toEqualDescr : 11.0",
                                )
                                indexNonExisting(19, "$toEqualDescr : 12.0")
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
                                    sizeSuccessInFail(2, 2),
                                    successInFail("$toEqualDescr : 1.0"),
                                    failInFail("$toEqualDescr : 1.0"),
                                    mismatches(2.0)
                                )
                                indexFail(2, 3.0, "$toEqualDescr : 2.0")
                                indexFail(
                                    3, 4, listOf(4.0, 5.0),
                                    sizeSuccessInFail(2, 2),
                                    failInFail("$toEqualDescr : 3.0"),
                                    failInFail("$toEqualDescr : -3.0"),
                                    mismatches(4.0, 5.0)
                                )
                                indexFail(
                                    5, 6, listOf(6.0, 7.0),
                                    sizeSuccessInFail(2, 2),
                                    failInFail("$toEqualDescr : 1.0"),
                                    failInFail("$toEqualDescr : 4.0"),
                                    mismatches(6.0, 7.0)
                                )
                                indexSuccess(7, 8.0, "$toEqualDescr : 8.0")
                                indexSuccess(
                                    8, 9, listOf(9.0, 10.0),
                                    //TODO 1.3.0: https://github.com/robstoll/atrium/issues/724 should not be shown, is enough to show the indices
                                    sizeSuccessInSuccess(2, 2),
                                    successInSuccess("$toEqualDescr : 10.0"),
                                    successInSuccess("$toEqualDescr : 9.0")
                                )
                                indexFail(
                                    10, 12, listOf(11.0),
                                    sizeFail(1, 3),
                                    failInFail("$toEqualDescr : -1.0"),
                                    failInFail("$toEqualDescr : -2.0"),
                                    failInFail("$toEqualDescr : 9.0"),
                                    mismatches(11.0)
                                )
                                indexNonExisting(
                                    13, 14,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr : 7.0", "$toEqualDescr : 8.0"
                                )
                                indexNonExisting(
                                    15, 16,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr : 9.0", "$toEqualDescr : -8.0"
                                )
                                indexNonExisting(
                                    17, 18,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr : 10.0", "$toEqualDescr : 11.0"
                                )
                                indexNonExisting(19, "$toEqualDescr : 12.0")
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failInFail("$toEqualDescr : 1.0"),
                                )
                                mismatches(2.0)
                                indexFail(2, 3.0, "$toEqualDescr : 2.0")
                                indexFail(
                                    3, 4, listOf(4.0, 5.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failInFail("$toEqualDescr : 3.0"),
                                    failInFail("$toEqualDescr : -3.0"),
                                )
                                mismatches(4.0, 5.0)
                                indexFail(
                                    5, 6, listOf(6.0, 7.0),
                                    null, //i.e no size check is shown as 2=2 and summary is only for inReportGroup
                                    failInFail("$toEqualDescr : 1.0"),
                                    failInFail("$toEqualDescr : 4.0"),
                                )
                                mismatches(6.0, 7.0)
                                notToContainIndex(7, 7)
                                notToContainIndex(8, 9)
                                indexFail(
                                    10, 12, listOf(11.0),
                                    sizeFail(1, 3),
                                    failInFail("$toEqualDescr : -1.0"),
                                    failInFail("$toEqualDescr : -2.0"),
                                    failInFail("$toEqualDescr : 9.0"),
                                )
                                mismatches(11.0)
                                indexNonExisting(
                                    13, 14,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr : 7.0", "$toEqualDescr : 8.0",
                                )
                                indexNonExisting(
                                    15, 16,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr : 9.0", "$toEqualDescr : -8.0",
                                )
                                indexNonExisting(
                                    17, 18,
                                    size("", explanatoryBulletPoint, null, 2),
                                    "$toEqualDescr : 10.0", "$toEqualDescr : 11.0",
                                )
                                indexNonExisting(19, "$toEqualDescr : 12.0")
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
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexFail(
                                    0, 1, listOf(null, 1.0),
                                    sizeSuccessInFail(2, 2),
                                    successInFail("$toEqualDescr : null"),
                                    failInFail("$toEqualDescr : null")
                                )
                                indexFail(
                                    2, 3, listOf(null, 3.0),
                                    sizeSuccessInFail(2, 2),
                                    successInFail("$toBeLessThanDescr : 5.0"),
                                    failInFail("$toBeGreaterThanDescr : 2.0")
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
                                toContainSize(4, 5)
                                toContain.exactly(1).value("$g$toContainInOrderOnlyGrouped :")
                                indexSuccess(
                                    0, 1, listOf(null, 1.0),
                                    sizeSuccessInSuccess(2, 2),
                                    successInSuccess("$toEqualDescr : null"),
                                    successInSuccess("$toEqualDescr : 1.0")
                                )
                                indexFail(
                                    2, 4, listOf(null, 3.0),
                                    sizeFail(2, 3),
                                    successInFail("$toEqualDescr : 3.0"),
                                    successInFail("$toEqualDescr : null"),
                                    failInFail("$toEqualDescr : null")

                                )
                            }
                        }
                    }
                }
            }
        }
    }
})
