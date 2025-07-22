package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.notToContain
import ch.tutteli.atrium.api.fluent.en_GB.regex
import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toContainRegex
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.Fun2
import ch.tutteli.atrium.specs.explanatoryBulletPoint
import ch.tutteli.atrium.specs.forSubjectLessTest
import ch.tutteli.atrium.specs.hintBulletPoint
import ch.tutteli.atrium.specs.indentListBulletPoint
import ch.tutteli.atrium.specs.indentRootBulletPoint
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterExplanatory
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterExplanatoryIndent
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterMismatchedWarning
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.anElementWhichNeedsDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.hasANextElement
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.index
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.mismatchedIndex
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toEqualFun
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.mismatches
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSevenNullable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.invoke
import ch.tutteli.atrium.specs.listBulletPoint
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.rootBulletPoint
import ch.tutteli.atrium.specs.toEqualDescr
import ch.tutteli.atrium.specs.warningBulletPoint
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation


@Suppress("FunctionName")
abstract class AbstractIterableNotToContainEntriesExpectationsTest(
    private val notToContainEntriesSpec: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    private val notToContainNullableEntriesSpec: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    private val notToHaveElementsOrNoneFunNameSpec: String,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            notToContainEntriesSpec.forSubjectLessTest({ toEqual(2.3) }, arrayOf())
        ),
        SubjectLessTestData(
            notToContainNullableEntriesSpec.forSubjectLessTest({ toEqual(2.3) }, arrayOf())
        )
    )

    private val notToContainDescr = DescriptionIterableLikeExpectation.NOT_TO_CONTAIN.getDefault()
    private val toBeLessThanDescr = DescriptionComparableExpectation.TO_BE_LESS_THAN.getDefault()
    private val toBeGreaterThanDescr = DescriptionComparableExpectation.TO_BE_GREATER_THAN.getDefault()

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            oneToSeven().toList().asIterable(),
            expectationCreator = ExpectationCreatorTriple(
                "${notToContainEntriesSpec.name} - first empty",
                "$toBeGreaterThanDescr: 8.0",
                {
                    notToContainEntriesSpec.invoke(
                        this,
                        { toBeGreaterThan(8.0) },
                        arrayOf(expectLambda { toBeGreaterThan(10.0) })
                    )
                },
                { notToContainEntriesSpec.invoke(this, {}, arrayOf(expectLambda { toBeGreaterThan(10.0) })) }
            ),
            ExpectationCreatorTriple(
                "${notToContainEntriesSpec.name} - second empty",
                "$toBeGreaterThanDescr: 10.0",
                {
                    notToContainEntriesSpec.invoke(
                        this,
                        { toBeGreaterThan(8.0) },
                        arrayOf(expectLambda { toBeGreaterThan(10.0) })
                    )
                },
                { notToContainEntriesSpec.invoke(this, { toBeGreaterThan(8.0) }, arrayOf(expectLambda<Double> {})) }
            )
        ),

        ExpectationCreatorTestData(
            subject = oneToSeven().toList().asIterable(),
            expectationCreator = ExpectationCreatorTriple<Iterable<Double?>>(
                "${notToContainNullableEntriesSpec.name} - first empty",
                "$toBeGreaterThanDescr: 8.0",
                {
                    notToContainNullableEntriesSpec.invoke(
                        this,
                        { toBeGreaterThan(8.0) },
                        arrayOf(expectLambda { toBeGreaterThan(10.0) })
                    )
                },
                { notToContainNullableEntriesSpec(this, {}, arrayOf(expectLambda { toBeGreaterThan(10.0) })) }
            ),
            ExpectationCreatorTriple(
                "${notToContainNullableEntriesSpec.name} - second empty",
                "$toBeGreaterThanDescr: 10.0",
                {
                    notToContainNullableEntriesSpec.invoke(
                        this,
                        { toBeGreaterThan(8.0) },
                        arrayOf(expectLambda { toBeGreaterThan(10.0) })
                    )
                },
                {
                    notToContainNullableEntriesSpec.invoke(
                        this,
                        { toBeGreaterThan(8.0) },
                        arrayOf(expectLambda<Double> {})
                    )
                }
            ),
            groupPrefix = "[nullable Element] "
        )
    )

    private fun Expect<Iterable<Double?>>.notToContainNullableFun(
        a: (Expect<Double>.() -> Unit)?,
        vararg aX: (Expect<Double>.() -> Unit)?,
    ) = notToContainNullableEntriesSpec(this, a, aX)

    @TestFactory
    fun empty_iterable__throws_AssertionError() =
        nonNullableCases(notToContainEntriesSpec, notToContainNullableEntriesSpec) { notToContainFunArr ->
            fun Expect<Iterable<Double>>.notToContainFun(
                a: Expect<Double>.() -> Unit,
                vararg aX: Expect<Double>.() -> Unit,
            ) = notToContainFunArr(a, aX)

            expect {
                expect(setOf<Double>().asIterable()).notToContainFun({ toEqual(4.0) })
            }.toThrow<AssertionError> {
                message {
                    toContainRegex(
                        "$hasANextElement$separator" +
                                "$indentRootBulletPoint\\Q$explanatoryBulletPoint\\E$notToContainDescr: $separator" +
                                "$indentRootBulletPoint$indentListBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                "$indentListBulletPoint$afterExplanatory$toEqualDescr: 4.0.*",
                        "$hintBulletPoint${
                            DescriptionIterableLikeExpectation.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE.getDefault()
                                .format(notToHaveElementsOrNoneFunNameSpec)
                        }"
                    )
                }
            }
        }

    @TestFactory
    fun iterable__happy_case() =
        nonNullableCases(notToContainEntriesSpec, notToContainNullableEntriesSpec) { notToContainFunArr ->
            fun Expect<Iterable<Double>>.notToContainFun(
                a: Expect<Double>.() -> Unit,
                vararg aX: Expect<Double>.() -> Unit,
            ) = notToContainFunArr(a, aX)
            testFactory(notToContainEntriesSpec) {
                it("$toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.0) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toBeGreaterThan(1.0); toBeLessThan(2.0) })
                }
                it("$toEqualFun(1.1), $toEqualFun(2.2), $toEqualFun(3.3) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toEqual(1.1) }, { toEqual(2.2) }, { toEqual(3.3) })
                }
                it("$toEqualFun(3.3), $toEqualFun(1.1), $toEqualFun(2.2) does not throw") {
                    ch.tutteli.atrium.api.verbs.internal.expect(oneToSeven())
                        .notToContainFun({ toEqual(3.3) }, { toEqual(1.1) }, { toEqual(2.2) })
                }
            }
            expect(oneToSeven()).notToContainFun({ toBeGreaterThan(1.0); toBeLessThan(2.0) })
        }

    @TestFactory
    fun iterable__failing_cases_search_string_at_different_positions() {
        nonNullableCases(notToContainEntriesSpec, notToContainNullableEntriesSpec) { notToContainFunArr ->
            fun Expect<Iterable<Double>>.notToContainFun(
                a: Expect<Double>.() -> Unit,
                vararg aX: Expect<Double>.() -> Unit,
            ) = notToContainFunArr(a, aX)

            testFactory(notToContainEntriesSpec) {
                it("$toBeLessThanFun(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toBeLessThan(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                        "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                        "$afterExplanatory$toBeLessThanDescr: 4.0.*$separator" +
                                        "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
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
                                        "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                        "$afterExplanatory$toEqualDescr: 1.0.*$separator" +
                                        "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                        "$afterMismatchedWarning${mismatchedIndex(0, "1.0")}.*$separator" +
                                        "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                        "$afterExplanatory$toEqualDescr: 4.0.*$separator" +
                                        "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
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
                            toContainRegex("$anElementWhichNeedsDescr: $separator.*$toEqualDescr: 4.0")
                            notToContain.regex("$anElementWhichNeedsDescr: $separator.*$toEqualDescr: 1.1")
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun iterable__null_does_not_throw() {
        nonNullableCases(notToContainEntriesSpec, notToContainNullableEntriesSpec) {
            expect(oneToSeven() as Iterable<Double?>).notToContainNullableFun(null)
        }
    }

    @TestFactory
    fun iterable__null() {
        testFactory(notToContainNullableEntriesSpec) {
            it("null throws AssertionError") {
                expect {
                    expect(oneToSevenNullable()).notToContainNullableFun(null)
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex(
                            "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toEqualDescr: null$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
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
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toEqualDescr: null$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
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
