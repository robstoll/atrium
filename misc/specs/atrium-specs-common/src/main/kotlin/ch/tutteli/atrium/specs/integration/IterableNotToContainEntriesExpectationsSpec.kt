package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

abstract class IterableNotToContainEntriesExpectationsSpec(
    notToContainEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    notToContainNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        notToContainEntries.forSubjectLess({ toEqual(2.3) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        notToContainNullableEntries.forSubjectLess({ toEqual(2.3) }, arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        *notToContainEntries.forAssertionCreatorSpec(
            "$toBeGreaterThanDescr: 8.0",
            "$toBeGreaterThanDescr: 10.0",
            { toBeGreaterThan(8.0) }, arrayOf(expectLambda { toBeGreaterThan(10.0) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        *notToContainNullableEntries.forAssertionCreatorSpec(
            "$toBeGreaterThanDescr: 8.0",
            "$toBeGreaterThanDescr: 10.0",
            { toBeGreaterThan(8.0) }, arrayOf(expectLambda { toBeGreaterThan(10.0) })
        )
    ) {})

    fun Expect<Iterable<Double?>>.notToContainNullableFun(
        a: (Expect<Double>.() -> Unit)?,
        vararg aX: (Expect<Double>.() -> Unit)?
    ) = notToContainNullableEntries(this, a, aX)

    val notToContainDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()

    nonNullableCases(
        describePrefix,
        notToContainEntries,
        notToContainNullableEntries
    ) { notToContainFunArr ->

        fun Expect<Iterable<Double>>.notToContainFun(
            a: Expect<Double>.() -> Unit,
            vararg aX: Expect<Double>.() -> Unit
        ) = notToContainFunArr(a, aX)

        context("empty collection") {
            val fluent = expect(setOf<Double>().asIterable())

            it("$toEqualFun(4.0) throws AssertionError") {
                expect {
                    fluent.notToContainFun({ toEqual(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex(
                            "$hasANextElement$separator" +
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                "$afterExplanatory$toBeDescr: 4.0.*"
                        )
                    }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {

            context("happy case") {
                it("$toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.0) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toBeGreaterThan(1.0); toBeLessThan(2.0) })
                }
                it("$toEqualFun(1.1), $toEqualFun(2.2), $toEqualFun(3.3) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toEqual(1.1) }, { toEqual(2.2) }, { toEqual(3.3) })
                }
                it("$toEqualFun(3.3), $toEqualFun(1.1), $toEqualFun(2.2) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toEqual(3.3) }, { toEqual(1.1) }, { toEqual(2.2) })
                }
            }

            context("failing cases; search string at different positions") {
                it("$toBeLessThanFun(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toBeLessThan(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$toBeLessThanDescr: 4.0.*$separator" +
                                    "$afterExplanatoryIndent$warningBulletPoint$mismatches: $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.0")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(1, "2.0")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(5, "3.0")}.*"
                            )
                        }
                    }
                }
                it("$toEqualFun(1.0), $toEqualFun(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toEqual(1.0) }, { toEqual(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$toBeDescr: 1.0.*$separator" +
                                    "$afterExplanatoryIndent$warningBulletPoint$mismatches: $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.0")}.*$separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$toBeDescr: 4.0.*$separator" +
                                    "$afterExplanatoryIndent$warningBulletPoint$mismatches: $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(2, "4.0")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(3, "4.0")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(8, "4.0")}.*"
                            )
                        }
                    }
                }
                it("$toEqualFun(4.0), $toEqualFun(1.1) throws AssertionError mentioning only 4.0") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toEqual(4.0) }, { toEqual(1.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex("$anElementWhich: $separator.*$toBeDescr: 4.0")
                            notToContain.regex("$anElementWhich: $separator.*$toBeDescr: 1.1")
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
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$isDescr: null$separator" +
                                    "$afterExplanatoryIndent$warningBulletPoint$mismatches: $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(1, "null")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(5, "null")}.*"
                            )
                        }
                    }
                }

                it("1.1, null throws AssertionError mentioning only null") {
                    expect {
                        expect(oneToSevenNullable()).notToContainNullableFun({ toEqual(1.1) }, null)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhich: $separator" +
                                    "$afterExplanatory$isDescr: null$separator" +
                                    "$afterExplanatoryIndent$warningBulletPoint$mismatches: $separator" +
                                    "$afterMismatchedWarning${index(1)}: null.*$separator" +
                                    "$afterMismatchedWarning${index(5)}: null.*"
                            )
                            this.notToContain("$notToContainDescr: 1.1")
                        }
                    }
                }
            }
        }
    }
})
