package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.CONTAINS_NOT
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class IterableContainsContainsNotAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsPair: Pair<String, IAssertionPlant<Iterable<Double>>.(Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>,
    containsNotPair: Pair<String, IAssertionPlant<Iterable<Double>>.(Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>
) : IterableContainsSpecBase({

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (contains, containsFunArr) = containsPair
    fun IAssertionPlant<Iterable<Double>>.containsFun(t: Double, vararg tX: Double)
        = containsFunArr(t, tX.toTypedArray())

    val (containsNot, containsNotFunArr) = containsNotPair
    fun IAssertionPlant<Iterable<Double>>.containsNotFun(t: Double, vararg tX: Double)
        = containsNotFunArr(t, tX.toTypedArray())


    describe("fun $contains and $containsNot") {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$contains 1.0 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.0)
                }.toThrow<AssertionError>().and.message.contains(
                    "$containsInAnyOrder: 1.0",
                    "$numberOfOccurrences: 0",
                    "$atLeast: 1"
                )
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
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(CONTAINS_NOT)
                }

                test("$contains 1.0 and 4.0 does not throw") {
                    fluent.containsFun(1.0, 4.0)
                }
                test("$containsNot 1.0 and 4.0 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.0, 4.0)
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(CONTAINS_NOT)
                }
            }

            context("search for 9.5 and 7.1") {
                test("$contains 9.5 and 7.1 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5, 7.1)
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(CONTAINS)
                }
                test("$containsNot 9.5 and 7.1 does not throw") {
                    fluent.containsNotFun(9.5, 7.1)
                }
            }

            context("search for 1.0 and 9.5") {
                test("$contains 9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5)
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(CONTAINS)
                }
                test("$containsNot 9.5 does not throw") {
                    fluent.containsNotFun(9.5)
                }

                test("$contains 1.0 and 9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(1.0, 9.5)
                    }.toThrow<AssertionError>().message.contains(CONTAINS.getDefault(), 9.5)
                }
                test("$containsNot 1.0 and 9.5 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.0, 9.5)
                    }.toThrow<AssertionError>().message.contains(CONTAINS_NOT.getDefault(), 1.0)
                }
            }
        }
    }
})
