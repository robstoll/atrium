@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class IterableAllAssertionsSpec(
    verbs: AssertionVerbFactory,
    allPair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit) -> Assert<Iterable<Double>>>,
    allNullablePair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        allPair.first to mapToCreateAssertion { allPair.second(this) { toBe(2.5) } },
        "${allNullablePair.first} for nullable" to mapToCreateAssertion { allNullablePair.second(this, null) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(allPair.first, { allPair.second(this) { isGreaterThan(2.5) } }, listOf(2.6, 3.0, 4.0).asIterable(), listOf(1.0, 2.5, 3.0)),
        checkingTriple("${allNullablePair.first} for nullable", { allNullablePair.second(this) { isGreaterThan(2.5) } }, listOf(2.6, 3.0, 4.0).asIterable(), listOf(1.0, 2.5, 3.0))
    ) {})

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
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
    ) { containsEntriesFun ->

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            test("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun { isLessThan(1.0) }
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
                        fluent.containsEntriesFun { isGreaterThan(2.5); isLessThan(7.0) }
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
                    fluent.containsEntriesFun { isGreaterThan(0.5); isLessThan(7.5) }
                }
            }
        }

        context("search for entry where the lambda does not specify any assertion") {
            it("throws an ${IllegalArgumentException::class.simpleName}") {
                expect {
                    fluent.containsEntriesFun({})
                }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun("$allOfNullable for nullable") {

            val listOfNulls = listOf(null, null)
            context("iterable $listOfNulls") {
                test("all are `null` (does not throw)") {
                    verbs.checkImmediately(listOfNulls).allOfNullableFun(null)
                }
            }

            val list = listOf(null, 1.0, null, 3.0)
            context("iterable $list") {
                test("$isGreaterThanDescr(0.5)") {
                    expect {
                        verbs.checkImmediately(list).allOfNullableFun { isGreaterThan(0.5) }
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
