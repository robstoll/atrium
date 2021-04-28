package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionComparableAssertion

abstract class IterableToHaveNextAndAnyExpectationsSpec(
    notToBeEmptyAndAny: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    notToBeEmptyAndAnyNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        notToBeEmptyAndAny.forSubjectLess { toEqual(2.5) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        notToBeEmptyAndAnyNullable.forSubjectLess(null)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        notToBeEmptyAndAny.forAssertionCreatorSpec("$isGreaterThanDescr: 1.0") { toBeGreaterThan(1.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        notToBeEmptyAndAnyNullable.forAssertionCreatorSpec("$isGreaterThanDescr: 1.0") { toBeGreaterThan(1.0) }
    ) {})

    nonNullableCases(
        describePrefix,
        notToBeEmptyAndAny,
        notToBeEmptyAndAnyNullable
    ) { notToBeEmptyAndAnyFun ->

        context("empty collection") {
            it("throws AssertionError as there needs to be at least one element") {
                expect {
                    expect(fluentEmpty()).notToBeEmptyAndAnyFun { toBeLessThan(1.0) }
                }.toThrow<AssertionError> {
                    messageContains(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhich: $separator",
                        "$toBeLessThanDescr: 1.0",
                        "$numberOfOccurrences: 0",
                        "$atLeastDescr: 1"
                    )

                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("search for entry which $toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        expect(oneToSeven()).notToBeEmptyAndAnyFun { toBeGreaterThan(1.0); toBeLessThan(2.0) }
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$rootBulletPoint$toContainInAnyOrder: $separator",
                            "$anElementWhich: $separator",
                            "$isGreaterThanDescr: 1.0",
                            "$toBeLessThanDescr: 2.0",
                            "$numberOfOccurrences: 0",
                            "$atLeastDescr: 1"
                        )
                    }
                }
            }

            context("search for entry which $toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.1)") {
                it("does not throw an exception") {
                    expect(oneToSeven()).notToBeEmptyAndAnyFun { toBeGreaterThan(1.0); toBeLessThan(2.1) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(notToBeEmptyAndAnyNullable) {
            val notToBeEmptyAndAnyFun = notToBeEmptyAndAnyNullable.lambda

            context("iterable ${oneToSevenNullable().toList()}") {
                context("happy cases (do not throw)") {
                    it("$toEqualFun(1.0)") {
                        expect(oneToSevenNullable()).notToBeEmptyAndAnyFun { toEqual(1.0) }
                    }
                    it("null") {
                        expect(oneToSevenNullable()).notToBeEmptyAndAnyFun(null)
                    }
                }

                context("failing cases") {
                    it("$toEqualFun(2.0)") {
                        expect {
                            expect(oneToSevenNullable()).notToBeEmptyAndAnyFun { toEqual(2.0) }
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrder: $separator",
                                    "$anElementWhich: $separator",
                                    "$toBeDescr: 2.0",
                                    "$numberOfOccurrences: 0",
                                    "$atLeastDescr: 1"
                                )
                            }
                        }
                    }
                }
            }

            context("iterable ${oneToSeven().toList()}") {
                it("null, throws an AssertionError") {
                    expect {
                        expect(oneToSeven() as Iterable<Double?>).notToBeEmptyAndAnyFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhich: $separator",
                                "$isDescr: null",
                                "$numberOfOccurrences: 0",
                                "$atLeastDescr: 1"
                            )
                        }
                    }
                }
            }
        }
    }
})
