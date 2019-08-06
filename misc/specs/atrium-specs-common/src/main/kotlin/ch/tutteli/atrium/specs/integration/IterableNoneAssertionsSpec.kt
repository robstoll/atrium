package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.style.specification.Suite

abstract class IterableNoneAssertionsSpec(
    verbs: AssertionVerbFactory,
    nonePair: Pair<String, Expect<Iterable<Double>>.(Expect<Double>.() -> Unit) -> Expect<Iterable<Double>>>,
    noneNullablePair: Pair<String, Expect<Iterable<Double?>>.((Expect<Double>.() -> Unit)?) -> Expect<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        nonePair.first to expectLambda { nonePair.second(this) { toBe(2.3) } }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        "${noneNullablePair.first} for nullable" to expectLambda { noneNullablePair.second(this) { toBe(2.3) } }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

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

        val fluent = verbs.check(oneToSeven)

        context("iterable $oneToSeven") {
            context("happy case") {
                listOf(1.1, 2.2, 3.3).forEach {
                    it("$toBeDescr($it) does not throw") {
                        fluent.containsNotFun { toBe(1.1) }
                    }
                }
            }

            context("failing cases; search string at different positions") {
                it("$toBeDescr(4.0) throws AssertionError") {
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
                it("null does not throw") {
                    verbs.check(oneToSeven as Iterable<Double?>).containsNotNullableFun(null)
                }
            }
            context("iterable $oneToSevenNullable"){
                it("null throws AssertionError") {
                    expect {
                        verbs.check(oneToSevenNullable).containsNotNullableFun(null)
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

                it("1.0 throws AssertionError") {
                    expect {
                        verbs.check(oneToSevenNullable).containsNotNullableFun { toBe(1.0) }
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
