package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsInOrderOnlyEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInOrderOnlyEntriesPair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>) -> Assert<Iterable<Double>>>,
    containsInOrderOnlyNullableEntriesPair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>>,
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

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsInOrderOnlyEntriesPair.first to mapToCreateAssertion { containsInOrderOnlyEntriesPair.second(this, { toBe(2.5) }, arrayOf()) },
        containsInOrderOnlyNullableEntriesPair.first to mapToCreateAssertion { containsInOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsInOrderOnlyEntriesPair.first, { containsInOrderOnlyEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }, listOf(2.5).asIterable(), listOf()),
        checkingTriple(containsInOrderOnlyNullableEntriesPair.first, { containsInOrderOnlyNullableEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }, listOf(2.5).asIterable(), listOf())
    ) {})

    fun SpecBody.describeFun(funName: String, body: SpecBody.() -> Unit)
        = group("fun `$funName`", body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException

    val (containsInOrderOnlyNullableEntries, containsInOrderOnlyNullableEntriesArr) = containsInOrderOnlyNullableEntriesPair
    fun Assert<Iterable<Double?>>.containsInOrderOnlyNullableEntriesFun(t: (Assert<Double>.() -> Unit)?, vararg tX: (Assert<Double>.() -> Unit)?)
        = containsInOrderOnlyNullableEntriesArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)

    val entryWithIndex = DescriptionIterableAssertion.ENTRY_WITH_INDEX.getDefault()
    val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()
    val explanatoryPointWithIndent = "$indentFeatureArrow$indentFeatureBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    val entryWhichWithFeature = "$indentFeatureArrow$featureBulletPoint$anEntryWhich"
    val anEntryAfterSuccess = "$entryWhichWithFeature: $separator$indentBulletPoint$indentSuccessfulBulletPoint$explanatoryPointWithIndent"
    val anEntryAfterFailing = "$entryWhichWithFeature: $separator$indentBulletPoint$indentFailingBulletPoint$explanatoryPointWithIndent"

    fun entry(index: Int)
        = String.format(entryWithIndex, index)

    fun Assert<CharSequence>.entrySuccess(index: Int, actual: Any, expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentSuccessfulBulletPoint$anEntryAfterSuccess$expected")
    }

    fun Assert<CharSequence>.entryFailing(index: Int, actual: Any, expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$anEntryAfterFailing$expected")
    }

    nonNullableCases(describePrefix,
        containsInOrderOnlyEntriesPair,
        containsInOrderOnlyNullableEntriesPair
    ) { containsEntriesFunArr ->
        fun Assert<Iterable<Double>>.containsEntriesFun(t: Assert<Double>.() -> Unit, vararg tX: Assert<Double>.() -> Unit)
            = containsEntriesFunArr(t, tX)

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            test("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, "$isLessThanDescr: 1.0")
                        containsNot(additionalEntries)
                        containsSize(0, 1)
                    }
                }
            }
            test("$isLessThanFun(1.0) and $isGreaterThanFun(4.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains("$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, "$isLessThanDescr: 1.0")
                        entryFailing(1, sizeExceeded, "$isGreaterThanDescr: 4.0")
                        containsNot(additionalEntries)
                        containsSize(0, 2)
                    }
                }
            }
            test("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmpty.containsEntriesFun({ returnValueOf(subject::dec).toBe(1.0) })
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE) } }
            }
        }

        val fluent = assert(oneToFour)
        context("iterable $oneToFour") {

            describe("happy case") {
                test("1.0, 2.0, 3.0, 4.0, 4.0") {
                    fluent.containsEntriesFun(
                        { toBe(1.0) },
                        { toBe(2.0) },
                        { toBe(3.0) },
                        { toBe(4.0) },
                        { toBe(4.0) })
                }
                test("$isLessThanFun(5.0), $isLessThanFun(5.0), $isLessThanFun(5.0), $isLessThanFun(5.0), $isLessThanFun(5.0)") {
                    fluent.containsEntriesFun(
                        { isLessThan(5.0) },
                        { isLessThan(5.0) },
                        { isLessThan(5.0) },
                        { isLessThan(5.0) },
                        { isLessThan(5.0) })
                }
            }

            describe("error cases ... throws AssertionError") {

                test("$isLessThanFun(5.0), 1.0, 2.0, $isGreaterThanFun(2.0), 4.0 -- wrong order") {
                    expect {
                        fluent.containsEntriesFun(
                            { isLessThan(5.0) },
                            { toBe(1.0) },
                            { toBe(2.0) },
                            { isGreaterThan(2.0) },
                            { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$isLessThanDescr: 5.0")
                            entryFailing(1, 2.0, "$toBeDescr: 1.0")
                            entryFailing(2, 3.0, "$toBeDescr: 2.0")
                            entrySuccess(3, 4.0, "$isGreaterThanDescr: 2.0")
                            entrySuccess(4, 4.0, "$toBeDescr: 4.0")
                            containsSize(5, 5)
                        }
                    }
                }

                test("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$toBeDescr: 1.0")
                            entrySuccess(1, 2.0, "$toBeDescr: 2.0")
                            entrySuccess(2, 3.0, "$toBeDescr: 3.0")
                            entrySuccess(3, 4.0, "$toBeDescr: 4.0")
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            containsSize(5, 4)
                        }
                    }
                }

                test("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$toBeDescr: 1.0")
                            entryFailing(1, 2.0, "$toBeDescr: 4.0")
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(2)}: 3.0",
                                "$listBulletPoint${entry(3)}: 4.0",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            containsSize(5, 2)
                        }
                    }
                }
                test("1.0, 3.0, $isGreaterThanFun(4.0) -- $isGreaterThanFun(4.0) is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(3.0) }, { isGreaterThan(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$toBeDescr: 1.0")
                            entryFailing(1, 2.0, "$toBeDescr: 3.0")
                            entryFailing(2, 3.0, "$isGreaterThanDescr: 4.0")
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(3)}: 4.0",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            containsSize(5, 3)
                        }
                    }
                }
                test("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 too much") {
                    expect {
                        fluent.containsEntriesFun(
                            { toBe(1.0) },
                            { toBe(2.0) },
                            { toBe(3.0) },
                            { toBe(4.0) },
                            { toBe(4.0) },
                            { toBe(5.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$toBeDescr: 1.0")
                            entrySuccess(1, 2.0, "$toBeDescr: 2.0")
                            entrySuccess(2, 3.0, "$toBeDescr: 3.0")
                            entrySuccess(3, 4.0, "$toBeDescr: 4.0")
                            entrySuccess(4, 4.0, "$toBeDescr: 4.0")
                            entryFailing(5, sizeExceeded, "$toBeDescr: 5.0")
                            containsSize(5, 6)
                        }
                    }
                }
            }
        }

        context("search for entry where the lambda does not specify any assertion") {
            it("throws an ${IllegalArgumentException::class.simpleName}") {
                expect {
                    fluent.containsEntriesFun({})
                }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(containsInOrderOnlyNullableEntries) {
            absentSubjectTests(verbs, Assert<Iterable<Double?>>::containsInOrderOnlyNullableEntriesFun)

            val list = listOf(null, 1.0, null, 3.0)
            val fluent = verbs.checkImmediately(list)
            context("iterable $list") {

                describe("happy case") {
                    test("null, 1.0, null, 3.0") {
                        fluent.containsInOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, null, { toBe(3.0) })
                    }
                    test("null, $isLessThanFun(5.0), null, $isLessThanFun(5.0)") {
                        fluent.containsInOrderOnlyNullableEntriesFun(
                            null,
                            { isLessThan(5.0) },
                            null,
                            { isLessThan(5.0) })
                    }
                }

                describe("error cases (throws AssertionError)") {

                    test("null, null, $isLessThanFun(5.0), $isGreaterThanFun(2.0) -- wrong order") {
                        expect {
                            fluent.containsInOrderOnlyNullableEntriesFun(
                                null,
                                null,
                                { isLessThan(5.0) },
                                { isGreaterThan(2.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains("$containsInOrderOnly:")
                                entrySuccess(0, "null", "$isDescr: null")
                                entryFailing(1, 1.0, "$isDescr: null")
                                entryFailing(2, "null", "$isLessThanDescr: 5.0")
                                entrySuccess(3, 3.0, "$isGreaterThanDescr: 2.0")
                                containsSize(4, 4)
                            }
                        }
                    }

                    test("null, 1.0, null, 3.0, null -- null too much") {
                        expect {
                            fluent.containsInOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, null, { toBe(3.0) }, null)
                        }.toThrow<AssertionError> {
                            message {
                                contains("$containsInOrderOnly:")
                                entrySuccess(0, "null", "$isDescr: null")
                                entrySuccess(1, 1.0, "$toBeDescr: 1.0")
                                entrySuccess(2, "null", "$isDescr: null")
                                entrySuccess(3, 3.0, "$toBeDescr: 3.0")
                                entryFailing(4, sizeExceeded, "$isDescr: null")
                                containsSize(4, 5)
                            }
                        }
                    }
                }
            }

            context("search for entry where the lambda does not specify any assertion") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    expect {
                        fluent.containsInOrderOnlyNullableEntriesFun({})
                    }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
                }
            }
        }
    }
})

