package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.ErrorMessages

abstract class IterableAnyAssertionsSpec(
    verbs: AssertionVerbFactory,
    any: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    anyNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        any.forSubjectLess { toBe(2.5) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        anyNullable.forSubjectLess(null)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        verbs, describePrefix, oneToSeven,
        any.forAssertionCreatorSpec("$isGreaterThanDescr: 1.0") { isGreaterThan(1.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        verbs, "$describePrefix[nullable Element] ", oneToSeven,
        anyNullable.forAssertionCreatorSpec("$isGreaterThanDescr: 1.0") { isGreaterThan(1.0) }
    ) {})

    val assert: (Iterable<Double>) -> Expect<Iterable<Double>> = verbs::check
    val expect = verbs::checkException

    nonNullableCases(
        describePrefix,
        any,
        anyNullable
    ) { anyFun ->

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            it("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmpty.anyFun { isLessThan(1.0) }
                }.toThrow<AssertionError> {
                    messageContains(
                        "$rootBulletPoint$containsInAnyOrder: $separator",
                        "$anEntryWhich: $separator",
                        "$isLessThanDescr: 1.0",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }
            //TODO remove with 1.0.0
            it("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmpty.anyFun {
                        @Suppress("DEPRECATION")
                        asAssert().returnValueOf(subject::dec).asExpect().toBe(1.0)
                    }
                }.toThrow<AssertionError> { messageContains(ErrorMessages.SUBJECT_ACCESSED_TOO_EARLY.getDefault()) }
            }
        }

        val fluent = assert(oneToSeven)
        context("iterable $oneToSeven") {
            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        fluent.anyFun { isGreaterThan(1.0); isLessThan(2.0) }
                    }.toThrow<AssertionError> {
                        messageContains(
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

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1)") {
                it("does not throw an exception") {
                    fluent.anyFun { isGreaterThan(1.0); isLessThan(2.1) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun("${anyNullable.name} for nullable") {
            val anyNullableFun = anyNullable.lambda

            val list = listOf(null, 1.0, null, 3.0).asIterable()
            val fluent = verbs.check(list)
            context("iterable $list") {
                context("happy cases (do not throw)") {
                    it("$toBeFun(1.0)") {
                        fluent.anyNullableFun { toBe(1.0) }
                    }
                    it("null") {
                        fluent.anyNullableFun(null)
                    }
                }

                context("failing cases") {
                    it("$toBeFun(2.0)") {
                        expect {
                            fluent.anyNullableFun { toBe(2.0) }
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrder: $separator",
                                    "$anEntryWhich: $separator",
                                    "$toBeDescr: 2.0",
                                    "$numberOfOccurrences: 0",
                                    "$atLeast: 1"
                                )
                            }
                        }
                    }
                }
            }

            context("iterable $oneToSeven") {
                it("null, throws an AssertionError") {
                    expect {
                        verbs.check(oneToSeven as Iterable<Double?>).anyNullableFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
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
    }
})
