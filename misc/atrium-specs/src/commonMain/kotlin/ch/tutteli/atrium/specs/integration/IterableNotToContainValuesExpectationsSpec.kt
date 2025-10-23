package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

abstract class IterableNotToContainValuesExpectationsSpec(
    notToContainValues: Fun2<Iterable<Double>, Double, Array<out Double>>,
    notToContainNullableValues: Fun2<Iterable<Double?>, Double?, Array<out Double?>>,
    notToHaveElementsOrNoneFunName: String,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        notToContainValues.forSubjectLessTest(2.3, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        notToContainNullableValues.forSubjectLessTest(2.3, arrayOf())
    ) {})

    fun Expect<Iterable<Double?>>.notToContainNullableFun(a: Double?, vararg aX: Double?) =
        notToContainNullableValues(this, a, aX)

    val anElementWhichIsWithIndent = "$indentRootBulletPoint$listBulletPoint$anElementWhichEquals"

    nonNullableCases(
        describePrefix,
        notToContainValues,
        notToContainNullableValues
    ) { notToContainFunArr ->

        fun Expect<Iterable<Double>>.notToContainFun(a: Double, vararg aX: Double) =
            notToContainFunArr(a, aX.toTypedArray())

        context("empty collection") {

            it("4.1 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).notToContainFun(4.1)
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex(
                            "$hasANextElement$separator" +
                                "$indentRootBulletPoint\\Q$explanatoryBulletPoint\\E$notToContainDescr: $separator" +
                                "$indentListBulletPoint$anElementWhichIsWithIndent: 4.1.*",
                            "$hintBulletPoint${
                                DescriptionIterableLikeExpectation.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE.getDefault()
                                    .format(notToHaveElementsOrNoneFunName)
                            }"
                        )
                    }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {

            context("happy case") {
                it("1.2 does not throw") {
                    expect(oneToSeven()).notToContainFun(1.2)
                }
                it("1.2, 2.2, 3.3 does not throw") {
                    expect(oneToSeven()).notToContainFun(1.2, 2.2, 3.3)
                }
                it("3.3, 1.2, 2.2 does not throw") {
                    expect(oneToSeven()).notToContainFun(3.3, 1.2, 2.2)
                }
            }

            context("failing cases; search string at different positions") {
                it("4.1 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun(4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$anElementWhichIsWithIndent: 4.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(2, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(3, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(8, "4.1")}.*"
                            )
                        }
                    }
                }
                it("1.1, 4.1 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun(1.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$anElementWhichIsWithIndent: 1.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.1")}.*$separator" +
                                    "$anElementWhichIsWithIndent: 4.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(2, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(3, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(8, "4.1")}.*"
                            )
                        }
                    }
                }
                it("4.1, 1.1 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun(4.1, 1.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$anElementWhichIsWithIndent: 4.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(2, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(3, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(8, "4.1")}.*$separator" +
                                    "$anElementWhichIsWithIndent: 1.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.1")}.*"
                            )
                        }
                    }
                }
            }

        }
    }

    nullableCases(describePrefix) {
        describeFun(notToContainNullableValues) {
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
                                    "$anElementWhichIsWithIndent: null$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(1, "null")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(5, "null")}.*"
                            )
                        }
                    }
                }

                it("1.2, null throws AssertionError mentioning only null") {
                    expect {
                        expect(oneToSevenNullable()).notToContainNullableFun(1.2, null)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$anElementWhichIsWithIndent: null$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(1, "null")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(5, "null")}.*"
                            )
                            notToContain("$notToContainDescr: 1.2")
                        }
                    }
                }
            }
        }
    }
})
