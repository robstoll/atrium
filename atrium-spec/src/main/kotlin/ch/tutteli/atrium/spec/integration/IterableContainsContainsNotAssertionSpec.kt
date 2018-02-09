package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS_NOT
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class IterableContainsContainsNotAssertionSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    containsNotPair: Pair<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, 1.2, arrayOf()) },
        containsNotPair.first to mapToCreateAssertion { containsNotPair.second(this, 2.5, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, 1.2, arrayOf()) }, listOf(1.2) as Iterable<Double>, listOf()),
        checkingTriple(containsNotPair.first, { containsNotPair.second(this, 2.5, arrayOf()) }, listOf(1.1) as Iterable<Double>, listOf(2.5))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (contains, containsFunArr) = containsPair
    fun Assert<Iterable<Double>>.containsFun(t: Double, vararg tX: Double)
        = containsFunArr(t, tX.toTypedArray())

    val (containsNot, containsNotFunArr) = containsNotPair
    fun Assert<Iterable<Double>>.containsNotFun(t: Double, vararg tX: Double)
        = containsNotFunArr(t, tX.toTypedArray())


    describeFun(contains, containsNot) {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$contains 1.0 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$containsInAnyOrder: 1.0",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
            }
            test("$containsNot 1.0 does not throw") {
                fluentEmptyString.containsNotFun(1.0)
            }
        }

        context("iterable '$oneToSeven'") {

            context("search for 1.0 and 4.0") {
                test("$contains 1.0 does not throw") {
                    fluent.containsFun(1.0)
                }
                test("$containsNot 1.0 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.0)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS_NOT) } }
                }

                test("$contains 1.0 and 4.0 does not throw") {
                    fluent.containsFun(1.0, 4.0)
                }
                test("$containsNot 1.0 and 4.0 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.0, 4.0)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS_NOT) } }
                }
            }

            context("search for 9.5 and 7.1") {
                test("$contains 9.5 and 7.1 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5, 7.1)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS) } }
                }
                test("$containsNot 9.5 and 7.1 does not throw") {
                    fluent.containsNotFun(9.5, 7.1)
                }
            }

            context("search for 1.0 and 9.5") {
                test("$contains 9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(CONTAINS) } }
                }
                test("$containsNot 9.5 does not throw") {
                    fluent.containsNotFun(9.5)
                }

                test("$contains 1.0 and 9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(1.0, 9.5)
                    }.toThrow<AssertionError> { message { contains(CONTAINS.getDefault(), 9.5) } }
                }
                test("$containsNot 1.0 and 9.5 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.0, 9.5)
                    }.toThrow<AssertionError> { message { contains(CONTAINS_NOT.getDefault(), 1.0) } }
                }
            }

            test("$contains 1.0 and 1.0 (searching twice in the same assertion) does not throw") {
                fluent.containsFun(1.0, 1.0)
            }

            test("$containsNot 9.5 and 9.5 does not throw") {
                fluent.containsNotFun(9.5, 9.5)
            }
        }
    }
})
