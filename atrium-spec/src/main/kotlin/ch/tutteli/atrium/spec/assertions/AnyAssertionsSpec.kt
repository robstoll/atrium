package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.*
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IReportingAssertionPlantNullable
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.atrium.spec.setUp
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class AnyAssertionsSpec(
    verbs: IAssertionVerbFactory,
    funInt: IAnyAssertionsSpecFunFactory<Int>,
    funDataClass: IAnyAssertionsSpecFunFactory<DataClass>,
    toBe: String,
    notToBe: String,
    isSame: String,
    isNotSame: String,
    isNullPair: Pair<String, IAssertionPlantNullable<Int?>.() -> Unit>,
    andPair: Pair<String, IAssertionPlant<Int>.() -> IAssertionPlant<Int>>,
    andLazyPair: Pair<String, IAssertionPlant<Int>.(IAssertionPlant<Int>.() -> Unit) -> IAssertionPlant<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    //TODO extend SubjectLess with nullable

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Int>(
        toBe to mapToCreateAssertion { funInt.toBeFun(this, 1) },
        notToBe to mapToCreateAssertion { funInt.notToBeFun(this, 1) },
        isSame to mapToCreateAssertion { funInt.isSameFun(this, 1) },
        isNotSame to mapToCreateAssertion { funInt.isNotSameFun(this, 1) },
        andPair.first to mapToCreateAssertion { andPair.second },
        andLazyPair.first to mapToCreateAssertion { andLazyPair.second }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Int>(verbs,
        checkingTriple(toBe, { funInt.toBeFun(this, 1) }, 1, 0),
        checkingTriple(notToBe, { funInt.notToBeFun(this, 1) }, 0, 1),
        checkingTriple(isSame, { funInt.isSameFun(this, 1) }, 1, 0),
        checkingTriple(isNotSame, { funInt.isNotSameFun(this, 1) }, 0, 1)
    ) {})

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit)
        = prefixedDescribe(describePrefix, description, body)

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val assert: (Int) -> IAssertionPlant<Int> = verbs::checkImmediately
    val (isNull, isNullFun) = isNullPair
    val (and, andProperty) = andPair
    val (andLazy, andLazyGroup) = andLazyPair

    describeFun(toBe, notToBe, isSame, isNotSame) {

        context("primitive") {
            val toBeFun: IAssertionPlant<Int>.(Int) -> IAssertionPlant<Int> = funInt.toBeFun
            val notToBeFun: IAssertionPlant<Int>.(Int) -> IAssertionPlant<Int> = funInt.notToBeFun
            val isSameFun: IAssertionPlant<Int>.(Int) -> IAssertionPlant<Int> = funInt.isSameFun
            val isNotSameFun: IAssertionPlant<Int>.(Int) -> IAssertionPlant<Int> = funInt.isNotSameFun

            context("one equals the other") {
                test("$toBe does not throw") {
                    assert(1).toBeFun(1)
                }
                test("$isSame does not throw") {
                    assert(1).isSameFun(1)
                }
                test("$notToBe throws AssertionError") {
                    expect {
                        assert(1).notToBeFun(1)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(NOT_TO_BE) } }
                }
                test("$isNotSame throws AssertionError") {
                    expect {
                        assert(1).isNotSameFun(1)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(IS_NOT_SAME) } }
                }
            }
            context("one does not equal the other") {
                test("$toBe throws AssertionError") {
                    expect {
                        assert(1).toBeFun(2)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(TO_BE) } }
                }
                test("$notToBe does not throw") {
                    assert(1).notToBeFun(2)
                }
                test("$isSame throws AssertionError") {
                    expect {
                        assert(1).isSameFun(2)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(IS_SAME) } }
                }
                test("$isNotSame does not throw") {
                    assert(1).isNotSameFun(2)
                }
            }
        }
        context("class") {
            val test = DataClass(true)
            val fluent = verbs.checkImmediately(test)
            val toBeFun: IAssertionPlant<DataClass>.(DataClass) -> IAssertionPlant<DataClass> = funDataClass.toBeFun
            val notToBeFun: IAssertionPlant<DataClass>.(DataClass) -> IAssertionPlant<DataClass> = funDataClass.notToBeFun
            val isSameFun: IAssertionPlant<DataClass>.(DataClass) -> IAssertionPlant<DataClass> = funDataClass.isSameFun
            val isNotSameFun: IAssertionPlant<DataClass>.(DataClass) -> IAssertionPlant<DataClass> = funDataClass.isNotSameFun
            context("same") {
                test("$toBe does not throw") {
                    fluent.toBeFun(test)
                }
                test("$notToBe throws AssertionError") {
                    expect {
                        fluent.notToBeFun(test)
                    }.toThrow<AssertionError>()
                }
                test("$isSame does not throw") {
                    fluent.isSameFun(test)
                }
                test("$isNotSame throws AssertionError") {
                    expect {
                        fluent.isNotSameFun(test)
                    }.toThrow<AssertionError>()
                }
            }
            context("not same but one equals the other") {
                val other = DataClass(true)
                test("$toBe does not throw") {
                    fluent.toBeFun(other)
                }
                test("$notToBe throws AssertionError") {
                    expect {
                        fluent.notToBeFun(other)
                    }.toThrow<AssertionError>()
                }
                test("$isSame throws AssertionError") {
                    expect {
                        fluent.isSameFun(other)
                    }.toThrow<AssertionError>()
                }
                test("$isNotSame does not throw") {
                    fluent.isNotSameFun(other)
                }
            }
            context("one does not equal the other") {
                val other = DataClass(false)
                test("$toBe does not throw") {
                    expect {
                        fluent.toBeFun(other)
                    }.toThrow<AssertionError>()
                }
                test("$notToBe throws AssertionError") {
                    fluent.notToBeFun(other)
                }
                test("$isSame throws AssertionError") {
                    expect {
                        fluent.isSameFun(other)
                    }.toThrow<AssertionError>()
                }
                test("$isNotSame does not throw") {
                    fluent.isNotSameFun(other)
                }
            }
        }
    }

    describeFun(isNull) {

        context("subject is null") {
            val subject: Int? = null
            it("does not throw an Exception") {
                verbs.checkNullable(subject).isNullFun()
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            val testee = verbs.checkNullable(1) as IReportingAssertionPlantNullable<Int?>
            val expectFun = verbs.checkException {
                testee.isNullFun()
            }
            setUp("throws an AssertionError") {
                context("exception message") {
                    it("contains the '${testee::subject.name}'") {
                        expectFun.toThrow<AssertionError> { message { contains(subject.toString()) } }
                    }
                    it("contains the '${IBasicAssertion::description.name}' of the assertion-message - which should be '${DescriptionAnyAssertion.TO_BE.getDefault()}'") {
                        expectFun.toThrow<AssertionError> {
                            message { containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE) }
                        }
                    }
                    it("contains the '${IBasicAssertion::expected.name}' of the assertion-message") {
                        expectFun.toThrow<AssertionError> { message { contains(RawString.NULL.string) } }
                    }
                }
            }
        }
    }

    prefixedDescribe("property `$and` immediate") {
        it("returns the same plant") {
            val plant = assert(1)
            verbs.checkImmediately(plant.andProperty()).toBe(plant)
        }
    }
    prefixedDescribe("`$andLazy` group") {
        it("returns the same plant") {
            val plant = assert(1)
            verbs.checkImmediately(plant.andLazyGroup { }).toBe(plant)
        }
    }

}) {
    interface IAnyAssertionsSpecFunFactory<T : Any> {
        val toBeFun: IAssertionPlant<T>.(T) -> IAssertionPlant<T>
        val notToBeFun: IAssertionPlant<T>.(T) -> IAssertionPlant<T>
        val isSameFun: IAssertionPlant<T>.(T) -> IAssertionPlant<T>
        val isNotSameFun: IAssertionPlant<T>.(T) -> IAssertionPlant<T>
    }

    data class DataClass(val isWhatever: Boolean)
}
