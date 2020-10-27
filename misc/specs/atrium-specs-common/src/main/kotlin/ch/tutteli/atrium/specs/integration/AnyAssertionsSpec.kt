package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.IS_GREATER_THAN
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.IS_LESS_THAN
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class AnyAssertionsSpec(
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
    isNoneOfInt: Fun2<Int, Int, Array<out Int>>,
    isNoneOfDataClass: Fun2<DataClass, DataClass, Array<out DataClass>>,
    isNoneOfNullableInt: Fun2<Int?, Int?, Array<out Int?>>,
    isNoneOfNullableDataClass: Fun2<DataClass?, DataClass?, Array<out DataClass?>>,
    isNotInInt: Fun1<Int, Iterable<Int>>,
    isNotInDataClass: Fun1<DataClass, Iterable<DataClass>>,
    isNotInNullableInt: Fun1<Int?, Iterable<Int?>>,
    isNotInNullableDataClass: Fun1<DataClass?, Iterable<DataClass?>>,
    because: Fun2<String, String, Expect<String>.() -> Unit>,
    becauseInt: Fun2<Int, String, Expect<Int>.() -> Unit>,

    toBeNull: Fun0<Int?>,
    toBeNullIfNullGivenElse: Fun1<Int?, (Expect<Int>.() -> Unit)?>,

    isAIntFeature: Feature0<out Any?, Int>,
    isAInt: Feature1<out Any?, Expect<Int>.() -> Unit, Int>,

    isASuperTypeFeature: Feature0<out Any?, SuperType>,
    isASuperType: Feature1<out Any?, Expect<SuperType>.() -> Unit, SuperType>,
    isASubTypeFeature: Feature0<out Any?, SubType>,
    isASubType: Feature1<out Any?, Expect<SubType>.() -> Unit, SubType>,

    notToBeNullFeature: Feature0<Int?, Int>,
    notToBeNull: Feature1<Int?, Expect<Int>.() -> Unit, Int>,

    andPair: Fun0<Int>,
    andLazyPair: Fun1<Int, Expect<Int>.() -> Unit>,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Int>(
        describePrefix,
        toBeInt.forSubjectLess(1),
        notToBeInt.forSubjectLess(1),
        isSameInt.forSubjectLess(1),
        isNotSameInt.forSubjectLess(1),
        isNoneOfInt.forSubjectLess(1, emptyArray()),
        isNotInInt.forSubjectLess(listOf(1)),
        andPair.forSubjectLess(),
        andLazyPair.forSubjectLess { toBe(1) }
    ) {})

    include(object : SubjectLessSpec<Int?>(
        "$describePrefix[nullable] ",
        toBeNullableInt.forSubjectLess(1),
        notToBeNullableInt.forSubjectLess(1),
        isSameNullableInt.forSubjectLess(1),
        isNotSameNullableInt.forSubjectLess(1),
        isNoneOfNullableInt.forSubjectLess(1, emptyArray()),
        isNotInNullableInt.forSubjectLess(listOf(1)),
        toBeNull.forSubjectLess(),
        isAIntFeature.forSubjectLess(),
        isAInt.forSubjectLess { toBe(1) },
        notToBeNullFeature.forSubjectLess(),
        notToBeNull.forSubjectLess { toBe(1) }
    ) {})

    include(object : AssertionCreatorSpec<Int>(
        describePrefix, 1,
        andLazyPair.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) }
    ) {})
    include(object : AssertionCreatorSpec<Int?>(
        "$describePrefix[nullable Element] ", 1,
        toBeNullIfNullGivenElse.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) },
        assertionCreatorSpecTriple(
            isAInt.name,
            "$toBeDescr: 1",
            { apply { isAInt.invoke(this) { toBe(1) } } },
            { apply { isAInt.invoke(this) {} } }),
        assertionCreatorSpecTriple(
            notToBeNull.name,
            "$toBeDescr: 1",
            { apply { notToBeNull.invoke(this) { toBe(1) } } },
            { apply { notToBeNull.invoke(this) {} } })

    ) {})

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) =
        prefixedDescribeTemplate(describePrefix, description, body)

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun <T : Int?> Suite.checkInt(
        description: String,
        expectSubject: Expect<T>,
        toBe: Fun1<T, Int>,
        notToBe: Fun1<T, Int>,
        isSame: Fun1<T, Int>,
        isNotSame: Fun1<T, Int>,
        isNoneOf: Fun2<T, Int, Array<Int>>,
        isNotIn: Fun1<T, Iterable<Int>>
    ) {
        context(description) {
            val toBeFun = toBe.lambda
            val notToBeFun = notToBe.lambda
            val isSameFun = isSame.lambda
            val isNotSameFun = isNotSame.lambda
            val isNoneOfFun = isNoneOf.lambda
            val isNotInFun = isNotIn.lambda

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
            context("one equals only one of the others") {
                it("${isNoneOf.name} throws AssertionError") {
                    expect {
                        expectSubject.isNoneOfFun(1, arrayOf(2))
                    }.toThrow<AssertionError> {
                        message {
                            contains(IS_NONE_OF.getDefault(), "${listBulletPoint}1")
                            containsNot("$listBulletPoint 2")
                        }
                    }
                }
                it("${isNotIn.name} throws AssertionError") {
                    expect {
                        expectSubject.isNotInFun(listOf(1, 2))
                    }.toThrow<AssertionError> {
                        message {
                            contains(IS_NONE_OF.getDefault(), "${listBulletPoint}1")
                            containsNot("$listBulletPoint 2")
                        }
                    }
                }
            }
            context("one does not equal to any of the others") {
                it("${isNoneOf.name} does not throw") {
                    expectSubject.isNoneOfFun(2, arrayOf(3))
                }
                it("${isNotIn.name} does not throw") {
                    expectSubject.isNotInFun(listOf(2, 3))
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
        isNoneOf: Fun2<T, DataClass, Array<DataClass>>,
        isNotIn: Fun1<T, Iterable<DataClass>>,
        test: DataClass
    ) {
        val toBeFun = toBe.lambda
        val notToBeFun = notToBe.lambda
        val isSameFun = isSame.lambda
        val isNotSameFun = isNotSame.lambda
        val isNoneOfFun = isNoneOf.lambda
        val isNotInFun = isNotIn.lambda

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
                it("${isNoneOf.name} throws AssertionError") {
                    expect {
                        expectSubject.isNoneOfFun(test, emptyArray())
                    }.toThrow<AssertionError> { messageContains(IS_NONE_OF.getDefault()) }
                }
                it("${isNotIn.name} throws AssertionError") {
                    expect {
                        expectSubject.isNotInFun(listOf(test))
                    }.toThrow<AssertionError> { messageContains(IS_NONE_OF.getDefault()) }
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
                it("${isNoneOf.name} throws AssertionError") {
                    expect {
                        expectSubject.isNoneOfFun(other, emptyArray())
                    }.toThrow<AssertionError> { messageContains(IS_NONE_OF.getDefault()) }
                }
                it("${isNotIn.name} throws AssertionError") {
                    expect {
                        expectSubject.isNotInFun(listOf(other))
                    }.toThrow<AssertionError> { messageContains(IS_NONE_OF.getDefault()) }
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
                it("${isNoneOf.name} does not throw") {
                    expectSubject.isNoneOfFun(other, emptyArray())
                }
                it("${isNotIn.name} does not throw") {
                    expectSubject.isNotInFun(listOf(other))
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
        isNoneOf: Fun2<T?, T?, Array<T?>>,
        isNotIn: Fun1<T?, Iterable<T?>>,
        value: T,
        emptyArray: Array<T?>
    ) {

        val toBeFun = toBe.lambda
        val notToBeFun = notToBe.lambda
        val isSameFun = isSame.lambda
        val isNotSameFun = isNotSame.lambda
        val isNoneOfFun = isNoneOf.lambda
        val isNotInFun = isNotIn.lambda
        val expectSubject = expect(null as T?)

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
                it("${isNoneOf.name} throws AssertionError") {
                    expect {
                        expectSubject.isNoneOfFun(null, emptyArray)
                    }.toThrow<AssertionError> { messageContains(IS_NONE_OF.getDefault()) }
                }
                it("${isNotIn.name} throws AssertionError") {
                    expect {
                        expectSubject.isNotInFun(listOf(null))
                    }.toThrow<AssertionError> { messageContains(IS_NONE_OF.getDefault()) }
                }
            }
            context("one does not equal the other") {
                it("${toBe.name} throws AssertionError") {
                    expect {
                        expect(null as T?).toBeFun(value)
                    }.toThrow<AssertionError> {
                        messageContains(TO_BE.getDefault())
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
                it("${isNoneOf.name} does not throw") {
                    expectSubject.isNoneOfFun(value, emptyArray)
                }
                it("${isNotIn.name} does not throw") {
                    expectSubject.isNotInFun(listOf(value))
                }
            }
        }
    }

    describeFun(toBeInt, notToBeInt, isSameInt, isNotSameInt, isNoneOfInt, isNotInInt) {
        checkInt("primitive", expect(1), toBeInt, notToBeInt, isSameInt, isNotSameInt, isNoneOfInt, isNotInInt)
        checkInt(
            "nullable primitive",
            expect(1 as Int?),
            toBeNullableInt,
            notToBeNullableInt,
            isSameNullableInt,
            isNotSameNullableInt,
            isNoneOfNullableInt,
            isNotInNullableInt
        )

        val subject = DataClass(true)
        checkDataClass(
            "class",
            expect(subject),
            toBeDataClass,
            notToBeDataClass,
            isSameDataClass,
            isNotSameDataClass,
            isNoneOfDataClass,
            isNotInDataClass,
            subject
        )
        checkDataClass(
            "nullable class",
            expect(subject as DataClass?),
            toBeNullableDataClass,
            notToBeNullableDataClass,
            isSameNullableDataClass,
            isNotSameNullableDataClass,
            isNoneOfNullableDataClass,
            isNotInNullableDataClass,
            subject
        )

        checkNull(
            "null as Int?",
            toBeNullableInt,
            notToBeNullableInt,
            isSameNullableInt,
            isNotSameNullableInt,
            isNoneOfNullableInt,
            isNotInNullableInt,
            2,
            emptyArray<Int?>()
        )
        checkNull(
            "null as DataClass?",
            toBeNullableDataClass,
            notToBeNullableDataClass,
            isSameNullableDataClass,
            isNotSameNullableDataClass,
            isNoneOfNullableDataClass,
            isNotInNullableDataClass,
            subject,
            emptyArray<DataClass?>()
        )
    }

    describeFun(toBeNull) {

        val toBeNullFun = toBeNull.lambda
        context("subject is null") {
            val subject: Int? = null
            it("does not throw an Exception") {
                expect(subject).toBeNullFun()
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            val testee = expect(1 as Int?)
            val expectFun by memoized {
                expect {
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
                    expectFun.toThrow<AssertionError> { messageContains(Text.NULL.string) }
                }
            }
        }
    }

    describeFun(toBeNullableInt) {
        val toBeNullableFun = toBeNullableInt.lambda

        context("subject is null") {
            val subject: Int? = null
            it("does not throw if null is passed") {
                expect(subject).toBeNullableFun(null)
            }
            it("throws an AssertionError if not null is passed") {
                expect {
                    expect(subject).toBeNullableFun(1)
                }.toThrow<AssertionError> {
                    messageContains(": null", "${TO_BE.getDefault()}: 1")
                }
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            it("does not throw if expected is subject") {
                expect(subject).toBeNullableFun(subject)
            }
            it("throws an AssertionError if null is passed") {
                expect {
                    expect(subject).toBeNullableFun(null)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: null")
                }
            }
            it("throws an AssertionError if expected does not equal subject") {
                expect {
                    expect(subject).toBeNullableFun(2)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: 2")
                }
            }
        }
    }

    describeFun(toBeNullIfNullGivenElse) {
        val toBeNullIfNullElseFun = toBeNullIfNullGivenElse.lambda
        context("subject is null") {
            val subject: Int? = null
            it("does not throw if null is passed") {
                expect(subject).toBeNullIfNullElseFun(null)
            }
            it("throws an AssertionError if not null is passed") {
                expect {
                    expect(subject).toBeNullIfNullElseFun { toBe(1) }
                }.toThrow<AssertionError> {
                    messageContains(": null", "${TO_BE.getDefault()}: 1")
                }
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            it("does not throw if sub assertion holds") {
                expect(subject).toBeNullIfNullElseFun { isLessThan(2) }
            }
            it("throws an AssertionError if sub assertion does not hold") {
                expect {
                    expect(subject).toBeNullIfNullElseFun { isGreaterThan(1) }
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${IS_GREATER_THAN.getDefault()}: 1")
                }
            }
            it("throws an AssertionError if null is passed") {
                expect {
                    expect(subject).toBeNullIfNullElseFun(null)
                }.toThrow<AssertionError> {
                    messageContains(": 1", "${TO_BE.getDefault()}: null")
                }
            }

        }
    }

    describeFun(notToBeNullFeature, notToBeNull) {
        val notToBeNullFunctions = unifySignatures(notToBeNullFeature, notToBeNull)

        context("subject is null") {
            notToBeNullFunctions.forEach { (name, notToBeNullFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        expect(null as Int?).notToBeNullFun { toBe(1) }
                    }.toThrow<AssertionError> {
                        messageContains(IS_A.getDefault() + ": Int (kotlin.Int)")
                        if (hasExtraHint) messageContains("$toBeDescr: 1")
                    }
                }
            }
        }

        context("subject is not null") {


            context("it allows to define an assertion for the subject") {
                notToBeNullFunctions.forEach { (name, notToBeNullFun, _) ->

                    it("$name - does not throw if the assertion holds") {
                        expect(1 as Int?).notToBeNullFun { isLessThan(2) }
                    }

                    it("$name - throws an AssertionError if the assertion does not hold") {
                        expect {
                            expect(1 as Int?).notToBeNullFun { isLessThan(0) }
                        }.toThrow<AssertionError> {
                            messageContains("${IS_LESS_THAN.getDefault()}: 0")
                        }
                    }
                }
            }
            context("it allows to define multiple assertions for the subject") {
                notToBeNullFunctions.forEach { (name, notToBeNullFun, hasExtraHint) ->
                    it("$name - does not throw if the assertions hold") {
                        expect(1 as Int?).notToBeNullFun { isGreaterThan(0); isLessThan(2) }
                    }

                    it("$name - throws an AssertionError if one assertion does not hold") {
                        expect {
                            val i: Int? = 1
                            expect(i).notToBeNullFun { isGreaterThan(2); isLessThan(5) }
                        }.toThrow<AssertionError> {
                            message {
                                contains(IS_GREATER_THAN.getDefault())
                                containsNot(IS_LESS_THAN.getDefault())
                            }
                        }
                    }

                    it("$name - throws an AssertionError if both assertions do not hold " + (if (hasExtraHint) "and contains both messages" else "and contains only first message")) {
                        expect {
                            val i: Int? = 1
                            expect(i).notToBeNullFun { isGreaterThan(2); isLessThan(0) }
                        }.toThrow<AssertionError> {
                            messageContains(IS_GREATER_THAN.getDefault())
                            if (hasExtraHint) messageContains(IS_LESS_THAN.getDefault())
                        }
                    }
                }
            }
        }

        context("in a feature assertion and subject is null") {
            notToBeNullFunctions.forEach { (name, notToBeNullFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {
                    class A(val i: Int? = null)
                    expect {
                        expect(A()).feature(A::i).notToBeNullFun { toBe(1) }
                    }.toThrow<AssertionError> {
                        messageContains(
                            A::class.simpleName!!,
                            IS_A.getDefault() + ": Int (kotlin.Int)"
                        )
                        if (hasExtraHint) messageContains(TO_BE.getDefault() + ": 1")
                    }
                }

                it("$name - throws an AssertionError which contains subsequent assertions") {
                    class A(val i: Int? = null)
                    expect {
                        expect(A()).feature(A::i).notToBeNull { isLessThan(1) }
                    }.toThrow<AssertionError> {
                        messageContains(
                            A::class.simpleName!!,
                            IS_A.getDefault() + ": Int (kotlin.Int)",
                            IS_LESS_THAN.getDefault()

                        )
                    }
                }
            }
        }
    }

    describeFun(isAIntFeature, isAInt) {
        val isAIntFunctions = unifySignatures<Any?, Int>(isAIntFeature, isAInt)

        context("subject is not in type hierarchy") {
            isAIntFunctions.forEach { (name, isAInt, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        expect("hello" as Any?).isAInt { toBe(1) }
                    }.toThrow<AssertionError> {
                        messageContains(IS_A.getDefault() + ": Int (kotlin.Int)")
                        if (hasExtraHint) messageContains(TO_BE.getDefault() + ": 1")
                    }
                }
            }
        }


        context("subject is the same type") {
            context("it allows to perform sub assertions") {
                isAIntFunctions.forEach { (name, isAInt, _) ->
                    it("$name - does not throw if it holds") {
                        expect(1 as Any?).isAInt { isLessThan(2) }
                    }

                    val expectedLessThan = 2
                    val actualValue: Any? = 5
                    it("$name - throws if it does not hold") {
                        expect {
                            expect(actualValue).isAInt { isLessThan(expectedLessThan) }
                        }.toThrow<AssertionError> {
                            messageContains(actualValue as Any, IS_LESS_THAN.getDefault(), expectedLessThan)
                        }
                    }
                }
            }
        }

        context("subject is a subtype") {
            val isASuperTypeFunctions = unifySignatures<Any?, SuperType>(isASuperTypeFeature, isASuperType)

            context("it allows to perform sub assertions") {
                isASuperTypeFunctions.forEach { (name, isASuperType, _) ->
                    it("$name - does not throw if it holds") {
                        val subject = SubType()
                        expect(subject as Any?).isASuperType { isSameAs(subject) }
                    }

                    it("$name - throws if it does not hold") {
                        val subject = SubType()
                        val otherSubType = SubType()
                        expect {
                            expect(subject as Any?).isASuperType { isSameAs(otherSubType) }
                        }.toThrow<AssertionError> {
                            messageContains(subject.toString(), IS_SAME.getDefault(), otherSubType.toString())
                        }
                    }
                }
            }
        }

        context("subject is a supertype") {
            val isASubTypeFunctions = unifySignatures<Any?, SubType>(isASubTypeFeature, isASubType)
            isASubTypeFunctions.forEach { (name, isASubType, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {

                    expect {
                        expect(SuperType() as Any?).isASubType { isSameAs(SubType()) }
                    }.toThrow<AssertionError> {
                        messageContains(
                            SuperType::class.fullName,
                            IS_A.getDefault(), SubType::class.fullName
                        )
                        if (hasExtraHint) messageContains(IS_SAME.getDefault())
                    }
                }
            }
        }
    }

    prefixedDescribe("property `${andPair.name}` immediate") {
        it("returns the same container") {
            val container = expect(1)
            expect(container.(andPair.lambda)()).toBe(container)
        }
    }
    prefixedDescribe("`${andLazyPair.name}` group") {
        it("returns the same container") {
            val container = expect(1)
            expect(container.(andLazyPair.lambda){ toBe(1) }).toBe(container)
        }
    }

    prefixedDescribe("because") {
        val becauseFun = because.lambda
        val becauseFunForInt = becauseInt.lambda

        it("the test on the supplied subject is not throwing an assertion error") {
            expect("filename")
                .becauseFun("? is not allowed in file names on Windows") {
                    containsNot("?")
                }
        }

        it("provoke the failing of one assertion") {
            expect {
                expect("filename?")
                    .becauseFun("? is not allowed in file names on Windows") {
                        containsNot("?")
                        startsWith("f")
                    }
            }.toThrow<AssertionError> {
                messageContains("ℹ ${String.format(BECAUSE.getDefault(), "? is not allowed in file names on Windows")}")
            }
        }

        it("provoke the failing of two assertions") {
            expect {
                expect(21)
                    .becauseFunForInt("we use the definition that teens are between 12 and 18 years old") {
                        isGreaterThanOrEqual(12)
                        isLessThan(18)
                        isNoneOf(21)
                    }
            }.toThrow<AssertionError> {
                messageContains("ℹ ${String.format(BECAUSE.getDefault(), "we use the definition that teens are between 12 and 18 years old")}")
            }
        }
    }

}) {
    data class DataClass(val isWhatever: Boolean)
    open class SuperType
    class SubType : SuperType()
}
