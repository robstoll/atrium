package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.builders.utils.subExpect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.AssertionVerbFactory
import ch.tutteli.atrium.translations.ErrorMessages

abstract class IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInAnyOrderEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    containsInAnyOrderNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsInAnyOrderEntries.forSubjectLess({ toBe(2.5) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        containsInAnyOrderNullableEntries.forSubjectLess(null, arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        verbs, describePrefix, listOf(1.2, 2.0),
        *containsInAnyOrderEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toBe(1.2) }, arrayOf(subExpect { toBe(2.0) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        verbs, "$describePrefix[nullable] ", listOf(1.2, 2.0),
        *containsInAnyOrderNullableEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toBe(1.2) }, arrayOf(subExpect { toBe(2.0) })
        )
    ) {})

    val assert: (Iterable<Double>) -> Expect<Iterable<Double>> = verbs::check
    val expect = verbs::checkException

    fun Expect<Iterable<Double?>>.containsInAnyOrderNullableEntriesFun(
        t: (Expect<Double>.() -> Unit)?,
        vararg tX: (Expect<Double>.() -> Unit)?
    ) = containsInAnyOrderNullableEntries(this, t, tX)

    nonNullableCases(
        describePrefix,
        containsInAnyOrderEntries,
        containsInAnyOrderNullableEntries
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
                        contains.exactly(1).values(
                            "$rootBulletPoint$containsInAnyOrder: $separator",
                            "$anEntryWhich: $separator",
                            "$isLessThanDescr: 1.0",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
            }
            it("$isLessThanFun(1.0) and $isGreaterThanFun(2.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(2.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains.exactly(2).values(
                            "$anEntryWhich: $separator",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                        contains.exactly(1).values(
                            "$rootBulletPoint$containsInAnyOrder: $separator",
                            "$isLessThanDescr: 1.0",
                            "$isGreaterThanDescr: 2.0"
                        )
                    }
                }
            }
            //TODO remove with 1.0.0
            it("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmpty.containsEntriesFun({
                        @Suppress("DEPRECATION")
                        asAssert().returnValueOf(subject::dec).asExpect().toBe(1.0)
                    })
                }.toThrow<AssertionError> { messageContains(ErrorMessages.SUBJECT_ACCESSED_TOO_EARLY.getDefault()) }
            }
        }

        val fluent = assert(oneToSeven)
        context("iterable $oneToSeven") {
            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        fluent.containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhich: $separator",
                                "$isGreaterThanDescr: 1.0",
                                "$isLessThanDescr: 2.0",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                        }
                    }
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1)") {
                it("does not throw an exception") {
                    fluent.containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.1) })
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1) and another entry which is $isLessThanFun(2.0)") {
                it("does not throw an exception") {
                    //finds twice the entry 1.0 but that is fine since we do not search for unique entries in this case
                    fluent.containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.1) }, { isLessThan(2.0) })
                }
            }

        }
    }

    nullableCases(describePrefix) {

        describeFun("${containsInAnyOrderNullableEntries.name} for nullable") {

            val list = listOf(null, 1.0, null, 3.0).asIterable()
            val fluent = verbs.check(list)
            context("iterable $list") {
                context("happy cases (do not throw)") {
                    it("$toBeFun(1.0)") {
                        fluent.containsInAnyOrderNullableEntriesFun({ toBe(1.0) })
                    }
                    it("null") {
                        fluent.containsInAnyOrderNullableEntriesFun(null)
                    }
                    it("$toBeFun(1.0) and null") {
                        fluent.containsInAnyOrderNullableEntriesFun({ toBe(1.0) }, null)
                    }
                    it("$toBeFun(3.0), null and $toBeFun(1.0)") {
                        fluent.containsInAnyOrderNullableEntriesFun({ toBe(3.0) }, null, { toBe(1.0) })
                    }
                    it("null, null, null") {
                        // finds twice the same entry with null but that is fine
                        // since we do not search for unique entries in this case
                        fluent.containsInAnyOrderNullableEntriesFun(null, null, null)
                    }
                }

                context("failing cases") {
                    it("$toBeFun(2.0)") {
                        expect {
                            fluent.containsInAnyOrderNullableEntriesFun({ toBe(2.0) })
                        }.toThrow<AssertionError> {
                            messageContains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhich: $separator",
                                "$toBeDescr: 2.0",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                        }
                    }

                    it("$isLessThanFun(1.0) and $isLessThanFun(3.0)") {
                        expect {
                            fluent.containsInAnyOrderNullableEntriesFun({ isLessThan(1.0) }, { isGreaterThan(3.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(2).values(
                                    "$anEntryWhich: $separator",
                                    "$numberOfOccurrences: 0",
                                    "$atLeast: 1"
                                )
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrder: $separator",
                                    "$isLessThanDescr: 1.0",
                                    "$isGreaterThanDescr: 3.0"
                                )
                            }
                        }
                    }
                }
            }

            context("iterable $oneToSeven") {
                it("null, throws an AssertionError") {
                    expect {
                        verbs.check(oneToSeven as Iterable<Double?>).containsInAnyOrderNullableEntriesFun(null)
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$rootBulletPoint$containsInAnyOrder: $separator",
                            "$anEntryWhich: $separator",
                            "$isDescr: null",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
            }
        }
    }
})
