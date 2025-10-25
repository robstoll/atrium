package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

abstract class AbstractIterableNotToHaveElementsOrAnyExpectationsTest(
    notToHaveElementsOrAny: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    notToHaveElementsOrAnyNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        notToHaveElementsOrAny.forSubjectLessTest { toEqual(2.5) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        notToHaveElementsOrAnyNullable.forSubjectLessTest(null)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        notToHaveElementsOrAny.forExpectationCreatorTest("$toBeGreaterThanDescr: 1.1") { toBeGreaterThan(1.1) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        notToHaveElementsOrAnyNullable.forExpectationCreatorTest("$toBeGreaterThanDescr: 1.1") { toBeGreaterThan(1.1) }
    ) {})

    val notToHaveElementsOrAnyDescr = DescriptionIterableLikeExpectation.NOT_TO_HAVE_ELEMENTS_OR_ANY.getDefault()
    val noSuchElementDescr = DescriptionIterableLikeExpectation.NEITHER_EMPTY_NOR_ELEMENT_FOUND.getDefault()

    nonNullableCases(
        describePrefix,
        notToHaveElementsOrAny,
        notToHaveElementsOrAnyNullable
    ) { notToHaveElementsOrAnyFun ->

        context("empty collection") {
            it("does not throw") {
                expect(fluentEmpty()).notToHaveElementsOrAnyFun { toBeLessThan(1.1) }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("search for entry which needs $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.1)") {
                it("throws AssertionError containing both assumptions in one expectation") {
                    expect {
                        expect(oneToSeven()).notToHaveElementsOrAnyFun { toBeGreaterThan(1.1); toBeLessThan(2.1) }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            "$rootBulletPoint$notToHaveElementsOrAnyDescr: $separator",
                            "$toBeGreaterThanDescr: 1.1",
                            "$toBeLessThanDescr: 2.1",
                            noSuchElementDescr
                        )
                    }
                }
            }

            context("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.2)") {
                it("does not throw an exception") {
                    expect(oneToSeven()).notToHaveElementsOrAnyFun { toBeGreaterThan(1.1); toBeLessThan(2.2) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(notToHaveElementsOrAnyNullable) {
            val notToHaveElementsOrAnyFun = notToHaveElementsOrAnyNullable.lambda

            context("iterable ${oneToSevenNullable().toList()}") {
                context("happy cases (do not throw)") {
                    it("$toEqualFun(1.1)") {
                        expect(oneToSevenNullable()).notToHaveElementsOrAnyFun { toEqual(1.1) }
                    }
                    it("null") {
                        expect(oneToSevenNullable()).notToHaveElementsOrAnyFun(null)
                    }
                }

                context("failing cases") {
                    it("$toEqualFun(2.1)") {
                        expect {
                            expect(oneToSevenNullable()).notToHaveElementsOrAnyFun { toEqual(2.1) }
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$notToHaveElementsOrAnyDescr: $separator",
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
                        expect(oneToSeven() as Iterable<Double?>).notToHaveElementsOrAnyFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$rootBulletPoint$notToHaveElementsOrAnyDescr: $separator",
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
