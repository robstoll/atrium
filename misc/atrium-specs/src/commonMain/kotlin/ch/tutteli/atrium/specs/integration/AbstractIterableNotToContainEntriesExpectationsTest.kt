package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterExplanatory
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterExplanatoryIndent
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterMismatchedWarning
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.anElementWhichNeedsDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.hasANextElement
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.index
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.mismatchedIndex
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toEqualFun
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.mismatches
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.notToContainDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSevenNullable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
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

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            oneToSeven().toList(),
            notToContainEntriesSpec.forExpectationCreatorTest(
                "$toBeGreaterThanDescr: 8.1",
                "$toBeGreaterThanDescr: 10.1",
                { toBeGreaterThan(8.1) },
                arrayOf(expectLambda { toBeGreaterThan(10.1) })
            )
        ),
        ExpectationCreatorTestData(
            oneToSeven().toList(),
            notToContainNullableEntriesSpec.forExpectationCreatorTest(
                "$toBeGreaterThanDescr: 8.1",
                "$toBeGreaterThanDescr: 10.1",
                { toBeGreaterThan(8.1) },
                arrayOf(expectLambda { toBeGreaterThan(10.1) })
            )
        )
    )

    @TestFactory
    fun empty_iterable__throws() = testFactoryNonNullable(
        notToContainEntriesSpec, notToContainNullableEntriesSpec
    ) { notToContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.notToContainFun(
            a: Expect<Double>.() -> Unit,
            vararg aX: Expect<Double>.() -> Unit
        ) = notToContainEntriesFunArr(a, aX)

        it("$toEqualFun(4.1) throws AssertionError") {
            expect {
                expect(setOf<Double>().asIterable()).notToContainFun({ toEqual(4.1) })
            }.toThrow<AssertionError> {
                message {
                    toContainRegex(
                        "$hasANextElement$separator" +
                            "$indentRootBulletPoint\\Q$explanatoryBulletPoint\\E$notToContainDescr: $separator" +
                            "$indentRootBulletPoint$indentListBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                            "$indentListBulletPoint$afterExplanatory$toEqualDescr: 4.1.*",
                        "$hintBulletPoint${
                            DescriptionIterableLikeExpectation.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE.getDefault()
                                .format(notToHaveElementsOrNoneFunNameSpec)
                        }"
                    )
                }
            }
        }
    }

    @TestFactory
    fun one_to_seven() = testFactoryNonNullable(
        notToContainEntriesSpec, notToContainNullableEntriesSpec
    ) { notToContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.notToContainFun(
            a: Expect<Double>.() -> Unit,
            vararg aX: Expect<Double>.() -> Unit
        ) = notToContainEntriesFunArr(a, aX)

        describeIterable(::oneToSeven) {
            describe("happy cases") {
                it("$toBeGreaterThanFun(1.2) and $toBeLessThanFun(2.1) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toBeGreaterThan(1.2); toBeLessThan(2.1) })
                }
                it("$toEqualFun(1.2), $toEqualFun(2.2), $toEqualFun(3.2) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toEqual(1.2) }, { toEqual(2.2) }, { toEqual(3.2) })
                }
                it("$toEqualFun(3.2), $toEqualFun(1.2), $toEqualFun(2.2) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toEqual(3.2) }, { toEqual(1.2) }, { toEqual(2.2) })
                }
            }

            describe("failing cases") {
                it("$toBeLessThanFun(4.1) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toBeLessThan(4.1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toBeLessThanDescr: 4.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(1, "2.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(5, "3.1")}.*"
                            )
                        }
                    }
                }
                it("$toEqualFun(1.1), $toEqualFun(4.1) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toEqual(1.1) }, { toEqual(4.1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$indentRootBulletPoint\\Q$listBulletPoint\\E$anElementWhichNeedsDescr: $separator" +
                                    "$afterExplanatory$toEqualDescr: 1.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.1")}.*$separator" +
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
                it("$toEqualFun(4.1), $toEqualFun(1.2) throws AssertionError mentioning only 4.1") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toEqual(4.1) }, { toEqual(1.2) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex("$anElementWhichNeedsDescr: $separator.*$toEqualDescr: 4.1")
                            notToContain.regex("$anElementWhichNeedsDescr: $separator.*$toEqualDescr: 1.2")
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun nullable_cases() = testFactory(notToContainNullableEntriesSpec) { notToContainNullableEntriesFunArr ->
        fun Expect<Iterable<Double?>>.notToContainNullableFun(
            a: (Expect<Double>.() -> Unit)?,
            vararg aX: (Expect<Double>.() -> Unit)?
        ) = notToContainNullableEntriesFunArr(this, a, aX)

        describeIterable(::oneToSeven) {
            it("null does not throw") {
                expect(oneToSeven() as Iterable<Double?>).notToContainNullableFun(null)
            }
        }
        describeIterable(::oneToSevenNullable) {
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

            it("1.2, null throws AssertionError mentioning only null") {
                expect {
                    expect(oneToSevenNullable()).notToContainNullableFun({ toEqual(1.2) }, null)
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
                        this.notToContain("$notToContainDescr: 1.2")
                    }
                }
            }
        }
    }
}
