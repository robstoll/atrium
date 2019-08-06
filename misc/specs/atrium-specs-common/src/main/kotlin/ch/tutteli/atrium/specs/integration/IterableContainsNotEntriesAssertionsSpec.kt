package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsNotEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsNotValuesPair: Pair<String, Expect<Iterable<Double>>.(Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>) -> Expect<Iterable<Double>>>,
    containsNotNullableValuesPair: Pair<String, Expect<Iterable<Double?>>.((Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>) -> Expect<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        containsNotValuesPair.first to expectLambda { containsNotValuesPair.second(this, { toBe(2.3) }, arrayOf()) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
    "${containsNotNullableValuesPair.first} for nullable" to expectLambda { containsNotNullableValuesPair.second(this, { toBe(2.3) }, arrayOf()) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException

    val (containsNotNullable, containsNotNullableFunArr) = containsNotNullableValuesPair
    fun Expect<Iterable<Double?>>.containsNotNullableFun(a: (Expect<Double>.() -> Unit)?, vararg aX: (Expect<Double>.() -> Unit)?)
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

        fun Expect<Iterable<Double>>.containsNotFun(a: Expect<Double>.() -> Unit, vararg aX: Expect<Double>.() -> Unit)
            = containsNotFunArr(a, aX)

        context("empty collection") {
            val fluent = verbs.check(setOf<Double>().asIterable())

            it("$toBeFun(4.0) throws AssertionError") {
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
            val fluent = verbs.check(oneToSeven)

            context("happy case") {
                it("$isGreaterThanFun(1.0) and $isLessThanFun(2.0) does not throw") {
                    fluent.containsNotFun({ isGreaterThan(1.0); isLessThan(2.0) })
                }
                it("$toBeFun(1.1), $toBeFun(2.2), $toBeFun(3.3) does not throw") {
                    fluent.containsNotFun({ toBe(1.1) }, { toBe(2.2) }, { toBe(3.3) })
                }
                it("$toBeFun(3.3), $toBeFun(1.1), $toBeFun(2.2) does not throw") {
                    fluent.containsNotFun({ toBe(3.3) }, { toBe(1.1) }, { toBe(2.2) })
                }
            }

            context("failing cases; search string at different positions") {
                it("$isLessThanFun(4.0) throws AssertionError") {
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
                it("$toBeFun(1.0), $toBeFun(4.0) throws AssertionError") {
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
                it("$toBeFun(4.0), $toBeFun(1.1) throws AssertionError mentioning only 4.0") {
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
                                    "$featureFailing$numberOfOccurrences: 2$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }

                it("1.1, null throws AssertionError mentioning only null") {
                    expect {
                        verbs.check(oneToSevenNullable).containsNotNullableFun({ toBe(1.1) }, null)
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
