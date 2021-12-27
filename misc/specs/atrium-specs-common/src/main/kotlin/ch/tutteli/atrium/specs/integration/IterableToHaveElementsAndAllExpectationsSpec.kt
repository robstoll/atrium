package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

abstract class IterableToHaveElementsAndAllExpectationsSpec(
    toHaveElementsAndAll: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    toHaveElementsAndAllNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        toHaveElementsAndAll.first to expectLambda { toHaveElementsAndAll.second(this) { toEqual(2.5) } }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        "${toHaveElementsAndAllNullable.first} for nullable" to expectLambda { toHaveElementsAndAllNullable.second(this, null) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven().toList().asIterable(),
        toHaveElementsAndAll.forAssertionCreatorSpec("$toBeGreaterThanDescr: 0.0") { toBeGreaterThan(0.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven().toList().asIterable(),
        toHaveElementsAndAllNullable.forAssertionCreatorSpec("$toBeGreaterThanDescr: 0.0") { toBeGreaterThan(0.0) }
    ) {})

    val allElementsDescr = DescriptionIterableLikeExpectation.ALL_ELEMENTS.getDefault()

    val explanatoryPointWithIndent = "$indentRootBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    fun index(index: Int) = listBulletPoint + String.format(DescriptionIterableLikeExpectation.INDEX.getDefault(), index)

    nonNullableCases(
        describePrefix,
        toHaveElementsAndAll,
        toHaveElementsAndAllNullable
    ) { toHaveElementsAndAllFun ->

        context("empty collection") {
            it("throws AssertionError as there needs to be at least one element") {
                expect {
                    expect(fluentEmpty()).toHaveElementsAndAllFun { toBeLessThan(1.0) }
                }.toThrow<AssertionError> {
                    message.toContainRegex(
                        hasANextElement,
                        "$explanatoryBulletPoint$allElementsDescr: ",
                        "$explanatoryPointWithIndent$toBeLessThanDescr: 1.0"
                    )
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("all are $toBeGreaterThanFun(2.5) and $toBeLessThanFun(7.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        expect(oneToSeven()).toHaveElementsAndAllFun { toBeGreaterThan(2.5); toBeLessThan(7.0) }
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
                    expect(oneToSeven()).toHaveElementsAndAllFun { toBeGreaterThan(0.5); toBeLessThan(7.5) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(toHaveElementsAndAllNullable) {
            val toHaveElementsAndAllNullableFun = toHaveElementsAndAllNullable.lambda

            val iterableOfNulls = { sequenceOf<Double?>(null, null).constrainOnce().asIterable() }
            context("iterable ${iterableOfNulls()}") {
                it("all are `null` does not throw") {
                    expect(iterableOfNulls()).toHaveElementsAndAllNullableFun(null)
                }
            }

            context("iterable ${oneToSevenNullable().toList()}") {
                it("$toBeGreaterThanDescr(0.5) throws because two are `null`") {
                    expect {
                        expect(oneToSevenNullable()).toHaveElementsAndAllNullableFun { toBeGreaterThan(0.5) }
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
