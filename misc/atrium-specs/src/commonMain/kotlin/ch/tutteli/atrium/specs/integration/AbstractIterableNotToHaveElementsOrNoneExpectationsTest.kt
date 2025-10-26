package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.mismatchedIndex
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
abstract class AbstractIterableNotToHaveElementsOrNoneExpectationsTest(
    private val notToHaveElementsOrNoneSpec: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    private val notToHaveElementsOrNoneNullableSpec: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            notToHaveElementsOrNoneSpec.forSubjectLessTest { toEqual(2.3) }
        ),
        SubjectLessTestData(
            notToHaveElementsOrNoneNullableSpec.forSubjectLessTest { toEqual(2.3) }
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            notToHaveElementsOrNoneSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 10.1") { toBeGreaterThan(10.1) }
        ),
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            notToHaveElementsOrNoneNullableSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 10.1") {
                toBeGreaterThan(
                    10.1
                )
            }
        )
    )

    val notToHaveElementsOrNoneDescr = DescriptionIterableLikeExpectation.NOT_TO_HAVE_ELEMENTS_OR_NONE.getDefault()


    fun mismatch(index: Int, value: String) =
        "$indentRootBulletPoint$indentListBulletPoint$indentWarningBulletPoint\\Q$listBulletPoint\\E${
            mismatchedIndex(index, value)
        }"

    @TestFactory
    fun empty_collection__does_not_throw() = testFactoryNonNullable(
        notToHaveElementsOrNoneSpec,
        notToHaveElementsOrNoneNullableSpec
    ) { notToHaveElementsOrNoneFun ->
        it("does not throw") {
            expect(emptyIterable()).notToHaveElementsOrNoneFun { toBeLessThan(1.1) }
        }
    }

    @TestFactory
    fun one_to_seven() = testFactoryNonNullable(
        notToHaveElementsOrNoneSpec, notToHaveElementsOrNoneNullableSpec
    ) { notToHaveElementsOrNoneFun ->
        listOf(1.2, 2.2, 3.3).forEach {
            it("$toEqualDescr($it) does not throw") {
                expect(oneToSeven()).notToHaveElementsOrNoneFun { toEqual(it) }
            }
        }

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

    @TestFactory
    fun nullable_cases() = testFactory(
        notToHaveElementsOrNoneNullableSpec
    ) { notToHaveElementsOrNoneFun ->
        describeIterable(::oneToSeven) {
            it("null does not throw") {
                expect(oneToSeven() as Iterable<Double?>).notToHaveElementsOrNoneFun(null)
            }
        }
        describeIterable(::oneToSevenNullable) {
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
