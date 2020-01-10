package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.ErrorMessages

abstract class IterableAnyAssertionsSpec(
    any: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    anyNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase({

    val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        any.forSubjectLess { toBe(2.5) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        anyNullable.forSubjectLess(null)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        any.forAssertionCreatorSpec("$isGreaterThanDescr: 1.0") { isGreaterThan(1.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        anyNullable.forAssertionCreatorSpec("$isGreaterThanDescr: 1.0") { isGreaterThan(1.0) }
    ) {})

    nonNullableCases(
        describePrefix,
        any,
        anyNullable
    ) { anyFun ->

        context("empty collection") {
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
            //TODO remove with 0.10.0
            it("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmpty.anyFun {
                        @Suppress("DEPRECATION")
                        asAssert().returnValueOf(subject::dec).asExpect().toBe(1.0)
                    }
                }.toThrow<AssertionError> { messageContains(ErrorMessages.SUBJECT_ACCESSED_TOO_EARLY.getDefault()) }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        expect(oneToSeven()).anyFun { isGreaterThan(1.0); isLessThan(2.0) }
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
                    expect(oneToSeven()).anyFun { isGreaterThan(1.0); isLessThan(2.1) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun("${anyNullable.name} for nullable") {
            val anyNullableFun = anyNullable.lambda

            context("iterable ${oneToSevenNullable().toList()}") {
                context("happy cases (do not throw)") {
                    it("$toBeFun(1.0)") {
                        expect(oneToSevenNullable()).anyNullableFun { toBe(1.0) }
                    }
                    it("null") {
                        expect(oneToSevenNullable()).anyNullableFun(null)
                    }
                }

                context("failing cases") {
                    it("$toBeFun(2.0)") {
                        expect {
                            expect(oneToSevenNullable()).anyNullableFun { toBe(2.0) }
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

            context("iterable ${oneToSeven().toList()}") {
                it("null, throws an AssertionError") {
                    expect {
                        expect(oneToSeven() as Iterable<Double?>).anyNullableFun(null)
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
