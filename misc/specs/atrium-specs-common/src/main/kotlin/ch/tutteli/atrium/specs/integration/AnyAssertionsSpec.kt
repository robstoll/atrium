package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_NOT_SAME
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class AnyAssertionsSpec(
    verbs: AssertionVerbFactory,
    toBeInt: Fun1<Int, Int>,
    toBeDataClass: Fun1<DataClass, DataClass>,
    toBeNullableInt: Fun1<Int?, Int?>,
    toBeNullableDataClass: Fun1<DataClass?, DataClass?>,
    notToBeInt: Fun1<Int, Int>,
    notToBeDataClass: Fun1<DataClass, DataClass>,
    notToBeNullableInt: Fun1<Int?, Int?>,
    notToBeNullableDataClass: Fun1<DataClass?, DataClass?>,
    isSameInt: Fun1<Int, Int>,
    isSameDataClass: Fun1<DataClass, DataClass>,
    isSameNullableInt: Fun1<Int?, Int?>,
    isSameNullableDataClass: Fun1<DataClass?, DataClass?>,
    isNotSameInt: Fun1<Int, Int>,
    isNotSameDataClass: Fun1<DataClass, DataClass>,
    isNotSameNullableInt: Fun1<Int?, Int?>,
    isNotSameNullableDataClass: Fun1<DataClass?, DataClass?>,

    toBeNull: Fun0<Int?>,
    toBeNullIfNullGivenElse: Fun1<Int?, (Expect<Int>.() -> Unit)?>,
    andPair: Fun0<Int>,
    andLazyPair: Fun1<Int, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Int>(
        describePrefix,
        toBeInt.forSubjectLess(1),
        notToBeInt.forSubjectLess(1),
        isSameInt.forSubjectLess(1),
        isNotSameInt.forSubjectLess(1),
        andPair.forSubjectLess(),
        andLazyPair.forSubjectLess { toBe(1) }
    ) {})

    include(object : SubjectLessSpec<Int?>(
        "$describePrefix[nullable] ",
        toBeNullableInt.forSubjectLess(1),
        notToBeNullableInt.forSubjectLess(1),
        isSameNullableInt.forSubjectLess(1),
        isNotSameNullableInt.forSubjectLess(1),
        toBeNull.forSubjectLess()
    ) {})

    include(object : CheckingAssertionSpec<Int>(
        verbs, describePrefix,
        toBeInt.forChecking(1, 1, 0),
        notToBeInt.forChecking(1, 0, 1),
        isSameInt.forChecking(1, 1, 0),
        isNotSameInt.forChecking(1, 0, 1)
    ) {})

    include(object : CheckingAssertionSpec<Int?>(
        verbs, "$describePrefix[nullable] ",
        toBeNullableInt.forChecking(1, 1, null),
        notToBeNullableInt.forChecking(1, null, 1),
        isSameNullableInt.forChecking(1, 1, null),
        isNotSameNullableInt.forChecking(1, null, 1),
        toBeNull.forChecking(null, 1)
    ) {})

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) =
        prefixedDescribeTemplate(describePrefix, description, body)

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException

    fun <T : Int?> Suite.checkInt(
        description: String,
        expectSubject: Expect<T>,
        toBe: Fun1<T, Int>,
        notToBe: Fun1<T, Int>,
        isSame: Fun1<T, Int>,
        isNotSame: Fun1<T, Int>
    ) {
        context(description) {
            val toBeFun = toBe.lambda
            val notToBeFun = notToBe.lambda
            val isSameFun = isSame.lambda
            val isNotSameFun = isNotSame.lambda

            context("one equals the other") {
                it("${toBe.name} does not throw") {
                    expectSubject.toBeFun(1)
                }
                it("${isSame.name} does not throw") {
                    expectSubject.isSameFun(1)
                }
                it("${notToBe.name} throws AssertionError") {
                    expect {
                        expectSubject.notToBeFun(1)
                    }.toThrow<AssertionError> { messageContains(NOT_TO_BE.getDefault()) }
                }
                it("${isNotSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isNotSameFun(1)
                    }.toThrow<AssertionError> { messageContains(IS_NOT_SAME.getDefault()) }
                }
            }
            context("one does not equal the other") {
                it("${toBe.name} throws AssertionError") {
                    expect {
                        expectSubject.toBeFun(2)
                    }.toThrow<AssertionError> { messageContains(TO_BE.getDefault()) }
                }
                it("${notToBe.name} does not throw") {
                    expectSubject.notToBeFun(2)
                }
                it("${isSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isSameFun(2)
                    }.toThrow<AssertionError> { messageContains(IS_SAME.getDefault()) }
                }
                it("${isNotSame.name} does not throw") {
                    expectSubject.isNotSameFun(2)
                }
            }
        }
    }

    fun <T : DataClass?> Suite.checkDataClass(
        description: String,
        expectSubject: Expect<T>,
        toBe: Fun1<T, DataClass>,
        notToBe: Fun1<T, DataClass>,
        isSame: Fun1<T, DataClass>,
        isNotSame: Fun1<T, DataClass>,
        test: DataClass
    ) {
        val toBeFun = toBe.lambda
        val notToBeFun = notToBe.lambda
        val isSameFun = isSame.lambda
        val isNotSameFun = isNotSame.lambda

        context(description) {
            context("same") {
                it("${toBe.name} does not throw") {
                    expectSubject.toBeFun(test)
                }
                it("${notToBe.name} throws AssertionError") {
                    expect {
                        expectSubject.notToBeFun(test)
                    }.toThrow<AssertionError> {}
                }
                it("${isSame.name} does not throw") {
                    expectSubject.isSameFun(test)
                }
                it("${isNotSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isNotSameFun(test)
                    }.toThrow<AssertionError> {}
                }
            }
            context("not same but one equals the other") {
                val other = DataClass(true)
                it("${toBe.name} does not throw") {
                    expectSubject.toBeFun(other)
                }
                it("${notToBe.name} throws AssertionError") {
                    expect {
                        expectSubject.notToBeFun(other)
                    }.toThrow<AssertionError> {}
                }
                it("${isSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isSameFun(other)
                    }.toThrow<AssertionError> {}
                }
                it("${isNotSame.name} does not throw") {
                    expectSubject.isNotSameFun(other)
                }
            }
            context("one does not equal the other") {
                val other = DataClass(false)
                it("${toBe.name} does not throw") {
                    expect {
                        expectSubject.toBeFun(other)
                    }.toThrow<AssertionError> {}
                }
                it("${notToBe.name} throws AssertionError") {
                    expectSubject.notToBeFun(other)
                }
                it("${isSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isSameFun(other)
                    }.toThrow<AssertionError> {}
                }
                it("${isNotSame.name} does not throw") {
                    expectSubject.isNotSameFun(other)
                }
            }
        }
    }

    fun <T : Any> Suite.checkNull(
        description: String,
        toBe: Fun1<T?, T?>,
        notToBe: Fun1<T?, T?>,
        isSame: Fun1<T?, T?>,
        isNotSame: Fun1<T?, T?>,
        value: T,
        type: String
    ) {

        val toBeFun = toBe.lambda
        val notToBeFun = notToBe.lambda
        val isSameFun = isSame.lambda
        val isNotSameFun = isNotSame.lambda
        val expectSubject = verbs.check(null as T?)

        context(description) {
            context("one equals the other") {
                it("${toBe.name} does not throw") {
                    expectSubject.toBeFun(null)
                }
                it("${isSame.name} does not throw") {
                    expectSubject.isSameFun(null)
                }
                it("${notToBe.name} throws AssertionError") {
                    expect {
                        expectSubject.notToBeFun(null)
                    }.toThrow<AssertionError> { messageContains(NOT_TO_BE.getDefault()) }
                }
                it("${isNotSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isNotSameFun(null)
                    }.toThrow<AssertionError> { messageContains(IS_NOT_SAME.getDefault()) }
                }
            }
            context("one does not equal the other") {
                it("${toBe.name} throws AssertionError") {
                    expect {
                        verbs.check(null as T?).toBeFun(value)
                    }.toThrow<AssertionError> {
                        messageContains(
                            TO_BE.getDefault(),
                            DescriptionTypeTransformationAssertion.IS_A.getDefault() + ": $type"
                        )
                    }
                }
                it("${notToBe.name} does not throw") {
                    expectSubject.notToBeFun(value)
                }
                it("${isSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isSameFun(value)
                    }.toThrow<AssertionError> { messageContains(IS_SAME.getDefault()) }
                }
                it("${isNotSame.name} does not throw") {
                    expectSubject.isNotSameFun(value)
                }
            }
        }
    }

    describeFun(toBeInt.name, notToBeInt.name, isSameInt.name, isNotSameInt.name) {
        checkInt("primitive", verbs.check(1), toBeInt, notToBeInt, isSameInt, isNotSameInt)
        checkInt(
            "nullable primitive",
            verbs.check(1 as Int?),
            toBeNullableInt,
            notToBeNullableInt,
            isSameNullableInt,
            isNotSameNullableInt
        )

        val subject = DataClass(true)
        checkDataClass(
            "class",
            verbs.check(subject),
            toBeDataClass,
            notToBeDataClass,
            isSameDataClass,
            isNotSameDataClass,
            subject
        )
        checkDataClass(
            "nullable class",
            verbs.check(subject as DataClass?),
            toBeNullableDataClass,
            notToBeNullableDataClass,
            isSameNullableDataClass,
            isNotSameNullableDataClass,
            subject
        )

        checkNull(
            "null as Int?",
            toBeNullableInt,
            notToBeNullableInt,
            isSameNullableInt,
            isNotSameNullableInt,
            2,
            "Int (kotlin.Int)"
        )
        checkNull(
            "null as DataClass?",
            toBeNullableDataClass,
            notToBeNullableDataClass,
            isSameNullableDataClass,
            isNotSameNullableDataClass,
            subject,
            "DataClass"
        )
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
                it("contains the subject") {
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

    describeFun(toBeNullableInt.name) {
        val toBeNullableFun = toBeNullableInt.lambda

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
            it("throws an AssertionError if null is passed") {
                expect {
                    verbs.check(subject).toBeNullableFun(null)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: null")
                }
            }
            it("throws an AssertionError if expected does not equal subject") {
                expect {
                    verbs.check(subject).toBeNullableFun(2)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: 2")
                }
            }
        }
    }

    describeFun(toBeNullIfNullGivenElse.name) {
        val toBeNullIfNullElseFun = toBeNullIfNullGivenElse.lambda
        context("subject is null") {
            val subject: Int? = null
            it("does not throw if null is passed") {
                verbs.check(subject).toBeNullIfNullElseFun(null)
            }
            it("throws an AssertionError if not null is passed") {
                expect {
                    verbs.check(subject).toBeNullIfNullElseFun { toBe(1) }
                }.toThrow<AssertionError> {
                    messageContains(": null", "${TO_BE.getDefault()}: 1")
                }
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
//            it("does not throw if sub assertion holds") {
//                verbs.check(subject).toBeNullIfNullElseFun { isLessThan(2) }
//            }
//            it("throws an AssertionError if sub assertion does not hold"){
//                expect{
//                    verbs.check(subject).toBeNullIfNullElseFun{ isGreaterThan(1) }
//                }.toThrow<AssertionError> {
//                    messageContains(": 1", "${DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()}: 1")
//                }
//            }
            it("throws an AssertionError if null is passed") {
                expect {
                    verbs.check(subject).toBeNullIfNullElseFun(null)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: null")
                }
            }

        }
    }


    prefixedDescribe("property `${andPair.name}` immediate") {
        it("returns the same plant") {
            val plant = verbs.check(1)
            verbs.check(plant.(andPair.lambda)()).toBe(plant)
        }
    }
    prefixedDescribe("`${andLazyPair.name}` group") {
        it("returns the same plant") {
            val plant = verbs.check(1)
            verbs.check(plant.(andLazyPair.lambda){ }).toBe(plant)
        }
    }

}) {
    data class DataClass(val isWhatever: Boolean)
}
