package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

abstract class IterableToHaveElementsAndNoneExpectationsSpec(
    toHaveElementsAndNone: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    toHaveElementsAndNoneNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        toHaveElementsAndNone.forSubjectLessTest { toEqual(2.3) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        toHaveElementsAndNoneNullable.forSubjectLessTest { toEqual(2.3) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        toHaveElementsAndNone.forExpectationCreatorTest("$toBeGreaterThanDescr\\s+: 10.0") { toBeGreaterThan(10.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        toHaveElementsAndNoneNullable.forExpectationCreatorTest("$toBeGreaterThanDescr\\s+: 10.0") { toBeGreaterThan(10.0) }
    ) {})

    val containsNotDescr = DescriptionIterableLikeExpectation.NOT_TO_CONTAIN.getDefault()

    nonNullableCases(
        describePrefix,
        toHaveElementsAndNone,
        toHaveElementsAndNoneNullable
    ) { toHaveElementsAndNoneFun ->

        context("empty collection") {
            it("throws AssertionError as there needs to be at least one element") {
                expect {
                    expect(fluentEmpty()).toHaveElementsAndNoneFun { toBeLessThan(1.0) }
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex(
                            "${toHaveDescr}\\s+: ${aNextElement}$lineSeparator" +
                                "$indentRoot\\Q$explanatoryBulletPoint\\E$containsNotDescr: $lineSeparator" +
                                "$indentRoot$indentList\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $lineSeparator" +
                                "$indentList$afterExplanatory$toBeLessThanDescr: 1.0.*"
                        )
                    }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("happy case") {
                listOf(1.1, 2.2, 3.3).forEach {
                    it("$toEqualDescr($it) does not throw") {
                        expect(oneToSeven()).toHaveElementsAndNoneFun { toEqual(1.1) }
                    }
                }
            }

            context("failing cases; search string at different positions") {
                it("$toEqualDescr(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toHaveElementsAndNoneFun { toEqual(4.0) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $lineSeparator" +
                                        "$indentRoot\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $lineSeparator" +
                                        "$afterExplanatory$toEqualDescr: 4.0.*$lineSeparator" +
                                        "$afterExplanatoryIndent\\Q$bb$mismatches:\\E $lineSeparator" +
                                        "$afterMismatchedWarning${mismatchedIndex(2, "4.0")}.*$lineSeparator" +
                                        "$afterMismatchedWarning${mismatchedIndex(3, "4.0")}.*$lineSeparator" +
                                    "$afterMismatchedWarning${mismatchedIndex(8, "4.0")}.*"
                            )
                        }
                    }
                }
            }
        }
    }
    nullableCases(describePrefix) {
        describeFun(toHaveElementsAndNoneNullable) {
            val toHaveElementsAndNoneFun = toHaveElementsAndNoneNullable.lambda

            context("iterable ${oneToSeven().toList()}") {
                it("null does not throw") {
                    expect(oneToSeven() as Iterable<Double?>).toHaveElementsAndNoneFun(null)
                }
            }
            context("iterable ${oneToSevenNullable().toList()}") {
                it("null throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).toHaveElementsAndNoneFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $lineSeparator" +
                                        "$indentRoot\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $lineSeparator" +
                                        "$afterExplanatory$toEqualDescr: null$lineSeparator" +
                                        "$afterExplanatoryIndent\\Q$bb$mismatches:\\E $lineSeparator" +
                                        "$afterMismatchedWarning${mismatchedIndex(1, "null")}.*$lineSeparator" +
                                    "$afterMismatchedWarning${mismatchedIndex(5, "null")}.*"
                            )
                        }
                    }
                }

                it("1.0 throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).toHaveElementsAndNoneFun { toEqual(1.0) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $lineSeparator" +
                                        "$indentRoot\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $lineSeparator" +
                                        "$afterExplanatory$toEqualDescr: 1.0.*$lineSeparator" +
                                        "$afterExplanatoryIndent\\Q$bb$mismatches:\\E $lineSeparator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.0")}.*"
                            )
                        }
                    }
                }
            }
        }
    }
})
