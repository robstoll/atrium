package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator

abstract class IterableNotToHaveElementsOrAllExpectationsSpec(
    notToHaveElementsOrAll: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    notToHaveElementsOrAllNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        notToHaveElementsOrAll.first to expectLambda { notToHaveElementsOrAll.second(this) { toEqual(2.5) } }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        "${notToHaveElementsOrAllNullable.first} for nullable" to expectLambda {
            notToHaveElementsOrAllNullable.second(this, null)
        }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        notToHaveElementsOrAll.forAssertionCreatorSpec("$toBeGreaterThanDescr\\s+: 0.0") { toBeGreaterThan(0.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        notToHaveElementsOrAllNullable.forAssertionCreatorSpec("$toBeGreaterThanDescr\\s+: 0.0") { toBeGreaterThan(0.0) }
    ) {})

    val allElementsDescr = DescriptionIterableLikeProof.NOT_TO_HAVE_ELEMENTS_OR_ALL.string

    fun index(index: Int) = DescriptionIterableLikeProof.INDEX.string.format(index)

    nonNullableCases(
        describePrefix,
        notToHaveElementsOrAll,
        notToHaveElementsOrAllNullable
    ) { notToHaveElementsOrAllFun ->

        context("empty collection") {
            it("does not throw") {
                expect(fluentEmpty()).notToHaveElementsOrAllFun { toBeLessThan(1.0) }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("all need $toBeGreaterThanFun(2.5) and $toBeLessThanFun(7.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        expect(oneToSeven()).notToHaveElementsOrAllFun { toBeGreaterThan(2.5); toBeLessThan(7.0) }
                    }.toThrow<AssertionError>().message.toMatch(
                        Regex(
                            "${expectationVerb}\\s+:.*$lineSeparator" +
                                "\\Q$g\\E${allElementsDescr} : $lineSeparator" +
                                "$indentG$explanatoryBulletPoint$toBeGreaterThanDescr\\s+: 2.5$lineSeparator" +
                                "$indentG$explanatoryBulletPoint$toBeLessThanDescr\\s+: 7.0$lineSeparator" +
                                "${indentG}$bb$mismatches : $lineSeparator" +
                                //TODO 1.3.0 should be on an own level so that they are not aligned with the description, i.e
                                // ${index(0)} :
                                // also, we should see the sub-assertions which failed
                                "${indentG}${indentBb}${listBulletPoint}${index(0)}\\s+: 1.0$lineSeparator" +
                                "${indentG}${indentBb}${listBulletPoint}${index(1)}\\s+: 2.0$lineSeparator" +
                                "${indentG}${indentBb}${listBulletPoint}${index(9)}\\s+: 7.0"
                        )
                    )
                }
            }

            context("all are $toBeGreaterThanFun(0.5) and $toBeLessThanFun(7.5)") {
                it("does not throw an exception") {
                    expect(oneToSeven()).notToHaveElementsOrAllFun { toBeGreaterThan(0.5); toBeLessThan(7.5) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(notToHaveElementsOrAllNullable) {
            val notToHaveElementsOrAllNullableFun = notToHaveElementsOrAllNullable.lambda

            val iterableOfNulls = { sequenceOf<Double?>(null, null).constrainOnce().asIterable() }
            context("iterable ${iterableOfNulls()}") {
                it("all are `null` does not throw") {
                    expect(iterableOfNulls()).notToHaveElementsOrAllNullableFun(null)
                }
            }

            context("iterable ${oneToSevenNullable().toList()}") {
                it("$toBeGreaterThanDescr(0.5) throws because two are `null`") {
                    expect {
                        expect(oneToSevenNullable()).notToHaveElementsOrAllNullableFun { toBeGreaterThan(0.5) }
                    }.toThrow<AssertionError> {
                        message {
                            //TODO 1.3.0 should be $x and not $g as we don't have details, should be use the debug icon instead of warning??
                            toContain.exactly(1).values(
                                "$g$allElementsDescr : $lineSeparator",
                                "$indentG$explanatoryBulletPoint$toBeGreaterThanDescr : 0.5",
                                "$bb$mismatches :"
                            )
                            //TODO 1.3.0 should be on an own level so that they are not aligned with the description, i.e
                            // ${index(0)} :
                            // we don't need to see why it failed because we only defined one expectation
                            toContain.exactly(1).regex(
                                "${index(1)}\\s+: null",
                                "${index(5)}\\s+: null"
                            )
                        }
                    }
                }
            }
        }
    }
})
