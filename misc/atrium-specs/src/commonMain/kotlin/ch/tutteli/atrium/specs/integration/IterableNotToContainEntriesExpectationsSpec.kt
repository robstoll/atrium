package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

abstract class IterableNotToContainEntriesExpectationsSpec(
    notToContainEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    notToContainNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    notToHaveElementsOrNoneFunName: String,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        notToContainEntries.forSubjectLessTest({ toEqual(2.3) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        notToContainNullableEntries.forSubjectLessTest({ toEqual(2.3) }, arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        notToContainEntries.forExpectationCreatorTest(
            "$toBeGreaterThanDescr: 8.1",
            "$toBeGreaterThanDescr: 10.1",
            { toBeGreaterThan(8.1) }, arrayOf(expectLambda { toBeGreaterThan(10.1) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        notToContainNullableEntries.forExpectationCreatorTest(
            "$toBeGreaterThanDescr: 8.1",
            "$toBeGreaterThanDescr: 10.1",
            { toBeGreaterThan(8.1) }, arrayOf(expectLambda { toBeGreaterThan(10.1) })
        )
    ) {})

    fun Expect<Iterable<Double?>>.notToContainNullableFun(
        a: (Expect<Double>.() -> Unit)?,
        vararg aX: (Expect<Double>.() -> Unit)?
    ) = notToContainNullableEntries(this, a, aX)

    val notToContainDescr = DescriptionIterableLikeExpectation.NOT_TO_CONTAIN.getDefault()

    nonNullableCases(
        describePrefix,
        notToContainEntries,
        notToContainNullableEntries
    ) { notToContainFunArr ->

        fun Expect<Iterable<Double>>.notToContainFun(
            a: Expect<Double>.() -> Unit,
            vararg aX: Expect<Double>.() -> Unit
        ) = notToContainFunArr(a, aX)

        context("empty iterable") {

            it("$toEqualFun(4.1) throws AssertionError") {
                expect {
                    expect(setOf<Double>().asIterable()).notToContainFun({ toEqual(4.1) })
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex(
                            "$hasANextElement$separator" +
                                "$indentRootBulletPoint\\Q$explanatoryBulletPoint\\E$notToContainDescr: $separator" +
                                "$indentRootBulletPoint$indentListBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                "$indentListBulletPoint$afterExplanatory$toEqualDescr: 4.1.*",
                                "$hintBulletPoint${DescriptionIterableLikeExpectation.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE.getDefault().format(notToHaveElementsOrNoneFunName)}"
                        )
                    }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {

            context("happy case") {
                it("$toBeGreaterThanFun(1.2) and $toBeLessThanFun(2.1) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toBeGreaterThan(1.2); toBeLessThan(2.1) })
                }
                it("$toEqualFun(1.2), $toEqualFun(2.2), $toEqualFun(3.2) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toEqual(1.2) }, { toEqual(2.2) }, { toEqual(3.2) })
                }
                it("$toEqualFun(3.2), $toEqualFun(1.2), $toEqualFun(2.2) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toEqual(3.2) }, { toEqual(1.2) }, { toEqual(2.2) })
                }
            }

            context("failing cases; search string at different positions") {
                it("$toBeLessThanFun(4.1) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toBeLessThan(4.1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toBeLessThanDescr: 4.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(1, "2.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(5, "3.1")}.*"
                            )
                        }
                    }
                }
                it("$toEqualFun(1.1), $toEqualFun(4.1) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toEqual(1.1) }, { toEqual(4.1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toEqualDescr: 1.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.1")}.*$separator" +
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
                it("$toEqualFun(4.1), $toEqualFun(1.2) throws AssertionError mentioning only 4.1") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toEqual(4.1) }, { toEqual(1.2) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex("$anElementWhichNeedsDescr: $separator.*$toEqualDescr: 4.1")
                            notToContain.regex("$anElementWhichNeedsDescr: $separator.*$toEqualDescr: 1.2")
                        }
                    }
                }
            }

        }
    }

    nullableCases(describePrefix) {
        describeFun(notToContainNullableEntries) {
            context("iterable ${oneToSeven().toList()}") {
                it("null does not throw") {
                    expect(oneToSeven() as Iterable<Double?>).notToContainNullableFun(null)
                }
            }
            context("iterable ${oneToSevenNullable().toList()}") {
                it("null throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).notToContainNullableFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toEqualDescr: null$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(1, "null")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(5, "null")}.*"
                            )
                        }
                    }
                }

                it("1.2, null throws AssertionError mentioning only null") {
                    expect {
                        expect(oneToSevenNullable()).notToContainNullableFun({ toEqual(1.2) }, null)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toEqualDescr: null$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${index(1)}: null.*$separator" +
                                    "$afterMismatchedWarning${index(5)}: null.*"
                            )
                            this.notToContain("$notToContainDescr: 1.2")
                        }
                    }
                }
            }
        }
    }
})
