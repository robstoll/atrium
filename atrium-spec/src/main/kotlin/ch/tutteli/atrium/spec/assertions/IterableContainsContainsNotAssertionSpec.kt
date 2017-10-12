package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class IterableContainsContainsNotAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsPair: Pair<String, IAssertionPlant<Iterable<Double>>.(Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>,
    containsNotPair: Pair<String, IAssertionPlant<Iterable<Double>>.(Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>
) : Spek({

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val iterable = listOf(1.2, 5.0, 2.5, 4.1, 3.8)
    val fluent = assert(iterable)

    val (contains, containsFunArr) = containsPair
    fun IAssertionPlant<Iterable<Double>>.containsFun(t: Double, vararg tX: Double)
        = containsFunArr(t, tX.toTypedArray())

    val (containsNot, containsNotFunArr) = containsNotPair
    fun IAssertionPlant<Iterable<Double>>.containsNotFun(t: Double, vararg tX: Double)
        = containsNotFunArr(t, tX.toTypedArray())

    describe("fun $contains and $containsNot") {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$contains 1.2 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.2)
                }.toThrow<AssertionError>().and.message.contains(
                    DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                    DescriptionIterableAssertion.AT_LEAST.getDefault() + ": 1"
                )
            }
            test("$containsNot 1.2 does not throw") {
                fluentEmptyString.containsNotFun(1.2)
            }
        }

        context("iterable '$iterable'") {

            context("search for 1.2 and 4.1") {
                test("$contains 1.2 does not throw") {
                    fluent.containsFun(1.2)
                }
                test("$containsNot 1.2 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.2)
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionIterableAssertion.CONTAINS_NOT)
                }

                test("$contains 1.2 and 4.1 does not throw") {
                    fluent.containsFun(1.2, 4.1)
                }
                test("$containsNot 1.2 and 4.1 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.2, 4.1)
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(DescriptionIterableAssertion.CONTAINS_NOT)
                }
            }

            context("search for 9.5 and 7.0") {
                test("$contains 9.5 and 7.0 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5, 7.0)
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(DescriptionIterableAssertion.CONTAINS)
                }
                test("$containsNot 9.5 and 7.0 does not throw") {
                    fluent.containsNotFun(9.5, 7.0)
                }
            }

            context("search for 1.2 and 9.5") {
                test("$contains 9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5)
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(DescriptionIterableAssertion.CONTAINS)
                }
                test("$containsNot 9.5 does not throw") {
                    fluent.containsNotFun(9.5)
                }

                test("$contains 1.2 and 9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(1.2, 9.5)
                    }.toThrow<AssertionError>().message.contains(DescriptionIterableAssertion.CONTAINS.getDefault(), 9.5)
                }
                test("$containsNot 1.2 and 9.5 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.2, 9.5)
                    }.toThrow<AssertionError>().message.contains(DescriptionIterableAssertion.CONTAINS_NOT.getDefault(), 1.2)
                }
            }
        }
    }
})
