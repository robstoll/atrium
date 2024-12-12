package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.*

abstract class IterableNotToContainEntriesExpectationsSpec(
    notToContainEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    notToContainNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    notToHaveElementsOrNoneFunName: String,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        notToContainEntries.forSubjectLess({ toEqual(2.3) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        notToContainNullableEntries.forSubjectLess({ toEqual(2.3) }, arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        *notToContainEntries.forAssertionCreatorSpec(
            "$toBeGreaterThanDescr\\s+: 8.0",
            "$toBeGreaterThanDescr\\s+: 10.0",
            { toBeGreaterThan(8.0) }, arrayOf(expectLambda { toBeGreaterThan(10.0) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        *notToContainNullableEntries.forAssertionCreatorSpec(
            "$toBeGreaterThanDescr\\s+: 8.0",
            "$toBeGreaterThanDescr\\s+: 10.0",
            { toBeGreaterThan(8.0) }, arrayOf(expectLambda { toBeGreaterThan(10.0) })
        )
    ) {})

    fun Expect<Iterable<Double?>>.notToContainNullableFun(
        a: (Expect<Double>.() -> Unit)?,
        vararg aX: (Expect<Double>.() -> Unit)?
    ) = notToContainNullableEntries(this, a, aX)

    val notToContainDescr = DescriptionIterableLikeProof.NOT_TO_CONTAIN.string

    nonNullableCases(
        describePrefix,
        notToContainEntries,
        notToContainNullableEntries
    ) { notToContainFunArr ->

        fun Expect<Iterable<Double>>.notToContainFun(
            a: Expect<Double>.() -> Unit,
            vararg aX: Expect<Double>.() -> Unit
        ) = notToContainFunArr(a, aX)

        context("empty iterable") {

            it("$toEqualFun(4.0) throws AssertionError") {
                expect {
                    expect(setOf<Double>().asIterable()).notToContainFun({ toEqual(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex(
                            "$x${toHaveDescr}\\s+: ${aNextElement}$lineSeparator" +
                                "$indentX$explanatoryBulletPoint$notToContainDescr : $lineSeparator" +
                                "$indentX$indentExplanatory$listBulletPoint$anElementWhichNeedsDescr : $lineSeparator" +
                                "$indentX$indentExplanatory$indentList$explanatoryBulletPoint$toEqualDescr : 4.0$lineSeparator" +
                                "$indentX$u${
                                    DescriptionIterableLikeProof.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE.string.format(
                                        notToHaveElementsOrNoneFunName
                                    )
                                }"
                        )
                    }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {

            context("happy case") {
                it("$toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.0) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toBeGreaterThan(1.0); toBeLessThan(2.0) })
                }
                it("$toEqualFun(1.1), $toEqualFun(2.2), $toEqualFun(3.3) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toEqual(1.1) }, { toEqual(2.2) }, { toEqual(3.3) })
                }
                it("$toEqualFun(3.3), $toEqualFun(1.1), $toEqualFun(2.2) does not throw") {
                    expect(oneToSeven()).notToContainFun({ toEqual(3.3) }, { toEqual(1.1) }, { toEqual(2.2) })
                }
            }

            context("failing cases; search string at different positions") {
                it("$toBeGreaterThanFun(1.0) and $toBeLessThanFun(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toBeGreaterThan(1.0).and.toBeLessThan(4.0) })
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            "$expectationVerb :.*$lineSeparator" +
                                "\\Q$g\\E$notToContainDescr : $lineSeparator" +
                                "$indentG$g$anElementWhichNeedsDescr : $lineSeparator" +
                                "$indentGg$explanatoryBulletPoint$toBeGreaterThanDescr : 1.0$lineSeparator" +
                                "$indentGg$explanatoryBulletPoint$toBeLessThanDescr : 4.0$lineSeparator" +
                                "$indentGg$bb$mismatches : $lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(1, "2.0")}$lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(5, "3.0")}"
                        )
                    )
                }

                it("$toEqualFun(1.0), $toEqualFun(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toEqual(1.0) }, { toEqual(4.0) })
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            "$expectationVerb :.*$lineSeparator" +
                                "\\Q$g\\E$notToContainDescr : $lineSeparator" +
                                "$indentG$g$anElementWhichNeedsDescr : $lineSeparator" +
                                "$indentGg$explanatoryBulletPoint$toEqualDescr : 1.0$lineSeparator" +
                                "$indentGg$bb$mismatches : $lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(0, "1.0")}$lineSeparator" +
                                "$indentG$g$anElementWhichNeedsDescr : $lineSeparator" +
                                "$indentGg$explanatoryBulletPoint$toEqualDescr : 4.0$lineSeparator" +
                                "$indentGg$bb$mismatches : $lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(2, "4.0")}$lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(3, "4.0")}$lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(8, "4.0")}"
                        )
                    )
                }
                it("$toEqualFun(4.0), $toEqualFun(1.1) throws AssertionError mentioning only 4.0") {
                    expect {
                        expect(oneToSeven()).notToContainFun({ toEqual(4.0) }, { toEqual(1.1) })
                    }.toThrow<AssertionError> {
                        message {
                            //TODO 1.3.0 should be $toEqualDescr :
                            toContainRegex("$anElementWhichNeedsDescr : $lineSeparator.*$toEqualDescr\\s+: 4.0")
                            notToContain.regex("$anElementWhichNeedsDescr : $lineSeparator.*$toEqualDescr\\s+: 1.1")
                        }
                    }
                }
            }

        }
    }

    nullableCases(describePrefix) {
        describeFun(notToContainNullableEntries) {
            context("iterable ${oneToSeven().toList()}") {
                it("null does not throw") {
                    expect(oneToSeven() as Iterable<Double?>).notToContainNullableFun(null)
                }
            }
            context("iterable ${oneToSevenNullable().toList()}") {
                it("null throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).notToContainNullableFun(null)
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            "$expectationVerb :.*$lineSeparator" +
                                "\\Q$g\\E$notToContainDescr : $lineSeparator" +
                                "$indentG$g$anElementWhichNeedsDescr : $lineSeparator" +
                                "$indentGg$explanatoryBulletPoint$toEqualDescr : null$lineSeparator" +
                                "$indentGg$bb$mismatches : $lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(1, "null")}$lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(5, "null")}"
                        )
                    )
                }

                it("1.1, null throws AssertionError mentioning only null") {
                    expect {
                        expect(oneToSevenNullable()).notToContainNullableFun({ toEqual(1.1) }, null)
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            "$expectationVerb :.*$lineSeparator" +
                                "\\Q$g\\E$notToContainDescr : $lineSeparator" +
                                "$indentG$g$anElementWhichNeedsDescr : $lineSeparator" +
                                "$indentGg$explanatoryBulletPoint$toEqualDescr : null$lineSeparator" +
                                "$indentGg$bb$mismatches : $lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(1, "null")}$lineSeparator" +
                                "$indentGg${indentBb}$listBulletPoint${mismatchedIndex(5, "null")}"
                        )
                    )
                }
            }
        }
    }
})
