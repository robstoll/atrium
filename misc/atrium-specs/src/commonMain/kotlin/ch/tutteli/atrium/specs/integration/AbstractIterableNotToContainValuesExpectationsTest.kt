package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.notToContain
import ch.tutteli.atrium.api.fluent.en_GB.toContainRegex
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterExplanatoryIndent
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.afterMismatchedWarning
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.hasANextElement
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.mismatchedIndex
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.anElementWhichEquals
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyIterable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.mismatches
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.notToContainDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSevenNullable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation
import kotlin.reflect.KProperty

@Suppress("FunctionName")
abstract class AbstractIterableNotToContainValuesExpectationsTest(
    private val notToContainValuesSpec: Fun2<Iterable<Double>, Double, Array<out Double>>,
    private val notToContainNullableValuesSpec: Fun2<Iterable<Double?>, Double?, Array<out Double?>>,
    private val notToHaveElementsOrNoneFunNameSpec: String,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            notToContainValuesSpec.forSubjectLessTest(2.3, arrayOf())
        ),
        SubjectLessTestData(
            notToContainNullableValuesSpec.forSubjectLessTest(2.3, arrayOf())
        )
    )

    val anElementWhichIsWithIndent = "$indentRootBulletPoint$listBulletPoint$anElementWhichEquals"


    @TestFactory
    fun empty_collection__throws() = testFactoryNonNullable(
        notToContainValuesSpec, notToContainNullableValuesSpec
    ) { notToContainValuesFunArr ->
        fun Expect<Iterable<Double>>.notToContainFun(a: Double, vararg aX: Double) =
            notToContainValuesFunArr(a, aX.toTypedArray())

        it("always throws as it implicitly check that it is not empty") {
            expect {
                expect(emptyIterable()).notToContainFun(4.1)
            }.toThrow<AssertionError> {
                message {
                    toContainRegex(
                        "$hasANextElement$separator" +
                            "$indentRootBulletPoint\\Q$explanatoryBulletPoint\\E$notToContainDescr: $separator" +
                            "$indentListBulletPoint$anElementWhichIsWithIndent: 4.1.*",
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
        notToContainValuesSpec, notToContainNullableValuesSpec
    ) { notToContainValuesFunArr ->
        fun Expect<Iterable<Double>>.notToContainFun(a: Double, vararg aX: Double) =
            notToContainValuesFunArr(a, aX.toTypedArray())

        describeIterable(::oneToSeven) {
            describe("happy cases") {
                it("1.2 does not throw") {
                    expect(oneToSeven()).notToContainFun(1.2)
                }
                it("1.2, 2.2, 3.3 does not throw") {
                    expect(oneToSeven()).notToContainFun(1.2, 2.2, 3.3)
                }
                it("3.3, 1.2, 2.2 does not throw") {
                    expect(oneToSeven()).notToContainFun(3.3, 1.2, 2.2)
                }
            }

            describe("failing cases") {

                it("4.1 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun(4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$anElementWhichIsWithIndent: 4.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(2, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(3, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(8, "4.1")}.*"
                            )
                        }
                    }
                }
                it("1.1, 4.1 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun(1.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$anElementWhichIsWithIndent: 1.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.1")}.*$separator" +
                                    "$anElementWhichIsWithIndent: 4.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(2, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(3, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(8, "4.1")}.*"
                            )
                        }
                    }
                }
                it("4.1, 1.1 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun(4.1, 1.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                    "$anElementWhichIsWithIndent: 4.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(2, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(3, "4.1")}.*$separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(8, "4.1")}.*$separator" +
                                    "$anElementWhichIsWithIndent: 1.1.*$separator" +
                                    "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                    "$afterMismatchedWarning${mismatchedIndex(0, "1.1")}.*"
                            )
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun nullable_cases() = testFactory(notToContainNullableValuesSpec) { notToContainNullableValuesFunArr ->
        fun Expect<Iterable<Double?>>.notToContainNullableFun(a: Double?, vararg aX: Double?) =
            notToContainNullableValuesFunArr(this, a, aX)

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
                                "$anElementWhichIsWithIndent: null$separator" +
                                "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                "$afterMismatchedWarning${mismatchedIndex(1, "null")}.*$separator" +
                                "$afterMismatchedWarning${mismatchedIndex(5, "null")}.*"
                        )
                    }
                }
            }
            it("1.2, null throws AssertionError mentioning only null") {
                expect {
                    expect(oneToSevenNullable()).notToContainNullableFun(1.2, null)
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex(
                            "\\Q$rootBulletPoint\\E$notToContainDescr: $separator" +
                                "$anElementWhichIsWithIndent: null$separator" +
                                "$afterExplanatoryIndent\\Q$warningBulletPoint$mismatches:\\E $separator" +
                                "$afterMismatchedWarning${mismatchedIndex(1, "null")}.*$separator" +
                                "$afterMismatchedWarning${mismatchedIndex(5, "null")}.*"
                        )
                        notToContain("$notToContainDescr: 1.2")
                    }
                }
            }
        }
    }
}
