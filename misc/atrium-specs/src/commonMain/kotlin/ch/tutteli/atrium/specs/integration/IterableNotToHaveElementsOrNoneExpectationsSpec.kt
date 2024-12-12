package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator

abstract class IterableNotToHaveElementsOrNoneExpectationsSpec(
    notToHaveElementsOrNone: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    notToHaveElementsOrNoneNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        notToHaveElementsOrNone.forSubjectLess { toEqual(2.3) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        notToHaveElementsOrNoneNullable.forSubjectLess { toEqual(2.3) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        notToHaveElementsOrNone.forAssertionCreatorSpec("$toBeGreaterThanDescr\\s+: 10.0") { toBeGreaterThan(10.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        notToHaveElementsOrNoneNullable.forAssertionCreatorSpec("$toBeGreaterThanDescr\\s+: 10.0") { toBeGreaterThan(10.0) }
    ) {})

    val notToHaveElementsOrNoneDescr = DescriptionIterableLikeProof.NOT_TO_HAVE_ELEMENTS_OR_NONE.string


    fun indentAndMismatch(index: Int, value: String) =
        "$indentG$indentBb$listBulletPoint${mismatchedIndex(index, value)}"

    nonNullableCases(
        describePrefix,
        notToHaveElementsOrNone,
        notToHaveElementsOrNoneNullable
    ) { notToHaveElementsOrNoneFun ->

        context("empty collection") {
            it("does not throw") {
                expect(fluentEmpty()).notToHaveElementsOrNoneFun { toBeLessThan(1.0) }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("happy case") {
                listOf(1.1, 2.2, 3.3).forEach {
                    it("$toEqualDescr($it) does not throw") {
                        expect(oneToSeven()).notToHaveElementsOrNoneFun { toEqual(1.1) }
                    }
                }
            }

            context("failing cases; search string at different positions") {
                it("$toEqualDescr(4.0) throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToHaveElementsOrNoneFun { toEqual(4.0) }
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            "$expectationVerb :.+$lineSeparator"+
                            "\\Q$g\\E$notToHaveElementsOrNoneDescr : $lineSeparator" +
                                "$indentG$explanatoryBulletPoint$toEqualDescr : 4.0$lineSeparator" +
                                "$indentG$bb$mismatches : $lineSeparator" +
                                "${indentAndMismatch(2, "4.0")}$lineSeparator" +
                                "${indentAndMismatch(3, "4.0")}$lineSeparator" +
                                indentAndMismatch(8, "4.0")
                        )
                    )
                }
            }
        }
    }
    nullableCases(describePrefix) {
        describeFun(notToHaveElementsOrNoneNullable) {
            val notToHaveElementsOrNoneFun = notToHaveElementsOrNoneNullable.lambda

            context("iterable ${oneToSeven().toList()}") {
                it("null does not throw") {
                    expect(oneToSeven() as Iterable<Double?>).notToHaveElementsOrNoneFun(null)
                }
            }
            context("iterable ${oneToSevenNullable().toList()}") {
                it("null throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).notToHaveElementsOrNoneFun(null)
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            "$expectationVerb :.+$lineSeparator"+
                                "\\Q$g\\E$notToHaveElementsOrNoneDescr : $lineSeparator" +
                                "$indentG$explanatoryBulletPoint$toEqualDescr : null$lineSeparator" +
                                "$indentG$bb$mismatches : $lineSeparator" +
                                "${indentAndMismatch(1, "null")}$lineSeparator" +
                                indentAndMismatch(5, "null")
                        )
                    )
                }

                it("1.0 throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).notToHaveElementsOrNoneFun { toEqual(1.0) }
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            "$expectationVerb :.+$lineSeparator"+
                                "\\Q$g\\E$notToHaveElementsOrNoneDescr : $lineSeparator" +
                                "$indentG$explanatoryBulletPoint$toEqualDescr : 1.0$lineSeparator" +
                                "$indentG$bb$mismatches : $lineSeparator" +
                                indentAndMismatch(0, "1.0")
                        )
                    )
                }
            }
        }
    }
})
