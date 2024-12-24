package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator

abstract class IterableToHaveElementsAndAnyExpectationsSpec(
    toHaveElementsAndAny: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    toHaveElementsAndAnyNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        toHaveElementsAndAny.forSubjectLess { toEqual(2.5) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toHaveElementsAndAnyNullable.forSubjectLess(null)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        toHaveElementsAndAny.forAssertionCreatorSpec("$toBeGreaterThanDescr\\s+: 1.0") { toBeGreaterThan(1.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        toHaveElementsAndAnyNullable.forAssertionCreatorSpec("$toBeGreaterThanDescr\\s+: 1.0") { toBeGreaterThan(1.0) }
    ) {})

    nonNullableCases(
        describePrefix,
        toHaveElementsAndAny,
        toHaveElementsAndAnyNullable
    ) { toHaveElementsAndAnyFun ->

        context("empty iterable") {
            it("throws AssertionError as there needs to be at least one element") {
                expect {
                    expect(fluentEmpty()).toHaveElementsAndAnyFun { toBeLessThan(1.0) }
                }.toThrow<AssertionError> {
                    messageToContain(
                        //TODO 1.3.0 should be x and not g
                        "$g$toContainInAnyOrder : $lineSeparator",
                        "$anElementWhichNeedsDescr : $lineSeparator",
                        "$toBeLessThanDescr : 1.0",
                        //TODO 1.3.0 should not be necessary
                        noSuchElementDescr
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
                        message {
                            toContain.exactly(1).values(
                                "$g$toContainInAnyOrder : $lineSeparator",
                                "$anElementWhichNeedsDescr : $lineSeparator"
                            )
                            toContain.exactly(1).regex(
                                "$toBeGreaterThanDescr\\s+: 1.0",
                                "$toBeLessThanDescr\\s+: 2.0",
                                noSuchElementDescr
                            )
                        }
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
                                    "$g$toContainInAnyOrder : $lineSeparator",
                                    "$anElementWhichNeedsDescr : $lineSeparator",
                                    "$toEqualDescr : 2.0",
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
                                "$g$toContainInAnyOrder : $lineSeparator",
                                "$anElementWhichNeedsDescr : $lineSeparator",
                                "$toEqualDescr : null",
                                noSuchElementDescr
                            )
                        }
                    }
                }
            }
        }
    }
})
