package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.fluentEmpty
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.mismatches
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSevenNullable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

@Suppress("FunctionName")
abstract class AbstractIterableNotToHaveElementsOrAllExpectationsTest(
    private val notToHaveElementsOrAllSpec: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    private val notToHaveElementsOrAllNullableSpec: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData<Iterable<Double>>(
            notToHaveElementsOrAllSpec.first to expectLambda {
                notToHaveElementsOrAllSpec.second(this) {
                    toEqual(2.5)
                }
            }
        ),
        SubjectLessTestData<Iterable<Double?>>(
            "${notToHaveElementsOrAllNullableSpec.first} for nullable" to expectLambda {
                notToHaveElementsOrAllNullableSpec.second(this, null)
            }
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            notToHaveElementsOrAllSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 0.0") { toBeGreaterThan(0.0) }
        ),
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            notToHaveElementsOrAllNullableSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 0.0") {
                toBeGreaterThan(
                    0.0
                )
            }
        )
    )

    val allElementsDescr = DescriptionIterableLikeExpectation.NOT_TO_HAVE_ELEMENTS_OR_ALL.getDefault()

    val explanatoryPointWithIndent = "$indentRootBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    fun index(index: Int) = listBulletPoint + DescriptionIterableLikeExpectation.INDEX.getDefault().format(index)


    @TestFactory
    fun empty_collection__does_not_throw() = nonNullableCases(
        notToHaveElementsOrAllSpec,
        notToHaveElementsOrAllNullableSpec
    ) { noToHaveElementsOrAllFun ->
        expect(fluentEmpty()).noToHaveElementsOrAllFun { toBeLessThan(1.1) }
    }

    @TestFactory
    fun one_to_7__throws() = nonNullableCases(
        notToHaveElementsOrAllSpec,
        notToHaveElementsOrAllNullableSpec
    ) { notToHaveElementsOrAllFun ->
        expect {
            expect(oneToSeven()).notToHaveElementsOrAllFun { toBeGreaterThan(2.5); toBeLessThan(7.1) }
        }.toThrow<AssertionError> {
            message {
                toContain.exactly(1).values(
                    "$rootBulletPoint$allElementsDescr: $separator",
                    "$explanatoryPointWithIndent$toBeGreaterThanDescr: 2.5",
                    "$explanatoryPointWithIndent$toBeLessThanDescr: 7.1",
                    "$warningBulletPoint$mismatches:",
                    "${index(0)}: 1.1",
                    "${index(1)}: 2.1",
                    "${index(9)}: 7.1"
                )
            }
        }
    }

    @TestFactory
    fun one_to_seven__does_not_throw() = nonNullableCases(
        notToHaveElementsOrAllSpec,
        notToHaveElementsOrAllNullableSpec
    ) { notToHaveElementsOrAllFun ->
        expect(oneToSeven()).notToHaveElementsOrAllFun { toBeGreaterThan(0.5); toBeLessThan(7.5) }
    }

    @TestFactory
    fun nullable_cases() = testFactory(notToHaveElementsOrAllNullableSpec)
    {
        val iterableOfNulls = { sequenceOf<Double?>(null, null).constrainOnce().asIterable() }
        describe("iterable ${iterableOfNulls()}") {
            it("all are `null` does not throw") {
                expect(iterableOfNulls()).notToHaveElementsOrAll(null)
            }
        }
        describe("iterable ${oneToSevenNullable().toList()}") {
            it("$toBeGreaterThanDescr(0.5) throws because two are `null`") {
                expect {
                    expect(oneToSevenNullable()).notToHaveElementsOrAll { toBeGreaterThan(0.5) }
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).values(
                            "$rootBulletPoint$allElementsDescr: $separator",
                            "$explanatoryPointWithIndent$toBeGreaterThanDescr: 0.5",
                            "$warningBulletPoint$mismatches:",
                            "${index(1)}: null",
                            "${index(5)}: null"
                        )
                    }
                }
            }
        }
    }
}
