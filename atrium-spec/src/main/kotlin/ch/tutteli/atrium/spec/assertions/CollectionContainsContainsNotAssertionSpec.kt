package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionCollectionAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class CollectionContainsContainsNotAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsPair: Pair<String, IAssertionPlant<Collection<Double>>.(Double, Array<out Double>) -> IAssertionPlant<Collection<Double>>>,
    containsNotPair: Pair<String, IAssertionPlant<Collection<Double>>.(Double, Array<out Double>) -> IAssertionPlant<Collection<Double>>>
) : Spek({

    val assert: (Collection<Double>) -> IAssertionPlant<Collection<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val collection = listOf(1.2, 5.0, 2.5, 4.1, 3.8)
    val fluent = assert(collection)

    val (contains, containsFunArr) = containsPair
    fun IAssertionPlant<Collection<Double>>.containsFun(t: Double, vararg tX: Double)
        = containsFunArr(t, tX.toTypedArray())

    val (containsNot, containsNotFunArr) = containsNotPair
    fun IAssertionPlant<Collection<Double>>.containsNotFun(t: Double, vararg tX: Double)
        = containsNotFunArr(t, tX.toTypedArray())

    describe("fun $contains and $containsNot") {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$contains 1.2 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.2)
                }.toThrow<AssertionError>()
                //TODO check the following as soon as contains is implemented with extra information
//                    .and.message.contains(
//                    DescriptionCollectionAssertion.NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
//                    DescriptionCollectionAssertion.AT_LEAST.getDefault() + ": 1"
//                )
            }
            test("$containsNot 1.2 does not throw") {
                fluentEmptyString.containsNotFun(1.2)
            }
        }

        context("text '$collection'") {

            context("search for 1.2 and 4.1") {
                test("$contains 1.2 does not throw") {
                    fluent.containsFun(1.2)
                }
                test("$containsNot 1.2 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.2)
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionCollectionAssertion.CONTAINS_NOT)
                }

                test("$contains 1.2 and 4.1 does not throw") {
                    fluent.containsFun(1.2, 4.1)
                }
                test("$containsNot 1.2 and 4.1 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.2, 4.1)
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(DescriptionCollectionAssertion.CONTAINS_NOT)
                }
            }

            context("search for 9.5 and 7.0") {
                test("$contains 9.5 and 7.0 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5, 7.0)
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(DescriptionCollectionAssertion.CONTAINS)
                }
                test("$containsNot 9.5 and 7.0 does not throw") {
                    fluent.containsNotFun(9.5, 7.0)
                }
            }

            context("search for 1.2 and 9.5") {
                test("$contains 9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5)
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(DescriptionCollectionAssertion.CONTAINS)
                }
                test("$containsNot 9.5 does not throw") {
                    fluent.containsNotFun(9.5)
                }

                test("$contains 1.2 and 9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(1.2, 9.5)
                    }.toThrow<AssertionError>().message.contains(DescriptionCollectionAssertion.CONTAINS.getDefault(), 9.5)
                }
                test("$containsNot 1.2 and 9.5 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.2, 9.5)
                    }.toThrow<AssertionError>().message.contains(DescriptionCollectionAssertion.CONTAINS_NOT.getDefault(), 1.2)
                }
            }
        }
    }
})
