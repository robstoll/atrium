package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

abstract class AbstractIterableNotToHaveElementsOrNoneExpectationsTest(
    notToHaveElementsOrNone: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    notToHaveElementsOrNoneNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        notToHaveElementsOrNone.forSubjectLessTest { toEqual(2.3) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        notToHaveElementsOrNoneNullable.forSubjectLessTest { toEqual(2.3) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        notToHaveElementsOrNone.forExpectationCreatorTest("$toBeGreaterThanDescr: 10.1") { toBeGreaterThan(10.1) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        notToHaveElementsOrNoneNullable.forExpectationCreatorTest("$toBeGreaterThanDescr: 10.1") { toBeGreaterThan(10.1) }
    ) {})

    val notToHaveElementsOrNoneDescr = DescriptionIterableLikeExpectation.NOT_TO_HAVE_ELEMENTS_OR_NONE.getDefault()


    fun mismatch(index: Int, value: String) =
        "$indentRootBulletPoint$indentListBulletPoint$indentWarningBulletPoint\\Q$listBulletPoint\\E${
            mismatchedIndex(index, value)
        }"

    nonNullableCases(
        describePrefix,
        notToHaveElementsOrNone,
        notToHaveElementsOrNoneNullable
    ) { notToHaveElementsOrNoneFun ->

        context("empty collection") {
            it("does not throw") {
                expect(emptyIterable()).notToHaveElementsOrNoneFun { toBeLessThan(1.1) }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("happy case") {
                listOf(1.2, 2.2, 3.3).forEach {
                    it("$toEqualDescr($it) does not throw") {
                        expect(oneToSeven()).notToHaveElementsOrNoneFun { toEqual(it) }
                    }
                }
            }

            context("failing cases; search string at different positions") {
                it("$toEqualDescr(4.1) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToHaveElementsOrNoneFun { toEqual(4.1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToHaveElementsOrNoneDescr: $separator" +
                                    "$indentRootBulletPoint$indentListBulletPoint\\Q$explanatoryBulletPoint\\E$toEqualDescr: 4.1.*$separator" +
                                    "$indentRootBulletPoint$indentListBulletPoint\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "${mismatch(2, "4.1")}.*$separator" +
                                    "${mismatch(3, "4.1")}.*$separator" +
                                    "${mismatch(8, "4.1")}.*"
                            )
                        }
                    }
                }
            }
        }
    }
    nullableCases(describePrefix) {
        describeFun(notToHaveElementsOrNoneNullable) {
            val notToHaveElementsOrNoneFun = notToHaveElementsOrNoneNullable.lambda

            context("iterable ${oneToSeven().toList()}") {
                it("null does not throw") {
                    expect(oneToSeven() as Iterable<Double?>).notToHaveElementsOrNoneFun(null)
                }
            }
            context("iterable ${oneToSevenNullable().toList()}") {
                it("null throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).notToHaveElementsOrNoneFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToHaveElementsOrNoneDescr: $separator" +
                                    "$indentRootBulletPoint$indentListBulletPoint\\Q$explanatoryBulletPoint\\E$toEqualDescr: null$separator" +
                                    "$indentRootBulletPoint$indentListBulletPoint\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "${mismatch(1, "null")}.*$separator" +
                                    "${mismatch(5, "null")}.*"
                            )
                        }
                    }
                }

                it("1.1 throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).notToHaveElementsOrNoneFun { toEqual(1.1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToHaveElementsOrNoneDescr: $separator" +
                                    "$indentRootBulletPoint$indentListBulletPoint\\Q$explanatoryBulletPoint\\E$toEqualDescr: 1.1.*$separator" +
                                    "$indentRootBulletPoint$indentListBulletPoint\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "${mismatch(0, "1.1")}.*"
                            )
                        }
                    }
                }
            }
        }
    }
})
