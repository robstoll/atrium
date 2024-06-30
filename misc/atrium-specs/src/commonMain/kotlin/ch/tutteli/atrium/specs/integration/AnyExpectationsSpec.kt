package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof.*
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDocumentationUtil
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.*
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.TO_BE_GREATER_THAN
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.TO_BE_LESS_THAN
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

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

    toEqualNull: Fun0<Int?>,
    toEqualNullIfNullGivenElse: Fun1<Int?, (Expect<Int>.() -> Unit)?>,

    toBeAnInstanceOfIntFeature: Feature0<out Any?, Int>,
    toBeAnInstanceOfInt: Feature1<out Any?, Expect<Int>.() -> Unit, Int>,

    toBeAnInstanceOfSuperTypeFeature: Feature0<out Any?, SuperType>,
    toBeAnInstanceOfSuperType: Feature1<out Any?, Expect<SuperType>.() -> Unit, SuperType>,
    toBeAnInstanceOfSubTypeFeature: Feature0<out Any?, SubType>,
    toBeAnInstanceOfSubType: Feature1<out Any?, Expect<SubType>.() -> Unit, SubType>,
    notToBeInstanceOfSuperType: Feature0<Any, out Any?>,
    notToBeInstanceOfKClass: Feature1<Any, KClass<*>, Any>,
    notToBeInstanceOfKClasses: Feature2<Any, KClass<*>, Array<out KClass<*>>, Any>,

    notToEqualNullFeature: Feature0<Int?, Int>,
    notToEqualNull: Feature1<Int?, Expect<Int>.() -> Unit, Int>,

    andPair: Fun0<Int>,
    andLazyPair: Fun1<Int, Expect<Int>.() -> Unit>,

    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Int>(
        describePrefix,
        toEqualInt.forSubjectLessTest(1),
        notToEqualInt.forSubjectLessTest(1),
        toBeTheInstanceInt.forSubjectLessTest(1),
        notToBeTheInstanceInt.forSubjectLessTest(1),
        notToEqualOneOfInt.forSubjectLessTest(1, emptyArray()),
        notToEqualOneInInt.forSubjectLessTest(listOf(1)),
        andPair.forSubjectLessTest(),
        andLazyPair.forSubjectLessTest { toEqual(1) }
    ) {})

    include(object : SubjectLessSpec<Int?>(
        "$describePrefix[nullable] ",
        toEqualNullableInt.forSubjectLessTest(1),
        notToEqualNullableInt.forSubjectLessTest(1),
        toBeTheInstanceNullableInt.forSubjectLessTest(1),
        notToBeTheInstanceNullableInt.forSubjectLessTest(1),
        notToEqualOneOfNullableInt.forSubjectLessTest(1, emptyArray()),
        notToBeNullableInt.forSubjectLessTest(listOf(1)),
        toEqualNull.forSubjectLessTest(),
        toBeAnInstanceOfIntFeature.forSubjectLessTest(),
        toBeAnInstanceOfInt.forSubjectLessTest { toEqual(1) },
        notToEqualNullFeature.forSubjectLessTest(),
        notToEqualNull.forSubjectLessTest { toEqual(1) }
    ) {})
    include(object : SubjectLessSpec<Any>(
        "$describePrefix[Any] ",
        notToBeInstanceOfSuperType.let {
            it.name to expectLambda { it.lambda.invoke(this) }
        },
        notToBeInstanceOfKClass.let {
            it.name to expectLambda { it.lambda.invoke(this, Int::class) }
        },
        notToBeInstanceOfKClasses.let {
            it.name to expectLambda { it.lambda.invoke(this, Int::class, arrayOf(Long::class, String::class)) }
        },
    ) {})

    include(object : AssertionCreatorSpec<Int>(
        describePrefix, 1,
        andLazyPair.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) }
    ) {})
    include(object : AssertionCreatorSpec<Int?>(
        "$describePrefix[nullable Element] ", 1,
        toEqualNullIfNullGivenElse.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) },
        assertionCreatorSpecTriple(
            toBeAnInstanceOfInt.name,
            "$toEqualDescr: 1",
            { apply { toBeAnInstanceOfInt.invoke(this) { toEqual(1) } } },
            { apply { toBeAnInstanceOfInt.invoke(this) {} } }),
        ExpectationCreatorTriple(
            notToEqualNull.name,
            "$toEqualDescr: 1",
            { apply { notToEqualNull.invoke(this) { toEqual(1) } } },
            { apply { notToEqualNull.invoke(this) {} } })

    ) {})

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) =
        prefixedDescribeTemplate(describePrefix, description, body)

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val toBeGreaterThanDescr = TO_BE_GREATER_THAN.getDefault()
    val toBeLessThanDescr = TO_BE_LESS_THAN.getDefault()

    fun <T : Int?> Suite.checkInt(
        description: String,
        expectSubject: () -> Expect<T>,
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
                    expectSubject().toEqualFun(1)
                }
                it("${toBeTheInstance.name} does not throw") {
                    expectSubject().toBeTheInstanceFun(1)
                }
                it("${notToEqual.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualFun(1)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL.string) }
                }
                it("${notToBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject().notToBeTheInstanceFun(1)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_BE_THE_INSTANCE.string) }
                }
                it("${notToEqualOneOf.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneOfFun(1, arrayOf())
                    }.toThrow<AssertionError> {
                        message {
                            toContain("${NOT_TO_EQUAL_ONE_OF.string} :")
                            toContainRegex(
                                "\\Q$failingBulletPoint${NOT_TO_EQUAL_ONE_OF.string}\\E :.*$separator" +
                                    "$indentFailingBulletPoint${failingBulletPoint}1"
                            )
                        }
                    }
                }
                it("${notToEqualOneIn.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneInFun(listOf(1))
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$failingBulletPoint${NOT_TO_EQUAL_ONE_OF.string}\\E :.*$separator" +
                                    "$indentFailingBulletPoint${failingBulletPoint}1"
                            )
                        }
                    }
                }
            }
            context("one does not equal the other") {
                it("${toEqual.name} throws AssertionError") {
                    expect {
                        expectSubject().toEqualFun(2)
                    }.toThrow<AssertionError> { messageToContain(TO_EQUAL.string) }
                }
                it("${notToEqual.name} does not throw") {
                    expectSubject().notToEqualFun(2)
                }
                it("${toBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject().toBeTheInstanceFun(2)
                    }.toThrow<AssertionError> { messageToContain(TO_BE_THE_INSTANCE.string) }
                }
                it("${notToBeTheInstance.name} does not throw") {
                    expectSubject().notToBeTheInstanceFun(2)
                }
                it("${notToEqualOneOf.name} does not throw") {
                    expectSubject().notToEqualOneOfFun(2, arrayOf())
                }
                it("${notToEqualOneIn.name} does not throw") {
                    expectSubject().notToEqualOneInFun(listOf(2))
                }
            }
            context("one equals only one of the others") {
                it("${notToEqualOneOf.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneOfFun(1, arrayOf(2))
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$failingBulletPoint${NOT_TO_EQUAL_ONE_OF.string}\\E :.*$separator" +
                                    "$indentFailingBulletPoint${failingBulletPoint}1"
                            )
                            notToContain("${failingBulletPoint}2")
                        }
                    }
                }
                it("${notToEqualOneIn.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneInFun(listOf(1, 2))
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Q$failingBulletPoint${NOT_TO_EQUAL_ONE_OF.string}\\E :.*$separator" +
                                    "$indentFailingBulletPoint${failingBulletPoint}1"
                            )
                            notToContain("${failingBulletPoint}2")
                        }
                    }
                }
            }
            context("one does not equal to any of the others") {
                it("${notToEqualOneOf.name} does not throw") {
                    expectSubject().notToEqualOneOfFun(2, arrayOf(3))
                }
                it("${notToEqualOneIn.name} does not throw") {
                    expectSubject().notToEqualOneInFun(listOf(2, 3))
                }
            }
        }
    }

    fun <T : DataClass?> Suite.checkDataClass(
        description: String,
        expectSubject: () -> Expect<T>,
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
                    expectSubject().toEqualFun(test)
                }
                it("${notToEqual.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualFun(test)
                    }.toThrow<AssertionError>()
                }
                it("${toBeTheInstance.name} does not throw") {
                    expectSubject().toBeTheInstanceFun(test)
                }
                it("${notToBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject().notToBeTheInstanceFun(test)
                    }.toThrow<AssertionError>()
                }
                it("${notToEqualOneOf.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneOfFun(test, emptyArray())
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_OF.string) }
                }
                it("${notToEqualOneIn.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneInFun(listOf(test))
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_OF.string) }
                }
            }
            context("not same but one equals the other") {
                val other = DataClass(true)
                it("${toEqual.name} does not throw") {
                    expectSubject().toEqualFun(other)
                }
                it("${notToEqual.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualFun(other)
                    }.toThrow<AssertionError>()
                }
                it("${toBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject().toBeTheInstanceFun(other)
                    }.toThrow<AssertionError>()
                }
                it("${notToBeTheInstance.name} does not throw") {
                    expectSubject().notToBeTheInstanceFun(other)
                }
                it("${notToEqualOneOf.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneOfFun(other, emptyArray())
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_OF.string) }
                }
                it("${notToEqualOneIn.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneInFun(listOf(other))
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_OF.string) }
                }
            }
            context("one does not equal the other") {
                val other = DataClass(false)
                it("${toEqual.name} does not throw") {
                    expect {
                        expectSubject().toEqualFun(other)
                    }.toThrow<AssertionError>()
                }
                it("${notToEqual.name} throws AssertionError") {
                    expectSubject().notToEqualFun(other)
                }
                it("${toBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject().toBeTheInstanceFun(other)
                    }.toThrow<AssertionError>()
                }
                it("${notToBeTheInstance.name} does not throw") {
                    expectSubject().notToBeTheInstanceFun(other)
                }
                it("${notToEqualOneOf.name} does not throw") {
                    expectSubject().notToEqualOneOfFun(other, emptyArray())
                }
                it("${notToEqualOneIn.name} does not throw") {
                    expectSubject().notToEqualOneInFun(listOf(other))
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
        val expectSubject = { expect(null as T?) }

        context(description) {
            context("one equals the other") {
                it("${toEqual.name} does not throw") {
                    expectSubject().toEqualFun(null)
                }
                it("${toBeTheInstance.name} does not throw") {
                    expectSubject().toBeTheInstanceFun(null)
                }
                it("${notToEqual.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualFun(null)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL.string) }
                }
                it("${notToBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject().notToBeTheInstanceFun(null)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_BE_THE_INSTANCE.string) }
                }
                it("${notToEqualOneOf.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneOfFun(null, emptyArray)
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_OF.string) }
                }
                it("${notToEqualOneIn.name} throws AssertionError") {
                    expect {
                        expectSubject().notToEqualOneInFun(listOf(null))
                    }.toThrow<AssertionError> { messageToContain(NOT_TO_EQUAL_ONE_OF.string) }
                }
            }
            context("one does not equal the other") {
                it("${toEqual.name} throws AssertionError") {
                    expect {
                        expect(null as T?).toEqualFun(value)
                    }.toThrow<AssertionError> {
                        messageToContain(TO_EQUAL.string)
                    }
                }
                it("${notToEqual.name} does not throw") {
                    expectSubject().notToEqualFun(value)
                }
                it("${toBeTheInstance.name} throws AssertionError") {
                    expect {
                        expectSubject().toBeTheInstanceFun(value)
                    }.toThrow<AssertionError> { messageToContain(TO_BE_THE_INSTANCE.string) }
                }
                it("${notToBeTheInstance.name} does not throw") {
                    expectSubject().notToBeTheInstanceFun(value)
                }
                it("${notToEqualOneOf.name} does not throw") {
                    expectSubject().notToEqualOneOfFun(value, emptyArray)
                }
                it("${notToEqualOneIn.name} does not throw") {
                    expectSubject().notToEqualOneInFun(listOf(value))
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
        checkInt(
            "primitive",
            { expect(1) },
            toEqualInt,
            notToEqualInt,
            toBeTheInstanceInt,
            notToBeTheInstanceInt,
            notToEqualOneOfInt,
            notToEqualOneInInt
        )
        checkInt(
            "nullable primitive",
            { expect(1 as Int?) },
            toEqualNullableInt,
            notToEqualNullableInt,
            toBeTheInstanceNullableInt,
            notToBeTheInstanceNullableInt,
            notToEqualOneOfNullableInt,
            notToBeNullableInt
        )

        val subject = DataClass(true)
        checkDataClass(
            "class",
            { expect(subject) },
            toEqualDataClass,
            notToEqualDataClass,
            toBeTheInstanceDataClass,
            notToBeTheInstanceDataClass,
            notToEqualOneOfDataClass,
            notToEqualOneInDataClass,
            subject
        )
        checkDataClass(
            "nullable class",
            { expect(subject as DataClass?) },
            toEqualNullableDataClass,
            notToEqualNullableDataClass,
            toBeTheInstanceNullableDataClass,
            notToBeTheInstanceNullableDataClass,
            notToEqualOneOfNullableDataClass,
            notToEqualOneInNullableDataClass,
            subject
        )

        checkNull(
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
        checkNull(
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

    describeFun(toEqualNull) {

        val toEqualNullFun = toEqualNull.lambda
        context("subject is null") {
            val subject: Int? = null
            it("does not throw an Exception") {
                expect(subject).toEqualNullFun()
            }
        }

        context("subject is not null") {
            val subject = 1 as Int?
            val testee = expect(1 as Int?)
            val expectFun by memoized {
                expect {
                    testee.toEqualNullFun()
                }
            }
            context("throws an AssertionError and exception message") {
                it("contains the subject") {
                    expectFun.toThrow<AssertionError> { messageToContain(subject.toString()) }
                }
                it("contains the description of the assertion-message - which should be '${toEqualDescr}'") {
                    expectFun.toThrow<AssertionError> { messageToContain(toEqualDescr) }
                }
                it("contains the representation of the assertion-message") {
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
                    message {
                        toContainSubject(null)
                        toContainToEqualDescr(1)
                    }
                }
            }
        }

        context("subject is not null") {
            val subject = 1 as Int?
            it("does not throw if expected is subject") {
                expect(subject).toEqualFun(subject)
            }
            it("throws an AssertionError if null is passed") {
                expect {
                    expect(subject).toEqualFun(null)
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject(1)
                        toContainToEqualDescr(null)
                    }
                }
            }
            it("throws an AssertionError if expected does not equal subject") {
                expect {
                    expect(subject).toEqualFun(2)
                }.toThrow<AssertionError> {
                    message {
                        toContainToEqualDescr(2)
                    }
                }
            }
        }
    }

    describeFun(toEqualNullIfNullGivenElse) {
        val toEqualNullIfNullElseFun = toEqualNullIfNullGivenElse.lambda
        context("subject is null") {
            val subject: Int? = null
            it("does not throw if null is passed") {
                expect(subject).toEqualNullIfNullElseFun(null)
            }
            it("throws an AssertionError if not null is passed") {
                expect {
                    expect(subject).toEqualNullIfNullElseFun { toEqual(1) }
                }.toThrow<AssertionError> {
                    message {
                        toContainToEqualDescr(1)
                    }
                }
            }
        }

        context("subject is not null") {
            val subject = 1 as Int?
            it("does not throw if sub assertion holds") {
                expect(subject).toEqualNullIfNullElseFun { toBeLessThan(2) }
            }
            it("throws an AssertionError if sub assertion does not hold") {
                expect {
                    expect(subject).toEqualNullIfNullElseFun { toBeGreaterThan(1) }
                }.toThrow<AssertionError> {
                    message {
                        toContainToBeGreaterDescr(1)
                    }
                }
            }
            it("throws an AssertionError if null is passed") {
                expect {
                    expect(subject).toEqualNullIfNullElseFun(null)
                }.toThrow<AssertionError> {
                    message {
                        toContainToEqualDescr(null)
                    }
                }
            }
        }
    }


    describeFun(notToEqualNullFeature, notToEqualNull) {
        val notToEqualNullFunctions = unifySignatures(notToEqualNullFeature, notToEqualNull)

        context("subject is null") {
            notToEqualNullFunctions.forEach { (name, notToEqualNullFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        expect(null as Int?).notToEqualNullFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr(notToEqualNullButToBeInstanceOfDescr, "Int (kotlin.Int)")
                            if (hasExtraHint) toContainToEqualDescr(1)
                        }

                    }
                }
            }
        }

        context("subject is not null") {

            context("it allows to define an assertion for the subject") {
                notToEqualNullFunctions.forEach { (name, notToBeNullFun, _) ->

                    it("$name - does not throw if the assertion holds") {
                        expect(1 as Int?).notToBeNullFun { toBeLessThan(2) }
                    }

                    it("$name - throws an AssertionError if the assertion does not hold") {
                        expect {
                            expect(1 as Int?).notToBeNullFun { toBeLessThan(0) }
                        }.toThrow<AssertionError> {
                            message {
                                toContainToBeLessThanDescr(0)
                            }
                        }
                    }
                }
            }
            context("it allows to define multiple assertions for the subject") {
                notToEqualNullFunctions.forEach { (name, notToBeNullFun, hasExtraHint) ->
                    it("$name - does not throw if the assertions hold") {
                        expect(1 as Int?).notToBeNullFun { toBeGreaterThan(0); toBeLessThan(2) }
                    }

                    it("$name - throws an AssertionError if one assertion does not hold") {
                        expect {
                            val i = 1 as Int?
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
                            val i = 1 as Int?
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
            notToEqualNullFunctions.forEach { (name, notToBeNullFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubExpectationIf(hasExtraHint)) {
                    class A(val i: Int? = null)
                    expect {
                        expect(A()).feature(A::i).notToBeNullFun { toEqual(1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainSubject(A::class.fullName)
                            toContainDescr(notToEqualNullButToBeInstanceOfDescr, "Int (kotlin.Int)")
                            if (hasExtraHint) toContainToEqualDescr(1)
                        }
                    }
                }

                it("$name - throws an AssertionError which contains subsequent expectations") {
                    class A(val i: Int? = null)
                    expect {
                        expect(A()).feature(A::i).notToEqualNull { toBeLessThan(1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainSubject(A::class.fullName)
                            toContainDescr(notToEqualNullButToBeInstanceOfDescr, "Int (kotlin.Int)")
                            toContainToBeLessThanDescr(1)
                        }
                    }
                }
            }
        }
    }

    describeFun(toBeAnInstanceOfIntFeature, toBeAnInstanceOfInt) {
        val toBeAnInstanceOfIntFunctions = unifySignatures<Any?, Int>(toBeAnInstanceOfIntFeature, toBeAnInstanceOfInt)

        context("subject is not in type hierarchy") {
            toBeAnInstanceOfIntFunctions.forEach { (name, toBeAnInstanceOfInt, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        expect("hello" as Any?).toBeAnInstanceOfInt { toEqual(1) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr(TO_BE_AN_INSTANCE_OF, "Int (kotlin.Int)")
                            if (hasExtraHint) toContainToEqualDescr(1)
                        }
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
                    val actualValue = 5 as Any?
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
                                TO_BE_THE_INSTANCE.string,
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
                it("$name - throws an AssertionError" + showsSubExpectationIf(hasExtraHint)) {

                    expect {
                        expect(SuperType() as Any?).toBeAnInstanceOfSubType { toBeTheInstance(SubType()) }
                    }.toThrow<AssertionError> {
                        messageToContain(
                            SuperType::class.fullName,
                            TO_BE_AN_INSTANCE_OF.string, SubType::class.fullName
                        )
                        if (hasExtraHint) messageToContain(TO_BE_THE_INSTANCE.string)
                    }
                }
            }
        }
    }

    describeFun(notToBeInstanceOfSuperType) {
        val notToBeInstanceOfSuperTypeFun = notToBeInstanceOfSuperType.lambda
        val notToBeInstanceOfKClassFun = notToBeInstanceOfKClass.lambda
        val notToBeInstanceOfKClassesFun = notToBeInstanceOfKClasses.lambda
        context("subject is not in type hierarchy") {
            it("${notToBeInstanceOfSuperType.name} -- does not throw") {
                expect(1 as Any).notToBeInstanceOfSuperTypeFun()
            }
            it("${notToBeInstanceOfKClass.name} -- does not throw") {
                expect(1 as Any).notToBeInstanceOfKClassFun(String::class)
            }
            it("${notToBeInstanceOfKClasses.name} -- does not throw") {
                expect(1 as Any).notToBeInstanceOfKClassesFun(String::class, arrayOf(Long::class, Double::class))
            }
        }

        context("subject is the same type") {
            it("${notToBeInstanceOfSuperType.name} -- throws") {
                expect {
                    expect(SuperType() as Any).notToBeInstanceOfSuperTypeFun()
                }.toThrow<AssertionError> {
                    message {
                        toContain(NOT_TO_BE_AN_INSTANCE_OF.string)
                        toContain(SuperType::class.simpleName!!)
                    }
                }
            }
            it("${notToBeInstanceOfKClass.name} -- throws") {
                expect {
                    expect(SuperType() as Any).notToBeInstanceOfKClassFun(SuperType::class)
                }.toThrow<AssertionError> {
                    message {
                        toContain(NOT_TO_BE_AN_INSTANCE_OF.string)
                        toContain(SuperType::class.simpleName!!)
                    }
                }
            }
            it("${notToBeInstanceOfKClasses.name} -- throws if first type is SuperType") {
                expect {
                    expect(SuperType() as Any).notToBeInstanceOfKClassesFun(
                        SuperType::class,
                        arrayOf(Long::class, Double::class)
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContain(NOT_TO_BE_AN_INSTANCE_OF.string)
                        toContain(SuperType::class.simpleName!!)
                        notToContain(Long::class.simpleName!!)
                        notToContain(Double::class.simpleName!!)
                    }
                }
            }
            it("${notToBeInstanceOfKClasses.name} -- throws if one of the other types is SuperType") {
                expect {
                    expect(SuperType() as Any).notToBeInstanceOfKClassesFun(
                        Int::class,
                        arrayOf(Long::class, SuperType::class)
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContain(NOT_TO_BE_AN_INSTANCE_OF.string)
                        toContain(SuperType::class.simpleName!!)
                        notToContain(Int::class.simpleName!!)
                        notToContain(Long::class.simpleName!!)
                    }
                }
            }
        }

        context("subject is a subtype") {
            it("${notToBeInstanceOfSuperType.name} -- throws") {
                expect {
                    expect(SubType() as Any).notToBeInstanceOfSuperTypeFun()
                }.toThrow<AssertionError> {
                    message {
                        toContain(NOT_TO_BE_AN_INSTANCE_OF.string)
                        toContain(SuperType::class.simpleName!!)
                    }
                }
            }
            it("${notToBeInstanceOfKClass.name} -- throws") {
                expect {
                    expect(SubType() as Any).notToBeInstanceOfKClassFun(SuperType::class)
                }.toThrow<AssertionError> {
                    message {
                        toContain(NOT_TO_BE_AN_INSTANCE_OF.string)
                        toContain(SuperType::class.simpleName!!)
                    }
                }
            }
            it("${notToBeInstanceOfKClasses.name} -- throws if first type is a subtype") {
                expect {
                    expect(SubType() as Any).notToBeInstanceOfKClassesFun(
                        SuperType::class,
                        arrayOf(Long::class, Double::class)
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContain(NOT_TO_BE_AN_INSTANCE_OF.string)
                        toContain(SuperType::class.simpleName!!)
                        notToContain(Long::class.simpleName!!)
                        notToContain(Double::class.simpleName!!)
                    }
                }
            }
            it("${notToBeInstanceOfKClasses.name} -- throws if one of the other types is a subtype ") {
                expect {
                    expect(SubType() as Any).notToBeInstanceOfKClassesFun(
                        Int::class,
                        arrayOf(SuperType::class, Double::class)
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContain(NOT_TO_BE_AN_INSTANCE_OF.string)
                        toContain(SuperType::class.simpleName!!)
                        notToContain(Int::class.simpleName!!)
                        notToContain(Long::class.simpleName!!)
                    }
                }
            }

            it("${notToBeInstanceOfKClasses.name} -- throws and lists all types which violates the expectation") {
                expect {
                    expect(SubType() as Any).notToBeInstanceOfKClassesFun(
                        SuperInterface::class,
                        arrayOf(Double::class, SuperType::class, SubType::class, Long::class)
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContain(NOT_TO_BE_AN_INSTANCE_OF.string)
                        toContain(SuperInterface::class.simpleName!!)
                        toContain(SuperType::class.simpleName!!)
                        toContain(SubType::class.simpleName!!)
                        notToContain(Double::class.simpleName!!)
                        notToContain(Long::class.simpleName!!)
                    }
                }
            }
        }

        context("subject is a supertype") {
            it("${notToBeInstanceOfSuperType.name} -- does not throw") {
                expect(object : SuperInterface {} as Any).notToBeInstanceOfSuperTypeFun()
            }
            it("${notToBeInstanceOfKClass.name} -- does not throw") {
                expect(object : SuperInterface {} as Any).notToBeInstanceOfKClassFun(SuperType::class)
            }
            it("${notToBeInstanceOfKClasses.name} -- does not throw") {
                expect(object : SuperInterface {} as Any).notToBeInstanceOfKClassesFun(
                    SubType::class,
                    arrayOf(Long::class, Double::class)
                )
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
            toContain.exactly(1)
                .matchFor(Regex("$separator\\Q${informationBulletPoint}${DescriptionDocumentationUtil.BECAUSE.string}\\E\\s+: \\Q$reason\\E"))

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
    interface SuperInterface
    open class SuperType : SuperInterface
    class SubType : SuperType()
}
