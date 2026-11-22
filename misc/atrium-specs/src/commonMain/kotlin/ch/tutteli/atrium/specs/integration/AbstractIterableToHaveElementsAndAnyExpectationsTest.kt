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
        toHaveElementsAndAny.forSubjectLessTest { toEqual(2.5) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toHaveElementsAndAnyNullable.forSubjectLessTest(null)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        toHaveElementsAndAny.forExpectationCreatorTest("$toBeGreaterThanDescr: 1.1") { toBeGreaterThan(1.1) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        toHaveElementsAndAnyNullable.forExpectationCreatorTest("$toBeGreaterThanDescr: 1.1") { toBeGreaterThan(1.1) }
    ) {})

    nonNullableCases(
        describePrefix,
        toHaveElementsAndAny,
        toHaveElementsAndAnyNullable
    ) { toHaveElementsAndAnyFun ->

        context("empty collection") {
            it("throws AssertionError as there needs to be at least one element") {
                expect {
                    expect(emptyIterable()).toHaveElementsAndAnyFun { toBeLessThan(1.1) }
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhichNeedsDescr: $separator",
                        "$toBeLessThanDescr: 1.1",
                        noSuchElementDescr
                    )

                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.1)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        expect(oneToSeven()).toHaveElementsAndAnyFun { toBeGreaterThan(1.1); toBeLessThan(2.1) }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            "$rootBulletPoint$toContainInAnyOrder: $separator",
                            "$anElementWhichNeedsDescr: $separator",
                            "$toBeGreaterThanDescr: 1.1",
                            "$toBeLessThanDescr: 2.1",
                            noSuchElementDescr
                        )
                    }
                }
            }

            context("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.2)") {
                it("does not throw an exception") {
                    expect(oneToSeven()).toHaveElementsAndAnyFun { toBeGreaterThan(1.1); toBeLessThan(2.2) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(toHaveElementsAndAnyNullable) {
            val toHaveElementsAndAnyFun = toHaveElementsAndAnyNullable.lambda

            context("iterable ${oneToSevenNullable().toList()}") {
                context("happy cases (do not throw)") {
                    it("$toEqualFun(1.1)") {
                        expect(oneToSevenNullable()).toHaveElementsAndAnyFun { toEqual(1.1) }
                    }
                    it("null") {
                        expect(oneToSevenNullable()).toHaveElementsAndAnyFun(null)
                    }
                }

                context("failing cases") {
                    it("$toEqualFun(2.1)") {
                        expect {
                            expect(oneToSevenNullable()).toHaveElementsAndAnyFun { toEqual(2.1) }
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrder: $separator",
                                    "$anElementWhichNeedsDescr: $separator",
                                    "$toEqualDescr: 2.1",
                                    noSuchElementDescr
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
                                "$anElementWhichNeedsDescr: $separator",
                                "$toEqualDescr: null",
                                noSuchElementDescr
                            )
                        }
                    }
                }
            }
        }
    }
})
