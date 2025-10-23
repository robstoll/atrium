package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
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
        toHaveElementsAndNone.forExpectationCreatorTest("$toBeGreaterThanDescr: 10.1") { toBeGreaterThan(10.1) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        toHaveElementsAndNoneNullable.forExpectationCreatorTest("$toBeGreaterThanDescr: 10.1") { toBeGreaterThan(10.1) }
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
                    expect(fluentEmpty()).toHaveElementsAndNoneFun { toBeLessThan(1.1) }
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex(
                            "$hasANextElement$separator" +
                                "$indentRootBulletPoint\\Q$explanatoryBulletPoint\\E$containsNotDescr: $separator" +
                                "$indentRootBulletPoint$indentListBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                "$indentListBulletPoint$afterExplanatory$toBeLessThanDescr: 1.1.*"
                        )
                    }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("happy case") {
                listOf(1.2, 2.2, 3.3).forEach {
                    it("$toEqualDescr($it) does not throw") {
                        expect(oneToSeven()).toHaveElementsAndNoneFun { toEqual(it) }
                    }
                }
            }

            context("failing cases; search string at different positions") {
                it("$toEqualDescr(4.1) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toHaveElementsAndNoneFun { toEqual(4.1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toEqualDescr: 4.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(2, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(3, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(8, "4.1")}.*"
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
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toEqualDescr: null$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(1, "null")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(5, "null")}.*"
                            )
                        }
                    }
                }

                it("1.1 throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).toHaveElementsAndNoneFun { toEqual(1.1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toEqualDescr: 1.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.1")}.*"
                            )
                        }
                    }
                }
            }
        }
    }
})
