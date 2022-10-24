package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.separator
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.*
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.TO_BE_GREATER_THAN
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.TO_BE_LESS_THAN
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class AnyExpectationsSpec(
    toEqualInt: Fun1<Int, Int>,
    toEqualDataClass: Fun1<DataClass, DataClass>,
    toEqualNullableInt: Fun1<Int?, Int?>,
    toEqualNullableDataClass: Fun1<DataClass?, DataClass?>,
    notToEqualInt: Fun1<Int, Int>,
    notToEqualDataClass: Fun1<DataClass, DataClass>,
    notToEqualNullableInt: Fun1<Int?, Int?>,
    notToEqualNullableDataClass: Fun1<DataClass?, DataClass?>,
    toBeTheInstanceInt: Fun1<Int, Int>,
    toBeTheInstanceDataClass: Fun1<DataClass, DataClass>,
    toBeTheInstanceNullableInt: Fun1<Int?, Int?>,
    toBeTheInstanceNullableDataClass: Fun1<DataClass?, DataClass?>,
    notToBeTheInstanceInt: Fun1<Int, Int>,
    notToBeTheInstanceDataClass: Fun1<DataClass, DataClass>,
    notToBeTheInstanceNullableInt: Fun1<Int?, Int?>,
    notToBeTheInstanceNullableDataClass: Fun1<DataClass?, DataClass?>,
    notToEqualOneOfInt: Fun2<Int, Int, Array<out Int>>,
    notToEqualOneOfDataClass: Fun2<DataClass, DataClass, Array<out DataClass>>,
    notToEqualOneOfNullableInt: Fun2<Int?, Int?, Array<out Int?>>,
    notToEqualOneOfNullableDataClass: Fun2<DataClass?, DataClass?, Array<out DataClass?>>,
    notToEqualOneInInt: Fun1<Int, Iterable<Int>>,
    notToEqualOneInDataClass: Fun1<DataClass, Iterable<DataClass>>,
    notToBeNullableInt: Fun1<Int?, Iterable<Int?>>,
    notToEqualOneInNullableDataClass: Fun1<DataClass?, Iterable<DataClass?>>,
    because: Fun2<String, String, Expect<String>.() -> Unit>,
    becauseInt: Fun2<Int, String, Expect<Int>.() -> Unit>,

    toBeNull: Fun0<Int?>,
    toBeNullIfNullGivenElse: Fun1<Int?, (Expect<Int>.() -> Unit)?>,

    toBeAnInstanceOfIntFeature: Feature0<out Any?, Int>,
    toBeAnInstanceOfInt: Feature1<out Any?, Expect<Int>.() -> Unit, Int>,

    toBeAnInstanceOfSuperTypeFeature: Feature0<out Any?, SuperType>,
    toBeAnInstanceOfSuperType: Feature1<out Any?, Expect<SuperType>.() -> Unit, SuperType>,
    toBeAnInstanceOfSubTypeFeature: Feature0<out Any?, SubType>,
    toBeAnInstanceOfSubType: Feature1<out Any?, Expect<SubType>.() -> Unit, SubType>,

    notToBeNullFeature: Feature0<Int?, Int>,
    notToBeNull: Feature1<Int?, Expect<Int>.() -> Unit, Int>,

    andPair: Fun0<Int>,
    andLazyPair: Fun1<Int, Expect<Int>.() -> Unit>,

    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Int>(
        describePrefix,
        toEqualInt.forSubjectLess(1),
        notToEqualInt.forSubjectLess(1),
        toBeTheInstanceInt.forSubjectLess(1),
        notToBeTheInstanceInt.forSubjectLess(1),
        notToEqualOneOfInt.forSubjectLess(1, emptyArray()),
        notToEqualOneInInt.forSubjectLess(listOf(1)),
        andPair.forSubjectLess(),
        andLazyPair.forSubjectLess { toEqual(1) }
    ) {})

    include(object : SubjectLessSpec<Int?>(
        "$describePrefix[nullable] ",
        toEqualNullableInt.forSubjectLess(1),
        notToEqualNullableInt.forSubjectLess(1),
        toBeTheInstanceNullableInt.forSubjectLess(1),
        notToBeTheInstanceNullableInt.forSubjectLess(1),
        notToEqualOneOfNullableInt.forSubjectLess(1, emptyArray()),
        notToBeNullableInt.forSubjectLess(listOf(1)),
        toBeNull.forSubjectLess(),
        toBeAnInstanceOfIntFeature.forSubjectLess(),
        toBeAnInstanceOfInt.forSubjectLess { toEqual(1) },
        notToBeNullFeature.forSubjectLess(),
        notToBeNull.forSubjectLess { toEqual(1) }
    ) {})

    include(object : AssertionCreatorSpec<Int>(
        describePrefix, 1,
        andLazyPair.forAssertionCreatorSpec("$toEqualDescr: 1") { toEqual(1) }
    ) {})
    include(object : AssertionCreatorSpec<Int?>(
        "$describePrefix[nullable Element] ", 1,
        toBeNullIfNullGivenElse.forAssertionCreatorSpec("$toEqualDescr: 1") { toEqual(1) },
        assertionCreatorSpecTriple(
            toBeAnInstanceOfInt.name,
            "$toEqualDescr: 1",
            { apply { toBeAnInstanceOfInt.invoke(this) { toEqual(1) } } },
            { apply { toBeAnInstanceOfInt.invoke(this) {} } }),
        assertionCreatorSpecTriple(
            notToBeNull.name,
            "$toEqualDescr: 1",
            { apply { notToBeNull.invoke(this) { toEqual(1) } } },
            { apply { notToBeNull.invoke(this) {} } })

    ) {})

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) =
        prefixedDescribeTemplate(describePrefix, description, body)

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val toBeGreaterThanDescr = TO_BE_GREATER_THAN.getDefault()
    val toBeLessThanDescr = TO_BE_LESS_THAN.getDefault()

    val indentRootBulletPoint = " ".repeat(rootBulletPoint.length)

    fun <T : Int?> Suite.checkInt(
        description: String,
        expectSubject: Expect<T>,
        toEqual: Fun1<T, Int>,
        notToEqual: Fun1<T, Int>,
        toBeTheInstance: Fun1<T, Int>,
        notToBeTheInstance: Fun1<T, Int>,
        notToEqualOneOf: Fun2<T, Int, Array<Int>>,
        notToEqualOneIn: Fun1<T, Iterable<Int>>
    ) {
        context(description) {
            val toEqualFun = toEqual.lambda
            val notToEqualFun = notToEqual.lambda
            val toBeTheInstanceFun = toBeTheInstance.lambda
            val notToBeTheInstanceFun = notToBeTheInstance.lambda
            val notToEqualOneOfFun = notToEqualOneOf.lambda
            val notToEqualOneInFun = notToEqualOneIn.lambda

            context("one equals the other") {
                it("${toEqual.name} does not throw") {
                    expectSubject.toEqualFun(1)
                }
                it("${toBeTheInstance.name} does not throw") {
                    expectSubject.toBeTheInstanceFun(1)
                }
                it("${notToEqual.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualFun(1)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL.getDefault()) }
                }
                it("${notToBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject.notToBeTheInstanceFun(1)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_BE_THE_INSTANCE.getDefault()) }
                }
            }
            context("one does not equal the other") {
                it("${toEqual.name} throws AssertionError") {
                    expect {
                        expectSubject.toEqualFun(2)
                    }.toThrow<AssertionError> { messageToContain(TO_EQUAL.getDefault()) }
                }
                it("${notToEqual.name} does not throw") {
                    expectSubject.notToEqualFun(2)
                }
                it("${toBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject.toBeTheInstanceFun(2)
                    }.toThrow<AssertionError> { messageToContain(TO_BE_THE_INSTANCE.getDefault()) }
                }
                it("${notToBeTheInstance.name} does not throw") {
                    expectSubject.notToBeTheInstanceFun(2)
                }
            }
            context("one equals only one of the others") {
                it("${notToEqualOneOf.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualOneOfFun(1, arrayOf(2))
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$rootBulletPoint${NOT_TO_EQUAL_ONE_IN.getDefault()}\\E:.*$separator" +
                                    "$indentRootBulletPoint${listBulletPoint}1"
                            )
                            notToContain("$listBulletPoint 2")
                        }
                    }
                }
                it("${notToEqualOneIn.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualOneInFun(listOf(1, 2))
                    }.toThrow<AssertionError> {
                        message {
                            toContain(NOT_TO_EQUAL_ONE_IN.getDefault(), "${listBulletPoint}1")
                            notToContain("$listBulletPoint 2")
                        }
                    }
                }
            }
            context("one does not equal to any of the others") {
                it("${notToEqualOneOf.name} does not throw") {
                    expectSubject.notToEqualOneOfFun(2, arrayOf(3))
                }
                it("${notToEqualOneIn.name} does not throw") {
                    expectSubject.notToEqualOneInFun(listOf(2, 3))
                }
            }
        }
    }

    fun <T : DataClass?> Suite.checkDataClass(
        description: String,
        expectSubject: Expect<T>,
        toEqual: Fun1<T, DataClass>,
        notToEqual: Fun1<T, DataClass>,
        toBeTheInstance: Fun1<T, DataClass>,
        notToBeTheInstance: Fun1<T, DataClass>,
        notToEqualOneOf: Fun2<T, DataClass, Array<DataClass>>,
        notToEqualOneIn: Fun1<T, Iterable<DataClass>>,
        test: DataClass
    ) {
        val toEqualFun = toEqual.lambda
        val notToEqualFun = notToEqual.lambda
        val toBeTheInstanceFun = toBeTheInstance.lambda
        val notToBeTheInstanceFun = notToBeTheInstance.lambda
        val notToEqualOneOfFun = notToEqualOneOf.lambda
        val notToEqualOneInFun = notToEqualOneIn.lambda

        context(description) {
            context("same") {
                it("${toEqual.name} does not throw") {
                    expectSubject.toEqualFun(test)
                }
                it("${notToEqual.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualFun(test)
                    }.toThrow<AssertionError>()
                }
                it("${toBeTheInstance.name} does not throw") {
                    expectSubject.toBeTheInstanceFun(test)
                }
                it("${notToBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject.notToBeTheInstanceFun(test)
                    }.toThrow<AssertionError>()
                }
                it("${notToEqualOneOf.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualOneOfFun(test, emptyArray())
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_IN.getDefault()) }
                }
                it("${notToEqualOneIn.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualOneInFun(listOf(test))
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_IN.getDefault()) }
                }
            }
            context("not same but one equals the other") {
                val other = DataClass(true)
                it("${toEqual.name} does not throw") {
                    expectSubject.toEqualFun(other)
                }
                it("${notToEqual.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualFun(other)
                    }.toThrow<AssertionError>()
                }
                it("${toBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject.toBeTheInstanceFun(other)
                    }.toThrow<AssertionError>()
                }
                it("${notToBeTheInstance.name} does not throw") {
                    expectSubject.notToBeTheInstanceFun(other)
                }
                it("${notToEqualOneOf.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualOneOfFun(other, emptyArray())
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_IN.getDefault()) }
                }
                it("${notToEqualOneIn.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualOneInFun(listOf(other))
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_IN.getDefault()) }
                }
            }
            context("one does not equal the other") {
                val other = DataClass(false)
                it("${toEqual.name} does not throw") {
                    expect {
                        expectSubject.toEqualFun(other)
                    }.toThrow<AssertionError>()
                }
                it("${notToEqual.name} throws AssertionError") {
                    expectSubject.notToEqualFun(other)
                }
                it("${toBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject.toBeTheInstanceFun(other)
                    }.toThrow<AssertionError>()
                }
                it("${notToBeTheInstance.name} does not throw") {
                    expectSubject.notToBeTheInstanceFun(other)
                }
                it("${notToEqualOneOf.name} does not throw") {
                    expectSubject.notToEqualOneOfFun(other, emptyArray())
                }
                it("${notToEqualOneIn.name} does not throw") {
                    expectSubject.notToEqualOneInFun(listOf(other))
                }
            }
        }
    }

    fun <T : Any> Suite.checkNull(
        description: String,
        toEqual: Fun1<T?, T?>,
        notToEqual: Fun1<T?, T?>,
        toBeTheInstance: Fun1<T?, T?>,
        notToBeTheInstance: Fun1<T?, T?>,
        notToEqualOneOf: Fun2<T?, T?, Array<T?>>,
        notToEqualOneIn: Fun1<T?, Iterable<T?>>,
        value: T,
        emptyArray: Array<T?>
    ) {

        val toEqualFun = toEqual.lambda
        val notToEqualFun = notToEqual.lambda
        val toBeTheInstanceFun = toBeTheInstance.lambda
        val notToBeTheInstanceFun = notToBeTheInstance.lambda
        val notToEqualOneOfFun = notToEqualOneOf.lambda
        val notToEqualOneInFun = notToEqualOneIn.lambda
        val expectSubject = expect(null as T?)

        context(description) {
            context("one equals the other") {
                it("${toEqual.name} does not throw") {
                    expectSubject.toEqualFun(null)
                }
                it("${toBeTheInstance.name} does not throw") {
                    expectSubject.toBeTheInstanceFun(null)
                }
                it("${notToEqual.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualFun(null)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL.getDefault()) }
                }
                it("${notToBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject.notToBeTheInstanceFun(null)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_BE_THE_INSTANCE.getDefault()) }
                }
                it("${notToEqualOneOf.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualOneOfFun(null, emptyArray)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_IN.getDefault()) }
                }
                it("${notToEqualOneIn.name} throws AssertionError") {
                    expect {
                        expectSubject.notToEqualOneInFun(listOf(null))
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_IN.getDefault()) }
                }
            }
            context("one does not equal the other") {
                it("${toEqual.name} throws AssertionError") {
                    expect {
                        expect(null as T?).toEqualFun(value)
                    }.toThrow<AssertionError> {
                        messageToContain(TO_EQUAL.getDefault())
                    }
                }
                it("${notToEqual.name} does not throw") {
                    expectSubject.notToEqualFun(value)
                }
                it("${toBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject.toBeTheInstanceFun(value)
                    }.toThrow<AssertionError> { messageToContain(TO_BE_THE_INSTANCE.getDefault()) }
                }
                it("${notToBeTheInstance.name} does not throw") {
                    expectSubject.notToBeTheInstanceFun(value)
                }
                it("${notToEqualOneOf.name} does not throw") {
                    expectSubject.notToEqualOneOfFun(value, emptyArray)
                }
                it("${notToEqualOneIn.name} does not throw") {
                    expectSubject.notToEqualOneInFun(listOf(value))
                }
            }
        }
    }

    describeFun(
        toEqualInt,
        notToEqualInt,
        toBeTheInstanceInt,
        notToBeTheInstanceInt,
        notToEqualOneOfInt,
        notToEqualOneInInt
    ) {
        //TODO 0.20.0 report regression, before kotlin 1.6.20 using `this` was not necessary
        this.checkInt(
            "primitive",
            expect(1),
            toEqualInt,
            notToEqualInt,
            toBeTheInstanceInt,
            notToBeTheInstanceInt,
            notToEqualOneOfInt,
            notToEqualOneInInt
        )
        this.checkInt(
            "nullable primitive",
            expect(1 as Int?),
            toEqualNullableInt,
            notToEqualNullableInt,
            toBeTheInstanceNullableInt,
            notToBeTheInstanceNullableInt,
            notToEqualOneOfNullableInt,
            notToBeNullableInt
        )

        val subject = DataClass(true)
        this.checkDataClass(
            "class",
            expect(subject),
            toEqualDataClass,
            notToEqualDataClass,
            toBeTheInstanceDataClass,
            notToBeTheInstanceDataClass,
            notToEqualOneOfDataClass,
            notToEqualOneInDataClass,
            subject
        )
        this.checkDataClass(
            "nullable class",
            expect(subject as DataClass?),
            toEqualNullableDataClass,
            notToEqualNullableDataClass,
            toBeTheInstanceNullableDataClass,
            notToBeTheInstanceNullableDataClass,
            notToEqualOneOfNullableDataClass,
            notToEqualOneInNullableDataClass,
            subject
        )

        this.checkNull(
            "null as Int?",
            toEqualNullableInt,
            notToEqualNullableInt,
            toBeTheInstanceNullableInt,
            notToBeTheInstanceNullableInt,
            notToEqualOneOfNullableInt,
            notToBeNullableInt,
            2,
            emptyArray<Int?>()
        )
        this.checkNull(
            "null as DataClass?",
            toEqualNullableDataClass,
            notToEqualNullableDataClass,
            toBeTheInstanceNullableDataClass,
            notToBeTheInstanceNullableDataClass,
            notToEqualOneOfNullableDataClass,
            notToEqualOneInNullableDataClass,
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
                    expectFun.toThrow<AssertionError> { messageToContain(subject.toString()) }
                }
                it("contains the '${DescriptiveAssertion::description.name}' of the assertion-message - which should be '${toEqualDescr}'") {
                    expectFun.toThrow<AssertionError> { messageToContain(toEqualDescr) }
                }
                it("contains the '${DescriptiveAssertion::representation.name}' of the assertion-message") {
                    expectFun.toThrow<AssertionError> { messageToContain(Text.NULL.string) }
                }
            }
        }
    }

    describeFun(toEqualNullableInt) {
        val toEqualFun = toEqualNullableInt.lambda

        context("subject is null") {
            val subject: Int? = null
            it("does not throw if null is passed") {
                expect(subject).toEqualFun(null)
            }
            it("throws an AssertionError if not null is passed") {
                expect {
                    expect(subject).toEqualFun(1)
                }.toThrow<AssertionError> {
                    messageToContain(": null", "$toEqualDescr: 1")
                }
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            it("does not throw if expected is subject") {
                expect(subject).toEqualFun(subject)
            }
            it("throws an AssertionError if null is passed") {
                expect {
                    expect(subject).toEqualFun(null)
                }.toThrow<AssertionError> {
                    messageToContain(": 1", "$toEqualDescr: null")
                }
            }
            it("throws an AssertionError if expected does not equal subject") {
                expect {
                    expect(subject).toEqualFun(2)
                }.toThrow<AssertionError> {
                    messageToContain(": 1", "$toEqualDescr: 2")
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
                    expect(subject).toBeNullIfNullElseFun { toEqual(1) }
                }.toThrow<AssertionError> {
                    messageToContain(": null", "$toEqualDescr: 1")
                }
            }
        }

        context("subject is not null") {
            val subject: Int? = 1
            it("does not throw if sub assertion holds") {
                expect(subject).toBeNullIfNullElseFun { toBeLessThan(2) }
            }
            it("throws an AssertionError if sub assertion does not hold") {
                expect {
                    expect(subject).toBeNullIfNullElseFun { toBeGreaterThan(1) }
                }.toThrow<AssertionError> {
                    messageToContain(": 1", "$toBeGreaterThanDescr: 1")
                }
            }
            it("throws an AssertionError if null is passed") {
                expect {
                    expect(subject).toBeNullIfNullElseFun(null)
                }.toThrow<AssertionError> {
                    messageToContain(": 1", "$toEqualDescr: null")
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
                        expect(null as Int?).notToBeNullFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        messageToContain("$toBeAnInstanceOfDescr: Int (kotlin.Int)")
                        if (hasExtraHint) messageToContain("$toEqualDescr: 1")
                    }
                }
            }
        }

        context("subject is not null") {


            context("it allows to define an assertion for the subject") {
                notToBeNullFunctions.forEach { (name, notToBeNullFun, _) ->

                    it("$name - does not throw if the assertion holds") {
                        expect(1 as Int?).notToBeNullFun { toBeLessThan(2) }
                    }

                    it("$name - throws an AssertionError if the assertion does not hold") {
                        expect {
                            expect(1 as Int?).notToBeNullFun { toBeLessThan(0) }
                        }.toThrow<AssertionError> {
                            messageToContain("$toBeLessThanDescr: 0")
                        }
                    }
                }
            }
            context("it allows to define multiple assertions for the subject") {
                notToBeNullFunctions.forEach { (name, notToBeNullFun, hasExtraHint) ->
                    it("$name - does not throw if the assertions hold") {
                        expect(1 as Int?).notToBeNullFun { toBeGreaterThan(0); toBeLessThan(2) }
                    }

                    it("$name - throws an AssertionError if one assertion does not hold") {
                        expect {
                            val i: Int? = 1
                            expect(i).notToBeNullFun { toBeGreaterThan(2); toBeLessThan(5) }
                        }.toThrow<AssertionError> {
                            message {
                                toContain(toBeGreaterThanDescr)
                                notToContain(toBeLessThanDescr)
                            }
                        }
                    }

                    it("$name - throws an AssertionError if both assertions do not hold " + (if (hasExtraHint) "and contains both messages" else "and contains only first message")) {
                        expect {
                            val i: Int? = 1
                            expect(i).notToBeNullFun { toBeGreaterThan(2); toBeLessThan(0) }
                        }.toThrow<AssertionError> {
                            messageToContain(toBeGreaterThanDescr)
                            if (hasExtraHint) messageToContain(toBeLessThanDescr)
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
                        expect(A()).feature(A::i).notToBeNullFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            A::class.simpleName!!,
                            "$toBeAnInstanceOfDescr: Int (kotlin.Int)"
                        )
                        if (hasExtraHint) messageToContain("$toEqualDescr: 1")
                    }
                }

                it("$name - throws an AssertionError which contains subsequent expectations") {
                    class A(val i: Int? = null)
                    expect {
                        expect(A()).feature(A::i).notToEqualNull { toBeLessThan(1) }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            A::class.simpleName!!,
                            "$toBeAnInstanceOfDescr: Int (kotlin.Int)",
                            //TODO use $toBeLessThanDescr with Kotlin 1.6 and report to https://youtrack.jetbrains.com/issue/KT-50388
                            "${TO_BE_LESS_THAN.getDefault()}: 1"
                        )
                    }
                }
            }
        }
    }

    describeFun(toBeAnInstanceOfIntFeature, toBeAnInstanceOfInt) {
        val toBeAnInstanceOfIntFunctions = unifySignatures<Any?, Int>(toBeAnInstanceOfIntFeature, toBeAnInstanceOfInt)

        context("subject is not in type hierarchy") {
            toBeAnInstanceOfIntFunctions.forEach { (name, toBeAnInstanceOfInt, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        expect("hello" as Any?).toBeAnInstanceOfInt { toEqual(1) }
                    }.toThrow<AssertionError> {
                        messageToContain("$toBeAnInstanceOfDescr: Int (kotlin.Int)")
                        if (hasExtraHint) messageToContain("$toEqualDescr: 1")
                    }
                }
            }
        }


        context("subject is the same type") {
            context("it allows to perform sub assertions") {
                toBeAnInstanceOfIntFunctions.forEach { (name, toBeAnInstanceOfInt, _) ->
                    it("$name - does not throw if it holds") {
                        expect(1 as Any?).toBeAnInstanceOfInt { toBeLessThan(2) }
                    }

                    val expectedLessThan = 2
                    val actualValue: Any? = 5
                    it("$name - throws if it does not hold") {
                        expect {
                            expect(actualValue).toBeAnInstanceOfInt { toBeLessThan(expectedLessThan) }
                        }.toThrow<AssertionError> {
                            messageToContain(actualValue as Any, toBeLessThanDescr, expectedLessThan)
                        }
                    }
                }
            }
        }

        context("subject is a subtype") {
            val toBeAnInstanceOfSuperTypeFunctions =
                unifySignatures<Any?, SuperType>(toBeAnInstanceOfSuperTypeFeature, toBeAnInstanceOfSuperType)

            context("it allows to perform sub assertions") {
                toBeAnInstanceOfSuperTypeFunctions.forEach { (name, toBeAnInstanceOfSuperType, _) ->
                    it("$name - does not throw if it holds") {
                        val subject = SubType()
                        expect(subject as Any?).toBeAnInstanceOfSuperType { toBeTheInstance(subject) }
                    }

                    it("$name - throws if it does not hold") {
                        val subject = SubType()
                        val otherSubType = SubType()
                        expect {
                            expect(subject as Any?).toBeAnInstanceOfSuperType { toBeTheInstance(otherSubType) }
                        }.toThrow<AssertionError> {
                            messageToContain(
                                subject.toString(),
                                TO_BE_THE_INSTANCE.getDefault(),
                                otherSubType.toString()
                            )
                        }
                    }
                }
            }
        }

        context("subject is a supertype") {
            val toBeAnInstanceOfSubTypeFunctions =
                unifySignatures<Any?, SubType>(toBeAnInstanceOfSubTypeFeature, toBeAnInstanceOfSubType)
            toBeAnInstanceOfSubTypeFunctions.forEach { (name, toBeAnInstanceOfSubType, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {

                    expect {
                        expect(SuperType() as Any?).toBeAnInstanceOfSubType { toBeTheInstance(SubType()) }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            SuperType::class.fullName,
                            TO_BE_AN_INSTANCE_OF.getDefault(), SubType::class.fullName
                        )
                        if (hasExtraHint) messageToContain(TO_BE_THE_INSTANCE.getDefault())
                    }
                }
            }
        }
    }

    prefixedDescribe("property `${andPair.name}` immediate") {
        it("returns the same container") {
            val container = expect(1)
            expect(container.(andPair.lambda)()).toEqual(container)
        }
    }
    prefixedDescribe("`${andLazyPair.name}` group") {
        it("returns the same container") {
            val container = expect(1)
            expect(container.(andLazyPair.lambda){ toEqual(1) }).toEqual(container)
        }
    }

    prefixedDescribe("because") {
        val becauseFun = because.lambda
        val becauseFunForInt = becauseInt.lambda

        fun Expect<String>.containsBecause(reason: String) =
            toContain.exactly(1).value("$separator${informationBulletPoint}${BECAUSE.getDefault().format(reason)}")

        it("the test on the supplied subject is not throwing an assertion error") {
            expect("filename")
                .becauseFun("? is not allowed in file names on Windows") {
                    notToContain("?")
                }
        }

        it("provoke the failing of one assertion") {
            expect {
                expect("filename?")
                    .becauseFun("? is not allowed in file names on Windows") {
                        notToContain("?")
                        toStartWith("f")
                    }
            }.toThrow<AssertionError> {
                message {
                    containsBecause("? is not allowed in file names on Windows")
                }
            }
        }

        it("provoke the failing of two assertions") {
            expect {
                expect(21)
                    .becauseFunForInt("we use the definition that teens are between 12 and 18 years old") {
                        toBeGreaterThanOrEqualTo(12)
                        toBeLessThan(18)
                        notToEqualOneOf(21)
                    }
            }.toThrow<AssertionError> {
                message {
                    containsBecause("we use the definition that teens are between 12 and 18 years old")
                }
            }
        }
    }

}) {
    data class DataClass(val isWhatever: Boolean)
    open class SuperType
    class SubType : SuperType()
}
