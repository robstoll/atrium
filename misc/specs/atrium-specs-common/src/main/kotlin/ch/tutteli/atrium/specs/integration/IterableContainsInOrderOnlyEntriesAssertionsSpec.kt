package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.utils.subExpect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.ErrorMessages
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsInOrderOnlyEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInOrderOnlyEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    containsInOrderOnlyNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
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

    //@formatter:off
    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        containsInOrderOnlyEntries.forSubjectLess({ toBe(2.5) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>("$describePrefix[nullable] ",
        containsInOrderOnlyNullableEntries.forSubjectLess(null, arrayOf())
    ) {})

     include(object : AssertionCreatorSpec<Iterable<Double>>(
        verbs, describePrefix, listOf(1.2, 2.0),
         *containsInOrderOnlyEntries.forAssertionCreatorSpec(
             "$toBeDescr: 1.2", "$toBeDescr: 2.0",
             { toBe(1.2) }, arrayOf(subExpect{ toBe(2.0) })
         )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        verbs, "$describePrefix[nullable] ", listOf(1.2, 2.0) as Iterable<Double?>,
        *containsInOrderOnlyNullableEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toBe(1.2) }, arrayOf(subExpect{ toBe(2.0) })
        )
    ) {})
    //@formatter:on


    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Expect<Iterable<Double>> = verbs::check
    val expect = verbs::checkException

    fun Expect<Iterable<Double?>>.containsInOrderOnlyNullableEntriesFun(
        t: (Expect<Double>.() -> Unit)?,
        vararg tX: (Expect<Double>.() -> Unit)?
    ) = containsInOrderOnlyNullableEntries(this, t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)

    val explanatoryPointWithIndent =
        "$indentFeatureArrow$indentFeatureBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    val entryWhichWithFeature = "$indentFeatureArrow$featureBulletPoint$anEntryWhich"
    val anEntryAfterSuccess =
        "$entryWhichWithFeature: $separator$indentBulletPoint$indentSuccessfulBulletPoint$explanatoryPointWithIndent"
    val anEntryAfterFailing =
        "$entryWhichWithFeature: $separator$indentBulletPoint$indentFailingBulletPoint$explanatoryPointWithIndent"

    fun entry(index: Int) = String.format(entryWithIndex, index)

    fun Expect<String>.entrySuccess(index: Int, actual: Any, expected: String): Expect<String> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentSuccessfulBulletPoint$anEntryAfterSuccess$expected"
        )
    }

    fun Expect<String>.entryFailing(index: Int, actual: Any, expected: String): Expect<String> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$anEntryAfterFailing$expected"
        )
    }

    nonNullableCases(
        describePrefix,
        containsInOrderOnlyEntries,
        containsInOrderOnlyNullableEntries
    ) { containsEntriesFunArr ->

        fun Expect<Iterable<Double>>.containsEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit
        ) = containsEntriesFunArr(t, tX)

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            it("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains("$rootBulletPoint$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, "$isLessThanDescr: 1.0")
                        containsNot(additionalEntries)
                        containsSize(0, 1)
                    }
                }
            }
            it("$isLessThanFun(1.0) and $isGreaterThanFun(4.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, "$isLessThanDescr: 1.0")
                        entryFailing(1, sizeExceeded, "$isGreaterThanDescr: 4.0")
                        containsNot(additionalEntries)
                        containsSize(0, 2)
                    }
                }
            }
            //TODO remove with 1.0.0
            it("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmpty.containsEntriesFun({
                        @Suppress("DEPRECATION")
                        asAssert().returnValueOf(subject::dec).toBe(1.0)
                    })
                }.toThrow<AssertionError> {
                    messageContains(ErrorMessages.SUBJECT_ACCESSED_TOO_EARLY.getDefault())
                }
            }
        }

        val fluent = assert(oneToFour)
        context("iterable $oneToFour") {

            context("happy case") {
                it("1.0, 2.0, 3.0, 4.0, 4.0") {
                    fluent.containsEntriesFun(
                        { toBe(1.0) },
                        { toBe(2.0) },
                        { toBe(3.0) },
                        { toBe(4.0) },
                        { toBe(4.0) })
                }
                it("$isLessThanFun(5.0), $isLessThanFun(5.0), $isLessThanFun(5.0), $isLessThanFun(5.0), $isLessThanFun(5.0)") {
                    fluent.containsEntriesFun(
                        { isLessThan(5.0) },
                        { isLessThan(5.0) },
                        { isLessThan(5.0) },
                        { isLessThan(5.0) },
                        { isLessThan(5.0) })
                }
            }

            context("error cases ... throws AssertionError") {

                it("$isLessThanFun(5.0), 1.0, 2.0, $isGreaterThanFun(2.0), 4.0 -- wrong order") {
                    expect {
                        fluent.containsEntriesFun(
                            { isLessThan(5.0) },
                            { toBe(1.0) },
                            { toBe(2.0) },
                            { isGreaterThan(2.0) },
                            { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            entrySuccess(0, 1.0, "$isLessThanDescr: 5.0")
                            entryFailing(1, 2.0, "$toBeDescr: 1.0")
                            entryFailing(2, 3.0, "$toBeDescr: 2.0")
                            entrySuccess(3, 4.0, "$isGreaterThanDescr: 2.0")
                            entrySuccess(4, 4.0, "$toBeDescr: 4.0")
                            containsSize(5, 5)
                        }
                    }
                }

                it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
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

                it("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
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
                it("1.0, 3.0, $isGreaterThanFun(4.0) -- $isGreaterThanFun(4.0) is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        fluent.containsEntriesFun({ toBe(1.0) }, { toBe(3.0) }, { isGreaterThan(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
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
                it("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 too much") {
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
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
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
    }

    nullableCases(describePrefix) {

        describeFun("${containsInOrderOnlyNullableEntries.name} for nullable") {

            val list = listOf(null, 1.0, null, 3.0).asIterable()
            val fluent = verbs.check(list)
            context("iterable $list") {

                context("happy case") {
                    it("null, 1.0, null, 3.0") {
                        fluent.containsInOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, null, { toBe(3.0) })
                    }
                    it("null, $isLessThanFun(5.0), null, $isLessThanFun(5.0)") {
                        fluent.containsInOrderOnlyNullableEntriesFun(
                            null,
                            { isLessThan(5.0) },
                            null,
                            { isLessThan(5.0) }
                        )
                    }
                }

                context("error cases (throws AssertionError)") {

                    it("null, null, $isLessThanFun(5.0), $isGreaterThanFun(2.0) -- wrong order") {
                        expect {
                            fluent.containsInOrderOnlyNullableEntriesFun(
                                null,
                                null,
                                { isLessThan(5.0) },
                                { isGreaterThan(2.0) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                                entrySuccess(0, "null", "$isDescr: null")
                                entryFailing(1, 1.0, "$isDescr: null")
                                entryFailing(2, "null", "$isLessThanDescr: 5.0")
                                entrySuccess(3, 3.0, "$isGreaterThanDescr: 2.0")
                                containsSize(4, 4)
                            }
                        }
                    }

                    it("null, 1.0, null, 3.0, null -- null too much") {
                        expect {
                            fluent.containsInOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, null, { toBe(3.0) }, null)
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
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
        }
    }
})

