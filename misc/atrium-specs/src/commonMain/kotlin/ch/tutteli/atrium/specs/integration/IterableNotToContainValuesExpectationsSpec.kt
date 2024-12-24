package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator

abstract class IterableNotToContainValuesExpectationsSpec(
    notToContainValues: Fun2<Iterable<Double>, Double, Array<out Double>>,
    notToContainNullableValues: Fun2<Iterable<Double?>, Double?, Array<out Double?>>,
    notToHaveElementsOrNoneFunName: String,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        notToContainValues.forSubjectLessTest(2.3, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        notToContainNullableValues.forSubjectLessTest(2.3, arrayOf())
    ) {})

    fun Expect<Iterable<Double?>>.notToContainNullableFun(a: Double?, vararg aX: Double?) =
        notToContainNullableValues(this, a, aX)

    nonNullableCases(
        describePrefix,
        notToContainValues,
        notToContainNullableValues
    ) { notToContainFunArr ->

        fun Expect<Iterable<Double>>.notToContainFun(a: Double, vararg aX: Double) =
            notToContainFunArr(a, aX.toTypedArray())

        context("empty collection") {

            it("4.0 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).notToContainFun(4.0)
                }.toThrow<AssertionError>().message.toMatch(
                    Regex(
                        "$expectationVerb : .+$lineSeparator" +
                            "\\Q$x\\E${toHaveDescr}\\s+: ${aNextElement}$lineSeparator" +
                            "$indentG$explanatoryBulletPoint$notToContainDescr\\s+: $lineSeparator" +
                            "$indentX$indentExplanatory$listBulletPoint$anElementWhichEquals : 4.0$lineSeparator" +
                            "$indentX$u${USE_NOT_TO_HAVE_ELEMENTS_OR_NONE.string.format(notToHaveElementsOrNoneFunName)}"
                    )
                )
            }
        }

        context("iterable ${oneToSeven().toList()}") {

            context("happy case") {
                it("1.1 does not throw") {
                    expect(oneToSeven()).notToContainFun(1.1)
                }
                it("1.1, 2.2, 3.3 does not throw") {
                    expect(oneToSeven()).notToContainFun(1.1, 2.2, 3.3)
                }
                it("3.3, 1.1, 2.2 does not throw") {
                    expect(oneToSeven()).notToContainFun(3.3, 1.1, 2.2)
                }
            }

            context("failing cases; search string at different positions") {
                it("4.0 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun(4.0)
                    }.toThrow<AssertionError>().message.toContainRegex(
                        "\\Q$g\\E$notToContainDescr : $lineSeparator" +
                            "$indentG\\Q$g\\E$anElementWhichEquals : 4.0$lineSeparator" +
                            "$indentGg$bb$mismatches : $lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(2, "4.0")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(3, "4.0")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(8, "4.0")}"
                    )
                }
                it("1.0, 4.0 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun(1.0, 4.0)
                    }.toThrow<AssertionError>().message.toContainRegex(
                        "\\Q$g\\E$notToContainDescr : $lineSeparator" +
                            "$indentG\\Q$g\\E$anElementWhichEquals : 1.0$lineSeparator" +
                            "$indentGg$bb$mismatches : $lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(0, "1.0")}$lineSeparator" +
                            "$indentG\\Q$g\\E$anElementWhichEquals : 4.0$lineSeparator" +
                            "$indentGg$bb$mismatches : $lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(2, "4.0")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(3, "4.0")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(8, "4.0")}"
                    )
                }
                it("4.0, 1.0 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun(4.0, 1.0)
                    }.toThrow<AssertionError>().message.toContainRegex(
                        "\\Q$g\\E$notToContainDescr : $lineSeparator" +
                            "$indentG\\Q$g\\E$anElementWhichEquals : 4.0$lineSeparator" +
                            "$indentGg$bb$mismatches : $lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(2, "4.0")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(3, "4.0")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(8, "4.0")}$lineSeparator" +
                            "$indentG\\Q$g\\E$anElementWhichEquals : 1.0$lineSeparator" +
                            "$indentGg$bb$mismatches : $lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(0, "1.0")}"
                    )
                }
            }

        }
    }

    nullableCases(describePrefix) {
        describeFun(notToContainNullableValues) {
            context("iterable ${oneToSeven().toList()}") {
                it("null does not throw") {
                    expect(oneToSeven() as Iterable<Double?>).notToContainNullableFun(null)
                }
            }
            context("iterable ${oneToSevenNullable().toList()}") {
                it("null throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).notToContainNullableFun(null)
                    }.toThrow<AssertionError>().message.toContainRegex(
                        "\\Q$g\\E$notToContainDescr : $lineSeparator" +
                            "$indentG\\Q$g\\E$anElementWhichEquals : null$lineSeparator" +
                            "$indentGg$bb$mismatches : $lineSeparator" +
                            //TODO 1.3.0 no need to show the value, we say not to equal null so it is clear the element at those index are null
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(1, "null")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(5, "null")}"
                    )
                }

                it("1.1, null throws AssertionError mentioning only null") {
                    expect {
                        expect(oneToSevenNullable()).notToContainNullableFun(1.1, null)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$g\\E$notToContainDescr : $lineSeparator" +
                                    "$indentG\\Q$g\\E$anElementWhichEquals : null$lineSeparator" +
                                    "$indentGg$bb$mismatches : $lineSeparator" +
                                    //TODO 1.3.0 no need to show the value, we say not to equal null so it is clear the element at those index are null
                                    "$indentGg$indentBb$listBulletPoint${mismatchedIndex(1, "null")}$lineSeparator" +
                                    "$indentGg$indentBb$listBulletPoint${mismatchedIndex(5, "null")}"
                            )
                            notToContain.regex("$notToContainDescr\\s+: 1.1")
                        }
                    }
                }
            }
        }
    }
})
