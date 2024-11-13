package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.reporting.reportables.descriptions.ErrorMessages
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

@Suppress("UNUSED_PARAMETER")
data class TestData(val nonNullValue: String, val nullableValue: Int?) {
    fun return0() = nonNullValue
    fun return1(arg1: String) = nonNullValue
    fun return2(arg1: String, arg2: Int?) = nonNullValue
    fun return3(arg1: String, arg2: Int?, arg3: Boolean) = nonNullValue
    fun return4(arg1: String, arg2: Int?, arg3: Boolean, arg4: Double) = nonNullValue
    fun return5(arg1: String, arg2: Int?, arg3: Boolean, arg4: Double, arg5: Char) = nonNullValue
    fun returnNullable0() = nullableValue
    fun returnNullable1(arg1: String) = nullableValue
    fun returnNullable2(arg1: String, arg2: Int?) = nullableValue
    fun returnNullable3(arg1: String, arg2: Int?, arg3: Boolean) = nullableValue
    fun returnNullable4(arg1: String, arg2: Int?, arg3: Boolean, arg4: Double) = nullableValue
    fun returnNullable5(arg1: String, arg2: Int?, arg3: Boolean, arg4: Double, arg5: Char) = nullableValue
}
typealias F = Expect<TestData>.() -> Unit

abstract class FeatureExpectationsSpec(

    propertyImmediate: F,
    propertyLazy: F,
    f0Immediate: F,
    f1Immediate: F,
    f2Immediate: F,
    f3Immediate: F,
    f4Immediate: F,
    f5Immediate: F,
    f0Lazy: F,
    f1Lazy: F,
    f2Lazy: F,
    f3Lazy: F,
    f4Lazy: F,
    f5Lazy: F,

    propertyNullableDoesNotHold: F,
    f0NullableDoesNotHold: F,
    f1NullableDoesNotHold: F,
    f2NullableDoesNotHold: F,
    f3NullableDoesNotHold: F,
    f4NullableDoesNotHold: F,
    f5NullableDoesNotHold: F,

    propertyNullableHolds: F,
    f0NullableHolds: F,
    f1NullableHolds: F,
    f2NullableHolds: F,
    f3NullableHolds: F,
    f4NullableHolds: F,
    f5NullableHolds: F,

    itsLazyWithNestedImmediate: F,
    itsLazyWithNestedLazy: F,

    propertyEmptyAssertionCreator: F,
    f0EmptyAssertionCreator: F,
    f1EmptyAssertionCreator: F,
    f2EmptyAssertionCreator: F,
    f3EmptyAssertionCreator: F,
    f4EmptyAssertionCreator: F,
    f5EmptyAssertionCreator: F,

    isAbleToEvaluateDescription: Boolean,
    additionalContentInException: String? = null,
    return0ImmediateFeatureInfo: String = "${TestData::return0.name}()",
    return1ImmediateFeatureInfo: String = "${TestData::return1.name}(\"a\")",
    return2ImmediateFeatureInfo: String = "${TestData::return2.name}(\"a\", 1)",
    return3ImmediateFeatureInfo: String = "${TestData::return3.name}(\"a\", 1, true)",
    return4ImmediateFeatureInfo: String = "${TestData::return4.name}(\"a\", 1, true, 1.2)",
    return5ImmediateFeatureInfo: String = "${TestData::return5.name}(\"a\", 1, true, 1.2, 'b')",
    return0LazyFeatureInfo: String = "${TestData::return0.name}()",
    return1LazyFeatureInfo: String = "${TestData::return1.name}(\"a\")",
    return2LazyFeatureInfo: String = "${TestData::return2.name}(\"a\", 1)",
    return3LazyFeatureInfo: String = "${TestData::return3.name}(\"a\", 1, true)",
    return4LazyFeatureInfo: String = "${TestData::return4.name}(\"a\", 1, true, 1.2)",
    return5LazyFeatureInfo: String = "${TestData::return5.name}(\"a\", 1, true, 1.2, 'b')",

    return0NullableFeatureInfo: String = "${TestData::returnNullable0.name}()",
    return1NullableFeatureInfo: String = "${TestData::returnNullable1.name}(\"a\")",
    return2NullableFeatureInfo: String = "${TestData::returnNullable2.name}(\"a\", 1)",
    return3NullableFeatureInfo: String = "${TestData::returnNullable3.name}(\"a\", 1, true)",
    return4NullableFeatureInfo: String = "${TestData::returnNullable4.name}(\"a\", 1, true, 1.2)",
    return5NullableFeatureInfo: String = "${TestData::returnNullable5.name}(\"a\", 1, true, 1.2, 'b')",

    lazyWithNestedImmediateFeatureInfo: String = "length",
    lazyWithNestedLazyFeatureInfo: String = "length",

    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) =
        prefixedDescribeTemplate(describePrefix, description, body)


    //@formatter:off
    val functions = arrayOf(
        Triple("property immediate", propertyImmediate, TestData::nonNullValue.name),
        Triple("property lazy", propertyLazy, TestData::nonNullValue.name),
        Triple("fun without arguments and immediate", f0Immediate, return0ImmediateFeatureInfo),
        Triple("fun with 1 argument and immediate", f1Immediate, return1ImmediateFeatureInfo),
        Triple("fun with 2 arguments and immediate", f2Immediate,return2ImmediateFeatureInfo),
        Triple("fun with 3 arguments and immediate", f3Immediate,return3ImmediateFeatureInfo),
        Triple("fun with 4 arguments and immediate", f4Immediate,return4ImmediateFeatureInfo),
        Triple("fun with 5 arguments and immediate", f5Immediate,return5ImmediateFeatureInfo),
        Triple("fun without arguments and lazy", f0Lazy,return0LazyFeatureInfo),
        Triple("fun with 1 argument and lazy", f1Lazy, return1LazyFeatureInfo),
        Triple("fun with 2 arguments and lazy", f2Lazy, return2LazyFeatureInfo),
        Triple("fun with 3 arguments and lazy", f3Lazy, return3LazyFeatureInfo),
        Triple("fun with 4 arguments and lazy", f4Lazy, return4LazyFeatureInfo),
        Triple("fun with 5 arguments and lazy", f5Lazy, return5LazyFeatureInfo)
    )
    val nullableFailingFunctions = arrayOf(
        Triple("property toEqual(null)", propertyNullableDoesNotHold, TestData::nullableValue.name),
        Triple("fun without argument and toEqual(null)", f0NullableDoesNotHold,return0NullableFeatureInfo),
        Triple("fun with 1 argument and toEqual(null)", f1NullableDoesNotHold, return1NullableFeatureInfo),
        Triple("fun with 2 arguments and toEqual(null)", f2NullableDoesNotHold,return2NullableFeatureInfo ),
        Triple("fun with 3 arguments and toEqual(null)", f3NullableDoesNotHold,return3NullableFeatureInfo ),
        Triple("fun with 4 arguments and toEqual(null)", f4NullableDoesNotHold,return4NullableFeatureInfo ),
        Triple("fun with 5 arguments and toEqual(null)", f5NullableDoesNotHold,return5NullableFeatureInfo)
    )
    val nullableHoldsFunctions = arrayOf(
        "property notToBeNull" to propertyNullableHolds,
        "fun with 1 argument and notToBeNull" to f1NullableHolds,
        "fun without argument and notToBeNull" to f0NullableHolds,
        "fun with 2 arguments and notToBeNull" to f2NullableHolds,
        "fun with 3 arguments and notToBeNull" to f3NullableHolds,
        "fun with 4 arguments and notToBeNull" to f4NullableHolds,
        "fun with 5 arguments and notToBeNull" to f5NullableHolds
    )

    include(object: SubjectLessSpec<TestData>(describePrefix,
        *(functions.map { (description, lambda, _) -> description to expectLambda(lambda) }).toTypedArray(),
        *(nullableFailingFunctions.map { (description, lambda, _) -> description to expectLambda(lambda) }).toTypedArray(),
        *(nullableHoldsFunctions.map { (description, lambda) -> description to expectLambda(lambda) }).toTypedArray()
    ) {})

    //@formatter:on

    fun <T> Suite.checkGenericNarrowingAssertionWithExceptionMessage(
        description: String,
        act: (T.() -> Unit) -> Unit,
        vararg methods: Triple<String, (T.() -> Unit), String>
    ) {
        context(description) {
            methods.forEach { (checkMethod, assertion, stringInExceptionMessage) ->
                it("in case of $checkMethod evaluation") {
                    expect {
                        act(assertion)
                    }.toThrow<AssertionError> {
                        messageToContain(stringInExceptionMessage)
                        if (additionalContentInException != null) {
                            messageToContain(additionalContentInException)
                        }
                    }
                }
            }
        }
    }

    fun <T> Suite.checkSubjectNotDefinedExceptionMessage(
        description: String,
        act: (T.() -> Unit) -> Unit,
        vararg methods: Triple<String, (T.() -> Unit), String>
    ) {
        context(description) {
            methods.forEach { (checkMethod, assertion, stringInExceptionMessage) ->
                it("in case of $checkMethod evaluation") {
                    expect {
                        act(assertion)
                    }.toThrow<AssertionError> {
                        if (isAbleToEvaluateDescription) {
                            messageToContain(stringInExceptionMessage)
                        } else {
                            messageToContain(ch.tutteli.atrium.translations.ErrorMessages.DESCRIPTION_BASED_ON_SUBJECT.getDefault())
                        }
                    }
                }
            }
        }
    }

    prefixedDescribe("different feature assertion functions") {

        checkGenericNarrowingAssertionWithExceptionMessage(
            "it throws an AssertionError if the assertion does not hold",
            { andWithCheck ->

                expect(TestData("what ever", 1)).andWithCheck()
            },
            *functions, *nullableFailingFunctions
        )


        checkSubjectNotDefinedExceptionMessage(
            "it throws an AssertionError if the subject is not defined but shows everything in reporting",
            { andWithCheck ->

                expect(listOf(TestData("what ever", 1))).get(100) {
                    andWithCheck()
                }

            },
            *functions, *nullableFailingFunctions
        )

        checkGenericNarrowingAssertion("it does not throw an exception if the assertion holds", { andWithCheck ->

            expect(TestData("hello robert", 1)).andWithCheck()

        }, *functions.map { it.first to it.second }.toTypedArray(), *nullableHoldsFunctions)
    }

    prefixedDescribe("assertion container which checks immediately; use lazy property which has nested...") {
        context("... immediate feature property") {
            it("it does not throw an exception if the assertion holds") {
                expect(TestData("hello robert", 1)).itsLazyWithNestedImmediate()
            }
            it("throws AssertionError if sub-assertion fails") {
                expect {
                    expect(TestData("hello robert and some additional text", 1)).itsLazyWithNestedImmediate()
                }.toThrow<AssertionError> {
                    message {
                        toContainDescr(lazyWithNestedImmediateFeatureInfo, 37)
                        toContainToEqualDescr(12)
                    }
                }
            }
        }
        context("... lazy feature property") {
            it("it does not throw an exception if the assertion holds") {
                expect(TestData("hello robert", 1)).itsLazyWithNestedLazy()
            }
            it("throws AssertionError if sub-assertion fails") {
                expect {
                    expect(TestData("hello robert and some additional text", 1)).itsLazyWithNestedLazy()
                }.toThrow<AssertionError> {
                    message {
                        toContainDescr(lazyWithNestedLazyFeatureInfo, 37)
                        toContainToEqualDescr(12)
                    }
                }
            }
        }
    }

    prefixedDescribe("empty assertion creator lambda throws IllegalArgumentException") {
        mapOf(
            "property" to propertyEmptyAssertionCreator,
            "fun without argument" to f0EmptyAssertionCreator,
            "fun with 1 argument" to f1EmptyAssertionCreator,
            "fun with 2 arguments" to f2EmptyAssertionCreator,
            "fun with 3 arguments" to f3EmptyAssertionCreator,
            "fun with 4 arguments" to f4EmptyAssertionCreator,
            "fun with 5 arguments" to f5EmptyAssertionCreator
        ).forEach { (description, lambda) ->

            context(description) {
                it("throws an IllegalStateException") {
                    expect {
                        expect(TestData("hello robert", 1)).lambda()
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION.string,
                                ErrorMessages.DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED.string
                            )
                            toContainRegex(ErrorMessages.AT_LEAST_ONE_EXPECTATION_DEFINED.string + "\\s+: false",)
                        }
                    }
                }
            }
        }
    }
})
