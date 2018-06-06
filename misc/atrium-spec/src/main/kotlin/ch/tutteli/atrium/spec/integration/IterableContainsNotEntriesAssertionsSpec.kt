package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class IterableContainsNotEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsNotValuesPair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>) -> Assert<Iterable<Double>>>,
    containsNotNullableValuesPair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsNotValuesPair.first to mapToCreateAssertion { containsNotValuesPair.second(this, { toBe(2.3) }, arrayOf()) },
        containsNotNullableValuesPair.first to mapToCreateAssertion { containsNotNullableValuesPair.second(this, { toBe(2.3) }, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsNotValuesPair.first, { containsNotValuesPair.second(this, { toBe(2.3) }, arrayOf()) }, listOf(2.1).asIterable(), listOf(2.1, 2.3)),
        checkingTriple(containsNotNullableValuesPair.first, { containsNotNullableValuesPair.second(this, { toBe(2.3) }, arrayOf()) }, listOf(2.1).asIterable(), listOf(2.1, 2.3))
    ) {})

    fun SpecBody.describeFun(funName: String, body: SpecBody.() -> Unit)
        = group("fun `$funName`", body = body)

    val expect = verbs::checkException

    val (containsNotNullable, containsNotNullableFunArr) = containsNotNullableValuesPair
    fun Assert<Iterable<Double?>>.containsNotNullableFun(a: (Assert<Double>.() -> Unit)?, vararg aX: (Assert<Double>.() -> Unit)?)
        = containsNotNullableFunArr(a, aX)

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
        containsNotValuesPair,
        containsNotNullableValuesPair
    ) {containsNotFunArr ->

        fun Assert<Iterable<Double>>.containsNotFun(a: Assert<Double>.() -> Unit, vararg aX: Assert<Double>.() -> Unit)
            = containsNotFunArr(a, aX)

        context("empty collection") {
            val fluent = verbs.checkImmediately(setOf<Double>())

            test("$toBeFun(4.0) throws AssertionError") {
                expect {
                    fluent.containsNotFun({ toBe(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        containsRegex(
                            "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator"+
                                "$afterExplanatory$toBeDescr: 4.0.*$separator" +
                                "$featureSuccess$numberOfOccurrences: 0$separator"+
                                "$isAfterSuccess: 0.*$separator"+
                                "$featureFailing$hasElement: false$separator" +
                                "$isAfterFailing: true"
                        )
                    }
                }
            }
        }

        context("iterable $oneToSeven") {
            val fluent = verbs.checkImmediately(oneToSeven)

            group("happy case") {
                test("$isGreaterThanFun(1.0) and $isLessThanFun(2.0) does not throw") {
                    fluent.containsNotFun({ isGreaterThan(1.0); isLessThan(2.0) })
                }
                test("$toBeFun(1.1), $toBeFun(2.2), $toBeFun(3.3) does not throw") {
                    fluent.containsNotFun({ toBe(1.1) }, { toBe(2.2) }, { toBe(3.3) })
                }
                test("$toBeFun(3.3), $toBeFun(1.1), $toBeFun(2.2) does not throw") {
                    fluent.containsNotFun({ toBe(3.3) }, { toBe(1.1) }, { toBe(2.2) })
                }
            }

            group("failing assertions; search string at different positions") {
                test("$isLessThanFun(4.0) throws AssertionError") {
                    expect {
                        fluent.containsNotFun({ isLessThan(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator"+
                                    "$afterExplanatory$isLessThanDescr: 4.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 3$separator"+
                                    "$isAfterFailing: 0.*$separator"+
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }
                test("$toBeFun(1.0), $toBeFun(4.0) throws AssertionError") {
                    expect {
                        fluent.containsNotFun({ toBe(1.0) }, { toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator"+
                                    "$afterExplanatory$toBeDescr: 1.0.*$separator" +
                                    "$featureFailing$numberOfOccurrences: 1$separator"+
                                    "$isAfterFailing: 0.*$separator"+
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true$separator"+
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
                test("$toBeFun(4.0), $toBeFun(1.1) throws AssertionError mentioning only 4.0") {
                    expect {
                        fluent.containsNotFun({ toBe(4.0) }, { toBe(1.0) })
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex("$anEntryWhich: $separator.*$toBeDescr: 4.0")
                            containsNot.regex("$anEntryWhich: $separator.*$toBeDescr: 1.1")
                        }
                    }
                }
            }

        }
    }

    nullableCases(describePrefix) {
        describeFun(containsNotNullable) {
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
                                    "$featureFailing$numberOfOccurrences: 2$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }

                test("1.1, null throws AssertionError mentioning only null") {
                    expect {
                        verbs.checkImmediately(oneToSevenNullable).containsNotNullableFun({ toBe(1.1) }, null)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$indentBulletPoint\\Q$listBulletPoint\\E$anEntryWhich: $separator"+
                                    "$afterExplanatory$isDescr: null$separator" +
                                    "$featureFailing$numberOfOccurrences: 2$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                            this.containsNot("$containsNotDescr: 1.1")
                        }
                    }
                }
            }
        }
    }
})
