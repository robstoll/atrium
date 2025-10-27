package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanFun
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
    fun empty_collection__does_not_throw() = testFactoryNonNullable(
        notToHaveElementsOrAllSpec, notToHaveElementsOrAllNullableSpec
    ) { noToHaveElementsOrAllFun ->
        it("does not throw") {
            expect(emptyIterable()).noToHaveElementsOrAllFun { toBeLessThan(1.1) }
        }
    }

    @TestFactory
    fun one_to_seven() = testFactoryNonNullable(
        notToHaveElementsOrAllSpec, notToHaveElementsOrAllNullableSpec
    ) { notToHaveElementsOrAllFun ->
        it("all need $toBeGreaterThanFun(2.5) and $toBeLessThanFun(7.0) - throws") {
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

        it("all are $toBeGreaterThanFun(0.5) and $toBeLessThanFun(7.5) - does not throw") {
            expect(oneToSeven()).notToHaveElementsOrAllFun { toBeGreaterThan(0.5); toBeLessThan(7.5) }
        }
    }

    private val iterableOfNulls = { sequenceOf<Double?>(null, null).constrainOnce().asIterable() }

    @TestFactory
    fun nullable_cases() = testFactory(notToHaveElementsOrAllNullableSpec) { notToHaveElementsOrAllNullableFun ->
        describeIterable(::iterableOfNulls) {
            it("all are `null` does not throw") {
                expect(iterableOfNulls()).notToHaveElementsOrAllNullableFun(null)
            }
        }
        describeIterable(::oneToSevenNullable) {
            it("$toBeGreaterThanDescr(0.5) throws because two are `null`") {
                expect {
                    expect(oneToSevenNullable()).notToHaveElementsOrAllNullableFun { toBeGreaterThan(0.5) }
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
