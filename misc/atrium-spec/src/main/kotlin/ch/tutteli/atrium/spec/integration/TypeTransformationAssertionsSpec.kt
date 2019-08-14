@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class TypeTransformationAssertionsSpec(
    verbs: AssertionVerbFactory,
    notToBeNullPair: Pair<String, AssertionPlantNullable<Int?>.(assertionCreator: Assert<Int>.() -> Unit) -> Unit>,
    notToBeNullLessFun: AssertionPlantNullable<Int?>.(Int) -> Unit,
    notToBeNullGreaterAndLessFun: AssertionPlantNullable<Int?>.(Int, Int) -> Unit,
    notToBeButPair:  Pair<String, AssertionPlantNullable<Int?>.(Int) -> Unit>,
    isA: String,
    isAIntFun: Assert<String>.(assertionCreator: Assert<Int>.() -> Unit) -> Unit,
    isAStringFun: Assert<String>.(assertionCreator: Assert<String>.() -> Unit) -> Unit,
    isACharSequenceFun: Assert<String>.(assertionCreator: Assert<CharSequence>.() -> Unit) -> Unit,
    isASubTypeFun: Assert<SuperType>.(assertionCreator: Assert<SubType>.() -> Unit) -> Unit,
    isAIntLessFun: Assert<Number>.(Int) -> Unit,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<SuperType>(verbs, describePrefix,
        checkingTriple(isA, { isASubTypeFun(this, {}) }, SubType(), SuperType())
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val (notToBeNull, notToBeNullFun) = notToBeNullPair
    val (notToBeBut, notToBeNullButFun) = notToBeButPair

    describeFun(notToBeNull) {

        val assert: (Int?) -> AssertionPlantNullable<Int?> = verbs::checkNullable

        context("subject is null") {
            it("throws an AssertionError") {
                expect {
                    val i: Int? = null
                    assert(i).notToBeNullFun {}
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    messageContains(
                        DescriptionTypeTransformationAssertion.IS_A.getDefault(),
                        Integer::class.java.name
                    )
                }
            }

            context("it still allows to define assertion on the subject even if it is null") {
                it("throws an AssertionError and contains the additional assertion as explanation") {
                    expect {
                        val i: Int? = null
                        assert(i).notToBeNullLessFun(2)
                    }.toThrow<AssertionError> {
                        @Suppress("DEPRECATION")
                        messageContains(
                            DescriptionTypeTransformationAssertion.IS_A.getDefault(),
                            Integer::class.java.name,
                            DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
                        )
                    }
                }
            }
        }

        context("subject is not null") {

            it("does not throw") {
                val i: Int? = 1
                assert(i).notToBeNullFun {}
            }

            context("it allows to define an assertion for the subject") {
                it("does not throw if the assertion holds") {
                    val i: Int? = 1
                    assert(i).notToBeNullLessFun(2)
                }

                it("throws an AssertionError if the assertion does not hold") {
                    expect {
                        val i: Int? = 1
                        assert(i).notToBeNullLessFun(0)
                    }.toThrow<AssertionError>{}
                }
            }
            context("it allows to define multiple assertions for the subject") {
                it("does not throw if the assertions hold") {
                    val i: Int? = 1
                    assert(i).notToBeNullGreaterAndLessFun(0, 2)
                }

                it("throws an AssertionError if one assertion does not hold") {
                    expect {
                        val i: Int? = 1
                        assert(i).notToBeNullGreaterAndLessFun(2, 5)
                    }.toThrow<AssertionError> {
                        message {
                            contains(DescriptionComparableAssertion.IS_GREATER_THAN.getDefault())
                            containsNot(DescriptionComparableAssertion.IS_LESS_THAN.getDefault())
                        }
                    }
                }

                it("throws an AssertionError if both assertions do not hold and contains both messages") {
                    expect {
                        val i: Int? = 1
                        assert(i).notToBeNullGreaterAndLessFun(2, 0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                DescriptionComparableAssertion.IS_GREATER_THAN.getDefault(),
                                DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
                            )
                        }
                    }
                }
            }
        }

        context("in a feature assertion and subject is null") {
            it("throws an AssertionError") {
                class A(val i: Int? = null)
                expect {
                    verbs.checkLazily(A()) { property(A::i).notToBeNull {} }
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    messageContains(
                        A::class.simpleName!!,
                        DescriptionTypeTransformationAssertion.IS_A.getDefault(),
                        Integer::class.java.name
                    )
                }
            }

            it("throws an AssertionError which contains subsequent assertions") {
                class A(val i: Int? = null)
                expect {
                    verbs.checkLazily(A()) { property(A::i).notToBeNullLessFun(1) }
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    messageContains(
                        A::class.simpleName!!,
                        DescriptionTypeTransformationAssertion.IS_A.getDefault(),
                        DescriptionComparableAssertion.IS_LESS_THAN.getDefault(),
                        Integer::class.java.name
                    )
                }
            }
        }
    }

    describeFun(isA) {

        val assert: (String) -> Assert<String> = verbs::checkImmediately

        context("subject is not in type hierarchy") {
            it("throws an AssertionError") {
                expect {
                    assert("hello").isAIntFun {}
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    messageContains(
                        DescriptionTypeTransformationAssertion.IS_A.getDefault(),
                        Integer::class.java.name
                    )
                }
            }
        }
        context("subject is the same type") {
            it("does not throw an AssertionError") {
                assert("hello").isAStringFun {}
            }

            group("it allows to perform an assertion specific for the subtype...") {

                test("... which holds -- does not throw") {
                    verbs.checkImmediately(1).isAIntLessFun(2)
                }

                val expectedLessThan = 2
                val actualValue = 5
                test("... which fails -- throws an AssertionError") {
                    expect {
                        verbs.checkImmediately(actualValue).isAIntLessFun(expectedLessThan)
                    }.toThrow<AssertionError> {
                        messageContains(actualValue, DescriptionComparableAssertion.IS_LESS_THAN.getDefault(), expectedLessThan)
                    }
                }
            }
        }

        context("subject is a subtype") {
            it("does not throw an AssertionError if the subject is a subtype") {
                assert("hello").isACharSequenceFun {}
            }

            group("it allows to perform an assertion specific for the subtype...") {

                test("... which holds -- does not throw") {
                    verbs.checkImmediately(1).isAIntLessFun(2)
                }

                val expectedLessThan = 2
                val actualValue = 5
                test("... which fails -- throws an AssertionError") {
                    expect {
                        verbs.checkImmediately(actualValue).isAIntLessFun(expectedLessThan)
                    }.toThrow<AssertionError> {
                        messageContains(actualValue, DescriptionComparableAssertion.IS_LESS_THAN.getDefault(), expectedLessThan)
                    }
                }
            }
        }

        context("subject is a supertype") {
            it("throws an AssertionError") {
                expect {
                    verbs.checkImmediately(SuperType()).isASubTypeFun {}
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    messageContains(
                        SuperType::class.java.name,
                        DescriptionTypeTransformationAssertion.IS_A.getDefault(),
                        SubType::class.java.name
                    )
                }
            }
        }
    }

    describeFun(notToBeBut){
        val assert: (Int?) -> AssertionPlantNullable<Int?> = verbs::checkNullable

        context("subject is null") {
            it("throws an AssertionError") {
                expect {
                    val i: Int? = null
                    assert(i).notToBeNullButFun(1)
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    messageContains(
                        DescriptionTypeTransformationAssertion.IS_A.getDefault(),
                        Integer::class.java.name,
                        "${DescriptionAnyAssertion.TO_BE.getDefault()}: 1"
                    )
                }
            }
        }

        context("subject is 2") {
            val i: Int? = 2
            test("2 does not throw") {
                assert(i).notToBeNullButFun(2)
            }

            test("3 throws an AssertionError") {
                expect {
                    assert(i).notToBeNullButFun(3)
                }.toThrow<AssertionError> {
                    messageContains(
                        "${DescriptionAnyAssertion.TO_BE.getDefault()}: 3"
                    )
                }
            }
        }
    }
}) {
    open class SuperType
    class SubType : SuperType()
}
