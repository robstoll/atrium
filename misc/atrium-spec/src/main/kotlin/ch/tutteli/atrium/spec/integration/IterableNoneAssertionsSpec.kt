// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class IterableNoneAssertionsSpec(
    verbs: AssertionVerbFactory,
    nonePair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit) -> Assert<Iterable<Double>>>,
    noneNullablePair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        nonePair.first to mapToCreateAssertion { nonePair.second(this) { toBe(2.3) } },
        "${noneNullablePair.first} for nullable" to mapToCreateAssertion { noneNullablePair.second(this) { toBe(2.3) } }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(nonePair.first, { nonePair.second(this) { toBe(2.3) } }, listOf(2.1).asIterable(), listOf(2.1, 2.3)),
        checkingTriple("${noneNullablePair.first} for nullable", { noneNullablePair.second(this) { toBe(2.3) } }, listOf(2.1).asIterable(), listOf(2.1, 2.3))
    ) {})

    fun SpecBody.describeFun(funName: String, body: SpecBody.() -> Unit)
        = group("fun `$funName`", body = body)

    val expect = verbs::checkException

    val (containsNotNullable, containsNotNullableFun) = noneNullablePair

    val containsNotDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()
    val hasElement = DescriptionIterableAssertion.HAS_ELEMENT.getDefault()

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)

    val featureSuccess = "$indentBulletPoint$indentListBulletPoint\\Q$successfulBulletPoint$featureArrow\\E"
    val featureFailing = "$indentBulletPoint$indentListBulletPoint\\Q$failingBulletPoint$featureArrow\\E"
    val isAfterFailing = "$indentBulletPoint$indentListBulletPoint$indentFailingBulletPoint$indentFeatureArrow\\Q$featureBulletPoint\\E$isDescr"
    val isAfterSuccess = "$indentBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow\\Q$featureBulletPoint\\E$isDescr"
    val afterExplanatory = "$indentBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint\\Q$explanatoryBulletPoint\\E"

    nonNullableCases(
        describePrefix,
        nonePair,
        noneNullablePair
    ){ containsNotFun ->

        val fluent = verbs.checkImmediately(oneToSeven)

        context("iterable $oneToSeven") {
            group("happy case") {
                listOf(1.1, 2.2, 3.3).forEach {
                    test("$toBeDescr($it) does not throw") {
                        fluent.containsNotFun { toBe(1.1) }
                    }
                }
            }

            group("failing cases; search string at different positions") {
                test("$toBeDescr(4.0) throws AssertionError") {
                    expect {
                        fluent.containsNotFun { toBe(4.0) }
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator"+
                                    "$afterExplanatory$toBeDescr: 4.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 3$separator"+
                                    "$isAfterFailing: 0.*$separator"+
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {
        describeFun("$containsNotNullable for nullable") {
            context("iterable $oneToSeven") {
                test("null does not throw") {
                    verbs.checkImmediately(oneToSeven).containsNotNullableFun(null)
                }
            }
            context("iterable $oneToSevenNullable"){
                test("null throws AssertionError") {
                    expect {
                        verbs.checkImmediately(oneToSevenNullable).containsNotNullableFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator"+
                                    "$afterExplanatory$isDescr: null$separator" +
                                    "$featureFailing$numberOfOccurrences: 2$separator"+
                                    "$isAfterFailing: 0.*$separator"+
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }

                test("1.0 throws AssertionError") {
                    expect {
                        verbs.checkImmediately(oneToSevenNullable).containsNotNullableFun { toBe(1.0) }
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator"+
                                    "$afterExplanatory$toBeDescr: 1.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 1$separator"+
                                    "$isAfterFailing: 0.*$separator"+
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }
            }
        }
    }
})
