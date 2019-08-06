package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

abstract class IterableAllAssertionsSpec(
    verbs: AssertionVerbFactory,
    allPair: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    allNullablePair: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
    rootBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        allPair.first to expectLambda { allPair.second(this) { toBe(2.5) } }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        "${allNullablePair.first} for nullable" to expectLambda { allNullablePair.second(this, null) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        verbs, describePrefix, oneToSeven,
        allPair.forAssertionCreatorSpec("$isGreaterThanDescr: 0.0") { isGreaterThan(0.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        verbs, "$describePrefix[nullable Element] ", oneToSeven,
        allNullablePair.forAssertionCreatorSpec("$isGreaterThanDescr: 0.0") { isGreaterThan(0.0) }
    ) {})

    val assert: (Iterable<Double>) -> Expect<Iterable<Double>> = verbs::check
    val expect = verbs::checkException

    val (allOfNullable, allOfNullableFun) = allNullablePair

    val all = DescriptionIterableAssertion.ALL.getDefault()
    val hasElement = DescriptionIterableAssertion.HAS_ELEMENT.getDefault()
    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)


    val explanatoryPointWithIndent = "$indentBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    fun index(index: Int)
        = listBulletPoint + String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)

    nonNullableCases(
        describePrefix,
        allPair,
        allNullablePair
    ) { allFun ->

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            it("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmpty.allFun { isLessThan(1.0) }
                }.toThrow<AssertionError> {
                    messageContains(
                        "$rootBulletPoint$featureArrow$hasElement: false$separator"+
                        "$indentBulletPoint$indentFeatureArrow$featureBulletPoint$isDescr: true"
                    )
                }
            }
        }

        val fluent = assert(oneToSeven)
        context("iterable $oneToSeven") {
            context("all are $isGreaterThanFun(2.5) and $isLessThanFun(7.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        fluent.allFun { isGreaterThan(2.5); isLessThan(7.0) }
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
                                "$rootBulletPoint$all: $separator",
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

        describeFun("$allOfNullable for nullable") {

            val listOfNulls = listOf(null, null) as Iterable<Double?>
            context("iterable $listOfNulls") {
                it("all are `null` (does not throw)") {
                    verbs.check(listOfNulls).allOfNullableFun(null)
                }
            }

            val list = listOf(null, 1.0, null, 3.0) as Iterable<Double?>
            context("iterable $list") {
                it("$isGreaterThanDescr(0.5)") {
                    expect {
                        verbs.check(list).allOfNullableFun { isGreaterThan(0.5) }
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
                                "$rootBulletPoint$all: $separator",
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
