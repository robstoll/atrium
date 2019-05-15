package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.ReportingAssertionPlantNullable
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.atrium.spec.setUp
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class AnyAssertionsDeprecatedSpec(
    verbs: AssertionVerbFactory,
    funInt: AnyAssertionsSpecFunFactory<Int>,
    funDataClass: AnyAssertionsSpecFunFactory<DataClass>,
    toBe: String,
    notToBe: String,
    isSame: String,
    isNotSame: String,
    toBeNullPair: Pair<String, AssertionPlantNullable<Int?>.() -> Unit>,
    toBeNullablePair: Pair<String, AssertionPlantNullable<Int?>.(Int?) -> Unit>,
    toBeNullIfNullElsePair: Pair<String, AssertionPlantNullable<Int?>.((Assert<Int>.() -> Unit)?) -> Unit>,
    andPair: Pair<String, Assert<Int>.() -> Assert<Int>>,
    andLazyPair: Pair<String, Assert<Int>.(Assert<Int>.() -> Unit) -> Assert<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    //TODO extend SubjectLess with nullable

    include(object : SubjectLessAssertionSpec<Int>(describePrefix,
        toBe to mapToCreateAssertion { funInt.toBeFun(this, 1) },
        notToBe to mapToCreateAssertion { funInt.notToBeFun(this, 1) },
        isSame to mapToCreateAssertion { funInt.isSameFun(this, 1) },
        isNotSame to mapToCreateAssertion { funInt.isNotSameFun(this, 1) },
        andPair.first to mapToCreateAssertion { andPair.second },
        andLazyPair.first to mapToCreateAssertion { andLazyPair.second }
    ) {})

    include(object : CheckingAssertionSpec<Int>(verbs, describePrefix,
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
    val assert: (Int) -> Assert<Int> = verbs::checkImmediately
    val (toBeNull, toBeNullFun) = toBeNullPair
    val (toBeNullable, toBeNullableFun) = toBeNullablePair
    val (toBeNullIfNullElse, toBeNullIfNullElseFun) = toBeNullIfNullElsePair
    val (and, andProperty) = andPair
    val (andLazy, andLazyGroup) = andLazyPair

    describeFun(toBe, notToBe, isSame, isNotSame) {

        context("primitive") {
            val toBeFun: Assert<Int>.(Int) -> Assert<Int> = funInt.toBeFun
            val notToBeFun: Assert<Int>.(Int) -> Assert<Int> = funInt.notToBeFun
            val isSameFun: Assert<Int>.(Int) -> Assert<Int> = funInt.isSameFun
            val isNotSameFun: Assert<Int>.(Int) -> Assert<Int> = funInt.isNotSameFun

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
                    }.toThrow<AssertionError> { messageContains(NOT_TO_BE.getDefault()) }
                }
                test("$isNotSame throws AssertionError") {
                    expect {
                        assert(1).isNotSameFun(1)
                    }.toThrow<AssertionError> { messageContains(IS_NOT_SAME.getDefault()) }
                }
            }
            context("one does not equal the other") {
                test("$toBe throws AssertionError") {
                    expect {
                        assert(1).toBeFun(2)
                    }.toThrow<AssertionError> { messageContains(TO_BE.getDefault()) }
                }
                test("$notToBe does not throw") {
                    assert(1).notToBeFun(2)
                }
                test("$isSame throws AssertionError") {
                    expect {
                        assert(1).isSameFun(2)
                    }.toThrow<AssertionError> { messageContains(IS_SAME.getDefault()) }
                }
                test("$isNotSame does not throw") {
                    assert(1).isNotSameFun(2)
                }
            }
        }
        context("class") {
            val test = DataClass(true)
            val fluent = verbs.checkImmediately(test)
            val toBeFun: Assert<DataClass>.(DataClass) -> Assert<DataClass> = funDataClass.toBeFun
            val notToBeFun: Assert<DataClass>.(DataClass) -> Assert<DataClass> = funDataClass.notToBeFun
            val isSameFun: Assert<DataClass>.(DataClass) -> Assert<DataClass> = funDataClass.isSameFun
            val isNotSameFun: Assert<DataClass>.(DataClass) -> Assert<DataClass> = funDataClass.isNotSameFun
            context("same") {
                test("$toBe does not throw") {
                    fluent.toBeFun(test)
                }
                test("$notToBe throws AssertionError") {
                    expect {
                        fluent.notToBeFun(test)
                    }.toThrow<AssertionError>{}
                }
                test("$isSame does not throw") {
                    fluent.isSameFun(test)
                }
                test("$isNotSame throws AssertionError") {
                    expect {
                        fluent.isNotSameFun(test)
                    }.toThrow<AssertionError>{}
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
                    }.toThrow<AssertionError>{}
                }
                test("$isSame throws AssertionError") {
                    expect {
                        fluent.isSameFun(other)
                    }.toThrow<AssertionError>{}
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
                    }.toThrow<AssertionError>{}
                }
                test("$notToBe throws AssertionError") {
                    fluent.notToBeFun(other)
                }
                test("$isSame throws AssertionError") {
                    expect {
                        fluent.isSameFun(other)
                    }.toThrow<AssertionError>{}
                }
                test("$isNotSame does not throw") {
                    fluent.isNotSameFun(other)
                }
            }
        }
    }

    describeFun(toBeNull) {

        context("subject is null") {
            val subject: Int? = null
            it("does not throw an Exception") {
                verbs.checkNullable(subject).toBeNullFun()
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            val testee = verbs.checkNullable(1) as ReportingAssertionPlantNullable<Int?>
            val expectFun = verbs.checkException {
                testee.toBeNullFun()
            }
            setUp("throws an AssertionError") {
                context("exception message") {
                    it("contains the '${testee::subject.name}'") {
                        expectFun.toThrow<AssertionError> { messageContains(subject.toString()) }
                    }
                    it("contains the '${DescriptiveAssertion::description.name}' of the assertion-message - which should be '${TO_BE.getDefault()}'") {
                        expectFun.toThrow<AssertionError> { messageContains(TO_BE.getDefault()) }
                    }
                    it("contains the '${DescriptiveAssertion::representation.name}' of the assertion-message") {
                        expectFun.toThrow<AssertionError> { messageContains(RawString.NULL.string) }
                    }
                }
            }
        }
    }

    describeFun(toBeNullable) {

        context("subject is null") {
            val subject: Int? = null
            it("does not throw if null is passed") {
                verbs.checkNullable(subject).toBeNullableFun(null)
            }
            it("throws an AssertionError if not null is passed") {
                expect {
                    verbs.checkNullable(subject).toBeNullableFun(1)
                }.toThrow<AssertionError> {
                    messageContains(": null", "${TO_BE.getDefault()}: 1")
                }
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            it("does not throw if expected is subject") {
                verbs.checkNullable(subject).toBeNullableFun(subject)
            }
            it("throws an AssertionError if null is passed"){
                expect{
                    verbs.checkNullable(subject).toBeNullableFun(null)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: null")
                }
            }
            it("throws an AssertionError if expected does not equal subject"){
                expect{
                    verbs.checkNullable(subject).toBeNullableFun(2)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: 2")
                }
            }
        }
    }

    describeFun(toBeNullIfNullElse) {

        context("subject is null") {
            val subject: Int? = null
            it("does not throw if null is passed") {
                verbs.checkNullable(subject).toBeNullIfNullElseFun(null)
            }
            it("throws an AssertionError if not null is passed") {
                expect {
                    verbs.checkNullable(subject).toBeNullIfNullElseFun { toBe(1) }
                }.toThrow<AssertionError> {
                    messageContains(": null", "${TO_BE.getDefault()}: 1")
                }
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            it("does not throw if sub assertion holds") {
                verbs.checkNullable(subject).toBeNullIfNullElseFun { isLessThan(2) }
            }
            it("throws an AssertionError if sub assertion does not hold"){
                expect{
                    verbs.checkNullable(subject).toBeNullIfNullElseFun{ isGreaterThan(1) }
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()}: 1")
                }
            }
            it("throws an AssertionError if null is passed"){
                expect{
                    verbs.checkNullable(subject).toBeNullIfNullElseFun(null)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: null")
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
    interface AnyAssertionsSpecFunFactory<T : Any> {
        val toBeFun: Assert<T>.(T) -> Assert<T>
        val notToBeFun: Assert<T>.(T) -> Assert<T>
        val isSameFun: Assert<T>.(T) -> Assert<T>
        val isNotSameFun: Assert<T>.(T) -> Assert<T>
    }

    data class DataClass(val isWhatever: Boolean)
}
