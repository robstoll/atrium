package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.checkNarrowingAssertion
import ch.tutteli.atrium.spec.checkNarrowingNullableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class NarrowingAssertionsSpec(
    verbs: IAssertionVerbFactory,
    isNotNullTriple: Triple<
        String,
        IAssertionPlantNullable<Int?>.() -> IAssertionPlant<Int>,
        IAssertionPlantNullable<Int?>.(createAssertions: IAssertionPlant<Int>.() -> Unit) -> IAssertionPlant<Int>
        >,
    isNotNullLessPair: Pair<
        IAssertionPlantNullable<Int?>.(Int) -> IAssertionPlant<Int>,
        IAssertionPlantNullable<Int?>.(Int) -> IAssertionPlant<Int>
        >,
    nameIsA: String,
    isAIntPair: Pair<
        IAssertionPlant<String>.() -> IAssertionPlant<Int>,
        IAssertionPlant<String>.(createAssertions: IAssertionPlant<Int>.() -> Unit) -> IAssertionPlant<Int>
        >,
    isAStringPair: Pair<
        IAssertionPlant<String>.() -> IAssertionPlant<String>,
        IAssertionPlant<String>.(createAssertions: IAssertionPlant<String>.() -> Unit) -> IAssertionPlant<String>
        >,
    isACharSequencePair: Pair<
        IAssertionPlant<String>.() -> IAssertionPlant<CharSequence>,
        IAssertionPlant<String>.(createAssertions: IAssertionPlant<CharSequence>.() -> Unit) -> IAssertionPlant<CharSequence>
        >,
    isASubTypePair: Pair<
        IAssertionPlant<SuperType>.() -> IAssertionPlant<SubType>,
        IAssertionPlant<SuperType>.(createAssertions: IAssertionPlant<SubType>.() -> Unit) -> IAssertionPlant<SubType>
        >,
    isAIntLessPair: Pair<IAssertionPlant<Number>.(Int) -> IAssertionPlant<Int>, IAssertionPlant<Number>.(Int) -> IAssertionPlant<Int>>
) : Spek({

    val expect = verbs::checkException

    val (nameIsNotNull, isNotNullFun, isNotNullLazyFun) = isNotNullTriple
    val (isNotNullLessFun, isNotNullLessLazyFun) = isNotNullLessPair

    val (isAIntFun, isAIntLazyFun) = isAIntPair
    val (isAStringFun, isAStringLazyFun) = isAStringPair
    val (isACharSequenceFun, isACharSequenceLazyFun) = isACharSequencePair
    val (isASubTypeFun, isASubTypeLazyFun) = isASubTypePair
    val (isAIntLessFun, isAIntLessLazyFun) = isAIntLessPair

    describe("fun $nameIsNotNull") {

        val assert: (Int?) -> IAssertionPlantNullable<Int?> = verbs::checkNullable

        checkNarrowingNullableAssertion<Int?>("it throws an AssertionError if the subject is null", { isNotNull ->
            expect {
                val i: Int? = null
                assert(i).isNotNull()
            }.toThrow<AssertionError>().and.message {
                containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                contains(Integer::class.java.name)
            }

        }, { isNotNullFun() }, { isNotNullLazyFun {} })

        checkNarrowingNullableAssertion<Int?>("it does not throw an Exception if the subject is not null", { isNotNull ->
            val i: Int? = 1
            assert(i).isNotNull()
        }, { isNotNullFun() }, { isNotNullLazyFun {} })

        context("it allows to define an assertion on the subject in case it is not null") {
            checkNarrowingNullableAssertion<Int?>("it throws an AssertionError if the assertion does not hold", { isNotNullWithCheck ->
                expect {
                    val i: Int? = 1
                    assert(i).isNotNullWithCheck()
                }.toThrow<AssertionError>()
            }, { isNotNullLessFun(0) }, { isNotNullLessLazyFun(0) })

            checkNarrowingNullableAssertion<Int?>("it does not throw an Exception if assertion holds", { isNotNullWithCheck ->
                val i: Int? = 1
                assert(i).isNotNullWithCheck()
            }, { isNotNullLessFun(2) }, { isNotNullLessLazyFun(2) })
        }

        context("it allows to define an assertion on the subject even if it is null") {
            checkNarrowingNullableAssertion<Int?>("it throws an AssertionError", { isNotNullWithCheck ->
                expect {
                    val i: Int? = null
                    assert(i).isNotNullWithCheck()
                }.toThrow<AssertionError>().and.message {
                    containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                    contains(Integer::class.java.name)
                }
            }, { isNotNullLessFun(2) }, { isNotNullLessLazyFun(2) })
        }

        context("in a feature assertion") {
            checkNarrowingNullableAssertion<Int?>("it throws an AssertionError if the subject is null", { isNotNull ->
                class A(val i: Int? = null)
                expect {
                    verbs.checkLazily(A()) { its(subject::i).isNotNull() }
                }.toThrow<AssertionError>().and.message {
                    contains(A::class.simpleName!!)
                    containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                    contains(Integer::class.java.name)
                }
            }, { isNotNullFun() }, { isNotNullLazyFun {} })
        }
    }

    describe("fun $nameIsA") {

        val assert: (String) -> IAssertionPlant<String> = verbs::checkImmediately

        checkNarrowingAssertion<String>("it throws an AssertionError if the subject is not of the given type", { isA ->
            expect {
                assert("hello").isA()
            }.toThrow<AssertionError>().and.message {
                containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                contains(Integer::class.java.name)
            }
        }, { isAIntFun() }, { isAIntLazyFun {} })

        checkNarrowingAssertion<String>("it does not throw an AssertionError if the subject is the same type", { isA ->
            assert("hello").isA()
        }, { isAStringFun() }, { isAStringLazyFun {} })


        checkNarrowingAssertion<String>("it does not throw an AssertionError if the subject is a subtype", { isA ->
            assert("hello").isA()
        }, { isACharSequenceFun() }, { isACharSequenceLazyFun {} })

        checkNarrowingAssertion<SuperType>("it throws an AssertionError if the subject is a supertype", { isA ->
            expect {
                verbs.checkImmediately(SuperType()).isA()
            }.toThrow<AssertionError> {
                message.contains(SuperType::class.java.name, "is type or sub-type of", SubType::class.java.name)
            }
        }, { isASubTypeFun() }, { isASubTypeLazyFun {} })

        checkNarrowingAssertion<Int>("it allows to perform an assertion specific for the subtype which holds", { isAWithAssertion ->
            verbs.checkImmediately(1).isAWithAssertion()
        }, { isAIntLessFun(2) }, { isAIntLessLazyFun(2) })

        val expectedLessThan = 2
        val actualValue = 5
        checkNarrowingAssertion<Int>("it allows to perform an assertion specific for the subtype which fails", { isAWithAssertion ->
            expect {
                verbs.checkImmediately(actualValue).isAWithAssertion()
            }.toThrow<AssertionError>().and.message.contains(actualValue, DescriptionNumberAssertion.IS_LESS_THAN.getDefault(), expectedLessThan)
        }, { isAIntLessFun(expectedLessThan) }, { isAIntLessLazyFun(expectedLessThan) })
    }

}) {

    open class SuperType
    class SubType : SuperType()
}
