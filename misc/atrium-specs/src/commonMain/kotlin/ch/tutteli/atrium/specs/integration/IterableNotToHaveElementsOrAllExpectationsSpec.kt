package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

abstract class IterableNotToHaveElementsOrAllExpectationsSpec(
    notToHaveElementsOrAll: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    notToHaveElementsOrAllNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        notToHaveElementsOrAll.first to expectLambda { notToHaveElementsOrAll.second(this) { toEqual(2.5) } }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        "${notToHaveElementsOrAllNullable.first} for nullable" to expectLambda { notToHaveElementsOrAllNullable.second(this, null) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        notToHaveElementsOrAll.forExpectationCreatorTest("$toBeGreaterThanDescr: 0.0") { toBeGreaterThan(0.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        notToHaveElementsOrAllNullable.forExpectationCreatorTest("$toBeGreaterThanDescr: 0.0") { toBeGreaterThan(0.0) }
    ) {})

    val allElementsDescr = DescriptionIterableLikeExpectation.NOT_TO_HAVE_ELEMENTS_OR_ALL.getDefault()

    val explanatoryPointWithIndent = "$indentRootBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    fun index(index: Int) = listBulletPoint + DescriptionIterableLikeExpectation.INDEX.getDefault().format(index)

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
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$rootBulletPoint$allElementsDescr: $separator",
                                "$explanatoryPointWithIndent$toBeGreaterThanDescr: 2.5",
                                "$explanatoryPointWithIndent$toBeLessThanDescr: 7.0",
                                "$warningBulletPoint$mismatches:",
                                "${index(0)}: 1.0",
                                "${index(1)}: 2.0",
                                "${index(9)}: 7.0"
                            )
                        }
                    }
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
})
