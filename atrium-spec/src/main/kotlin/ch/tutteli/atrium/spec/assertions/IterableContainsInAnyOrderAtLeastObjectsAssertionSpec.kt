package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.AT_LEAST
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderAtLeastObjectsAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsAtLeastTriple: Triple<String, (String, String) -> String, IAssertionPlant<Iterable<Double>>.(Int, Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>,
    //TODO use as soon as containsAtLeastButAtMost exists
    //containsAtLeastButAtMostPair: Triple<String, (String, String, String) -> String, IAssertionPlant<Iterable<Double>>.(Int, Int, Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>,
    containsNotPair: Pair<String, (Int) -> String>
    //TODO use as soon as exactly exists
    //exactlyPair: Pair<String, (Int) -> String>,
    //errorMsgAtLeastButAtMost: (Int, Int) -> String
) : IterableContainsSpecBase({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double>>(
        containsAtLeastTriple.first to mapToCreateAssertion { containsAtLeastTriple.third(this, 1, 2.3, arrayOf()) }
        //TODO use as soon as containsAtLeastButAtMost exists
//        containsAtLeastButAtMostTriple.first to mapToCreateAssertion { containsAtLeastButAtMostTriple.third(this, 1, 2, 2.3, arrayOf()) },
    ) {})

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsAtLeast, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun IAssertionPlant<Iterable<Double>>.containsAtLeastFun(atLeast: Int, a: Double, vararg aX: Double)
        = containsAtLeastFunArr(atLeast, a, aX.toTypedArray())

    val (containsNot, errorMsgContainsNot) = containsNotPair
    //val (exactly, errorMsgExactly) = exactlyPair

    //TODO use as soon as containsAtLeastButAtMost exists
    //describe("fun $containsAtLeast (and sometimes $containsAtLeastButAtMost)") {
    describe("fun $containsAtLeast") {
        context("throws an $illegalArgumentException") {
            test("for at least -1 -- only positive numbers") {
                expect {
                    fluent.containsAtLeastFun(-1, 9.0)
                }.toThrow<IllegalArgumentException> { message { contains("positive number", -1) } }
            }
            test("for at least 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtLeastFun(0, 9.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            //TODO use as soon as containsAtLeastButAtMost exists
//            group("using $containsAtLeastButAtMost") {
//                test("for at least 1 but at most -1 -- since -1 is smaller than 1") {
//                    expect {
//                        fluent.containsAtLeastButAtMostFun(1, -1, 9.0)
//                    }.toThrow<IllegalArgumentException>{ message { toBe(errorMsgAtLeastButAtMost(1, -1) } })
//                }
//                test("for at least 1 but at most 0 -- since 0 is smaller than 1") {
//                    expect {
//                        fluent.containsAtLeastButAtMostFun(1, 0, 9.0)
//                    }.toThrow<IllegalArgumentException>{ message { toBe(errorMsgAtLeastButAtMost(1, 0) } })
//                }
//                test("for at least 2 but at most 1 -- since 1 is smaller than 2") {
//                    expect {
//                        fluent.containsAtLeastButAtMostFun(2, 1, 9.0)
//                    }.toThrow<IllegalArgumentException>{ message { toBe(errorMsgAtLeastButAtMost(2, 1) } })
//                }
////                //TODO use as soon as exactly exists
////                test("for at least 1 but at most 1 -- points to $exactly") {
////                    expect {
////                        fluent.containsAtLeastButAtMostFun(1, 1, 9.0)
////                    }.toThrow<IllegalArgumentException>{ message { toBe(errorMsgExactly(1) } })
////                }
//            }
        }

        context("text $oneToSeven") {

            group("happy case with $containsAtLeast once") {
                test("${containsAtLeastTest("1.0", "once")} does not throw") {
                    fluent.containsAtLeastFun(1, 1.0)
                }
                test("${containsAtLeastTest("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    fluent.containsAtLeastFun(1, 1.0, 2.0, 3.0)
                }
                test("${containsAtLeastTest("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    fluent.containsAtLeastFun(1, 3.0, 1.0, 2.0)
                }
            }

            group("failing assertions; search wrong number at different positions with $containsAtLeast once") {
                test("${containsAtLeastTest("1.1", "once")} throws AssertionError") {
                    expect {
                        fluent.containsAtLeastFun(1, 1.1)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_LEAST) } }
                }
                test("${containsAtLeastTest("1.0, 2.3", "once")} throws AssertionError") {
                    expect {
                        fluent.containsAtLeastFun(1, 1.0, 2.3)
                    }.toThrow<AssertionError> { message { contains(atLeast, 2.3) } }
                }
                test("${containsAtLeastTest("2.3, 1.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsAtLeastFun(1, 2.3, 1.0)
                    }.toThrow<AssertionError> { message { contains(atLeast, 2.3) } }
                }
                test("${containsAtLeastTest("1.0, 2.3, 3.1 and 6.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsAtLeastFun(1, 1.0, 2.3, 3.1, 6.0)
                    }.toThrow<AssertionError> { message { contains(atLeast, 2.3, 3.1) } }
                }
            }

            group("multiple occurrences of the search string") {
                test("${containsAtLeastTest("5.0", "once")} does not throw") {
                    fluent.containsAtLeastFun(1, 5.0)
                }
                test("${containsAtLeastTest("5.0", "twice")} does not throw") {
                    fluent.containsAtLeastFun(2, 5.0)
                }

                test("${containsAtLeastTest("5.0", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsAtLeastFun(3, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrder: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atLeast: 3")
                        }
                    }
                }

                test("${containsAtLeastTest("5.0 and 4.0", "twice")} does not throw") {
                    fluent.containsAtLeastFun(2, 5.0, 4.0)
                }
                test("${containsAtLeastTest("4.0", "3 times")} does not throw") {
                    fluent.containsAtLeastFun(3, 4.0)
                }

                test("${containsAtLeastTest("5.0 and 4.0", "3 times")} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsAtLeastFun(3, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$containsInAnyOrder: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atLeast: 3")
                            containsNot("$containsInAnyOrder: 4.0")
                        }
                    }
                }
            }

            //TODO as soon as containsAtLeastButAtMost is implemented
//            group("using $containsAtLeastButAtMost") {
//                test("${containsAtLeastButAtMostTest("5.0", "once", "twice")} does not throw") {
//                    fluent.contains.atLeast(1).butAtMost(2).value(5.0)
//                }
//                test("${containsAtLeastButAtMostTest("5.0 and 4.0", "once", "twice")} throws AssertionError and message contains both, at most: 2 and how many times it actually contained 4.0 (3)") {
//                    expect {
//                        fluent.contains.atLeast(1).butAtMost(2).values(5.0, 4.0)
//                    }.toThrow<AssertionError>().and.message {
//                        contains(
//                            "$containsInAnyOrder: 4.0",
//                            "$numberOfOccurrences: 3$separator"
//                        )
//                        endsWith(AT_MOST.getDefault() + ": 2")
//                        containsNot("$containsInAnyOrder: 5.0")
//                        containsNotDefaultTranslationOf(AT_LEAST)
//                    }
//                }
//                test("${containsAtLeastButAtMostTest("5.0 and 4.0", "twice", "3 times")} does not throw") {
//                    fluent.contains.atLeast(2).butAtMost(3).values(5.0, 4.0)
//                }
//
//                test("${containsAtLeastButAtMostTest("5.0 and 4.0", "3 times", "4 times")} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 5.0 (2)") {
//                    expect {
//                        fluent.contains.atLeast(3).butAtMost(4).values(5.0, 4.0)
//                    }.toThrow<AssertionError>().and.message {
//                        contains(
//                            "$containsInAnyOrder: 5.0",
//                            "$numberOfOccurrences: 2$separator"
//                        )
//                        endsWith("$atLeast: 3")
//                        containsNot("$containsInAnyOrder: 4.0")
//                        containsNotDefaultTranslationOf(AT_MOST)
//                    }
//                }
//            }
        }
    }
})
