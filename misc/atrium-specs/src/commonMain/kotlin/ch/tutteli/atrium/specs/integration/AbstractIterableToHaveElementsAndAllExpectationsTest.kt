package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.hasANextElement
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
abstract class AbstractIterableToHaveElementsAndAllExpectationsTest(
    private val toHaveElementsAndAllSpec: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    private val toHaveElementsAndAllNullableSpec: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData<Iterable<Double>>(
            toHaveElementsAndAllSpec.first to expectLambda {
                toHaveElementsAndAllSpec.second(this) {
                    toEqual(2.5)
                }
            }
        ),
        SubjectLessTestData<Iterable<Double?>>(
            "${toHaveElementsAndAllNullableSpec.first} for nullable" to expectLambda {
                toHaveElementsAndAllNullableSpec.second(this, null)
            }
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            toHaveElementsAndAllSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 0.0") { toBeGreaterThan(0.0) }
        ),
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            toHaveElementsAndAllNullableSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 0.0") {
                toBeGreaterThan(0.0)
            }
        )
    )

    val allElementsDescr = DescriptionIterableLikeExpectation.ALL_ELEMENTS.getDefault()
    val explanatoryPointWithIndent = "$indentRootBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    fun index(index: Int) = listBulletPoint + DescriptionIterableLikeExpectation.INDEX.getDefault().format(index)

    @TestFactory
    fun empty_collection__throws() = testFactoryNonNullable(
        toHaveElementsAndAllSpec, toHaveElementsAndAllNullableSpec
    ) { toHaveElementsAndAllFun ->
        it("throws AssertionError as there needs to be at least one element") {
            expect {
                expect(emptyIterable()).toHaveElementsAndAllFun { toBeLessThan(1.1) }
            }.toThrow<AssertionError> {
                message.toContainRegex(
                    hasANextElement,
                    "$explanatoryBulletPoint$allElementsDescr: ",
                    "$explanatoryPointWithIndent$toBeLessThanDescr: 1.1"
                )
            }
        }
    }

    @TestFactory
    fun one_to_seven() = testFactoryNonNullable(
        toHaveElementsAndAllSpec, toHaveElementsAndAllNullableSpec
    ) { toHaveElementsAndAllFun ->
        it("all are $toBeGreaterThanFun(2.5) and $toBeLessThanFun(7.1) - throws") {
            expect {
                expect(oneToSeven()).toHaveElementsAndAllFun { toBeGreaterThan(2.5); toBeLessThan(7.1) }
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
            expect(oneToSeven()).toHaveElementsAndAllFun { toBeGreaterThan(0.5); toBeLessThan(7.5) }
        }
    }

    private val iterableOfNulls = { sequenceOf<Double?>(null, null).constrainOnce().asIterable() }

    @TestFactory
    fun nullable_cases() = testFactory(toHaveElementsAndAllNullableSpec) { toHaveElementsAndAllNullableFun ->
        describeIterable(::iterableOfNulls) {
            it("all are `null` does not throw") {
                expect(iterableOfNulls()).toHaveElementsAndAllNullableFun(null)
            }
        }
        describeIterable(::oneToSevenNullable) {
            it("$toBeGreaterThanDescr(0.5) throws because two are `null`") {
                expect {
                    expect(oneToSevenNullable()).toHaveElementsAndAllNullableFun { toBeGreaterThan(0.5) }
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
