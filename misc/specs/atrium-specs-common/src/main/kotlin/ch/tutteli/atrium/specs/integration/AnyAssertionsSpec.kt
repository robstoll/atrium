package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.ErrorMessages
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

    isAFeature: Feature0<Int?, Int>,
    isA: Feature1<Int?, Expect<Int>.() -> Unit, Int>,

    isAIntFun: Feature1<String, Expect<Int>.() -> Unit, Int>,
    isAStringFun: Feature1<String, Expect<String>.() -> Unit, String>,
    isACharSequenceFun: Feature1<String, Expect<CharSequence>.() -> Unit, CharSequence>,
    isASubTypeFun: Feature1<SuperType, Expect<SubType>.() -> Unit, SubType>,
    isAIntLessThan: Feature1<Number, Int, Int>,

    notToBeNull: Feature1<Int?, Expect<Int>.() -> Unit, Int>,
    notToBeNullLessThanFun: Expect<Int?>.(Int) -> Expect<Int>,
    notToBeNullGreaterAndLessThanFun: Expect<Int?>.(Int, Int) -> Expect<Int>,

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
        toBeNull.forSubjectLess(),
        isAFeature.forSubjectLess(),
        isA.forSubjectLess { toBe(1) },
        notToBeNull.forSubjectLess { toBe(1) }
    ) {})

    include(object : AssertionCreatorSpec<Int>(
        verbs, describePrefix, 1,
        andLazyPair.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) }
    ) {})
    include(object : AssertionCreatorSpec<Int?>(
        verbs, "$describePrefix[nullable Element] ", 1,
        toBeNullIfNullGivenElse.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) },
        assertionCreatorSpecTriple(isA.name, "$toBeDescr: 1", { apply{isA.invoke(this) { toBe(1) } }}, { apply{isA.invoke(this) {} }} )
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
                    }.toThrow<AssertionError>()
                }
                it("${isSame.name} does not throw") {
                    expectSubject.isSameFun(test)
                }
                it("${isNotSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isNotSameFun(test)
                    }.toThrow<AssertionError>()
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
                    }.toThrow<AssertionError>()
                }
                it("${isSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isSameFun(other)
                    }.toThrow<AssertionError>()
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
                    }.toThrow<AssertionError>()
                }
                it("${notToBe.name} throws AssertionError") {
                    expectSubject.notToBeFun(other)
                }
                it("${isSame.name} throws AssertionError") {
                    expect {
                        expectSubject.isSameFun(other)
                    }.toThrow<AssertionError>()
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
                            DescriptionAnyAssertion.IS_A.getDefault() + ": $type"
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
            it("does not throw if sub assertion holds") {
                verbs.check(subject).toBeNullIfNullElseFun { isLessThan(2) }
            }
            it("throws an AssertionError if sub assertion does not hold") {
                expect {
                    verbs.check(subject).toBeNullIfNullElseFun { isGreaterThan(1) }
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()}: 1")
                }
            }
            it("throws an AssertionError if null is passed") {
                expect {
                    verbs.check(subject).toBeNullIfNullElseFun(null)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: null")
                }
            }

        }
    }


    describeFun(notToBeNull.name) {

        val notToBeNullFun = notToBeNull.lambda

        context("subject is null") {
            it("throws an AssertionError") {
                expect {
                    val i: Int? = null
                    verbs.check(i).notToBeNullFun { toBe(1) }
                }.toThrow<AssertionError> {
                    messageContains(IS_A.getDefault() + ": Int (kotlin.Int)")
                }
            }

            context("it still allows to define assertion on the subject even if it is null") {
                it("throws an AssertionError and contains the additional assertion as explanation") {
                    expect {
                        val i: Int? = null
                        verbs.check(i).notToBeNullLessThanFun(2)
                    }.toThrow<AssertionError> {
                        messageContains(
                            IS_A.getDefault() + ": Int (kotlin.Int)",
                            DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
                        )
                    }
                }
            }
        }

        context("subject is not null") {

            it("does not throw") {
                val i: Int? = 1
                verbs.check(i).notToBeNullFun { toBe(1) }
            }

            context("it allows to define an assertion for the subject") {
                it("does not throw if the assertion holds") {
                    val i: Int? = 1
                    verbs.check(i).notToBeNullLessThanFun(2)
                }

                it("throws an AssertionError if the assertion does not hold") {
                    expect {
                        val i: Int? = 1
                        verbs.check(i).notToBeNullLessThanFun(0)
                    }.toThrow<AssertionError>()
                }
            }
            context("it allows to define multiple assertions for the subject") {
                it("does not throw if the assertions hold") {
                    val i: Int? = 1
                    verbs.check(i).notToBeNullGreaterAndLessThanFun(0, 2)
                }

                it("throws an AssertionError if one assertion does not hold") {
                    expect {
                        val i: Int? = 1
                        verbs.check(i).notToBeNullGreaterAndLessThanFun(2, 5)
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
                        verbs.check(i).notToBeNullGreaterAndLessThanFun(2, 0)
                    }.toThrow<AssertionError> {
                        messageContains(
                            DescriptionComparableAssertion.IS_GREATER_THAN.getDefault(),
                            DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
                        )
                    }
                }
            }
        }

        context("in a feature assertion and subject is null") {
            it("throws an AssertionError") {
                class A(val i: Int? = null)
                expect {
                    verbs.check(A()).feature(A::i).notToBeNullFun { toBe(1) }
                }.toThrow<AssertionError> {
                    messageContains(
                        A::class.simpleName!!,
                        IS_A.getDefault() + ": Int (kotlin.Int)",
                        TO_BE.getDefault() + ": 1"
                    )
                }
            }

            it("throws an AssertionError which contains subsequent assertions") {
                class A(val i: Int? = null)
                expect {
                    verbs.check(A()).feature(A::i).notToBeNullLessThanFun(1)
                }.toThrow<AssertionError> {
                    messageContains(
                        A::class.simpleName!!,
                        IS_A.getDefault() + ": Int (kotlin.Int)",
                        DescriptionComparableAssertion.IS_LESS_THAN.getDefault()

                    )
                }
            }
        }
    }


    describeFun("${isAFeature.name} feature"){
        val isAFun = isAFeature.lambda

        context("subject is not in type hierarchy") {
            it("throws an AssertionError") {
                expect {
                    verbs.check(null as Int?).isAFun().toBe(1)
                }.toThrow<AssertionError> {
                    message{
                        contains(IS_A.getDefault() + ": Int (kotlin.Int)")
                        containsNot(TO_BE.getDefault() + ": 1")
                    }
                }
            }
        }

        context("subject is the same type") {
            it("does not throw an AssertionError") {
                verbs.check(1 as Int?).isAFun()
            }
            context("it allows to perform an assertion specific for the subtype...") {

                it("... which holds -- does not throw") {
                    verbs.check(1 as Int?).isAFun().isLessThan(2)
                }
                it("... which fails -- throws an AssertionError") {
                    val expectedLessThan = 2
                    val actualValue: Number = 5
                    expect {
                        verbs.check(actualValue as Int?).isAFun().isLessThan(expectedLessThan)
                    }.toThrow<AssertionError> {
                        messageContains(
                            actualValue,
                            DescriptionComparableAssertion.IS_LESS_THAN.getDefault(),
                            expectedLessThan
                        )
                    }
                }
            }
        }
    }

    describeFun(isA.name) {

        context("subject is not in type hierarchy") {
            it("throws an AssertionError") {
                expect {
                    verbs.check("hello").(isAIntFun.lambda) { toBe(1) }
                }.toThrow<AssertionError> {
                    messageContains(
                        IS_A.getDefault() + ": Int (kotlin.Int)",
                        TO_BE.getDefault() + ": 1"
                    )
                }
            }
        }

        val isAIntLessThanFun = isAIntLessThan.lambda

        context("subject is the same type") {
            it("does not throw an AssertionError") {
                verbs.check("hello").(isAStringFun.lambda) { toBe("hello") }
            }

            context("it allows to perform an assertion specific for the subtype...") {

                it("... which holds -- does not throw") {
                    verbs.check(1 as Number).isAIntLessThanFun(2)
                }

                val expectedLessThan = 2
                val actualValue: Number = 5
                it("... which fails -- throws an AssertionError") {
                    expect {
                        verbs.check(actualValue).isAIntLessThanFun(expectedLessThan)
                    }.toThrow<AssertionError> {
                        messageContains(
                            actualValue,
                            DescriptionComparableAssertion.IS_LESS_THAN.getDefault(),
                            expectedLessThan
                        )
                    }
                }
            }
        }

        context("subject is a subtype") {

            it("does not throw an AssertionError if the subject is a subtype") {
                verbs.check("hello").(isACharSequenceFun.lambda) { isNotEmpty() }
            }

            context("it allows to perform an assertion specific for the subtype...") {

                it("... which holds -- does not throw") {
                    verbs.check(1 as Number).isAIntLessThanFun(2)
                }

                val expectedLessThan = 2
                val actualValue: Number = 5
                it("... which fails -- throws an AssertionError") {
                    expect {
                        verbs.check(actualValue).isAIntLessThanFun(expectedLessThan)
                    }.toThrow<AssertionError> {
                        messageContains(
                            actualValue,
                            DescriptionComparableAssertion.IS_LESS_THAN.getDefault(),
                            expectedLessThan
                        )
                    }
                }
            }
        }

        context("subject is a supertype") {
            it("throws an AssertionError") {
                expect {
                    verbs.check(SuperType()).(isASubTypeFun.lambda) { isSameAs(SubType()) }
                }.toThrow<AssertionError> {
                    messageContains(
                        SuperType::class.fullName,
                        IS_A.getDefault(),
                        SubType::class.fullName,
                        DescriptionAnyAssertion.IS_SAME.getDefault()
                    )
                }
            }
        }

        context("empty assertionCreator lambda") {
            it("is the expected type, throws nonetheless"){
                expect {
                    verbs.check("hello").(isACharSequenceFun.lambda) {}
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            ErrorMessages.AT_LEAST_ONE_ASSERTION_DEFINED.getDefault() + ": false",
                            ErrorMessages.FORGOT_DO_DEFINE_ASSERTION.getDefault(),
                            ErrorMessages.HINT_AT_LEAST_ONE_ASSERTION_DEFINED.getDefault()
                        )
                        containsNot( "${DescriptionAnyAssertion.IS_A.getDefault()}: CharSequence")
                    }
                }
            }
            it("is not the expected type, contains the error as well") {
                expect {
                    verbs.check("hello").(isAIntFun.lambda) {}
                }.toThrow<AssertionError> {
                    messageContains(
                        ErrorMessages.AT_LEAST_ONE_ASSERTION_DEFINED.getDefault() + ": false",
                        ErrorMessages.FORGOT_DO_DEFINE_ASSERTION.getDefault(),
                        ErrorMessages.HINT_AT_LEAST_ONE_ASSERTION_DEFINED.getDefault(),
                        "${DescriptionAnyAssertion.IS_A.getDefault()}: Int"
                    )
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
            verbs.check(plant.(andLazyPair.lambda){ toBe(1) }).toBe(plant)
        }
    }

}) {
    data class DataClass(val isWhatever: Boolean)
    open class SuperType
    class SubType : SuperType()
}
