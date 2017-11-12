package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.checkNarrowingAssertion
import ch.tutteli.atrium.spec.checkNarrowingNullableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class NarrowingAssertionsSpec(
    verbs: IAssertionVerbFactory,
    isNotNullPair: Pair<String, IAssertionPlantNullable<Int?>.(createAssertions: IAssertionPlant<Int>.() -> Unit) -> Unit>,
    isNotNullLessFun: IAssertionPlantNullable<Int?>.(Int) -> Unit,
    nameIsA: String,
    isAIntFun: IAssertionPlant<String>.(createAssertions: IAssertionPlant<Int>.() -> Unit) -> Unit,
    isAStringFun: IAssertionPlant<String>.(createAssertions: IAssertionPlant<String>.() -> Unit) -> Unit,
    isACharSequenceFun: IAssertionPlant<String>.(createAssertions: IAssertionPlant<CharSequence>.() -> Unit) -> Unit,
    isASubTypeFun: IAssertionPlant<SuperType>.(createAssertions: IAssertionPlant<SubType>.() -> Unit) -> Unit,
    isAIntLessFun: IAssertionPlant<Number>.(Int) -> Unit
) : Spek({

    val expect = verbs::checkException

    val (nameIsNotNull, isNotNullFun) = isNotNullPair

    describe("fun $nameIsNotNull") {

        val assert: (Int?) -> IAssertionPlantNullable<Int?> = verbs::checkNullable

        context("subject is null") {
            checkNarrowingNullableAssertion<Int?>("it throws an AssertionError", { isNotNull ->
                expect {
                    val i: Int? = null
                    assert(i).isNotNull()
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                        contains(Integer::class.java.name)
                    }
                }

            }, { isNotNullFun {} })

            context("it still allows to define assertion on the subject even if it is null") {
                checkNarrowingNullableAssertion<Int?>("it throws an AssertionError and contains the additional assertion as explanation", { isNotNullWithCheck ->
                    expect {
                        val i: Int? = null
                        assert(i).isNotNullWithCheck()
                    }.toThrow<AssertionError> {
                        message {
                            containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                            contains(Integer::class.java.name)
                            //TODO fix IDownCastFailureHandler
                            //containsDefaultTranslationOf(DescriptionNumberAssertion.IS_LESS_THAN)
                        }
                    }
                }, { isNotNullLessFun(2) })
            }
        }

        context("subject is not null") {

            checkNarrowingNullableAssertion<Int?>("it does not throw", { isNotNull ->
                val i: Int? = 1
                assert(i).isNotNull()
            }, { isNotNullFun {} })

            context("it allows to define an assertion on the subject") {
                checkNarrowingNullableAssertion<Int?>("it throws an AssertionError if the assertion does not hold", { isNotNullWithCheck ->
                    expect {
                        val i: Int? = 1
                        assert(i).isNotNullWithCheck()
                    }.toThrow<AssertionError>()
                }, { isNotNullLessFun(0) })

                checkNarrowingNullableAssertion<Int?>("it does not throw an Exception if assertion holds", { isNotNullWithCheck ->
                    val i: Int? = 1
                    assert(i).isNotNullWithCheck()
                }, { isNotNullLessFun(2) })
            }
        }

        context("in a feature assertion and subject is null") {
            checkNarrowingNullableAssertion<Int?>("it throws an AssertionError", { isNotNull ->
                class A(val i: Int? = null)
                expect {
                    verbs.checkLazily(A()) { its(subject::i).isNotNull() }
                }.toThrow<AssertionError> {
                    message {
                        contains(A::class.simpleName!!)
                        containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                        contains(Integer::class.java.name)
                    }
                }
            }, { isNotNull {} })

            //TODO change how DownCaster behaves, it should pass the assertion to the failureHandler such that the handler
            //can wrap all assertions in one group
//            checkNarrowingNullableAssertion<Int?>("it throws an AssertionError which contains subsequent assertions", { isNotNull ->
//                class A(val i: Int? = null)
//                expect {
//                    verbs.checkLazily(A()) { its(subject::i).isNotNull() }
//                }.toThrow<AssertionError> {
//                    message {
//                        contains(A::class.simpleName!!)
//                        containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A, DescriptionNumberAssertion.IS_LESS_THAN)
//                        contains(Integer::class.java.name)
//                    }
//                }
//            }, { isNotNullLessFun(1) })
        }
    }

    describe("fun $nameIsA") {

        val assert: (String) -> IAssertionPlant<String> = verbs::checkImmediately

        context("subject is not in type hierarchy") {
            checkNarrowingAssertion<String>("it throws an AssertionError", { isA ->
                expect {
                    assert("hello").isA()
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                        contains(Integer::class.java.name)
                    }
                }
            }, { isAIntFun {} })
        }
        context("subject is the same type") {
            checkNarrowingAssertion<String>("it does not throw an AssertionError", { isA ->
                assert("hello").isA()
            }, { isAStringFun {} })

            group("it allows to perform an assertion specific for the subtype...") {

                checkNarrowingAssertion<Int>("... which holds -- does not throw", { isAWithAssertion ->
                    verbs.checkImmediately(1).isAWithAssertion()
                }, { isAIntLessFun(2) })

                val expectedLessThan = 2
                val actualValue = 5
                checkNarrowingAssertion<Int>("... which fails -- throws an AssertionError", { isAWithAssertion ->
                    expect {
                        verbs.checkImmediately(actualValue).isAWithAssertion()
                    }.toThrow<AssertionError> {
                        message { contains(actualValue, DescriptionNumberAssertion.IS_LESS_THAN.getDefault(), expectedLessThan) }
                    }
                }, { isAIntLessFun(expectedLessThan) })
            }
        }

        context("subject is a subtype") {
            checkNarrowingAssertion<String>("it does not throw an AssertionError if the subject is a subtype", { isA ->
                assert("hello").isA()
            }, { isACharSequenceFun {} })

            group("it allows to perform an assertion specific for the subtype...") {

                checkNarrowingAssertion<Number>("... which holds -- does not throw", { isAWithAssertion ->
                    verbs.checkImmediately(1).isAWithAssertion()
                }, { isAIntLessFun(2) })

                val expectedLessThan = 2
                val actualValue = 5
                checkNarrowingAssertion<Number>("... which fails -- throws an AssertionError", { isAWithAssertion ->
                    expect {
                        verbs.checkImmediately(actualValue).isAWithAssertion()
                    }.toThrow<AssertionError> {
                        message { contains(actualValue, DescriptionNumberAssertion.IS_LESS_THAN.getDefault(), expectedLessThan) }
                    }
                }, { isAIntLessFun(expectedLessThan) })
            }
        }

        context("subject is a supertype") {
            checkNarrowingAssertion<SuperType>("it throws an AssertionError", { isA ->
                expect {
                    verbs.checkImmediately(SuperType()).isA()
                }.toThrow<AssertionError> {
                    message { contains(SuperType::class.java.name, "is type or sub-type of", SubType::class.java.name) }
                }
            }, { isASubTypeFun {} })
        }
    }

}) {

    open class SuperType
    class SubType : SuperType()
}
