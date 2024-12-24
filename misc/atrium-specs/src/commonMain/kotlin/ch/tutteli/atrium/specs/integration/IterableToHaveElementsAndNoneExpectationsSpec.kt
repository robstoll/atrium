package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator

abstract class IterableToHaveElementsAndNoneExpectationsSpec(
    toHaveElementsAndNone: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    toHaveElementsAndNoneNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    notToHaveElementsOrNoneFunName: String,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toHaveElementsAndNone.forSubjectLess { toEqual(2.3) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toHaveElementsAndNoneNullable.forSubjectLess { toEqual(2.3) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        toHaveElementsAndNone.forAssertionCreatorSpec("$toBeGreaterThanDescr\\s+: 10.0") { toBeGreaterThan(10.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        toHaveElementsAndNoneNullable.forAssertionCreatorSpec("$toBeGreaterThanDescr\\s+: 10.0") { toBeGreaterThan(10.0) }
    ) {})

    nonNullableCases(
        describePrefix,
        toHaveElementsAndNone,
        toHaveElementsAndNoneNullable
    ) { toHaveElementsAndNoneFun ->

        context("empty iterable") {
            it("throws AssertionError as there needs to be at least one element") {
                expect {
                    expect(fluentEmpty()).toHaveElementsAndNoneFun { toBeLessThan(1.0) }
                }.toThrow<AssertionError>().message.toContainRegex(
                    "\\Q$x\\E$toHaveDescr\\s+: ${aNextElement}$lineSeparator" +
                        "$indentG$explanatoryBulletPoint$notToContainDescr : $lineSeparator" +
                        "$indentG$indentExplanatory$listBulletPoint$anElementWhichNeedsDescr : $lineSeparator" +
                        "$indentG$indentExplanatory$indentList$explanatoryBulletPoint$toBeLessThanDescr : 1.0$lineSeparator" +
                        "$indentX$u${USE_NOT_TO_HAVE_ELEMENTS_OR_NONE.string.format(notToHaveElementsOrNoneFunName)}"
                )
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("happy case") {
                listOf(1.1, 2.2, 3.3).forEach {
                    it("$toEqualDescr($it) does not throw") {
                        expect(oneToSeven()).toHaveElementsAndNoneFun { toEqual(1.1) }
                    }
                }
            }

            context("failing cases; search string at different positions") {
                it("$toEqualDescr(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toHaveElementsAndNoneFun { toEqual(4.0) }
                    }.toThrow<AssertionError>().message.toContainRegex(
                        "\\Q$g\\E${notToContainDescr} : $lineSeparator" +
                            "$indentG\\Q$g\\E$anElementWhichNeedsDescr : $lineSeparator" +
                            "$indentGg$explanatoryBulletPoint$toEqualDescr : 4.0$lineSeparator" +
                            "$indentGg$bb$mismatches : $lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(2, "4.0")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(3, "4.0")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(8, "4.0")}"
                    )
                }
            }
        }
    }
    nullableCases(describePrefix) {
        describeFun(toHaveElementsAndNoneNullable) {
            val toHaveElementsAndNoneFun = toHaveElementsAndNoneNullable.lambda

            context("iterable ${oneToSeven().toList()}") {
                it("null does not throw") {
                    expect(oneToSeven() as Iterable<Double?>).toHaveElementsAndNoneFun(null)
                }
            }
            context("iterable ${oneToSevenNullable().toList()}") {
                it("null throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).toHaveElementsAndNoneFun(null)
                    }.toThrow<AssertionError>().message.toContainRegex(
                        "\\Q$g\\E${notToContainDescr} : $lineSeparator" +
                            "$indentG\\Q$g\\E$anElementWhichNeedsDescr : $lineSeparator" +
                            "$indentGg$explanatoryBulletPoint$toEqualDescr : null$lineSeparator" +
                            "$indentGg$bb$mismatches : $lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(1, "null")}$lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(5, "null")}"
                    )
                }

                it("1.0 throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).toHaveElementsAndNoneFun { toEqual(1.0) }
                    }.toThrow<AssertionError>().message.toContainRegex(
                        "\\Q$g\\E${notToContainDescr} : $lineSeparator" +
                            "$indentG\\Q$g\\E$anElementWhichNeedsDescr : $lineSeparator" +
                            "$indentGg$explanatoryBulletPoint$toEqualDescr : 1.0$lineSeparator" +
                            "$indentGg$bb$mismatches : $lineSeparator" +
                            "$indentGg$indentBb$listBulletPoint${mismatchedIndex(0, "1.0")}"
                    )
                }
            }
        }
    }
})
