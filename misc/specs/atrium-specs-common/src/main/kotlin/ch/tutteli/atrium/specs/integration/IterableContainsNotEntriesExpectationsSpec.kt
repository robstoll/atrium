package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

abstract class IterableContainsNotEntriesExpectationsSpec(
    containsNotEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    containsNotNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsNotEntries.forSubjectLess({ toEqual(2.3) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        containsNotNullableEntries.forSubjectLess({ toEqual(2.3) }, arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        *containsNotEntries.forAssertionCreatorSpec(
            "$isGreaterThanDescr: 8.0",
            "$isGreaterThanDescr: 10.0",
            { isGreaterThan(8.0) }, arrayOf(expectLambda { isGreaterThan(10.0) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        *containsNotNullableEntries.forAssertionCreatorSpec(
            "$isGreaterThanDescr: 8.0",
            "$isGreaterThanDescr: 10.0",
            { isGreaterThan(8.0) }, arrayOf(expectLambda { isGreaterThan(10.0) })
        )
    ) {})

    fun Expect<Iterable<Double?>>.containsNotNullableFun(
        a: (Expect<Double>.() -> Unit)?,
        vararg aX: (Expect<Double>.() -> Unit)?
    ) = containsNotNullableEntries(this, a, aX)

    val containsNotDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()
    val hasElement = DescriptionIterableAssertion.HAS_ELEMENT.getDefault()

    nonNullableCases(
        describePrefix,
        containsNotEntries,
        containsNotNullableEntries
    ) { containsNotFunArr ->

        fun Expect<Iterable<Double>>.containsNotFun(
            a: Expect<Double>.() -> Unit,
            vararg aX: Expect<Double>.() -> Unit
        ) = containsNotFunArr(a, aX)

        context("empty collection") {
            val fluent = expect(setOf<Double>().asIterable())

            it("$toBeFun(4.0) throws AssertionError") {
                expect {
                    fluent.containsNotFun({ toEqual(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        containsRegex(
                            "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                "$afterExplanatory$toBeDescr: 4.0.*$separator" +
                                "$featureSuccess$numberOfOccurrences: 0$separator" +
                                "$isAfterSuccess: 0.*$separator" +
                                "$featureFailing$hasElement: false$separator" +
                                "$isAfterFailing: true"
                        )
                    }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {

            context("happy case") {
                it("$isGreaterThanFun(1.0) and $isLessThanFun(2.0) does not throw") {
                    expect(oneToSeven()).containsNotFun({ isGreaterThan(1.0); isLessThan(2.0) })
                }
                it("$toBeFun(1.1), $toBeFun(2.2), $toBeFun(3.3) does not throw") {
                    expect(oneToSeven()).containsNotFun({ toEqual(1.1) }, { toEqual(2.2) }, { toEqual(3.3) })
                }
                it("$toBeFun(3.3), $toBeFun(1.1), $toBeFun(2.2) does not throw") {
                    expect(oneToSeven()).containsNotFun({ toEqual(3.3) }, { toEqual(1.1) }, { toEqual(2.2) })
                }
            }

            context("failing cases; search string at different positions") {
                it("$isLessThanFun(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).containsNotFun({ isLessThan(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$isLessThanDescr: 4.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 3$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }
                it("$toBeFun(1.0), $toBeFun(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).containsNotFun({ toEqual(1.0) }, { toEqual(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$toBeDescr: 1.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 1$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true$separator" +
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
                it("$toBeFun(4.0), $toBeFun(1.1) throws AssertionError mentioning only 4.0") {
                    expect {
                        expect(oneToSeven()).containsNotFun({ toEqual(4.0) }, { toEqual(1.0) })
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex("$anElementWhich: $separator.*$toBeDescr: 4.0")
                            containsNot.regex("$anElementWhich: $separator.*$toBeDescr: 1.1")
                        }
                    }
                }
            }

        }
    }

    nullableCases(describePrefix) {
        describeFun(containsNotNullableEntries) {
            context("iterable ${oneToSeven().toList()}") {
                it("null does not throw") {
                    expect(oneToSeven() as Iterable<Double?>).containsNotNullableFun(null)
                }
            }
            context("iterable ${oneToSevenNullable().toList()}") {
                it("null throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).containsNotNullableFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
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

                it("1.1, null throws AssertionError mentioning only null") {
                    expect {
                        expect(oneToSevenNullable()).containsNotNullableFun({ toEqual(1.1) }, null)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$isDescr: null$separator" +
                                    "$featureFailing$numberOfOccurrences: 2$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                            this.containsNot("$containsNotDescr: 1.1")
                        }
                    }
                }
            }
        }
    }
})
