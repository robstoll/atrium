package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toEqualFun
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.fluentEmpty
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSevenNullable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

@Suppress("FunctionName")
abstract class AbstractIterableNotToHaveElementsOrAnyExpectationsTest(
    private val notToHaveElementsOrAnySpec: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    private val notToHaveElementsOrAnyNullableSpec: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            notToHaveElementsOrAnySpec.forSubjectLessTest { toEqual(2.5) }
        ),
        SubjectLessTestData(
            notToHaveElementsOrAnyNullableSpec.forSubjectLessTest(null)
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            notToHaveElementsOrAnySpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 1.1") { toBeGreaterThan(1.1) }
        ),
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            notToHaveElementsOrAnyNullableSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 1.1") {
                toBeGreaterThan(
                    1.1
                )
            }
        )
    )

    val notToHaveElementsOrAnyDescr = DescriptionIterableLikeExpectation.NOT_TO_HAVE_ELEMENTS_OR_ANY.getDefault()
    val noSuchElementDescr = DescriptionIterableLikeExpectation.NEITHER_EMPTY_NOR_ELEMENT_FOUND.getDefault()


    @TestFactory
    fun empty_collection__does_not_throw() = nonNullableCases(
        notToHaveElementsOrAnySpec,
        notToHaveElementsOrAnyNullableSpec
    ) { notToHaveElementsOrAnyFun ->
        expect(fluentEmpty()).notToHaveElementsOrAnyFun { toBeLessThan(1.1) }
    }

    @TestFactory
    fun one_to_7__throws() = nonNullableCases(
        notToHaveElementsOrAnySpec,
        notToHaveElementsOrAnyNullableSpec
    ) { notToHaveElementsOrAnyFun ->
        expect {
            expect(oneToSeven()).notToHaveElementsOrAnyFun { toBeGreaterThan(1.1); toBeLessThan(2.1) }
        }.toThrow<AssertionError> {
            messageToContain(
                "$rootBulletPoint$notToHaveElementsOrAnyDescr: $separator",
                "$toBeGreaterThanDescr: 1.1",
                "$toBeLessThanDescr: 2.1",
                noSuchElementDescr
            )
        }
    }

    @TestFactory
    fun one_to_7__does_not_throw() = nonNullableCases(
        notToHaveElementsOrAnySpec,
        notToHaveElementsOrAnyNullableSpec
    ) { notToHaveElementsOrAnyFun ->
        expect(oneToSeven()).notToHaveElementsOrAnyFun { toBeGreaterThan(1.1); toBeLessThan(2.2) }
    }


    @TestFactory
    fun nullable_cases() = testFactory(notToHaveElementsOrAnyNullableSpec) { notToHaveElementsOrAnyFun ->
        describe("iterable ${oneToSevenNullable().toList().joinToString(",")}") {
            describe("happy cases (do not throw)") {
                it("$toEqualFun(1.1)") {
                    expect(oneToSevenNullable()).notToHaveElementsOrAnyFun { toEqual(1.1) }
                }
                it("null") {
                    expect(oneToSevenNullable()).notToHaveElementsOrAnyFun(null)
                }
            }
        }
        describe("failing cases") {
            it("$toEqualFun(2.1)") {
                expect {
                    expect(oneToSevenNullable()).notToHaveElementsOrAnyFun { toEqual(2.1) }
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).values(
                            "$rootBulletPoint$notToHaveElementsOrAnyDescr: $separator",
                            "$toEqualDescr: 2.1",
                            noSuchElementDescr
                        )
                    }
                }
            }
        }
        describe("iterable ${oneToSeven().toList().joinToString(",")}") {
            it("null, throws an AssertionError") {
                expect {
                    expect(oneToSeven() as Iterable<Double?>).notToHaveElementsOrAnyFun(null)
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).values(
                            "$rootBulletPoint$notToHaveElementsOrAnyDescr: $separator",
                            "$toEqualDescr: null",
                            noSuchElementDescr
                        )
                    }
                }
            }
        }
    }
}
