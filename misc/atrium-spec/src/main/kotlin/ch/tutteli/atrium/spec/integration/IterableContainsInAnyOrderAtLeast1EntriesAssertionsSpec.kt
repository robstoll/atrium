package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInAnyOrderEntriesPair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>) -> Assert<Iterable<Double>>>,
    containsInAnyOrderNullableEntriesPair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsInAnyOrderEntriesPair.first to mapToCreateAssertion { containsInAnyOrderEntriesPair.second(this, { toBe(2.5) }, arrayOf()) },
        containsInAnyOrderNullableEntriesPair.first to mapToCreateAssertion { containsInAnyOrderNullableEntriesPair.second(this, null, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsInAnyOrderEntriesPair.first, { containsInAnyOrderEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }, listOf(2.5).asIterable(), listOf()),
        checkingTriple(containsInAnyOrderNullableEntriesPair.first, { containsInAnyOrderNullableEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }, listOf(2.5).asIterable(), listOf())
    ) {})

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException

    val (containsInAnyOrderNullableEntries, containsInAnyOrderNullableEntriesFunArr) = containsInAnyOrderNullableEntriesPair
    fun Assert<Iterable<Double?>>.containsInAnyOrderNullableEntriesFun(t: (Assert<Double>.() -> Unit)?, vararg tX: (Assert<Double>.() -> Unit)?)
        = containsInAnyOrderNullableEntriesFunArr(t, tX)

    nonNullableCases(
        describePrefix,
        containsInAnyOrderEntriesPair,
        containsInAnyOrderNullableEntriesPair
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
            test("$isLessThanFun(1.0) and $isGreaterThanFun(2.0) throws AssertionError") {
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
            test("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmpty.containsEntriesFun({ returnValueOf(subject::dec).toBe(1.0) })
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE) } }
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

        context("search for entry where the lambda does not specify any assertion") {
            it("throws an ${IllegalArgumentException::class.simpleName}") {
                expect {
                    fluent.containsEntriesFun({})
                }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(containsInAnyOrderNullableEntries) {
            absentSubjectTests(verbs, Assert<Iterable<Double?>>::containsInAnyOrderNullableEntriesFun)

            val list = listOf(null, 1.0, null, 3.0)
            val fluent = verbs.checkImmediately(list)
            context("iterable $list") {
                context("happy cases (do not throw)") {
                    test("$toBeFun(1.0)") {
                        fluent.containsInAnyOrderNullableEntriesFun({ toBe(1.0) })
                    }
                    test("null") {
                        fluent.containsInAnyOrderNullableEntriesFun(null)
                    }
                    test("$toBeFun(1.0) and null") {
                        fluent.containsInAnyOrderNullableEntriesFun({ toBe(1.0) }, null)
                    }
                    test("$toBeFun(3.0), null and $toBeFun(1.0)") {
                        fluent.containsInAnyOrderNullableEntriesFun({ toBe(3.0) }, null, { toBe(1.0) })
                    }
                    test("null, null, null") {
                        //finds twice the same entry with null but that is fine since we do not search for unique entries in this case
                        fluent.containsInAnyOrderNullableEntriesFun(null, null, null)
                    }
                }

                context("failing cases") {
                    test("$toBeFun(2.0)") {
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

                    test("$isLessThanFun(1.0) and $isLessThanFun(3.0)") {
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
                test("null, throws an AssertionError") {
                    expect {
                        assert(oneToSeven).containsInAnyOrderNullableEntriesFun(null)
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

            context("search for entry where the lambda does not specify any assertion") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    expect {
                        fluent.containsInAnyOrderNullableEntriesFun({})
                    }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
                }
            }
        }
    }
})
