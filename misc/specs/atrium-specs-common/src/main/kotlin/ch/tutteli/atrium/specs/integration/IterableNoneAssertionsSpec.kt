package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.style.specification.Suite

abstract class IterableNoneAssertionsSpec(
    verbs: AssertionVerbFactory,
    none: Fun1<Iterable<Double>, Expect<Double>.() -> Unit>,
    noneNullable: Fun1<Iterable<Double?>, (Expect<Double>.() -> Unit)?>,
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
        none.forSubjectLess { toBe(2.3) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        noneNullable.forSubjectLess { toBe(2.3) }
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        verbs, describePrefix, oneToSeven,
        none.forAssertionCreatorSpec("$isGreaterThanDescr: 10.0") { isGreaterThan(10.0) }
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        verbs, "$describePrefix[nullable Element] ", oneToSeven,
        noneNullable.forAssertionCreatorSpec("$isGreaterThanDescr: 10.0") { isGreaterThan(10.0) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException

    val containsNotDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()
    val hasElement = DescriptionIterableAssertion.HAS_ELEMENT.getDefault()

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)

    //@formatter:off
    val featureSuccess = "$indentBulletPoint$indentListBulletPoint\\Q$successfulBulletPoint$featureArrow\\E"
    val featureFailing = "$indentBulletPoint$indentListBulletPoint\\Q$failingBulletPoint$featureArrow\\E"
    val isAfterFailing = "$indentBulletPoint$indentListBulletPoint$indentFailingBulletPoint$indentFeatureArrow\\Q$featureBulletPoint\\E$isDescr"
    val isAfterSuccess = "$indentBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow\\Q$featureBulletPoint\\E$isDescr"
    val afterExplanatory = "$indentBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint\\Q$explanatoryBulletPoint\\E"
    //@formatter:on

    nonNullableCases(
        describePrefix,
        none,
        noneNullable
    ) { noneFun ->

        val fluent = verbs.check(oneToSeven)

        context("iterable $oneToSeven") {
            context("happy case") {
                listOf(1.1, 2.2, 3.3).forEach {
                    it("$toBeDescr($it) does not throw") {
                        fluent.noneFun { toBe(1.1) }
                    }
                }
            }

            context("failing cases; search string at different positions") {
                it("$toBeDescr(4.0) throws AssertionError") {
                    expect {
                        fluent.noneFun { toBe(4.0) }
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator" +
                                    "$afterExplanatory$toBeDescr: 4.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 3$separator" +
                                    "$isAfterFailing: 0.*$separator" +
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
        describeFun("${noneNullable.name} for nullable") {
            val noneFun = noneNullable.lambda

            context("iterable $oneToSeven") {
                it("null does not throw") {
                    verbs.check(oneToSeven as Iterable<Double?>).noneFun(null)
                }
            }
            context("iterable $oneToSevenNullable") {
                it("null throws AssertionError") {
                    expect {
                        verbs.check(oneToSevenNullable).noneFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator" +
                                    "$afterExplanatory$isDescr: null$separator" +
                                    "$featureFailing$numberOfOccurrences: 2$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }

                it("1.0 throws AssertionError") {
                    expect {
                        verbs.check(oneToSevenNullable).noneFun { toBe(1.0) }
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator" +
                                    "$afterExplanatory$toBeDescr: 1.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 1$separator" +
                                    "$isAfterFailing: 0.*$separator" +
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
