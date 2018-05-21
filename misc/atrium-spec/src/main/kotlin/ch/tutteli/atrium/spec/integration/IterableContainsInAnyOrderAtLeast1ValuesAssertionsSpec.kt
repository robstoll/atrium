package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInAnyOrderValuesPair: Pair<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    containsInAnyOrderNullableValuesPair: Pair<String, Assert<Iterable<Double?>>.(Double?, Array<out Double?>) -> Assert<Iterable<Double?>>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsInAnyOrderValuesPair.first to mapToCreateAssertion { containsInAnyOrderValuesPair.second(this, 1.2, arrayOf()) },
        containsInAnyOrderNullableValuesPair.first to mapToCreateAssertion { containsInAnyOrderNullableValuesPair.second(this, null, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsInAnyOrderValuesPair.first, { containsInAnyOrderValuesPair.second(this, 1.2, arrayOf()) }, listOf(1.2).asIterable(), listOf()),
        checkingTriple(containsInAnyOrderNullableValuesPair.first, { containsInAnyOrderNullableValuesPair.second(this, 1.2, arrayOf()) }, listOf(1.2).asIterable(), listOf())
    ) {})

    fun SpecBody.describeFun(funName: String, body: SpecBody.() -> Unit)
        = group("fun `$funName`", body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException

    val (containsInAnyOrderValues, containsInAnyOrderValuesFunArr) = containsInAnyOrderValuesPair

    val (containsInAnyOrderNullableValues, containsInAnyOrderNullableValuesFunArr) = containsInAnyOrderNullableValuesPair
    fun Assert<Iterable<Double?>>.containsInAnyOrderNullableValuesFun(t: Double?, vararg tX: Double?)
        = containsInAnyOrderNullableValuesFunArr(t, tX)

    group("$describePrefix describe non-nullable cases") {
        mapOf<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Any>(
            containsInAnyOrderValues to { a, aX -> this.containsInAnyOrderValuesFunArr(a, aX) },
            containsInAnyOrderNullableValues to { a, aX -> this.containsInAnyOrderNullableValuesFunArr(a, aX) }
        ).forEach { (describe, containsValuesFunArr) ->
            fun Assert<Iterable<Double>>.containsFun(t: Double, vararg tX: Double)
                = containsValuesFunArr(t, tX.toTypedArray())

            describeFun(describe) {
                context("empty collection") {
                    val fluentEmptyString = assert(setOf())
                    test("$containsInAnyOrderValues 1.0 throws AssertionError") {
                        expect {
                            fluentEmptyString.containsFun(1.0)
                        }.toThrow<AssertionError> {
                            messageContains(
                                "$containsInAnyOrder: 1.0",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                        }
                    }
                }

                val fluent = assert(oneToSeven)
                context("iterable '$oneToSeven'") {

                    describe("happy cases") {
                        (1..7).forEach {
                            val d = it.toDouble()
                            test("$d does not throw") {
                                fluent.containsFun(d)
                            }
                        }
                        test("1.0 and 4.0 does not throw") {
                            fluent.containsFun(1.0, 4.0)
                        }
                        test("1.0 and 1.0 (searching twice in the same assertion) does not throw") {
                            fluent.containsFun(1.0, 1.0)
                        }
                    }

                    describe("error cases") {
                        test("9.5 throws AssertionError") {
                            expect {
                                fluent.containsFun(9.5)
                            }.toThrow<AssertionError> {
                                messageContains(
                                    "$containsInAnyOrder: 9.5",
                                    "$numberOfOccurrences: 0",
                                    "$atLeast: 1"
                                )
                            }
                        }
                        test("9.5 and 7.1 throws AssertionError") {
                            expect {
                                fluent.containsFun(9.5, 7.1)
                            }.toThrow<AssertionError> {
                                messageContains(
                                    "$containsInAnyOrder: 9.5",
                                    "$containsInAnyOrder: 7.1"
                                )
                            }
                        }
                        test("1.0 and 9.5 throws AssertionError") {
                            expect {
                                fluent.containsFun(1.0, 9.5)
                            }.toThrow<AssertionError> {
                                message {
                                    contains("$containsInAnyOrder: 9.5")
                                    containsNot("$containsInAnyOrder: 1.0")
                                }
                            }
                        }

                    }
                }
            }
        }
    }
    group("$describePrefix describe nullable cases") {

        describeFun(containsInAnyOrderNullableValues) {

            val list = listOf(null, 1.0, null, 3.0)
            val fluent = verbs.checkImmediately(list)

            context("iterable $list") {
                listOf(
                    1.0 to arrayOf<Double>(),
                    3.0 to arrayOf<Double>(),
                    null to arrayOf<Double>(),
                    null to arrayOf(3.0, null),
                    null to arrayOf(1.0),
                    1.0 to arrayOf(3.0, null)
                ).forEach { (first, rest) ->
                    val restText = if (rest.isEmpty()) {
                        ""
                    } else {
                        ", ${rest.joinToString()}"
                    }
                    context("search for $first$restText") {
                        test("$first$restText does not throw") {
                            fluent.containsInAnyOrderNullableValuesFun(first, *rest)
                        }
                    }

                }

                context("search for 2.5") {
                    test("2.5 throws AssertionError") {
                        expect {
                            fluent.containsInAnyOrderNullableValuesFun(2.5)
                        }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CONTAINS) } }
                    }
                }
            }
        }
    }
})
