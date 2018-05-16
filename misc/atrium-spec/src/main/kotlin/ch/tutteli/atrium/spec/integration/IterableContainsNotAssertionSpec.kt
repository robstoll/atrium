package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class IterableContainsNotAssertionSpec(
    verbs: AssertionVerbFactory,
    containsNotValuesPair: Pair<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    containsNotNullableValuesPair: Pair<String, Assert<Iterable<Double?>>.(Double?, Array<out Double?>) -> Assert<Iterable<Double?>>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsNotValuesPair.first to mapToCreateAssertion { containsNotValuesPair.second(this, 2.3, arrayOf()) },
        containsNotNullableValuesPair.first to mapToCreateAssertion { containsNotNullableValuesPair.second(this, 2.3, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsNotValuesPair.first, { containsNotValuesPair.second(this, 2.3, arrayOf()) }, listOf(2.1) as Iterable<Double>, listOf(2.1, 2.3)),
        checkingTriple(containsNotNullableValuesPair.first, { containsNotNullableValuesPair.second(this, 2.3, arrayOf()) }, listOf(2.1) as Iterable<Double>, listOf(2.1, 2.3))
    ) {})

    fun SpecBody.describeFun(funName: String, body: SpecBody.() -> Unit)
        = group("fun `$funName`", body = body)

    val expect = verbs::checkException

    val (containsNot, containsNotFunArr) = containsNotValuesPair

    val (containsNotNullable, containsNotNullableFunArr) = containsNotNullableValuesPair
    fun Assert<Iterable<Double?>>.containsNotNullableFun(a: Double?, vararg aX: Double?) =
        containsNotNullableFunArr(a, aX)

    val containsNotDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()

    group("$describePrefix describe non-nullable cases") {
        mapOf<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Any>(
            containsNot to { a, aX -> this.containsNotFunArr(a, aX) },
            containsNotNullable to { a, aX -> this.containsNotNullableFunArr(a, aX) }
        ).forEach { (describe, containsNotFunArr) ->

            fun Assert<Iterable<Double>>.containsNotFun(a: Double, vararg aX: Double) =
                containsNotFunArr(a, aX.toTypedArray())

            describeFun(describe) {
                val fluent = verbs.checkImmediately(oneToSeven)

                context("iterable $oneToSeven") {
                    group("happy case") {
                        test("1.1 does not throw") {
                            fluent.containsNotFun(1.1)
                        }
                        test("1.1, 2.2, 3.3 does not throw") {
                            fluent.containsNotFun(1.1, 2.2, 3.3)
                        }
                        test("3.3, 1.1, 2.2 does not throw") {
                            fluent.containsNotFun(3.3, 1.1, 2.2)
                        }
                    }

                    group("failing assertions; search string at different positions") {
                        test("4.0 throws AssertionError") {
                            expect {
                                fluent.containsNotFun(4.0)
                            }.toThrow<AssertionError> {
                                message {
                                    containsRegex(
                                        "$containsNotDescr: 4.0.*$separator" +
                                            ".*${CharSequenceContainsSpecBase.numberOfOccurrences}: 3.*$separator" +
                                            ".*${DescriptionBasic.IS.getDefault()}: 0"
                                    )
                                }
                            }
                        }
                        test("1.0, 4.0 throws AssertionError") {
                            expect {
                                fluent.containsNotFun(1.0, 4.0)
                            }.toThrow<AssertionError> {
                                message {
                                    containsRegex(
                                        "$containsNotDescr: 1.0.*$separator" +
                                            ".*${CharSequenceContainsSpecBase.numberOfOccurrences}: 1.*$separator" +
                                            ".*${DescriptionBasic.IS.getDefault()}: 0",
                                        "$containsNotDescr: 4.0.*$separator" +
                                            ".*${CharSequenceContainsSpecBase.numberOfOccurrences}: 3.*$separator" +
                                            ".*${DescriptionBasic.IS.getDefault()}: 0"
                                    )
                                }
                            }
                        }
                        test("4.0, 1.0 once throws AssertionError") {
                            expect {
                                fluent.containsNotFun(4.0, 1.0)
                            }.toThrow<AssertionError> {
                                messageContains("$containsNotDescr: 4.0")
                            }
                        }
                    }
                }
            }
        }
    }

    group("$describePrefix describe nullable cases"){
        describeFun(containsNotNullable){
            context("empty iterable") {
                test("null does not throw") {
                    verbs.checkImmediately(listOf<Double?>()).containsNotNullableFun(null)
                }
            }
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
                        messageContains(
                            "$containsNotDescr: null",
                            "${CharSequenceContainsSpecBase.numberOfOccurrences}: 2",
                            "${DescriptionBasic.IS.getDefault()}: 0"
                        )
                    }
                }

                test("1.1, null throws AssertionError") {
                    expect {
                        verbs.checkImmediately(oneToSevenNullable).containsNotNullableFun(1.1, null)
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$containsNotDescr: null",
                            "${CharSequenceContainsSpecBase.numberOfOccurrences}: 2",
                            "${DescriptionBasic.IS.getDefault()}: 0"
                        )
                        message { this.containsNot("$containsNotDescr: 1.1") }
                    }
                }
            }
        }
    }
})
