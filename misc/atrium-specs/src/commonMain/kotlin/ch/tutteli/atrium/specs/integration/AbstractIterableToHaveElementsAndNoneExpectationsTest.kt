package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterExplanatory
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterExplanatoryIndent
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterMismatchedWarning
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.anElementWhichNeedsDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.hasANextElement
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.mismatchedIndex
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyIterable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.mismatches
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSevenNullable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

@Suppress("FunctionName")
abstract class AbstractIterableToHaveElementsAndNoneExpectationsTest(
    private val toHaveElementsAndNoneSpec: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    private val toHaveElementsAndNoneNullableSpec: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData<Iterable<Double>>(
            toHaveElementsAndNoneSpec.first to expectLambda {
                toHaveElementsAndNoneSpec.second(this) {
                    toEqual(2.3)
                }
            }
        ),
        SubjectLessTestData<Iterable<Double?>>(
            "${toHaveElementsAndNoneNullableSpec.first} for nullable" to expectLambda {
                toHaveElementsAndNoneNullableSpec.second(this) {
                    toEqual(2.3)
                }
            }
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            toHaveElementsAndNoneSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 10.1") { toBeGreaterThan(10.1) }
        ),
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            toHaveElementsAndNoneNullableSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 10.1") {
                toBeGreaterThan(10.1)
            }
        )
    )

    val containsNotDescr = DescriptionIterableLikeExpectation.NOT_TO_CONTAIN.getDefault()

    @TestFactory
    fun empty_collection__throws() = testFactoryNonNullable(
        toHaveElementsAndNoneSpec, toHaveElementsAndNoneNullableSpec
    ) { toHaveElementsAndNoneFun ->
        it("throws AssertionError as there needs to be at least one element") {
            expect {
                expect(emptyIterable()).toHaveElementsAndNoneFun { toBeLessThan(1.1) }
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

    @TestFactory
    fun one_to_seven() = testFactoryNonNullable(
        toHaveElementsAndNoneSpec, toHaveElementsAndNoneNullableSpec
    ) { toHaveElementsAndNoneFun ->
        listOf(1.2, 2.2, 3.3).forEach {
            it("$toEqualDescr($it) does not throw") {
                expect(oneToSeven()).toHaveElementsAndNoneFun { toEqual(it) }
            }
        }

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

    @TestFactory
    fun nullable_cases() = testFactory(toHaveElementsAndNoneNullableSpec) { toHaveElementsAndNoneFun ->
        describeIterable(::oneToSeven) {
            it("null does not throw") {
                expect(oneToSeven() as Iterable<Double?>).toHaveElementsAndNoneFun(null)
            }
        }
        describeIterable(::oneToSevenNullable) {
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
