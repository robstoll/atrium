package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

abstract class IterableToHaveNextAndNoneExpectationsSpec(
    notToBeEmptyAndNone: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    notToBeEmptyAndNoneNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        notToBeEmptyAndNone.forSubjectLess { toEqual(2.3) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        notToBeEmptyAndNoneNullable.forSubjectLess { toEqual(2.3) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        notToBeEmptyAndNone.forAssertionCreatorSpec("$toBeGreaterThanDescr: 10.0") { toBeGreaterThan(10.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        notToBeEmptyAndNoneNullable.forAssertionCreatorSpec("$toBeGreaterThanDescr: 10.0") { toBeGreaterThan(10.0) }
    ) {})

    val containsNotDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()
    val hasElement = DescriptionIterableAssertion.HAS_ELEMENT.getDefault()

    nonNullableCases(
        describePrefix,
        notToBeEmptyAndNone,
        notToBeEmptyAndNoneNullable
    ) { notToBeEmptyAndNoneFun ->

        context("empty collection") {
            it("throws AssertionError as there needs to be at least one element") {
                expect {
                    expect(fluentEmpty()).notToBeEmptyAndNoneFun { toBeLessThan(1.0) }
                }.toThrow<AssertionError> {
                    messageContains("$featureArrow$hasElement: false")
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("happy case") {
                listOf(1.1, 2.2, 3.3).forEach {
                    it("$toBeDescr($it) does not throw") {
                        expect(oneToSeven()).notToBeEmptyAndNoneFun { toEqual(1.1) }
                    }
                }
            }

            context("failing cases; search string at different positions") {
                it("$toBeDescr(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToBeEmptyAndNoneFun { toEqual(4.0) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$toBeDescr: 4.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 3$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }
            }
        }
    }
    nullableCases(describePrefix) {
        describeFun(notToBeEmptyAndNoneNullable) {
            val notToBeEmptyAndNoneFun = notToBeEmptyAndNoneNullable.lambda

            context("iterable ${oneToSeven().toList()}") {
                it("null does not throw") {
                    expect(oneToSeven() as Iterable<Double?>).notToBeEmptyAndNoneFun(null)
                }
            }
            context("iterable ${oneToSevenNullable().toList()}") {
                it("null throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).notToBeEmptyAndNoneFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$isDescr: null$separator" +
                                    "$featureFailing$numberOfOccurrences: 2$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }

                it("1.0 throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).notToBeEmptyAndNoneFun { toEqual(1.0) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$toBeDescr: 1.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 1$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }
            }
        }
    }
})
