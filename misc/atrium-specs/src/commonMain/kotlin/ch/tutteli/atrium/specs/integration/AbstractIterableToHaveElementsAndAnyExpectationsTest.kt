package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.anElementWhichNeedsDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.noSuchElementDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toEqualFun
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyIterable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSevenNullable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainInAnyOrder
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractIterableToHaveElementsAndAnyExpectationsTest(
    private val toHaveElementsAndAnySpec: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    private val toHaveElementsAndAnyNullableSpec: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData<Iterable<Double>>(
            toHaveElementsAndAnySpec.first to expectLambda {
                toHaveElementsAndAnySpec.second(this) {
                    toEqual(2.5)
                }
            }
        ),
        SubjectLessTestData<Iterable<Double?>>(
            "${toHaveElementsAndAnyNullableSpec.first} for nullable" to expectLambda {
                toHaveElementsAndAnyNullableSpec.second(this, null)
            }
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            toHaveElementsAndAnySpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 1.1") { toBeGreaterThan(1.1) }
        ),
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            toHaveElementsAndAnyNullableSpec.forExpectationCreatorTest("$toBeGreaterThanDescr: 1.1") {
                toBeGreaterThan(1.1)
            }
        )
    )

    @TestFactory
    fun empty_collection__throws() = testFactoryNonNullable(
        toHaveElementsAndAnySpec, toHaveElementsAndAnyNullableSpec
    ) { toHaveElementsAndAnyFun ->
        it("throws AssertionError as there needs to be at least one element") {
            expect {
                expect(emptyIterable()).toHaveElementsAndAnyFun { toBeLessThan(1.1) }
            }.toThrow<AssertionError> {
                messageToContain(
                    "$rootBulletPoint$toContainInAnyOrder: $separator",
                    "$anElementWhichNeedsDescr: $separator",
                    "$toBeLessThanDescr: 1.1",
                    noSuchElementDescr
                )
            }
        }
    }

    @TestFactory
    fun one_to_seven() = testFactoryNonNullable(
        toHaveElementsAndAnySpec, toHaveElementsAndAnyNullableSpec
    ) { toHaveElementsAndAnyFun ->
        it("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.1) - throws") {
            expect {
                expect(oneToSeven()).toHaveElementsAndAnyFun { toBeGreaterThan(1.1); toBeLessThan(2.1) }
            }.toThrow<AssertionError> {
                messageToContain(
                    "$rootBulletPoint$toContainInAnyOrder: $separator",
                    "$anElementWhichNeedsDescr: $separator",
                    "$toBeGreaterThanDescr: 1.1",
                    "$toBeLessThanDescr: 2.1",
                    noSuchElementDescr
                )
            }
        }

        it("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.2) - does not throw") {
            expect(oneToSeven()).toHaveElementsAndAnyFun { toBeGreaterThan(1.1); toBeLessThan(2.2) }
        }
    }

    @TestFactory
    fun nullable_cases() = testFactory(toHaveElementsAndAnyNullableSpec) { toHaveElementsAndAnyFun ->
        describeIterable(::oneToSevenNullable) {
            describe("happy cases (do not throw)") {
                it("$toEqualFun(1.1)") {
                    expect(oneToSevenNullable()).toHaveElementsAndAnyFun { toEqual(1.1) }
                }
                it("null") {
                    expect(oneToSevenNullable()).toHaveElementsAndAnyFun(null)
                }
            }

            describe("failing cases") {
                it("$toEqualFun(2.1)") {
                    expect {
                        expect(oneToSevenNullable()).toHaveElementsAndAnyFun { toEqual(2.1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichNeedsDescr: $separator",
                                "$toEqualDescr: 2.1",
                                noSuchElementDescr
                            )
                        }
                    }
                }
            }
        }

        describeIterable(::oneToSeven) {
            it("null, throws an AssertionError") {
                expect {
                    expect(oneToSeven() as Iterable<Double?>).toHaveElementsAndAnyFun(null)
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrder: $separator",
                            "$anElementWhichNeedsDescr: $separator",
                            "$toEqualDescr: null",
                            noSuchElementDescr
                        )
                    }
                }
            }
        }
    }
}
