package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.api.cc.en_GB.value
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.regex
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInOrderOnlyGroupedEntriesPair: Pair<String, Expect<Iterable<Double?>>.(Group<(Expect<Double>.() -> Unit)?>, Group<(Expect<Double>.() -> Unit)?>, Array<out Group<(Expect<Double>.() -> Unit)?>>) -> Expect<Iterable<Double?>>>,
    groupFactory: (Array<out (Expect<Double>.() -> Unit)?>) -> Group<(Expect<Double>.() -> Unit)?>,
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

    fun context(vararg assertionCreators: (Expect<Double>.() -> Unit)?) = groupFactory(assertionCreators)

    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        containsInOrderOnlyGroupedEntriesPair.first to expectLambda { containsInOrderOnlyGroupedEntriesPair.second(this, context({ toBe(2.5) }), context({ toBe(4.1) }), arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double?>>(verbs, describePrefix,
        checkingTriple(containsInOrderOnlyGroupedEntriesPair.first, { containsInOrderOnlyGroupedEntriesPair.second(this, context({ toBe(2.5) }), context({ toBe(1.2) }, { toBe(2.2) }), arrayOf()) },
            listOf(2.5 as Double?, 2.2, 1.2).asIterable(), listOf(2.2 as Double?, 1.2, 2.5)
        )
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException

    val (containsInOrderOnlyGroupedEntries, containsInOrderOnlyGroupedEntriesFunArr) = containsInOrderOnlyGroupedEntriesPair
    fun Expect<Iterable<Double?>>.containsInOrderOnlyGroupedEntriesFun(t1: Group<(Expect<Double>.() -> Unit)?>, t2: Group<(Expect<Double>.() -> Unit)?>, vararg tX: Group<(Expect<Double>.() -> Unit)?>)
        = containsInOrderOnlyGroupedEntriesFunArr(t1, t2, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)
    val indentWarningBulletPoint = " ".repeat(warningBulletPoint.length)

    fun index(index: Int)
        = String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)

    fun entryWithIndex(index: Int)
        = String.format(entryWithIndex, index)

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


    fun Expect<String>.indexSuccess(index: Int, actual: Any, expected: String): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$featureBulletPoint$anEntryWhich: $separator" +
                "$afterSuccess$indentListBulletPoint$explanatoryBulletPoint$expected")
    }

    fun Expect<String>.indexSuccess(fromIndex: Int, toIndex: Int, actual: List<Double?>, vararg expected: String): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")

        )
    }

    fun Expect<String>.indexFail(index: Int, actual: Any, expected: String): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$anEntryWhich: $separator" +
                "$afterFail$indentListBulletPoint$explanatoryBulletPoint$expected")
    }

    fun Expect<String>.indexFail(fromIndex: Int, toIndex: Int, actual: List<Double?>, vararg expected: String): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")
        )
    }




    describeFun(containsInOrderOnlyGroupedEntries) {
        context("$describePrefix describe non-nullable cases") {

            val fluent = verbs.check(oneToFour as Iterable<Double?>)
            context("throws an $illegalArgumentException") {
                it("if an empty group is given as first parameter") {
                    expect {
                        fluent.containsInOrderOnlyGroupedEntriesFun(context(), context({ toBe(-1.2) }))
                    }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
                }
                it("if an empty group is given as second parameter") {
                    expect {
                        fluent.containsInOrderOnlyGroupedEntriesFun(context({ toBe(1.2) }), context())
                    }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
                }
                it("if an empty group is given as third parameter") {
                    expect {
                        fluent.containsInOrderOnlyGroupedEntriesFun(context({ toBe(1.2) }), context({ toBe(4.3) }), context())
                    }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
                }
                it("if an empty group is given as fourth parameter") {
                    expect {
                        fluent.containsInOrderOnlyGroupedEntriesFun(
                            context({ toBe(1.2) }),
                            context({ toBe(4.3) }),
                            context({ toBe(5.7) }),
                            context()
                        )
                    }.toThrow<IllegalArgumentException> { messageContains("a group of values cannot be empty") }
                }
            }

            context("empty collection") {
                val fluentEmpty = verbs.check(setOf<Double?>().asIterable())
                it("(1.0), (1.2) throws AssertionError") {
                    expect {
                        fluentEmpty.containsInOrderOnlyGroupedEntriesFun(context({ toBe(1.0) }), context({ toBe(1.2) }))
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                            asExpect().indexFail(0, sizeExceeded, "$toBeDescr: 1.0")
                            asExpect().indexFail(1, sizeExceeded, "$toBeDescr: 1.2")
                            containsNot(additionalEntries)
                            asExpect().containsSize(0, 2)
                        }
                    }
                }
            }

            context("iterable $oneToFour") {

                context("happy case") {
                    it("(1.0), (2.0, 3.0), (4.0, 4.0)") {
                        fluent.containsInOrderOnlyGroupedEntriesFun(
                            context({ toBe(1.0) }),
                            context({ toBe(2.0) }, { toBe(3.0) }),
                            context({ toBe(4.0) }, { toBe(4.0) })
                        )
                    }
                    it("(2.0, 1.0), (4.0, 3.0), (4.0)") {
                        fluent.containsInOrderOnlyGroupedEntriesFun(
                            context({ toBe(2.0) }, { toBe(1.0) }),
                            context({ toBe(4.0) }, { toBe(3.0) }),
                            context({ toBe(4.0) })
                        )
                    }
                    it("(2.0, 3.0, 1.0), (4.0), (4.0)") {
                        fluent.containsInOrderOnlyGroupedEntriesFun(
                            context({ toBe(2.0) }, { toBe(3.0) }, { toBe(1.0) }),
                            context({ toBe(4.0) }),
                            context({ toBe(4.0) })
                        )
                    }
                    it("(1.0, 2.0), (4.0, 3.0, 4.0)") {
                        fluent.containsInOrderOnlyGroupedEntriesFun(
                            context({ toBe(1.0) }, { toBe(2.0) }),
                            context({ toBe(4.0) }, { toBe(3.0) }, { toBe(4.0) })
                        )
                    }
                    it("[$isLessThanFun(2.1) && $isGreaterThanFun(1.0), $isLessThanFun(2.0)], [$isGreaterThanFun(3.0), $isGreaterThanFun(2.0), $isGreaterThanFun(1.0) && $isLessThanFun(5.0)]") {
                        fluent.containsInOrderOnlyGroupedEntriesFun(
                            context({ isLessThan(2.1).and.isGreaterThan(1.0) }, { isLessThan(2.0) }),
                            context({ isGreaterThan(3.0) },
                                { isGreaterThan(2.0) },
                                { isGreaterThan(1.0); isLessThan(5.0) })
                        )
                    }
                }

                context("error cases (throws AssertionError)") {

                    it("(4.0, 1.0), (2.0, 3.0, 4.0) -- wrong order") {
                        expect {
                            fluent.containsInOrderOnlyGroupedEntriesFun(
                                context({ toBe(4.0) }, { toBe(1.0) }),
                                context({ toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                asExpect().indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    failAfterFail("$toBeDescr: 4.0"),
                                    successAfterFail("$toBeDescr: 1.0"),
                                    successSizeAfterFail(2),
                                    mismatchesAfterFail(2.0)
                                )
                                asExpect().indexFail(
                                    2, 4, listOf(3.0, 4.0, 4.0),
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

                    it("[$isLessThanFun(2.1), $isLessThanFun(2.0)], (4.0, 3.0, 4.0) -- first win also applies here, $isLessThanFun(2.1) matches 1.0 and not 2.0") {
                        expect {
                            fluent.containsInOrderOnlyGroupedEntriesFun(
                                context({ isLessThan(2.1) }, { isLessThan(2.0) }),
                                context({ toBe(4.0) }, { toBe(3.0) }, { toBe(4.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                asExpect().indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    successAfterFail("$isLessThanDescr: 2.1"),
                                    failAfterFail("$isLessThanDescr: 2.0"),
                                    successSizeAfterFail(2),
                                    mismatchesAfterFail(2.0)
                                )
                                asExpect().indexSuccess(
                                    2, 4, listOf(3.0, 4.0, 4.0),
                                    successAfterSuccess("$toBeDescr: 4.0"),
                                    successAfterSuccess("$toBeDescr: 3.0"),
                                    successAfterSuccess("$toBeDescr: 4.0"),
                                    successSizeAfterFail(3)
                                )
                                containsRegex(size(indentBulletPoint, successfulBulletPoint, 5, 5))
                            }
                        }
                    }

                    it("(1.0), (4.0, 3.0, 2.0) -- 4.0 was missing") {
                        expect {
                            fluent.containsInOrderOnlyGroupedEntriesFun(
                                context({ toBe(1.0) }),
                                context({ toBe(4.0) }, { toBe(2.0) }, { toBe(3.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                asExpect().indexSuccess(0, 1.0, "$toBeDescr: 1.0")
                                asExpect().indexSuccess(
                                    1, 3, listOf(2.0, 3.0, 4.0),
                                    successAfterSuccess("$toBeDescr: 4.0", "$toBeDescr: 2.0", "$toBeDescr: 3.0"),
                                    successSizeAfterSuccess(3)
                                )
                                containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 4))
                                containsRegex(additional(4 to 4.0))
                            }
                        }
                    }

                    it("(1.0), (4.0) -- 2.0, 3.0 and 4.0 was missing") {
                        expect {
                            fluent.containsInOrderOnlyGroupedEntriesFun(context({ toBe(1.0) }), context({ toBe(4.0) }))
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                asExpect().indexSuccess(0, 1.0, "$toBeDescr: 1.0")
                                asExpect().indexFail(1, 2.0, "$toBeDescr: 4.0")
                                containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 2))
                                containsRegex(additional(2 to 3.0, 3 to 4.0, 4 to 4.0))
                            }
                        }
                    }
                    it("(1.0, 3.0), (5.0) -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                        expect {
                            fluent.containsInOrderOnlyGroupedEntriesFun(
                                context({ toBe(1.0) }, { toBe(3.0) }),
                                context({ toBe(5.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                asExpect().indexFail(
                                    0, 1, listOf(1.0, 2.0),
                                    successAfterFail("$toBeDescr: 1.0"),
                                    failAfterFail("$toBeDescr: 3.0"),
                                    successSizeAfterFail(2),
                                    mismatchesAfterFail(2.0)
                                )
                                asExpect().indexFail(2, 3.0, "$toBeDescr: 5.0")
                                containsRegex(size(indentBulletPoint, failingBulletPoint, 5, 3))
                                containsRegex(additional(3 to 4.0, 4 to 4.0))
                            }
                        }
                    }
                    it("(4.0, 1.0, 3.0, 2.0), (5.0, 4.0) -- 5.0 too much") {
                        expect {
                            fluent.containsInOrderOnlyGroupedEntriesFun(
                                context({ toBe(4.0) }, { toBe(1.0) }, { toBe(3.0) }, { toBe(2.0) }),
                                context({ toBe(5.0) }, { toBe(4.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                asExpect().indexSuccess(
                                    0, 3, listOf(1.0, 2.0, 3.0, 4.0),
                                    successAfterSuccess(
                                        "$toBeDescr: 4.0",
                                        "$toBeDescr: 1.0",
                                        "$toBeDescr: 3.0",
                                        "$toBeDescr: 2.0"
                                    ),
                                    successSizeAfterSuccess(4)
                                )
                                asExpect().indexFail(
                                    4, 5, listOf(4.0),
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
    }
    nullableCases(describePrefix) {
        describeFun("$containsInOrderOnlyGroupedEntries for nullable") {
            val list = listOf(null, 1.0, null, 3.0).asIterable()
            val fluent = verbs.check(list)

            context("iterable $list") {

                context("happy case") {
                    it("[$toBeFun(1.0), null], [null, $toBeFun(3.0)]") {
                        fluent.containsInOrderOnlyGroupedEntriesFun(
                            context({ toBe(1.0) }, null),
                            context(null, { toBe(3.0) })
                        )
                    }
                    it("[null], [null, $isGreaterThanFun(2.0), $isLessThanFun(5.0)]") {
                        fluent.containsInOrderOnlyGroupedEntriesFun(
                            context(null),
                            context(null, { isGreaterThan(2.0) }, { isLessThan(5.0) })
                        )
                    }
                }

                context("error cases (throws AssertionError)") {

                    it("[null, null], [$isLessThanFun(5.0), $isGreaterThanFun(2.0)] -- wrong order") {
                        expect {
                            fluent.containsInOrderOnlyGroupedEntriesFun(
                                context(null, null),
                                context({ isLessThan(5.0) }, { isGreaterThan(2.0) })
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                asExpect().indexFail(
                                    0, 1, listOf(null, 1.0),
                                    successAfterFail("$isDescr: null"),
                                    failAfterFail("$isDescr: null"),
                                    successSizeAfterFail(2)
                                )
                                asExpect().indexFail(
                                    2, 3, listOf(null, 3.0),
                                    successAfterFail("$isLessThanDescr: 5.0"),
                                    failAfterFail("$isGreaterThanDescr: 2.0"),
                                    successSizeAfterFail(2)
                                )
                                asExpect().containsSize(4, 4)
                            }
                        }
                    }

                    it("[null, $toBeFun(1.0)], [$toBeFun(3.0), null, null] -- null too much") {
                        expect {
                            fluent.containsInOrderOnlyGroupedEntriesFun(
                                context(null, { toBe(1.0) }),
                                context({ toBe(3.0) }, null, null)
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnlyGrouped:")
                                asExpect().indexSuccess(
                                    0, 1, listOf(null, 1.0),
                                    successAfterSuccess("$isDescr: null"),
                                    successAfterSuccess("$toBeDescr: 1.0"),
                                    successSizeAfterSuccess(2)
                                )
                                asExpect().indexFail(
                                    2, 4, listOf(null, 3.0),
                                    successAfterFail("$toBeDescr: 3.0"),
                                    successAfterFail("$isDescr: null"),
                                    failAfterFail("$isDescr: null"),
                                    failSizeAfterFail(2, 3)
                                )
                                asExpect().containsSize(4, 5)
                            }
                        }
                    }
                }
            }
        }
    }
})
