package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

abstract class IterableNotToHaveElementsOrAnyExpectationsSpec(
    notToHaveElementsOrAny: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    notToHaveElementsOrAnyNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        notToHaveElementsOrAny.forSubjectLessTest { toEqual(2.5) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        notToHaveElementsOrAnyNullable.forSubjectLessTest(null)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        notToHaveElementsOrAny.forExpectationCreatorTest("$toBeGreaterThanDescr\\s+: 1.0") { toBeGreaterThan(1.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        notToHaveElementsOrAnyNullable.forExpectationCreatorTest("$toBeGreaterThanDescr\\s+: 1.0") { toBeGreaterThan(1.0) }
    ) {})

    val notToHaveElementsOrAnyDescr = DescriptionIterableLikeProof.NOT_TO_HAVE_ELEMENTS_OR_ANY.string
    val noSuchElementDescr = DescriptionIterableLikeProof.NEITHER_EMPTY_NOR_ELEMENT_FOUND.string

    nonNullableCases(
        describePrefix,
        notToHaveElementsOrAny,
        notToHaveElementsOrAnyNullable
    ) { notToHaveElementsOrAnyFun ->

        context("empty collection") {
            it("does not throw") {
                expect(fluentEmpty()).notToHaveElementsOrAnyFun { toBeLessThan(1.0) }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("search for entry which needs $toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one expectation") {
                    expect {
                        expect(oneToSeven()).notToHaveElementsOrAnyFun { toBeGreaterThan(1.0); toBeLessThan(2.0) }
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            //TODO 1.4.0 would be nice to see the elements of a sequence
                            "${expectationVerb}\\s+:.*$lineSeparator" +
                                "\\Q$g\\E${notToHaveElementsOrAnyDescr} : $lineSeparator" +
                                "$indentG$explanatoryBulletPoint$toBeGreaterThanDescr\\s+: 1.0$lineSeparator" +
                                "$indentG$explanatoryBulletPoint$toBeLessThanDescr\\s+: 2.0$lineSeparator" +
                                //TODO use !! it had next elements
                                //TODO 1.4.0 how about we include how many next elements were found?
                                "\\Q$indentG$explanatoryBulletPoint$noSuchElementDescr\\E"
                        )
                    )
                }
            }

            context("search for entry which $toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.1)") {
                it("does not throw an exception") {
                    expect(oneToSeven()).notToHaveElementsOrAnyFun { toBeGreaterThan(1.0); toBeLessThan(2.1) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(notToHaveElementsOrAnyNullable) {
            val notToHaveElementsOrAnyFun = notToHaveElementsOrAnyNullable.lambda

            context("iterable ${oneToSevenNullable().toList()}") {
                context("happy cases (do not throw)") {
                    it("$toEqualFun(1.0)") {
                        expect(oneToSevenNullable()).notToHaveElementsOrAnyFun { toEqual(1.0) }
                    }
                    it("null") {
                        expect(oneToSevenNullable()).notToHaveElementsOrAnyFun(null)
                    }
                }

                context("failing cases") {
                    it("$toEqualFun(2.0)") {
                        expect {
                            expect(oneToSevenNullable()).notToHaveElementsOrAnyFun { toEqual(2.0) }
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$notToHaveElementsOrAnyDescr : $lineSeparator",
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
                        expect(oneToSeven() as Iterable<Double?>).notToHaveElementsOrAnyFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$notToHaveElementsOrAnyDescr : $lineSeparator",
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
