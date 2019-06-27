package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.subAssert
import ch.tutteli.atrium.domain.builders.utils.subExpect
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

import ch.tutteli.atrium.translations.DescriptionComparableAssertion

abstract class AnyAssertionsSpec(
    verbs: AssertionVerbFactory,
    toBeInt: Expect<Int>.(Int) -> Expect<Int>,
    toBeDataClass: Expect<DataClass>.(DataClass) -> Expect<DataClass>,
    toBeNullableInt: Expect<Int?>.(Int?) -> Expect<Int?>,
    toBeNullableDataClass: Expect<DataClass?>.(DataClass?) -> Expect<DataClass?>,
    toBe: String,
    toBeNull: Fun0<Int?>,
    toBeNullable: Fun1<Int?, Int?>,
//    isSame: String,
//    isNotSame: String,
//    toBeNullPair: Pair<String, Expect<Int?>.() -> Unit>,
//    toBeNullablePair: Pair<String, Expect<Int?>.(Int?) -> Unit>,
//    toBeNullIfNullElsePair: Pair<String, Expect<Int?>.((Expect<Int>.() -> Unit)?) -> Unit>,
//    andPair: Pair<String, Expect<Int>.() -> Expect<Int>>,
//    andLazyPair: Pair<String, Expect<Int>.(Expect<Int>.() -> Unit) -> Expect<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessAssertionSpec<Int>(describePrefix,
        toBe to expectLambda { toBeInt(this, 1) }
    ) {})

    include(object : SubjectLessAssertionSpec<Int?>("$describePrefix[nullable] ",
        toBe to expectLambda { toBeNullableInt(this, 1) }
    ) {})
//
//    include(object : SubjectLessAssertionSpec<Int>(describePrefix,
//
//        notToBe to mapToCreateAssertion { toBeInt.notToBeFun(this, 1) },
//        isSame to mapToCreateAssertion { toBeInt.isSameFun(this, 1) },
//        isNotSame to mapToCreateAssertion { toBeInt.isNotSameFun(this, 1) },
//        andPair.first to mapToCreateAssertion { andPair.second },
//        andLazyPair.first to mapToCreateAssertion { andLazyPair.second }
//    ) {})
//
    include(object : CheckingAssertionSpec<Int>(verbs, describePrefix,
        checkingTriple(toBe, { toBeInt(this, 1) }, 1, 0)
//        checkingTriple(notToBe, { toBeInt.notToBeFun(this, 1) }, 0, 1),
//        checkingTriple(isSame, { toBeInt.isSameFun(this, 1) }, 1, 0),
//        checkingTriple(isNotSame, { toBeInt.isNotSameFun(this, 1) }, 0, 1)
    ) {})

    include(object : CheckingAssertionSpec<Int?>(verbs, "$describePrefix[nullable] ",
        checkingTriple(toBe, { toBeNullableInt(this, 1) }, 1, null)
//        checkingTriple(notToBe, { toBeInt.notToBeFun(this, 1) }, 0, 1),
//        checkingTriple(isSame, { toBeInt.isSameFun(this, 1) }, 1, 0),
//        checkingTriple(isNotSame, { toBeInt.isNotSameFun(this, 1) }, 0, 1)
    ) {})

//    fun prefixedDescribe(description: String, body: Suite.() -> Unit)
//        = prefixedDescribe(describePrefix, description, body)

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
//    val verbs.check: (Int?) -> Expect<Int?> = verbs::check
//    val (toBeNull, toBeNullFun) = toBeNullPair
//    val (toBeNullable, toBeNullableFun) = toBeNullablePair
//    val (toBeNullIfNullElse, toBeNullIfNullElseFun) = toBeNullIfNullElsePair
//    val (and, andProperty) = andPair
//    val (andLazy, andLazyGroup) = andLazyPair

    fun <T: Int?> Suite.checkInt(description: String, expectSubject: Expect<T>, toBeFun: Expect<T>.(Int) -> Expect<T>) {
        context(description) {
//            val isSameFun: Expect<Int>.(Int) -> Expect<Int> = toBeInt.isSameFun
//            val isNotSameFun: Expect<Int>.(Int) -> Expect<Int> = toBeInt.isNotSameFun

            context("one equals the other") {
                it("$toBe does not throw") {
                    expectSubject.toBeFun(1)
                }
//                test("$isSame does not throw") {
//                    assert(1).isSameFun(1)
//                }
//                test("$notToBe throws AssertionError") {
//                    expect {
//                        assert(1).notToBeFun(1)
//                    }.toThrow<AssertionError> { messageContains(NOT_TO_BE.getDefault()) }
//                }
//                test("$isNotSame throws AssertionError") {
//                    expect {
//                        assert(1).isNotSameFun(1)
//                    }.toThrow<AssertionError> { messageContains(IS_NOT_SAME.getDefault()) }
//                }
            }
            context("one does not equal the other") {
                it("$toBe throws AssertionError") {
                    expect {
                        expectSubject.toBeFun(2)
                    }.toThrow<AssertionError> { messageContains(TO_BE.getDefault()) }
                }
//                test("$notToBe does not throw") {
//                    assert(1).notToBeFun(2)
//                }
//                test("$isSame throws AssertionError") {
//                    expect {
//                        assert(1).isSameFun(2)
//                    }.toThrow<AssertionError> { messageContains(IS_SAME.getDefault()) }
//                }
//                test("$isNotSame does not throw") {
//                    assert(1).isNotSameFun(2)
//                }
            }
        }
    }

    fun <T: DataClass?> Suite.checkDataClass(description: String, expectSubject: Expect<T>, toBeFun: Expect<T>.(DataClass) -> Expect<T>, test: DataClass) {
        context(description) {
//            val notToBeFun: Expect<DataClass>.(DataClass) -> Expect<DataClass> = funDataClass.notToBeFun
//            val isSameFun: Expect<DataClass>.(DataClass) -> Expect<DataClass> = funDataClass.isSameFun
//            val isNotSameFun: Expect<DataClass>.(DataClass) -> Expect<DataClass> = funDataClass.isNotSameFun
            context("same") {
                it("$toBe does not throw") {
                    expectSubject.toBeFun(test)
                }
//                test("$notToBe throws AssertionError") {
//                    expect {
//                        fluent.notToBeFun(test)
//                    }.toThrow<AssertionError>{}
//                }
//                test("$isSame does not throw") {
//                    fluent.isSameFun(test)
//                }
//                test("$isNotSame throws AssertionError") {
//                    expect {
//                        fluent.isNotSameFun(test)
//                    }.toThrow<AssertionError>{}
//                }
            }
            context("not same but one equals the other") {
                val other = DataClass(true)
                it("$toBe does not throw") {
                    expectSubject.toBeFun(other)
                }
//                test("$notToBe throws AssertionError") {
//                    expect {
//                        fluent.notToBeFun(other)
//                    }.toThrow<AssertionError>{}
//                }
//                test("$isSame throws AssertionError") {
//                    expect {
//                        fluent.isSameFun(other)
//                    }.toThrow<AssertionError>{}
//                }
//                test("$isNotSame does not throw") {
//                    fluent.isNotSameFun(other)
//                }
            }
            context("one does not equal the other") {
                val other = DataClass(false)
                it("$toBe does not throw") {
                    expect {
                        expectSubject.toBeFun(other)
                    }.toThrow<AssertionError> {}
                }
//                test("$notToBe throws AssertionError") {
//                    fluent.notToBeFun(other)
//                }
//                test("$isSame throws AssertionError") {
//                    expect {
//                        fluent.isSameFun(other)
//                    }.toThrow<AssertionError>{}
//                }
//                test("$isNotSame does not throw") {
//                    fluent.isNotSameFun(other)
//                }
            }
        }
    }

    fun <T: Any> Suite.checkNull(description: String, toBeFun: Expect<T?>.(T?) -> Expect<T?>, value: T, type: String) {
        context(description){
            context("one equals the other") {
                it("$toBe does not throw") {
                    verbs.check(null as T?).toBeFun(null)
                }
//                test("$isSame does not throw") {
//                    assert(1).isSameFun(1)
//                }
//                test("$notToBe throws AssertionError") {
//                    expect {
//                        assert(1).notToBeFun(1)
//                    }.toThrow<AssertionError> { messageContains(NOT_TO_BE.getDefault()) }
//                }
//                test("$isNotSame throws AssertionError") {
//                    expect {
//                        assert(1).isNotSameFun(1)
//                    }.toThrow<AssertionError> { messageContains(IS_NOT_SAME.getDefault()) }
//                }
            }
            context("one does not equal the other") {
                it("$toBe throws AssertionError") {
                    expect {
                        verbs.check(null as T?).toBeFun(value)
                    }.toThrow<AssertionError> {
                        messageContains(
                            TO_BE.getDefault(),
                            DescriptionTypeTransformationAssertion.IS_A.getDefault()+": $type"
                        )
                    }
                }
//                test("$notToBe does not throw") {
//                    assert(1).notToBeFun(2)
//                }
//                test("$isSame throws AssertionError") {
//                    expect {
//                        assert(1).isSameFun(2)
//                    }.toThrow<AssertionError> { messageContains(IS_SAME.getDefault()) }
//                }
//                test("$isNotSame does not throw") {
//                    assert(1).isNotSameFun(2)
//                }
            }
        }
    }

    describeFun(toBe) {
        //, notToBe, isSame, isNotSame) {
        checkInt("primitive", verbs.check(1), toBeInt)
        checkInt("nullable primitive", verbs.check(1 as Int?), toBeNullableInt)

        val subject = DataClass(true)
        checkDataClass("class", verbs.check(subject), toBeDataClass, subject)
        checkDataClass("nullable class", verbs.check(subject as DataClass?), toBeNullableDataClass, subject)

        checkNull("null as Int?", toBeNullableInt, 2, "Int (kotlin.Int)")
        checkNull("null as DataClass?", toBeNullableDataClass, subject, "DataClass")
    }

    describeFun(toBeNull.name) {

        val toBeNullFun = toBeNull.lambda
        context("subject is null") {
            val subject: Int? = null
            it("does not throw an Exception") {
                verbs.check(subject).toBeNullFun()
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            val testee = verbs.check(1 as Int?)
            val expectFun by memoized {
                verbs.checkException {
                    testee.toBeNullFun()
                }
            }
            context("throws an AssertionError and exception message") {
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

    describeFun(toBeNullable.name) {
        val toBeNullableFun = toBeNullable.lambda

        context("subject is null") {
            val subject: Int? = null
            it("does not throw if null is passed") {
                verbs.check(subject).toBeNullableFun(null)
            }
            it("throws an AssertionError if not null is passed") {
                expect {
                    verbs.check(subject).toBeNullableFun(1)
                }.toThrow<AssertionError> {
                    messageContains(": null", "${TO_BE.getDefault()}: 1")
                }
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            it("does not throw if expected is subject") {
                verbs.check(subject).toBeNullableFun(subject)
            }
            it("throws an AssertionError if null is passed"){
                expect{
                    verbs.check(subject).toBeNullableFun(null)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: null")
                }
            }
            it("throws an AssertionError if expected does not equal subject"){
                expect{
                    verbs.check(subject).toBeNullableFun(2)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: 2")
                }
            }
        }
    }
//
//    describeFun(toBeNullIfNullElse) {
//
//        context("subject is null") {
//            val subject: Int? = null
//            it("does not throw if null is passed") {
//                verbs.check(subject).toBeNullIfNullElseFun(null)
//            }
//            it("throws an AssertionError if not null is passed") {
//                expect {
//                    verbs.check(subject).toBeNullIfNullElseFun { toBe(1) }
//                }.toThrow<AssertionError> {
//                    messageContains(": null", "${TO_BE.getDefault()}: 1")
//                }
//            }
//        }
//
//        context("subject is not null") {
//            val subject: Int? = 1
////            it("does not throw if sub assertion holds") {
////                verbs.check(subject).toBeNullIfNullElseFun { isLessThan(2) }
////            }
////            it("throws an AssertionError if sub assertion does not hold"){
////                expect{
////                    verbs.check(subject).toBeNullIfNullElseFun{ isGreaterThan(1) }
////                }.toThrow<AssertionError> {
////                    messageContains(": 1", "${DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()}: 1")
////                }
////            }
//            it("throws an AssertionError if null is passed"){
//                expect{
//                    verbs.check(subject).toBeNullIfNullElseFun(null)
//                }.toThrow<AssertionError> {
//                    messageContains(": 1", "${TO_BE.getDefault()}: null")
//                }
//            }
//
//        }
//    }

//
//    prefixedDescribe("property `$and` immediate") {
//        it("returns the same plant") {
//            val plant = assert(1)
//            verbs.check(plant.andProperty()).toBe(plant)
//        }
//    }
//    prefixedDescribe("`$andLazy` group") {
//        it("returns the same plant") {
//            val plant = assert(1)
//            verbs.check(plant.andLazyGroup { }).toBe(plant)
//        }
//    }

}) {
    data class DataClass(val isWhatever: Boolean)
}
