package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

abstract class IterableAllAssertionsSpec(
    all: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    allNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    rootBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        all.first to expectLambda { all.second(this) { toBe(2.5) } }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        "${allNullable.first} for nullable" to expectLambda { allNullable.second(this, null) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, oneToSeven,
        all.forAssertionCreatorSpec("$isGreaterThanDescr: 0.0") { isGreaterThan(0.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable Element] ", oneToSeven,
        allNullable.forAssertionCreatorSpec("$isGreaterThanDescr: 0.0") { isGreaterThan(0.0) }
    ) {})

    val allDescr = DescriptionIterableAssertion.ALL.getDefault()
    val hasElement = DescriptionIterableAssertion.HAS_ELEMENT.getDefault()
    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)


    val explanatoryPointWithIndent = "$indentBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    fun index(index: Int) = listBulletPoint + String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)

    nonNullableCases(
        describePrefix,
        all,
        allNullable
    ) { allFun ->

        context("empty collection") {
            it("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmpty.allFun { isLessThan(1.0) }
                }.toThrow<AssertionError> {
                    messageContains(
                        "$rootBulletPoint$featureArrow$hasElement: false$separator" +
                            "$indentBulletPoint$indentFeatureArrow$featureBulletPoint$isDescr: true"
                    )
                }
            }
        }

        val fluent = expect(oneToSeven)
        context("iterable $oneToSeven") {
            context("all are $isGreaterThanFun(2.5) and $isLessThanFun(7.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        fluent.allFun { isGreaterThan(2.5); isLessThan(7.0) }
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
                                "$rootBulletPoint$allDescr: $separator",
                                "$explanatoryPointWithIndent$isGreaterThanDescr: 2.5",
                                "$explanatoryPointWithIndent$isLessThanDescr: 7.0",
                                "$warningBulletPoint$mismatches:",
                                "${index(0)}: 1.0",
                                "${index(1)}: 2.0",
                                "${index(9)}: 7.0"
                            )
                        }
                    }
                }
            }

            context("all are $isGreaterThanFun(0.5) and $isLessThanFun(7.5)") {
                it("does not throw an exception") {
                    fluent.allFun { isGreaterThan(0.5); isLessThan(7.5) }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun("${allNullable.name} for nullable") {
            val allNullableFun = allNullable.lambda

            val listOfNulls = listOf(null, null) as Iterable<Double?>
            context("iterable $listOfNulls") {
                it("all are `null` (does not throw)") {
                    expect(listOfNulls).allNullableFun(null)
                }
            }

            val list = listOf(null, 1.0, null, 3.0) as Iterable<Double?>
            context("iterable $list") {
                it("$isGreaterThanDescr(0.5)") {
                    expect {
                        expect(list).allNullableFun { isGreaterThan(0.5) }
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
                                "$rootBulletPoint$allDescr: $separator",
                                "$explanatoryPointWithIndent$isGreaterThanDescr: 0.5",
                                "$warningBulletPoint$mismatches:",
                                "${index(0)}: null",
                                "${index(2)}: null"
                            )
                        }
                    }
                }
            }
        }
    }
})
