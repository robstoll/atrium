package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionComparableExpectation

abstract class IterableToHaveElementsAndAnyExpectationsSpec(
    toHaveElementsAndAny: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    toHaveElementsAndAnyNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    val toBeGreaterThanDescr = DescriptionComparableExpectation.TO_BE_GREATER_THAN.getDefault()

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        toHaveElementsAndAny.forSubjectLess { toEqual(2.5) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toHaveElementsAndAnyNullable.forSubjectLess(null)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        toHaveElementsAndAny.forAssertionCreatorSpec("$toBeGreaterThanDescr: 1.0") { toBeGreaterThan(1.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        toHaveElementsAndAnyNullable.forAssertionCreatorSpec("$toBeGreaterThanDescr: 1.0") { toBeGreaterThan(1.0) }
    ) {})

    nonNullableCases(
        describePrefix,
        toHaveElementsAndAny,
        toHaveElementsAndAnyNullable
    ) { toHaveElementsAndAnyFun ->

        context("empty collection") {
            it("throws AssertionError as there needs to be at least one element") {
                expect {
                    expect(fluentEmpty()).toHaveElementsAndAnyFun { toBeLessThan(1.0) }
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhich: $separator",
                        "$toBeLessThanDescr: 1.0",
                        noSuchEntryDescr
                    )

                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("search for entry which $toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        expect(oneToSeven()).toHaveElementsAndAnyFun { toBeGreaterThan(1.0); toBeLessThan(2.0) }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            "$rootBulletPoint$toContainInAnyOrder: $separator",
                            "$anElementWhich: $separator",
                            "$toBeGreaterThanDescr: 1.0",
                            "$toBeLessThanDescr: 2.0",
                            noSuchEntryDescr
                        )
                    }
                }
            }

            context("search for entry which $toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.1)") {
                it("does not throw an exception") {
                    expect(oneToSeven()).toHaveElementsAndAnyFun { toBeGreaterThan(1.0); toBeLessThan(2.1) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(toHaveElementsAndAnyNullable) {
            val toHaveElementsAndAnyFun = toHaveElementsAndAnyNullable.lambda

            context("iterable ${oneToSevenNullable().toList()}") {
                context("happy cases (do not throw)") {
                    it("$toEqualFun(1.0)") {
                        expect(oneToSevenNullable()).toHaveElementsAndAnyFun { toEqual(1.0) }
                    }
                    it("null") {
                        expect(oneToSevenNullable()).toHaveElementsAndAnyFun(null)
                    }
                }

                context("failing cases") {
                    it("$toEqualFun(2.0)") {
                        expect {
                            expect(oneToSevenNullable()).toHaveElementsAndAnyFun { toEqual(2.0) }
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrder: $separator",
                                    "$anElementWhich: $separator",
                                    "$toEqualDescr: 2.0",
                                    noSuchEntryDescr
                                )
                            }
                        }
                    }
                }
            }

            context("iterable ${oneToSeven().toList()}") {
                it("null, throws an AssertionError") {
                    expect {
                        expect(oneToSeven() as Iterable<Double?>).toHaveElementsAndAnyFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhich: $separator",
                                "$toEqualDescr: null",
                                noSuchEntryDescr
                            )
                        }
                    }
                }
            }
        }
    }
})
