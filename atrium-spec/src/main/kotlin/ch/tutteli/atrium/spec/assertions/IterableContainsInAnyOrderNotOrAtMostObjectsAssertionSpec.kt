package ch.tutteli.atrium.spec.assertions


import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.AT_MOST
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderNotOrAtMostObjectsAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsNotOrAtMostTriple: Triple<String, (String, String) -> String, IAssertionPlant<Iterable<Double>>.(Int, Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double>>(
        containsNotOrAtMostTriple.first to mapToCreateAssertion { containsNotOrAtMostTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Iterable<Double>>(verbs,
        checkingTriple(containsNotOrAtMostTriple.first, { containsNotOrAtMostTriple.third(this, 2, 2.3, arrayOf()) }, listOf<Double>() as Iterable<Double>, listOf(2.3, 2.3, 2.3))
    ) {})

    fun describeFun(description: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, description, body)

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsNotOrAtMost, containsNotOrAtMostTest, containsNotOrAtMostFunArr) = containsNotOrAtMostTriple
    fun IAssertionPlant<Iterable<Double>>.containsNotOrAtMostFun(atLeast: Int, a: Double, vararg aX: Double)
        = containsNotOrAtMostFunArr(atLeast, a, aX.toTypedArray())
    val (containsNot, errorMsgContainsNot) = containsNotPair

    describeFun(containsNotOrAtMost) {

        context("throws an $illegalArgumentException") {
            test("for not at all or at most -1 -- only positive numbers") {
                expect {
                    fluent.containsNotOrAtMostFun(-1, 0.0)
                }.toThrow<IllegalArgumentException> { message { contains("positive number", -1) } }
            }
            test("for not at all or at most 0 -- points to $containsNot") {
                expect {
                    fluent.containsNotOrAtMostFun(0, 0.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
        }

        context("text $oneToSeven") {
            group("happy case with $containsNotOrAtMost once") {
                test("${containsNotOrAtMostTest("1.0", "once")} does not throw") {
                    fluent.containsNotOrAtMostFun(1, 1.0)
                }
                test("${containsNotOrAtMostTest("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    fluent.containsNotOrAtMostFun(1, 1.0, 2.0, 3.0)
                }
                test("${containsNotOrAtMostTest("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    fluent.containsNotOrAtMostFun(1, 3.0, 1.0, 2.0)
                }
                test("${containsNotOrAtMostTest("21.1 and 34.0 and 11.23", "twice")}  does not throw") {
                    fluent.containsNotOrAtMostFun(2, 21.1, 34.0, 11.23)
                }
            }

            group("failing assertions; search string at different positions") {
                test("${containsNotOrAtMostTest("4.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 4.0)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_MOST) } }
                }
                test("${containsNotOrAtMostTest("1.0, 4.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 1.0, 4.0)
                    }.toThrow<AssertionError> { message { contains(atMost, 4.0) } }
                }
                test("${containsNotOrAtMostTest("4.0, 1.0", "once")} once throws AssertionError") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 4.0, 1.0)
                    }.toThrow<AssertionError> { message { contains(atMost, 4.0) } }
                }
                test("${containsNotOrAtMostTest("5.0, 3.1, 3.0, 4.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 5.0, 3.1, 3.0, 4.0)
                    }.toThrow<AssertionError> { message { contains(atMost, 5.0, 4.0) } }
                }
            }

            group("multiple occurrences of the search string") {
                test("${containsNotOrAtMostTest("5.0", "once")} throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrder: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atMost: 1")
                        }
                    }
                }

                test("${containsNotOrAtMostTest("5.0", "twice")} does not throw") {
                    fluent.containsNotOrAtMostFun(2, 5.0)
                }


                test("${containsNotOrAtMostTest("5.0", "3 times")} does not throw") {
                    fluent.containsNotOrAtMostFun(3, 5.0)
                }
                test("${containsNotOrAtMostTest("5.0 and 4.0", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 4.0 (3)") {
                    expect {
                        fluent.containsNotOrAtMostFun(2, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrder: 4.0",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$atMost: 2")
                            containsNot("$containsInAnyOrder: 5.0")
                        }
                    }
                }
                test("${containsNotOrAtMostTest("4.0", "3 times")} does not throw") {
                    fluent.containsNotOrAtMostFun(3, 4.0)
                }
                test("${containsNotOrAtMostTest("5.0 and 4.0", "3 times")} does not throw") {
                    fluent.containsNotOrAtMostFun(3, 5.0, 4.0)
                }

            }
        }
    }
})
