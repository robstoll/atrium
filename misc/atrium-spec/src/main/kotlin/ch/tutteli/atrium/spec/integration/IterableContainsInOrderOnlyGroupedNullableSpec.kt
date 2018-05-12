package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.CONTAINS
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.CONTAINS_NOT
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction1

abstract class IterableContainsInOrderOnlyGroupedNullableSpec(
    verbs: AssertionVerbFactory,
    //TODO nullable values should also be tested
    containsInOrderOnlyGroupedNullableEntriesPair: Pair<String, Assert<Iterable<Double?>>.(Group<(Assert<Double>.() -> Unit)?>, Group<(Assert<Double>.() -> Unit)?>, Array<out Group<(Assert<Double>.() -> Unit)?>>) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    fun group(vararg doubles: (Assert<Double>.() -> Unit)?) = object : Group<(Assert<Double>.() -> Unit)?> {
        override fun toList() = doubles.toList()
    }

    include(object : SubjectLessAssertionSpec<Iterable<Double?>>(describePrefix,
        containsInOrderOnlyGroupedNullableEntriesPair.first to mapToCreateAssertion { containsInOrderOnlyGroupedNullableEntriesPair.second(this, group(null), group({ toBe(1.0) }, { toBe(2.0) }), arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double?>>(
        verbs, describePrefix,
        checkingTriple(containsInOrderOnlyGroupedNullableEntriesPair.first, { containsInOrderOnlyGroupedNullableEntriesPair.second(this, group(null), group({ toBe(1.0) }, { toBe(2.0) }), arrayOf()) }, listOf(null, 2.0, 1.0) as Iterable<Double?>, listOf(1.0, null, 2.0))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double?>) -> Assert<Iterable<Double?>> = verbs::checkImmediately
    val expect = verbs::checkException
    val list = listOf(null, 1.0, null, 3.0)
    val fluent = assert(list)

    val (containsInOrderOnlyGroupedNullableEntries, containsInOrderOnlyGroupedNullableEntriesArr) = containsInOrderOnlyGroupedNullableEntriesPair
    fun Assert<Iterable<Double?>>.containsInOrderOnlyGroupedNullableEntriesFun(t1: Group<(Assert<Double>.() -> Unit)?>, t2: Group<(Assert<Double>.() -> Unit)?>, vararg tX: Group<(Assert<Double>.() -> Unit)?>)
        = containsInOrderOnlyGroupedNullableEntriesArr(t1, t2, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)
    val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)

    val isDescr = DescriptionBasic.IS.getDefault()

    fun index(fromIndex: Int, toIndex: Int)
        = String.format(DescriptionIterableAssertion.INDEX_FROM_TO.getDefault(), fromIndex, toIndex)

    fun entry(prefix: String, bulletPoint: String, indentBulletPoint: String, expected: Array<out String>)
        = expected.joinToString(".*$separator") {
        "$prefix\\Q$bulletPoint$anEntryWhich: \\E$separator" +
            "$prefix$indentBulletPoint$indentListBulletPoint$explanatoryBulletPoint$it"
    }

    fun size(prefix: String, bulletPoint: String, actual: Int, expected: Int)
        = "$prefix\\Q$bulletPoint\\E${featureArrow}size: $actual[^:]+: $expected"


    val afterFail = "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun failAfterFail(vararg expected: String)
        = entry(afterFail, failingBulletPoint, indentFailingBulletPoint, expected)

    fun successAfterFail(vararg expected: String)
        = entry(afterFail, successfulBulletPoint, indentSuccessfulBulletPoint, expected)

    fun failSizeAfterFail(actual: Int, expected: Int)
        = size(afterFail, failingBulletPoint, actual, expected)

    fun successSizeAfterFail(size: Int)
        = size(afterFail, successfulBulletPoint, size, size)

    val afterSuccess = "$indentBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint"
    fun successAfterSuccess(vararg expected: String)
        = entry(afterSuccess, successfulBulletPoint, indentSuccessfulBulletPoint, expected)

    fun successSizeAfterSuccess(size: Int)
        = size(afterSuccess, successfulBulletPoint, size, size)


    fun Assert<CharSequence>.indexSuccess(fromIndex: Int, toIndex: Int, actual: List<Double?>, vararg expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")

        )
    }

    fun Assert<CharSequence>.indexFail(fromIndex: Int, toIndex: Int, actual: List<Double?>, vararg expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${index(fromIndex, toIndex)}: $actual\\E.*$separator" +
                "$indentBulletPoint$indentFailingBulletPoint$indentFeatureArrow$featureBulletPoint$containsInAnyOrderOnly: $separator" +
                expected.joinToString(".*$separator")
        )
    }

    fun SpecBody.absentSubjectTests(testeeFun: Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>) {
        context("$returnValueOfFun(...), absent subject and explanation required") {
            test("empty iterable, states that iterable was empty") {
                expect {
                    //TODO replace with returnValueOf as soon as https://youtrack.jetbrains.com/issue/KT-17340 is fixed
                    assert(setOf()).testeeFun({
                        AssertImpl.feature.returnValueOf1(
                            this,
                            subject::compareTo,
                            2.0,
                            "compareTo"
                        ).toBe(0)
                    }, arrayOf())
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE) } }
            }
            test("only null, states that iterable only returned null") {
                expect {
                    assert(listOf(null, null)).testeeFun({
                        //TODO get rid of val as soon as https://youtrack.jetbrains.com/issue/KT-17340 is fixed
                        val f: KFunction1<Double, Int> = subject::compareTo
                        returnValueOf(f, 2.0)
                    }, arrayOf())
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_ONLY_NULL) } }
            }

            test("$list, it outputs explanation (since we have a non-null entry)") {
                expect {
                    //TODO replace with returnValueOf as soon as https://youtrack.jetbrains.com/issue/KT-17340 is fixed
                    assert(list).testeeFun({
                        AssertImpl.feature.returnValueOf1(
                            this,
                            subject::compareTo,
                            2.0,
                            "compareTo"
                        ).toBe(0)
                    }, arrayOf())
                }.toThrow<AssertionError> {
                    messageContains(
                        "$anEntryWhich: $separator",
                        "compareTo(2.0):",
                        "${DescriptionAnyAssertion.TO_BE.getDefault()}: 0"
                    )
                }
            }
        }
    }

    describeFun(containsInOrderOnlyGroupedNullableEntries) {
        absentSubjectTests({ t, tX ->
            if (tX.isEmpty()) {
                containsInOrderOnlyGroupedNullableEntriesFun(group(t), group(null))
            } else {
                containsInOrderOnlyGroupedNullableEntriesFun(group(t), group(*tX))
            }
        })

        context("iterable $list") {

            describe("happy case") {
                test("[1.0, null], [null, 3.0]") {
                    fluent.containsInOrderOnlyGroupedNullableEntriesFun(
                        group({ toBe(1.0) }, null),
                        group(null, { toBe(3.0) })
                    )
                }
                test("[null], [null, $isGreaterThanFun(2.0), $isLessThanFun(5.0)]") {
                    fluent.containsInOrderOnlyGroupedNullableEntriesFun(
                        group(null),
                        group(null, { isGreaterThan(2.0) }, { isLessThan(5.0) })
                    )
                }
            }

            describe("error cases (throws AssertionError)") {

                test("[null, null], [$isLessThanFun(5.0), $isGreaterThanFun(2.0)] -- wrong order") {
                    expect {
                        fluent.containsInOrderOnlyGroupedNullableEntriesFun(
                            group(null, null),
                            group({ isLessThan(5.0) }, { isGreaterThan(2.0) }))
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexFail(0, 1, listOf(null, 1.0),
                                successAfterFail("$isDescr: null"),
                                failAfterFail("$isDescr: null"),
                                successSizeAfterFail(2)
                            )
                            indexFail(2, 3, listOf(null, 3.0),
                                successAfterFail("$isLessThanDescr: 5.0"),
                                failAfterFail("$isGreaterThanDescr: 2.0"),
                                successSizeAfterFail(2)
                            )
                            containsSize(4, 4)
                        }
                    }
                }

                test("[null, 1.0], [3.0, null, null] -- null too much") {
                    expect {
                        fluent.containsInOrderOnlyGroupedNullableEntriesFun(
                            group(null, { toBe(1.0) }),
                            group({ toBe(3.0) }, null, null)
                        )
                    }.toThrow<AssertionError> {
                        message {
                            contains("$containsInOrderOnlyGrouped:")
                            indexSuccess(0, 1, listOf(null, 1.0),
                                successAfterSuccess("$isDescr: null"),
                                successAfterSuccess("$toBeDescr: 1.0"),
                                successSizeAfterSuccess(2)
                            )
                            indexFail(2, 4, listOf(null, 3.0),
                                successAfterFail("$toBeDescr: 3.0"),
                                successAfterFail("$isDescr: null"),
                                failAfterFail("$isDescr: null"),
                                failSizeAfterFail(2, 3)
                            )
                            containsSize(4, 5)
                        }
                    }
                }
            }
        }
    }
})
